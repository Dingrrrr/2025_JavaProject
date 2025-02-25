package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.net.ssl.SSLContext;

import java.awt.image.BufferedImage;
import javax.swing.border.EmptyBorder;

public class VoteModifyScreen extends JFrame {
	private BufferedImage image;
	private JLabel modifyLabel, closeLabel, grayFrameLabel, addButtonLabel, imageLabel;
	private JButton saveButton, delButton;
	private VotePhotoModifyDialog vmd;
	boolean flag = false;
	TPMgr mgr;
	VoteBean bean;
	private byte[] imageBytes; // 이미지 데이터를 저장할 멤버 변수

	public VoteModifyScreen(VoteBean vb, VoteMainScreen preFrame) {
		setTitle("프레임 설정");
		setSize(350, 470);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mgr = new TPMgr();
		bean = new VoteBean();
		imageBytes = vb.getVote_image();

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
				} else if (source == addButtonLabel && addButtonLabel.isEnabled()) {
					System.out.println("➕ 추가 버튼 클릭됨!");
					if (vmd == null) {
						vmd = new VotePhotoModifyDialog(preFrame, VoteModifyScreen.this);
						vmd.setLocation(getX() + 3, getY() + 335);
					} else {
						vmd.setLocation(getX() + 3, getY() + 335);
						vmd.setVisible(true);
					}
					setEnabled(false);
				} else if (source == modifyLabel) {
					System.out.println("수정 버튼 클릭됨");
					addButtonLabel.setEnabled(true);
					saveButton.setEnabled(true);
					delButton.setEnabled(true);
					flag = true;
				} else if (source == delButton && delButton.isEnabled()) {
					System.out.println("삭제 버튼 클릭됨");
					System.out.println(vb.getVote_id());
					if (mgr.delVote(vb.getVote_id())) {
						dispose();
						preFrame.dispose();
						new VoteMainScreen();
					}
				} else if (source == saveButton && saveButton.isEnabled()) {
					System.out.println("저장 버튼 클릭됨");
					if (flag) {
						vb.setVote_image(imageBytes);
						if (mgr.updVote(vb.getVote_id(), vb)) {
							addButtonLabel.setEnabled(false);
							saveButton.setEnabled(false);
							delButton.setEnabled(false);
							preFrame.dispose();
							dispose();
							new VoteMainScreen();
						}
					} /*
						 * dispose(); preFrame.dispose(); new VoteMainScreen();
						 */
				}

			}
		};

		// 🔹 추가 버튼
		addButtonLabel = createScaledImageLabel("TeamProject/add_button.png", 62, 62);
		addButtonLabel.setBounds(245, 265, 62, 62);
		addButtonLabel.addMouseListener(commonMouseListener);
		addButtonLabel.setEnabled(false);
		add(addButtonLabel);

		// 투표 이미지
		byte[] imgBytes = vb.getVote_image();
		imageLabel = new JLabel();
		if (imgBytes == null || imgBytes.length == 0) {
			imageLabel = createScaledImageLabel("TeamProject/photo_frame.png", 318, 318);
			imageLabel.setBounds(35, 55, 280, 280);
		} else {
			ImageIcon icon = new ImageIcon(imgBytes);
			Image img = icon.getImage();

			// 원본 이미지 크기
			int imgWidth = icon.getIconWidth();
			int imgHeight = icon.getIconHeight();

			// 타겟 크기 (280x280)
			int targetWidth = 280;
			int targetHeight = 280;

			// 비율 유지하며 축소
			double widthRatio = (double) targetWidth / imgWidth;
			double heightRatio = (double) targetHeight / imgHeight;
			double ratio = Math.min(widthRatio, heightRatio);
			int newWidth = (int) (imgWidth * ratio);
			int newHeight = (int) (imgHeight * ratio);

			// 새 BufferedImage 생성 (투명 배경)
			BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);

			// Graphics2D로 그리기 (안티앨리어싱 적용)
			Graphics2D g2d = resizedImage.createGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

			// 중앙 정렬 (여백 생김)
			int x = (targetWidth - newWidth) / 2;
			int y = (targetHeight - newHeight) / 2;
			g2d.drawImage(img, x, y, newWidth, newHeight, null);
			g2d.dispose();

			// 새 ImageIcon 설정
			ImageIcon resizedIcon = new ImageIcon(resizedImage);
			imageLabel.setIcon(resizedIcon);
			imageLabel.setPreferredSize(new Dimension(targetWidth, targetHeight)); // 크기 고정
			imageLabel.setMaximumSize(new Dimension(targetWidth, targetHeight)); // 크기 고정
			imageLabel.setBounds(35, 55, targetWidth, targetHeight);
		}
		add(imageLabel);

		// 저장 버튼
		saveButton = new RoundedButton("저장");
		saveButton.setBounds(65, 390, 100, 40);
		saveButton.setBackground(new Color(91, 91, 91));
		saveButton.setForeground(Color.WHITE);
		saveButton.addMouseListener(commonMouseListener);
		saveButton.setEnabled(false);
		add(saveButton);

		// 삭제 버튼
		delButton = new RoundedButton("삭제");
		delButton.setBounds(190, 390, 100, 40);
		delButton.setBackground(new Color(91, 91, 91));
		delButton.setForeground(Color.WHITE);
		delButton.addMouseListener(commonMouseListener);
		delButton.setEnabled(false);
		add(delButton);

		// JPanel 추가
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					// 이미지 크기 조정 후 그리기
					Image scaledImage = image.getScaledInstance(350, 470, Image.SCALE_SMOOTH);
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

		// 🔹 수정 버튼 이미지 추가
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

	public JLabel getImageLabel() {
		return imageLabel;
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
//		new VoteModifyScreen();
	}
}
