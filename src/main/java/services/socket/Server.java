package services.socket;

import commons.Constants;
import services.control.MasterControl;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author tranphuquy19@gmail.com
 * @since 14/10/2019
 */
public class Server implements Runnable {
    private ServerSocket serverSocket;
    private Thread thread;
    private ServerThread serverThread;

    private int port;
    private String workingDir;
    private boolean forClient = false;

    public Server(int port, String workingDir) throws IOException {
        this.port = port;
        this.workingDir = workingDir;
        open();
        start();
    }

    @Override
    public void run() {
        while (thread != null) {
            try {
                addThread(serverSocket.accept());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void open() throws IOException {
        serverSocket = new ServerSocket(this.port);
        System.out.println("Server is open on port: " + this.port);
    }

    private void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    private void addThread(Socket socket) throws IOException {
        System.out.println("Client is accepted");
        System.out.println("Socket: " + socket);
        serverThread = new ServerThread(socket);
        serverThread.open();
        serverThread.start();
    }

    private class ServerThread extends Thread {
        private Socket socket;
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;

        public ServerThread(Socket socket) {
            this.socket = socket;
        }

        public void open() throws IOException {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }

        public void run() {
            System.out.println("Listening client");
            RandomAccessFile randomAccessFile = null;
            long currentFilePointer = 0;
            String fileName = "";
            long fileLength = 1;
            boolean loopBreak = false;

            while (!socket.isClosed()) {
                try {
                    boolean closed = socket.isClosed();
                    if (dataInputStream.readByte() == Packet.INITIALIZE) {
                        int b = 0;
                        dataInputStream.readByte(); // tieu thu SEPARATOR
                        byte[] cmdBuff = new byte[3];
                        dataInputStream.read(cmdBuff, 0, cmdBuff.length);
                        byte[] recvData = Packet.readStream(dataInputStream);
                        String cmd = new String(cmdBuff);
                        switch (cmd) {
                            case Packet.COMMAND_SEND_FILE_NAME:
                                fileName = new String(recvData);
                                randomAccessFile = new RandomAccessFile(workingDir + fileName, "rw");
                                dataOutputStream.write(Packet.createDataPacket(Packet.COMMAND_REQUEST_SEND_FILE_DATA, String.valueOf(currentFilePointer).getBytes("UTF8")));
                                dataOutputStream.flush();
                                break;
                            case Packet.COMMAND_SEND_FILE_LENGTH:
                                fileLength = Long.parseLong(new String(recvData));
                                break;
                            case Packet.COMMAND_SEND_FILE_DATA:
                                randomAccessFile.seek(currentFilePointer);
                                randomAccessFile.write(recvData);
                                currentFilePointer = randomAccessFile.getFilePointer();
                                float percent = ((float) currentFilePointer / fileLength) * 100;
                                System.out.println("Download percentage: " + percent + "%");
                                dataOutputStream.write(Packet.createDataPacket(Packet.COMMAND_REQUEST_SEND_FILE_DATA, String.valueOf(currentFilePointer).getBytes("UTF8")));
                                dataOutputStream.flush();
                                break;
                            case Packet.COMMAND_SEND_FINISH:
                                if (!forClient) {
                                    String toFilename = MasterControl.excute(fileName, workingDir);
                                    Client client = new Client(socket.getInetAddress().getHostAddress(), Constants.CLIENT_CHANNEL);
                                    client.sendFile(workingDir + toFilename);
                                    if ("Close".equals(new String(recvData))) {
                                        loopBreak = true;
                                    }
                                } else {
                                    File revFile = new File(Constants.CLIENT_RES + fileName);
                                    Desktop desktop = Desktop.getDesktop();
                                    if (revFile.exists()) desktop.open(revFile);
                                }
                                break;
                        }
                    }
                    if (loopBreak) {
                        randomAccessFile.close();
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        randomAccessFile.close();
                        socket.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } //end while
        }

    }

    /**
     * Mặc định nơi lưu file là workingDir + "/res/files/"
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new Server(Constants.SERVER_CHANNEL, Constants.SERVER_RES);
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public ServerThread getServerThread() {
        return serverThread;
    }

    public void setServerThread(ServerThread serverThread) {
        this.serverThread = serverThread;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWorkingDir() {
        return workingDir;
    }

    public void setWorkingDir(String workingDir) {
        this.workingDir = workingDir;
    }

    public boolean isForClient() {
        return forClient;
    }

    public void setForClient(boolean forClient) {
        this.forClient = forClient;
    }
}
