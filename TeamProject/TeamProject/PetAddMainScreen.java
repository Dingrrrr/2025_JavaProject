package TeamProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class PetAddMainScreen extends JFrame {

	private BufferedImage image;
	private ImageIcon image2;
	private JLabel alarmLabel, profileLabel, mainProfileLabel, petProfileLabel, addButtonLabel;
	private JLabel alarmLabel, profileLabel, mainProfileLabel, petProfileLabel, addButtonLabel, imageLabel,
			imageProfileLabel, petImageLabel;
	private ImageIcon image2;
	private JButton logoutButton;
	private JLabel welcomeLabel, petNameLabel, petSpeciesLabel, petAgeLabel, petGenderLabel;
	TPMgr mgr = new TPMgr();
	PetBean bean;
	Vector<PetBean> vlist;
	private PetChooseDialog pc;
	private JPanel petaddPanel;
	private JScrollPane scrollPane;	
	private JScrollPane scrollPane; // 스크롤 패널
	private byte[] imageBytes, imageBytes1;
	public PetAddMainScreen() {
		setTitle("프레임 설정");
		setSize(402, 874);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vlist = mgr.showPet(LoginScreen.id);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UserBean bean1 = mgr.showUser(StaticData.user_id);
		vlist = mgr.showPet(StaticData.user_id);
		try {
			bean[0] = (PetBean)vlist.elementAt(0);
			bean[1] = (PetBean)vlist.elementAt(1);
		} catch (Exception e) {}
		
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

				if (source == alarmLabel) {
					System.out.println("🔔 알람 클릭됨!");
					dispose();
					new AlarmMainScreen(PetAddMainScreen.this);
				} else if (source == imageLabel) {
					System.out.println("👤 프로필 클릭됨!");
					dispose();
					new UpdateUserScreen(PetAddMainScreen.this);
				} else if (source == imageProfileLabel) {
					dispose();
					new UpdateUserScreen(PetAddMainScreen.this);
				} else if (source == addButtonLabel) {
					System.out.println("➕ 추가 버튼 클릭됨!");
					if (pc == null) {
						pc = new PetChooseDialog(PetAddMainScreen.this);
						// ZipcodeFrame의 창의 위치를 MemberAWT 옆에 지정
						pc.setLocation(getX() + 25, getY() + 300);
					} else {
						pc.setLocation(getX() + 25, getY() + 300);
						pc.setVisible(true);
					}
					setEnabled(false);
				}
			}
		};

		// 🔹 알람 아이콘
		alarmLabel = createScaledImageLabel("TeamProject/alarm.png", 40, 40);
		alarmLabel.setBounds(280, 120, 40, 40);
		alarmLabel.addMouseListener(commonMouseListener);
		add(alarmLabel);

		// 메인 프로필 이미지
		System.out.println(bean1.getUser_image());
		byte[] imgBytes = bean1.getUser_image();
		String imgNull = Arrays.toString(imgBytes);
		if (imgBytes == null || imgBytes.length == 0) {
			imageLabel = new JLabel();
			imageLabel = createScaledImageLabel("TeamProject/profile.png", 200, 200);
			imageLabel.setBounds(101, 178, 200, 200);
			imageLabel.addMouseListener(commonMouseListener);
			add(imageLabel);
		} else {
			ImageIcon icon = new ImageIcon(imgBytes);
			Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			imageLabel = new JLabel();
			imageLabel.setIcon(new ImageIcon(img));
			imageLabel.setBounds(101, 178, 200, 200);
			imageLabel.addMouseListener(commonMouseListener);
			add(imageLabel);
		}

		// 상단 프로필 아이디
		if (imgNull == "[]") {
			imageProfileLabel = new JLabel();
			imageProfileLabel = createScaledImageLabel("TeamProject/profile.png", 40, 40);
			imageProfileLabel.setBounds(330, 120, 40, 40);
			imageProfileLabel.addMouseListener(commonMouseListener);
			add(imageProfileLabel);
		} else {
			ImageIcon icon1 = new ImageIcon(imgBytes);
			Image img1 = icon1.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			imageProfileLabel = new JLabel();
			imageProfileLabel.setIcon(new ImageIcon(img1));
			imageProfileLabel.setBounds(330, 120, 40, 40);
			imageProfileLabel.addMouseListener(commonMouseListener);
			add(imageProfileLabel);
		}

		// 환영 문구
		welcomeLabel = new JLabel("어서오세요, " + mgr.userName(LoginScreen.id) + "님");
		welcomeLabel.setBounds(135, 401, 134, 20);
		welcomeLabel.setForeground(Color.BLACK);
		add(welcomeLabel);
		
		//한 사용자당 반려동물은 2마리로 제한
			
		
		//반려견 프로필
		

		// 로그아웃 버튼
		logoutButton = new RoundedButton("로그아웃");
		logoutButton.setBounds(30, 122, 85, 36);
		logoutButton.setBackground(new Color(91, 91, 91));
		logoutButton.setForeground(Color.WHITE);
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				mgr.userOut(StaticData.user_id);
				new LoginScreen();
			}
		});
		add(logoutButton);

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
				g.drawLine(22, 443, 379, 443);
				g.drawLine(22, 639, 379, 639);
			}
		};

		panel.setOpaque(false);
		panel.setLayout(null);
		add(panel);
		
		// 🔹 스크롤 가능한 게시글 패널 설정
		petaddPanel = new JPanel();
		petaddPanel.setLayout(new BoxLayout(petaddPanel, BoxLayout.Y_AXIS)); // 세로로 쌓이게 설정
		petaddPanel.setBackground(Color.darkGray);
		
		// 🔹 더미 게시글 데이터 추가
				for (int i = 1; i <= 5; i++) {
					petAddMain();
				}
				
				// 🔹 스크롤 패널 추가 (23, 165, 357, 615 영역에 배치)
				scrollPane = new JScrollPane(petaddPanel);
				scrollPane.setBounds(23, 430, 357, 360);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 스크롤바 숨기기
				scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 부드러운 스크롤 유지
				panel.add(scrollPane);
				
				// 🔹 추가 버튼 (화면에 고정)
				addButtonLabel = createScaledImageLabel("TeamProject/add_button.png", 70, 70);
				addButtonLabel.setBounds(300, 700, 70, 70);
				addButtonLabel.addMouseListener(commonMouseListener);
				addButtonLabel.setOpaque(true);
				addButtonLabel.setBackground(new Color(255, 255, 255, 0));
				addButtonLabel.setVisible(true);
				getLayeredPane().add(addButtonLabel, JLayeredPane.PALETTE_LAYER);


		// 🔹 스크롤 가능한 게시글 패널 설정
		petaddPanel = new JPanel();
		petaddPanel.setLayout(new BoxLayout(petaddPanel, BoxLayout.Y_AXIS)); // 세로로 쌓이게 설정
		petaddPanel.setBackground(Color.darkGray);

		petAddMain();

		// 🔹 스크롤 패널 추가 (23, 165, 357, 615 영역에 배치)
		scrollPane = new JScrollPane(petaddPanel);
		scrollPane.setBounds(23, 430, 357, 360);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 스크롤바 숨기기
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 부드러운 스크롤 유지
		panel.add(scrollPane);

		// 🔹 추가 버튼 (화면에 고정)
		addButtonLabel = createScaledImageLabel("TeamProject/add_button.png", 70, 70);
		addButtonLabel.setBounds(300, 700, 70, 70);
		addButtonLabel.addMouseListener(commonMouseListener);
		addButtonLabel.setOpaque(true);
		addButtonLabel.setBackground(new Color(255, 255, 255, 0));
		addButtonLabel.setVisible(true);
		getLayeredPane().add(addButtonLabel, JLayeredPane.PALETTE_LAYER);

		// 🔹 닫기 버튼
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
	
	private void petAddMain() {

		for (PetBean pb : vlist) {
			JPanel petAddMainPanel = new JPanel();
			petAddMainPanel.setPreferredSize(new Dimension(353, 150)); // 크기 지정
			petAddMainPanel.setBackground(Color.WHITE);
			petAddMainPanel.setBorder(new LineBorder(Color.black, 1)); // 외곽 테두리
			petAddMainPanel.setLayout(new BorderLayout(10, 10)); // 여백 포함

			// 2) 상단 패널 (USER_ID + 날짜)
			JPanel topPanel = new JPanel(new BorderLayout());

			// 왼쪽 - 이미지
			System.out.println(pb.getPet_image());
			byte[] imgBytes = pb.getPet_image();
			String imgNull = Arrays.toString(imgBytes);
			System.out.println(imgNull);
			petImageLabel = new JLabel(); // JLabel을 먼저 생성
			if (imgBytes == null || imgBytes.length == 0) {
				petImageLabel = createScaledImageLabel("TeamProject/dog.png", 135, 135);
				petImageLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
						new PetHomeScreen(pb.getPet_id());
					}
				});
				} else {
				ImageIcon icon1 = new ImageIcon(imgBytes);
				Image img1 = icon1.getImage().getScaledInstance(135, 135, Image.SCALE_SMOOTH);
				petImageLabel.setIcon(new ImageIcon(img1));
				petImageLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
						new PetHomeScreen(pb.getPet_id());
					}
				});
			}

			// 4) 본문 패널 (이미지 + 텍스트)
			JPanel contentPanel = new JPanel(new BorderLayout(10, 0));
			contentPanel.setBackground(Color.WHITE);
			contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 3, 3, 0)); // 위, 왼쪽, 아래, 오른쪽 순서
			contentPanel.add(petImageLabel, BorderLayout.WEST);

			// 오른쪽 - 이름, 종, 나이, 성별
			JPanel textPanel = new JPanel();
			textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
			textPanel.setBackground(Color.WHITE);

			JLabel nameLabel = new JLabel("이름 : " + pb.getPet_name());
			JLabel speciesLabel = new JLabel("종 : " + pb.getPet_species());
			JLabel ageLabel = new JLabel("나이 : " + pb.getPet_age());
			JLabel genderLabel = new JLabel("성별 : " + pb.getPet_gender());

			textPanel.add(nameLabel);
			textPanel.add(speciesLabel);
			textPanel.add(ageLabel);
			textPanel.add(genderLabel);
			textPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));

			contentPanel.add(textPanel, BorderLayout.CENTER);

			// 5) 전체 구성
			petAddMainPanel.add(contentPanel, BorderLayout.SOUTH);
			petaddPanel.add(petAddMainPanel);

			// 각 애완동물 항목 간에 간격을 둔다
			petaddPanel.add(Box.createVerticalStrut(0)); // 0px 간격
		}

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