package DiaryProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import quiz.DbConn;
import quiz0908.HelloDTO;

public class Id_Pw_Check {

	//전역변수에 접속 db 호출
	DbConn db = new DbConn();
	
	
	////////////////////////////////////////////////////////////
	//ID와 PW 체크를 위해 Vector를 반환
	//로그인 시 ID, PW 확인 로직
	public int idpwCheck(String id, String pw) {
		
		JoinDTO dto = new JoinDTO();
		
		//DB에서 정보 확인용
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//sql
		String sql = "select id, password from join where id=? and password=?";
		
		//중복 검사용 int 변수
		int state = 0;
		
		
		//후속
		try {
			pstmt = conn.prepareStatement(sql);

			//배치와 확정
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
						
			//읽기
			while (rs.next()) {
				state=1;
			}
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		
		return state;
		
	}
	
}
