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


//ID ���� �� �����ؾ��ϴµ� ActionListener�� ���� ������ ������ ���, id���� ���� �ȵǱ⿡
//�� Ŭ���� �ȿ��� ���� ó�����־���!!!
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class DiaryMain02 extends JFrame implements ActionListener, KeyListener{

	Container cp;
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	JPanel panel;
	
	JLabel background, welcome, search;

	JTextField tfSearch;
	
	JButton btnSel, btnAdd, btnDel, btnSearch, btnRefresh;
	
	//���̺� ��������
	DefaultTableModel model;
	JTable table;
	
	
	//Model
	//�ڿ� ������ϸ� null�� ���´�
	DiaryModel diaryModel = new DiaryModel();
	
	//��°�
	Vector<DiaryDTO> list;
	
	
	//���� ���� ���� id ��ü
	String id = "";
	
	
	//�ձٹ�ư
	RoundButton roundButton = new RoundButton();
	
	
	//////////////////////////////////////
	public DiaryMain02() {
		
		super("We ���̾");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(300, 50, 1000, 700);
		//â ������ ����Ұ�
		this.setResizable(false);
		//������ ���̰� �ϱ�
		initDesign(id);
		this.setVisible(true);
	}
	
	
	/////////////////////////////////////
	//������
	public void initDesign(String id) {
		
		//��� �̹����� �� ���� �Ҵ�
		panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(null);
		
		
		////////////////////////////////////////////////////////////////////////////////
		//~�� ������� ���
		//ID�� ���� �̸� ��������!!!!
		//!!�̰� �����ϴµ� ����������...!!!
		JoinModel joinModel = new JoinModel();
		String writer = joinModel.showName(id);
		welcome = new JLabel("���� �뷡�ϴ� �������� " + writer + " ���� �� �ູ�Ͻñ�!");
		welcome.setBounds(195, 10, 1000, 100);
		welcome.setFont(new Font("�����ձ۾� �ٽ� ������", Font.BOLD, 40));
		welcome.setForeground(Color.pink);
		panel.add(welcome);
		/////////////////////////////////////////////////////////////////////////////////
		
		
		//���̺� �� ���ϱ�
		//���⼭ ���� Vector DTO ��
		//DTO�� ���� �������
		list = diaryModel.showAllTable();
		
		
		//���̺�� ���
		String [] title = {"No", "�ۼ���", "����", "����"};
		
		//����Ʈ �𵨰� ���̺� JScrollPane�� �߰�
		model = new DefaultTableModel(title,0);
		table = new JTable(model);
		//�÷��� �� ���� ����
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(140);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		
		
		//JScrollPane�� �ø���
		JScrollPane js = new JScrollPane(table);
		js.setBounds(100, 155, 800, 440);
		this.add(js);
		
		
		//���̺� ���
		showTable();
		
		
		////////////////////////////////////////////////////////////////
		
		//�˻� ��
		search = new JLabel("�ۼ��� �˻�:");
		search.setFont(new Font("�޸�����ü", Font.BOLD, 20));
		search.setForeground(Color.white);
		search.setBounds(270, 75, 500, 100);
		panel.add(search);
		
		//�˻�â
		tfSearch = new JTextField();
		tfSearch.setBounds(390, 113, 240, 25);
		tfSearch.setBorder(new LineBorder(Color.pink));
		//���⼭ ����Ű �Է½� �̺�Ʈ ����߰�
		tfSearch.addKeyListener(this);
		panel.add(tfSearch);
		
		//�˻���ư
		btnSearch = new JButton(new ImageIcon("C:\\Users\\user\\Desktop\\������Ʈ\\�ڹ� ���� ���̾\\�̹���\\������.png"));
		btnSearch.setBounds(635, 100, 50, 45);
		//���콺 �ö󰥰�� �̹��� ����
		btnSearch.setRolloverIcon(new ImageIcon("C:\\Users\\user\\Desktop\\������Ʈ\\�ڹ� ���� ���̾\\�̹���\\������2.png"));
		btnSearch.setBorderPainted(false);
		btnSearch.setContentAreaFilled(false);
		btnSearch.setFocusPainted(false);
		btnSearch.setOpaque(false);
		btnSearch.addActionListener(this);
		panel.add(btnSearch);
		
		
		//���ΰ�ħ
		btnRefresh = new JButton(new ImageIcon("C:\\Users\\user\\Desktop\\������Ʈ\\�ڹ� ���� ���̾\\�̹���\\���ΰ�ħ.png"));
		btnRefresh.setBounds(685, 100, 60, 45);
		//���콺 �ö󰥰�� �̹��� ����
		btnRefresh.setRolloverIcon(new ImageIcon("C:\\Users\\user\\Desktop\\������Ʈ\\�ڹ� ���� ���̾\\�̹���\\���ΰ�ħ2.png"));
		btnRefresh.setBorderPainted(false);
		btnRefresh.setContentAreaFilled(false);
		btnRefresh.setFocusPainted(false);
		btnRefresh.setOpaque(false);
		btnRefresh.addActionListener(this);
		panel.add(btnRefresh);

		////////////////////////////////////////////////////////////////
		
		//��ư�� �߰�
		btnAdd = new RoundButton("�۾���");
		btnDel = new RoundButton("�����");
		btnSel = new RoundButton("�б�");
		
		btnDel.setFont(new Font("�޸�����ü", Font.BOLD, 17));
		btnDel.setForeground(Color.black);
		btnDel.setBackground(Color.pink);
		btnDel.setBorder(new LineBorder(Color.black));
		btnDel.setBounds(835, 615, 65, 30);
		btnDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//������ �� ��ȣ�� �ް� �װ� ���� ���� ��´�!!
				//!!!!!!!!!!!!
				int row = table.getSelectedRow();
				Object value = table.getValueAt(row, 0);
				
				//���� ���� num������ ��ȯ!!!
				int num = Integer.parseInt((String) value);
				//System.out.println(num);
				
				
				//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//id���� �ٸ� ���
				//���̺� id���� ����Ǿ� ���� �ʱ⿡ �ٸ� ������ ���ؾ���
				//�켱 ���õ� �Խù��� num���� �����;��� - �޼��� ȣ���ؼ� �ش� �Խù��� id���� ������
				String postId = diaryModel.showOneId(num);
				
				//�˻�� ��°�
				System.out.println(num);
				//!!!!!!!!!!!!
				//������ ���������� �߻�Ŭ������ id���� ���� �ȵȴ�
				System.out.println(id);
				System.out.println(postId);
				
				//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//String ������ ���� equals�� ���ϱ�!!!!!!!!!!!!!
				if(!id.equals(postId)) {
					JOptionPane.showMessageDialog(null, "���������� �����ϴ�");
					return;					
				}
						
	
				//row�� ����
				//�� ��ü ����
				DiaryDTO dto = new DiaryDTO();
				diaryModel.deleteDiary(num);
				
				list = diaryModel.showAllTable();
				
				showTable();
				
			}
		});
		panel.add(btnDel);
		
		
		btnAdd.setFont(new Font("�޸�����ü", Font.BOLD, 17));
		btnAdd.setForeground(Color.black);
		btnAdd.setBackground(Color.pink);
		btnAdd.setBorder(new LineBorder(Color.black));
		btnAdd.setBounds(700, 615, 65, 30);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//System.out.println(id); - �˻��
				InsertDiary insertDiary = new InsertDiary(id);
				
			}
		});
		panel.add(btnAdd);
		
		btnSel.setFont(new Font("�޸�����ü", Font.BOLD, 17));
		btnSel.setForeground(Color.black);
		btnSel.setBackground(Color.pink);
		btnSel.setBorder(new LineBorder(Color.black));
		btnSel.setBounds(767, 615, 65, 30);
		btnSel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				
				//�ƹ��͵� ���þ��� ��� ����ó��
				if(row==-1) {
					JOptionPane.showMessageDialog(null, "���� ������ �������ּ���");
					return;
				}
				
				//!!!!!!row���� ���� ��ã��!!!!!
				//getValueAt
				int num = Integer.parseInt((String) table.getValueAt(row, 0));
				
				
				//�� ���鸸 DTO�� ��Ƽ�
				DiaryDTO dto = new DiaryModel().showOneTable(num);
				
				//����Ʈ �� ��������
				SelectDiary selectDiary = new SelectDiary(id, num);
				
				//������ DTO ���� ���� ����
				selectDiary.tfTitle.setText(dto.getTitle());
				selectDiary.tfImg.setText(dto.getImg());
				selectDiary.tfContent.setText(dto.getContent());
				
				//Day�� Date�ε� setText�� ����� �ϱ⿡ ��ȯ������ �ʿ�
				//��¥ ����� ���� �۾�
				SimpleDateFormat sysdate = new SimpleDateFormat("yyyy�� MM�� dd�� HH�� mm��");
				Calendar time = Calendar.getInstance();
				String date = sysdate.format(dto.getDay());
				selectDiary.outDate.setText(date);
				
				
				//!!!!�̹��� �����ϱ�!!!!
				//�̹����� ���̺� ����Ǿ� ���� ����
				//���� Vector ������ �����;� ��
				String img = diaryModel.showOneImg(num);
				selectDiary.imageName = img;
				selectDiary.photoDraw.repaint();
			}
		});
		panel.add(btnSel);
		
		////////////////////////////////////////////////////////////////
		
		//��� �̹��� ����
		background = new JLabel();
		//�̹��� �ڿ� Ȯ���ڸ� ���̴� �� ���� �ʱ�!!      
	    background.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\������Ʈ\\�ڹ� ���� ���̾\\�̹���\\������.jpg"));
	    background.setSize(1000, 700);
	    panel.add(background);
		
	}	
	
	
	//////////////////////////////////////////////////
	//Action �����ʿ� ������ ����
	
	
	//////////////////////////////////////////////////
	//��ư �̺�Ʈ ����
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object ob = e.getSource();
		
		//Insert
//		if (ob==btnAdd) {
//			
//			//�˻��-��� ���� �ҽǵƴ��� �˾ƺ�����
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
//			//������ �� ��ȣ�� �ް� �װ� ���� ���� ��´�!!
//			//!!!!!!!!!!!!
//			int row = table.getSelectedRow();
//			Object value = table.getValueAt(row, 0);
//			
//			//���� ���� num������ ��ȯ!!!
//			int num = Integer.parseInt((String) value);
//			//System.out.println(num);
//			
//			
//			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//			//id���� �ٸ� ���
//			//���̺� id���� ����Ǿ� ���� �ʱ⿡ �ٸ� ������ ���ؾ���
//			//�켱 ���õ� �Խù��� num���� �����;��� - �޼��� ȣ���ؼ� �ش� �Խù��� id���� ������
//			String postId = diaryModel.showOneId(num);
//			
//			//�˻�� ��°�
//			System.out.println(num);
//			//!!!!!!!!!!!!
//			//������ ���������� �߻�Ŭ������ id���� ���� �ȵȴ�
//			System.out.println(id);
//			System.out.println(postId);
//			
//			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//			//String ������ ���� equals�� ���ϱ�!!!!!!!!!!!!!
//			if(!id.equals(postId)) {
//				JOptionPane.showMessageDialog(null, "���������� �����ϴ�");
//				return;					
//			}
//					
//
//			//row�� ����
//			//�� ��ü ����
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
			//�𵨰� �˻��� ����
			//��� �޼���� ������ - �״�� ��ü ����� ����Ʈ�� ���� �޶����� ����
			list = search.search(model, writer);
			showTable();
			
			
		//Refresh	
		} else if (ob==btnRefresh) {
			
			//�ٽ� ����� ����Ʈ�� ���� ���� ���� �޾ƿ�
			list = diaryModel.showAllTable();
			
			showTable();
		}
	}
	
	
	
	//////////////////////////////////////////////////
	//���̺� ���
	public void showTable() {
		
		//���̺��� ���
		//ó���� �� �ʱ�ȭ
		model.setRowCount(0);
		
		//���̺� ������ �߰�
		//Vector list�� ��� ������ for-each������ �� ���
		for (DiaryDTO dto : list) {
			
			//���� ��� ��ü�� Vector ���� - String�� Vector ����
			//����� ���̱⿡ String������ ����
			Vector<String> data = new Vector<String>();
			
			//Vector�� �������߰�
			//���̺� ���븸 ���
			data.add(String.valueOf(dto.getNum()));	//dto�� String ������ ���Ƿ� ����ȯ �ʼ�
			data.add(dto.getWriter());
			data.add(dto.getTitle());
			data.add(String.valueOf(dto.getDay()));
			
			//�߰��� Vector�� model�� �߰�
			model.addRow(data);
		}
		
	}
	
	
	///////////////////////////////////////////////////
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
			btnSearch.doClick();
		}
		
	}
	
	///////////////////////////////////////////////////
	public static void main(String[] args) {
		
		new DiaryMain02();
	}

}
