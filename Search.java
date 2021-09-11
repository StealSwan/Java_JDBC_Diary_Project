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
		
		//JDBC �����ϱ�
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		//���޹��� String ������ �˻�
		String sql = "select * from diary where writer=? order by num";
		
		
		//�ļ�
		try {
			pstmt = conn.prepareStatement(sql);
			
			//�Ҵ�
			pstmt.setString(1, writer);
			
			rs = pstmt.executeQuery();
			
			//��¹�
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
}


	

