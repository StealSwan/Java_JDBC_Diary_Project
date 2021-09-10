package DiaryProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import quiz.DbConn;
import quiz0908.HelloDTO;

public class Id_Pw_Check {

	//���������� ���� db ȣ��
	DbConn db = new DbConn();
	
	
	////////////////////////////////////////////////////////////
	//ID�� PW üũ�� ���� Vector�� ��ȯ
	//�α��� �� ID, PW Ȯ�� ����
	public int idpwCheck(String id, String pw) {
		
		JoinDTO dto = new JoinDTO();
		
		//DB���� ���� Ȯ�ο�
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//sql
		String sql = "select id, password from join where id=? and password=?";
		
		//�ߺ� �˻�� int ����
		int state = 0;
		
		
		//�ļ�
		try {
			pstmt = conn.prepareStatement(sql);

			//��ġ�� Ȯ��
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
						
			//�б�
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
