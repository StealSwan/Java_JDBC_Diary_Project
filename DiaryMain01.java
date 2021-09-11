package DiaryProject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


public class DiaryMain01 extends JFrame implements ActionListener, KeyListener{

	Container cp;
	
	JLabel title, id, pw, background, copyright;
	
	JPanel panel;
	
	JTextField tfId;
	JPasswordField tfPw;
	
	JButton btnLogIn, btnJoin;
	
	
	//////////////////////////////////////
	public DiaryMain01() {
		
		super("공유 다이어리");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(300, 50, 1000, 700);
		//창 사이즈 변경불가
		this.setResizable(false);
		//디자인 보이게 하기
		initDesign();
		this.setVisible(true);
	}
	
	
	//////////////////////////////////////
	//메인 화면
	public void initDesign() {
		
		//배경 이미지가 들어갈 공간 할당
		panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(null);
		
		
		//제목 - 제목이 먼저 와야함
		title = new JLabel(" We Diary");
		title.setFont(new Font("Parisienne", Font.BOLD, 65));
		title.setForeground(Color.white);	//폰트색
		title.setBounds(330, 150, 600, 200);
		panel.add(title);
		
		
		//ID
		id = new JLabel("ID:");
		id.setFont(new Font("Agency FB", Font.BOLD, 30));
		id.setForeground(Color.white);
		id.setBounds(385, 255, 500, 200);
		panel.add(id);
		
		//PW
		pw = new JLabel("PW:");
		pw.setFont(new Font("Agency FB", Font.BOLD, 25));
		pw.setForeground(Color.white);
		pw.setBounds(385, 305, 500, 200);
		panel.add(pw);
		
		
		//TextField
		//ID
		tfId = new JTextField();
		tfId.setBounds(435, 345, 160, 25);
		tfId.setBorder(new LineBorder(Color.yellow));
		panel.add(tfId);
		
		//PW
		tfPw = new JPasswordField();
		tfPw.setBounds(435, 395, 160, 25);
		tfPw.setBorder(new LineBorder(Color.yellow));
		//여기서 엔터키 입력시 이벤트 기능추가
		tfPw.addKeyListener(this);
		panel.add(tfPw);
		
		
		//이미지 버튼 추가
		//로그인
		btnLogIn = new JButton(new ImageIcon("C:\\Users\\user\\Desktop\\프로젝트\\자바 스윙 다이어리\\이미지\\로그인1.png"));
		btnLogIn.setBounds(500, 460, 40, 40);
		//마우스 올라갈경우 이미지 변경
		btnLogIn.setRolloverIcon(new ImageIcon("C:\\Users\\user\\Desktop\\프로젝트\\자바 스윙 다이어리\\이미지\\로그인2.png"));
		btnLogIn.setBorderPainted(false);
		btnLogIn.setContentAreaFilled(false);
		btnLogIn.setFocusPainted(false);
		btnLogIn.setOpaque(false);
		btnLogIn.addActionListener(this);
		panel.add(btnLogIn);
		
		
		//회원가입
		btnJoin = new JButton(new ImageIcon("C:\\Users\\user\\Desktop\\프로젝트\\자바 스윙 다이어리\\이미지\\회원가입1.png"));
		btnJoin.setBounds(545, 457, 50, 50);
		//마우스 올라갈경우 이미지 변경
		btnJoin.setRolloverIcon(new ImageIcon("C:\\Users\\user\\Desktop\\프로젝트\\자바 스윙 다이어리\\이미지\\회원가입2.png"));
		btnJoin.setBorderPainted(false);
		btnJoin.setContentAreaFilled(false);
		btnJoin.setFocusPainted(false);
		btnJoin.setOpaque(false);
		btnJoin.addActionListener(this);
		panel.add(btnJoin);
		
		
		//Copy Right
		copyright = new JLabel("Copyright ⓒ 2021 by 김수환 All Programs cannot be copied without permission");
		copyright.setFont(new Font("",Font.BOLD,10));
		copyright.setBounds(570, 590, 500, 100);
		copyright.setForeground(Color.white);
		panel.add(copyright);
		
		
		//배경 이미지 설정
		background = new JLabel();
		//이미지 뒤에 확장자명 붙이는 거 잊지 않기!!      
	    background.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\프로젝트\\자바 스윙 다이어리\\이미지\\유성우.gif"));
	    background.setSize(1000, 700);
	    panel.add(background);
		
		
	}
	
	
	////////////////////////////////////////
	//버튼기능 구현
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object ob = e.getSource();
		
		if (ob==btnLogIn) {
			
			String id = tfId.getText();
			String pw = tfPw.getText();
			
			//입력된 id와 pw를 검사 메서드로 넘김
			Id_Pw_Check id_Pw_Check = new Id_Pw_Check();
			int state = id_Pw_Check.idpwCheck(id,pw);
			
			if (state==0) {
				JOptionPane.showMessageDialog(this, "ID, PW를 확인해주세요");
				return;
			}
			
			
			//ID와 PW가 정상 입력될 경우
			DiaryMain02 diaryMain02 = new DiaryMain02();
			
			//ID 전달
			JoinModel joinModel = new JoinModel();
		
			
			//메인창02
			diaryMain02.initDesign(id);
			
			//로그인하면 안보이게
			this.setVisible(false);
			
			
		} else if (ob==btnJoin) {
			
			Join join = new Join();
			
		}
		
	}
	
	
	
	//////////////////////////////////////////////////
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
			btnLogIn.doClick();
		}
		
	}
	
	
	
	/////////////////////////////////////////
	public static void main(String[] args) {
		
	new DiaryMain01();	

	}

}
