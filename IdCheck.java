package DiaryProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import quiz.DbConn;

class IdChcek{
	
	//////////////////////////////////////////////
	//ID ���� + ���ڸ� �Է¹ޱ�
	//String ���� ������ �ϳ��ϳ��� Ȯ�ιޱ�
	//��� ��� ����ȭ ��Ģ�� ���� Ŭ���� ���� ����
	public boolean checkIdOnlyNumberAndAlphabet(String id) {
	
		char chrInput;
	
		for (int i = 0; i < id.length(); i++) {
	
		// �Է¹��� �ؽ�Ʈ���� ���� �ϳ��ϳ� �����ͼ� üũ
		chrInput = id.charAt(i); 
	
		if (chrInput >= 0x61 && chrInput <= 0x7A) {
		    // ����(�ҹ���) OK!
		} 
		else if (chrInput >=0x41 && chrInput <= 0x5A) {
		    // ����(�빮��) OK!
		}
		else if (chrInput >= 0x30 && chrInput <= 0x39) {
		    // ���� OK!
		} 
		else {
		    return false;   // �����ڵ� �ƴϰ� ���ڵ� �ƴ�!
		}
	
	}
		return true;
	
	}


	//////////////////////////////////////////
	//ID �ߺ�üũ
	//id���� �޾� DTO�� ���� ��ȯ
	public int getidCheck(String id){
		
		//DTO�� �������� ���� ����Ŭ ���� �ʿ�
		DbConn db = new DbConn();
								
		//�⺻
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		//sql
		String sql = "select id from join where id = ?";
		
		//�ߺ��� id�� �ִ��� �˻��ϱ� ���� ����
		int cnt = 0;
		
		//�ļ�
		try {
			pstmt = conn.prepareStatement(sql);
			
			//��ġ�� ����
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			//���� ���� ������ ���(id�� �ߺ��� ���) cnt�� �� ����
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
		
		