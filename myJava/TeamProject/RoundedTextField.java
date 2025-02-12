package TeamProject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class RoundedTextField extends JTextField {
	private int arcWidth = 0; // 모서리 둥글기(가로)
	private int arcHeight = 20; //모서리 둥글기(세로)
	
	public RoundedTextField(int columns) {
		super(columns);
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
	}
	
	//테두리를 둥근 사각형으로 그리는 메서드
	@Override
	public void paintComponents(Graphics g) {
		if(g instanceof Graphics2D g2) {
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(getBackground());
			g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
		}
	}
}
