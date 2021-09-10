package DiaryProject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class Join extends JFrame implements ActionListener{

	private JLabel joinTitle, background, warning;
	private JButton btnJoin;
	private JTextField tfId, tfName, tfEmail, tfPhone;
	
	private JPasswordField tfPw, tfpwCheck;
	
	JPanel panel;

	
	public Join() {

		super("감성 다이어리");
		
		//이게 안가려져 있으면 x누를때 다같이 꺼진다
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(430, 600);
		//창 사이즈 변경불가
		this.setResizable(false);
		//디자인 보이게 하기
		initDesign();
		this.setVisible(true);
	
	}
	
	
	/////////////////////////////////////////////////////
	public void initDesign() {
		
		
		//배경 이미지가 들어갈 공간 할당
		panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(null);
		
		
		//타이틀 제목
	    joinTitle = new JLabel("Join The Club");
	    joinTitle.setFont(new Font("Parisienne", Font.BOLD, 30));
	    joinTitle.setForeground(Color.yellow);
		joinTitle.setBounds(120, 20, 200, 50);
		panel.add(joinTitle);
		
		//주의 알림
		warning = new JLabel("ID & PW는 영문과 숫자 5자 이상으로 입력해주세요");
		warning.setFont(new Font("휴먼편지체", Font.BOLD, 12));
		warning.setForeground(Color.cyan);
		warning.setBounds(87, 60, 250, 50);
		panel.add(warning);
		
		//아이디
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblId.setForeground(Color.white);		
		lblId.setBounds(69, 113, 69, 35);
		panel.add(lblId);
		
		//비밀번호
		JLabel lblPw = new JLabel("PW");
		lblPw.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblPw.setForeground(Color.white);
		lblPw.setBounds(69, 178, 69, 35);
		panel.add(lblPw);
		
		//비밀번호 확인
		JLabel lblPwCheck = new JLabel("PW Check");
		lblPwCheck.setFont(new Font("Agency FB", Font.BOLD, 20));
		lblPwCheck.setForeground(Color.white);
		lblPwCheck.setBounds(69, 243, 69, 35);
		panel.add(lblPwCheck);
		
		//이름
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblName.setForeground(Color.white);	
		lblName.setBounds(69, 308, 69, 35);
		panel.add(lblName);
		
		//이메일
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblEmail.setForeground(Color.white);	
		lblEmail.setBounds(69, 373, 69, 35);
		panel.add(lblEmail);
		
		//전화번호
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblPhone.setForeground(Color.white);
		lblPhone.setBounds(69, 438, 69, 35);
		panel.add(lblPhone);
		
		
		//id입력
		tfId = new JTextField();
		tfId.setColumns(10);
		tfId.setBounds(159, 116, 186, 35);
		tfId.setBorder(new LineBorder(Color.yellow));
		panel.add(tfId);
		
		//pw입력
		tfPw = new JPasswordField();
		tfPw.setColumns(10);
		tfPw.setBounds(159, 181, 186, 35);
		tfPw.setBorder(new LineBorder(Color.yellow));
		panel.add(tfPw);
		
		//pw확인
		tfpwCheck = new JPasswordField();
		tfpwCheck.setColumns(10);
		tfpwCheck.setBounds(159, 246, 186, 35);
		tfpwCheck.setBorder(new LineBorder(Color.yellow));
		panel.add(tfpwCheck);
		
		//이름 입력
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(159, 311, 186, 35);
		tfName.setBorder(new LineBorder(Color.yellow));
		panel.add(tfName);
		
		//이메일 입력
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(159, 376, 186, 35);
		tfEmail.setBorder(new LineBorder(Color.yellow));
		panel.add(tfEmail);
		
		//전화번호 입력
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(159, 441, 186, 35);
		tfPhone.setBorder(new LineBorder(Color.yellow));
		tfPhone.setText("010--");
		panel.add(tfPhone);
		
		
		//버튼
		btnJoin = new JButton(new ImageIcon("C:\\Users\\user\\Desktop\\프로젝트\\자바 스윙 다이어리\\이미지\\로그인1.png"));
		btnJoin.setBounds(330, 500, 80, 40);
		//마우스 올라갈경우 이미지 변경
		btnJoin.setRolloverIcon(new ImageIcon("C:\\Users\\user\\Desktop\\프로젝트\\자바 스윙 다이어리\\이미지\\로그인2.png"));
		btnJoin.setBorderPainted(false);
		btnJoin.setContentAreaFilled(false);
		btnJoin.setFocusPainted(false);
		btnJoin.setOpaque(false);
		btnJoin.addActionListener(this);
		panel.add(btnJoin);

		
		//배경 이미지 설정
		background = new JLabel();
		//이미지 뒤에 확장자명 붙이는 거 잊지 않기!!      
	    background.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\프로젝트\\자바 스윙 다이어리\\이미지\\밤하늘 그라데이션.jpg"));
	    background.setSize(430, 600);
	    panel.add(background);
			
	}
	
	
	//////////////////////////////////////////////
	//버튼기능
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JoinDTO dto = new JoinDTO();
		
		//길이 정의 - 공백제외
		String id = tfId.getText().trim();
		String pw = tfPw.getText().trim();
		String pwCheck = tfpwCheck.getText().trim();
		String name = tfName.getText().trim();
		String email = tfEmail.getText().trim();
		String phone = tfPhone.getText().trim();
		
		
		//아이디 체크용 클래스 - 메서드 호출
		IdChcek idChcek = new IdChcek();
		//id에 영문, 숫자만 입력가능
		boolean idcheck = idChcek.checkIdOnlyNumberAndAlphabet(id);
		//id 중복체크
		int overlapId = idChcek.getidCheck(id);

		
		//이메일 체크용 클래스 - 메서드 호출
		EmailCheck emailCheck = new EmailCheck();
		int emailcheck = emailCheck.EmailCheck(email);
		
		
		//가입방지조항
		if (id.length()<5) {
			JOptionPane.showMessageDialog(this, "ID는 최소 5자 이상으로 입력해주세요");
			return;
			
		} else if (idcheck==false) {
			//영문, 숫자만 입력확인
			JOptionPane.showMessageDialog(this, "ID는 영문 & 숫자 입력만 가능합니다");
			return;
			
		} else if (overlapId!=0) {
			//아이디 중복여부
			JOptionPane.showMessageDialog(this, "해당 ID는 이미 사용중입니다");
			return;
			
		} else if (pw.length()<5) {
			JOptionPane.showMessageDialog(this, "비밀번호는 최소 5자 이상으로 입력해주세요");
			return;
			
		} else if (!pw.equals(pwCheck)) {
			//equals에 !붙여서 비교
			JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다");
			return;
			
		} else if (name.length()==0) {
			JOptionPane.showMessageDialog(this, "이름을 입력해주세요");
			return;
			
		} else if (emailcheck==1) {
			JOptionPane.showMessageDialog(this, "올바른 이메일 형식이 아닙니다");
			return;
			
		} else if (phone.length()!=13) {
			JOptionPane.showMessageDialog(this, "연락처를 입력해주세요");
			return;
		}
		
		
		JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
		
		
		//값 DTO에 넣어주기
		dto.setId(id);
		dto.setPassword(pw);
		dto.setName(name);
		dto.setEmail(email);
		dto.setPhone(phone);
		
		
		//Model에 추가
		JoinModel joinModel = new JoinModel();
		joinModel.insertJoin(dto);
		
		
		//회원가입 완료시 창 사라지게 하기
		setVisible(false);
		
	}
	
	
	//////////////////////////////////////////////
//	public static void main(String[] args) {
//	
//		new Join();
//		
//	}
	
}

