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
		setSize(364, 496);
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
					System.out.println("➕ 추가 버튼 클릭됨!");
					if (vmd == null) {
						vmd = new VotePhotoModifyDialog(VoteModifyScreen.this);
						vmd.setLocation(getX() + 3, getY() + 335);
					} else {
						vmd.setLocation(getX() + 3, getY() + 335);
						vmd.setVisible(true);
					}
				} else if (source == modifyLabel) {
					System.out.println("수정 버튼 클릭됨");
					addButtonLabel.setEnabled(true);
					saveButton.setEnabled(true);
					delButton.setEnabled(true);
					flag = true;
				} else if (source == delButton) {
					System.out.println("삭제 버튼 클릭됨");
					System.out.println(vb.getVote_id());
					if(mgr.delVote(vb.getVote_id())) {
						dispose();
						preFrame.dispose();
						new VoteMainScreen();
					}
				} else if (source == saveButton) {
					System.out.println("저장 버튼 클릭됨");
					if (flag) {
						vb.setVote_image(imageBytes);
						if (mgr.updVote(vb.getVote_id(), vb)) {
							addButtonLabel.setEnabled(false);
							saveButton.setEnabled(false);
							delButton.setEnabled(false);
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
		imageBytes = vb.getVote_image();
		if (imgBytes == null || imgBytes.length == 0) {
			imageLabel = new JLabel();
			imageLabel = createScaledImageLabel("TeamProject/photo_frame.png", 318, 318);
			imageLabel.setBounds(23, 45, 318, 318);
		} else {
			imageBytes = vb.getVote_image();
			ImageIcon icon = new ImageIcon(imgBytes);
			Image img = icon.getImage().getScaledInstance(318, 318, Image.SCALE_SMOOTH);
			imageLabel = new JLabel();
			imageLabel.setIcon(new ImageIcon(img));
			imageLabel.setBounds(23, 45, 318, 318);
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
					Image scaledImage = image.getScaledInstance(364, 496, Image.SCALE_SMOOTH);
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

		// 🔹 수정 버튼 이미지 추가
		modifyLabel = createScaledImageLabel("Teamproject/modify_icon.png", 28, 28);
		modifyLabel.setBounds(290, 7, 28, 28);
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
