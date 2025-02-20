package TeamProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class DiaryAddDialog extends JFrame {
	private BufferedImage image;
	private JLabel closeLabel;
	private JLabel DiaryTitleLabel, DiaryWritelabel;
	private JTextField  DiaryTitleTField;
	private JTextArea DiaryWriteTArea;
	private JButton SaveButton;
	private JScrollPane scrollPane;
	private String title, content;
	DiaryBean bean;
	TPMgr mgr;

	public DiaryAddDialog(JFrame preFrame) {
		setTitle("프레임 설정");
		setSize(350, 500);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bean = new DiaryBean();
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
				} else if (source == SaveButton) {
					System.out.println("저장 버튼클릭됨");
					title = DiaryTitleTField.getText().trim();
					content = DiaryWriteTArea.getText().trim();
					bean.setDiary_name(title);
					bean.setDiary_content(content);
					mgr.addDiary(StaticData.pet_id, bean);
					dispose();
					preFrame.dispose();
					new DiaryMainScreen();
				}
			}
		};
		
				// 일기 제목 라벨
				DiaryTitleLabel = new JLabel("제목");
				DiaryTitleLabel.setBounds(15, 20, 48, 60);
				DiaryTitleLabel.setForeground(Color.black);
				add(DiaryTitleLabel);

				// 일기 제목 텍스트 필드 추가
				DiaryTitleTField = new JTextField();
				DiaryTitleTField.setBounds(15, 70, 318, 40);
				DiaryTitleTField.setText("");
				DiaryTitleTField.setBorder(BorderFactory.createCompoundBorder(
				        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
				    ));
				add(DiaryTitleTField);

				// 일기 내용 라벨
				DiaryWritelabel = new JLabel("내용");
				DiaryWritelabel.setBounds(15, 110, 48, 60);
				DiaryWritelabel.setForeground(Color.black);
				add(DiaryWritelabel);

				// 일기 내용 텍스트 필드 추가
				DiaryWriteTArea = new JTextArea();
				DiaryWriteTArea.setText("");
				DiaryWriteTArea.setLineWrap(true);
				DiaryWriteTArea.setWrapStyleWord(true);
				add(DiaryWriteTArea);

				JScrollPane scrollPane = new JScrollPane(DiaryWriteTArea);
				scrollPane.setBounds(15, 160, 318, 194); // 텍스트 영역 크기와 위치 설정
				scrollPane.setBackground(Color.WHITE);
				// 스크롤 바 안 보이게 설정
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
				scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부여백(위, 왼쪽, 아래, 오른쪽)
						));
				add(scrollPane, BorderLayout.CENTER); // JScrollPane을 프레임에 추가
				
				// 저장 버튼
				SaveButton = new RoundedButton("저장");
				SaveButton.setBounds(115, 430, 100, 40);
				SaveButton.setBackground(new Color(91, 91, 91));
				SaveButton.setForeground(Color.WHITE);
				SaveButton.addMouseListener(commonMouseListener);
				add(SaveButton);
				
		// JPanel 추가
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					// 이미지 크기 조정 후 그리기
					Image scaledImage = image.getScaledInstance(350, 500, Image.SCALE_SMOOTH);
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
