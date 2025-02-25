package TeamProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class AlbumResultDialog extends JFrame {
	private BufferedImage image;
	private JLabel closeLabel, addButtonLabel, grayFrameLabel, modifyLabel;
	private JLabel diaryTagLabel, diaryWritelabel, imageLabel;
	private JTextArea diaryWriteArea;
	private JTextField diaryTagTField;
	private JButton SaveButton, delButton;
	private JScrollPane scrollpane;
	private String tag, write;
	boolean flag = false;
	TPMgr mgr;
	AlbumBean bean;
	private AlbumPhotoModifyDialog amd;
	private byte[] imageBytes; // 이미지 데이터를 저장할 멤버 변수

	public AlbumResultDialog(AlbumBean ab, AlbumMainScreen preFrame) {
		setTitle("프레임 설정");
		setSize(350, 620);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mgr = new TPMgr();
		bean = new AlbumBean();

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
					System.out.println("+아이콘 클릭됨");
					if (amd == null) {
						amd = new AlbumPhotoModifyDialog(AlbumResultDialog.this);
						amd.setLocation(getX() + 1, getY() + 455);
					} else {
						amd.setLocation(getX() + 1, getY() + 455);
						amd.setVisible(true);
					}
				} else if (source == SaveButton && SaveButton.isEnabled()) {
					System.out.println("저장 버튼클릭됨");
					if (flag) {
						ab.setAlbum_desc(diaryWriteArea.getText().trim());
						ab.setAlbum_tags(diaryTagTField.getText().trim());
						ab.setAlbum_image(imageBytes);
						if (mgr.updAlbum(StaticData.album_id, ab)) {
							diaryTagTField.setEnabled(false);
							diaryWriteArea.setEnabled(false);
							addButtonLabel.setEnabled(false);
							SaveButton.setEnabled(false);
							delButton.setEnabled(false);
						}
					}
					dispose();
					preFrame.dispose();
					new AlbumMainScreen();
				} else if (source == modifyLabel) {
					System.out.println("수정 버튼클릭됨");
					diaryTagTField.setEnabled(true);
					diaryWriteArea.setEnabled(true);
					addButtonLabel.setEnabled(true);
					SaveButton.setEnabled(true);
					delButton.setEnabled(true);
					flag = true;
				} else if (source == delButton && delButton.isEnabled()) {
					System.out.println("삭제 버튼 클릭됨");
					System.out.println(StaticData.album_id);
					if (mgr.delAlbum(StaticData.album_id)) {
						dispose();
						preFrame.dispose();
						if (mgr.isAlbum(StaticData.pet_id)) {
							new AlbumMainScreen();
						} else {
							new AlbumScreen();
						}
					}
				}
			}
		};

		// 앨범 태그 라벨
		diaryTagLabel = new JLabel("태그");
		diaryTagLabel.setBounds(15, 315, 48, 60);
		diaryTagLabel.setForeground(Color.black);
		add(diaryTagLabel);

		// 앨범 태그 텍스트 필드 추가
		diaryTagTField = new JTextField(ab.getAlbum_tags());
		diaryTagTField.setBounds(15, 355, 318, 40);
		diaryTagTField
				.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부
																														// 여백
																														// (위,
																														// 왼쪽,
																														// 아래,
																														// 오른쪽)
				));
		add(diaryTagTField);
		diaryTagTField.setEnabled(false);

		// 앨범 설명 라벨
		diaryWritelabel = new JLabel("설명");
		diaryWritelabel.setBounds(15, 380, 48, 60);
		diaryWritelabel.setForeground(Color.black);
		add(diaryWritelabel);

		// 앨범 설명 텍스트 필드 추가
		diaryWriteArea = new JTextArea(ab.getAlbum_desc());
		diaryWriteArea.setBounds(15, 420, 318, 130);
		diaryWriteArea.setLineWrap(true);
		diaryWriteArea.setWrapStyleWord(true);
		diaryWriteArea
				.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(0), new EmptyBorder(10, 15, 10, 15)));
		add(diaryWriteArea);
		diaryWriteArea.setEnabled(false);

		// 스크롤 기능
		scrollpane = new JScrollPane(diaryWriteArea);
		scrollpane.setBounds(15, 420, 318, 130);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 가로 스크롤
		scrollpane.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(0), new EmptyBorder(0, 0, 0, 0)));
		add(scrollpane, BorderLayout.CENTER);

		// 저장 버튼
		SaveButton = new RoundedButton("저장");
		SaveButton.setBounds(55, 560, 100, 40);
		SaveButton.setBackground(new Color(91, 91, 91));
		SaveButton.setForeground(Color.WHITE);
		SaveButton.addMouseListener(commonMouseListener);
		add(SaveButton);

		// 수정
		delButton = new RoundedButton("삭제");
		delButton.setBounds(200, 560, 100, 40);
		delButton.setBackground(new Color(91, 91, 91));
		delButton.setForeground(Color.WHITE);
		delButton.addMouseListener(commonMouseListener);
		add(delButton);

		// 🔹 추가 버튼
		addButtonLabel = createScaledImageLabel("TeamProject/add_button.png", 62, 62);
		addButtonLabel.setBounds(245, 245, 62, 62);
		addButtonLabel.addMouseListener(commonMouseListener);
		add(addButtonLabel);
		addButtonLabel.setEnabled(false);

		// 앨범 이미지
		byte[] imgBytes = ab.getAlbum_image();
		imageBytes = ab.getAlbum_image();
		JLabel imageLabel = new JLabel(); // JLabel을 먼저 생성
		if (imgBytes == null || imgBytes.length == 0) {
			imageLabel = createScaledImageLabel("TeamProject/photo_frame.png", 280, 280);
			imageLabel.setBounds(35, 35, 280, 280);
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
			imageLabel.setBounds(35, 35, targetWidth, targetHeight);
		}
		add(imageLabel);

		diaryTagTField.setEnabled(false);
		diaryWriteArea.setEnabled(false);
		addButtonLabel.setEnabled(false);
		SaveButton.setEnabled(false);
		delButton.setEnabled(false);

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

		// 🔹 수정 버튼 이미지 추가
		modifyLabel = createScaledImageLabel("Teamproject/modify_icon.png", 28, 28);
		modifyLabel.setBounds(275, 7, 28, 28);
		modifyLabel.addMouseListener(commonMouseListener);
		panel.add(modifyLabel);

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
		new LoginScreen();
	}
}
