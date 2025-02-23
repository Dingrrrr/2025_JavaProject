package TeamProject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
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

        // 이미지 크기 설정
        int imgWidth = getWidth() - 2 * borderWidth;  // 테두리 두께만큼 이미지 크기 축소
        int imgHeight = getHeight() - 2 * borderWidth;  // 테두리 두께만큼 이미지 크기 축소

        // 이미지의 y 좌표 계산 (정렬 방식에 따라 다르게)
        int y = borderWidth;  // 테두리 두께를 고려하여 이미지 y 좌표 설정
        if (verticalAlignment == CENTER) {
            y = (getHeight() - imgHeight) / 2;
        } else if (verticalAlignment == BOTTOM) {
            y = getHeight() - imgHeight - borderWidth;
        }

        // 둥근 이미지를 만들기 위해 BufferedImage 생성
        BufferedImage roundedImage = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // 이미지를 둥근 모서리로 자르기 위한 Clip 설정
        g2d.setClip(new RoundRectangle2D.Double(0, 0, imgWidth, imgHeight, arcWidth, arcHeight));
        g2d.drawImage(image, 0, 0, imgWidth, imgHeight, this);
        g2d.dispose();

        // 이미지를 그린 후 둥근 테두리 그리기
        g2.drawImage(roundedImage, borderWidth, y, this);

        // 테두리 그리기
        g2.setStroke(new BasicStroke(7));  // 테두리 두께 설정
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
