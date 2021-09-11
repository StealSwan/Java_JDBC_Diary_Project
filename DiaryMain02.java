package DiaryProject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import quiz0908.HelloDTO;
import quiz0908.HelloModel;


//ID 값을 쭉 전달해야하는데 ActionListener를 내부 변수로 지정할 경우, id값이 전달 안되기에
//한 클래스 안에서 전부 처리해주었다!!!
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class DiaryMain02 extends JFrame implements ActionListener, KeyListener{

	Container cp;
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	JPanel panel;
	
	JLabel background, welcome, search;

	JTextField tfSearch;
	
	JButton btnSel, btnAdd, btnDel, btnSearch, btnRefresh;
	
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
	
	
	//둥근버튼
	RoundButton roundButton = new RoundButton();
	
	
	//////////////////////////////////////
	public DiaryMain02() {
		
		super("We 다이어리");
		
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
		String writer = joinModel.showName(id);
		welcome = new JLabel("별을 노래하는 마음으로 " + writer + " 님이 늘 행복하시길!");
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
		js.setBounds(100, 155, 800, 440);
		this.add(js);
		
		
		//테이블 출력
		showTable();
		
		
		////////////////////////////////////////////////////////////////
		
		//검색 라벨
		search = new JLabel("작성자 검색:");
		search.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		search.setForeground(Color.white);
		search.setBounds(270, 75, 500, 100);
		panel.add(search);
		
		//검색창
		tfSearch = new JTextField();
		tfSearch.setBounds(390, 113, 240, 25);
		tfSearch.setBorder(new LineBorder(Color.pink));
		//여기서 엔터키 입력시 이벤트 기능추가
		tfSearch.addKeyListener(this);
		panel.add(tfSearch);
		
		//검색버튼
		btnSearch = new JButton(new ImageIcon("C:\\Users\\user\\Desktop\\프로젝트\\자바 스윙 다이어리\\이미지\\돋보기.png"));
		btnSearch.setBounds(635, 100, 50, 45);
		//마우스 올라갈경우 이미지 변경
		btnSearch.setRolloverIcon(new ImageIcon("C:\\Users\\user\\Desktop\\프로젝트\\자바 스윙 다이어리\\이미지\\돋보기2.png"));
		btnSearch.setBorderPainted(false);
		btnSearch.setContentAreaFilled(false);
		btnSearch.setFocusPainted(false);
		btnSearch.setOpaque(false);
		btnSearch.addActionListener(this);
		panel.add(btnSearch);
		
		
		//새로고침
		btnRefresh = new JButton(new ImageIcon("C:\\Users\\user\\Desktop\\프로젝트\\자바 스윙 다이어리\\이미지\\새로고침.png"));
		btnRefresh.setBounds(685, 100, 60, 45);
		//마우스 올라갈경우 이미지 변경
		btnRefresh.setRolloverIcon(new ImageIcon("C:\\Users\\user\\Desktop\\프로젝트\\자바 스윙 다이어리\\이미지\\새로고침2.png"));
		btnRefresh.setBorderPainted(false);
		btnRefresh.setContentAreaFilled(false);
		btnRefresh.setFocusPainted(false);
		btnRefresh.setOpaque(false);
		btnRefresh.addActionListener(this);
		panel.add(btnRefresh);

		////////////////////////////////////////////////////////////////
		
		//버튼들 추가
		btnAdd = new RoundButton("글쓰기");
		btnDel = new RoundButton("지우기");
		btnSel = new RoundButton("읽기");
		
		btnDel.setFont(new Font("휴먼편지체", Font.BOLD, 17));
		btnDel.setForeground(Color.black);
		btnDel.setBackground(Color.pink);
		btnDel.setBorder(new LineBorder(Color.black));
		btnDel.setBounds(835, 615, 65, 30);
		btnDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//선택한 행 번호를 받고 그걸 통해 값을 얻는다!!
				//!!!!!!!!!!!!
				int row = table.getSelectedRow();
				Object value = table.getValueAt(row, 0);
				
				//얻은 값을 num값으로 변환!!!
				int num = Integer.parseInt((String) value);
				//System.out.println(num);
				
				
				//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//id값이 다를 경우
				//테이블에 id값이 저장되어 있지 않기에 다른 곳에서 비교해야함
				//우선 선택된 게시물의 num값을 가져와야함 - 메서드 호출해서 해당 게시물의 id값만 가져옴
				String postId = diaryModel.showOneId(num);
				
				//검산용 출력값
				System.out.println(num);
				//!!!!!!!!!!!!
				//삭제도 마찬가지로 추상클래스면 id값이 전달 안된다
				System.out.println(id);
				System.out.println(postId);
				
				//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//String 끼리의 값은 equals로 비교하기!!!!!!!!!!!!!
				if(!id.equals(postId)) {
					JOptionPane.showMessageDialog(null, "삭제권한이 없습니다");
					return;					
				}
						
	
				//row값 전달
				//모델 객체 생성
				DiaryDTO dto = new DiaryDTO();
				diaryModel.deleteDiary(num);
				
				list = diaryModel.showAllTable();
				
				showTable();
				
			}
		});
		panel.add(btnDel);
		
		
		btnAdd.setFont(new Font("휴먼편지체", Font.BOLD, 17));
		btnAdd.setForeground(Color.black);
		btnAdd.setBackground(Color.pink);
		btnAdd.setBorder(new LineBorder(Color.black));
		btnAdd.setBounds(700, 615, 65, 30);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//System.out.println(id); - 검산용
				InsertDiary insertDiary = new InsertDiary(id);
				
			}
		});
		panel.add(btnAdd);
		
		btnSel.setFont(new Font("휴먼편지체", Font.BOLD, 17));
		btnSel.setForeground(Color.black);
		btnSel.setBackground(Color.pink);
		btnSel.setBorder(new LineBorder(Color.black));
		btnSel.setBounds(767, 615, 65, 30);
		btnSel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				
				//아무것도 선택안할 경우 에러처리
				if(row==-1) {
					JOptionPane.showMessageDialog(null, "읽을 내용을 선택해주세요");
					return;
				}
				
				//!!!!!!row값을 토대로 값찾기!!!!!
				//getValueAt
				int num = Integer.parseInt((String) table.getValueAt(row, 0));
				
				
				//볼 값들만 DTO에 담아서
				DiaryDTO dto = new DiaryModel().showOneTable(num);
				
				//셀렉트 폼 가져오기
				SelectDiary selectDiary = new SelectDiary(id, num);
				
				//가져온 DTO 값을 토대로 세팅
				selectDiary.tfTitle.setText(dto.getTitle());
				selectDiary.tfImg.setText(dto.getImg());
				selectDiary.tfContent.setText(dto.getContent());
				
				//Day는 Date인데 setText를 해줘야 하기에 변환과정이 필요
				//날짜 출력을 위한 작업
				SimpleDateFormat sysdate = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
				Calendar time = Calendar.getInstance();
				String date = sysdate.format(dto.getDay());
				selectDiary.outDate.setText(date);
				
				
				//!!!!이미지 세팅하기!!!!
				//이미지는 테이블에 저장되어 있지 않음
				//따라서 Vector 값에서 가져와야 함
				String img = diaryModel.showOneImg(num);
				selectDiary.imageName = img;
				selectDiary.photoDraw.repaint();
			}
		});
		panel.add(btnSel);
		
		////////////////////////////////////////////////////////////////
		
		//배경 이미지 설정
		background = new JLabel();
		//이미지 뒤에 확장자명 붙이는 거 잊지 않기!!      
	    background.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\프로젝트\\자바 스윙 다이어리\\이미지\\새벽녘.jpg"));
	    background.setSize(1000, 700);
	    panel.add(background);
		
	}	
	
	
	//////////////////////////////////////////////////
	//Action 리스너에 변수값 전달
	
	
	//////////////////////////////////////////////////
	//버튼 이벤트 구현
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object ob = e.getSource();
		
		//Insert
//		if (ob==btnAdd) {
//			
//			//검산용-어디서 값이 소실됐는지 알아볼거임
//			System.out.println(id);
//			InsertDiary insertDiary = new InsertDiary(id);
//			
//		//Select
//		 if (ob==btnSel) {
//			
//			int row = table.getSelectedRow();
//			
//		
//		} else
			
		
//		//Delete	
//		if (ob==btnDel) {
//			
//			//선택한 행 번호를 받고 그걸 통해 값을 얻는다!!
//			//!!!!!!!!!!!!
//			int row = table.getSelectedRow();
//			Object value = table.getValueAt(row, 0);
//			
//			//얻은 값을 num값으로 변환!!!
//			int num = Integer.parseInt((String) value);
//			//System.out.println(num);
//			
//			
//			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//			//id값이 다를 경우
//			//테이블에 id값이 저장되어 있지 않기에 다른 곳에서 비교해야함
//			//우선 선택된 게시물의 num값을 가져와야함 - 메서드 호출해서 해당 게시물의 id값만 가져옴
//			String postId = diaryModel.showOneId(num);
//			
//			//검산용 출력값
//			System.out.println(num);
//			//!!!!!!!!!!!!
//			//삭제도 마찬가지로 추상클래스면 id값이 전달 안된다
//			System.out.println(id);
//			System.out.println(postId);
//			
//			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//			//String 끼리의 값은 equals로 비교하기!!!!!!!!!!!!!
//			if(!id.equals(postId)) {
//				JOptionPane.showMessageDialog(null, "삭제권한이 없습니다");
//				return;					
//			}
//					
//
//			//row값 전달
//			//모델 객체 생성
//			DiaryDTO dto = new DiaryDTO();
//			diaryModel.deleteDiary(num);
//			
//			list = diaryModel.showAllTable();
//			
//			showTable();
			
			
		//Search	
		 if (ob==btnSearch) {
			
			String writer = tfSearch.getText().trim();
			
			Search search = new Search();
			//모델과 검색값 전달
			//출력 메서드는 동일함 - 그대신 전체 출력할 리스트의 값이 달라지는 것임
			list = search.search(model, writer);
			showTable();
			
			
		//Refresh	
		} else if (ob==btnRefresh) {
			
			//다시 출력할 리스트에 모델을 통해 값을 받아옴
			list = diaryModel.showAllTable();
			
			showTable();
		}
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
	//엔터키 입력시 로그인
	//키와 버튼 이벤트 둘 다 처리
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
				
		int key = e.getKeyCode();
		if (key==KeyEvent.VK_ENTER) {
			Toolkit.getDefaultToolkit().beep();
			btnSearch.doClick();
		}
		
	}
	
	///////////////////////////////////////////////////
	public static void main(String[] args) {
		
		new DiaryMain02();
	}

}
