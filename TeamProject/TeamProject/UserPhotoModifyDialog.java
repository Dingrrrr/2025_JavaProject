package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.*;

public class UserPhotoModifyDialog extends JFrame {
	private JPanel p;
	private BufferedImage image;
	private JButton addpicButton, deletepicButton, cancelButton;
	private UpdateUserScreen updateUserScreen; // UpdateUserScreen 객체를 저장할 변수
	private JFrame frame;
	private File selectedFile;

	public UserPhotoModifyDialog(UpdateUserScreen updateUserScreen) {
		setTitle("프레임 설정");
		setSize(358, 160);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.updateUserScreen = updateUserScreen;

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
				if (source == addpicButton) {
					System.out.println("추가 버튼 클릭됨");
					selectImage();
					dispose();
					updateUserScreen.setEnabled(true);
					updateUserScreen.setVisible(true);
				} else if (source == deletepicButton) {
					System.out.println("삭제 버튼 클릭됨");
					deleteImage();
					dispose();
					updateUserScreen.setEnabled(true);
					updateUserScreen.setVisible(true);
				} else if (source == cancelButton) {
					System.out.println("취소 버튼 클릭됨");
					dispose();
					updateUserScreen.setEnabled(true);
					updateUserScreen.setVisible(true);
				}

			}
		};

		// 사진 추가 버튼
		addpicButton = new JButton("사진 추가");
		addpicButton.setBounds(2, 2, 354, 53);
		addpicButton.setBackground(Color.white);
		addpicButton.setFocusable(false); // 포커스 끄기
		addpicButton.addMouseListener(commonMouseListener);
		add(addpicButton);

		// 사진 삭제 버튼
		deletepicButton = new JButton("사진 삭제");
		deletepicButton.setBounds(2, 55, 354, 53);
		deletepicButton.setBackground(Color.white);
		deletepicButton.setFocusable(false); // 포커스 끄기
		deletepicButton.addMouseListener(commonMouseListener);
		add(deletepicButton);

		// 취소 버튼
		cancelButton = new JButton("취소");
		cancelButton.setBounds(2, 105, 354, 53);
		cancelButton.setBackground(Color.white);
		cancelButton.setFocusable(false); // 포커스 끄기
		cancelButton.addMouseListener(commonMouseListener);
		add(cancelButton);

		// JPanel 추가
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					// 이미지 크기 조정 후 그리기
					Image scaledImage = image.getScaledInstance(358, 160, Image.SCALE_SMOOTH);
					g.drawImage(scaledImage, 0, 0, this);
				}
				// y=158 위치에 가로로 회색 선 그리기
				g.setColor(Color.LIGHT_GRAY); // 선 색을 회색으로 설정
				g.drawLine(3, 55, 360, 55);
				g.drawLine(3, 105, 360, 105);
			}
		};

		panel.setLayout(null);
		panel.setOpaque(false); // 🔹 배경을 투명하게 설정
		add(panel);

		setVisible(true);

		/*
		 * // 🔹 회색프레임 grayFrameLabel =
		 * createScaledImageLabel("TeamProject/photo_frame.png", 280, 280);
		 * grayFrameLabel.setBounds(35, 90, 280, 280); add(grayFrameLabel,
		 * BorderLayout.SOUTH);
		 */
	}

	private void selectImage() {
	    JFileChooser fileChooser = new JFileChooser();
	    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
	        selectedFile = fileChooser.getSelectedFile();
	        
	        try {
	            // 이미지 파일 유효성 검사
	            BufferedImage testImage = ImageIO.read(selectedFile);
	            if (testImage == null) {
	                JOptionPane.showMessageDialog(this, "유효한 이미지 파일이 아닙니다.", "오류", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            
	            // 이미지를 byte[]로 변환
	            byte[] imageBytes = convertFileToByteArray(selectedFile);
	            
	            // 변환된 이미지를 updateUserScreen에 저장
	            if (imageBytes != null && imageBytes.length > 0) {
	                updateUserScreen.setImageBytes(imageBytes);
	                
	                // UI에 이미지 표시 로직
	                JLabel imageLabel = updateUserScreen.getImageLabel();
	                if (imageLabel != null) {
	                	
	                    // 기존 imageLabel이 있는 경우 업데이트
	                    ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
	                    Image img = icon.getImage();
	                    Image resizedImg = img.getScaledInstance(270, 270, Image.SCALE_SMOOTH);
	                    ImageIcon resizedIcon = new ImageIcon(resizedImg);
	                    imageLabel.setIcon(resizedIcon);
	                    imageLabel.setText("");
	                } else {
	                    // imageLabel이 null인 경우 - UpdateUserScreen 새로고침 요청
	                    JOptionPane.showMessageDialog(this, "이미지가 선택되었습니다. 확인 버튼을 누르면 적용됩니다.");
	                    // 이미지 변경 후 필요한 경우 화면 갱신을 위한 코드 추가
	                    updateUserScreen.repaint();
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "이미지 처리 중 오류가 발생했습니다: " + e.getMessage());
	        }
	    }
	}
	
	private void deleteImage() {
	    try {
	        // 직접 파일 경로 지정
	        File selectedFile = new File("TeamProject/profile.png");
	        
	        // 파일 존재 확인
	        if (!selectedFile.exists()) {
	            System.out.println("기본 이미지 파일이 존재하지 않습니다: " + selectedFile.getAbsolutePath());
	            return;
	        }
	        
	        // 이미지 읽기
	        ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
	        Image img = icon.getImage();
	        
	        // getScaledInstance로 이미지 크기 조정
	        Image resizedImg = img.getScaledInstance(270, 270, Image.SCALE_SMOOTH);
	        
	        // 새로운 ImageIcon 생성
	        ImageIcon resizedIcon = new ImageIcon(resizedImg);
	        
	        // JLabel이 null이 아닌지 확인
	        JLabel imageLabel = updateUserScreen.getImageLabel();
	        if (imageLabel != null) {
	            imageLabel.setIcon(resizedIcon);
	            imageLabel.setText(""); // 텍스트 제거
	        }
	        
	        // 이미지를 byte[]로 변환
	        byte[] imageBytes = convertFileToByteArray(selectedFile);
	        
	        // 변환된 이미지를 updateUserScreen에 저장
	        updateUserScreen.setImageBytes(imageBytes);
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "이미지 삭제 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
	    }
	}

	// 파일을 byte 배열로 변환하는 메서드
	private byte[] convertFileToByteArray(File file) {
	    try (FileInputStream fis = new FileInputStream(file);
	         ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
	        byte[] buffer = new byte[1024];
	        int bytesRead;
	        while ((bytesRead = fis.read(buffer)) != -1) {
	            baos.write(buffer, 0, bytesRead);
	        }
	        return baos.toByteArray();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	public static void main(String[] args) {
	}
}
