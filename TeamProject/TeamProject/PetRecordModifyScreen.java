package TeamProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

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
    private JLabel petHeightLabel, petWeightLabel, petMtLabel, petVsLabel, petChecksLabel, petMtTimeLabel, warningLabel;
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
                    petRcModifyButton.setEnabled(true);
                    petRcDeleteButton.setEnabled(true);
                } else if (source == petRcModifyButton) {
                    String time = petMtTimeTField.getText().trim();
                    if(!time.isEmpty()) {
                    	if(time.substring(0, 1).equals("0") || time.length()!= 8)
                    		warningLabel.setVisible(true);
                    } else if (time.isEmpty() || (!time.substring(0, 1).equals("0") && time.length()==8)){
                    	System.out.println("수정 완료 버튼 클릭됨");
                    	h = petHeightTField.getText().trim();
                        w = petWeightTField.getText().trim();
                        me = petMtTField.getText().trim();
                        va = petVsTField.getText().trim();
                        ch = petChecksTField.getText().trim();
                        da = petMtTimeTField.getText().trim();
                        BigDecimal he = new BigDecimal(0);
                        BigDecimal we = new BigDecimal(0);
                        try {
                            he = new BigDecimal(h);
                            we = new BigDecimal(w);
                            bean.setHeight(he);
                            bean.setWeight(we);
                        } catch (Exception e2) {
                        	he = new BigDecimal(0);
							we = new BigDecimal(0);
							bean.setHeight(he);
							bean.setWeight(we);
                        }
                        bean.setMedical_history(me);
                        bean.setVaccination_status(va);
                        bean.setCheckup_status(ch);
                        bean.setDate(da);
                        
                        System.out.println(recordId);
                        mgr.updHRPet(recordId, bean); // Record_id 사용하여 수정

                        dispose();
                        preFrame.dispose();
                        new PetHomeScreen(StaticData.pet_id);
                    }
                    
                } else if (source == petRcDeleteButton) {
                    System.out.println("삭제 버튼 클릭됨");
                    System.out.println(recordId);
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
        petHeightTField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
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
        petWeightTField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
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
        petMtTField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
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
        petVsTField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
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
        petChecksTField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
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
        petMtTimeTField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
        add(petMtTimeTField);
        
        ((AbstractDocument) petMtTimeTField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string != null) {
                    // 기존 내용과 새로 입력할 내용을 합친 길이를 확인
                    String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                    String newText = currentText.substring(0, offset) + string + currentText.substring(offset);
                    if (newText.matches("\\d{0,8}")) { // 8자리 숫자 체크 
                        super.insertString(fb, offset, string.replaceAll("[^0-9]", ""), attr);
                    }
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text != null) {
                    // 기존 내용과 새로 입력할 내용을 합친 길이를 확인
                    String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                    String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);
                    if (newText.matches("\\d{0,8}")) { // 8자리 숫자 체크 
                        super.replace(fb, offset, length, text.replaceAll("[^0-9]", ""), attrs);
                    }
                }
            }

            @Override
            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                super.remove(fb, offset, length);
            }
        });
        
        warningLabel = new JLabel("올바른 진료 시간을 기입하시오");
        warningLabel.setForeground(Color.RED);
        warningLabel.setBounds(43, 705, 200, 40);
        add(warningLabel);
        warningLabel.setVisible(false);

        // 반려동물 정보 수정 버튼
        petRcModifyButton = new JButton("완료");
        petRcModifyButton.setBounds(98, 740, 91, 43);
        petRcModifyButton.setBackground(new Color(91, 91, 91));
        petRcModifyButton.setForeground(Color.WHITE);
        petRcModifyButton.addMouseListener(commonMouseListener);
        add(petRcModifyButton);
        petRcModifyButton.setEnabled(false);

        // 반려동물 정보 삭제 버튼
        petRcDeleteButton = new JButton("삭제");
        petRcDeleteButton.setBounds(215, 740, 91, 43);
        petRcDeleteButton.setBackground(new Color(91, 91, 91));
        petRcDeleteButton.setForeground(Color.WHITE);
        petRcDeleteButton.addMouseListener(commonMouseListener);
        add(petRcDeleteButton);
        petRcDeleteButton.setEnabled(false);

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

