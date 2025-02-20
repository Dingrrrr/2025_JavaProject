package TeamProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.io.*;

public class UpdateUserScreen extends JFrame {

	private BufferedImage image;
	private JLabel nameLabel, pwLabel, emailLabel, phoneLabel, profileLabel, delLabel, backLabel, imageLabel;
	private JTextField nameField, emailField, phoneField;
	private JPasswordField pwField;
	private JButton updataButton, fisButton, addButton, deleteButton;
	private JFrame previousFrame; // 이전 프레임 저장
	private UserDeleteDialog udd;
	TPMgr mgr;
	private UserPhotoModifyDialog upm;
	private byte[] imageBytes; // 이미지 데이터를 저장할 멤버 변수

	public UpdateUserScreen(JFrame previousFrame) {
		setTitle("회원정보 수정");
		setSize(402, 874);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mgr = new TPMgr();
		UserBean bean = mgr.showUser(StaticData.user_id);
		imageBytes = bean.getUser_image();

		try {
			image = ImageIO.read(new File("TeamProject/phone_frame.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 🔹 공통 마우스 클릭 이벤트 리스너
		MouseAdapter commonMouseListener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object source = e.getSource(); // 클릭된 컴포넌트 확인

				if (source == backLabel) {
					System.out.println("뒤로가기 클릭됨");
					dispose();
					previousFrame.setVisible(true);
				} else if (source == addButton && addButton.isEnabled()) {
					System.out.println("유저 프로필 사진 추가 클릭됨!");
					if (upm == null) {
						// UserPhotoModifyDialog 생성 시 'this'는 JFrame, UpdateUserScreen.this는
						// UpdateUserScreen 객체
						upm = new UserPhotoModifyDialog(UpdateUserScreen.this);
						upm.setLocation(getX() + 22, getY() + 630);
					} else {
						upm.setLocation(getX() + 22, getY() + 630);
						upm.setVisible(true);
					}
					setEnabled(false);
				} else if (source == updataButton) {
					System.out.println("유저 정보 수정 버튼 클릭됨!");
					nameField.setEnabled(true);
					pwField.setEnabled(true);
					emailField.setEnabled(true);
					phoneField.setEnabled(true);
					addButton.setEnabled(true);
					fisButton.setEnabled(true);
					deleteButton.setEnabled(true);
				} else if (source == fisButton && fisButton.isEnabled()) {
					System.out.println("유저 정보 완료 버튼 클릭됨!");
					String name = nameField.getText().trim();
					String pw = pwField.getText().trim();
					String email = emailField.getText().trim();
					String phone = phoneField.getText().trim();
					if (name.isEmpty())
						nameField.requestFocus();
					else if (!name.isEmpty() && pw.isEmpty())
						pwField.requestFocus();
					else if (!name.isEmpty() && !pw.isEmpty() && phone.isEmpty())
						phoneField.requestFocus();
					else if (!name.isEmpty() && !pw.isEmpty() && !phone.isEmpty()) {
						if (phone.length() != 11 || !phone.substring(0, 3).equals("010")) {
							phoneField.setForeground(Color.RED);
						} else {
							UserBean bb = new UserBean();
							bb.setUsername(name);
							bb.setPassword(pw);
							bb.setEmail(email);
							bb.setPhone(phone);
							// 이미지 값이 null일 경우 기본 이미지 db에 저장
							if (imageBytes == null || imageBytes.length == 0) {
								File selectedFile = new File("TeamProject/profile.png");
								byte[] imageBytes = convertFileToByteArray(selectedFile);
								bb.setUser_image(imageBytes);
							} else {
								bb.setUser_image(imageBytes);
							}
							if (mgr.userUpd(StaticData.user_id, bb)) {
								nameField.setEnabled(false);
								pwField.setEnabled(false);
								emailField.setEnabled(false);
								phoneField.setEnabled(false);
								dispose();
								mgr.userIn(StaticData.user_id);
								if (mgr.isPet(StaticData.user_id)) {
									dispose();
									new PetAddMainScreen(); // 반려동물 정보가 이미 있는 경우
								} else {
									dispose();
									new UserHomeScreen(); // 반려동물 정보가 없는 경우
								}
							}
						}
					}
				} else if (source == deleteButton && deleteButton.isEnabled()) {
					new UserDeleteDialog(UpdateUserScreen.this, mgr);
				}
			}
		};

		// 뒤로가기 아이콘
		backLabel = createScaledImageLabel("TeamProject/back_Button.png", 40, 40);
		backLabel.setBounds(25, 120, 40, 40);
		backLabel.addMouseListener(commonMouseListener);
		add(backLabel);

		// 이미지 추가 버튼
		addButton = new RoundedButton("추가");
		addButton.setBounds(277, 450, 80, 35);
		addButton.setBackground(new Color(91, 91, 91));
		addButton.setForeground(Color.WHITE);
		addButton.addMouseListener(commonMouseListener);
		addButton.setEnabled(false);
		add(addButton);

		// 메인 프로필 이미지
		byte[] imgBytes = bean.getUser_image();
		if (imgBytes == null || imgBytes.length == 0) {
			// 기본 프로필 이미지 사용
			imageLabel = new JLabel();
			imageLabel = createScaledImageLabel("TeamProject/profile.png", 270, 270);
			imageLabel.setBounds(70, 189, 270, 270);
			imageLabel.addMouseListener(commonMouseListener);
			add(imageLabel);
		} else {
			// 사용자 이미지가 있을 경우
			ImageIcon icon = new ImageIcon(imgBytes);
			Image img = icon.getImage().getScaledInstance(270, 270, Image.SCALE_SMOOTH);

			// RoundedImageLabel 사용
			RoundedImageLabel roundedImageLabel = new RoundedImageLabel(img, 270, 270, 3); // 100은 둥근 정도
			roundedImageLabel.setBounds(70, 189, 270, 270);
			add(roundedImageLabel);
		}

		// 이름
		nameLabel = new JLabel("이름");
		nameLabel.setBounds(43, 469, 32, 60);
		nameLabel.setForeground(Color.BLACK);
		add(nameLabel);

		nameField = new JTextField(bean.getUsername());
		nameField.setBounds(43, 510, 220, 40);
		nameField.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부
																														// 여백
																														// (위,
																														// 왼쪽,
																														// 아래,
																														// 오른쪽)
		));
		add(nameField);

		// 비밀번호
		pwLabel = new JLabel("비밀번호");
		pwLabel.setBounds(43, 539, 65, 60);
		pwLabel.setForeground(Color.BLACK);
		add(pwLabel);

		pwField = new JPasswordField(bean.getPassword());
		pwField.setBounds(43, 580, 320, 40);
		pwField.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부
																													// 여백
																													// (위,
																													// 왼쪽,
																													// 아래,
																													// 오른쪽)
		));
		add(pwField);

		// 이메일
		emailLabel = new JLabel("이메일");
		emailLabel.setBounds(43, 609, 49, 60);
		emailLabel.setForeground(Color.BLACK);
		add(emailLabel);

		emailField = new JTextField(bean.getEmail());
		emailField.setBounds(43, 650, 320, 40);
		emailField.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부
																														// 여백
																														// (위,
																														// 왼쪽,
																														// 아래,
																														// 오른쪽)
		));
		add(emailField);

		// 휴대폰 번호
		phoneLabel = new JLabel("휴대폰 번호");
		phoneLabel.setBounds(43, 679, 86, 60);
		phoneLabel.setForeground(Color.BLACK);
		add(phoneLabel);

		phoneField = new JTextField(bean.getPhone());
		phoneField.setBounds(43, 720, 320, 40);
		phoneField.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부
																														// 여백
																														// (위,
																														// 왼쪽,
																														// 아래,
																														// 오른쪽)
		));
		// DocumentFilter를 사용하여 전화번호 형식 제한
		((AbstractDocument) phoneField.getDocument()).setDocumentFilter(new DocumentFilter() {
			@Override
			public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
					throws BadLocationException {
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
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
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
		add(phoneField);

		// 회원 탈퇴 버튼
		deleteButton = new RoundedButton("탈퇴");
		deleteButton.setBounds(295, 125, 70, 30);
		deleteButton.setBackground(new Color(91, 91, 91));
		deleteButton.setForeground(Color.WHITE);
		deleteButton.addMouseListener(commonMouseListener);
		add(deleteButton);

		// 수정 버튼
		updataButton = new RoundedButton("수정");
		updataButton.setBounds(98, 770, 91, 43);
		updataButton.setBackground(new Color(91, 91, 91));
		updataButton.setForeground(Color.WHITE);
		updataButton.addMouseListener(commonMouseListener);
		add(updataButton);

		// 완료 버튼
		fisButton = new RoundedButton("완료");
		fisButton.setBounds(215, 770, 91, 43);
		fisButton.setBackground(new Color(91, 91, 91));
		fisButton.setForeground(Color.WHITE);
		fisButton.addMouseListener(commonMouseListener);
		add(fisButton);

		nameField.setEnabled(false);
		pwField.setEnabled(false);
		emailField.setEnabled(false);
		phoneField.setEnabled(false);
		fisButton.setEnabled(false);
		deleteButton.setEnabled(false);

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

				// y=158 위치에 가로로 회색 선 그리기
				g.setColor(Color.LIGHT_GRAY); // 선 색을 회색으로 설정
			}
		};
		panel.setOpaque(false);
		panel.setLayout(null);
		add(panel);

		// 닫기 버튼
		JButton closeButton = new JButton("X");
		closeButton.setBounds(370, 10, 20, 20);
		closeButton.setBackground(Color.RED);
		closeButton.setForeground(Color.WHITE);
		closeButton.setBorder(BorderFactory.createEmptyBorder());
		closeButton.setFocusPainted(false);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mgr.userOut(StaticData.user_id);
				System.exit(0);
			}
		});
		panel.add(closeButton);

		setVisible(true);
	}

	private JLabel createScaledImageLabel(String imagePath, int width, int height) {
		ImageIcon icon = new ImageIcon(imagePath);
		Image scaledImage = icon.getImage().getScaledInstance(width, height, image.SCALE_SMOOTH);
		return new JLabel(new ImageIcon(scaledImage));
	}

	public JLabel getImageLabel() {
		return imageLabel;
	}

	// 이미지 바이트 배열을 설정하는 setter
	public void setImageBytes(byte[] imageBytes) {
		this.imageBytes = imageBytes;
	}

	// imageBytes를 얻는 메서드
	public byte[] getImageBytes() {
		return imageBytes;
	}

	// 파일을 byte 배열로 변환하는 메서드
	private byte[] convertFileToByteArray(File file) {
		try (FileInputStream fis = new FileInputStream(file);
				ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		new LoginScreen();
	}
}
