package DiaryProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import quiz.DbConn;
import quiz0908.HelloDTO;

public class JoinModel {
	
	DbConn db = new DbConn();
	
	////////////////////////////////////////////
	//회원가입할 경우, 값을 Join 테이블에 저장
	//입력한 DTO를 인자값으로 전달받는다
	public void insertJoin(JoinDTO dto) {
		
		//DCL의 기본 기능들을 넣어놓는 것이기에 접속은 필수
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		
		//sql
		String sql = "insert into join values(?,?,?,?,?)";
		
		
		//후속
		try {
			pstmt = conn.prepareStatement(sql);
			
			//DTO 객체는 이미 받았으므로 따로 생성할 필요없음
			
			//값 배분
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getPhone());
			
			//업데이트
			pstmt.execute();
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(pstmt, conn);
		}
		
	}
	
	
	/////////////////////////////////////////////
	//Select
	//다 필요없고 Main02서 유저 이름 호출용
	public String showName(String id) {
		
		JoinDTO dto = new JoinDTO();
		
		//JoinDTO 값을 먹는 Vector
		Vector<JoinDTO> list = new Vector<JoinDTO>();
		
		//DCL의 기본 기능들을 넣어놓는 것이기에 접속은 필수
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//sql
		String sql = "Select name from join where id = ?";
		
		
		//후속
		try {
			pstmt = conn.prepareStatement(sql);
			
			//배치
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {

				//DTO에 넣어주기
				dto.setName(rs.getString("name"));
			}
						
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		
		//여기서 id값을 리턴하지 않으면 사라짐?
		return dto.getName();
	}
	

	/////////////////////////////////////////////	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
