package TeamProject;

import javax.imageio.ImageIO;
import javax.sql.CommonDataSource;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
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
			imageProfileLabel, likeCountLabel;
	private JPanel votePanel; // 투표 패널
	private JScrollPane scrollPane; // 스크롤 패널
	private VoteAddDialog va;
	private JButton popularButton, recentButton, oldButton;
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
					new PetAddMainScreen();
				} else if (source == commuLabel) {
					System.out.println("커뮤 버튼 클릭됨");
					dispose();
					new CommuMainScreen();
				} else if (source == voteLabel) {
					System.out.println("투표 버튼 클릭됨");
					dispose();
					new VoteMainScreen();
				} else if (source == addButtonLabel) {
					System.out.println("투표 추가 버튼 클릭됨!");
					if (va == null) {
						va = new VoteAddDialog(VoteMainScreen.this);
						va.setLocation(getX() + 25, getY() + 150);
					} else {
						va.setLocation(getX() + 25, getY() + 150);
						va.setVisible(true);
					}
					setEnabled(false);
				} else if (source == popularButton) {
					System.out.println("인기순 버튼 클릭됨");
					vlist = mgr.popVote();
					addVote();
				} else if (source == recentButton) {
					System.out.println("최신순 버튼 클릭됨");
					vlist = mgr.newVote();
					addVote();
				} else if (source == oldButton) {
					System.out.println("오래된순 버튼 클릭됨");
					vlist = mgr.oldVote();
					addVote();
				}
			}
		};

		// 🔹 알람 아이콘
		alarmLabel = createScaledImageLabel("TeamProject/alarm.png", 40, 40);
		alarmLabel.setBounds(280, 120, 40, 40);
		alarmLabel.addMouseListener(commonMouseListener);
		add(alarmLabel);

		System.out.println(bean.getUser_image());
		byte[] imgBytes = bean.getUser_image();
		String imgNull = Arrays.toString(imgBytes);
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

		// 인기순 버튼
		popularButton = new RoundedButton("인기순");
		popularButton.setBounds(175, 165, 60, 30);
		popularButton.setBackground(new Color(91, 91, 91));
		popularButton.setForeground(Color.WHITE);
		popularButton.addMouseListener(commonMouseListener);
		add(popularButton);

		// 최신순 버튼
		recentButton = new RoundedButton("최신순");
		recentButton.setBounds(245, 165, 60, 30);
		recentButton.setBackground(new Color(91, 91, 91));
		recentButton.setForeground(Color.WHITE);
		recentButton.addMouseListener(commonMouseListener);
		add(recentButton);

		// 오래된 순 버튼
		oldButton = new RoundedButton("오래된순");
		oldButton.setBounds(315, 165, 60, 30);
		oldButton.setBackground(new Color(91, 91, 91));
		oldButton.setForeground(Color.WHITE);
		oldButton.addMouseListener(commonMouseListener);
		add(oldButton);

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
		addButtonLabel = createScaledImageLabel("TeamProject/add_button.png", 70, 70);
		addButtonLabel.setBounds(300, 700, 70, 70);
		addButtonLabel.addMouseListener(commonMouseListener);
		addButtonLabel.setOpaque(true);
		addButtonLabel.setBackground(new Color(255, 255, 255, 0));
		addButtonLabel.setVisible(true);
		getLayeredPane().add(addButtonLabel, JLayeredPane.PALETTE_LAYER);

		// 🔹 votePanel 설정 수정
		votePanel = new JPanel();
		votePanel.setLayout(new GridLayout(0, 2, 2, 2)); // 2열 정렬
		votePanel.setBackground(Color.WHITE);

		// 🔹 스크롤 패널 추가 (23, 165, 357, 615 영역에 배치)
		// 이전 코드에서는 scrollPane이 이 부분 앞에 있을 수 있어, 여기에 잘못된 위치에서 접근되고 있었을 가능성이 있습니다.
		scrollPane = new JScrollPane(votePanel);
		scrollPane.setBounds(23, 200, 357, 580);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 스크롤바 숨기기
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 부드러운 스크롤 유지
		panel.add(scrollPane);


		// 🔹 더미 투표 데이터 추가
		for (int i = 1; i <= 16; i++) {
			addVote();
		}

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
			System.out.println(vb.getVote_image());
			byte[] imgBytes = vb.getVote_image();
			String imgNull = Arrays.toString(imgBytes);
			System.out.println(imgNull);
			JLabel imageLabel = new JLabel(); // JLabel을 먼저 생성
			if (imgBytes == null || imgBytes.length == 0) {
				imageLabel = createScaledImageLabel("TeamProject/photo_frame.png", 176, 150);
				imageLabel.setPreferredSize(new Dimension(176, 150));
				imageLabel.setMaximumSize(new Dimension(176, 150));
				imageLabel.setOpaque(false);
			} else {
				ImageIcon icon1 = new ImageIcon(imgBytes);
				Image img1 = icon1.getImage().getScaledInstance(176, 150, Image.SCALE_SMOOTH);
				imageLabel.setIcon(new ImageIcon(img1));
				imageLabel.setPreferredSize(new Dimension(176, 150));
				imageLabel.setMaximumSize(new Dimension(176, 150));
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
			
			JLabel voteLabel = createScaledImageLabel("TeamProject/vote.png", 40, 40);
			
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
			// 사용자가 좋아요를 눌렀는지 확인
			boolean isLiked = mgr.alrLikeVote(vb.getVote_id(), StaticData.user_id);
			
			// 좋아요 개수 라벨 생성
			likeCountLabel = new JLabel(String.valueOf(likeCount), SwingConstants.CENTER);
			
			likeCountLabel.setBounds(135, 114, 30, 20);
			likeCountLabel.setFont(new Font("Arial", Font.BOLD, 12));
			
					
			if (isLiked) {
				likeCountLabel.setForeground(Color.WHITE);
			}else {
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