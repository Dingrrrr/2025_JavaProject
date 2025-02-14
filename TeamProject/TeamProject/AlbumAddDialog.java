package TeamProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class AlbumAddDialog extends JFrame {
	private BufferedImage image;
	private JLabel closeLabel, addButtonLabel, grayFrameLabel;
	private JLabel AlbumTagLabel, AlbumWritelabel;
	private JTextField  AlbumTagTField;
	private JTextArea AlbumWriteTArea;
	private JButton SaveButton;
	private String tags, write;
	TPMgr mgr;
	AlbumBean bean;

	public AlbumAddDialog() {
		setTitle("프레임 설정");
		setSize(350, 620);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mgr = new TPMgr();
		bean = new AlbumBean();
		String img = "";


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
				} else if (source == addButtonLabel) {
					System.out.println("+아이콘 클릭됨");
					//사진 추가
				} else if (source == SaveButton) {
					System.out.println("저장 버튼클릭됨");
					tags = AlbumTagTField.getText().trim();
					write = AlbumWriteTArea.getText().trim();
					bean.setAlbum_tags(tags);
					bean.setAlbum_desc(write);
					bean.setAlbum_image(img);
					mgr.addAlbum(StaticData.pet_id, bean);
					dispose();
					new AlbumMainScreen();
				}
			}
		};

		
				// 앨범	태그 라벨
				AlbumTagLabel = new JLabel("태그");
				AlbumTagLabel.setBounds(15, 315, 48, 60);
				AlbumTagLabel.setForeground(Color.black);
				add(AlbumTagLabel);


				// 앨범 태그 텍스트 필드 추가
				AlbumTagTField = new JTextField();
				AlbumTagTField.setBounds(15, 355, 318, 40);
				AlbumTagTField.setText("");
				AlbumTagTField.setBorder(BorderFactory.createCompoundBorder(
				        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
				    ));
				add(AlbumTagTField);

		// 앨범 태그 라벨
		AlbumTagLabel = new JLabel("태그");
		AlbumTagLabel.setBounds(15, 315, 48, 60);
		AlbumTagLabel.setForeground(Color.black);
		add(AlbumTagLabel);

				// 앨범 설명 라벨
				AlbumWritelabel = new JLabel("설명");
				AlbumWritelabel.setBounds(15, 380, 48, 60);
				AlbumWritelabel.setForeground(Color.black);
				add(AlbumWritelabel);

		// 앨범 태그 텍스트 필드 추가
		AlbumTagTField = new JTextField();
		AlbumTagTField.setBounds(15, 355, 318, 40);
		AlbumTagTField.setText("");
		AlbumTagTField
				.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부
																														// 여백
																														// (위,
																														// 왼쪽,
																														// 아래,
																														// 오른쪽)
				));
		add(AlbumTagTField);

				// 앨범 설명 텍스트 필드 추가
				AlbumWriteTArea = new JTextArea();
				AlbumWriteTArea.setBounds(15, 420, 318, 130);
				AlbumWriteTArea.setText("");
				AlbumWriteTArea.setLineWrap(true);
				AlbumWriteTArea.setWrapStyleWord(true);
				AlbumWriteTArea.setBorder(BorderFactory.createCompoundBorder(
				        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
				    ));
				add(AlbumWriteTArea);
			

				
				JScrollPane scrollPane = new JScrollPane(AlbumWriteTArea);
				scrollPane.setBounds(15, 420, 318, 130); // 텍스트 영역 크기와 위치 설정
				add(scrollPane); // JScrollPane을 프레임에 추가
				
				// 저장 버튼
				SaveButton = new RoundedButton("저장");
				SaveButton.setBounds(115, 565, 100, 40);
				SaveButton.setBackground(new Color(91, 91, 91));
				SaveButton.setForeground(Color.WHITE);
				SaveButton.addMouseListener(commonMouseListener);
				add(SaveButton);

		// 앨범 설명 라벨
		AlbumWritelabel = new JLabel("설명");
		AlbumWritelabel.setBounds(15, 380, 48, 60);
		AlbumWritelabel.setForeground(Color.black);
		add(AlbumWritelabel);


		// 앨범 설명 텍스트 필드 추가
		AlbumWriteTArea = new JTextArea();
		AlbumWriteTArea.setBounds(15, 420, 318, 130);
		AlbumWriteTArea.setText("");
		AlbumWriteTArea.setLineWrap(true);
		AlbumWriteTArea.setWrapStyleWord(true);
		AlbumWriteTArea.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) ));
		add(AlbumWriteTArea);
		
		JScrollPane scrollPane = new JScrollPane(AlbumWriteTArea);
		scrollPane.setBounds(15, 420, 318, 130); // 텍스트 영역 크기와 위치 설정
		add(scrollPane); // JScrollPane을 프레임에 추가

		// 저장 버튼
		SaveButton = new RoundedButton("저장");
		SaveButton.setBounds(115, 565, 100, 40);
		SaveButton.setBackground(new Color(91, 91, 91));
		SaveButton.setForeground(Color.WHITE);
		SaveButton.addMouseListener(commonMouseListener);
		add(SaveButton);

		// 🔹 추가 버튼 (화면에 고정)
		addButtonLabel = createScaledImageLabel("TeamProject/add_button.png", 70, 70);
		addButtonLabel.setBounds(300, 700, 70, 70);
		addButtonLabel.addMouseListener(commonMouseListener);
		addButtonLabel.setOpaque(true);
		addButtonLabel.setBackground(new Color(255, 255, 255, 0));
		addButtonLabel.setVisible(true);
		getLayeredPane().add(addButtonLabel, JLayeredPane.PALETTE_LAYER);

		// 🔹 회색프레임
		grayFrameLabel = createScaledImageLabel("TeamProject/photo_frame.png", 280, 280);
		grayFrameLabel.setBounds(35, 35, 280, 280);
		grayFrameLabel.addMouseListener(commonMouseListener);
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

	public static void main(String[] args) {
		new AlbumAddDialog();
	}
}
