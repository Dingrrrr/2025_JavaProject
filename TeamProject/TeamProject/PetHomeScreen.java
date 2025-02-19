package TeamProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class PetHomeScreen extends JFrame {
	private BufferedImage image;
	private JLabel backLabel, alarmLabel, profileLabel, petProfileLabel, addButtonLabel;
	private JLabel petNameLabel, petSpecLabel, petBirthLabel, petGenderLabel;
	private JLabel petRcDateLabel, petRcWHLabel, petRecordLabel, petRcVcLabel, petRcCheckLabel, petRcTimeLabel;
	private JLabel photoLabel, homeLabel, commuLabel, voteLabel;
	private JPanel scrollPanel; //스크롤 패널
	private JScrollPane scrollPane; 
	TPMgr mgr;
	PetBean bean;
	Vector<HRBean> hrV;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd   HH:mm");

	public PetHomeScreen(int petId) {
		setTitle("프레임 설정");
		setSize(402, 874);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mgr = new TPMgr();
		bean = mgr.showOnePet(petId);
		hrV = mgr.showHRPet(petId);

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
				} else if (source == profileLabel) {
					System.out.println("👤 프로필 클릭됨!");
					dispose();
					new UpdateUserScreen(PetHomeScreen.this);
				} else if (source == addButtonLabel) {
					System.out.println("➕ 추가 버튼 클릭됨!");
					dispose();
					new PetRecordAddScreen(bean, PetHomeScreen.this);
				} else if(source == backLabel) {
					dispose();
					new PetAddMainScreen();
				} else if (source == photoLabel) {
					System.out.println("앨범 & 일기 버튼 클릭됨");
					new AlbumChooseDialog();
				}else if (source == homeLabel) {
					System.out.println("홈 버튼 클릭됨");
					dispose();
					new PetAddMainScreen();
				}else if (source == commuLabel) {
					System.out.println("커뮤 버튼 클릭됨");
				}else if (source == voteLabel) {
					System.out.println("투표 버튼 클릭됨");
				}
			}
		};
		
        // 🔹 스크롤 패널 설정
        scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        scrollPanel.setBackground(Color.WHITE);
        
        for (HRBean hr : hrV) {
            JPanel recordPanel = new JPanel();
            recordPanel.setLayout(new GridLayout(6, 1));
            recordPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            recordPanel.setBackground(Color.WHITE);
            recordPanel.setPreferredSize(new Dimension(360, 120));

            // 🔹 반려동물 진료기록 작성일 라벨
            JLabel dateLabel = new JLabel("날짜: " + sdf.format(hr.getHr_date()));
            
            // 🔹 반려동물 키 / 몸무게 라벨
            JLabel whLabel = new JLabel("키: " + hr.getHeight() + "cm   몸무게: " + hr.getWeight() + "kg");
            
            // 🔹 반려동물 진료 기록 설명 라벨
            JLabel historyLabel = new JLabel("진료 기록: " + hr.getMedical_history());
            
        	// 🔹 반려동물 예방접종 상태 라벨
            JLabel vcLabel = new JLabel("예방접종 상태: " + hr.getVaccination_status());
            
        	// 🔹 반려동물 체크해야 할 정보 라벨
            JLabel checkLabel = new JLabel("체크해야 할 정보: " + hr.getCheckup_status());
            
            // 🔹 반려동물 진료 시간
            JLabel mtDateLabel = new JLabel("진료 시간: " + hr.getDate());

            recordPanel.add(dateLabel);
            recordPanel.add(whLabel);
            recordPanel.add(historyLabel);
            recordPanel.add(vcLabel);
            recordPanel.add(checkLabel);
            recordPanel.add(mtDateLabel);
            
            recordPanel.addMouseListener(new MouseAdapter() {
            	@Override
            	public void mouseClicked(MouseEvent e) {
            		dispose();
            		new PetRecordModifyScreen(mgr.showHRPetId(hr.getHr_date()), petId);
            	}
            });

            scrollPanel.add(recordPanel);
            
            //진료 기록이 하나인 경우
            if(hrV.size() == 1) {
                JPanel recordPanel2 = new JPanel();
                recordPanel2.setLayout(new GridLayout(6, 1));
                recordPanel2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                recordPanel2.setBackground(Color.WHITE);
                recordPanel2.setPreferredSize(new Dimension(360, 120));

                // 🔹 반려동물 진료기록 작성일 라벨
                JLabel dateLabel2 = new JLabel();
                
                // 🔹 반려동물 키 / 몸무게 라벨
                JLabel whLabel2 = new JLabel();
                
                // 🔹 반려동물 진료 기록 설명 라벨
                JLabel historyLabel2 = new JLabel();
                
            	// 🔹 반려동물 예방접종 상태 라벨
                JLabel vcLabel2 = new JLabel();
                
            	// 🔹 반려동물 체크해야 할 정보 라벨
                JLabel checkLabel2 = new JLabel();
                
                // 🔹 반려동물 진료 시간
                JLabel mtDateLabel2 = new JLabel();

                recordPanel2.add(dateLabel2);
                recordPanel2.add(whLabel2);
                recordPanel2.add(historyLabel2);
                recordPanel2.add(vcLabel2);
                recordPanel2.add(checkLabel2);
                recordPanel2.add(mtDateLabel2);

                scrollPanel.add(recordPanel2);
            }
        }

        // 🔹 스크롤 가능한 JScrollPane 생성
        scrollPane = new JScrollPane(scrollPanel);
        scrollPane.setBounds(21, 371, 360, 410);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 스크롤바 숨기기
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);	//부드러운 스크롤 유지
        add(scrollPane);
		
		
		// 🔹 상단 뒤로가기 아이콘
		backLabel = createScaledImageLabel("TeamProject/back_button.png", 40, 40);
		backLabel.setBounds(25, 120, 40, 40);
		backLabel.addMouseListener(commonMouseListener);
		add(backLabel);

		// 🔹 알람 아이콘
		alarmLabel = createScaledImageLabel("TeamProject/alarm.png", 40, 40);
		alarmLabel.setBounds(280, 120, 40, 40);
		alarmLabel.addMouseListener(commonMouseListener);
		add(alarmLabel);

		// 🔹 상단 프로필 아이콘
		profileLabel = createScaledImageLabel("TeamProject/profile.png", 40, 40);
		profileLabel.setBounds(330, 120, 40, 40);
		profileLabel.addMouseListener(commonMouseListener);
		add(profileLabel);

		// 🔹 반려동물 프로필 이미지
		petProfileLabel = createScaledImageLabel("TeamProject/dog.png", 150, 150);
		petProfileLabel.setBounds(40, 190, 150, 150);
		add(petProfileLabel);

		// 🔹 반려동물 이름 라벨
		petNameLabel = new JLabel("이름: " + bean.getPet_name());
		petNameLabel.setBounds(230, 210, 150, 27); // (x, y, 너비, 높이)
		petNameLabel.setForeground(Color.BLACK); // 텍스트 색 설정
		add(petNameLabel);

		// 🔹 반려동물 종 라벨
		petSpecLabel = new JLabel("종: " + bean.getPet_species());
		petSpecLabel.setBounds(230, 240, 150, 27); // (x, y, 너비, 높이)
		petSpecLabel.setForeground(Color.BLACK); // 텍스트 색 설정
		add(petSpecLabel);

		// 🔹 반려동물 생년월일 라벨
		petBirthLabel = new JLabel("생년월일: " + bean.getPet_age());
		petBirthLabel.setBounds(230, 270, 150, 27); // (x, y, 너비, 높이)
		petBirthLabel.setForeground(Color.BLACK); // 텍스트 색 설정
		add(petBirthLabel);

		// 🔹 반려동물 성별 라벨
		petGenderLabel = new JLabel("성별: " + bean.getPet_gender());
		petGenderLabel.setBounds(230, 300, 150, 27); // (x, y, 너비, 높이)
		petGenderLabel.setForeground(Color.BLACK); // 텍스트 색 설정
		add(petGenderLabel);
		

		// 🔹 추가 버튼 (화면에 고정)
		addButtonLabel = createScaledImageLabel("TeamProject/add_button.png", 70, 70);
		addButtonLabel.setBounds(293, 700, 70, 70);
		addButtonLabel.addMouseListener(commonMouseListener);
		addButtonLabel.setOpaque(true);
		addButtonLabel.setBackground(new Color(255, 255, 255, 0));
		addButtonLabel.setVisible(true);
		getLayeredPane().add(addButtonLabel, JLayeredPane.PALETTE_LAYER);

		// 🔹 앨범 & 일기 버튼
		photoLabel = createScaledImageLabel("TeamProject/photo.png", 60, 60);
		photoLabel.setBounds(37, 785, 60, 60);
		photoLabel.addMouseListener(commonMouseListener);
		add(photoLabel);

		// 🔹 홈 버튼
		homeLabel = createScaledImageLabel("TeamProject/home.png", 58, 58);
		homeLabel.setBounds(125, 787, 58, 58);
		homeLabel.addMouseListener(commonMouseListener);
		add(homeLabel);

		// 🔹 커뮤니티 버튼
		commuLabel = createScaledImageLabel("TeamProject/commu.png", 58, 58);
		commuLabel.setBounds(215, 788, 58, 58);
		commuLabel.addMouseListener(commonMouseListener);
		add(commuLabel);

		// 🔹 투표 버튼
		voteLabel = createScaledImageLabel("TeamProject/vote.png", 55, 55);
		voteLabel.setBounds(305, 789, 55, 55);
		voteLabel.addMouseListener(commonMouseListener);
		add(voteLabel);

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
				g.drawLine(22, 370, 379, 370);
				g.drawLine(22, 574, 379, 574);
				g.drawLine(22, 780, 379, 780);
				g.drawLine(111, 780, 111, 851);
				g.drawLine(200, 780, 200, 851);
				g.drawLine(289, 780, 289, 851);
				
				Graphics2D g2 = (Graphics2D) g; // Graphics를 Graphics2D로 캐스팅
				g2.setColor(Color.black);
				g2.setStroke(new BasicStroke(5)); // 선 두께 5px 설정
				g2.drawLine(135, 841, 262, 841); // (x1, y1) -> (x2, y2)까지 선 그리기
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
		closeButton.addActionListener(e -> System.exit(0));
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
