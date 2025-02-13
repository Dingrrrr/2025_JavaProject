package TeamProject;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class AlbumModifyDialog extends JFrame{
	private JLabel addpicLabel, cancelLabel, deletepicLabel, grayFrameLabel;
	private JPanel p;
	private BufferedImage image;
	
	public AlbumModifyDialog() {
		setTitle("프레임 설정");
		setSize(364, 166);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			image = ImageIO.read(new File("TeamProject/pet_add_frame.png")); // 투명 PNG 불러오기
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//사진 추가 라벨
		addpicLabel = new JLabel("사진 추가");
		addpicLabel.setBounds(155, 5, 72, 50);
		addpicLabel.setForeground(Color.BLACK);
		add(addpicLabel);
		
		//사진 삭제 라벨
		deletepicLabel = new JLabel("사진 삭제");
		deletepicLabel.setBounds(155, 55, 72, 50);
		deletepicLabel.setForeground(Color.BLACK);
		add(deletepicLabel);
		
		//취소 라벨
		cancelLabel = new JLabel("취소");
		cancelLabel.setBounds(170, 105, 72, 50);
		cancelLabel.setForeground(Color.BLACK);
		add(cancelLabel);
		
		// JPanel 추가
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					// 이미지 크기 조정 후 그리기
					Image scaledImage = image.getScaledInstance(364, 166, Image.SCALE_SMOOTH);
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
	
	
	public static void main(String[] args) {
		new AlbumModifyDialog();
	}
}
