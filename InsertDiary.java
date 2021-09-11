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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


//ID ���� �� �����ؾ��ϴµ� ActionListener�� ���� ������ ������ ���, id���� ���� �ȵǱ⿡
//�� Ŭ���� �ȿ��� ó�����־���!!!
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class InsertDiary extends JFrame implements ActionListener{

	//�𵨵�
	DiaryModel diaryModel = new DiaryModel();
	DiaryDTO diaryDTO = new DiaryDTO();
	
	Container cp;
	
	JLabel background, title, content, img, date, outDate;
	JTextField tfTitle, tfContent, tfImg;
	
	JButton btnImg, btnAdd;
	
	JPanel panel;
	
	
	//�̹��� ���� �غ�
	PhotoDraw photoDraw = new PhotoDraw();
	String imageName;
	
	
	////////////////////////////////////////////////////////
	//�⺻
	public InsertDiary(String id) {
		
		super("We ���̾");
		
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(300, 50, 600, 700);
		//â ������ ����Ұ�
		this.setResizable(false);
		//������ ���̰� �ϱ�
		initDesign(id);
		this.setVisible(true);
		
	}
	
	
	////////////////////////////////////////////////////////
	//init
	public void initDesign(String id) {
		
		//��� �̹����� �� ���� �Ҵ�
		panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(null);
		
		
		////////////////////////////////////////////////////
		//�̹��� ���
		//���� Ŭ������ Canvas Ŭ���� ȣ�� �ʿ�
		//��濡 �������� ������ �ٸ� �ͺ��� ���� ���;���
		//�̹����� ��µ� â�� ũ�� ����
		photoDraw.setBounds(200, 137, 200, 120);
		//�̹��� ���
		panel.add(photoDraw);
		
		
		/////////////////////////////////////////////////////
		
		//��¥�� �߰�
		date = new JLabel("��¥:");
		date.setBounds(30, 0, 1000, 100);
		date.setFont(new Font("�����ձ۾� �ٽ� ������", Font.BOLD, 40));
		date.setForeground(Color.white);
		panel.add(date);
		
		//��¥ ����� ���� �۾�
		SimpleDateFormat sysdate = new SimpleDateFormat("yyyy�� MM�� dd�� HH�� mm��");
		Calendar time = Calendar.getInstance();
		String todayDate = sysdate.format(time.getTime()); 
		
		//��¥ �־��ֱ�
		outDate = new JLabel(todayDate);
		outDate.setBounds(120, 0, 1000, 100);
		outDate.setFont(new Font("�����ձ۾� �ٽ� ������", Font.BOLD, 35));
		outDate.setForeground(Color.BLACK);
		panel.add(outDate);
		
		//�̹��� ÷��
		img = new JLabel("�̹���:");
		img.setBounds(30, 60, 1000, 100);
		img.setFont(new Font("�����ձ۾� �ٽ� ������", Font.BOLD, 40));
		img.setForeground(Color.white);
		panel.add(img);
		
		//Ÿ��Ʋ
		title = new JLabel("����:");
		title.setBounds(40, 230, 1000, 100);
		title.setFont(new Font("�����ձ۾� �ٽ� ������", Font.BOLD, 40));
		title.setForeground(Color.white);
		panel.add(title);
		
		////////////////////////////////////////////////////
		//��ư �߰�
		btnImg = new RoundButton("+");
		btnImg.setFont(new Font("�޸�����ü", Font.BOLD, 20));
		btnImg.setForeground(Color.black);
		btnImg.setBackground(Color.pink);
		btnImg.setBounds(115, 100, 40, 30);
		btnImg.addActionListener(this);
		panel.add(btnImg);
		
		btnAdd = new RoundButton("�Ϸ�");
		btnAdd.setFont(new Font("�޸�����ü", Font.BOLD, 20));
		btnAdd.setForeground(Color.black);
		btnAdd.setBackground(Color.pink);
		btnAdd.setBounds(520, 620, 50, 30);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//��θ� �־�����ϱ⿡ DTO Ŭ���� ����
				DiaryDTO dto = new DiaryDTO();
				
				//���� �Է� ���� ���
				if(tfTitle.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "������ �Է����ּ���");
					return;
							
				} else if (tfContent.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "������ �Է����ּ���");
					return;
				}
				
				//�־�����ϴ� ��� dto�� �� �־��ֱ�
				//textField�� �ۼ��� �� �ֱ�
				dto.setTitle(tfTitle.getText());
				dto.setContent(tfContent.getText());
				dto.setImg(tfImg.getText());
				
				//���̵𰪵� ����
				diaryModel.insertDiary(dto, id);
				
				//����â �ݱ�
				setVisible(false);
				
			}
		});
		panel.add(btnAdd);
		
		
		////////////////////////////////////////////////////
		//textfield �߰�
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
		//��� �̹��� ����
		background = new JLabel();
		//�̹��� �ڿ� Ȯ���ڸ� ���̴� �� ���� �ʱ�!!      
	    background.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\������Ʈ\\�ڹ� ���� ���̾\\�̹���\\������.jpg"));
	    background.setSize(1000, 700);
	    panel.add(background);
		
	}
	
	
	////////////////////////////////////////////////////////
	//��ư���
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object ob = e.getSource();
		
		//�̹����߰�
		if (ob==btnImg) {
			
			//���� �����ϴ� ��ü - FileDialog
			//���� ���̾�α� ���� - this ����, "�˾�â �̸�", FileDialog.LOAD!!!!
			FileDialog dlg = new FileDialog(this, "�̹��� ��������", FileDialog.LOAD);
			//â ���̰� �ϱ�
			dlg.setVisible(true);
			
			//��� ������ �޼��� ����
			//���̾�α��� ���� ����� ��
			if (dlg.getDirectory()==null) {
				return;
			}
			
			//�̹����� ���
			imageName = dlg.getDirectory() + dlg.getFile();
			
			//�̹����� �󺧿� ���
			tfImg.setText(imageName);
			
			//�̹��� ��� �޼���
			photoDraw.repaint();
			
			
		//�ۼ��Ϸ�	
		}
//		else if (ob==btnAdd) {
//			
//			//��θ� �־�����ϱ⿡ DTO Ŭ���� ����
//			DiaryDTO dto = new DiaryDTO();
//			
//			//�־�����ϴ� ��� dto�� �� �־��ֱ�
//			//textField�� �ۼ��� �� �ֱ�
//			dto.setTitle(tfTitle.getText());
//			dto.setContent(tfContent.getText());
//			dto.setImg(tfImg.getText());
//			
//			//���̵𰪵� ����
//			diaryModel.insertDiary(dto, id);
//			
//			//����â �ݱ�
//			this.setVisible(false);
//		}
	}
	
	
	////////////////////////////////////////////////////////
	//Canvas ����Ŭ���� - extends�� ȣ��
	class PhotoDraw extends Canvas{
		
		//paint �������̵�
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			//String ������ �����ߴ� imageName
			//�̹����� ���õǾ��� ���
			if (imageName!=null) {
				
				//���õ� �̹��� �������� ����
				Image image = new ImageIcon(imageName).getImage();
				
				//������ ������ ũ�� ����
				//g�� ��ü���ϴ� draw image���� ���!!
				//�ȿ� ��µ� �̹��� ũ��
				g.drawImage(image, 0, 0, 200, 120, this);
			}
		}
	}
	
	
	//////////////////////////////////////////////////////
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		new InsertDiary();

//	}

}
