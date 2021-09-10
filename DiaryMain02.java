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
//���� + Selectâ ����
public class DiaryMain02 extends JFrame implements ActionListener{

	Container cp;
	
	JPanel panel;
	
	JLabel background, welcome;
	
	JButton btnAdd, btnDel, btnUpdate;
	
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
	
	
	//////////////////////////////////////
	public DiaryMain02() {
		
		super("���� ���̾");
		
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
		welcome = new JLabel("���� �뷡�ϴ� �������� " + joinModel.showName(id) + " ���� �� �ູ�Ͻñ�!");
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
		js.setBounds(100, 105, 800, 500);
		this.add(js);
		
		
		//���̺� ���
		showTable();
		
		
		//��� �̹��� ����
		background = new JLabel();
		//�̹��� �ڿ� Ȯ���ڸ� ���̴� �� ���� �ʱ�!!      
	    background.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\������Ʈ\\�ڹ� ���� ���̾\\�̹���\\������.jpg"));
	    background.setSize(1000, 700);
	    panel.add(background);
		
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
	public static void main(String[] args) {

		new DiaryMain02();
		
	}

}
