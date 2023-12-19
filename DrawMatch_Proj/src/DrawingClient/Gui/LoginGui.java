package DrawingClient.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginGui {
    private JFrame frame;
    private JLabel titleLabel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JButton button;

    private String id = null;
    private String ip = "127.0.0.1";

    public void makeLoginFrame() {
        createLoginFrame();
        createTitleLabel();
        createIdLabel();
        createIdTextField();
        createButton();
        setIp();
        setId();
        frame.setVisible(true);
    }

    private void createLoginFrame() {
        frame = new JFrame();
        frame.setTitle("로그인 화면");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // 프레임 크기 변경 금지
        frame.setLayout(null); // 레이아웃을 사용하지 않음
        frame.setLocationRelativeTo(null); // 중앙에 표시
        //frame.setVisible(true);
    }

    private void createTitleLabel() {
        titleLabel = new JLabel("로그인");
        titleLabel.setBounds(130, 10, 100, 20);
        frame.add(titleLabel);
    }

    private void createIdLabel() {
        idLabel = new JLabel("ID:");
        idLabel.setBounds(10, 40, 30, 30);
        frame.add(idLabel);
    }

    private void createIdTextField() {
        idTextField = new JTextField();
        idTextField.setBounds(50, 42, 200, 30);
        idTextField.setHorizontalAlignment(JTextField.CENTER);
        idTextField.setToolTipText("아이디"); // 힌트 추가
        frame.add(idTextField);
    }

    private void createButton() {
        button = new JButton("로그인");
        button.setBounds(50, 80, 200, 30);
        frame.add(button);
    }

    private void setId() {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                id = idTextField.getText();
                frame.dispose();
            }
        });
    }

    public String getId() {
        return this.id;
    }

    public String getIp() {
        return this.ip;
    }

    private void setIp() {
        this.ip = "127.0.0.1";
    }
}
