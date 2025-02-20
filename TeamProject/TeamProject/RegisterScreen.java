package TeamProject;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.beans.beancontext.BeanContext;
import java.io.File;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;


public class RegisterScreen extends JFrame {
	private BufferedImage image;
	private JTextField id_textField, name_textField, email_textField, phone_textField;
	private JPasswordField pw_textField, pwChk_textField;
	private JButton loginChkButton, registerButton;
	private JLabel idLabel, pwLabel, pwChkLabel, nameLabel, emailLabel, phoneLabel, 
	warningLabel1, warningLabel2, warningLabel3, warningLabel4, pwWarningLabel1, pwWarningLabel2, phoneWarningLabel, allWarningLabel, backLabel;
	TPMgr mgr;
	UserBean bean;
	private boolean flag1 = false, flag2 = true, flag3 = true;

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
							dispose();
							new LoginScreen();
						} 
					}
				};
				
		// 🔹 상단 뒤로가기 아이콘
		backLabel = createScaledImageLabel("TeamProject/back_button.png", 40, 40);
		backLabel.setBounds(25, 120, 40, 40);
		backLabel.addMouseListener(commonMouseListener);
		add(backLabel);
		
		allWarningLabel = new JLabel("모든 정보를 입력하시오");
		allWarningLabel.setForeground(Color.RED);
		allWarningLabel.setBounds(137, 700, 200, 60);

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
		
		pwWarningLabel1 = new JLabel("비밀번호가 일치하지 않습니다.");
		pwWarningLabel1.setForeground(Color.RED);
		pwWarningLabel1.setBounds(41, 400, 200, 60);
		
		//비밀번호
		pwLabel = new JLabel("비밀번호");
		pwLabel.setBounds(41, 241, 65, 60);
		
		pw_textField = new JPasswordField();
		pw_textField.setBounds(41, 287, 320, 40);
		
		pw_textField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
		pw_textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				pwWarningLabel1.setVisible(true);
			}
		});
		// 첫 번째 텍스트 필드에 DocumentListener 추가
		pw_textField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				checkValues();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkValues();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				checkValues();
			}
			private void checkValues() {
                if (pw_textField.getText().equals(pwChk_textField.getText())) {
                	pwWarningLabel1.setText("비밀번호가 일치합니다");
                	pwWarningLabel1.setForeground(Color.BLUE);
                } else {
                	pwWarningLabel1.setText("비밀번호가 일치하지 않습니다");
                	pwWarningLabel1.setForeground(Color.RED);
                }
            }
		});
		
		//비밀번호 확인
		pwChkLabel = new JLabel("비밀번호 확인");
		pwChkLabel.setBounds(41, 330, 105, 60);
		
		pwChk_textField = new JPasswordField();
		pwChk_textField.setBounds(41, 379, 320, 40);
		pwChk_textField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
		// 두 번째 텍스트 필드에 DocumentListener 추가
		pwChk_textField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				checkValues();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkValues();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				checkValues();
			}
			private void checkValues() {
                if (pw_textField.getText().equals(pwChk_textField.getText())) {
                	pwWarningLabel1.setText("비밀번호가 일치합니다");
                	pwWarningLabel1.setForeground(Color.BLUE);
                } else {
                	pwWarningLabel1.setText("비밀번호가 일치하지 않습니다");
                	pwWarningLabel1.setForeground(Color.RED);
                }
            }
		});
		
		
//		pwWarningLabel2 = new JLabel("비밀번호가 일치합니다.");
//		pwWarningLabel2.setForeground(Color.BLUE);
//		pwWarningLabel2.setBounds(41, 400, 200, 60);
		
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
		email_textField.setText("(선택 사항)");
		email_textField.setForeground(Color.GRAY);
		email_textField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
		email_textField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(email_textField.getText().isEmpty()) {
					email_textField.setText("(선택 사항)");
					email_textField.setForeground(Color.GRAY);
					flag3 = true;
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(flag3) {
					email_textField.setText("");
					email_textField.setForeground(Color.BLACK);
					flag3 = false;
				}
			}
		});
	
		
		//휴대폰 번호
		phoneLabel = new JLabel("휴대폰 번호");
		phoneLabel.setBounds(41, 606, 86, 60);
		
		phone_textField = new JTextField();
		phone_textField.setBounds(41, 655, 320, 40);
		phone_textField.setText("하이픈('-')없이 숫자만 입력하시오");
		phone_textField.setForeground(Color.GRAY);
		phone_textField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
		phone_textField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(phone_textField.getText().isEmpty()) {
					phone_textField.setText("하이픈('-')없이 숫자만 입력하시오");
					phone_textField.setForeground(Color.GRAY);
					flag2 = true;
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(flag2) {
					phone_textField.setText("");
					phone_textField.setForeground(Color.BLACK);
					flag2 = false;
				}
			}
		});
		  // DocumentFilter를 사용하여 전화번호 형식 제한
        ((AbstractDocument) phone_textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string != null) {
                    // 기존 내용과 새로 입력할 내용을 합친 길이를 확인
                    String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                    String newText = currentText.substring(0, offset) + string + currentText.substring(offset);
                    if (newText.matches("\\d{0,11}")) { // 11자리 숫자 체크
                        super.insertString(fb, offset, string.replaceAll("[^0-9]", ""), attr);
                    }
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text != null) {
                    // 기존 내용과 새로 입력할 내용을 합친 길이를 확인
                    String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                    String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);
                    if (newText.matches("\\d{0,11}")) { // 11자리 숫자 체크
                        super.replace(fb, offset, length, text.replaceAll("[^0-9]", ""), attrs);
                    }
                }
            }

            @Override
            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                super.remove(fb, offset, length);
            }
        });
        
        
       phoneWarningLabel = new JLabel("올바른 전화번호를 입력하시오");
       phoneWarningLabel.setForeground(Color.RED);
       phoneWarningLabel.setBounds(41, 680, 200, 60);
        
		//회원가입 버튼
		registerButton = new RoundedButton("회원가입");
		registerButton.setBounds(60, 740, 281, 58);
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//모든 값들이 들어가면 회원가입 버튼 활성화
				String id = id_textField.getText().trim();
				String pw = pw_textField.getText().trim();
				String pw_chk = pwChk_textField.getText().trim();
				String name = name_textField.getText().trim();
				String email = email_textField.getText().trim();
				String phone = phone_textField.getText().trim();
				if(flag1) {	//아이디 중복 체크 성공
					if(!pw.isEmpty() && !name.isEmpty() && !phone.isEmpty()) {	//모든 정보를 입력함
						if(!pw.equals(pw_chk)) {		//비밀번호 확인 틀림
							pwWarningLabel1.setVisible(true);
//							pwWarningLabel2.setVisible(false);
						} else {	//비밀번호 확인 맞음
							if(phone.length() != 11 || !phone.substring(0, 3).equals("010")) {	//11글자가 아니거나 처음에 010으로 시작하지 않으면
								phoneWarningLabel.setVisible(true);
							} else if(mgr.phoneChk(phone)) {	//이미 등록한 전화번호
								phoneWarningLabel.setText("이미 등록한 전화번호 입니다.");
								phoneWarningLabel.setVisible(true);
							} else {	//정상적인 전화번호까지 입력하면 회원가입 성공
								bean.setUser_id(id);
								bean.setPassword(pw);
								bean.setUsername(name);
								bean.setEmail(email);
								bean.setPhone(phone);
								mgr.register(bean);
								dispose();
								new LoginScreen();
							}
						}
					} else {	//정보가 다 입력되지 않음
						allWarningLabel.setVisible(true);
					}
				} else {	//중복체크X
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
//		add(pwWarningLabel2);
//		pwWarningLabel2.setVisible(false);
		add(nameLabel);
		add(name_textField);
		add(emailLabel);
		add(email_textField);
		add(phoneLabel);
		add(phone_textField);
		add(phoneWarningLabel);
		phoneWarningLabel.setVisible(false);
		add(allWarningLabel);
		allWarningLabel.setVisible(false);
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
