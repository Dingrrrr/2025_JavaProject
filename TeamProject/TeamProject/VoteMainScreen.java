package TeamProject;

import javax.imageio.ImageIO;
import javax.sql.CommonDataSource;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.Vector;

public class VoteMainScreen extends JFrame {
	private BufferedImage image;
	private ImageIcon image2;
	private JLabel alarmLabel, profileLabel, voteLabel, addButtonLabel, photoLabel, homeLabel, commuLabel,
			imageProfileLabel, likeCountLabel, logoLabel, popularLabel, newLabel, oldLabel, voteAddLabel;
	private JPanel votePanel; // 투표 패널
	private JScrollPane scrollPane; // 스크롤 패널
	private VoteAddDialog va;
	private TPMgr mgr = new TPMgr();

	private Vector<VoteBean> vlist = mgr.showVote(StaticData.vote_id);;

	public VoteMainScreen() {
		setTitle("프레임 설정");
		setSize(402, 874);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mgr = new TPMgr();
		UserBean bean = mgr.showUser(StaticData.user_id);

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
					new AlarmMainScreen(VoteMainScreen.this);
				} else if (source == profileLabel) {
					System.out.println("👤 프로필 클릭됨!");
					dispose();
					new UpdateUserScreen(VoteMainScreen.this);
				} else if (source == photoLabel) {
					System.out.println("앨범 & 일기 버튼 클릭됨");
					setEnabled(false);
					new AlbumChooseDialog(VoteMainScreen.this);
				} else if (source == homeLabel) {
					System.out.println("홈 버튼 클릭됨");
					dispose();
					new PetHomeScreen(StaticData.pet_id);
				} else if (source == commuLabel) {
					System.out.println("커뮤 버튼 클릭됨");
					dispose();
					new CommuMainScreen();
				} else if (source == voteLabel) {
					System.out.println("투표 버튼 클릭됨");
					dispose();
					new VoteMainScreen();
				} else if (source == addButtonLabel) {
					if(popularLabel.isVisible()) {
						popularLabel.setVisible(false);
						newLabel.setVisible(false);
						oldLabel.setVisible(false);
						voteAddLabel.setVisible(false);
					} else {
						popularLabel.setVisible(true);
						newLabel.setVisible(true);
						oldLabel.setVisible(true);
						voteAddLabel.setVisible(true);
					}
				} else if (source == popularLabel) {
					System.out.println("인기순 버튼 클릭됨");
					vlist = mgr.popVote();
					addVote();
				} else if (source == newLabel) {
					System.out.println("최신순 버튼 클릭됨");
					vlist = mgr.newVote();
					addVote();
				} else if (source == oldLabel) {
					System.out.println("오래된순 버튼 클릭됨");
					vlist = mgr.oldVote();
					addVote();
				} else if(source == voteAddLabel) {
					System.out.println("투표 추가 버튼 클릭됨!");
					if (va == null) {
						va = new VoteAddDialog(VoteMainScreen.this);
						va.setLocation(getX() + 25, getY() + 150);
					} else {
						va.setLocation(getX() + 25, getY() + 150);
						va.setVisible(true);
					}
					setEnabled(false);
					popularLabel.setVisible(false);
					newLabel.setVisible(false);
					oldLabel.setVisible(false);
					voteAddLabel.setVisible(false);
				}
			}
		};

		// 🔹 알람 아이콘
		alarmLabel = createScaledImageLabel("TeamProject/alarm.png", 40, 40);
		alarmLabel.setBounds(280, 120, 40, 40);
		alarmLabel.addMouseListener(commonMouseListener);
		add(alarmLabel);

		// 로고 아이콘
		logoLabel = createScaledImageLabel("TeamProject/logo2.png", 180, 165);
		logoLabel.setBounds(5, 54, 180, 165);
		logoLabel.setVisible(true);
		add(logoLabel);

		byte[] imgBytes = bean.getUser_image();
		String imgNull = Arrays.toString(imgBytes);
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
					new UpdateUserScreen(VoteMainScreen.this);
				}
			});
			add(roundedProfileImageLabel);
		}

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
		voteLabel = createScaledImageLabel("TeamProject/vote_click.png", 55, 55);
		voteLabel.setBounds(305, 789, 55, 55);
		voteLabel.addMouseListener(commonMouseListener);
		add(voteLabel);
		
		//인기순
		popularLabel = createScaledImageLabel("TeamProject/popularity.png", 40, 40);
		popularLabel.setBounds(305, 615, 40, 40);
		popularLabel.addMouseListener(commonMouseListener);
		popularLabel.setVisible(false);
		add(popularLabel); 
		
		//최신순
		newLabel = createScaledImageLabel("TeamProject/new.png", 40, 40);
		newLabel.setBounds(305, 555, 40, 40);
		newLabel.addMouseListener(commonMouseListener);
		newLabel.setVisible(false);
		add(newLabel); 
		
		//오래된순
		oldLabel = createScaledImageLabel("TeamProject/old.png", 40, 40);
		oldLabel.setBounds(305, 495, 40, 40);
		oldLabel.addMouseListener(commonMouseListener);
		oldLabel.setVisible(false);
		add(oldLabel);
		
		//투표 추가 버튼
		voteAddLabel = createScaledImageLabel("TeamProject/vote_add.png", 40, 40);
		voteAddLabel.setBounds(305, 675, 40, 40);
		voteAddLabel.addMouseListener(commonMouseListener);
		voteAddLabel.setVisible(false);
		add(voteAddLabel); 

		// 🔹 배경 패널
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					Image scaledImage = image.getScaledInstance(402, 874, Image.SCALE_SMOOTH);
					g.drawImage(scaledImage, 0, 0, this);
				}
				g.setColor(Color.LIGHT_GRAY);
				g.drawLine(22, 164, 379, 164);
				g.drawLine(22, 780, 379, 780);
				g.drawLine(111, 780, 111, 851);
				g.drawLine(200, 780, 200, 851);
				g.drawLine(289, 780, 289, 851);
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(Color.black);
				g2.setStroke(new BasicStroke(5));
				g2.drawLine(135, 841, 262, 841);
			}
		};

		panel.setOpaque(false);
		panel.setLayout(null);
		add(panel);

		// 🔹 추가 버튼 (화면에 고정)
		addButtonLabel = createScaledImageLabel("TeamProject/vote_menu.png", 85, 45);
		addButtonLabel.setBounds(280, 725, 85, 45);
		addButtonLabel.addMouseListener(commonMouseListener);
		addButtonLabel.setOpaque(true);
		addButtonLabel.setBackground(new Color(255, 255, 255, 0));
		addButtonLabel.setVisible(true);
		getLayeredPane().add(addButtonLabel, JLayeredPane.PALETTE_LAYER);

		// 🔹 votePanel 설정 수정
		votePanel = new JPanel();
		votePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); // 2열 정렬
		votePanel.setBackground(Color.WHITE);
		votePanel.setBorder(new LineBorder(Color.WHITE, 1));

		// 🔹 스크롤 패널 추가 (23, 165, 357, 615 영역에 배치)
		// 이전 코드에서는 scrollPane이 이 부분 앞에 있을 수 있어, 여기에 잘못된 위치에서 접근되고 있었을 가능성이 있습니다.
		scrollPane = new JScrollPane(votePanel);
		scrollPane.setBounds(23, 165, 357, 615);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 스크롤바 숨기기
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 부드러운 스크롤 유지
		scrollPane.setBorder(new MatteBorder(0, 0, 0, 0, Color.white));
		panel.add(scrollPane);

		addVote();

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

	/**
	 * 투표 추가 메서드
	 */

	public void addVote() {
		// 기존 투표 목록을 삭제하여 중복 추가 방지
		votePanel.removeAll();

		for (VoteBean vb : vlist) {
			StaticData.vote_id = vb.getVote_id();

			// 1️ 개별 투표 아이템을 담을 패널 생성
			JPanel contentPanel = new JPanel(null); // 직접 위치 설정을 위해 null 레이아웃 사용
			contentPanel.setPreferredSize(new Dimension(176, 150)); // 크기 설정
			contentPanel.setBackground(Color.WHITE);

			contentPanel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("이미지 클릭됨");
					setEnabled(false);
					if (vb.getUser_id().equals(StaticData.user_id)) { // 내가 올린 투표
						new VoteModifyScreen(vb, VoteMainScreen.this);
					} else { // 남이 올린 투표
						new VoteScreenDialog(VoteMainScreen.this, vb);
					}
				}
			});

			// 투표 레이블 생성
			byte[] imgBytes = vb.getVote_image();
			JLabel imageLabel = new JLabel(); // JLabel을 먼저 생성
			if (imgBytes == null || imgBytes.length == 0) {
				imageLabel = createScaledImageLabel("TeamProject/photo_frame.png", 176, 150);
				imageLabel.setPreferredSize(new Dimension(176, 150));
				imageLabel.setMaximumSize(new Dimension(176, 150));
				imageLabel.setOpaque(false);
			} else {
				ImageIcon icon1 = new ImageIcon(imgBytes);
				Image img1 = icon1.getImage();

				// 원본 이미지 크기
				int imgWidth = icon1.getIconWidth();
				int imgHeight = icon1.getIconHeight();

				// 타겟 크기 (176x150)
				int targetWidth = 176;
				int targetHeight = 150;

				// 비율 유지하면서 자르기 위해 더 많이 필요한 쪽 기준으로 크기 조정
				double targetRatio = (double) targetWidth / targetHeight;
				double imgRatio = (double) imgWidth / imgHeight;

				int cropWidth = imgWidth;
				int cropHeight = imgHeight;

				if (imgRatio > targetRatio) {
					// 원본이 더 넓은 경우 → 가로를 자름
					cropWidth = (int) (imgHeight * targetRatio);
				} else {
					// 원본이 더 높은 경우 → 세로를 자름
					cropHeight = (int) (imgWidth / targetRatio);
				}

				// 중심을 기준으로 자를 영역 계산
				int x = (imgWidth - cropWidth) / 2;
				int y = (imgHeight - cropHeight) / 2;

				// BufferedImage로 자르기
				BufferedImage bufferedImage = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
				Graphics g = bufferedImage.getGraphics();
				g.drawImage(img1, 0, 0, null);
				g.dispose();

				BufferedImage croppedImage = bufferedImage.getSubimage(x, y, cropWidth, cropHeight);

				// 173x100으로 크기 조정
				Image scaledImage = croppedImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
				ImageIcon croppedIcon = new ImageIcon(scaledImage);
				
				imageLabel.setIcon(croppedIcon);
				imageLabel.setPreferredSize(new Dimension(targetWidth, targetHeight));
				imageLabel.setMaximumSize(new Dimension(targetWidth, targetHeight));
				
				imageLabel.setOpaque(false);
			}
			imageLabel.setBounds(0, 0, 176, 150);

			// contentPanel의 아랫부분에만 검정색 테두리 추가
			Border blackBottomBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
			imageLabel.setBorder(blackBottomBorder);

			// 🔹 `JLayeredPane`을 사용해 이미지 위에 하트 버튼을 배치
			JLayeredPane layeredPane = new JLayeredPane();
			layeredPane.setLayout(null);
			layeredPane.setBounds(0, 0, 176, 150); // 전체 크기 맞춤
			// 🔹 이미지 추가 (기본 레이어)
			layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);
			JLabel voteLabel = createScaledImageLabel("TeamProject/vote_empty.png", 40, 40);

			// 중복 투표 여부 확인
			if (!mgr.alrLikeVote(vb.getVote_id(), StaticData.user_id)) {
				voteLabel.setBounds(130, 105, 40, 40); // 💡 오른쪽 아래로 이동
				voteLabel.setOpaque(false);
			} else if (mgr.alrLikeVote(vb.getVote_id(), StaticData.user_id)) { // 이미 투표했으면 true 출력
				voteLabel = createScaledImageLabel("TeamProject/vote_complete.png", 40, 40);
				voteLabel.setBounds(130, 105, 40, 40);
				voteLabel.setOpaque(false);
			}

			// 🔹 하트 버튼을 이미지 위에 추가 (위쪽 레이어)
			layeredPane.add(voteLabel, JLayeredPane.PALETTE_LAYER);
			// 🔹 contentPanel에 `layeredPane` 추가 (이미지 & 버튼 함께 추가됨)
			contentPanel.add(layeredPane);

			// 좋아요 개수 가져오기
			int likeCount = mgr.getVoteLikeCount(vb.getVote_id());
			if (likeCount < 0) {
				likeCount = 0;
			}

			boolean isLiked = mgr.alrLikeVote(vb.getVote_id(), StaticData.user_id);

			// 좋아요 개수 라벨 생성
			likeCountLabel = new JLabel(String.valueOf(likeCount), SwingConstants.CENTER);

			likeCountLabel.setBounds(135, 114, 30, 20);
			likeCountLabel.setFont(new Font("Arial", Font.BOLD, 12));

			if (isLiked) {
				likeCountLabel.setForeground(Color.WHITE);

			} else {
				likeCountLabel.setForeground(Color.BLACK);
			}

			// 좋아요 개수
			layeredPane.add(likeCountLabel, JLayeredPane.DRAG_LAYER, 0);

			// ✅ 디버깅을 위한 로그 추가
			System.out.println("likeCountLabel 추가됨: " + likeCountLabel.getText());
			System.out.println("현재 layeredPane에 포함된 컴포넌트 개수: " + layeredPane.getComponentCount());

			contentPanel.add(layeredPane);
			votePanel.add(contentPanel);

		}
		// 패널 크기 갱신 (투표 개수에 따라 스크롤 가능하도록 조정)
		int rows = (votePanel.getComponentCount() + 1) / 2; // 2열 기준
		votePanel.setPreferredSize(new Dimension(338, rows * 151)); // 세로 크기 유지

		votePanel.revalidate();
		votePanel.repaint();
		scrollPane.revalidate();

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