package TeamProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;

public class PetSpeciesSearchDialog extends JFrame {
	private BufferedImage image;
	private JLabel closeLabel;
	private JLabel searchLabel;
	private JButton searchButton;
	private JTextField searchTextField;
	private String dogSearch;
	DefaultListModel<String> dogList;
	JList<String> searchResultList;
	JScrollPane scrollPane;
	TPMgr mgr;
	Vector<DogBean> vlist;

	public PetSpeciesSearchDialog(PetAddScreen preFrame) {
		setTitle("프레임 설정");
		setSize(350, 450);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mgr = new TPMgr();
		vlist = mgr.showDog();

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
				} else if (source == searchButton) {
					Vector<DogBean> vd = new Vector<DogBean>();
					System.out.println("검색 버튼 클릭됨");
					dogSearch = searchTextField.getText().trim();
					dogSearch = "%"+dogSearch+"%";
					vd = mgr.showSearchDog(dogSearch);
					dogList.removeAllElements();
					for (DogBean db : vd) {
						dogList.addElement(db.getDog());
					}
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
		searchButton.setBounds(265, 60, 70, 30);
		searchButton.setBackground(new Color(91, 91, 91));
		searchButton.setForeground(Color.WHITE);
		searchButton.addMouseListener(commonMouseListener);
		add(searchButton);

		/*
		 * // 설명 필드 추가 searchResultTextArea = new JTextArea();
		 * searchResultTextArea.setBounds(15, 95, 318, 330); for (DogBean db : vlist) {
		 * searchResultTextArea.append(db.getDog()); }
		 * searchResultTextArea.setLineWrap(true);
		 * searchResultTextArea.setWrapStyleWord(true); searchResultTextArea
		 * .setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(10), new
		 * EmptyBorder(10, 5, 10, 5) )); add(searchResultTextArea);
		 */

		// 검색 결과 리스트 모델 및 JList 생성
		dogList = new DefaultListModel<>();
		searchResultList = new JList<>(dogList);
		scrollPane = new JScrollPane(searchResultList);

		// 스크롤 및 테두리 설정
		scrollPane.setBounds(15, 95, 318, 330);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));  // 테두리 설정
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER); // 수직 스크롤 바 숨김
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // 수평 스크롤 바 숨김

		// JList 크기 고정 (스크롤 방지)
		searchResultList.setFixedCellHeight(30);  // 각 항목 높이 고정
		searchResultList.setFixedCellWidth(303);  // 너비 고정

		// 데이터 추가 (DogBean에서 정보 가져오기)
		for (DogBean db : vlist) {
		    dogList.addElement(db.getDog()); // 리스트에 추가
		}
		
		//클릭한 데이터 이벤트
		searchResultList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // 더블클릭 감지
                    String selectedItem = searchResultList.getSelectedValue();
                    if (selectedItem != null) {
                        dispose();
                        preFrame.updateSpecies(selectedItem);
                        preFrame.setEnabled(true);
                        preFrame.setVisible(true);
                    }
                }
			}
		});

		// 리스트 스타일 설정
		searchResultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		searchResultList.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(10), new EmptyBorder(10, 5, 10, 5))); // 둥근 테두리 설정
		searchResultList.setBackground(Color.WHITE); // 배경색 설정 (선택적)

		// 리스트 추가
		add(scrollPane);

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
		new LoginScreen();
	}
}
