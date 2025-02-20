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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Stack;

public class PetRecordAddScreen extends JFrame {
	private BufferedImage image;
	private JLabel backLabel;
	private JLabel petRecordLabel;
	private JLabel petHeightLabel, petWeightabel, petMtLabel, petVsLabel, petChecksLabel, petMtTimeLabel, warningLabel;
	private JTextField petHeightTField, petWeightTField, petMtTField, petVsTField, petChecksTField, petMtTimeTField;
	private JButton petAddRcButton;
	private JFrame previousFrame; // 이전 프레임 저장
	int pet_id;
	boolean flag = true;
	TPMgr mgr;
	HRBean bean;
	java.util.Date date = new java.util.Date();

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	public PetRecordAddScreen(PetBean pb, JFrame previousFrame) {
		setTitle("프레임 설정");
		setSize(402, 874);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mgr = new TPMgr();
		bean = new HRBean();

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


				if (source == petAddRcButton) {
					System.out.println("기입완료 버튼 클릭됨");
					// 값을 입력했는데 0으로 시작하거나 8자리를 다 입력하지 않았다면 실행
					String time = petMtTimeTField.getText().trim();
					//System.out.println(!time.substring(0, 1).equals("0") && time.length() == 8);
					System.out.println("*******"+ time.substring(0, 1).equals("0"));
					System.out.println("*******"+ time.length());
					if (!time.isEmpty() && (time.substring(0, 1).equals("0") || time.length() != 8)) {
							warningLabel.setVisible(true);
					} else if (time.isEmpty() || (!time.substring(0, 1).equals("0") && time.length() == 8)) {
						System.out.println("abc");
						BigDecimal height = new BigDecimal(0);
						BigDecimal weight = new BigDecimal(0);
						try {
							if (!petHeightTField.getText().trim().isEmpty()) {
								height = new BigDecimal(petHeightTField.getText());
							}
							if (!petWeightTField.getText().trim().isEmpty()) {
								weight = new BigDecimal(petWeightTField.getText());
							}
							bean.setHeight(height);
							bean.setWeight(weight);
						} catch (Exception e2) { // 텍스트 필드값이 숫자가 아닌 경우
							height = new BigDecimal(0);
							weight = new BigDecimal(0);
							bean.setHeight(height);
							bean.setWeight(weight);
						}
						bean.setMedical_history(petMtTField.getText().trim());
						bean.setVaccination_status(petVsTField.getText().trim());
						bean.setCheckup_status(petChecksTField.getText().trim());
						bean.setDate(petMtTimeTField.getText().trim());
						mgr.addPet(StaticData.user_id, pb);
						pet_id = mgr.showPetId(StaticData.user_id, pb);
						mgr.addHRPet(pet_id, bean);
						dispose();
						new PetHomeScreen(pet_id);
					}
				} else if (source == backLabel) {
					System.out.println("뒤로가기 버튼 클릭됨");
					dispose();
					previousFrame.setVisible(true);
				}
			}
		};

		// 🔹 상단 뒤로가기 아이콘
		backLabel = createScaledImageLabel("TeamProject/back_button.png", 40, 40);
		backLabel.setBounds(25, 120, 40, 40);
		backLabel.addMouseListener(commonMouseListener);
		add(backLabel);

		// 반려동물 건강기록 추가 안내 라벨
		petRecordLabel = new JLabel("가장 최근에 검진받은 정보를 적어주세요!");
		petRecordLabel.setBounds(43, 165, 306, 60);
		petRecordLabel.setForeground(Color.black);
		add(petRecordLabel);

		// 반려동물 키 라벨
		petHeightLabel = new JLabel("키");
		petHeightLabel.setBounds(43, 200, 17, 60);
		petHeightLabel.setForeground(Color.black);
		add(petHeightLabel);

		// 반려동물 키 텍스트 필드 추가
		petHeightTField = new JTextField();
		petHeightTField.setBounds(43, 250, 318, 40);
		petHeightTField.setText("");
		petHeightTField
				.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부
																														// 여백
																														// (위,
																														// 왼쪽,
																														// 아래,
																														// 오른쪽)
				));
		add(petHeightTField);

		// 반려동물 몸무게 라벨
		petWeightabel = new JLabel("몸무게");
		petWeightabel.setBounds(43, 285, 48, 60);
		petWeightabel.setForeground(Color.black);
		add(petWeightabel);

		// 반려동물 몸무게 텍스트 필드 추가
		petWeightTField = new JTextField();
		petWeightTField.setBounds(43, 335, 318, 40);
		petWeightTField.setText("");
		petWeightTField
				.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부
																														// 여백
																														// (위,
																														// 왼쪽,
																														// 아래,
																														// 오른쪽)
				));
		add(petWeightTField);

		// 반려동물 진료 기록 라벨
		petMtLabel = new JLabel("진료 기록");
		petMtLabel.setBounds(43, 370, 70, 60);
		petMtLabel.setForeground(Color.black);
		add(petMtLabel);

		// 반려동물 진료 기록 텍스트 필드 추가
		petMtTField = new JTextField();
		petMtTField.setBounds(43, 420, 318, 40);
		petMtTField.setText("");
		petMtTField.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부
																														// 여백
																														// (위,
																														// 왼쪽,
																														// 아래,
																														// 오른쪽)
		));
		add(petMtTField);

		// 반려동물 예방접종 상태 라벨
		petVsLabel = new JLabel("예방접종 상태");
		petVsLabel.setBounds(43, 455, 104, 60);
		petVsLabel.setForeground(Color.black);
		add(petVsLabel);

		// 반려동물 예방접종 상태 텍스트 필드 추가
		petVsTField = new JTextField();
		petVsTField.setBounds(43, 505, 318, 40);
		petVsTField.setText("");
		petVsTField.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부
																														// 여백
																														// (위,
																														// 왼쪽,
																														// 아래,
																														// 오른쪽)
		));
		add(petVsTField);

		// 반려동물 체크해야 할 정보 라벨
		petChecksLabel = new JLabel("체크해야 할 정보");
		petChecksLabel.setBounds(43, 540, 104, 60);
		petChecksLabel.setForeground(Color.black);
		add(petChecksLabel);

		// 반려동물 체크해야 할 정보 텍스트 필드 추가
		petChecksTField = new JTextField();
		petChecksTField.setBounds(43, 590, 318, 40);
		petChecksTField.setText("");
		petChecksTField
				.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부
																														// 여백
																														// (위,
																														// 왼쪽,
																														// 아래,
																														// 오른쪽)
				));
		add(petChecksTField);

		// 반려동물 진료 관련 시간 라벨
		petMtTimeLabel = new JLabel("진료 관련 시간");
		petMtTimeLabel.setBounds(43, 625, 104, 60);
		petMtTimeLabel.setForeground(Color.black);
		add(petMtTimeLabel);

		// 반려동물 진료 관련 시간 텍스트 필드 추가
		petMtTimeTField = new JTextField();
		petMtTimeTField.setBounds(43, 675, 318, 40);
		petMtTimeTField.setText(sdf.format(date));
		petMtTField.setForeground(Color.GRAY);
		petMtTimeTField
				.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부
																														// 여백
																														// (위,
																														// 왼쪽,
																														// 아래,
																														// 오른쪽)
				));
		add(petMtTimeTField);

		petMtTimeTField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (petMtTimeTField.getText().isEmpty()) {
					petMtTimeTField.setText("ex) " + sdf.format(date));
					petMtTimeTField.setForeground(Color.GRAY);
					flag = true;
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (flag) {
					petMtTimeTField.setText("");
					petMtTimeTField.setForeground(Color.BLACK);
					flag = false;
				}
			}
		});

		((AbstractDocument) petMtTimeTField.getDocument()).setDocumentFilter(new DocumentFilter() {
			@Override
			public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
					throws BadLocationException {
				if (string != null) {
					// 기존 내용과 새로 입력할 내용을 합친 길이를 확인
					String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
					String newText = currentText.substring(0, offset) + string + currentText.substring(offset);
					if (newText.matches("\\d{0,8}")) { // 8자리 숫자 체크
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
					if (newText.matches("\\d{0,8}")) { // 8자리 숫자 체크
						super.replace(fb, offset, length, text.replaceAll("[^0-9]", ""), attrs);
					}
				}
			}

			@Override
			public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
				super.remove(fb, offset, length);
			}
		});

		warningLabel = new JLabel("올바른 진료 시간을 기입하시오");
		warningLabel.setForeground(Color.RED);
		warningLabel.setBounds(43, 705, 200, 40);
		add(warningLabel);
		warningLabel.setVisible(false);

		// 반려동물 정보 입력 완료 버튼
		petAddRcButton = new RoundedButton("기입 완료");
		petAddRcButton.setBounds(53, 740, 281, 58);
		petAddRcButton.setBackground(new Color(91, 91, 91));
		petAddRcButton.setForeground(Color.WHITE);
		petAddRcButton.addMouseListener(commonMouseListener);
		add(petAddRcButton);

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
				g.drawLine(22, 165, 379, 165);
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

	/**
	 * 이미지 크기를 조정하여 JLabel을 생성하는 메서드
	 */
	private JLabel createScaledImageLabel(String imagePath, int width, int height) {
		ImageIcon icon = new ImageIcon(imagePath);
		Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new JLabel(new ImageIcon(scaledImage));
	}

	public static void main(String[] args) {
		new LoginScreen();
	}
}
