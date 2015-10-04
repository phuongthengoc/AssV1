package view;

import controller.FromServer;
import controller.MainModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by panda on 27/09/2015
 */
public class ClientForm {
    private JPanel pnMain;
    private JPanel pnDangNhap;
    private JPanel pnMenu;
    private JPanel pnTrangChinh;
    private JPanel pnSubTrangCaNhan;
    private JPanel pnTrangCaNhan;
    private JTextField JTUserName;
    private JPasswordField JPPassWord;
    private JButton dangNhapButton;
    private JButton dangKyButton;
    private JCheckBox nhoTenDangNhapCheckBox;
    private JButton dangBaiVietButton;
    private JButton trangChinhButton;
    private JButton suaThongTinCaButton;
    private JButton timKiemThemBanButton;
    private JButton trangCaNhanButton;
    private JButton hopThuButton;
    private JButton dangXuatButton;
    private JPanel pnSubTrangChinh;
    private JButton trangChinh1Button;
    private JButton trangCaNhan1Button;
    private JTable tbTrangChinh;
    private JButton tinNhan1Button;
    private JButton bạnBèButton;
    private JTextField textTimKiem;
    private JButton timButton;
    private JButton thongTinCaNhan1Button;
    private JButton dangXuat1Button;
    private JTextField dangTxt;
    private JButton dangButton;
    private JTextField browserTxt;
    private JButton browserButton;
    private JTable tbCaNhan;
    private JButton trangChu2Button;


    public ClientForm() {
        pnDangNhap.setVisible(true);
        pnMenu.setVisible(false);
        pnTrangChinh.setVisible(false);
        pnTrangCaNhan.setVisible(false);
        nhoTenDangNhapCheckBox.setSelected(true);

        JTUserName.setText("panda");
        JPPassWord.setText("123");

        final int[] id = {0};

        final FromServer server = new FromServer();

        dangNhapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(server.checkUserLogin(JTUserName.getText(),JPPassWord.getText()) && !server.checkLockUser(JTUserName.getText())){
                    id[0] = server.getIdFromUsername(JTUserName.getText());
                    MainModel.Id = id[0];
                    MainModel mainModel = new MainModel();
                    tbTrangChinh.setModel(mainModel);
                    pnDangNhap.setVisible(false);
                    pnTrangChinh.setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(pnMain,"Username or PassWord wrong !");
                }
                if(!nhoTenDangNhapCheckBox.isSelected()){
                    JTUserName.setText("");
                    JPPassWord.setText("");
                }
            }
        });

        dangXuat1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pnTrangChinh.setVisible(false);
                pnDangNhap.setVisible(true);
            }
        });
        trangCaNhan1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pnTrangChinh.setVisible(false);
                pnTrangCaNhan.setVisible(true);
            }
        });
        trangChu2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainModel.Id = id[0];
                MainModel mainModel = new MainModel();
                pnTrangCaNhan.setVisible(false);
                pnTrangChinh.setVisible(true);
            }
        });

        dangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                server.posStatus(id[0],dangTxt.getText());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ClientForm");
        frame.setContentPane(new ClientForm().pnMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setSize(400, 600);
        frame.setTitle("AssBook");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


//        JMenuBar menuBar = new JMenuBar();
//        frame.setJMenuBar(menuBar);
//
//        JMenu file = new JMenu("File");
//        menuBar.add(file);
//        JMenuItem exit = new JMenuItem("exit");
//        file.add(exit);
    }
}
