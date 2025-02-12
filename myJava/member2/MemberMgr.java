package member2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class MemberMgr {

	private DBConnectionMgr pool;

	public MemberMgr() {
		try {
			// Connection 객체를 Vector 미리 10개 만듬
			pool = DBConnectionMgr.getInstance();
			// System.out.println("성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// DB 연동 잘되는지 테스트하는 메소드, 실제 코드에서 사용되지 않음.
	public void selectCnt() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "select count(*) from tblMember";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt(1);
				System.out.println("cnt : " + cnt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

	// DML : Data Manipulation Language
	// 저장 : insert, post () - db2
	public boolean insertMember(MemberBean bean) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				// num은 자동 증가이기 때문에 null을 세팅
				// 나머지 값은 동적으로 세팅해야 하기 때문에 ?로 세팅
				sql = "insert tblMember values (null, ?, ?, ?, ?)";
				pstmt = con.prepareStatement(sql);
				// 1은 첫번째 ?를 의미
				pstmt.setString(1, bean.getName()); //null, '홍길동', 
				pstmt.setString(2, bean.getPhone());
				pstmt.setString(3, bean.getAddress());
				pstmt.setString(4, bean.getTeam());
				// 보통의 경우에는 insert는 성공이면 1 실패면 0이 리턴됨.
				int cnt /*적용된 레코드 개수*/ = pstmt.executeUpdate();
				if(cnt==1) flag = true;

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
			return flag;
		}

	// 리스트 - db1
	public Vector<MemberBean> listMember() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<MemberBean> vlist = new Vector<MemberBean>();
		try {
			con = pool.getConnection();
			sql = "select * from tblMember";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); //DB 실행
			while(rs.next()/*리턴되는 레코드 개수는 몇개인지 모름*/) {
				//현재 커서가 있는 위치에 컬럼의 값을 저장하기 위한 Beans 생성
				MemberBean bean = new MemberBean();
				bean.setNum(rs.getInt("num"/*컬럼 명*/)); //리턴받은 num값을 Beans에 저장
				bean.setName(rs.getString("name"));
				bean.setPhone(rs.getString("phone"));
				bean.setAddress(rs.getString("address"));
				bean.setTeam(rs.getString("team"));
				//마지막에 beans를 벡터에 저장
				vlist.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	// 중복된 값 찾기 - db2
	public boolean duplicationPhone(String phone) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "SELECT COUNT(*) FROM tblMember WHERE phone = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, phone);
			rs = pstmt.executeQuery();
			if(rs.next() && rs.getInt(1) > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	// 레코드 한 개 - db1
	public MemberBean getMember(int num/*어떤 레코드를 리턴할지 조건으로 들어가는 값*/) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		MemberBean bean = new MemberBean();
		try {
			con = pool.getConnection();
			sql = "select * from tblMember where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num); //첫 번째 물음표에 매개변수 num 세팅
			rs = pstmt.executeQuery();
			//num은 PK이므로 2개 이상의 레코드는 절대 리턴 될 수 없음.
			if(rs.next()) {
				bean.setNum(rs.getInt(1)); //매개변수 정수는 컬럼의 index 세팅
				bean.setName(rs.getString(2));
				bean.setPhone(rs.getString(3));
				bean.setAddress(rs.getString(4));
				bean.setTeam(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
	}
	
	// 수정 - db2
	public boolean updateMember(MemberBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update tblMember set name=?, phone=?, address=?, team=?" + "where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getPhone());
			pstmt.setString(3, bean.getAddress());
			pstmt.setString(4, bean.getTeam());
			pstmt.setInt(5, bean.getNum());
			int cnt = pstmt.executeUpdate();
			if(cnt==1) flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	// 삭제 - db2
	public boolean deleteMember(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "delete from tblMember where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			if(pstmt.executeUpdate()==1) flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	// 팀 이름 리스트 (중복 제거) - db1
	public Vector<String> getTeamList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<String> vlist = new Vector<String>();
		try {
			con = pool.getConnection();
			sql = "select distinct team from tblMember";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//리턴과 동시에 바로 Vector에 저장
				vlist.addElement(rs.getString(1)); // 1은 team을 뜻함.
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	public static void main(String[] args) {
		MemberMgr mgr = new MemberMgr();
		//mgr.selectCnt();
		Vector<MemberBean> vlist = mgr.listMember();
		System.out.println(vlist.size());
	}

}
