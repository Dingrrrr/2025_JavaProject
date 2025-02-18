package TeamProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.Stack;

public class PetAddScreen extends JFrame {
	private BufferedImage image;
	private JLabel backLabel, petProfileLabel, deleteLabel, imageLabel;
	private JLabel petNameLabel, petSpecLabel, petBirthLabel, petGenderLabel, petMaleLabel, petFemaleLabel,
			warningLabel;
	private JTextField petNameTField, petSpecTField, petBirthTField;
	private JButton petAddProButton, petSpSearchButton, nextButton;
	private JRadioButton petMaleRdButton, petFemaleRdBotton;
	TPMgr mgr;
	PetBean bean;
	private PetSpeciesSearchDialog pssd;
	private PetPhotoAddDialog ppm;
	private byte[] imageBytes; // 이미지 데이터를 저장할 멤버 변수

	public PetAddScreen(JFrame preFrame) {
		setTitle("프레임 설정");
		setSize(402, 874);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mgr = new TPMgr();
		bean = new PetBean();

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
					System.out.println("뒤로가기 클릭됨");
					dispose();
					preFrame.setVisible(true);
				} else if (source == petAddProButton) {
					System.out.println("반려동물 프로필 사진 추가 클릭됨!");
					if (ppm == null) {
						ppm = new PetPhotoAddDialog(PetAddScreen.this);
						ppm.setLocation(getX() + 22, getY() + 630);
					} else {
						ppm.setLocation(getX() + 22, getY() + 630);
						ppm.setVisible(true);
					}
				} else if (source == petSpSearchButton) {
					System.out.println("반려동물 종 검색 버튼 클릭됨!");
					if (pssd == null) {
						pssd = new PetSpeciesSearchDialog(PetAddScreen.this);
						pssd.setLocation(getX() + 25, getY() + 350);
					} else {
						pssd.setLocation(getX() + 25, getY() + 350);
						pssd.setVisible(true);
					}
					setEnabled(false);
				} else if (source == nextButton) {
					System.out.println("다음 단계 버튼 클릭됨!");
					if (petNameTField.getText().trim().isEmpty()) {
						warningLabel.setVisible(true);
					} else {
						bean.setPet_name(petNameTField.getText().trim());
						bean.setPet_species(petSpecTField.getText().trim());
						bean.setPet_age(petBirthTField.getText().trim());
						dispose();
						new PetRecordAddScreen(bean, PetAddScreen.this);
					}
				}
			}
		};

		// 🔹 상단 뒤로가기 아이콘
		backLabel = createScaledImageLabel("TeamProject/back_button.png", 40, 40);
		backLabel.setBounds(25, 120, 40, 40);
		backLabel.addMouseListener(commonMouseListener);
		add(backLabel);

		// 메인 프로필 이미지
		System.out.println(bean.getPet_image());
		byte[] imgBytes = bean.getPet_image();
		String imgNull = Arrays.toString(imgBytes);
		System.out.println(imgNull);
		if (imgBytes == null || imgBytes.length == 0) {
		    imageLabel = createScaledImageLabel("TeamProject/dog.png", 200, 200); // 기본 이미지
		} else {
		    ImageIcon icon = new ImageIcon(imgBytes);
		    Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		    imageLabel = new JLabel();
		    imageLabel.setIcon(new ImageIcon(img));
		}
		imageLabel.setBounds(101, 230, 200, 200);
		imageLabel.addMouseListener(commonMouseListener);
		add(imageLabel);

		// 반려동물 프로필 사진 추가 버튼
		petAddProButton = new RoundedButton("추가");
		petAddProButton.setBounds(277, 450, 80, 35);
		petAddProButton.setBackground(new Color(91, 91, 91));
		petAddProButton.setForeground(Color.WHITE);
		petAddProButton.addMouseListener(commonMouseListener);
		// bean.setPet_image(""); // 지금은 null값, 나중에 수정
		add(petAddProButton);

		// 반려동물 이름 라벨
		petNameLabel = new JLabel("이름");
		petNameLabel.setBounds(43, 479, 32, 60);
		petNameLabel.setForeground(Color.black);
		add(petNameLabel);

		// 반려동물 이름 텍스트 필드 추가
		petNameTField = new JTextField();
		petNameTField.setBounds(43, 520, 318, 40);
		petNameTField.setText("");
		petNameTField
				.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부
																														// 여백
																														// (위,
																														// 왼쪽,
																														// 아래,
																														// 오른쪽)
				));
		add(petNameTField);

		// 경고문
		warningLabel = new JLabel("이름을 입력하시오");
		warningLabel.setForeground(Color.RED);
		warningLabel.setBounds(43, 545, 250, 60);
		add(warningLabel);
		warningLabel.setVisible(false);

		// 반려동물 종 라벨
		petSpecLabel = new JLabel("종");
		petSpecLabel.setBounds(43, 567, 16, 60);
		petSpecLabel.setForeground(Color.black);
		add(petSpecLabel);

		// 반려동물 종 텍스트 필드 추가
		petSpecTField = new JTextField();
		petSpecTField.setBounds(43, 608, 225, 40);
		petSpecTField.setText("");
		petSpecTField
				.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부
																														// 여백
																														// (위,
																														// 왼쪽,
																														// 아래,
																														// 오른쪽)
				));
		petSpecTField.setEnabled(false);
		add(petSpecTField);

		// 반려동물 종 검색 버튼
		petSpSearchButton = new RoundedButton("검색");
		petSpSearchButton.setBounds(270, 608, 90, 40);
		petSpSearchButton.setBackground(new Color(91, 91, 91));
		petSpSearchButton.setForeground(Color.WHITE);
		petSpSearchButton.addMouseListener(commonMouseListener);
		add(petSpSearchButton);

		// 반려동물 생년월일 라벨
		petBirthLabel = new JLabel("생년월일");
		petBirthLabel.setBounds(43, 655, 66, 60);
		petBirthLabel.setForeground(Color.black);
		add(petBirthLabel);

		// 반려동물 종 생년월일 필드 추가
		petBirthTField = new JTextField();
		petBirthTField.setBounds(43, 696, 147, 40);
		petBirthTField.setText("");
		petBirthTField
				.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부
																														// 여백
																														// (위,
																														// 왼쪽,
																														// 아래,
																														// 오른쪽)
				));
		add(petBirthTField);

		// 반려동물 성별 라벨
		petGenderLabel = new JLabel("성별");
		petGenderLabel.setBounds(220, 655, 32, 60);
		petGenderLabel.setForeground(Color.black);
		add(petGenderLabel);

		// 반려동물 남 라벨
		petMaleLabel = new JLabel("남");
		petMaleLabel.setBounds(220, 683, 17, 60);
		petMaleLabel.setForeground(Color.black);
		add(petMaleLabel);

		// 반려동물 남 라디오 버튼
		petMaleRdButton = new JRadioButton();
		petMaleRdButton.setBounds(250, 705, 20, 20); // 위치와 크기 설정
		petMaleRdButton.setOpaque(false); // 배경 투명 처리
		petMaleRdButton.setContentAreaFilled(false); // 내용 영역 투명
		petMaleRdButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // 테두리 설정
		petMaleRdButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bean.setPet_gender("수컷");
			}
		});
		add(petMaleRdButton);

		// 반려동물 여 라벨
		petFemaleLabel = new JLabel("여");
		petFemaleLabel.setBounds(290, 683, 17, 60);
		petFemaleLabel.setForeground(Color.black);
		add(petFemaleLabel);

		// 반려동물 여 라디오 버튼
		petFemaleRdBotton = new JRadioButton();
		petFemaleRdBotton.setBounds(320, 705, 20, 20); // 위치와 크기 설정
		petFemaleRdBotton.setOpaque(false); // 배경 투명 처리
		petFemaleRdBotton.setContentAreaFilled(false); // 내용 영역 투명
		petFemaleRdBotton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // 테두리 설정
		petFemaleRdBotton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bean.setPet_gender("암컷");
			}
		});
		add(petFemaleRdBotton);

		// 라디오 버튼 그룹으로 묶기 (선택은 하나만)
		ButtonGroup group = new ButtonGroup();
		group.add(petMaleRdButton);
		group.add(petFemaleRdBotton);

		// 다음단계 버튼
		nextButton = new RoundedButton("다음 단계");
		nextButton.setBounds(53, 760, 281, 58);
		nextButton.setBackground(new Color(91, 91, 91));
		nextButton.setForeground(Color.WHITE);
		nextButton.addMouseListener(commonMouseListener);
		add(nextButton);

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

	public void updateSpecies(String species) {
		petSpecTField.setText(species);
	}

	/**
	 * 이미지 크기를 조정하여 JLabel을 생성하는 메서드
	 */
	private JLabel createScaledImageLabel(String imagePath, int width, int height) {
		ImageIcon icon = new ImageIcon(imagePath);
		Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
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

	public static void main(String[] args) {
		new LoginScreen();
		//
	}
}
