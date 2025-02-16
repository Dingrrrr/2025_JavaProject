package TeamProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class AlbumResultDialog extends JFrame {
	private BufferedImage image;
	private JLabel closeLabel, addButtonLabel, grayFrameLabel, modifyLabel;
	private JLabel diaryTagLabel, diaryWritelabel;
	private JTextArea diaryWriteArea;
	private JTextField  diaryTagTField;
	private JButton SaveButton, delButton;
	private JScrollPane scrollpane;
	private String tag, write;
	TPMgr mgr;
	AlbumBean bean;
	private AlbumPhotoModifyDialog amd;
	
	public AlbumResultDialog(AlbumBean ab, AlbumMainScreen preFrame) {
		setTitle("프레임 설정");
		setSize(350, 620);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mgr = new TPMgr();
		bean = new AlbumBean();
		bean.setAlbum_image("");

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
					if(amd==null) {
						amd = new AlbumPhotoModifyDialog(AlbumResultDialog.this);
						amd.setLocation(getX()+1, getY()+455);
					}else {
						amd.setLocation(getX()+1, getY()+455);
						amd.setVisible(true);
					}
				} else if (source == SaveButton) {
					System.out.println("저장 버튼클릭됨");
					write = diaryWriteArea.getText().trim();
					tag = diaryTagTField.getText().trim();
					bean.setAlbum_desc(write);
					bean.setAlbum_tags(tag);
					mgr.updAlbum(StaticData.album_id, bean);
					diaryTagTField.setEnabled(false);
					diaryWriteArea.setEnabled(false);
					addButtonLabel.setEnabled(false);
					dispose();
					preFrame.dispose();
					new AlbumMainScreen();
				}else if (source == modifyLabel) {
					System.out.println("수정 버튼클릭됨");
					diaryTagTField.setEnabled(true);
					diaryWriteArea.setEnabled(true);
					addButtonLabel.setEnabled(true);
				} else if(source == delButton) {
					System.out.println("삭제 버튼 클릭됨");
					System.out.println(StaticData.album_id);
					if(mgr.delAlbum(StaticData.album_id)) {
						dispose();
						preFrame.dispose();
						if(mgr.isAlbum(StaticData.pet_id)) {
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
				diaryTagTField.setBorder(BorderFactory.createCompoundBorder(
				        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
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
				diaryWriteArea.setBorder(BorderFactory.createCompoundBorder(
						new RoundedBorder(0), new EmptyBorder(10, 15, 10, 15)
					));
				add(diaryWriteArea);
				diaryWriteArea.setEnabled(false);
				
				
				// 스크롤 기능
				scrollpane = new JScrollPane(diaryWriteArea);
				scrollpane.setBounds(15, 420, 318, 130);
				scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
				scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 가로 스크롤
				scrollpane.setBorder(BorderFactory.createCompoundBorder(
							new RoundedBorder(0), new EmptyBorder(0, 0, 0, 0)
						));
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
		
		//🔹 수정 버튼 이미지 추가
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

	public static void main(String[] args) {
		new LoginScreen();
	}
}
