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
	//Select - 특정 num img 저장소 값 반환
	public String showOneImg(int num) {
		
		DiaryDTO dto = new DiaryDTO();
		
		//DB에 접속
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//sql문
		String sql = "select img from diary where num = ? order by num";
		
		
		//후속
		try {
			pstmt = conn.prepareStatement(sql);
			
			//배분
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			//출력
			while (rs.next()) {
				
				//rs값 담기
				dto.setImg(rs.getString("img"));				
			}
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		
		return dto.getImg();
	}
	
	
	////////////////////////////////////////////
	//Select - 특정 num id 값 반환
	public String showOneId(int num) {
		
		DiaryDTO dto = new DiaryDTO();
		
		//DB에 접속
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//sql문
		String sql = "select id from diary where num = ?";
		
		//후속
		try {
			pstmt = conn.prepareStatement(sql);
			
			//배분
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			//출력
			while (rs.next()) {
				
				//rs값 담기
				dto.setId(rs.getString("id"));				
			}
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		
		return dto.getId();
	}	
	
	
	////////////////////////////////////////////
	//Select - 1개 테이블만 - DTO 반환
	public DiaryDTO showOneTable(int num) {
		
		//값이 담긴 dto 전달하기 위해 객체 생성
		DiaryDTO dto = new DiaryDTO();
		
		//DB에 접속
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//sql문
		String sql = "select * from diary where num=? order by num";
		
		
		//후속
		try {
			pstmt = conn.prepareStatement(sql);
			
			//할당
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			//출력
			while (rs.next()) {
								
				//rs값 담기
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
		
		//writer 호출
		JoinModel joinModel = new JoinModel();
		
		//DCL의 기본 기능들을 넣어놓는 것이기에 접속은 필수
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		
		//sql
		String sql = "insert into diary values(?,seq_diary.nextval,?,?,?,?,sysdate)";
		
		
		//후속
		try {
			pstmt = conn.prepareStatement(sql);
			
			//DTO 객체는 이미 받았으므로 따로 생성할 필요없음
			
			//Join 모델에서 닉네임과 아이디 가져오기
			String writer = joinModel.showName(id);
			
			//검산용-어디서 값이 소실됐는지 알아볼거임
			System.out.println(id);
			System.out.println(writer);
			
			//값 배분
			pstmt.setString(1, id);
			pstmt.setString(2, writer);
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getImg());

			//업데이트
			pstmt.execute();
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(pstmt, conn);
		}
		
	}
	
	
	///////////////////////////////////////////////////////
	//Update
	public void updateDiary(DiaryDTO dto, int num) {
		
		//DB에 접속
		Connection conn = db.getLoclaConnection();
		PreparedStatement pstmt = null;
		
		//sql문
		//업데이트되는 내용들만 작성해주면 된다.
		String sql = "update diary set title=?, content=?, img=? where num=?";
		
		//배분
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getImg());
			pstmt.setInt(4, num);
			
			//실행
			pstmt.execute();
			
		} catch (SQLException e) {
			
		} finally {
			db.dbClose(pstmt, conn);
		}
	}
	
	
	//////////////////////////////////////////////////////
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
