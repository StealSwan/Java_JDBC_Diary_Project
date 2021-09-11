package DiaryProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import quiz.DbConn;

public class Search extends JFrame{

	DbConn db = new DbConn();
	
	
	////////////////////////////////////////
	public Vector<DiaryDTO> search(DefaultTableModel model, String writer) {
		
		Vector<DiaryDTO> list = new Vector<DiaryDTO>();
		
		//JDBC 연결하기
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		//전달받은 String 값으로 검색
		String sql = "select * from diary where writer=? order by num";
		
		
		//후속
		try {
			pstmt = conn.prepareStatement(sql);
			
			//할당
			pstmt.setString(1, writer);
			
			rs = pstmt.executeQuery();
			
			//출력문
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
}


	

