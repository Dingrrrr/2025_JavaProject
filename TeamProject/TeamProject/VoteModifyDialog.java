package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.border.EmptyBorder;

public class VoteModifyDialog extends JFrame {
	private BufferedImage image;
	private JLabel modifyLabel, closeLabel, grayFrameLabel, addButtonLabel;
	private JButton saveButton, delButton;

	public VoteModifyDialog() {
		setTitle("프레임 설정");
		setSize(364, 496);
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
						} else  {

						}

					}
				};
				
				// 🔹 추가 버튼
				addButtonLabel = createScaledImageLabel("TeamProject/add_button.png", 62, 62);
				addButtonLabel.setBounds(245, 265, 62, 62);
				addButtonLabel.addMouseListener(commonMouseListener);
				add(addButtonLabel);
				
				// 🔹 회색프레임
				grayFrameLabel = createScaledImageLabel("TeamProject/photo_frame.png", 280, 280);
				grayFrameLabel.setBounds(23, 45, 318, 318);
				grayFrameLabel.addMouseListener(commonMouseListener);
				add(grayFrameLabel);
		
				// 저장 버튼
				saveButton = new RoundedButton("저장");
				saveButton.setBounds(65, 390, 100, 40);
				saveButton.setBackground(new Color(91, 91, 91));
				saveButton.setForeground(Color.WHITE);
				saveButton.addMouseListener(commonMouseListener);
				add(saveButton);
				
				// 삭제 버튼
				delButton = new RoundedButton("삭제");
				delButton.setBounds(190, 390, 100, 40);
				delButton.setBackground(new Color(91, 91, 91));
				delButton.setForeground(Color.WHITE);
				delButton.addMouseListener(commonMouseListener);
				add(delButton);
				
		
		// JPanel 추가
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					// 이미지 크기 조정 후 그리기
					Image scaledImage = image.getScaledInstance(364, 496, Image.SCALE_SMOOTH);
					g.drawImage(scaledImage, 0, 0, this);
				}
			}
		};

		panel.setLayout(null);
		panel.setOpaque(false); // 🔹 배경을 투명하게 설정
		add(panel);

		// 🔹 닫기 버튼 이미지 추가
		closeLabel = createScaledImageLabel("TeamProject/delete_button.png", 28, 28);
		closeLabel.setBounds(330, 7, 28, 28);
		closeLabel.addMouseListener(commonMouseListener);
		panel.add(closeLabel); // 🔹 패널에 추가
		
		//🔹 수정 버튼 이미지 추가
		modifyLabel = createScaledImageLabel("Teamproject/modify_icon.png", 28, 28);
		modifyLabel.setBounds(290, 7, 28, 28);
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
		new VoteModifyDialog();
	}
}
