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
	public void insertDiary(DiaryDTO dto) {
		
		//DCL�� �⺻ ��ɵ��� �־���� ���̱⿡ ������ �ʼ�
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		
		//sql
		String sql = "insert into diary values(?,seq_diary.nextval,?,?,?,?,sysdate)";
		
		
		//�ļ�
		try {
			pstmt = conn.prepareStatement(sql);
			
			//DTO ��ü�� �̹� �޾����Ƿ� ���� ������ �ʿ����
			
			//�� ���
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			
			//������Ʈ
			pstmt.execute();
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(pstmt, conn);
		}
		
	}
	
	
	
	////////////////////////////////////////////
	//Delete
	//�������� �ѹ����� ���ڰ����� �Է¹޴´�
	//���� �����Ǿ����� �ƴ��� ���θ� int������ �����Ѵ�.
	public void deleteDiary(int num) {
		
		//�⺻
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		
		//sql
		String sql = "delete from diary where num = ?";
				
		//�ļ�
		try {
			pstmt = conn.prepareStatement(sql);
			
			//�� �Ҵ�
			pstmt.setInt(1, num);
			
			//������Ʈ�� ���ÿ� ���� �Ҵ�
			pstmt.execute();
			
			//������� ���θ� �˷��ִ� int ���� ��ȯ
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(pstmt, conn);
		} 
		
	}
	
	
	
	public static void main(String[] args) {
		

	}

}
