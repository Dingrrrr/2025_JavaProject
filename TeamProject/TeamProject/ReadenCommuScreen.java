package TeamProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Vector;

import javax.imageio.ImageIO;

public class ReadenCommuScreen extends JFrame {
	private BufferedImage image;
	private JLabel closeLabel, grayFrameLabel;
	private JLabel TitleLabel, ExplainLabel, PhotoLabel, commentLabel, useridLabel;
	private JTextArea ExplainTArea, CommentTArea, TitleArea;
	private JPanel CommuPanel;
	private JScrollPane scrollPane, scrollPane1; // 스크롤 패널
	private JButton SendButton;
	TPMgr mgr;
	ComuBean cb;
	Vector<CmtBean> vlist;
	JFrame preFrame;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd   HH:mm");

	public ReadenCommuScreen(CommuMainScreen preFrame, ComuBean cb) {
		setTitle("프레임 설정");
		setSize(350, 620);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mgr = new TPMgr();
		vlist = mgr.showCmt(cb.getPost_id());
		this.preFrame = preFrame;
		this.cb = cb;

		try {
			image = ImageIO.read(new File("TeamProject/pet_add_frame.png")); // 투명 PNG 불러오기
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 🔹 공통 마우스 클릭 이벤트 리스너
		MouseAdapter commonMouseListener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object source = e.getSource(); // 클릭된 컴포넌트 확인
				if (source == closeLabel) {
					System.out.println("닫기 버튼 클릭됨");
					dispose(); // 창 닫기
					preFrame.setEnabled(true);
					preFrame.setVisible(true);
				} else if (source == SendButton) {
					System.out.println("전송버튼 클릭됨");
					mgr.addCmt(cb.getPost_id(), StaticData.user_id, CommentTArea.getText().trim());
					dispose();
					preFrame.setVisible(true);
					new ReadenCommuScreen(preFrame, cb);
				}
			}
		};

		// 댓글 텍스트필드
		CommentTArea = new JTextArea();
		CommentTArea.setText("");
		CommentTArea.setBounds(15, 560, 290, 40);
		CommentTArea.setLineWrap(true);
		CommentTArea.setWrapStyleWord(true);
		CommentTArea.setBackground(Color.WHITE);

		JScrollPane scrollPane = new JScrollPane(CommentTArea);
		scrollPane.setBounds(15, 560, 290, 40); // 텍스트 영역 크기와 위치 설정
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBackground(Color.WHITE);
		scrollPane
				.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15))); // 내부
																														// 여백
																														// (위,
																														// 왼쪽,
																														// 아래,
																														// 오른쪽)
		add(scrollPane); // JScrollPane을 프레임에 추가

		// 전송 버튼
		SendButton = new RoundedButton("전송");
		SendButton.setBounds(305, 570, 40, 20);
		SendButton.setBackground(new Color(91, 91, 91));
		SendButton.setForeground(Color.WHITE);
		SendButton.addMouseListener(commonMouseListener);
		add(SendButton);

		// JPanel 추가
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					// 이미지 크기 조정 후 그리기
					Image scaledImage = image.getScaledInstance(350, 620, Image.SCALE_SMOOTH);
					g.drawImage(scaledImage, 0, 0, this);

					// y=520 위치에 가로로 회색 선 그리기
					g.setColor(Color.LIGHT_GRAY); // 선 색을 회색으로 설정
					g.drawLine(0, 550, 350, 550);
					g.drawLine(0, 320, 350, 320);
					g.drawLine(0, 34, 350, 34);
					Graphics2D g2 = (Graphics2D) g; // Graphics를 Graphics2D로 캐스팅
					g2.setStroke(new BasicStroke(6)); // 선 두께 6px 설정
				}
			}
		};

		panel.setLayout(null);
		panel.setOpaque(false); // 🔹 배경을 투명하게 설정
		add(panel);

		// 🔹 스크롤 가능한 게시글 패널 설정
		CommuPanel = new JPanel();
		CommuPanel.setLayout(new BoxLayout(CommuPanel, BoxLayout.Y_AXIS)); // 세로로 쌓이게 설정
		CommuPanel.setBackground(Color.WHITE);

		// 상단 컨텐츠를 담을 새로운 패널 생성
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setPreferredSize(new Dimension(340, 460)); // 적절한 높이 설정

		// 유저 아이디 라벨
		useridLabel = new JLabel(cb.getUser_id());
		useridLabel.setBounds(5, 8, 66, 30);
		useridLabel.setForeground(Color.BLACK);
		useridLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setEnabled(false);
				StaticData.msg_user_id = cb.getUser_id();
				new NoteSendScreen(ReadenCommuScreen.this);
			}
		});

		// 제목 라벨
		TitleLabel = new JLabel("제목");
		TitleLabel.setBounds(5, 35, 48, 30);
		TitleLabel.setForeground(Color.black);

		// 제목 텍스트 필드
		TitleArea = new JTextArea(cb.getComu_title());
		TitleArea.setBounds(5, 70, 330, 30);
		TitleArea.setEditable(false);
		TitleArea.setBackground(Color.WHITE);
		TitleArea.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(5, 10, 5, 10)));

		// 설명 라벨
		ExplainLabel = new JLabel("설명");
		ExplainLabel.setBounds(5, 115, 48, 30);
		ExplainLabel.setForeground(Color.black);

		// 설명 텍스트 필드
		ExplainTArea = new JTextArea(cb.getComu_content());
		ExplainTArea.setBounds(5, 155, 330, 100);
		ExplainTArea.setEditable(false);
		ExplainTArea.setBackground(Color.WHITE);
		ExplainTArea
				.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15)));

		// 커뮤 이미지
		System.out.println(cb.getComu_image());
		byte[] imgBytes1 = cb.getComu_image();
		String imgNull = Arrays.toString(imgBytes1);
		System.out.println(imgNull);
		if (imgBytes1 == null || imgBytes1.length == 0) {
			grayFrameLabel = new JLabel();
			grayFrameLabel = createScaledImageLabel("TeamProject/photo_frame.png", 300, 150);
			grayFrameLabel.setBounds(15, 270, 300, 150);
		} else {
			ImageIcon icon = new ImageIcon(imgBytes1);
			Image img = icon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
			grayFrameLabel = new JLabel();
			grayFrameLabel.setIcon(new ImageIcon(img));
			grayFrameLabel.setBounds(15, 270, 300, 150);
		}

		// 댓글 라벨
		commentLabel = new JLabel("댓글");
		commentLabel.setBounds(5, 425, 48, 30);
		commentLabel.setForeground(Color.black);

		// 컨텐츠 패널에 컴포넌트 추가
		contentPanel.add(useridLabel);
		contentPanel.add(TitleLabel);
		contentPanel.add(TitleArea);
		contentPanel.add(ExplainLabel);
		contentPanel.add(ExplainTArea);
		contentPanel.add(grayFrameLabel);
		contentPanel.add(commentLabel);

		// 컨텐츠 패널을 CommuPanel에 먼저 추가
		CommuPanel.add(contentPanel);

		// 🔹 스크롤 패널 추가 (0, 161 ~ 874, 782 영역에 배치)
		scrollPane1 = new JScrollPane(CommuPanel);
		scrollPane1.setBounds(5, 38, 340, 510);
		scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 스크롤바 숨기기
		scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.getVerticalScrollBar().setUnitIncrement(16); // 부드러운 스크롤 유지
		panel.add(scrollPane1);

		addCommu();

		// 🔹 닫기 버튼 이미지 추가
		closeLabel = createScaledImageLabel("TeamProject/delete_button.png", 28, 28);
		closeLabel.setBounds(315, 7, 28, 28);
		closeLabel.addMouseListener(commonMouseListener);
		panel.add(closeLabel); // 🔹 패널에 추가

		setVisible(true);
	}

	// 댓글창 추가 메소드

	private void addCommu() {

		for (CmtBean cmb : vlist) {
			// 1) 전체 항목을 감싸는 패널
			JPanel commuItemPanel = new JPanel();
			commuItemPanel.setPreferredSize(new Dimension(75, 99)); // 크기 지정
			commuItemPanel.setBackground(Color.WHITE);
			commuItemPanel.setBorder(new LineBorder(Color.black, 1)); // 외곽 테두리
			commuItemPanel.setLayout(new BorderLayout(10, 10)); // 여백 포함

			// 2) 상단 패널 (작성자 + 날짜)
			JPanel topPanel = new JPanel(new BorderLayout());
			topPanel.setBackground(Color.WHITE);
			topPanel.setPreferredSize(new Dimension(340, 20)); // 가로 340px, 세로 15px
			topPanel.setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); // 아래쪽만 테두리 1px

			JLabel userIdLabel = new JLabel(cmb.getUser_id());
			userIdLabel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0)); // 왼쪽에 3px 여백 추가

			JLabel dateLabel = new JLabel(sdf.format(cmb.getCmt_date()), SwingConstants.RIGHT);
			dateLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 3)); // 오른쪽에 3px 여백 추가
			topPanel.add(userIdLabel, BorderLayout.WEST);
			topPanel.add(dateLabel, BorderLayout.EAST);

			// 3) 구분선
			JSeparator separator = new JSeparator();
			separator.setForeground(Color.GRAY);

			// 4) 본문 패널 (이미지 + 텍스트)
			JPanel contentPanel = new JPanel(new BorderLayout(10, 0));
			contentPanel.setBackground(Color.WHITE);

			// 오른쪽 - 제목 & 내용
			JPanel textPanel = new JPanel();
			textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
			textPanel.setBackground(Color.WHITE);

			JLabel titleLabel = new JLabel(cmb.getCmt_content());
			textPanel.add(titleLabel);
			textPanel.add(Box.createVerticalStrut(10)); // 10px 간격
			contentPanel.add(textPanel, BorderLayout.CENTER);

			// 5) 전체 구성
			commuItemPanel.add(topPanel, BorderLayout.NORTH);
			commuItemPanel.add(contentPanel, BorderLayout.CENTER);
			CommuPanel.add(commuItemPanel);
		}

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
//		new ReadenCommuScreen();
	}
}