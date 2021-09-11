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
		
		super("���� ���̾");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(300, 50, 1000, 700);
		//â ������ ����Ұ�
		this.setResizable(false);
		//������ ���̰� �ϱ�
		initDesign();
		this.setVisible(true);
	}
	
	
	//////////////////////////////////////
	//���� ȭ��
	public void initDesign() {
		
		//��� �̹����� �� ���� �Ҵ�
		panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(null);
		
		
		//���� - ������ ���� �;���
		title = new JLabel(" We Diary");
		title.setFont(new Font("Parisienne", Font.BOLD, 65));
		title.setForeground(Color.white);	//��Ʈ��
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
		//���⼭ ����Ű �Է½� �̺�Ʈ ����߰�
		tfPw.addKeyListener(this);
		panel.add(tfPw);
		
		
		//�̹��� ��ư �߰�
		//�α���
		btnLogIn = new JButton(new ImageIcon("C:\\Users\\user\\Desktop\\������Ʈ\\�ڹ� ���� ���̾\\�̹���\\�α���1.png"));
		btnLogIn.setBounds(500, 460, 40, 40);
		//���콺 �ö󰥰�� �̹��� ����
		btnLogIn.setRolloverIcon(new ImageIcon("C:\\Users\\user\\Desktop\\������Ʈ\\�ڹ� ���� ���̾\\�̹���\\�α���2.png"));
		btnLogIn.setBorderPainted(false);
		btnLogIn.setContentAreaFilled(false);
		btnLogIn.setFocusPainted(false);
		btnLogIn.setOpaque(false);
		btnLogIn.addActionListener(this);
		panel.add(btnLogIn);
		
		
		//ȸ������
		btnJoin = new JButton(new ImageIcon("C:\\Users\\user\\Desktop\\������Ʈ\\�ڹ� ���� ���̾\\�̹���\\ȸ������1.png"));
		btnJoin.setBounds(545, 457, 50, 50);
		//���콺 �ö󰥰�� �̹��� ����
		btnJoin.setRolloverIcon(new ImageIcon("C:\\Users\\user\\Desktop\\������Ʈ\\�ڹ� ���� ���̾\\�̹���\\ȸ������2.png"));
		btnJoin.setBorderPainted(false);
		btnJoin.setContentAreaFilled(false);
		btnJoin.setFocusPainted(false);
		btnJoin.setOpaque(false);
		btnJoin.addActionListener(this);
		panel.add(btnJoin);
		
		
		//Copy Right
		copyright = new JLabel("Copyright �� 2021 by ���ȯ All Programs cannot be copied without permission");
		copyright.setFont(new Font("",Font.BOLD,10));
		copyright.setBounds(570, 590, 500, 100);
		copyright.setForeground(Color.white);
		panel.add(copyright);
		
		
		//��� �̹��� ����
		background = new JLabel();
		//�̹��� �ڿ� Ȯ���ڸ� ���̴� �� ���� �ʱ�!!      
	    background.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\������Ʈ\\�ڹ� ���� ���̾\\�̹���\\������.gif"));
	    background.setSize(1000, 700);
	    panel.add(background);
		
		
	}
	
	
	////////////////////////////////////////
	//��ư��� ����
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object ob = e.getSource();
		
		if (ob==btnLogIn) {
			
			String id = tfId.getText();
			String pw = tfPw.getText();
			
			//�Էµ� id�� pw�� �˻� �޼���� �ѱ�
			Id_Pw_Check id_Pw_Check = new Id_Pw_Check();
			int state = id_Pw_Check.idpwCheck(id,pw);
			
			if (state==0) {
				JOptionPane.showMessageDialog(this, "ID, PW�� Ȯ�����ּ���");
				return;
			}
			
			
			//ID�� PW�� ���� �Էµ� ���
			DiaryMain02 diaryMain02 = new DiaryMain02();
			
			//ID ����
			JoinModel joinModel = new JoinModel();
		
			
			//����â02
			diaryMain02.initDesign(id);
			
			//�α����ϸ� �Ⱥ��̰�
			this.setVisible(false);
			
			
		} else if (ob==btnJoin) {
			
			Join join = new Join();
			
		}
		
	}
	
	
	
	//////////////////////////////////////////////////
	//����Ű �Է½� �α���
	//Ű�� ��ư �̺�Ʈ �� �� ó��
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
