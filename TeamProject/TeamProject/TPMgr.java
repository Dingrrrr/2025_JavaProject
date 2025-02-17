package TeamProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Vector;

public class TPMgr {
	
	private DBConnectionMgr pool;
	
	public TPMgr() {
		try {
			pool = DBConnectionMgr.getInstance();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//회원가입
	public boolean register(UserBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "insert user values(?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUser_id());
			pstmt.setString(2, bean.getUsername());
			pstmt.setString(3, bean.getPassword());
			pstmt.setString(4, bean.getEmail());
			pstmt.setString(5, bean.getPhone());
			pstmt.setString(6, "");
			int cnt =pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	//아이디 중복 체크
	public boolean idChk(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select user_id from user where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			flag = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return !flag;	//중복되지 않으면 true 반환, 중복이 있으면 false 반환
	}
	
	//로그인
	public boolean loginChk(String user_id, String pwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select user_id from user where user_id = ? and password = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			flag = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}
	
	//접속 시작
	public void userIn(String user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "insert id_check values(?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	//동시 접속 확인(이미 접속해있으면 true)
	public boolean userCheck(String user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select * from id_check where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next())
				flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}
	
	//접속 끊음
	public void userOut(String user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "delete from id_check where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	//사용자 이름
	public String userName(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String name = "";
		try {
			con = pool.getConnection();
			sql = "select username from user where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next())
				name = rs.getString("username");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return name;
	}
	
	//사용자 정보 출력
	public UserBean showUser(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		UserBean bean = new UserBean();
		try {
			con = pool.getConnection();
			sql = "select * from user where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bean.setUser_id(rs.getString("user_id"));
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
				bean.setEmail(rs.getString("email"));
				bean.setPhone(rs.getString("phone"));
				bean.setUser_image(rs.getString("user_image"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
	}
	
	//유저 프로필 수정
	public boolean userUpd(String user_id, UserBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update user set username = ?, password = ?, email = ?, phone = ?, user_image = ? where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUsername());
			pstmt.setString(2, bean.getPassword());
			pstmt.setString(3, bean.getEmail());
			pstmt.setString(4, bean.getPhone());
			pstmt.setString(5, bean.getUser_image());
			pstmt.setString(6, user_id);	//id를 매개변수로 받아 업데이트
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)	//수정 성공
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	//반려동물 정보 추가(필수 사항)
	public void addPet(String user_id, PetBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "insert Pet values(null, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);	//매개변수로 유저 아이디를 받음
			pstmt.setString(2, bean.getPet_name());
			pstmt.setString(3, bean.getPet_species());
			pstmt.setString(4, bean.getPet_age());
			pstmt.setString(5, bean.getPet_gender());
			pstmt.setString(6, bean.getPet_image());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	//반려동물 정보 수정(필수 사항)
	public boolean updPet(int pet_id, PetBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update pet set pet_name = ?, pet_species = ?, pet_age = ?, pet_gender = ?, pet_image = ? where pet_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getPet_name());
			pstmt.setString(2, bean.getPet_species());
			pstmt.setString(3, bean.getPet_age());
			pstmt.setString(4, bean.getPet_gender());
			pstmt.setString(5, bean.getPet_image());
			pstmt.setInt(6, pet_id);
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	//반려동물 정보 삭제(필수 사항)
	public boolean delPet(int pet_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "delete from pet where pet_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pet_id);
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	//반려동물 정보 화면에 출력(필수 사항)
	public Vector<PetBean> showPet(String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<PetBean> vlist = new Vector<PetBean>();
		try {
			con = pool.getConnection();
			sql = "select * from pet where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				PetBean bean = new PetBean();
				bean.setPet_id(rs.getInt("pet_id"));
				bean.setPet_name(rs.getString("pet_name"));
				bean.setPet_species(rs.getString("pet_species"));
				bean.setPet_age(rs.getString("pet_age"));
				bean.setPet_gender(rs.getString("pet_gender"));
				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	//반려동물 아이디 출력
	public int showPetId(String id, PetBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int pet_id = -1;
		try {
			con = pool.getConnection();
			sql = "select pet_id from pet where user_id = ? and pet_name = ? and pet_species = ? and pet_age = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, bean.getPet_name());
			pstmt.setString(3, bean.getPet_species());
			pstmt.setString(4, bean.getPet_age());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pet_id = rs.getInt("pet_id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return pet_id;
	}
	
	//반려동물를 이미 추가했는지 여부(있으면 true 출력)
	public boolean isPet(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select * from pet where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next())
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}
	
	//반려동물 한마리의 정보 출력
	public PetBean showOnePet(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		PetBean bean = new PetBean();
		try {
			con = pool.getConnection();
			sql = "select * from pet where pet_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setPet_name(rs.getString("pet_name"));
				bean.setPet_species(rs.getString("pet_species"));
				bean.setPet_age(rs.getString("pet_age"));
				bean.setPet_gender(rs.getString("pet_gender"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
	}
	
	//개 품종 출력
	public Vector<DogBean> showDog() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<DogBean> vlist = new Vector<DogBean>();
		try {
			con = pool.getConnection();
			sql = "select * from dog_species";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DogBean bean = new DogBean();
				bean.setDog(rs.getString("dog"));
				vlist.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	//개 품종 검색 출력
	public Vector<DogBean> showSearchDog(String dogSearch){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<DogBean> vlist = new Vector<DogBean>();
		try {
			con = pool.getConnection();
			sql = "select * from dog_species where dog like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dogSearch);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DogBean bean = new DogBean();
				bean.setDog(rs.getString("dog"));
				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	//고양이 품종 출력
	public Vector<CatBean> showCat() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<CatBean> vlist = new Vector<CatBean>();
		try {
			con = pool.getConnection();
			sql = "select * from cat_species";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CatBean bean = new CatBean();
				bean.setCat(rs.getString("cat"));
				vlist.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	//반려동물 정보 추가(선택 사항)
	public void addHRPet(int pet_id, HRBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "insert health_record values(null, ?, ?, ?, ?, ?, ?, ?, now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pet_id);
			pstmt.setBigDecimal(2, bean.getWeight());
			pstmt.setBigDecimal(3, bean.getHeight());
			pstmt.setString(4, bean.getMedical_history());
			pstmt.setString(5, bean.getVaccination_status());
			pstmt.setString(6, bean.getCheckup_status());
			pstmt.setString(7, bean.getDate());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	//반려동물 정보 수정(선택 사항)
	public boolean updHRPet(int record_id, HRBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update health_record set weight = ?, height = ?, medical_history = ?, vaccination_status = ?, checkup_status = ?, date = ? where record_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setBigDecimal(1, bean.getWeight());
			pstmt.setBigDecimal(2, bean.getHeight());
			pstmt.setString(3, bean.getMedical_history());
			pstmt.setString(4, bean.getVaccination_status());
			pstmt.setString(5, bean.getCheckup_status());
			pstmt.setString(6, bean.getDate());
			pstmt.setInt(7, record_id);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	//반려동물 정보 삭제(선택 사항)
	public boolean delHRPet(int record_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "delete from health_record where record_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, record_id);
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	//반려동물 레코드 아이디를 출력(선택 사항)
	public int showHRPetId(Timestamp ts) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int record_id = -1;
		try {
			con = pool.getConnection();
			sql = "select record_id from health_record where hr_date = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setTimestamp(1, ts);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				record_id = rs.getInt("record_id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return record_id;
	}
	
	//한 반려동물의 정보를 출력(선택 사항)
	public HRBean showOneHRPet(int record_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		HRBean bean = new HRBean();
		try {
			con = pool.getConnection();
			sql = "select * from health_record where record_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, record_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setHeight(rs.getBigDecimal("height"));
				bean.setWeight(rs.getBigDecimal("weight"));
				bean.setMedical_history(rs.getString("medical_history"));
				bean.setVaccination_status(rs.getString("vaccination_status"));;
				bean.setCheckup_status(rs.getString("checkup_status"));
				bean.setDate(rs.getString("date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
	}
	
	
	//반려동물 정보 화면에 출력(선택 사항)
	public Vector<HRBean> showHRPet(int pet_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<HRBean> vlist = new Vector<HRBean>();
		try {
			con = pool.getConnection();
			sql = "select * from health_record where pet_id = ? ORDER BY hr_date DESC;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pet_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HRBean bean = new HRBean();
				bean.setRecord_id(rs.getInt("record_id"));
				bean.setHr_date(rs.getTimestamp("hr_date"));
				bean.setHeight(rs.getBigDecimal("height"));
				bean.setWeight(rs.getBigDecimal("weight"));
				bean.setMedical_history(rs.getString("medical_history"));
				bean.setVaccination_status(rs.getString("vaccination_status"));
				bean.setCheckup_status(rs.getString("checkup_status"));
				bean.setDate(rs.getString("date"));
				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	//앨범 존재 유무(이미 앨범이 있으면 true 출력)
	public boolean isAlbum(int pet_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select * from album where pet_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pet_id);
			rs = pstmt.executeQuery();
			if(rs.next())
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}
	
	//앨범 추가
	public void addAlbum(int pet_id, AlbumBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "insert album values(null, ?, ?, ?, ?, now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pet_id);
			pstmt.setString(2, bean.getAlbum_image());
			pstmt.setString(3, bean.getAlbum_desc());
			pstmt.setString(4, bean.getAlbum_tags());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	//앨범 수정
	public boolean updAlbum(int album_id, AlbumBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update album set album_image = ?, album_desc = ?, album_tags = ? where album_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getAlbum_image());
			pstmt.setString(2, bean.getAlbum_desc());
			pstmt.setString(3, bean.getAlbum_tags());
			pstmt.setInt(4, album_id);
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	//앨범 삭제
	public boolean delAlbum(int album_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "delete from album where album_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, album_id);
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	//앨범 화면에 출력
	public Vector<AlbumBean> showAlbum(int pet_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<AlbumBean> vlist = new Vector<AlbumBean>();
		try {
			con = pool.getConnection();
			sql = "select * from album where pet_id = ? ORDER BY album_date DESC;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pet_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AlbumBean bean = new AlbumBean();
				bean.setAlbum_id(rs.getInt("album_id"));
				bean.setAlbum_image(rs.getString("album_image"));
				bean.setAlbum_tags(rs.getString("album_tags"));
				bean.setAlbum_date(rs.getTimestamp("album_date"));
				bean.setAlbum_desc(rs.getString("album_desc"));
				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	//일기가 존재 유무(이미 일기가 있으면 true 반환)
	public boolean isDiary(int pet_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select * from diary where pet_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pet_id);
			rs = pstmt.executeQuery();
			if(rs.next())
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}
	
	//일기 추가
	public void addDiary(int pet_id, DiaryBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "insert diary values(null, ?, ?, now(), ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getDiary_name());
			pstmt.setInt(2, pet_id);
			pstmt.setString(3, bean.getDiary_content());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	//일기 수정
	public boolean updDiary(int diary_id, DiaryBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update diary set diary_name = ?, diary_content = ? where diary_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getDiary_name());
			pstmt.setString(2, bean.getDiary_content());
			pstmt.setInt(3, diary_id);
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	//일기 삭제
	public boolean delDiary(int diary_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "delete from diary where diary_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, diary_id);
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	//일기 화면에 출력
	public Vector<DiaryBean> showDiary(int pet_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<DiaryBean> vlist = new Vector<DiaryBean>();
		try {
			con = pool.getConnection();
			sql = "select * from diary where pet_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pet_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DiaryBean bean = new DiaryBean();
				bean.setDiary_id(rs.getInt("diary_id"));
				bean.setDiary_date(rs.getTimestamp("diary_date"));
				bean.setDiary_name(rs.getString("diary_name"));
				bean.setDiary_content(rs.getString("diary_content"));
				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	//일기 한개 출력
	public DiaryBean showOneDiary(int diary_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		DiaryBean bean = new DiaryBean();
		try {
			con = pool.getConnection();
			sql = "select * from diary where diary_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, diary_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setDiary_name(rs.getString("diary_name"));
				bean.setDiary_content(rs.getString("diary_content"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
	}
	
	//커뮤니티 추가
	public void addComu(String user_id, ComuBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "insert comu_post values(null, ?, ?, ?, now(), ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, bean.getComu_title());
			pstmt.setString(3, bean.getComu_content());
			pstmt.setString(4, bean.getComu_image());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	//커뮤니티 수정
	public boolean updComu(int post_id, ComuBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update comu_post set comu_title = ?, comu_content = ?, comu_image = ? where post_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getComu_title());
			pstmt.setString(2, bean.getComu_content());
			pstmt.setString(3, bean.getComu_image());
			pstmt.setInt(4, post_id);
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	//커뮤니티 삭제
	public boolean delComu(int post_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "delete from comu_post where post_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, post_id);
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	//커뮤니티 화면에 출력
	public Vector<ComuBean> showComu(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<ComuBean> vlist = new Vector<ComuBean>();
		try {
			con = pool.getConnection();
			sql = "select * from comu_post order by comu_date desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ComuBean bean = new ComuBean();
				bean.setPost_id(rs.getInt("post_id"));
				bean.setUser_id(rs.getString("user_id"));
				bean.setComu_date(rs.getTimestamp("comu_date"));
				bean.setComu_image(rs.getString("comu_image"));
				bean.setComu_title(rs.getString("comu_title"));
				bean.setComu_content(rs.getString("comu_content"));
				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	
	//커뮤니티 댓글 추가
	public void addCmt(int post_id, String user_id, String cmt) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "insert cmt values(null, ?, ?, ?, now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, post_id);
			pstmt.setString(2, user_id);
			pstmt.setString(3, cmt);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	//커뮤니티 댓글 수정
	public boolean updCmt(int cmt_id, String cmt) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update cmt set cmt_content = ? where cmt_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cmt);
			pstmt.setInt(2, cmt_id);
			int cnt =pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	//커뮤니티 댓글 삭제
	public boolean delCmt(int cmt_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "delete from cmt where cmt_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cmt_id);
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	//커뮤니티 댓글 화면에 출력
	public Vector<CmtBean> showCmt(int post_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<CmtBean> vlist = new Vector<CmtBean>();
		try {
			con = pool.getConnection();
			sql = "select * from cmt where post_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, post_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CmtBean bean = new CmtBean();
				bean.setUser_id(rs.getString("user_id"));
				bean.setCmt_content(rs.getString("cmt_content"));
				bean.setCmt_date(rs.getTimestamp("cmt_date"));
				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	//투표 추가(수정 필요)
	public void addVote(String user_id, int pet_id, String image) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "insert vote values(null, ?, ?, 0, ?, now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, pet_id);
			pstmt.setString(3, image);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	//투표 수정
	public boolean updVote(String image, int vote_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update vote set vote_image = ? where vote_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, image);
			pstmt.setInt(2, vote_id);
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	//투표 삭제
	public boolean delVote(int vote_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "delete from vote where vote_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vote_id);
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	//투표 화면에 출력(최신순이 디폴트)
	public Vector<VoteBean> showVote(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<VoteBean> vlist = new Vector<VoteBean>();
		try {
			con = pool.getConnection();
			sql = "select * from vote order by vote_date desc";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VoteBean bean = new VoteBean();
				bean.setVote_id(rs.getInt("vote_id"));
				bean.setUser_id(rs.getString("user_id"));
				bean.setVote_image(rs.getString("vote_image"));
				bean.setVote_like(rs.getInt("vote_like"));
				bean.setVote_date(rs.getTimestamp("vote_date"));
				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	//투표 인기순 정렬
	public Vector<VoteBean> popVote() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<VoteBean> vlist = new Vector<VoteBean>();
		try {
			con = pool.getConnection();
			sql = "select * from vote order by vote_like desc";	//vote_like 컬럼을 기준으로 내림차순
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				VoteBean bean = new VoteBean();
				bean.setVote_id(rs.getInt("vote_id"));
				bean.setUser_id(rs.getString("user_id"));
				bean.setVote_image(rs.getString("vote_image"));
				bean.setVote_like(rs.getInt("vote_like"));
				bean.setVote_date(rs.getTimestamp("vote_date"));
				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	//투표 최신순 정렬
	public Vector<VoteBean> newVote(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<VoteBean> vlist = new Vector<VoteBean>();
		try {
			con = pool.getConnection();
			sql = "select * from vote order by vote_date desc";	//vote_date 컬럼을 기준으로 내림차순
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				VoteBean bean = new VoteBean();
				bean.setVote_id(rs.getInt("vote_id"));
				bean.setUser_id(rs.getString("user_id"));
				bean.setVote_image(rs.getString("vote_image"));
				bean.setVote_like(rs.getInt("vote_like"));
				bean.setVote_date(rs.getTimestamp("vote_date"));
				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	//투표 오래된순 정렬
	public Vector<VoteBean> oldVote(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<VoteBean> vlist = new Vector<VoteBean>();
		try {
			con = pool.getConnection();
			sql = "select * from vote order by vote_date asc";	//vote_date 컬럼을 기준으로 내림차순
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				VoteBean bean = new VoteBean();
				bean.setVote_id(rs.getInt("vote_id"));
				bean.setUser_id(rs.getString("user_id"));
				bean.setVote_image(rs.getString("vote_image"));
				bean.setVote_like(rs.getInt("vote_like"));
				bean.setVote_date(rs.getTimestamp("vote_date"));
				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	//투표 좋아요
	public void likeVote(int vote_id, String user_id) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = null;

	    try {
	        con = pool.getConnection();
	        con.setAutoCommit(false);  // 트랜잭션 시작
	    
	        
	        // 1. 현재 투표 좋아요 개수 가져오기
	        sql = "SELECT vote_like FROM vote WHERE vote_id = ?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, vote_id);
	        rs = pstmt.executeQuery();
	        
	        int vote_like = 0;
	        if (rs.next()) {
	        	vote_like = rs.getInt("vote_like");
	        }else {
	        	System.out.println("❌ vote_id " + vote_id + " 에 해당하는 데이터가 없습니다.");
	        	return;
	        }

	        // 2. 좋아요 개수 증가
	        sql = "UPDATE vote SET vote_like = ? WHERE vote_id = ?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, vote_like + 1);
	        pstmt.setInt(2, vote_id);
	        pstmt.executeUpdate();
	        pstmt.close();

	        // 3. 투표한 사용자 정보 저장
	        sql = "INSERT INTO vote_mgr (vote_id, vt_user_id) VALUES (?, ?)";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, vote_id);
	        pstmt.setString(2, user_id);
	        pstmt.executeUpdate();
	        pstmt.close();
	        
	        // 4. 투표 올린 사용자에게 알람 전송
	        if (!user_id.equals(user_id)) { // 자신이 올린 투표에 좋아요하면 알람 안보냄
	            sql = "INSERT INTO msg (msg_title, sender_id, receiver_id, msg_content, msg_date) VALUES (?, ?, ?, ?, NOW())";
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, "새로운 좋아요!"); // 알람 제목
	            pstmt.setString(2, user_id); // 좋아요를 누른 사용자
	            pstmt.setString(3, user_id); // receiver_id = 투표 올린 사람 (vote 테이블의 user_id)
	            pstmt.setString(4, user_id + "님이 당신의 투표에 좋아요를 눌렀습니다!"); // 알람 내용
	            pstmt.executeUpdate();

	            System.out.println(user_id + "님에게 좋아요 알람이 전송되었습니다.");
	        }

	        // 모든 SQL 실행이 정상적으로 끝났으면 커밋
	        con.commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        try {
	            if (con != null) con.rollback();  // 오류 발생 시 롤백
	        } catch (Exception rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (con != null) {
	                con.setAutoCommit(true); // 다시 기본값으로 변경
	                pool.freeConnection(con);
	            }
	        } catch (Exception closeEx) {
	            closeEx.printStackTrace();
	        }
	    }
	}
	
	//투표 유무(이미 투표했으면 true 반환)
	public boolean alrLikeVote(int vote_id, String user_id) {
		//투표 게시글의 아이디, 투표한 사용자의 아이디
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select * from vote_mgr where vote_id = ? and vt_user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vote_id);
			pstmt.setString(2, user_id);
			rs = pstmt.executeQuery();
			if(rs.next())
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}

	//쪽지
	public boolean sendMsg(String user_id, MsgBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "insert msg values(null, ?, ?, ?, ?, now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getMsg_title());
			pstmt.setString(2, user_id);
			pstmt.setString(3, bean.getReceiver_id());
			pstmt.setString(4, bean.getMsg_content());
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	//알림
	public Vector<MsgBean> showMsgList(String user_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<MsgBean> vlist = new Vector<MsgBean>();
		try {
			con = pool.getConnection();
			sql = "select * from msg where receiver_id = ? order by msg_date desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MsgBean bean = new MsgBean();
				bean.setMsg_id(rs.getInt("msg_id"));
				bean.setSender_id(rs.getString("sender_id"));
				bean.setMsg_title(rs.getString("msg_title"));
				bean.setMsg_content(rs.getString("msg_content"));
				bean.setMsg_date(rs.getTimestamp("msg_date"));
				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	//알림 삭제
	public boolean delMsg(int msg_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "delete from msg where msg_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, msg_id);
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	//알림 하나 출력
	public MsgBean showOneMsg(int msg_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		MsgBean bean = new MsgBean();
		try {
			con = pool.getConnection();
			sql = "select * from msg where msg_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, msg_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setMsg_title(rs.getString("msg_title"));
				bean.setMsg_content(rs.getString("msg_content"));
				bean.setSender_id(rs.getString("sender_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
	}
	
	
	
	public static void main(String[] args) {
		TPMgr tpm = new TPMgr();
	}
}
