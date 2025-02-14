package TeamProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class NoteSendScreen extends JFrame {
	private BufferedImage image;
	private JLabel closeLabel;
	private JLabel SendIdLabel,TitleLabel, DescriptionLabel;
	private JTextField  SendIdTField;
	private JTextArea TitleTArea, DescriptionTArea;
	private JButton SendButton;

	public NoteSendScreen() {
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
				} else if (source == SendButton) {
					System.out.println("전송 버튼클릭됨");
				}
			}
		};
		
				// 전송할 아이디 라벨
		SendIdLabel = new JLabel("전송할 아이디");
		SendIdLabel.setBounds(15, 20, 100, 60);
		SendIdLabel.setForeground(Color.black);
				add(SendIdLabel);

				// 전송할 아이디 필드 추가
				SendIdTField = new JTextField();
				SendIdTField.setBounds(15, 60, 318, 40);
				SendIdTField.setText("");
				SendIdTField.setBorder(BorderFactory.createCompoundBorder(
				        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
				    ));
				add(SendIdTField);

				// 제목 라벨
				TitleLabel = new JLabel("제목");
				TitleLabel.setBounds(15, 90, 48, 60);
				TitleLabel.setForeground(Color.black);
				add(TitleLabel);

				// 제목 필드 추가
				TitleTArea = new JTextArea();
				TitleTArea.setBounds(15, 130, 318, 40);
				TitleTArea.setText("");
				TitleTArea.setBorder(BorderFactory.createCompoundBorder(
				        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
				    ));
				add(TitleTArea);
				
				//설명 라벨
				DescriptionLabel = new JLabel("설명");
				DescriptionLabel.setBounds(15, 160, 100, 60);
				DescriptionLabel.setForeground(Color.black);
				add(DescriptionLabel);
				
				//설명 필드 추가
				DescriptionTArea = new JTextArea();
				DescriptionTArea.setBounds(15, 200, 318, 350);
				DescriptionTArea.setText("");
				DescriptionTArea.setLineWrap(true);
				DescriptionTArea.setWrapStyleWord(true);
				DescriptionTArea.setBorder(BorderFactory.createCompoundBorder(
				        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
				    ));
				add(DescriptionTArea);
				
				//설명 필드 스크롤
				JScrollPane scrollPane = new JScrollPane(DescriptionTArea);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 스크롤바 숨기기
				scrollPane.setBounds(15, 200, 318, 350); // 텍스트 영역 크기와 위치 설정
				add(scrollPane); // JScrollPane을 프레임에 추가
				
				// 저장 버튼
				SendButton = new RoundedButton("전송");
				SendButton.setBounds(115, 565, 100, 40);
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
				}
			}
		};

		panel.setLayout(null);
		panel.setOpaque(false); // 🔹 배경을 투명하게 설정
		add(panel);

		// 🔹 닫기 버튼 이미지 추가
		closeLabel = createScaledImageLabel("TeamProject/delete_button.png", 28, 28);
		closeLabel.setBounds(315, 7, 28, 28);
		closeLabel.addMouseListener(commonMouseListener);
		panel.add(closeLabel); // 🔹 패널에 추가

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
		new NoteSendScreen();
	}
}