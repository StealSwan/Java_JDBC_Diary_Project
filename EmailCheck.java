package DiaryProject;


//모듈 기능 세분화 원칙에 따라 클래스 따로 만듦
public class EmailCheck {

	public int EmailCheck(String email) {
		
		int emailCheck =0;
		
		//email에 @ 가 있는가?
		//email에 .이 올바르게 있는가? 
		//email에 특수문자가 있는가? 
		if(email.indexOf("@")== -1 || period(email)==true ||specialCharacter(email)==0) {
			
			//indexOf는 특정 문자나 문자열이 앞에서부터 처음발견되는 인덱스를 반환
			//만약 찾기 못할 경우, "-1"을 반환
			
			emailCheck = 1; 
			} 
		
		// 0이면 이메일 체크결과 이상 없다, 1이면 이상 있다 
		return emailCheck; 
		
	}

	
	public Boolean period(String email) {
	
		//split은 @ 기준으로 앞뒤로 나눈다 
		String spl[] = email.split("@"); 
		
		//spl[0] = id, spl[1] = email주소 
		//last와 index의 값차이를 검사
		//lastIndex of는 주어진 값과 일치하는 부분을 역순으로 탐색해 처음 마주치는 인덱스 값 반환 / 못찾으면 -1 반환
		//뒤에 먼저 특정 값을 찾고 그 값의 (올바른)인덱스를 반환
		int num = spl[1].lastIndexOf(".") - spl[1].indexOf("."); 
		
		boolean clear = false; 
		
		//"@"의 뒷단 도메인 부분의 "." 들의 간격이 4칸이상 떨어지면 이메일이 아니다
		if(num <4); 
		else clear = true;
		
		//"@"의 앞단 "." 있는지 확인 
		if(spl[0].indexOf(".")==-1);
		else clear=true; 
		
		//이메일에 .이 잘못되었을 경우 true를 보낸다 
		
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
		// 이메일 검사했을때 특수문자가 있으면 1을 보내고 없으면 0을 보낸다 
		return result; 
		} 
	}

