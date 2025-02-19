package TeamProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class ImageDatabaseApp {
    
    private JFrame frame;
    private JLabel imageLabel;
    private JButton selectButton, uploadButton, retrieveButton;
    private File selectedFile;
    private DBConnectionMgr pool;
    
    public ImageDatabaseApp() {
        frame = new JFrame("Image Database App");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        pool = DBConnectionMgr.getInstance();
        
        imageLabel = new JLabel("No Image Selected", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(300, 300));
        frame.add(imageLabel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        selectButton = new JButton("Select Image");
        uploadButton = new JButton("Upload Image");
        retrieveButton = new JButton("Retrieve Image");
        
        buttonPanel.add(selectButton);
        buttonPanel.add(uploadButton);
        buttonPanel.add(retrieveButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        selectButton.addActionListener(e -> selectImage());
        uploadButton.addActionListener(e -> uploadImage());
        retrieveButton.addActionListener(e -> retrieveImage());
        
        frame.setVisible(true);
    }
    
    private void selectImage() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();

            // 이미지 미리보기 추가
            ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
            imageLabel.setText(""); 
        }
    }
    
    private void uploadImage() {
        if (selectedFile == null || !selectedFile.exists()) {
            JOptionPane.showMessageDialog(frame, "No valid image selected!");
            return;
        }
        
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try (FileInputStream fis = new FileInputStream(selectedFile)) {
            con = pool.getConnection();
            pstmt = con.prepareStatement("INSERT INTO images (image) VALUES (?)");
            pstmt.setBinaryStream(1, fis, (int) selectedFile.length());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Image uploaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt);
        }
    }
    
    private void retrieveImage() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = pool.getConnection();
            pstmt = con.prepareStatement("SELECT image FROM images ORDER BY id DESC LIMIT 1");
            rs = pstmt.executeQuery();

            if (rs.next()) {
                byte[] imgBytes = rs.getBytes("image");
                ImageIcon icon = new ImageIcon(imgBytes);
                Image img = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
                imageLabel.setText(""); 
            } else {
                JOptionPane.showMessageDialog(frame, "No image found in database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ImageDatabaseApp::new);
    }
}


