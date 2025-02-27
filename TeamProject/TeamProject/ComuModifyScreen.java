package TeamProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class ComuModifyScreen extends JFrame {
	private BufferedImage image;
	private JLabel titleLabel, contentLabel, closeLabel, grayFrameLabel, addButtonLabel;
	private JTextField titleField;
	private JTextArea contentArea;
	private JButton saveButton, delButton;
	private ComuModifyDialog cmd;
	private String title, content;
	ComuBean bean;
	TPMgr mgr;
	private byte[] imageBytes;

	public ComuModifyScreen(JFrame prePreFrame, WritenCommuScreen preFrame, ComuBean cb) {
		setTitle("프레임 설정");
		setSize(364, 630);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bean = new ComuBean();
		mgr = new TPMgr();
		imageBytes = cb.getComu_image();

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
					prePreFrame.setVisible(true);
					preFrame.setVisible(true);
				} else if (source == saveButton) {
					System.out.println("저장 버튼 클리됨");
					title = titleField.getText().trim();
					content = contentArea.getText().trim();
					bean.setComu_title(title);
					bean.setComu_content(content);
					bean.setComu_image(imageBytes);
					mgr.updComu(cb.getPost_id(), bean);

					// 화면 닫기
					dispose();
					preFrame.dispose();
					prePreFrame.dispose();

					// 새로 화면을 띄우기
					new CommuMainScreen();
				} else if (source == delButton) {
					System.out.println("삭제 버튼 클릭됨");
					mgr.delComu(cb.getPost_id());
					dispose();
					preFrame.dispose();
					prePreFrame.dispose();
					new CommuMainScreen();
				} else if (source == addButtonLabel) {
					System.out.println("추가 버튼 클릭됨");
					if (cmd == null) {
						cmd = new ComuModifyDialog(ComuModifyScreen.this);
						cmd.setLocation(getX() + 1, getY() + 455);
					} else {
						cmd.setLocation(getX() + 1, getY() + 455);
						cmd.setVisible(true);
					}

				}
			}
		};

		titleLabel = new JLabel("제목");
		titleLabel.setBounds(50, 23, 33, 30);
		titleLabel.setForeground(Color.BLACK);
		add(titleLabel);

		titleField = new JTextField(cb.getComu_title());
		titleField.setBounds(50, 51, 280, 32);
		titleField.setBackground(Color.WHITE);
		titleField.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부
																														// 여백
																														// (위,
																														// 왼쪽,
																														// 아래,
																														// 오른쪽)
		));
		add(titleField);

		// 🔹 추가 버튼
		addButtonLabel = createScaledImageLabel("TeamProject/add_button.png", 62, 62);
		addButtonLabel.setBounds(259, 299, 62, 62);
		addButtonLabel.addMouseListener(commonMouseListener);
		add(addButtonLabel);

		// 회색 프레임
		byte[] imgBytes = cb.getComu_image();
		grayFrameLabel = new JLabel();
		if (imgBytes == null || imgBytes.length == 0) {
			grayFrameLabel = createScaledImageLabel("TeamProject/photo_frame.png", 280, 280);
			grayFrameLabel.setBounds(50, 90, 280, 280);
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
			grayFrameLabel.setIcon(resizedIcon);
			grayFrameLabel.setPreferredSize(new Dimension(targetWidth, targetHeight)); // 크기 고정
			grayFrameLabel.setMaximumSize(new Dimension(targetWidth, targetHeight)); // 크기 고정
			grayFrameLabel.setBounds(50, 90, targetWidth, targetHeight);
		}
		add(grayFrameLabel);

		contentLabel = new JLabel("설명");
		contentLabel.setBounds(50, 370, 33, 30);
		contentLabel.setForeground(Color.BLACK);
		add(contentLabel);

		// 게시글 설명 텍스트 필드 추가
		contentArea = new JTextArea(cb.getComu_content());
		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true);
		contentArea.setBackground(Color.WHITE);
		add(contentArea);

		JScrollPane scrollPane = new JScrollPane(contentArea);
		scrollPane.setBounds(50, 400, 280, 135); // 텍스트 영역 크기와 위치 설정
		scrollPane.setBackground(Color.WHITE);
		// 스크롤 바 안 보이게 설정
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부여백(위,
																														// 왼쪽,
																														// 아래,
																														// 오른쪽)
		));
		add(scrollPane, BorderLayout.CENTER); // JScrollPane을 프레임에 추가

		// 저장 버튼
		saveButton = new RoundedButton("저장");
		saveButton.setBounds(80, 555, 100, 40);
		saveButton.setBackground(new Color(91, 91, 91));
		saveButton.setForeground(Color.WHITE);
		saveButton.addMouseListener(commonMouseListener);
		add(saveButton);

		// 삭제 버튼
		delButton = new RoundedButton("삭제");
		delButton.setBounds(185, 555, 100, 40);
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
					Image scaledImage = image.getScaledInstance(364, 630, Image.SCALE_SMOOTH);
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
