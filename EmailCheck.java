package DiaryProject;


//��� ��� ����ȭ ��Ģ�� ���� Ŭ���� ���� ����
public class EmailCheck {

	public int EmailCheck(String email) {
		
		int emailCheck =0;
		
		//email�� @ �� �ִ°�?
		//email�� .�� �ùٸ��� �ִ°�? 
		//email�� Ư�����ڰ� �ִ°�? 
		if(email.indexOf("@")== -1 || period(email)==true ||specialCharacter(email)==0) {
			
			//indexOf�� Ư�� ���ڳ� ���ڿ��� �տ������� ó���߰ߵǴ� �ε����� ��ȯ
			//���� ã�� ���� ���, "-1"�� ��ȯ
			
			emailCheck = 1; 
			} 
		
		// 0�̸� �̸��� üũ��� �̻� ����, 1�̸� �̻� �ִ� 
		return emailCheck; 
		
	}

	
	public Boolean period(String email) {
	
		//split�� @ �������� �յڷ� ������ 
		String spl[] = email.split("@"); 
		
		//spl[0] = id, spl[1] = email�ּ� 
		//last�� index�� �����̸� �˻�
		//lastIndex of�� �־��� ���� ��ġ�ϴ� �κ��� �������� Ž���� ó�� ����ġ�� �ε��� �� ��ȯ / ��ã���� -1 ��ȯ
		//�ڿ� ���� Ư�� ���� ã�� �� ���� (�ùٸ�)�ε����� ��ȯ
		int num = spl[1].lastIndexOf(".") - spl[1].indexOf("."); 
		
		boolean clear = false; 
		
		//"@"�� �޴� ������ �κ��� "." ���� ������ 4ĭ�̻� �������� �̸����� �ƴϴ�
		if(num <4); 
		else clear = true;
		
		//"@"�� �մ� "." �ִ��� Ȯ�� 
		if(spl[0].indexOf(".")==-1);
		else clear=true; 
		
		//�̸��Ͽ� .�� �߸��Ǿ��� ��� true�� ������ 
		
		return clear; 
		
	} 
	
	
	public int specialCharacter(String email) {
		
		String text[] = { "#", "!","$","%","^","&","*","(",")","-", "_","+","=",",","[","]","{","}",":",";","'","?","<",">"};
		int result = 0; 
		
		for(int i=0; i<text.length; i++) {
			if(email.indexOf(text[i]) == -1) {
				result = 1; 
				} else {
					break; 
				} 
			} 
		// �̸��� �˻������� Ư�����ڰ� ������ 1�� ������ ������ 0�� ������ 
		return result; 
		} 
	}

