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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Stack;

public class PetRecordAddScreen extends JFrame {
	private BufferedImage image;
	private JLabel backLabel, calLabel;
	private JLabel petRecordLabel, logoLabel;
	private JLabel petHeightLabel, petWeightabel, petMtLabel, petVsLabel, petChecksLabel, petMtTimeLabel;
	private JTextField petHeightTField, petWeightTField, petMtTField, petVsTField, petChecksTField, petMtTimeTField;
	private JButton petAddRcButton;
	private JFrame previousFrame;  // 이전 프레임 저장
	int pet_id;
	boolean flag = true;
	TPMgr mgr;
	HRBean bean;
	java.util.Date date = new java.util.Date();
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	public PetRecordAddScreen(PetBean pb, JFrame previousFrame) {
		setTitle("프레임 설정");
		setSize(402, 874);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mgr = new TPMgr();
		bean = new HRBean();
		pet_id = pb.getPet_id();

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
				
				if (source == petAddRcButton) {
					System.out.println("기입완료 버튼 클릭됨");
					BigDecimal height = new BigDecimal(0);
					BigDecimal weight = new BigDecimal(0);
					try {
						if (!petHeightTField.getText().trim().isEmpty()) {
							height = new BigDecimal(petHeightTField.getText());
						}
						if (!petWeightTField.getText().trim().isEmpty()) {
							weight = new BigDecimal(petWeightTField.getText());
						}
						bean.setHeight(height);
						bean.setWeight(weight);
					} catch (Exception e2) { // 텍스트 필드값이 숫자가 아닌 경우
						height = new BigDecimal(0);
						weight = new BigDecimal(0);
						bean.setHeight(height);
						bean.setWeight(weight);
					}
					bean.setMedical_history(petMtTField.getText().trim());
					bean.setVaccination_status(petVsTField.getText().trim());
					bean.setCheckup_status(petChecksTField.getText().trim());
					bean.setDate(petMtTimeTField.getText().trim());
					if(!mgr.isThatPet(StaticData.pet_id)) {
						mgr.addPet(StaticData.user_id, pb);
						pet_id = mgr.showPetId(StaticData.user_id);
						isBirthAndMedic(pet_id);
					}
					mgr.addHRPet(pet_id, bean);
					dispose();
					new PetHomeScreen(pet_id);
				} else if (source == backLabel) {
					System.out.println("뒤로가기 버튼 클릭됨");
					dispose();
					previousFrame.setVisible(true);
				} else if(source == calLabel) {
					setEnabled(false);
					new CalendarDialog(PetRecordAddScreen.this, petMtTimeTField);
				}
			}
		};

		// 🔹 상단 뒤로가기 아이콘
		backLabel = createScaledImageLabel("TeamProject/back_button.png", 40, 40);
		backLabel.setBounds(25, 120, 40, 40);
		backLabel.addMouseListener(commonMouseListener);
		add(backLabel);
		
		// 로고 아이콘
		logoLabel = createScaledImageLabel("TeamProject/logo2.png", 180, 165);
		logoLabel.setBounds(105, 54, 180, 165);
		add(logoLabel);
		
		// 🔹 캘린더 아이콘
		calLabel = createScaledImageLabel("TeamProject/calendar.png", 30, 30);
		calLabel.setBounds(330, 680, 30, 30);
		calLabel.addMouseListener(commonMouseListener);
		add(calLabel);

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
		petHeightTField = new JTextField();
		petHeightTField.setBounds(43, 250, 318, 40);
		petHeightTField.setText("");
		petHeightTField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
		add(petHeightTField);

		// 반려동물 몸무게 라벨
		petWeightabel = new JLabel("몸무게");
		petWeightabel.setBounds(43, 285, 48, 60);
		petWeightabel.setForeground(Color.black);
		add(petWeightabel);

		// 반려동물 몸무게 텍스트 필드 추가
		petWeightTField = new JTextField();
		petWeightTField.setBounds(43, 335, 318, 40);
		petWeightTField.setText("");
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
		petMtTField = new JTextField();
		petMtTField.setBounds(43, 420, 318, 40);
		petMtTField.setText("");
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
		petVsTField = new JTextField();
		petVsTField.setBounds(43, 505, 318, 40);
		petVsTField.setText("");
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
		petChecksTField = new JTextField();
		petChecksTField.setBounds(43, 590, 318, 40);
		petChecksTField.setText("");
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
		petMtTimeTField = new JTextField();
		petMtTimeTField.setBounds(43, 675, 280, 40);
		petMtTField.setForeground(Color.GRAY);
		petMtTimeTField.setEnabled(false);
		petMtTimeTField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
		add(petMtTimeTField);
		
		
   
        

		// 반려동물 정보 입력 완료 버튼
		petAddRcButton = new RoundedButton("기입 완료");
		petAddRcButton.setBounds(53, 740, 281, 58);
		petAddRcButton.setBackground(new Color(91, 91, 91));
		petAddRcButton.setForeground(Color.WHITE);
		petAddRcButton.addMouseListener(commonMouseListener);
		add(petAddRcButton);

		// JPanel 추가
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					// 이미지 크기 조정 후 그리기
					Image scaledImage = image.getScaledInstance(402, 874, Image.SCALE_SMOOTH);
					g.drawImage(scaledImage, 0, 0, this);
				}

				// y=158 위치에 가로로 회색 선 그리기
				g.setColor(Color.LIGHT_GRAY); // 선 색을 회색으로 설정
				g.drawLine(22, 165, 379, 165);
			}
		};

		panel.setOpaque(false);
		panel.setLayout(null);
		add(panel);

		// 닫기 버튼
		JButton closeButton = new JButton("X");
		closeButton.setBounds(370, 10, 20, 20);
		closeButton.setBackground(Color.RED);
		closeButton.setForeground(Color.WHITE);
		closeButton.setBorder(BorderFactory.createEmptyBorder());
		closeButton.setFocusPainted(false);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mgr.userOut(StaticData.user_id);
				System.exit(0);
			}
		});
		panel.add(closeButton);

		setVisible(true);
	}

	/**
	 * 이미지 크기를 조정하여 JLabel을 생성하는 메서드
	 */
	private JLabel createScaledImageLabel(String imagePath, int width, int height) {
		ImageIcon icon = new ImageIcon(imagePath);
		Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new JLabel(new ImageIcon(scaledImage));
	}
	
	private void isBirthAndMedic(int pet_id) {
		PetBean pb = mgr.showOnePet(pet_id);
		String birth = pb.getPet_age();
		if (!birth.isEmpty()) { // 생일이 기입했을 경우
			// 반려동물 생일 알림
			Calendar calendar = Calendar.getInstance();
			int month1 = calendar.get(Calendar.MONTH) + 1; // 월은 0부터 시작하므로 +1
			int day1 = calendar.get(Calendar.DAY_OF_MONTH);
			String today = String.format("%02d%02d", month1, day1); // 형식: MM월 DD일
			if (mgr.isPetBirth(pb.getPet_id()).equals(birth)) { // 마지막으로 알림 보낸 날짜가 오늘이랑 같을 경우
				// 반응 안함
			} else { // 마지막으로 알림 보낸 날짜가 오늘이 아닌경우
				String[] date = birth.split("\\.");
				String month = date[1];
				String day = date[2];
				if (today.equals(month + day)) { // 오늘이 생일인 경우
					MsgBean mb = new MsgBean();
					mb.setMsg_title(pb.getPet_name() + "의 특별한 날! 생일 축하해요!");
					mb.setMsg_content("안녕하세요! 좋은 소식을 전해 드립니다! \r\n" + "오늘은 바로" + pb.getPet_name() + "의 생일이에요! "
							+ "이 특별한 날을 축하해 주세요! 맛있는 간식과 함께 행복한 시간을 보내길 바래요. \r\n" + pb.getPet_name()
							+ "도 여러분의 사랑을 기다리고 있을 거예요! \r\n" + "즐거운 하루 되세요!");
					mb.setReceiver_id(StaticData.user_id);
					mgr.sendMsg("admin", mb);
					mgr.petBirth(pb.getPet_id(), birth);
				}
			}
		}
		
		// 건강 검진일 알림

		// 혼합 백신(생후 6주부터 2주간격으로 계속 알림, 15주부터 매년 알림)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

		// 생일을 LocalDate로 변환
		LocalDate birthday = LocalDate.parse(birth, formatter);
		LocalDate currentDay = LocalDate.now();

		// 6주 후부터 2주 간격으로 5번 알림
		int weeksAfter = 6;
		int interval = 2;
		int reminderCount = 5;

		for (int i = 0; i < reminderCount; i++) {
			LocalDate reminderDate = birthday.plusWeeks(weeksAfter + (i * interval));
			if (reminderDate.equals(currentDay)) { // 검진일이 오늘이라면
				MsgBean mgb = new MsgBean();
				mgb.setMsg_title("반려동물 혼합 백신 검진일 안내");
				mgb.setMsg_content("안녕하세요, " + mgr.showOneUserName(StaticData.user_id) + "님!\r\n"
						+ "소중한 반려동물의 건강을 위해 혼합 백신 검진일이 다가왔음을 알려드립니다.\r\n" + "\r\n" + "검진 대상: ["
						+ pb.getPet_name() + "]\r\n" + "예정일: [" + reminderDate.format(formatter) + "]\r\n"
						+ "\r\n" + "정기적인 백신 접종은 우리 아이의 건강을 지키는 가장 좋은 방법입니다. 잊지 말고 가까운 동물병원을 방문해 주세요! ");
				mgb.setReceiver_id(StaticData.user_id);
				if (mgr.isPetMedic(pb.getPet_id(), "혼합 백신").equals(currentDay.format(formatter))) { // 혼합 백신 알림을
																									// 보낸게 오늘일
																									// 경우
					// 반응 안함
				} else {
					mgr.sendMsg("admin", mgb);
					mgr.petMedic(pb.getPet_id(), currentDay.format(formatter), "혼합 백신");
				}
			}
		}

		// 15주 후부터 1년 간격으로 알림
		LocalDate yearlyStart = birthday.plusWeeks(15);
		if (!currentDay.isBefore(yearlyStart)) {
			for (int i = 0; i <= 50; i++) { // 최대 50년 동안 알림 (조정 가능)
				LocalDate yearlyReminder = yearlyStart.plusYears(i);
				if (yearlyReminder.equals(currentDay)) {
					MsgBean mgb = new MsgBean();
					mgb.setMsg_title("반려동물 혼합 백신 검진일 안내");
					mgb.setMsg_content("안녕하세요, " + mgr.showOneUserName(StaticData.user_id) + "님!\r\n"
							+ "소중한 반려동물의 건강을 위해 혼합 백신 검진일이 다가왔음을 알려드립니다.\r\n" + "\r\n" + "검진 대상: ["
							+ pb.getPet_name() + "]\r\n" + "예정일: [" + yearlyReminder.format(formatter) + "]\r\n"
							+ "\r\n" + "정기적인 백신 접종은 우리 아이의 건강을 지키는 가장 좋은 방법입니다. 잊지 말고 가까운 동물병원을 방문해 주세요! ");
					mgb.setReceiver_id(StaticData.user_id);
					if (mgr.isPetMedic(pb.getPet_id(), "혼합 백신").equals(currentDay.format(formatter))) { // 혼합 백신
																										// 알림을
																										// 보낸게
																										// 오늘일
																										// 경우
						// 반응 안함
					} else {
						mgr.sendMsg("admin", mgb);
						mgr.petMedic(pb.getPet_id(), currentDay.format(formatter), "혼합 백신");
					}
				}
			}
		}

		// 코로나

		// 6주 후부터 2주 간격으로 2번 알림
		weeksAfter = 6;
		interval = 2;
		reminderCount = 2;

		for (int i = 0; i < reminderCount; i++) {
			LocalDate reminderDate = birthday.plusWeeks(weeksAfter + (i * interval));
			if (reminderDate.equals(currentDay)) { // 검진일이 오늘이라면
				MsgBean mgb = new MsgBean();
				mgb.setMsg_title("반려동물 코로나 검진일 안내");
				mgb.setMsg_content("안녕하세요, " + mgr.showOneUserName(StaticData.user_id) + "님!\r\n"
						+ "소중한 반려동물의 건강을 위해 코로나 검진일이 다가왔음을 알려드립니다.\r\n" + "\r\n" + "검진 대상: [" + pb.getPet_name()
						+ "]\r\n" + "예정일: [" + reminderDate.format(formatter) + "]\r\n" + "\r\n"
						+ "정기적인 백신 접종은 우리 아이의 건강을 지키는 가장 좋은 방법입니다. 잊지 말고 가까운 동물병원을 방문해 주세요! ");
				mgb.setReceiver_id(StaticData.user_id);
				if (mgr.isPetMedic(pb.getPet_id(), "코로나").equals(currentDay.format(formatter))) { // 혼합 백신 알림을
																									// 보낸게 오늘일
																									// 경우
					// 반응 안함
				} else {
					mgr.sendMsg("admin", mgb);
					mgr.petMedic(pb.getPet_id(), currentDay.format(formatter), "코로나");
				}
			}
		}

		// 15주 후부터 1년 간격으로 알림
		yearlyStart = birthday.plusWeeks(15);
		if (!currentDay.isBefore(yearlyStart)) {
			for (int i = 0; i <= 50; i++) { // 최대 50년 동안 알림 (조정 가능)
				LocalDate yearlyReminder = yearlyStart.plusYears(i);
				if (yearlyReminder.equals(currentDay)) {
					MsgBean mgb = new MsgBean();
					mgb.setMsg_title("반려동물 코로나 검진일 안내");
					mgb.setMsg_content("안녕하세요, " + mgr.showOneUserName(StaticData.user_id) + "님!\r\n"
							+ "소중한 반려동물의 건강을 위해 코로나 검진일이 다가왔음을 알려드립니다.\r\n" + "\r\n" + "검진 대상: ["
							+ pb.getPet_name() + "]\r\n" + "예정일: [" + yearlyReminder.format(formatter) + "]\r\n"
							+ "\r\n" + "정기적인 백신 접종은 우리 아이의 건강을 지키는 가장 좋은 방법입니다. 잊지 말고 가까운 동물병원을 방문해 주세요! ");
					mgb.setReceiver_id(StaticData.user_id);
					if (mgr.isPetMedic(pb.getPet_id(), "코로나").equals(currentDay.format(formatter))) { // 혼합 백신
																										// 알림을
																										// 보낸게
																										// 오늘일
																										// 경우
						// 반응 안함
					} else {
						mgr.sendMsg("admin", mgb);
						mgr.petMedic(pb.getPet_id(), currentDay.format(formatter), "코로나");
					}
				}
			}
		}

		// 켄넬코프

		// 10주 후부터 2주 간격으로 2번 알림
		weeksAfter = 10;
		interval = 2;
		reminderCount = 2;

		for (int i = 0; i < reminderCount; i++) {
			LocalDate reminderDate = birthday.plusWeeks(weeksAfter + (i * interval));
			if (reminderDate.equals(currentDay)) { // 검진일이 오늘이라면
				MsgBean mgb = new MsgBean();
				mgb.setMsg_title("반려동물 켄넬코프 검진일 안내");
				mgb.setMsg_content("안녕하세요, " + mgr.showOneUserName(StaticData.user_id) + "님!\r\n"
						+ "소중한 반려동물의 건강을 위해 켄넬코프 검진일이 다가왔음을 알려드립니다.\r\n" + "\r\n" + "검진 대상: ["
						+ pb.getPet_name() + "]\r\n" + "예정일: [" + reminderDate.format(formatter) + "]\r\n"
						+ "\r\n" + "정기적인 백신 접종은 우리 아이의 건강을 지키는 가장 좋은 방법입니다. 잊지 말고 가까운 동물병원을 방문해 주세요! ");
				mgb.setReceiver_id(StaticData.user_id);
				if (mgr.isPetMedic(pb.getPet_id(), "켄넬코프").equals(currentDay.format(formatter))) { // 혼합 백신 알림을
																									// 보낸게 오늘일
																									// 경우
					// 반응 안함
				} else {
					mgr.sendMsg("admin", mgb);
					mgr.petMedic(pb.getPet_id(), currentDay.format(formatter), "켄넬코프");
				}
			}
		}

		// 15주 후부터 1년 간격으로 알림
		yearlyStart = birthday.plusWeeks(15);
		if (!currentDay.isBefore(yearlyStart)) {
			for (int i = 0; i <= 50; i++) { // 최대 50년 동안 알림 (조정 가능)
				LocalDate yearlyReminder = yearlyStart.plusYears(i);
				if (yearlyReminder.equals(currentDay)) {
					MsgBean mgb = new MsgBean();
					mgb.setMsg_title("반려동물 켄넬코프 검진일 안내");
					mgb.setMsg_content("안녕하세요, " + mgr.showOneUserName(StaticData.user_id) + "님!\r\n"
							+ "소중한 반려동물의 건강을 위해 켄넬코프 검진일이 다가왔음을 알려드립니다.\r\n" + "\r\n" + "검진 대상: ["
							+ pb.getPet_name() + "]\r\n" + "예정일: [" + yearlyReminder.format(formatter) + "]\r\n"
							+ "\r\n" + "정기적인 백신 접종은 우리 아이의 건강을 지키는 가장 좋은 방법입니다. 잊지 말고 가까운 동물병원을 방문해 주세요! ");
					mgb.setReceiver_id(StaticData.user_id);
					if (mgr.isPetMedic(pb.getPet_id(), "켄넬코프").equals(currentDay.format(formatter))) { // 혼합 백신
																										// 알림을
																										// 보낸게
																										// 오늘일
																										// 경우
						// 반응 안함
					} else {
						mgr.sendMsg("admin", mgb);
						mgr.petMedic(pb.getPet_id(), currentDay.format(formatter), "켄넬코프");
					}
				}
			}
		}

		// 광견병

		// 14주에 1번 알림
		LocalDate reminderDate = birthday.plusWeeks(14);
		if (reminderDate.equals(currentDay)) { // 검진일이 오늘이라면
			MsgBean mgb = new MsgBean();
			mgb.setMsg_title("반려동물 광견병 검진일 안내");
			mgb.setMsg_content("안녕하세요, " + mgr.showOneUserName(StaticData.user_id) + "님!\r\n"
					+ "소중한 반려동물의 건강을 위해 광견병 검진일이 다가왔음을 알려드립니다.\r\n" + "\r\n" + "검진 대상: [" + pb.getPet_name()
					+ "]\r\n" + "예정일: [" + reminderDate.format(formatter) + "]\r\n" + "\r\n"
					+ "정기적인 백신 접종은 우리 아이의 건강을 지키는 가장 좋은 방법입니다. 잊지 말고 가까운 동물병원을 방문해 주세요! ");
			mgb.setReceiver_id(StaticData.user_id);
			if (mgr.isPetMedic(pb.getPet_id(), "광견병").equals(currentDay.format(formatter))) { // 혼합 백신 알림을 보낸게
																								// 오늘일 경우
				// 반응 안함
			} else {
				mgr.sendMsg("admin", mgb);
				mgr.petMedic(pb.getPet_id(), currentDay.format(formatter), "광견병");
			}
		}

		// 15주 후부터 1년 간격으로 알림
		yearlyStart = birthday.plusWeeks(15);
		if (!currentDay.isBefore(yearlyStart)) {
			for (int i = 0; i <= 50; i++) { // 최대 50년 동안 알림 (조정 가능)
				LocalDate yearlyReminder = yearlyStart.plusYears(i);
				if (yearlyReminder.equals(currentDay)) {
					MsgBean mgb = new MsgBean();
					mgb.setMsg_title("반려동물 광견병 검진일 안내");
					mgb.setMsg_content("안녕하세요, " + mgr.showOneUserName(StaticData.user_id) + "님!\r\n"
							+ "소중한 반려동물의 건강을 위해 광견병 검진일이 다가왔음을 알려드립니다.\r\n" + "\r\n" + "검진 대상: ["
							+ pb.getPet_name() + "]\r\n" + "예정일: [" + yearlyReminder.format(formatter) + "]\r\n"
							+ "\r\n" + "정기적인 백신 접종은 우리 아이의 건강을 지키는 가장 좋은 방법입니다. 잊지 말고 가까운 동물병원을 방문해 주세요! ");
					mgb.setReceiver_id(StaticData.user_id);
					if (mgr.isPetMedic(pb.getPet_id(), "광견병").equals(currentDay.format(formatter))) { // 혼합 백신
																										// 알림을
																										// 보낸게
																										// 오늘일
																										// 경우
						// 반응 안함
					} else {
						mgr.sendMsg("admin", mgb);
						mgr.petMedic(pb.getPet_id(), currentDay.format(formatter), "광견병");
					}
				}
			}
		}

		// 신종플루

		// 14주 후부터 2주 간격으로 2번 알림
		weeksAfter = 14;
		interval = 2;
		reminderCount = 2;

		for (int i = 0; i < reminderCount; i++) {
			reminderDate = birthday.plusWeeks(weeksAfter + (i * interval));
			if (reminderDate.equals(currentDay)) { // 검진일이 오늘이라면
				MsgBean mgb = new MsgBean();
				mgb.setMsg_title("반려동물 신종플루 검진일 안내");
				mgb.setMsg_content("안녕하세요, " + mgr.showOneUserName(StaticData.user_id) + "님!\r\n"
						+ "소중한 반려동물의 건강을 위해 신종플루 검진일이 다가왔음을 알려드립니다.\r\n" + "\r\n" + "검진 대상: ["
						+ pb.getPet_name() + "]\r\n" + "예정일: [" + reminderDate.format(formatter) + "]\r\n"
						+ "\r\n" + "정기적인 백신 접종은 우리 아이의 건강을 지키는 가장 좋은 방법입니다. 잊지 말고 가까운 동물병원을 방문해 주세요! ");
				mgb.setReceiver_id(StaticData.user_id);
				if (mgr.isPetMedic(pb.getPet_id(), "신종플루").equals(currentDay.format(formatter))) { // 혼합 백신 알림을
																									// 보낸게 오늘일
																									// 경우
					// 반응 안함
				} else {
					mgr.sendMsg("admin", mgb);
					mgr.petMedic(pb.getPet_id(), currentDay.format(formatter), "신종플루");
				}
			}
		}

		// 구충제

		// 4주 후부터 2달 간격으로 2번 알림
		int startWeeks = 4;
		int monthInterval = 2;

		for (int i = 0; i < 50; i++) {
			LocalDate extraReminderDate = birthday.plusWeeks(startWeeks).plusMonths(i * monthInterval);
			if (extraReminderDate.equals(currentDay)) { // 검진일이 오늘이라면
				MsgBean mgb = new MsgBean();
				mgb.setMsg_title("반려동물 구충제 투약 안내");
				mgb.setMsg_content("안녕하세요, " + mgr.showOneUserName(StaticData.user_id) + "님!\r\n"
						+ "소중한 반려동물의 건강을 위해 구충제 투약이 다가왔음을 알려드립니다.\r\n" + "\r\n" + "투약 대상: [" + pb.getPet_name()
						+ "]\r\n" + "예정일: [" + extraReminderDate.format(formatter) + "]\r\n" + "\r\n"
						+ "정기적인 백신 접종은 우리 아이의 건강을 지키는 가장 좋은 방법입니다. 잊지 말고 가까운 동물병원을 방문해 주세요! ");
				mgb.setReceiver_id(StaticData.user_id);
				if (mgr.isPetMedic(pb.getPet_id(), "구충제").equals(currentDay.format(formatter))) { // 혼합 백신 알림을
																									// 보낸게 오늘일
																									// 경우
					// 반응 안함
				} else {
					mgr.sendMsg("admin", mgb);
					mgr.petMedic(pb.getPet_id(), currentDay.format(formatter), "구충제");
				}
			}
		}

		// 심장사상충

		// 4주 후부터 1달 간격으로 알림
		startWeeks = 4;
		monthInterval = 1;

		for (int i = 0; i < 50; i++) {
			LocalDate extraReminderDate = birthday.plusWeeks(startWeeks).plusMonths(i * monthInterval);
			if (extraReminderDate.equals(currentDay)) { // 검진일이 오늘이라면
				MsgBean mgb = new MsgBean();
				mgb.setMsg_title("반려동물 심장사상충 치료 안내");
				mgb.setMsg_content("안녕하세요, " + mgr.showOneUserName(StaticData.user_id) + "님!\r\n"
						+ "소중한 반려동물의 건강을 위해 심장사상충 치료일이 다가왔음을 알려드립니다.\r\n" + "\r\n" + "치료 대상: ["
						+ pb.getPet_name() + "]\r\n" + "예정일: [" + extraReminderDate.format(formatter) + "]\r\n"
						+ "\r\n" + "정기적인 백신 접종은 우리 아이의 건강을 지키는 가장 좋은 방법입니다. 잊지 말고 가까운 동물병원을 방문해 주세요! ");
				mgb.setReceiver_id(StaticData.user_id);
				if (mgr.isPetMedic(pb.getPet_id(), "심장사상충").equals(currentDay.format(formatter))) { // 혼합 백신 알림을
																									// 보낸게 오늘일
																									// 경우
					// 반응 안함
				} else {
					mgr.sendMsg("admin", mgb);
					mgr.petMedic(pb.getPet_id(), currentDay.format(formatter), "심장사상충");
				}
			}
		}
	}

	public static void main(String[] args) {
		new LoginScreen();
	}
}
