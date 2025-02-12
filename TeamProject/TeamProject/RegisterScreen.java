package TeamProject;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.beans.beancontext.BeanContext;
import java.io.File;
import javax.imageio.ImageIO;


class RoundedButton extends JButton {
    public RoundedButton(String text) {
        super(text);
        setBorderPainted(false);
        setOpaque(false);	//불투명하게 만듦 -> background색이 보임
        setContentAreaFilled(false); // 기본 버튼 배경 제거
        setFocusPainted(false); // 클릭 시 테두리 제거
        setBorder(new RoundedBorder(20)); // 둥근 테두리 적용
        setForeground(Color.WHITE);
        setBackground(Color.GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.GRAY);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        g2.dispose();
    }
}


public class RegisterScreen extends JFrame {
	private BufferedImage image;
	private JTextField id_textField, name_textField, email_textField, phone_textField;
	private JPasswordField pw_textField, pwChk_textField;
	private JButton loginChkButton, registerButton;
	private JLabel idLabel, pwLabel, pwChkLabel, nameLabel, emailLabel, phoneLabel, 
	warningLabel1, warningLabel2, warningLabel3, warningLabel4, pwWarningLabel1, pwWarningLabel2, backLabel;
	TPMgr mgr;
	UserBean bean;
	private boolean flag1 = false;

	public RegisterScreen() {
		setTitle("프레임 설정");
		setSize(402, 874);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mgr = new TPMgr();
		bean = new UserBean();

		try {
			image = ImageIO.read(new File("TeamProject/phone_frame.png")); // 투명 PNG 불러오기
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 🔹 공통 마우스 클릭 이벤트 리스너
				MouseAdapter commonMouseListener = new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Object source = e.getSource(); // 클릭된 컴포넌트 확인

						if (source == backLabel) {
							new LoginScreen();
						} 
					}
				};
				
		// 🔹 상단 뒤로가기 아이콘
		backLabel = createScaledImageLabel("TeamProject/back_button.png", 40, 40);
		backLabel.setBounds(25, 120, 40, 40);
		backLabel.addMouseListener(commonMouseListener);
		add(backLabel);

		//아이디
		idLabel = new JLabel("아이디");
		idLabel.setBounds(41, 149, 49, 60);
		
		id_textField = new JTextField();
		id_textField.setBounds(41, 195, 220, 40);
		id_textField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
		
		warningLabel1 = new JLabel("생성 가능한 아이디입니다.");
		warningLabel1.setForeground(Color.BLUE);
		warningLabel1.setBounds(41, 215, 200, 60);
		
		warningLabel2 = new JLabel("이미 존재하는 아이디입니다.");
		warningLabel2.setForeground(Color.RED);
		warningLabel2.setBounds(41, 215, 200, 60);
		
		warningLabel3 = new JLabel("아이디를 입력하세요");
		warningLabel3.setForeground(Color.RED);
		warningLabel3.setBounds(41, 215, 200, 60);
		
		warningLabel4 = new JLabel("아이디 중복 체크를 하십시오");
		warningLabel4.setForeground(Color.RED);
		warningLabel4.setBounds(41, 215, 200, 60);
		
		loginChkButton = new RoundedButton("중복");
		loginChkButton.setBounds(268, 194, 91, 43);
		loginChkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(id_textField.getText().isEmpty()) {
					warningLabel1.setVisible(false);
					warningLabel2.setVisible(false);
					warningLabel3.setVisible(true);
					warningLabel4.setVisible(false);
					id_textField.requestFocus();
				}else {
					if(mgr.idChk(id_textField.getText().trim())) {
						warningLabel1.setVisible(true);
						warningLabel2.setVisible(false);
						warningLabel3.setVisible(false);
						warningLabel4.setVisible(false);
						flag1 = true;
					} else {
						id_textField.requestFocus();
						warningLabel1.setVisible(false);
						warningLabel2.setVisible(true);
						warningLabel3.setVisible(false);
						warningLabel4.setVisible(false);
					}
				}
			}
		});
		
		//비밀번호
		pwLabel = new JLabel("비밀번호");
		pwLabel.setBounds(41, 241, 65, 60);
		
		pw_textField = new JPasswordField();
		pw_textField.setBounds(41, 287, 320, 40);
		
		pw_textField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
		
		//비밀번호 확인
		pwChkLabel = new JLabel("비밀번호 확인");
		pwChkLabel.setBounds(41, 330, 105, 60);
		
		pwChk_textField = new JPasswordField();
		pwChk_textField.setBounds(41, 379, 320, 40);
		
		pwChk_textField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
		
		pwWarningLabel1 = new JLabel("비밀번호가 일치하지 않습니다.");
		pwWarningLabel1.setForeground(Color.RED);
		pwWarningLabel1.setBounds(41, 400, 200, 60);
		
		pwWarningLabel2 = new JLabel("비밀번호가 일치합니다.");
		pwWarningLabel2.setForeground(Color.BLUE);
		pwWarningLabel2.setBounds(41, 400, 200, 60);
		
		//이름
		nameLabel = new JLabel("이름");
		nameLabel.setBounds(41, 422, 32, 60);
		
		name_textField = new JTextField();
		name_textField.setBounds(41, 471, 320, 40);
		
		name_textField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
		
		//이메일
		emailLabel = new JLabel("이메일");
		emailLabel.setBounds(41, 514, 49, 60);
		
		email_textField = new JTextField();
		email_textField.setBounds(41, 563, 320, 40);
		
		email_textField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
		
		//휴대폰 번호
		phoneLabel = new JLabel("휴대폰 번호");
		phoneLabel.setBounds(41, 606, 86, 60);
		
		phone_textField = new JTextField();
		phone_textField.setBounds(41, 655, 320, 40);
		
		phone_textField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
		
		//회원가입 버튼
		registerButton = new RoundedButton("회원가입");
		registerButton.setBounds(60, 740, 281, 58);
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//모든 값들이 들어가면 회원가입 버튼 활성화
				if(flag1) {
					if(!pw_textField.getText().trim().isEmpty() && !name_textField.getText().trim().isEmpty() && 
							!email_textField.getText().trim().isEmpty() && !phone_textField.getText().trim().isEmpty()) {
						if(!pw_textField.getText().trim().equals(pwChk_textField.getText().trim())) {
							pwWarningLabel1.setVisible(true);
							pwWarningLabel2.setVisible(false);
						} else {
							bean.setUser_id(id_textField.getText().trim());
							bean.setPassword(pw_textField.getText().trim());
							bean.setUsername(name_textField.getText().trim());
							bean.setEmail(email_textField.getText().trim());
							bean.setPhone(phone_textField.getText().trim());
							mgr.register(bean);
							new LoginScreen();
						}
					}
				} else {
					if(id_textField.getText().trim().isEmpty()) {
						warningLabel1.setVisible(false);
						warningLabel2.setVisible(false);
						warningLabel3.setVisible(true);
						warningLabel4.setVisible(false);
					}else {
						warningLabel1.setVisible(false);
						warningLabel2.setVisible(false);
						warningLabel3.setVisible(false);
						warningLabel4.setVisible(true);
						loginChkButton.requestFocus();
					}
				}
				
			}
		});
		
		
		//라벨, 텍스트필드 부착
		add(idLabel);
		add(id_textField);
		add(warningLabel1);
		warningLabel1.setVisible(false);
		add(warningLabel2);
		warningLabel2.setVisible(false);
		add(warningLabel3);
		warningLabel3.setVisible(false);
		add(warningLabel4);
		warningLabel4.setVisible(false);
		add(loginChkButton);
		add(pwLabel);
		add(pw_textField);
		add(pwChkLabel);
		add(pwChk_textField);
		add(pwWarningLabel1);
		pwWarningLabel1.setVisible(false);
		add(pwWarningLabel2);
		pwWarningLabel2.setVisible(false);
		add(nameLabel);
		add(name_textField);
		add(emailLabel);
		add(email_textField);
		add(phoneLabel);
		add(phone_textField);
		add(registerButton);

		// JPanel 추가
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					// 이미지 크기 조정 후 그리기
					Image scaledImage = image.getScaledInstance(402, 874, Image.SCALE_SMOOTH);
					g.drawImage(scaledImage, 0, 0, this);
				}
			}
		};

		panel.setOpaque(false);
		panel.setLayout(null);
		add(panel);

		// 닫기 버튼 추가
		JButton closeButton = new JButton("X");
		closeButton.setBounds(370, 10, 20, 20); // 오른쪽 상단에 위치
		closeButton.setBackground(Color.RED); // 버튼 배경 색
		closeButton.setForeground(Color.WHITE); // 버튼 텍스트 색
		closeButton.setBorder(BorderFactory.createEmptyBorder());
		closeButton.setFocusPainted(false);
		closeButton.addActionListener(e -> System.exit(0)); // 애플리케이션 종료

		panel.add(closeButton);

		setVisible(true);
	}

	private JLabel createScaledImageLabel(String imagePath, int width, int height) {
		ImageIcon icon = new ImageIcon(imagePath);
		Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new JLabel(new ImageIcon(scaledImage));
	}

	
	
	
	public static void main(String[] args) {
		new LoginScreen();
	}
}
