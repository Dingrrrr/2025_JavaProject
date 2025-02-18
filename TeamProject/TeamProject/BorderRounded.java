package TeamProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.border.AbstractBorder;

//텍스트필드 테두리 둥글게 하는 클래스
class RoundedBorder extends AbstractBorder {
    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.GRAY); // 테두리 색상
        g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius); // 둥근 테두리 그리기
        g2.dispose();
    }
}

//버튼 테두리 둥글게 하는 클래스
class RoundedButton extends JButton {
    public RoundedButton(String text) {
        super(text);
        setBorderPainted(false);
        setOpaque(false);	//불투명하게 만듦 -> background색이 보임
        setContentAreaFilled(false); // 기본 버튼 배경 제거
        setFocusPainted(false); // 클릭 시 테두리 제거
        setBorder(new RoundedBorder(20)); // 둥근 테두리 적용
        setForeground(Color.WHITE);
        setBackground(Color.GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g);
        g2.dispose();
    }
    
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.GRAY);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        g2.dispose();
    }
}





