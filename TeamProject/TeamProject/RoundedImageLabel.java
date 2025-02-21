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
    private int verticalAlignment;  // 이미지 정렬을 위한 변수

    public static final int TOP = 0;
    public static final int CENTER = 1;
    public static final int BOTTOM = 2;

    public RoundedImageLabel(Image image, int arcWidth, int arcHeight, int borderWidth) {
        this.image = image;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.borderWidth = borderWidth;  // 테두리 두께 초기화
        this.verticalAlignment = TOP;  // 기본값: 위쪽 정렬

        // 배경을 투명하게 설정
        setOpaque(false);
        
        setLayout(null);
        setSize(200, 200); // 크기 조정
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // 둥근 모서리 설정
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        g2.setClip(roundedRectangle);

        // 이미지 크기
        int imgWidth = getWidth();
        int imgHeight = getHeight();

        // 이미지의 y 좌표 계산 (정렬 방식에 따라 다르게)
        int y = 0;
        if (verticalAlignment == CENTER) {
            y = (getHeight() - imgHeight) / 2;
        } else if (verticalAlignment == BOTTOM) {
            y = getHeight() - imgHeight;
        }

        // 이미지 그리기
        g2.drawImage(image, 0, y, imgWidth, imgHeight, this);

        // 테두리 그리기
        g2.setStroke(new BasicStroke(borderWidth));  // 테두리 두께 설정
        g2.setColor(java.awt.Color.white);  // 테두리 색상 설정 (원하는 색으로 변경 가능)
        g2.draw(roundedRectangle);  // 테두리 그리기

        g2.dispose();
    }

    public void setImage(Image image) {
        this.image = image;
        repaint();  // 이미지 변경 후 UI 갱신
    }

    // 이미지 정렬 방식 설정
    public void setVerticalAlignment(int alignment) {
        if (alignment == TOP || alignment == CENTER || alignment == BOTTOM) {
            this.verticalAlignment = alignment;
            repaint();  // 정렬 방식 변경 후 UI 갱신
        }
    }
}
