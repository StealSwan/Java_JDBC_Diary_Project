package DiaryProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import quiz.DbConn;

public class DiaryModel {

	//�⺻ ����
	DbConn db = new DbConn();
	
	
	////////////////////////////////////////////
	//����ȭ�� Select - DTO�� �� �־���� Vector�� ����
	public Vector<DiaryDTO> showAllTable() {
		
		Vector<DiaryDTO> list = new Vector<DiaryDTO>();
		
		//DB�� ����
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//sql��
		String sql = "select * from diary order by num";
		
		
		//�ļ�
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//���
			while (rs.next()) {
				
				//dto�� ����ֱ� - �׷��� ���� ��ü ����
				DiaryDTO dto = new DiaryDTO();
				
				//rs�� ���
				dto.setId(rs.getString("id"));
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));				
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setImg(rs.getString("img"));
				dto.setDay(rs.getDate("day"));
				
				//Vector�� ���ϱ�
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
