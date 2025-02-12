package member2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

// 테이블 명 + Mgr
public class ZipcodeMgr {
	
	private DBConnectionMgr pool;
	
	public ZipcodeMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	
	//우편번호 검색
	public Vector<ZipcodeBean> searchZipcode(String area3) {
		Connection con = null;
		//SQL문 생성하는 객체
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<ZipcodeBean> vlist = new Vector<ZipcodeBean>();
		try {
			con = pool.getConnection();
			sql = "select * from tblzipcode where area3 like ?"; // ? <- 쿼리문을 동적인 매게변수로 받는 것이 ?
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1/*첫번째 ?*/, area3+"%"); // like '강남대로%'
			rs/*테이블 데이터를 가르킴*/ = pstmt.executeQuery(); // 실제 DB 서버 실행 후 결과값 받아옴
			while(rs.next()) {
				ZipcodeBean bean = new ZipcodeBean();
				bean.setZipcode(rs.getString("zipcode"/*컬럼 명*/));
				bean.setArea1(rs.getString("area1"));
				bean.setArea2(rs.getString("area2"));
				bean.setArea3(rs.getString("area3"));
				//빈즈를 Vector 반드시 저장
				vlist.addElement(bean);
			}
			System.out.println("size : " + vlist.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	public static void main(String[] args) {
		ZipcodeMgr mgr = new ZipcodeMgr();
		mgr.searchZipcode("광평로");
		mgr.searchZipcode("강남대로");
	}
}
