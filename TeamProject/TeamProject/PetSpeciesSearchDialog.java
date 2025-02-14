package TeamProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class PetSpeciesSearchDialog extends JFrame {
	private BufferedImage image;
	private JLabel closeLabel;
	private JLabel searchLabel;
	private JButton searchButton;
	private JTextField searchTextField;
	private JTextArea searchResultTextArea;

	public PetSpeciesSearchDialog() {
		setTitle("프레임 설정");
		setSize(350, 450);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(0, 0, 0, 0)); // 투명 배경 설정

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
				} else if (source == searchButton) {
					System.out.println("검색 버튼 클릭됨");
				} 
			}
		};

		// 품종 검색 안내 라벨
		searchLabel = new JLabel("아이의 품종을 검색해주세요!");
		searchLabel.setBounds(15, 10, 250, 60);
		searchLabel.setForeground(Color.black);
		add(searchLabel);

		// 검색할 품종 종류 필드
		searchTextField = new JTextField();
		searchTextField.setBounds(15, 60, 250, 30);
		searchTextField.setText("");		
		searchTextField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("검색 결과 출력 바람");
			}
		});
		add(searchTextField);
		
		// 검색 버튼
		searchButton = new JButton("검색");
		searchButton.setBounds(265, 60, 70,30);
		searchButton.setBackground(new Color(91, 91, 91));
		searchButton.setForeground(Color.WHITE);
		searchButton.addMouseListener(commonMouseListener);
		add(searchButton);

		//설명 필드 추가
		searchResultTextArea = new JTextArea();
		searchResultTextArea.setBounds(15, 95, 318, 330);
		searchResultTextArea.setText("ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ");
		searchResultTextArea.setLineWrap(true);
		searchResultTextArea.setWrapStyleWord(true);
		searchResultTextArea.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(10), new EmptyBorder(10, 5, 10, 5) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
		add(searchResultTextArea);

		// JPanel 추가
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					// 이미지 크기 조정 후 그리기
					Image scaledImage = image.getScaledInstance(350, 450, Image.SCALE_SMOOTH);
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
		new PetSpeciesSearchDialog();
	}
}
