package DiaryProject;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class InsertDiary extends JFrame implements ActionListener{

	//모델들
	DiaryDTO diaryDTO = new DiaryDTO();
	
	Container cp;
	
	JLabel background, title, content, img, date, outDate;
	JTextField tfTitle, tfContent, tfImg;
	
	JButton btnImg, btnAdd;
	
	JPanel panel;
	
	
	//이미지 담을 준비
	PhotoDraw photoDraw = new PhotoDraw();
	String imageName;
	
	
	////////////////////////////////////////////////////////
	//기본
	public InsertDiary() {
		
		super("We 다이어리");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(300, 50, 600, 700);
		//창 사이즈 변경불가
		this.setResizable(false);
		//디자인 보이게 하기
		initDesign();
		this.setVisible(true);
		
	}
	
	
	////////////////////////////////////////////////////////
	//init
	public void initDesign() {
		
		//배경 이미지가 들어갈 공간 할당
		panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(null);
		
		
		////////////////////////////////////////////////////
		//이미지 출력
		//내부 클래스로 Canvas 클래스 호출 필요
		//배경에 가려지기 때문에 다른 것보다 먼저 나와야함
		//이미지가 출력될 창의 크기 세팅
		photoDraw.setBounds(200, 137, 200, 120);
		//이미지 출력
		panel.add(photoDraw);
		
		
		/////////////////////////////////////////////////////
		
		//날짜라벨 추가
		date = new JLabel("날짜:");
		date.setBounds(30, 0, 1000, 100);
		date.setFont(new Font("나눔손글씨 다시 시작해", Font.BOLD, 40));
		date.setForeground(Color.white);
		panel.add(date);
		
		//날짜 출력을 위한 작업
		SimpleDateFormat sysdate = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
		Calendar time = Calendar.getInstance();
		String todayDate = sysdate.format(time.getTime()); 
		
		//날짜 넣어주기
		outDate = new JLabel(todayDate);
		outDate.setBounds(120, 0, 1000, 100);
		outDate.setFont(new Font("나눔손글씨 다시 시작해", Font.BOLD, 35));
		outDate.setForeground(Color.BLACK);
		panel.add(outDate);
		
		//이미지 첨부
		img = new JLabel("이미지:");
		img.setBounds(30, 60, 1000, 100);
		img.setFont(new Font("나눔손글씨 다시 시작해", Font.BOLD, 40));
		img.setForeground(Color.white);
		panel.add(img);
		
		//타이틀
		title = new JLabel("제목:");
		title.setBounds(40, 230, 1000, 100);
		title.setFont(new Font("나눔손글씨 다시 시작해", Font.BOLD, 40));
		title.setForeground(Color.white);
		panel.add(title);
		
		////////////////////////////////////////////////////
		//버튼 추가
		btnImg = new RoundButton("+");
		btnImg.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		btnImg.setForeground(Color.black);
		btnImg.setBackground(Color.pink);
		btnImg.setBounds(115, 100, 40, 30);
		btnImg.addActionListener(this);
		panel.add(btnImg);
		
		btnAdd = new RoundButton("완료");
		btnAdd.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		btnAdd.setForeground(Color.black);
		btnAdd.setBackground(Color.pink);
		btnAdd.setBounds(520, 620, 50, 30);
		btnAdd.addActionListener(this);
		panel.add(btnAdd);
		
		
		////////////////////////////////////////////////////
		//textfield 추가
		tfImg = new JTextField();
		tfImg.setBounds(165, 102, 355, 25);
		tfImg.setBorder(new LineBorder(Color.pink));
		panel.add(tfImg);
		
		tfTitle = new JTextField();
		tfTitle.setBounds(110, 270, 410, 25);
		tfTitle.setBorder(new LineBorder(Color.yellow));
		panel.add(tfTitle);
		
		tfContent = new JTextField();
		tfContent.setBounds(33, 310, 520, 300);
		tfContent.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.add(tfContent);
		
		////////////////////////////////////////////////////
		//배경 이미지 설정
		background = new JLabel();
		//이미지 뒤에 확장자명 붙이는 거 잊지 않기!!      
	    background.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\프로젝트\\자바 스윙 다이어리\\이미지\\새벽녘.jpg"));
	    background.setSize(1000, 700);
	    panel.add(background);
		
	}
	
	
	////////////////////////////////////////////////////////
	//버튼기능
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object ob = e.getSource();
		
		//이미지추가
		if (ob==btnImg) {
			
			//파일 선택하는 객체 - FileDialog
			//파일 다이얼로그 생성 - this 여기, "팝업창 이름", FileDialog.LOAD!!!!
			FileDialog dlg = new FileDialog(this, "이미지 가져오기", FileDialog.LOAD);
			//창 보이게 하기
			dlg.setVisible(true);
			
			//취소 누르면 메서드 종료
			//다이얼로그의 값이 비었을 때
			if (dlg.getDirectory()==null) {
				return;
			}
			
			//이미지명 얻기
			imageName = dlg.getDirectory() + dlg.getFile();
			
			//이미지명 라벨에 출력
			tfImg.setText(imageName);
			
			//이미지 출력 메서드
			photoDraw.repaint();
			
			
		//작성완료	
		} else if (ob==btnAdd) {
			
			DiaryDTO diaryDTO = new DiaryDTO();
			
		}
	}
	
	
	////////////////////////////////////////////////////////
	//Canvas 내부클래스 - extends로 호출
	class PhotoDraw extends Canvas{
		
		//paint 오버라이딩
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			//String 값으로 선언했던 imageName
			//이미지가 선택되었을 경우
			if (imageName!=null) {
				
				//선택된 이미지 아이콘을 생성
				Image image = new ImageIcon(imageName).getImage();
				
				//생성한 아이콘 크기 설정
				//g를 객체로하는 draw image임을 명심!!
				//안에 출력될 이미지 크기
				g.drawImage(image, 0, 0, 200, 120, this);
			}
		}
	}
	
	
	//////////////////////////////////////////////////////
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new InsertDiary();

	}

}
