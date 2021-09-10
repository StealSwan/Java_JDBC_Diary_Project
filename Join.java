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

		super("���� ���̾");
		
		//�̰� �Ȱ����� ������ x������ �ٰ��� ������
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(430, 600);
		//â ������ ����Ұ�
		this.setResizable(false);
		//������ ���̰� �ϱ�
		initDesign();
		this.setVisible(true);
	
	}
	
	
	/////////////////////////////////////////////////////
	public void initDesign() {
		
		
		//��� �̹����� �� ���� �Ҵ�
		panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(null);
		
		
		//Ÿ��Ʋ ����
	    joinTitle = new JLabel("Join The Club");
	    joinTitle.setFont(new Font("Parisienne", Font.BOLD, 30));
	    joinTitle.setForeground(Color.yellow);
		joinTitle.setBounds(120, 20, 200, 50);
		panel.add(joinTitle);
		
		//���� �˸�
		warning = new JLabel("ID & PW�� ������ ���� 5�� �̻����� �Է����ּ���");
		warning.setFont(new Font("�޸�����ü", Font.BOLD, 12));
		warning.setForeground(Color.cyan);
		warning.setBounds(87, 60, 250, 50);
		panel.add(warning);
		
		//���̵�
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblId.setForeground(Color.white);		
		lblId.setBounds(69, 113, 69, 35);
		panel.add(lblId);
		
		//��й�ȣ
		JLabel lblPw = new JLabel("PW");
		lblPw.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblPw.setForeground(Color.white);
		lblPw.setBounds(69, 178, 69, 35);
		panel.add(lblPw);
		
		//��й�ȣ Ȯ��
		JLabel lblPwCheck = new JLabel("PW Check");
		lblPwCheck.setFont(new Font("Agency FB", Font.BOLD, 20));
		lblPwCheck.setForeground(Color.white);
		lblPwCheck.setBounds(69, 243, 69, 35);
		panel.add(lblPwCheck);
		
		//�̸�
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblName.setForeground(Color.white);	
		lblName.setBounds(69, 308, 69, 35);
		panel.add(lblName);
		
		//�̸���
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblEmail.setForeground(Color.white);	
		lblEmail.setBounds(69, 373, 69, 35);
		panel.add(lblEmail);
		
		//��ȭ��ȣ
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblPhone.setForeground(Color.white);
		lblPhone.setBounds(69, 438, 69, 35);
		panel.add(lblPhone);
		
		
		//id�Է�
		tfId = new JTextField();
		tfId.setColumns(10);
		tfId.setBounds(159, 116, 186, 35);
		tfId.setBorder(new LineBorder(Color.yellow));
		panel.add(tfId);
		
		//pw�Է�
		tfPw = new JPasswordField();
		tfPw.setColumns(10);
		tfPw.setBounds(159, 181, 186, 35);
		tfPw.setBorder(new LineBorder(Color.yellow));
		panel.add(tfPw);
		
		//pwȮ��
		tfpwCheck = new JPasswordField();
		tfpwCheck.setColumns(10);
		tfpwCheck.setBounds(159, 246, 186, 35);
		tfpwCheck.setBorder(new LineBorder(Color.yellow));
		panel.add(tfpwCheck);
		
		//�̸� �Է�
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(159, 311, 186, 35);
		tfName.setBorder(new LineBorder(Color.yellow));
		panel.add(tfName);
		
		//�̸��� �Է�
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(159, 376, 186, 35);
		tfEmail.setBorder(new LineBorder(Color.yellow));
		panel.add(tfEmail);
		
		//��ȭ��ȣ �Է�
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(159, 441, 186, 35);
		tfPhone.setBorder(new LineBorder(Color.yellow));
		tfPhone.setText("010--");
		panel.add(tfPhone);
		
		
		//��ư
		btnJoin = new JButton(new ImageIcon("C:\\Users\\user\\Desktop\\������Ʈ\\�ڹ� ���� ���̾\\�̹���\\�α���1.png"));
		btnJoin.setBounds(330, 500, 80, 40);
		//���콺 �ö󰥰�� �̹��� ����
		btnJoin.setRolloverIcon(new ImageIcon("C:\\Users\\user\\Desktop\\������Ʈ\\�ڹ� ���� ���̾\\�̹���\\�α���2.png"));
		btnJoin.setBorderPainted(false);
		btnJoin.setContentAreaFilled(false);
		btnJoin.setFocusPainted(false);
		btnJoin.setOpaque(false);
		btnJoin.addActionListener(this);
		panel.add(btnJoin);

		
		//��� �̹��� ����
		background = new JLabel();
		//�̹��� �ڿ� Ȯ���ڸ� ���̴� �� ���� �ʱ�!!      
	    background.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\������Ʈ\\�ڹ� ���� ���̾\\�̹���\\���ϴ� �׶��̼�.jpg"));
	    background.setSize(430, 600);
	    panel.add(background);
			
	}
	
	
	//////////////////////////////////////////////
	//��ư���
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JoinDTO dto = new JoinDTO();
		
		//���� ���� - ��������
		String id = tfId.getText().trim();
		String pw = tfPw.getText().trim();
		String pwCheck = tfpwCheck.getText().trim();
		String name = tfName.getText().trim();
		String email = tfEmail.getText().trim();
		String phone = tfPhone.getText().trim();
		
		
		//���̵� üũ�� Ŭ���� - �޼��� ȣ��
		IdChcek idChcek = new IdChcek();
		//id�� ����, ���ڸ� �Է°���
		boolean idcheck = idChcek.checkIdOnlyNumberAndAlphabet(id);
		//id �ߺ�üũ
		int overlapId = idChcek.getidCheck(id);

		
		//�̸��� üũ�� Ŭ���� - �޼��� ȣ��
		EmailCheck emailCheck = new EmailCheck();
		int emailcheck = emailCheck.EmailCheck(email);
		
		
		//���Թ�������
		if (id.length()<5) {
			JOptionPane.showMessageDialog(this, "ID�� �ּ� 5�� �̻����� �Է����ּ���");
			return;
			
		} else if (idcheck==false) {
			//����, ���ڸ� �Է�Ȯ��
			JOptionPane.showMessageDialog(this, "ID�� ���� & ���� �Է¸� �����մϴ�");
			return;
			
		} else if (overlapId!=0) {
			//���̵� �ߺ�����
			JOptionPane.showMessageDialog(this, "�ش� ID�� �̹� ������Դϴ�");
			return;
			
		} else if (pw.length()<5) {
			JOptionPane.showMessageDialog(this, "��й�ȣ�� �ּ� 5�� �̻����� �Է����ּ���");
			return;
			
		} else if (!pw.equals(pwCheck)) {
			//equals�� !�ٿ��� ��
			JOptionPane.showMessageDialog(this, "��й�ȣ�� ��ġ���� �ʽ��ϴ�");
			return;
			
		} else if (name.length()==0) {
			JOptionPane.showMessageDialog(this, "�̸��� �Է����ּ���");
			return;
			
		} else if (emailcheck==1) {
			JOptionPane.showMessageDialog(this, "�ùٸ� �̸��� ������ �ƴմϴ�");
			return;
			
		} else if (phone.length()!=13) {
			JOptionPane.showMessageDialog(this, "����ó�� �Է����ּ���");
			return;
		}
		
		
		JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.");
		
		
		//�� DTO�� �־��ֱ�
		dto.setId(id);
		dto.setPassword(pw);
		dto.setName(name);
		dto.setEmail(email);
		dto.setPhone(phone);
		
		
		//Model�� �߰�
		JoinModel joinModel = new JoinModel();
		joinModel.insertJoin(dto);
		
		
		//ȸ������ �Ϸ�� â ������� �ϱ�
		setVisible(false);
		
	}
	
	
	//////////////////////////////////////////////
//	public static void main(String[] args) {
//	
//		new Join();
//		
//	}
	
}

