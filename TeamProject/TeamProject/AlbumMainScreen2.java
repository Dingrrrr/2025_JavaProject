package TeamProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class AlbumMainScreen2 extends JFrame {
    private BufferedImage image;
    private JLabel alarmLabel, profileLabel, addButtonLabel, photoLabel, homeLabel, commuLabel, voteLabel;
    private JPanel albumPanel; // 앨범 패널
    private JScrollPane scrollPane; // 스크롤 패널

    public AlbumMainScreen2() {
        setTitle("프레임 설정");
        setSize(402, 874);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            image = ImageIO.read(new File("TeamProject/phone_frame.png")); // 투명 PNG 불러오기
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 🔹 배경 패널
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    Image scaledImage = image.getScaledInstance(402, 874, Image.SCALE_SMOOTH);
                    g.drawImage(scaledImage, 0, 0, this);
                }
                g.setColor(Color.LIGHT_GRAY);
                g.drawLine(22, 165, 379, 165);
                g.drawLine(22, 780, 379, 780);
                g.drawLine(111, 780, 111, 851);
                g.drawLine(200, 780, 200, 851);
                g.drawLine(289, 780, 289, 851);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.black);
                g2.setStroke(new BasicStroke(5));
                g2.drawLine(135, 841, 262, 841);
            }
        };

        panel.setOpaque(false);
        panel.setLayout(null);
        add(panel);

        // 🔹 스크롤 가능한 앨범 패널 설정
        albumPanel = new JPanel();
        albumPanel.setLayout(new GridLayout(0, 2, 10, 10)); // 2열 배치
        albumPanel.setBackground(Color.WHITE);

        // 🔹 스크롤 패널 추가 (0, 161 ~ 874, 782 영역에 배치)
        scrollPane = new JScrollPane(albumPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(22, 165, 375, 615);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        panel.add(scrollPane);

        // 🔹 추가 버튼
        addButtonLabel = createScaledImageLabel("TeamProject/add_button.png", 92, 92);
        addButtonLabel.setBounds(155, 400, 92, 92);
        addButtonLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addAlbum();
            }
        });
        panel.add(addButtonLabel);

        // 🔹 더미 앨범 데이터 추가
        for (int i = 1; i <= 8; i++) {
            addAlbum();
        }

        // 🔹 닫기 버튼
        JButton closeButton = new JButton("X");
        closeButton.setBounds(370, 10, 20, 20);
        closeButton.setBackground(Color.RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.setBorder(BorderFactory.createEmptyBorder());
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> System.exit(0));
        panel.add(closeButton);

        setVisible(true);
    }

    /**
     * 앨범 추가 메서드
     */
    private void addAlbum() {
        JLabel albumLabel = new JLabel("📸 앨범 " + (albumPanel.getComponentCount() + 1));
        albumLabel.setFont(new Font("Arial", Font.BOLD, 16));
        albumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        albumLabel.setOpaque(true);
        albumLabel.setBackground(Color.LIGHT_GRAY);
        albumLabel.setPreferredSize(new Dimension(150, 100));
        albumPanel.add(albumLabel);

        // 패널 업데이트
        albumPanel.revalidate();
        albumPanel.repaint();
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
        new AlbumMainScreen2();
    }
}


