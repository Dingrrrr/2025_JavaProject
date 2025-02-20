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

public class VoteScreenDialog extends JFrame {
	private BufferedImage image;
	private JLabel closeLabel, heartLabel, grayFrameLabel;
	private JLabel albumphoto;
	TPMgr mgr;
	private byte[] imageBytes; // 이미지 데이터를 저장할 멤버 변수

	public VoteScreenDialog(VoteMainScreen preFrame, VoteBean vb) {
		setTitle("프레임 설정");
		setSize(350, 350);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mgr = new TPMgr();

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
				} else if (source == heartLabel) {
					if (!mgr.alrLikeVote(vb.getVote_id(), StaticData.user_id)) { // 누르지 않았다면
						System.out.println("하트 클릭됨");
						mgr.likeVote(vb.getVote_id(), StaticData.user_id);
						MsgBean bean = new MsgBean();
						bean.setMsg_title("새로운 좋아요!");
						bean.setReceiver_id(vb.getUser_id());
						bean.setMsg_content(mgr.showOneUserName(StaticData.user_id) + "님이 당신의 투표에 좋아요를 눌렀습니다!");
						mgr.sendMsg(StaticData.user_id, bean);
						preFrame.addVote();
						dispose();
						preFrame.setVisible(true);
						new VoteScreenDialog(preFrame, vb);
					}else {
						System.out.println("하트 클릭됨");
						mgr.cancelVote(vb.getVote_id(), StaticData.user_id);
						preFrame.addVote();
						dispose();
						preFrame.setVisible(true);
						new VoteScreenDialog(preFrame, vb);
					}
				}
			}
		};

		// 🔹 하트 버튼 이미지 추가
		if (mgr.alrLikeVote(vb.getVote_id(), StaticData.user_id)) { // 이미 눌렀다면
			heartLabel = createScaledImageLabel("TeamProject/vote_complete.png", 70, 70);
			heartLabel.setBounds(235, 240, 70, 70);
			heartLabel.setOpaque(false);
			heartLabel.addMouseListener(commonMouseListener);
		} else {
			heartLabel = createScaledImageLabel("TeamProject/vote.png", 70, 70);
			heartLabel.setBounds(235, 240, 70, 70);
			heartLabel.addMouseListener(commonMouseListener);
		}
		add(heartLabel); // 🔹 패널에 추가

		// 투표 이미지
		byte[] imgBytes = vb.getVote_image();
		imageBytes = vb.getVote_image();
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
					Image scaledImage = image.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
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
		new LoginScreen();
	}
}
