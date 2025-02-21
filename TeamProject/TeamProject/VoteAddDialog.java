package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.border.EmptyBorder;

public class VoteAddDialog extends JFrame {
	private BufferedImage image;
	private JLabel votetitleLabel, closeLabel, grayFrameLabel, addButtonLabel;
	private JButton addButton;
	TPMgr mgr;
	VoteBean bean;
	private VotePhotoAddDialog vad;
	private byte[] imageBytes; // 이미지 데이터를 저장할 멤버 변수

	public VoteAddDialog(VoteMainScreen preFrame) {
		setTitle("프레임 설정");
		setSize(350, 500);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mgr = new TPMgr();
		bean = new VoteBean();

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
				} else if (source == addButtonLabel) {
					System.out.println("+아이콘 클릭됨");
					if (vad == null) {
						vad = new VotePhotoAddDialog(preFrame, VoteAddDialog.this);
						vad.setLocation(getX() + 1, getY() + 340);
					} else {
						vad.setLocation(getX() + 1, getY() + 340);
						vad.setVisible(true);
					}
					setEnabled(false);
				} else if (source == addButton) {
					System.out.println("올리기 버튼 클릭됨");
					bean.setVote_image(imageBytes);
					mgr.addVote(StaticData.user_id, StaticData.pet_id, bean);
					dispose();
					preFrame.dispose();
					new VoteMainScreen();
				}

			}
		};

		// 투표 환영 라벨
		votetitleLabel = new JLabel("자랑하고 싶은 아이의 사진을 올려주세요!");
		votetitleLabel.setBounds(70, 50, 306, 30);
		votetitleLabel.setForeground(Color.BLACK);
		add(votetitleLabel);

		// 🔹 추가 버튼
		addButtonLabel = createScaledImageLabel("TeamProject/add_button.png", 62, 62);
		addButtonLabel.setBounds(245, 320, 62, 62);
		addButtonLabel.addMouseListener(commonMouseListener);
		add(addButtonLabel);

		// 🔹 회색프레임
		byte[] imgBytes = bean.getVote_image();
		if (imgBytes == null || imgBytes.length == 0) {
			grayFrameLabel = new JLabel();
			grayFrameLabel = createScaledImageLabel("TeamProject/photo_frame.png", 280, 280);
			grayFrameLabel.setBounds(38, 110, 280, 280);
			grayFrameLabel.addMouseListener(commonMouseListener);
			add(grayFrameLabel);
		} else {
			ImageIcon icon = new ImageIcon(imgBytes);
			Image img = icon.getImage().getScaledInstance(280, 280, Image.SCALE_SMOOTH);
			grayFrameLabel = new JLabel();
			grayFrameLabel.setIcon(new ImageIcon(img));
			grayFrameLabel.setBounds(38, 110, 280, 280);
			grayFrameLabel.addMouseListener(commonMouseListener);
			add(grayFrameLabel);
		}

		// 올리기 버튼
		addButton = new RoundedButton("올리기");
		addButton.setBounds(120, 420, 100, 40);
		addButton.setBackground(new Color(91, 91, 91));
		addButton.setForeground(Color.WHITE);
		addButton.addMouseListener(commonMouseListener);
		add(addButton);

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

		setVisible(true);
	}

	private JLabel createScaledImageLabel(String imagePath, int width, int height) {
		ImageIcon icon = new ImageIcon(imagePath);
		Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new JLabel(new ImageIcon(scaledImage));
	}

	public JLabel getImageLabel() {
		return grayFrameLabel;
	}

	// 이미지 바이트 배열을 설정하는 setter
	public void setImageBytes(byte[] imageBytes) {
		this.imageBytes = imageBytes;
	}

	// imageBytes를 얻는 메서드
	public byte[] getImageBytes() {
		return imageBytes;
	}

	public static void main(String[] args) {
		new LoginScreen();
	}
}
