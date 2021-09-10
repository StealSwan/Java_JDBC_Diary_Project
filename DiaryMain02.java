package DiaryProject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/////////////////////////////////////////
//메인 + Select창 구현
public class DiaryMain02 extends JFrame implements ActionListener{

	Container cp;
	
	JPanel panel;
	
	JLabel background, welcome;
	
	JButton btnAdd, btnDel, btnUpdate;
	
	//테이블 가져오기
	DefaultTableModel model;
	JTable table;
	
	
	//Model
	//뒤에 선언안하면 null값 나온다
	DiaryModel diaryModel = new DiaryModel();
	
	//출력값
	Vector<DiaryDTO> list;
	
	
	//값을 전달 받을 id 객체
	String id = "";
	
	
	//////////////////////////////////////
	public DiaryMain02() {
		
		super("감성 다이어리");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(300, 50, 1000, 700);
		//창 사이즈 변경불가
		this.setResizable(false);
		//디자인 보이게 하기
		initDesign(id);
		this.setVisible(true);
	}
	
	
	/////////////////////////////////////
	//디자인
	public void initDesign(String id) {
		
		//배경 이미지가 들어갈 공간 할당
		panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(null);
		
		
		////////////////////////////////////////////////////////////////////////////////
		//~님 어서오세요 출력
		//ID를 통해 이름 가져오기!!!!
		//!!이거 구현하는데 죽을뻔했음...!!!
		JoinModel joinModel = new JoinModel();
		welcome = new JLabel("별을 노래하는 마음으로 " + joinModel.showName(id) + " 님이 늘 행복하시길!");
		welcome.setBounds(195, 10, 1000, 100);
		welcome.setFont(new Font("나눔손글씨 다시 시작해", Font.BOLD, 40));
		welcome.setForeground(Color.pink);
		panel.add(welcome);
		/////////////////////////////////////////////////////////////////////////////////
		
		
		//테이블 값 더하기
		//여기서 쓰일 Vector DTO 값
		//DTO의 값을 가지고옴
		list = diaryModel.showAllTable();
		
		
		//테이블명 출력
		String [] title = {"No", "작성자", "제목", "일자"};
		
		//디폴트 모델과 테이블 JScrollPane에 추가
		model = new DefaultTableModel(title,0);
		table = new JTable(model);
		//컬럼별 셀 간격 조정
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(140);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		
		
		//JScrollPane에 올리기
		JScrollPane js = new JScrollPane(table);
		js.setBounds(100, 105, 800, 500);
		this.add(js);
		
		
		//테이블 출력
		showTable();
		
		
		//배경 이미지 설정
		background = new JLabel();
		//이미지 뒤에 확장자명 붙이는 거 잊지 않기!!      
	    background.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\프로젝트\\자바 스윙 다이어리\\이미지\\새벽녘.jpg"));
	    background.setSize(1000, 700);
	    panel.add(background);
		
	}
	
	
	//////////////////////////////////////////////////
	//테이블 출력
	public void showTable() {
		
		//테이블을 출력
		//처음엔 모델 초기화
		model.setRowCount(0);
		
		//테이블에 데이터 추가
		//Vector list에 담긴 값들을 for-each문으로 쭉 출력
		for (DiaryDTO dto : list) {
			
			//새로 출력 객체용 Vector 생성 - String형 Vector 생성
			//출력할 것이기에 String형으로 생성
			Vector<String> data = new Vector<String>();
			
			//Vector에 데이터추가
			//테이블 내용만 출력
			data.add(String.valueOf(dto.getNum()));	//dto는 String 형으로 들어가므로 형변환 필수
			data.add(dto.getWriter());
			data.add(dto.getTitle());
			data.add(String.valueOf(dto.getDay()));
			
			//추가된 Vector를 model에 추가
			model.addRow(data);
		}
		
	}
	
	
	
	///////////////////////////////////////////////////
	public static void main(String[] args) {

		new DiaryMain02();
		
	}

}
