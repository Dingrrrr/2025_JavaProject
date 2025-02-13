package TeamProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class DiaryResultDialog extends JFrame {
	private BufferedImage image;
	private JLabel closeLabel;
	private JLabel DiaryTitleLabel, DiaryWriteLabel, modifyLabel;
	private JTextField DiaryTitleField;
	private JTextArea DirayWriteArea;
	private JButton SaveButton, DelButton;
	private JScrollPane scrollPane;

	public DiaryResultDialog() {
		setTitle("프레임 설정");
		setSize(350, 500);
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
				} else if (source == SaveButton) {
					System.out.println("저장 버튼클릭됨");
				} else if (source == DelButton) {
					System.out.println("삭제 버튼 클릭됨");
				} else if (source == modifyLabel) {
					System.out.println("수정 버튼 클릭됨");
				}
			}
		};

		// 일기 제목 라벨
		DiaryTitleLabel = new JLabel("제목");
		DiaryTitleLabel.setBounds(15, 30, 33, 36);
		DiaryTitleLabel.setForeground(Color.black);
		add(DiaryTitleLabel);

		// 일기 제목 텍스트 필드 추가
		DiaryTitleField = new JTextField();
		DiaryTitleField.setBounds(15, 70, 318, 40);
		DiaryTitleField.setText("");
		DiaryTitleField
				.setBorder(BorderFactory.createCompoundBorder(
						// 내부 여백 (위, 왼쪽, 아래, 오른쪽)
						new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15)
				));
		add(DiaryTitleField);
		
		// 일기 내용 라벨
		DiaryWriteLabel = new JLabel("내용");
		DiaryWriteLabel.setBounds(15, 110, 48, 60);
		DiaryWriteLabel.setForeground(Color.black);
		add(DiaryWriteLabel);
		
		// 일기 내용 텍스트 필드 추가
		DirayWriteArea = new JTextArea();
		DirayWriteArea.setBounds(15, 160, 318, 250);
		DirayWriteArea.setText("");
		DirayWriteArea.setLineWrap(true);
		DirayWriteArea.setWrapStyleWord(true);
		DirayWriteArea.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(0), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
		
		scrollPane = new JScrollPane(DirayWriteArea);
		scrollPane.setBounds(15, 160, 318, 250); // 텍스트 영역 크기와 위치 설정
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 가로 스크롤
		scrollPane.setBorder(BorderFactory.createCompoundBorder(
					new RoundedBorder(0), new EmptyBorder(0, 0, 0, 0)
				));
		add(scrollPane); // JScrollPane을 프레임에 추가
		
		// 저장 버튼
		SaveButton = new RoundedButton("저장");
		SaveButton.setBounds(65, 430, 100, 40);
		SaveButton.setBackground(new Color(91, 91, 91));
		SaveButton.setForeground(Color.WHITE);
		SaveButton.addMouseListener(commonMouseListener);
		add(SaveButton);
		
		// 저장 버튼
		DelButton = new RoundedButton("삭제");
		DelButton.setBounds(185, 430, 100, 40);
		DelButton.setBackground(new Color(91, 91, 91));
		DelButton.setForeground(Color.WHITE);
		DelButton.addMouseListener(commonMouseListener);
		add(DelButton);

		// JPanel 추가
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					// 이미지 크기 조정 후 그리기
					Image scaledImage = image.getScaledInstance(350, 500, Image.SCALE_SMOOTH);
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
		
		//🔹 수정 버튼 이미지 추가
		modifyLabel = createScaledImageLabel("Teamproject/modify_icon.png", 28, 28);
		modifyLabel.setBounds(275, 7, 28, 28);
		modifyLabel.addMouseListener(commonMouseListener);
		panel.add(modifyLabel);

		setVisible(true);
	}

	private JLabel createScaledImageLabel(String imagePath, int width, int height) {
		ImageIcon icon = new ImageIcon(imagePath);
		Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new JLabel(new ImageIcon(scaledImage));
	}

	public static void main(String[] args) {
		new DiaryResultDialog();
	}
}
