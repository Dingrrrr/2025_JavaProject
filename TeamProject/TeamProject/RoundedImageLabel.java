package TeamProject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.BasicStroke;  // 테두리 설정을 위한 추가 import
import javax.swing.JPanel;

public class RoundedImageLabel extends JPanel {
    private Image image;
    private int arcWidth;
    private int arcHeight;
    private int borderWidth;  // 테두리 두께

    public RoundedImageLabel(Image image, int arcWidth, int arcHeight, int borderWidth) {
        this.image = image;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.borderWidth = borderWidth;  // 테두리 두께 초기화
        
        // 배경을 투명하게 설정
        setOpaque(false);
        
        setLayout(null);
        setSize(200, 200); // 크기 조정
    }

    @Override
    protected void paintComponent(Graphics g) {
        // 배경을 지우지 않음 (투명 유지)
        // super.paintComponent(g); << 주석 처리
        
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // 둥근 모서리 설정
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        g2.setClip(roundedRectangle);

        // 이미지 그리기
        g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        // 테두리 그리기
        g2.setStroke(new BasicStroke(borderWidth));  // 테두리 두께 설정
        g2.setColor(java.awt.Color.white);  // 테두리 색상 설정 (원하는 색으로 변경 가능)
        g2.draw(roundedRectangle);  // 테두리 그리기

        g2.dispose();
    }
}

