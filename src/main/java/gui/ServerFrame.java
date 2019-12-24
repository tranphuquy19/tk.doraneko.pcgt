package gui;

import commons.Constants;
import services.socket.Server;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/*
 * @author tranphuquy19@gmail.com
 * @since 12/24/2019
 */
public class ServerFrame extends JFrame {
    private JFrame frmServer;
    private JTextField textField;

    public ServerFrame() {
        initialize();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ServerFrame serverGui = new ServerFrame();
                    Runnable target;
                    Thread serverThread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                new Server(Constants.SERVER_CHANNEL, Constants.SERVER_RES);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    serverThread.start();
                    serverGui.frmServer.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void initialize() {
        frmServer = new JFrame();
        frmServer.setTitle("Server");
        frmServer.setBounds(100, 100, 566, 300);
        frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmServer.getContentPane().setLayout(null);

        JLabel lblChuongTrinhPhan = new JLabel("Chuong trinh phan cong giam thi");
        lblChuongTrinhPhan.setHorizontalAlignment(SwingConstants.CENTER);
        lblChuongTrinhPhan.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblChuongTrinhPhan.setBounds(10, 11, 530, 30);
        frmServer.getContentPane().add(lblChuongTrinhPhan);

        JLabel lblServerPort = new JLabel("Server Port:");
        lblServerPort.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblServerPort.setBounds(10, 52, 96, 30);
        frmServer.getContentPane().add(lblServerPort);

        textField = new JTextField();
        textField.setEditable(false);
        textField.setText("16057");
        textField.setBounds(131, 58, 86, 20);
        frmServer.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblServerStatus = new JLabel("Server Status");
        lblServerStatus.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblServerStatus.setBounds(10, 93, 102, 20);
        frmServer.getContentPane().add(lblServerStatus);

        JLabel lblOnline = new JLabel("online");
        lblOnline.setForeground(new Color(0, 128, 0));
        lblOnline.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
        lblOnline.setBounds(131, 97, 46, 14);
        frmServer.getContentPane().add(lblOnline);
    }
}
