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
	//Select - Ư�� num img ����� �� ��ȯ
	public String showOneImg(int num) {
		
		DiaryDTO dto = new DiaryDTO();
		
		//DB�� ����
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//sql��
		String sql = "select img from diary where num = ? order by num";
		
		
		//�ļ�
		try {
			pstmt = conn.prepareStatement(sql);
			
			//���
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			//���
			while (rs.next()) {
				
				//rs�� ���
				dto.setImg(rs.getString("img"));				
			}
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		
		return dto.getImg();
	}
	
	
	////////////////////////////////////////////
	//Select - Ư�� num id �� ��ȯ
	public String showOneId(int num) {
		
		DiaryDTO dto = new DiaryDTO();
		
		//DB�� ����
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//sql��
		String sql = "select id from diary where num = ?";
		
		//�ļ�
		try {
			pstmt = conn.prepareStatement(sql);
			
			//���
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			//���
			while (rs.next()) {
				
				//rs�� ���
				dto.setId(rs.getString("id"));				
			}
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		
		return dto.getId();
	}	
	
	
	////////////////////////////////////////////
	//Select - 1�� ���̺� - DTO ��ȯ
	public DiaryDTO showOneTable(int num) {
		
		//���� ��� dto �����ϱ� ���� ��ü ����
		DiaryDTO dto = new DiaryDTO();
		
		//DB�� ����
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//sql��
		String sql = "select * from diary where num=? order by num";
		
		
		//�ļ�
		try {
			pstmt = conn.prepareStatement(sql);
			
			//�Ҵ�
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			//���
			while (rs.next()) {
								
				//rs�� ���
				dto.setId(rs.getString("id"));
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));				
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setImg(rs.getString("img"));
				dto.setDay(rs.getDate("day"));
								
			}
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		
		return dto;
		
	}
	
	
	
	////////////////////////////////////////////
	//Insert
	public void insertDiary(DiaryDTO dto, String id) {
		
		//writer ȣ��
		JoinModel joinModel = new JoinModel();
		
		//DCL�� �⺻ ��ɵ��� �־���� ���̱⿡ ������ �ʼ�
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		
		//sql
		String sql = "insert into diary values(?,seq_diary.nextval,?,?,?,?,sysdate)";
		
		
		//�ļ�
		try {
			pstmt = conn.prepareStatement(sql);
			
			//DTO ��ü�� �̹� �޾����Ƿ� ���� ������ �ʿ����
			
			//Join �𵨿��� �г��Ӱ� ���̵� ��������
			String writer = joinModel.showName(id);
			
			//�˻��-��� ���� �ҽǵƴ��� �˾ƺ�����
			System.out.println(id);
			System.out.println(writer);
			
			//�� ���
			pstmt.setString(1, id);
			pstmt.setString(2, writer);
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getImg());

			//������Ʈ
			pstmt.execute();
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(pstmt, conn);
		}
		
	}
	
	
	///////////////////////////////////////////////////////
	//Update
	public void updateDiary(DiaryDTO dto, int num) {
		
		//DB�� ����
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		
		//sql��
		//������Ʈ�Ǵ� ����鸸 �ۼ����ָ� �ȴ�.
		String sql = "update diary set title=?, content=?, img=? where num=?";
		
		//���
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getImg());
			pstmt.setInt(4, num);
			
			//����
			pstmt.execute();
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(pstmt, conn);
		}
	}
	
	
	//////////////////////////////////////////////////////
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
