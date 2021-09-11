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
	public void insertDiary(DiaryDTO dto) {
		
		//DCL의 기본 기능들을 넣어놓는 것이기에 접속은 필수
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		
		//sql
		String sql = "insert into diary values(?,seq_diary.nextval,?,?,?,?,sysdate)";
		
		
		//후속
		try {
			pstmt = conn.prepareStatement(sql);
			
			//DTO 객체는 이미 받았으므로 따로 생성할 필요없음
			
			//값 배분
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			
			//업데이트
			pstmt.execute();
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(pstmt, conn);
		}
		
	}
	
	
	
	////////////////////////////////////////////
	//Delete
	//삭제받을 넘버값을 인자값으로 입력받는다
	//정상 삭제되었는지 아닌지 여부를 int값으로 리턴한다.
	public void deleteDiary(int num) {
		
		//기본
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		
		//sql
		String sql = "delete from diary where num = ?";
				
		//후속
		try {
			pstmt = conn.prepareStatement(sql);
			
			//값 할당
			pstmt.setInt(1, num);
			
			//업데이트와 동시에 변수 할당
			pstmt.execute();
			
			//정상삭제 여부를 알려주는 int 값을 반환
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(pstmt, conn);
		} 
		
	}
	
	
	
	public static void main(String[] args) {
		

	}

}
