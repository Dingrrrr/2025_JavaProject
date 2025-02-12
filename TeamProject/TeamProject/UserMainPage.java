package TeamProject;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class UserMainPage extends MainFrame{
	
	private BufferedImage image;
	
	public UserMainPage() {
		setTitle("유저 홈화면");
		try {
			image = ImageIO.read(new File("img/free-icon-notifications-545271.png")); // 투명 PNG 불러오기
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					// 원하는 크기로 조정 (예: 300x600)
					Image scaledImage = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
					g.drawImage(scaledImage, 296, 13, this);
				}
			}
		};

		panel.setOpaque(false);
		panel.setLayout(null);
		add(panel);
		
		validate();
	}
	
	public static void main(String[] args) {
		new UserMainPage();
	}
}
