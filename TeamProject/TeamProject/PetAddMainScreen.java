package TeamProject;

import java.awt.*;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class PetAddMainScreen extends JFrame {

	private BufferedImage image;
	private JLabel alarmLabel, profileLabel, mainProfileLabel, petProfileLabel, addButtonLabel, imageLabel,
			imageProfileLabel, petImageLabel, logoutLabel;
	private ImageIcon image2;
	private JLabel welcomeLabel, petNameLabel, petSpeciesLabel, petAgeLabel, petGenderLabel;
	TPMgr mgr = new TPMgr();
	PetBean bean;
	Vector<PetBean> vlist;
	private PetChooseDialog pc;
	private JPanel petaddPanel;
	private JScrollPane scrollPane; // 스크롤 패널
	private byte[] imageBytes, imageBytes1;

	public PetAddMainScreen() {
		setTitle("프레임 설정");
		setSize(402, 874);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UserBean bean1 = mgr.showUser(StaticData.user_id);
		vlist = mgr.showPet(StaticData.user_id);

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
				} else if (source == logoutLabel) {
					dispose();
					mgr.userOut(StaticData.user_id);
					new LoginScreen();
				}
			}
		};

		// 🔹 알람 아이콘
		alarmLabel = createScaledImageLabel("TeamProject/alarm.png", 40, 40);
		alarmLabel.setBounds(280, 120, 40, 40);
		alarmLabel.addMouseListener(commonMouseListener);
		add(alarmLabel);

		// 메인 프로필 이미지
		byte[] imgBytes = bean1.getUser_image();
		if (imgBytes == null || imgBytes.length == 0) {
			// 기본 프로필 이미지 사용
			imageLabel = new JLabel();
			imageLabel = createScaledImageLabel("TeamProject/profile.png", 200, 200);
			imageLabel.setBounds(101, 178, 200, 200);
			imageLabel.addMouseListener(commonMouseListener);
			add(imageLabel);
		} else {
			// 사용자 이미지가 있을 경우
			ImageIcon icon = new ImageIcon(imgBytes);
			Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);

			// RoundedImageLabel 사용
			RoundedImageLabel roundedImageLabel = new RoundedImageLabel(img, 200, 200, 3); // 100은 둥근 정도
			roundedImageLabel.setBounds(101, 185, 200, 200);
			roundedImageLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("👤 프로필 클릭됨!");
					dispose();
					new UpdateUserScreen(PetAddMainScreen.this);
				}
			});
			add(roundedImageLabel);
		}

		// 상단 프로필 아이디
		if (imgBytes == null || imgBytes.length == 0) {
			imageProfileLabel = new JLabel();
			imageProfileLabel = createScaledImageLabel("TeamProject/profile.png", 40, 40);
			imageProfileLabel.setBounds(330, 120, 40, 40);
			imageProfileLabel.addMouseListener(commonMouseListener);
			add(imageProfileLabel);
		} else {
			// 사용자 이미지가 있을 경우
			ImageIcon icon1 = new ImageIcon(imgBytes);
			Image img = icon1.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);

			// RoundedImageLabel 사용
			RoundedImageLabel roundedProfileImageLabel = new RoundedImageLabel(img, 40, 40, 3); // 100은 둥근 정도
			roundedProfileImageLabel.setBounds(330, 120, 40, 40);
			roundedProfileImageLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("👤 프로필 클릭됨!");
					dispose();
					new UpdateUserScreen(PetAddMainScreen.this);
				}
			});
			add(roundedProfileImageLabel);
		}

		// 환영 문구
		welcomeLabel = new JLabel("어서오세요, " + mgr.userName(StaticData.user_id) + "님");
		welcomeLabel.setBounds(135, 401, 134, 20);
		welcomeLabel.setForeground(Color.BLACK);
		add(welcomeLabel);

		// 로그아웃 버튼
		logoutLabel = createScaledImageLabel("TeamProject/logout_icon.png", 40, 40);
		logoutLabel.setBounds(30, 120, 40, 40);
		logoutLabel.addMouseListener(commonMouseListener);
		add(logoutLabel);

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
		petaddPanel.setBorder(new LineBorder(Color.BLACK, 1));
		petaddPanel.setBackground(Color.WHITE);

		petAddMain();

		// 🔹 스크롤 패널 추가 (23, 165, 357, 615 영역에 배치)
		scrollPane = new JScrollPane(petaddPanel);
		scrollPane.setBounds(23, 430, 357, 360);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 스크롤바 숨기기
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 부드러운 스크롤 유지
		scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
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
			petAddMainPanel.setPreferredSize(new Dimension(353, 160)); // 크기 지정
			petAddMainPanel.setMaximumSize(new Dimension(353, 160)); // 최대 크기 고정
			petAddMainPanel.setBackground(Color.WHITE);
			petAddMainPanel.setBorder(new LineBorder(Color.black, 0));
			petAddMainPanel.setLayout(new BorderLayout(10, 10)); // 여백 포함

			// 2) 상단 패널 (USER_ID + 날짜)
			JPanel topPanel = new JPanel(new BorderLayout());

			// 3) 구분선
			JSeparator separator = new JSeparator();
			separator.setForeground(Color.BLACK);

			// 왼쪽 - 이미지
			byte[] imgBytes = pb.getPet_image();
			String imgNull = Arrays.toString(imgBytes);
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
				petImageLabel.setVerticalAlignment(JLabel.TOP);
				petImageLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
						new PetHomeScreen(pb.getPet_id());
					}
				});
			}

			// 4) 본문 패널 (이미지 + 텍스트)
			JPanel contentPanel = new JPanel();
			contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5)); // 왼쪽 정렬 FlowLayout으로 변경
			contentPanel.setBackground(Color.WHITE);
			contentPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // 위, 왼쪽, 아래, 오른쪽 순서
			contentPanel.add(petImageLabel);

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
			textPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

			contentPanel.add(textPanel, BorderLayout.CENTER);
			// 5) 전체 구성
			petAddMainPanel.add(contentPanel, BorderLayout.CENTER);
			petAddMainPanel.add(separator, BorderLayout.SOUTH);

			// petaddPanel에 추가
			petaddPanel.add(petAddMainPanel);

			// 각 애완동물 항목 간에 간격을 둔다
			petaddPanel.add(Box.createVerticalStrut(0)); // 0px 간격
			
			
			//반려동물 생일 알림
			Calendar calendar = Calendar.getInstance();
	        int month1 = calendar.get(Calendar.MONTH) + 1; // 월은 0부터 시작하므로 +1
	        int day1 = calendar.get(Calendar.DAY_OF_MONTH);
	        String today = String.format("%02d%02d", month1, day1); // 형식: MM월 DD일
	        String birth = pb.getPet_age();
	        if(mgr.isPetBirth(pb.getPet_id()).equals(birth)) {	//마지막으로 알림 보낸 날짜가 오늘이랑 같을 경우
	        	//반응 안함
	        } else {	//마지막으로 알림 보낸 날짜가 오늘이 아닌경우
				String[] date = birth.split("\\.");
				String month = date[1];
				String day = date[2];
				if(today.equals(month+day)) {		//오늘이 생일인 경우
					MsgBean mb = new MsgBean();
					mb.setMsg_title(pb.getPet_name() + "의 특별한 날! 생일 축하해요!");
					mb.setMsg_content("안녕하세요! 좋은 소식을 전해 드립니다! \n"
							+ "오늘은 바로" + pb.getPet_name() + "의 생일이에요!\n"
							+ "이 특별한 날을 축하해 주세요! \n맛있는 간식과 함께 행복한 시간을 보내길 바래요. \n"
							+ pb.getPet_name() + "도 여러분의 사랑을 기다리고 있을 거예요! \n"
							+ "즐거운 하루 되세요!");
					mb.setReceiver_id(StaticData.user_id);
					mgr.sendMsg("admin", mb);
					mgr.petBirth(pb.getPet_id(), birth);
				}
	        }
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