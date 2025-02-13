package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class AlbumChooseDialog extends JFrame {
	private BufferedImage image;
	private JLabel closeLabel;
	private JLabel chooseLabel;
	private JLabel albumLabel, diaryLabel;
	private JLabel albumintroLabel, diaryintroLabel;
	TPMgr mgr;
	
	public AlbumChooseDialog() {
		setTitle("프레임 설정");
		setSize(350, 350);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(0, 0, 0, 0)); // 투명 배경 설정
		mgr = new TPMgr();
		
		try {
			image = ImageIO.read(new File("TeamProject/pet_add_frame.png"));
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
						} else if (source == albumLabel) {
							System.out.println("앨범 선택 아이콘 클릭됨");
							dispose();
							if(mgr.isAlbum(StaticData.pet_id)) {	//이미 앨범이 있으면 실행
								new AlbumMainScreen();
							} else {	//DB에 앨범이 없으면 실행
								new AlbumScreen();
							}
						} else if (source == diaryLabel) {
							System.out.println("일기 선택 아이콘 클릭됨");
						}
					}
				};

				// 🔹 앨범 & 일기 선택 라벨
				chooseLabel = new JLabel("어떤 추억을 남기고 싶으신가요?");
				chooseLabel.setBounds(90, 22, 186, 40); // (x, y, 너비, 높이)
				chooseLabel.setForeground(Color.BLACK); // 텍스트 색 설정
				add(chooseLabel);

				// 🔹 앨범 선택 아이콘 추가
				albumLabel = createScaledImageLabel("TeamProject/album_icon.png", 150, 150);
				albumLabel.setBounds(18, 80, 150, 150);
				albumLabel.addMouseListener(commonMouseListener);
				add(albumLabel);

				// 🔹 일기 선택 아이콘 추가
				diaryLabel = createScaledImageLabel("TeamProject/diary_icon.png", 150, 150);
				diaryLabel.setBounds(183, 80, 150, 150);
				diaryLabel.addMouseListener(commonMouseListener);
				add(diaryLabel);
				
				// 🔹 앨범 라벨
				albumintroLabel = new JLabel("앨범");
				albumintroLabel.setBounds(79, 220, 34, 60);
				albumintroLabel.setForeground(Color.BLACK);
				add(albumintroLabel);
				
				// 🔹 다이어리 라벨
				diaryintroLabel = new JLabel("일기");
				diaryintroLabel.setBounds(244, 220, 34, 60);
				diaryintroLabel.setForeground(Color.BLACK);
				add(diaryintroLabel);

				// JPanel 추가
				JPanel panel = new JPanel() {
					@Override
					protected void paintComponent(Graphics g) {
						super.paintComponent(g);
						if (image != null) {
							// 이미지 크기 조정 후 그리기
							Image scaledImage = image.getScaledInstance(350, 300, Image.SCALE_SMOOTH);
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
		new AlbumChooseDialog();
	}
}
