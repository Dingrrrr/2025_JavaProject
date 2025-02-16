package TeamProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;

public class PetRecordModifyScreen extends JFrame {
    private BufferedImage image;
    private JLabel backLabel, modifyLabel;
    private JLabel petRecordLabel;
    private JLabel petHeightLabel, petWeightLabel, petMtLabel, petVsLabel, petChecksLabel, petMtTimeLabel;
    private JTextField petHeightTField, petWeightTField, petMtTField, petVsTField, petChecksTField, petMtTimeTField;
    private JButton petRcModifyButton, petRcDeleteButton;
    private TPMgr mgr;
    private HRBean bean;
    private String h, w, me, va, ch, da;
    private int recordId; // Record_id 추가

    public PetRecordModifyScreen(HRBean hr, JFrame preFrame, int recordId) { // Record_id를 파라미터로 받음
        setTitle("프레임 설정");
        setSize(402, 874);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mgr = new TPMgr();
        bean = hr;
        this.recordId = recordId; // Record_id 저장

        try {
            image = ImageIO.read(new File("TeamProject/phone_frame.png")); // 투명 PNG 불러오기
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 🔹 공통 마우스 클릭 이벤트 리스너
        MouseAdapter commonMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object source = e.getSource(); // 클릭된 컴포넌트 확인

                if (source == backLabel) {
                    System.out.println("뒤로가기 버튼 클릭됨");
                    setVisible(false); // 창 숨기기
                    preFrame.setEnabled(true);
                    preFrame.setVisible(true); // PetHomeScreen을 보이게
                } else if (source == modifyLabel) {
                    System.out.println("수정 버튼 클릭됨");
                    petHeightTField.setEnabled(true);
                    petWeightTField.setEnabled(true);
                    petMtTField.setEnabled(true);
                    petVsTField.setEnabled(true);
                    petChecksTField.setEnabled(true);
                    petMtTimeTField.setEnabled(true);
                } else if (source == petRcModifyButton) {
                    System.out.println("수정 완료 버튼 클릭됨");

                    h = petHeightTField.getText();
                    w = petWeightTField.getText();
                    me = petMtTField.getText();
                    va = petVsTField.getText();
                    ch = petChecksTField.getText();
                    da = petMtTimeTField.getText();
                    BigDecimal he = new BigDecimal(0);
                    BigDecimal we = new BigDecimal(0);
                    try {
                        he = new BigDecimal(h);
                        we = new BigDecimal(w);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    bean.setHeight(he);
                    bean.setWeight(we);
                    bean.setMedical_history(me);
                    bean.setVaccination_status(va);
                    bean.setCheckup_status(ch);
                    bean.setDate(da);
                    
                    System.out.println(recordId);
                    mgr.updHRPet(recordId, bean); // Record_id 사용하여 수정

                    petHeightTField.setEnabled(false);
                    petWeightTField.setEnabled(false);
                    petMtTField.setEnabled(false);
                    petVsTField.setEnabled(false);
                    petChecksTField.setEnabled(false);
                    petMtTimeTField.setEnabled(false);
                    dispose();
                    preFrame.dispose();
                    new PetHomeScreen(StaticData.pet_id);
                } else if (source == petRcDeleteButton) {
                    System.out.println("삭제 버튼 클릭됨");
                    System.out.println(StaticData.pet_id);
                    mgr.delHRPet(recordId);  // Record_id 사용하여 삭제
                    dispose();
                    preFrame.dispose();
                    new PetHomeScreen(StaticData.pet_id);
                }
            }
        };

        // 🔹 상단 뒤로가기 아이콘
        backLabel = createScaledImageLabel("TeamProject/back_button.png", 40, 40);
        backLabel.setBounds(25, 120, 40, 40);
        backLabel.addMouseListener(commonMouseListener);
        add(backLabel);

        // 🔹 수정 아이콘
        modifyLabel = createScaledImageLabel("TeamProject/modify_icon.png", 35, 35);
        modifyLabel.setBounds(345, 122, 35, 35);
        modifyLabel.addMouseListener(commonMouseListener);
        add(modifyLabel);

        // 반려동물 건강기록 추가 안내 라벨
        petRecordLabel = new JLabel("가장 최근에 검진받은 정보를 적어주세요!");
        petRecordLabel.setBounds(43, 165, 306, 60);
        petRecordLabel.setForeground(Color.black);
        add(petRecordLabel);

        // 반려동물 키 라벨
        petHeightLabel = new JLabel("키");
        petHeightLabel.setBounds(43, 200, 17, 60);
        petHeightLabel.setForeground(Color.black);
        add(petHeightLabel);

        // 반려동물 키 텍스트 필드 추가
        BigDecimal height = new BigDecimal(bean.getHeight().toString());
        petHeightTField = new JTextField(height.toString());
        petHeightTField.setBounds(43, 250, 318, 40);
        petHeightTField.setEnabled(false);
        add(petHeightTField);

        // 반려동물 몸무게 라벨
        petWeightLabel = new JLabel("몸무게");
        petWeightLabel.setBounds(43, 285, 48, 60);
        petWeightLabel.setForeground(Color.black);
        add(petWeightLabel);

        // 반려동물 몸무게 텍스트 필드 추가
        BigDecimal weight = new BigDecimal(bean.getWeight().toString());
        petWeightTField = new JTextField(weight.toString());
        petWeightTField.setBounds(43, 335, 318, 40);
        petWeightTField.setEnabled(false);
        add(petWeightTField);

        // 반려동물 진료 기록 라벨
        petMtLabel = new JLabel("진료 기록");
        petMtLabel.setBounds(43, 370, 70, 60);
        petMtLabel.setForeground(Color.black);
        add(petMtLabel);

        // 반려동물 진료 기록 텍스트 필드 추가
        petMtTField = new JTextField(bean.getMedical_history());
        petMtTField.setBounds(43, 420, 318, 40);
        petMtTField.setEnabled(false);
        add(petMtTField);

        // 반려동물 예방접종 상태 라벨
        petVsLabel = new JLabel("예방접종 상태");
        petVsLabel.setBounds(43, 455, 104, 60);
        petVsLabel.setForeground(Color.black);
        add(petVsLabel);

        // 반려동물 예방접종 상태 텍스트 필드 추가
        petVsTField = new JTextField(bean.getVaccination_status());
        petVsTField.setBounds(43, 505, 318, 40);
        petVsTField.setEnabled(false);
        add(petVsTField);

        // 반려동물 체크해야 할 정보 라벨
        petChecksLabel = new JLabel("체크해야 할 정보");
        petChecksLabel.setBounds(43, 540, 104, 60);
        petChecksLabel.setForeground(Color.black);
        add(petChecksLabel);

        // 반려동물 체크해야 할 정보 텍스트 필드 추가
        petChecksTField = new JTextField(bean.getCheckup_status());
        petChecksTField.setBounds(43, 590, 318, 40);
        petChecksTField.setEnabled(false);
        add(petChecksTField);

        // 반려동물 진료 관련 시간 라벨
        petMtTimeLabel = new JLabel("진료 관련 시간");
        petMtTimeLabel.setBounds(43, 625, 104, 60);
        petMtTimeLabel.setForeground(Color.black);
        add(petMtTimeLabel);

        // 반려동물 진료 관련 시간 텍스트 필드 추가
        petMtTimeTField = new JTextField(bean.getDate());
        petMtTimeTField.setBounds(43, 675, 318, 40);
        petMtTimeTField.setEnabled(false);
        add(petMtTimeTField);

        // 반려동물 정보 수정 버튼
        petRcModifyButton = new JButton("완료");
        petRcModifyButton.setBounds(98, 740, 91, 43);
        petRcModifyButton.setBackground(new Color(91, 91, 91));
        petRcModifyButton.setForeground(Color.WHITE);
        petRcModifyButton.addMouseListener(commonMouseListener);
        add(petRcModifyButton);

        // 반려동물 정보 삭제 버튼
        petRcDeleteButton = new JButton("삭제");
        petRcDeleteButton.setBounds(215, 740, 91, 43);
        petRcDeleteButton.setBackground(new Color(91, 91, 91));
        petRcDeleteButton.setForeground(Color.WHITE);
        petRcDeleteButton.addMouseListener(commonMouseListener);
        add(petRcDeleteButton);

        // JPanel 추가
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    // 이미지 크기 조정 후 그리기
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                }
            }
        };
        panel.setBounds(0, 0, 402, 874);
        panel.setLayout(null);
        add(panel);

        setVisible(true);
    }

    private JLabel createScaledImageLabel(String imagePath, int width, int height) {
        JLabel label = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(imagePath);
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return label;
    }
}

