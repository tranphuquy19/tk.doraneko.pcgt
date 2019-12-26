package services.socket;

import commons.ConsoleColors;
import commons.Constants;
import gui.ClientFrame;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author tranphuquy19@gmail.com
 * @since 14/10/2019
 */
public class Client {
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    private final int MAX_LENGTH_SEND = 20000;

    private String host;
    private int port;
    private ClientFrame clientGui;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    public Client(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        start();
    }

    public Client(String host, int port, ClientFrame clientGui) throws IOException {
        this.host = host;
        this.port = port;
        this.clientGui = clientGui;
        start();
    }

    private void start() throws IOException {
        socket = new Socket(host, port);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());

        System.out.println("Connect to server successfully");
    }

    public void sendFile(String filePath) throws IOException {
        File sourceFile = new File(filePath);
        byte[] temp = Packet.createDataPacket(Packet.COMMAND_SEND_FILE, sourceFile.getName().getBytes("UTF8"));
        dataOutputStream.write(temp);
        dataOutputStream.write(Packet.createDataPacket(Packet.COMMAND_SEND_FILE_NAME, sourceFile.getName().getBytes("UTF8")));
        RandomAccessFile randomAccessFile = new RandomAccessFile(sourceFile, "r");

        dataOutputStream.write(Packet.createDataPacket(Packet.COMMAND_SEND_FILE_LENGTH, String.valueOf(randomAccessFile.length()).getBytes("UTF8")));
        long currentFilePointer;
        boolean loopBreak = false;

        while (!socket.isClosed()) {
            if (dataInputStream.readByte() == Packet.INITIALIZE) {
                dataInputStream.readByte(); // tiêu thụ SEPARATOR
                byte[] cmdBuff = new byte[3];
                dataInputStream.read(cmdBuff, 0, cmdBuff.length);

                byte[] recvBuff = Packet.readStream(dataInputStream);

                String cmd = new String(cmdBuff);
                switch (cmd) {
                    case Packet.COMMAND_REQUEST_SEND_FILE_DATA:
                        currentFilePointer = Long.valueOf(new String(recvBuff));
                        long residualLen = randomAccessFile.length() - currentFilePointer; //phần dư ra sau khi cắt file
                        int buffLen = (int) (residualLen < MAX_LENGTH_SEND ? residualLen : MAX_LENGTH_SEND); // tính toán phần data phải gửi
                        byte[] tempBuff = new byte[buffLen];

                        if (currentFilePointer != randomAccessFile.length()) { // == nếu con tro file nằm cuối file
                            randomAccessFile.seek(currentFilePointer);
                            randomAccessFile.read(tempBuff, 0, tempBuff.length); // fill vào mảng

                            dataOutputStream.write(Packet.createDataPacket(Packet.COMMAND_SEND_FILE_DATA, tempBuff));
                            dataOutputStream.flush();

                            float percent = ((float) (currentFilePointer + tempBuff.length) / randomAccessFile.length()) * 100;
                            try{
                                clientGui.getProgressBar().setValue((int) percent);
                                clientGui.getTxtStatus().setText(clientGui.getTxtStatus().getText() + "Upload percentage: " + percent + "%\n");
                                if((int)percent == 100)
                                    clientGui.getTxtStatus().setText(clientGui.getTxtStatus().getText() + "The server is processing, please wait a few seconds...\n" +
                                            "The server has finished processing\n" +
                                            "opening result file\n");
                                clientGui.getScrollPane().getVerticalScrollBar().setValue(clientGui.getScrollPane().getVerticalScrollBar().getMaximum());
                            }catch (Exception e){
//                                e.printStackTrace();
                            }
                        } else {
                            loopBreak = true;
                        }
                        break;
                }// end switch
            }
            if (loopBreak == true) {
                dataOutputStream.write(Packet.createDataPacket(Packet.COMMAND_SEND_FINISH, "Close".getBytes("UTF8")));
                dataOutputStream.flush();
                randomAccessFile.close();
                socket.close();
                break;
            }
        }// end while
    }

    public static void main(String[] args) throws IOException {
        Client client;
        Server server = new Server(Constants.CLIENT_CHANNEL, Constants.CLIENT_RES);
        server.setForClient(true);

        Scanner sc = new Scanner(System.in);
        String cmdArgs = null;

        try {
            cmdArgs = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        cmdArgs = "true";
        if (!cmdArgs.isEmpty()) {
            String temp = cmdArgs;
            if ("true".equals(temp)) {
                while (true) {
                    client = new Client("0.0.0.0", Constants.SERVER_CHANNEL);
                    JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                    int returnValue = jFileChooser.showOpenDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = jFileChooser.getSelectedFile();
                        client.sendFile(selectedFile.getAbsolutePath());
                    }
                    System.out.println(ConsoleColors.GREEN_BOLD + "Enter if you want send more or Ctrl+C" + ConsoleColors.RESET);
                    sc.nextLine();
                }
            } else {
                client = new Client("0.0.0.0", 16057);
                client.sendFile(temp);
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
