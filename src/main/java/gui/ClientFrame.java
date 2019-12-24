package gui;

import commons.Constants;
import services.socket.Client;
import services.socket.Server;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/*
 * @author tranphuquy19@gmail.com
 * @since 12/24/2019
 */
public class ClientFrame extends JFrame {
    private JPanel contentPane;
    private JTextField txtServerAddress;
    private JTextField txtServerPort;
    private JButton btnChooseFile;
    private JTextArea txtStatus;
    private JProgressBar progressBar;
    private JScrollPane scrollPane;
    private JLabel lblChngTrnhPhn;

    public ClientFrame() {
        initComponent();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(Constants.CLIENT_CHANNEL, Constants.CLIENT_RES);
        server.setForClient(true);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientFrame clientGui = new ClientFrame();
                    clientGui.getBtnChooseFile().addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            Thread thread = new Thread() {
                                @Override
                                public void run() {
                                    String serverAddress = clientGui.getTxtServerAddress().getText();
                                    String serverPort = clientGui.getTxtServerPort().getText();
                                    try {
                                        Client client = new Client(serverAddress, Integer.parseInt(serverPort), clientGui);
                                        JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                                        int returnValue = jFileChooser.showOpenDialog(null);
                                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                                            File selectedFile = jFileChooser.getSelectedFile();
                                            client.sendFile(selectedFile.getAbsolutePath());
                                        }
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            };
                            thread.start();
                        }
                    });
                    clientGui.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initComponent() {
        setTitle("Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 596, 358);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Server Address");
        lblNewLabel.setBounds(10, 51, 104, 14);
        contentPane.add(lblNewLabel);

        txtServerAddress = new JTextField();
        txtServerAddress.setText("0.0.0.0");
        txtServerAddress.setBounds(137, 48, 216, 20);
        contentPane.add(txtServerAddress);
        txtServerAddress.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Server Port");
        lblNewLabel_1.setBounds(10, 91, 74, 14);
        contentPane.add(lblNewLabel_1);

        txtServerPort = new JTextField();
        txtServerPort.setText("16057");
        txtServerPort.setBounds(137, 88, 216, 20);
        contentPane.add(txtServerPort);
        txtServerPort.setColumns(10);

        txtStatus = new JTextArea();
        txtStatus.setEditable(false);
        txtStatus.setBounds(10, 126, 478, 122);
//        contentPane.add(txtStatus);

        scrollPane = new JScrollPane(txtStatus);
        scrollPane.setBounds(10, 126, 478, 122);
        contentPane.add(scrollPane);

        btnChooseFile = new JButton("Choose File");
        btnChooseFile.setBounds(10, 259, 200, 23);
        contentPane.add(btnChooseFile);

        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(235, 259, 146, 14);
        contentPane.add(progressBar);

        lblChngTrnhPhn = new JLabel("Chuong trinh phan cong giam thi");
        lblChngTrnhPhn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblChngTrnhPhn.setHorizontalAlignment(SwingConstants.CENTER);
        lblChngTrnhPhn.setBounds(10, 11, 560, 28);
        contentPane.add(lblChngTrnhPhn);
    }

    public JTextField getTxtServerAddress() {
        return txtServerAddress;
    }

    public void setTxtServerAddress(JTextField txtServerAddress) {
        this.txtServerAddress = txtServerAddress;
    }

    public JTextField getTxtServerPort() {
        return txtServerPort;
    }

    public void setTxtServerPort(JTextField txtServerPort) {
        this.txtServerPort = txtServerPort;
    }

    public JButton getBtnChooseFile() {
        return btnChooseFile;
    }

    public void setBtnChooseFile(JButton btnChooseFile) {
        this.btnChooseFile = btnChooseFile;
    }

    public JTextArea getTxtStatus() {
        return txtStatus;
    }

    public void setTxtStatus(JTextArea txtStatus) {
        this.txtStatus = txtStatus;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JLabel getLblChngTrnhPhn() {
        return lblChngTrnhPhn;
    }

    public void setLblChngTrnhPhn(JLabel lblChngTrnhPhn) {
        this.lblChngTrnhPhn = lblChngTrnhPhn;
    }
}
