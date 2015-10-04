package view;

import controller.Start;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by panda on 27/09/2015.
 */
public class ServerForm {
    private JButton startButton;
    private JButton exitButton;
    private JPanel pnMain;
    private JLabel JlServer;

    public ServerForm() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Start();
                JlServer.setText("ASSBOOK STARTED...");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ViewForm");
        frame.setContentPane(new ServerForm().pnMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(200, 200);
        frame.setTitle("AssBook");
        frame.setVisible(true);

    }

}
