package TeamProject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class CalendarDialog {
    private JFrame frame;
    private JLabel monthLabel;
    private JPanel calendarPanel;
    private int currentYear, currentMonth;

    public CalendarDialog(JFrame preFrame, JTextField tf) {
        frame = new JFrame("캘린더");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        StaticData.jf = preFrame;
        StaticData.tf = tf;

        // 전체를 덮는 패널 생성
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new LineBorder(Color.BLACK, 1)); // 전체 테두리 설정
        mainPanel.setBackground(Color.WHITE);
        
        // 상단 패널 설정
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        JButton prevButton = new JButton("<");
        JButton nextButton = new JButton(">");
        monthLabel = new JLabel("", JLabel.CENTER);

        prevButton.setContentAreaFilled(false);
        prevButton.setBorderPainted(false);
        nextButton.setContentAreaFilled(false);
        nextButton.setBorderPainted(false);

        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeMonth(-1);
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeMonth(1);
            }
        });

        topPanel.add(prevButton);
        topPanel.add(monthLabel);
        topPanel.add(nextButton);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // 달력 패널 설정
        calendarPanel = new JPanel(new GridLayout(0, 7));
        calendarPanel.setBackground(Color.WHITE);
        mainPanel.add(calendarPanel, BorderLayout.CENTER);

        frame.add(mainPanel); // 전체 패널을 프레임에 추가

        Calendar cal = Calendar.getInstance();
        currentYear = cal.get(Calendar.YEAR);
        currentMonth = cal.get(Calendar.MONTH);
        updateCalendar();

        frame.setVisible(true);
    }

    private void changeMonth(int delta) {
        currentMonth += delta;
        if (currentMonth < 0) {
            currentMonth = 11;
            currentYear--;
        } else if (currentMonth > 11) {
            currentMonth = 0;
            currentYear++;
        }
        updateCalendar();
    }

    private void updateCalendar() {
        calendarPanel.removeAll();
        String[] days = {"일", "월", "화", "수", "목", "금", "토"};
        for (String day : days) {
            JLabel dayLabel = new JLabel(day, JLabel.CENTER);
            dayLabel.setOpaque(true);
            dayLabel.setBackground(Color.WHITE);
            calendarPanel.add(dayLabel);
        }

        Calendar cal = Calendar.getInstance();
        cal.set(currentYear, currentMonth, 1);
        int firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i < firstDayOfWeek; i++) {
            calendarPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= daysInMonth; day++) {
            final int selectedDay = day;
            JLabel dayLabel = new JLabel(String.valueOf(day), JLabel.CENTER);
            dayLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            dayLabel.setOpaque(true);
            dayLabel.setBackground(Color.WHITE);
            dayLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    String date = String.format("%d.%02d.%02d", currentYear, (currentMonth + 1), selectedDay);
                    StaticData.tf.setText(date);
                    frame.dispose();
                    StaticData.jf.setEnabled(true);
                    StaticData.jf.setVisible(true);
                }
            });
            calendarPanel.add(dayLabel);
        }

        monthLabel.setText(currentYear + "년 " + (currentMonth + 1) + "월");
        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    public static void main(String[] args) {
    }
}
