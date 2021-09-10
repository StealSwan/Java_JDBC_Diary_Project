package DiaryProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import quiz.DbConn;

class IdChcek{
	
	//////////////////////////////////////////////
	//ID 영어 + 숫자만 입력받기
	//String 값을 가져와 하나하나씩 확인받기
	//모듈 기능 세분화 원칙에 따라 클래스 따로 만듦
	public boolean checkIdOnlyNumberAndAlphabet(String id) {
	
		char chrInput;
	
		for (int i = 0; i < id.length(); i++) {
	
		// 입력받은 텍스트에서 문자 하나하나 가져와서 체크
		chrInput = id.charAt(i); 
	
		if (chrInput >= 0x61 && chrInput <= 0x7A) {
		    // 영문(소문자) OK!
		} 
		else if (chrInput >=0x41 && chrInput <= 0x5A) {
		    // 영문(대문자) OK!
		}
		else if (chrInput >= 0x30 && chrInput <= 0x39) {
		    // 숫자 OK!
		} 
		else {
		    return false;   // 영문자도 아니고 숫자도 아님!
		}
	
	}
		return true;
	
	}


	//////////////////////////////////////////
	//ID 중복체크
	//id값을 받아 DTO의 값을 반환
	public int getidCheck(String id){
		
		//DTO를 가져오기 위한 오라클 연결 필요
		DbConn db = new DbConn();
								
		//기본
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		//sql
		String sql = "select id from join where id = ?";
		
		//중복된 id가 있는지 검사하기 위한 변수
		int cnt = 0;
		
		//후속
		try {
			pstmt = conn.prepareStatement(sql);
			
			//배치와 실행
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			//읽을 값이 존재할 경우(id가 중복될 경우) cnt의 값 증가
			while (rs.next()) {
				cnt++;
			}
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		
		return cnt;
	} 
}
		
		