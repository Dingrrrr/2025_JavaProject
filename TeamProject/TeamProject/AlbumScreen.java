package TeamProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

public class AlbumScreen extends JFrame{
	private BufferedImage image;
	private JLabel alarmLabel, profileLabel, addButtonLabel,photoLabel, homeLabel, commuLabel, voteLabel  ;
	private JLabel additionLabel, welcomeLabel1, welcomeLabel2, welcomeLabel3;
	private AlbumAddDialog pc;
	TPMgr mgr = new TPMgr();
	Vector<PetBean> vlist1;
	PetBean bean[] = new PetBean[2];
	
	
	public AlbumScreen() {
		setTitle("프레임 설정");
		setSize(402, 874);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vlist1 = mgr.showPet(StaticData.user_id);
		try {
			bean[0] = (PetBean)vlist1.elementAt(0);
			bean[1] = (PetBean)vlist1.elementAt(1);
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
					new AlarmMainScreen(AlbumScreen.this);
				} else if (source == profileLabel) {
					System.out.println("👤 프로필 클릭됨!");
					dispose();
					new UpdateUserScreen(AlbumScreen.this);
				} else if (source == addButtonLabel) {
					System.out.println("➕ 추가 버튼 클릭됨!");
					if(pc==null) {
						pc = new AlbumAddDialog(AlbumScreen.this);
						//ZipcodeFrame의 창의 위치를 MemberAWT 옆에 지정
						pc.setLocation(getX()+25, getY()+150);
					}else {
						pc.setLocation(getX()+25, getY()+150);
						pc.setVisible(true);
					}
					setEnabled(false);
				}else if (source == photoLabel) {
					System.out.println("앨범 & 일기 버튼 클릭됨");
					new AlbumChooseDialog(AlbumScreen.this);
				}else if (source == homeLabel) {
					System.out.println("홈 버튼 클릭됨");
					dispose();
					new PetHomeScreen(bean[0].getPet_id());
				}else if (source == commuLabel) {
					System.out.println("커뮤 버튼 클릭됨");
					dispose();
					new CommuMainScreen();
				}else if (source == voteLabel) {
					System.out.println("투표 버튼 클릭됨");
					dispose();
					new VoteMainScreen();
				}
			}
		};
		
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
				
				// 🔹 추가 버튼
				addButtonLabel = createScaledImageLabel("TeamProject/add_button.png", 92, 92);
				addButtonLabel.setBounds(155, 400, 92, 92);
				addButtonLabel.addMouseListener(commonMouseListener);
				add(addButtonLabel);
				
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
				
				
				
				// 환영 문구
				welcomeLabel1 = new JLabel("앨범이 비었어요");
				welcomeLabel2 = new JLabel("멋진 아이의 순간들을");
				welcomeLabel3 = new JLabel("기록해주세요!");
				welcomeLabel1.setBounds(155, 230, 134, 20);
				welcomeLabel2.setBounds(145, 255, 134, 20);
				welcomeLabel3.setBounds(160, 280, 134, 20);
				welcomeLabel1.setForeground(Color.BLACK);
				welcomeLabel2.setForeground(Color.BLACK);
				welcomeLabel3.setForeground(Color.BLACK);
				add(welcomeLabel1);
				add(welcomeLabel2);
				add(welcomeLabel3);
		
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
		}
	}
