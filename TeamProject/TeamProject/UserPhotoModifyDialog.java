package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class UserPhotoModifyDialog extends JFrame {
	private JLabel addpicLabel, cancelLabel, deletepicLabel, grayFrameLabel;
	private JPanel p;
	private BufferedImage image;
	private JButton addpicButton, deletepicButton, cancelButton;
	private JLabel imageLabel;
	private UpdateUserScreen parentScreen; // UpdateUserScreen 객체를 참조

	public UserPhotoModifyDialog(UpdateUserScreen parentFrame) {
		this.parentScreen = parentFrame;
		setTitle("프레임 설정");
		setSize(358, 160);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// imageLabel 초기화
        imageLabel = new JLabel();

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
				if (source == addpicButton) {
					System.out.println("추가 버튼 클릭됨");
					selectImage(); // 이미지 선택 메소드 호출
				} else if (source == deletepicButton) {
					System.out.println("삭제 버튼 클릭됨");
				} else if (source == cancelButton) {
					System.out.println("취소 버튼 클릭됨");
					dispose();
				}

			}
		};

		// 사진 추가 버튼
		addpicButton = new JButton("사진 추가");
		addpicButton.setBounds(2, 2, 354, 53);
		addpicButton.setBackground(Color.white);
		addpicButton.setFocusable(false); // 포커스 끄기
		addpicButton.addMouseListener(commonMouseListener);
		add(addpicButton);

		// 사진 삭제 버튼
		deletepicButton = new JButton("사진 삭제");
		deletepicButton.setBounds(2, 55, 354, 53);
		deletepicButton.setBackground(Color.white);
		deletepicButton.setFocusable(false); // 포커스 끄기
		deletepicButton.addMouseListener(commonMouseListener);
		add(deletepicButton);

		// 취소 버튼
		cancelButton = new JButton("취소");
		cancelButton.setBounds(2, 105, 354, 53);
		cancelButton.setBackground(Color.white);
		cancelButton.setFocusable(false); // 포커스 끄기
		cancelButton.addMouseListener(commonMouseListener);
		add(cancelButton);

		// JPanel 추가
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					// 이미지 크기 조정 후 그리기
					Image scaledImage = image.getScaledInstance(358, 160, Image.SCALE_SMOOTH);
					g.drawImage(scaledImage, 0, 0, this);
				}
				// y=158 위치에 가로로 회색 선 그리기
				g.setColor(Color.LIGHT_GRAY); // 선 색을 회색으로 설정
				g.drawLine(3, 55, 360, 55);
				g.drawLine(3, 105, 360, 105);
			}
		};

		panel.setLayout(null);
		panel.setOpaque(false); // 🔹 배경을 투명하게 설정
		add(panel);

		setVisible(true);

		/*
		 * // 🔹 회색프레임 grayFrameLabel =
		 * createScaledImageLabel("TeamProject/photo_frame.png", 280, 280);
		 * grayFrameLabel.setBounds(35, 90, 280, 280); add(grayFrameLabel,
		 * BorderLayout.SOUTH);
		 */
	}

	private void selectImage() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(
				new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "png", "jpeg"));

		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile(); // 선택된 파일 가져오기

			// 선택된 파일로 이미지 아이콘 생성
			ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
			// 이미지를 JLabel에 맞게 크기 조정
			Image img = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);

			// 이미지 미리보기를 설정
			imageLabel.setIcon(new ImageIcon(img));
			imageLabel.setText(""); // 텍스트는 빈 값으로 설정

			// 부모 화면(UpdateUserScreen)에게 이미지 전달
			parentScreen.updateProfileImage(img); // 부모 화면에 이미지 전달
		}
	}

	public static void main(String[] args) {
	}
}
