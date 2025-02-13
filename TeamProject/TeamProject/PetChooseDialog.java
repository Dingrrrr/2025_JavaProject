package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class PetChooseDialog extends JFrame {
	private BufferedImage image;
	private JLabel closeLabel;
	private JLabel chooseLabel;
	private JLabel dogLabel, catLabel;
	

	public PetChooseDialog() {
		setTitle("프레임 설정");
		setSize(350, 254);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(0, 0, 0, 0)); // 투명 배경 설정
		

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
				} else if (source == dogLabel) {
					System.out.println("강아지 선택 아이콘 클릭됨");
					dispose();
					new PetAddScreen();
				} else if (source == catLabel) {
					System.out.println("고양이 선택 아이콘 클릭됨");
				}
			}
		};

		// 🔹 반려동물 진료기록 작성일 라벨
		chooseLabel = new JLabel("아이는 어떤 아이인가요?");
		chooseLabel.setBounds(115, 22, 186, 40); // (x, y, 너비, 높이)
		chooseLabel.setForeground(Color.BLACK); // 텍스트 색 설정
		add(chooseLabel);

		// 🔹 강아지 선택 아이콘 추가
		dogLabel = createScaledImageLabel("TeamProject/dog_icon.png", 150, 150);
		dogLabel.setBounds(18, 80, 150, 150);
		dogLabel.addMouseListener(commonMouseListener);
		add(dogLabel);

		// 🔹 고양이 선택 아이콘 추가
		catLabel = createScaledImageLabel("TeamProject/cat_icon.png", 150, 150);
		catLabel.setBounds(183, 80, 150, 150);
		catLabel.addMouseListener(commonMouseListener);
		add(catLabel);

		// JPanel 추가
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					// 이미지 크기 조정 후 그리기
					Image scaledImage = image.getScaledInstance(350, 254, Image.SCALE_SMOOTH);
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
	}
}
