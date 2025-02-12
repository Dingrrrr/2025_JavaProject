package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class CircularProfilePanel extends JPanel {
    private BufferedImage image;

    public CircularProfilePanel(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath)); // 로컬 이미지 불러오기
        } catch (IOException e) {
            System.out.println("이미지를 불러올 수 없습니다: " + e.getMessage());
        }
        setPreferredSize(new Dimension(270, 270)); // 패널 크기 지정
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int width = getWidth();
            int height = getHeight();
            
            // 원형 이미지 그리기
            BufferedImage circleBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = circleBuffer.createGraphics();
            
            g2.setClip(new Ellipse2D.Float(0, 0, width, height));
            g2.drawImage(image, 0, 0, width, height, this);
            g2.dispose();
            
            g.drawImage(circleBuffer, 0, 0, null);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("원형 프로필 이미지");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(270, 270);
        frame.setLayout(new BorderLayout());

        CircularProfilePanel profilePanel = new CircularProfilePanel("dproject/profile.png"); // 이미지 경로 설정
        frame.add(profilePanel);

        frame.setVisible(true);
    }
}

