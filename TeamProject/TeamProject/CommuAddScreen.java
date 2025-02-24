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

public class CommuAddScreen extends JFrame {
	private BufferedImage image;
	private JLabel closeLabel, addButtonLabel, grayFrameLabel;
	private JLabel CommuAddTagLabel, CommuAddWritelabel;
	private JTextField CommuAddTagTField;
	private JTextArea CommuAddWriteTArea;
	private JButton SaveButton;
	private String title, content;
	TPMgr mgr;
	ComuBean bean;
	private byte[] imageBytes;
	private ComuAddDialog cad;

	public CommuAddScreen(JFrame preFrame) {
		setTitle("프레임 설정");
		setSize(350, 620);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mgr = new TPMgr();
		bean = new ComuBean();
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
					if (cad == null) {
						cad = new ComuAddDialog(preFrame, CommuAddScreen.this);
						cad.setLocation(getX() + 1, getY() + 455);
					} else {
						cad.setLocation(getX() + 1, getY() + 455);
						cad.setVisible(true);
					}
					setEnabled(false);
					// 이미지 추가
				} else if (source == SaveButton) {
					System.out.println("저장 버튼클릭됨");
					title = CommuAddTagTField.getText().trim();
					content = CommuAddWriteTArea.getText().trim();
					bean.setComu_title(title);
					bean.setComu_content(content);
					bean.setComu_image(imageBytes);
					mgr.addComu(StaticData.user_id, bean);
					dispose();
					preFrame.dispose();
					new CommuMainScreen();
				}
			}
		};

		// 커뮤니티 게시글 추가화면 제목 라벨
		CommuAddTagLabel = new JLabel("제목");
		CommuAddTagLabel.setBounds(15, 315, 48, 60);
		CommuAddTagLabel.setForeground(Color.black);
		add(CommuAddTagLabel);

		// 커뮤니티 게시글 추가화면 제목 텍스트 필드 추가
		CommuAddTagTField = new JTextField();
		CommuAddTagTField.setBounds(15, 355, 318, 40);
		CommuAddTagTField.setText("");
		CommuAddTagTField.setBorder(BorderFactory.
				createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부
																														// 여백
																														// (위,
																														// 왼쪽,
																														// 아래,
																														// 오른쪽)
				));
		add(CommuAddTagTField);

		// 커뮤니티 게시글 추가화면 설명 라벨
		CommuAddWritelabel = new JLabel("설명");
		CommuAddWritelabel.setBounds(15, 380, 48, 60);
		CommuAddWritelabel.setForeground(Color.black);
		add(CommuAddWritelabel);

		// 게시글 설명 텍스트 필드 추가
		CommuAddWriteTArea = new JTextArea();
		CommuAddWriteTArea.setText("");
		CommuAddWriteTArea.setLineWrap(true);
		CommuAddWriteTArea.setWrapStyleWord(true);
		add(CommuAddWriteTArea);

		JScrollPane scrollPane = new JScrollPane(CommuAddWriteTArea);
		scrollPane.setBounds(15, 420, 318, 135); // 텍스트 영역 크기와 위치 설정
		scrollPane.setBackground(Color.WHITE);
		// 스크롤 바 안 보이게 설정
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부여백(위, 왼쪽, 아래, 오른쪽)
				));
		add(scrollPane, BorderLayout.CENTER); // JScrollPane을 프레임에 추가

		// 저장 버튼
		SaveButton = new RoundedButton("저장");
		SaveButton.setBounds(115, 565, 100, 40);
		SaveButton.setBackground(new Color(91, 91, 91));
		SaveButton.setForeground(Color.WHITE);
		SaveButton.addMouseListener(commonMouseListener);
		add(SaveButton);

		// 🔹 추가 버튼
		addButtonLabel = createScaledImageLabel("TeamProject/add_button.png", 62, 62);
		addButtonLabel.setBounds(245, 245, 62, 62);
		addButtonLabel.addMouseListener(commonMouseListener);
		add(addButtonLabel);

		// 회색 프레임
		byte[] imgBytes = bean.getComu_image();
		if (imgBytes == null || imgBytes.length == 0) {
			grayFrameLabel = new JLabel();
			grayFrameLabel = createScaledImageLabel("TeamProject/photo_frame.png", 280, 280);
			grayFrameLabel.setBounds(35, 35, 280, 280);
		} else {
			ImageIcon icon = new ImageIcon(imgBytes);
			Image img = icon.getImage().getScaledInstance(280, 280, Image.SCALE_SMOOTH);
			grayFrameLabel = new JLabel();
			grayFrameLabel.setIcon(new ImageIcon(img));
			grayFrameLabel.setBounds(35, 35, 280, 280);
		}
		add(grayFrameLabel);

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
