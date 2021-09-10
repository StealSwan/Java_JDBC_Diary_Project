package DiaryProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import quiz.DbConn;

public class DiaryModel {

	//기본 접속
	DbConn db = new DbConn();
	
	
	////////////////////////////////////////////
	//메인화면 Select - DTO에 값 넣어놓고 Vector로 뱉어내기
	public Vector<DiaryDTO> showAllTable() {
		
		Vector<DiaryDTO> list = new Vector<DiaryDTO>();
		
		//DB에 접속
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//sql문
		String sql = "select * from diary order by num";
		
		
		//후속
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//출력
			while (rs.next()) {
				
				//dto에 담아주기 - 그러기 위해 객체 생성
				DiaryDTO dto = new DiaryDTO();
				
				//rs값 담기
				dto.setId(rs.getString("id"));
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));				
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setImg(rs.getString("img"));
				dto.setDay(rs.getDate("day"));
				
				//Vector에 더하기
				list.add(dto);
				
			}
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	
	////////////////////////////////////////////
	//Insert
	
	
	
	public static void main(String[] args) {
		

	}

}
