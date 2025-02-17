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

public class ComuModifyScreen extends JFrame {
	private BufferedImage image;
	private JLabel titleLabel, contentLabel, closeLabel, grayFrameLabel, addButtonLabel;
	private JTextField titleField;
	private JTextArea contentArea;
	private JButton saveButton, delButton;

	private String title, content;
	ComuBean bean;
	TPMgr mgr;


	public ComuModifyScreen(WritenCommuScreen preFrame, ComuBean cb) {

	private ComuModifyDialog cmd;


		setTitle("프레임 설정");
		setSize(364, 630);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bean = new ComuBean();
		bean.setComu_image("");
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
						} else if (source == saveButton) {
							System.out.println("저장 버튼 클리됨");
							title = titleField.getText().trim();
							content = contentArea.getText().trim();
							bean.setComu_title(title);
							bean.setComu_content(content);
							mgr.updComu(cb.getPost_id(), bean);
							dispose();
							preFrame.updateTitleContent(title, content);
							preFrame.setEnabled(true);
						} else if (source == delButton) {
							System.out.println("삭제 버튼 클릭됨");
							mgr.delComu(cb.getPost_id());
							dispose();
							preFrame.dispose();
							new CommuMainScreen();
						} else if (source == addButtonLabel) {

							System.out.println("추가 버튼 클릭됨");
							setEnabled(false);
							new ComuModifyDialog();

							System.out.println("➕ 추가 버튼 클릭됨!");
							if (cmd==null) {
								cmd = new ComuModifyDialog(ComuModifyScreen.this);
								cmd.setLocation(getX()+1, getY()+455);
							} else {
								cmd.setLocation(getX()+1, getY()+455);
								cmd.setVisible(true);
							}

						}
					}
				};
				
				titleLabel = new JLabel("제목");
				titleLabel.setBounds(35, 23, 33, 30);
				titleLabel.setForeground(Color.BLACK);
				add(titleLabel);
				
				titleField = new JTextField(cb.getComu_title());
				titleField.setBounds(35, 51, 280, 32);
				titleField.setBorder(BorderFactory.createCompoundBorder(
					new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
				));
				add(titleField);
				
				// 🔹 추가 버튼
				addButtonLabel = createScaledImageLabel("TeamProject/add_button.png", 62, 62);
				addButtonLabel.setBounds(244, 299, 62, 62);
				addButtonLabel.addMouseListener(commonMouseListener);
				add(addButtonLabel);
				
				// 🔹 회색프레임
				grayFrameLabel = createScaledImageLabel("TeamProject/photo_frame.png", 280, 280);
				grayFrameLabel.setBounds(35, 90, 280, 280);
				grayFrameLabel.addMouseListener(commonMouseListener);
				add(grayFrameLabel);
				
				contentLabel = new JLabel("설명");
				contentLabel.setBounds(35, 370, 33, 30);
				contentLabel.setForeground(Color.BLACK);
				add(contentLabel);
				
				contentArea = new JTextArea(cb.getComu_content());
				contentArea.setBounds(35, 400, 280, 116);
				contentArea.setLineWrap(true);
				contentArea.setWrapStyleWord(true);
				contentArea.setBorder(BorderFactory.createCompoundBorder(
					new RoundedBorder(0), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
				));
				
				JScrollPane scrollPane = new JScrollPane(contentArea);
				scrollPane.setBounds(35, 400, 280, 116); // 텍스트 영역 크기와 위치 설정
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
				scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 가로 스크롤
				scrollPane.setBorder(BorderFactory.createCompoundBorder(
					new RoundedBorder(0), new EmptyBorder(0, 0, 0, 0)
				));
				add(scrollPane); // JScrollPane을 프레임에 추가
				
				// 저장 버튼
				saveButton = new RoundedButton("저장");
				saveButton.setBounds(65, 535, 100, 40);
				saveButton.setBackground(new Color(91, 91, 91));
				saveButton.setForeground(Color.WHITE);
				saveButton.addMouseListener(commonMouseListener);
				add(saveButton);
				
				// 삭제 버튼
				delButton = new RoundedButton("삭제");
				delButton.setBounds(190, 535, 100, 40);
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

	}
}
