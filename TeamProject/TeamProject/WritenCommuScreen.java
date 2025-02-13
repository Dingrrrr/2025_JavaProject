<<<<<<< HEAD
package TeamProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class WritenCommuScreen extends JFrame {
	private BufferedImage image;
	private JLabel closeLabel, modifyLabel,  grayFrameLabel;
	private JLabel TitleLabel,ExplainLabel, PhotoLabel, CommentLabel;
	private JTextField  TitleTField;
	private JTextArea ExplainTArea, CommentTArea;
	private JPanel CommuPanel;
	private JScrollPane scrollPane, scrollPane1; // 스크롤 패널
	private JButton SendButton;

	public WritenCommuScreen() {
		setTitle("프레임 설정");
		setSize(350, 620);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
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
				} else if (source == modifyLabel) {
					System.out.println("수정버튼 클릭됨");
				} else if (source == SendButton) {
					System.out.println("전송버튼 클릭됨");
				}
			}
		};		
				
				//댓글 라벨
				CommentLabel = new JLabel("댓글");
				CommentLabel.setBounds(15, 530, 48, 60);
				CommentLabel.setForeground(Color.black);
				add(CommentLabel);
				
				//댓글 텍스트필드
				CommentTArea = new JTextArea();
				CommentTArea.setBounds(15, 570, 290, 40);
				CommentTArea.setText("");
				CommentTArea.setLineWrap(true);
				CommentTArea.setWrapStyleWord(true);
				CommentTArea.setBorder(BorderFactory.createCompoundBorder(
				        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
				    ));
				add(CommentTArea);
			
				JScrollPane scrollPane = new JScrollPane(CommentTArea);
				scrollPane.setBounds(15, 570, 290, 40); // 텍스트 영역 크기와 위치 설정
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
				add(scrollPane); // JScrollPane을 프레임에 추가
				
				// 전송 버튼
				SendButton = new RoundedButton("전송");
				SendButton.setBounds(305, 575, 40, 20);
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
		CommuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // 아이템이 정렬되도록 설정
		CommuPanel.setBackground(Color.WHITE);

				// 🔹 스크롤 패널 추가 (0, 161 ~ 874, 782 영역에 배치)
		scrollPane1 = new JScrollPane(CommuPanel);
		scrollPane1.setBounds(5,38 ,340 ,510 );
		//scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 스크롤바 숨기기
		scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.getVerticalScrollBar().setUnitIncrement(16); // 부드러운 스크롤 유지
				panel.add(scrollPane1);
				
		// 🔹 닫기 버튼 이미지 추가
		closeLabel = createScaledImageLabel("TeamProject/delete_button.png", 28, 28);
		closeLabel.setBounds(315, 7, 28, 28);
		closeLabel.addMouseListener(commonMouseListener);
		panel.add(closeLabel); // 🔹 패널에 추가
		
		// 🔹 수정 버튼 이미지 추가
		modifyLabel = createScaledImageLabel("TeamProject/modify_icon.png", 30, 30);
		modifyLabel.setBounds(280, 7, 30, 30);
		modifyLabel.addMouseListener(commonMouseListener);
		panel.add(modifyLabel); // 🔹 패널에 추가

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
		new WritenCommuScreen();
	}
=======
package TeamProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class WritenCommuScreen extends JFrame {
	private BufferedImage image;
	private JLabel closeLabel, modifyLabel, grayFrameLabel;
	private JLabel TitleLabel, ExplainLabel, PhotoLabel, CommentLabel;
	private JTextField TitleTField;
	private JTextArea ExplainTArea, CommentTArea;
	private JPanel CommuPanel, commentPanel;
	private JScrollPane scrollPane, scrollPane1; // 스크롤 패널
	private JButton SendButton;

	public WritenCommuScreen() {
		setTitle("프레임 설정");
		setSize(350, 620);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
				} else if (source == modifyLabel) {
					System.out.println("수정버튼 클릭됨");
				} else if (source == SendButton) {
					System.out.println("전송버튼 클릭됨");
				}
			}
		};

		// 사진 라벨
		PhotoLabel = new JLabel("사진");
		PhotoLabel.setBounds(160, 100, 300, 300);
		PhotoLabel.setForeground(Color.black);
		add(PhotoLabel);

		// 제목 라벨
		TitleLabel = new JLabel("제목");
		TitleLabel.setBounds(15, 15, 48, 60);
		TitleLabel.setForeground(Color.black);
		add(TitleLabel);

		// 제목 텍스트 필드
		TitleTField = new JTextField();
		TitleTField.setBounds(15, 55, 318, 20);
		TitleTField.setText("");
		TitleTField.setEditable(false);
		TitleTField.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), 
				new EmptyBorder(0, 0, 0, 0) //내부여백(위, 왼쪽, 아래, 오른쪽)
		));
		add(TitleTField);

		// 설명 라벨
		ExplainLabel = new JLabel("설명");
		ExplainLabel.setBounds(15, 70, 48, 60);
		ExplainLabel.setForeground(Color.black);
		add(ExplainLabel);

		// 설명 텍스트 필드
		ExplainTArea = new JTextArea();
		ExplainTArea.setBounds(15, 110, 318, 70);
		ExplainTArea.setText("");
		ExplainTArea.setEditable(false);
		ExplainTArea.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		));
		add(ExplainTArea);

		// 댓글 라벨
		CommentLabel = new JLabel("댓글");
		CommentLabel.setBounds(15, 530, 48, 60);
		CommentLabel.setForeground(Color.black);
		add(CommentLabel);

		// 댓글 텍스트필드
		CommentTArea = new JTextArea();
		CommentTArea.setBounds(15, 570, 290, 40);
		CommentTArea.setText("");
		CommentTArea.setLineWrap(true);
		CommentTArea.setWrapStyleWord(true);
		CommentTArea.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), 
				new EmptyBorder(0, 0, 0, 0) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		));

		scrollPane = new JScrollPane(CommentTArea);
		scrollPane.setBounds(15, 570, 290, 40); // 텍스트 영역 크기와 위치 설정
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		add(scrollPane); // JScrollPane을 프레임에 추가

		// 전송 버튼
		SendButton = new RoundedButton("전송");
		SendButton.setBounds(305, 575, 40, 20);
		SendButton.setBackground(new Color(91, 91, 91));
		SendButton.setForeground(Color.WHITE);
		SendButton.addMouseListener(commonMouseListener);
		add(SendButton);

		// 🔹 회색프레임
		grayFrameLabel = createScaledImageLabel("TeamProject/photo_frame.png", 200, 100);
		grayFrameLabel.setBounds(75, 200, 200, 100);
		grayFrameLabel.addMouseListener(commonMouseListener);
		add(grayFrameLabel);

		// JPanel 추가
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					// 이미지 크기 조정 후 그리기
					Image scaledImage = image.getScaledInstance(350, 620, Image.SCALE_SMOOTH);
					g.drawImage(scaledImage, 0, 0, this);
				}
			}
		};
		
		// 댓글 표시 패널 (스크롤 가능)
        commentPanel = new JPanel();
        commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS)); // 수직 정렬

        JScrollPane scrollPane = new JScrollPane(commentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		// 🔹 CommuPanel (스크롤 패널 내부의 메인 패널)
		CommuPanel = new JPanel();
		CommuPanel.setLayout(new BoxLayout(CommuPanel, BoxLayout.Y_AXIS)); // 세로 정렬
		CommuPanel.setBackground(Color.WHITE);
		CommuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// 🔹 스크롤 패널 추가 (0, 161 ~ 874, 782 영역에 배치)
		scrollPane1 = new JScrollPane(CommuPanel);
		scrollPane1.setBounds(5, 38, 340, 510);
		scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 스크롤바 숨기기
		scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.getVerticalScrollBar().setUnitIncrement(16); // 부드러운 스크롤 유지
		panel.add(scrollPane1);

		panel.setLayout(null);
		panel.setOpaque(false); // 🔹 배경을 투명하게 설정
		add(panel);

		// 🔹 닫기 버튼 이미지 추가
		closeLabel = createScaledImageLabel("TeamProject/delete_button.png", 28, 28);
		closeLabel.setBounds(315, 7, 28, 28);
		closeLabel.addMouseListener(commonMouseListener);
		panel.add(closeLabel); // 🔹 패널에 추가

		// 🔹 수정 버튼 이미지 추가
		modifyLabel = createScaledImageLabel("TeamProject/modify_icon.png", 30, 30);
		modifyLabel.setBounds(280, 7, 30, 30);
		modifyLabel.addMouseListener(commonMouseListener);
		panel.add(modifyLabel); // 🔹 패널에 추가

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
		new WritenCommuScreen();
	}
>>>>>>> branch 'main' of https://github.com/min9yu12/mingyu_.git
}