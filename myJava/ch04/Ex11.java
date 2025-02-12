package ch04;

public class Ex11 {

	public static void main(String[] args) {
		
		//중첩for문 
		for (int i = 0; i < 2; i++/*0,1*/) {
			for (int j = 0; j < 3; j++/*0,1,2*/) {
				System.out.println("i:"+i+",j:"+j);
			}//--for2
		}//--for1
		System.out.println("-------------");
		
		/*i와 j의 합이 10초과인 값을 제외한 숫자를 표시*/
		for (int i = 1; i < 11; i++) {
			for (int j = 1;j < 11; j++) {
				if(i+j>10) {
					break;
				}//--if
				System.out.println(i+"+"+"="+(i+j));
			}//--for2
		}//--for1
		System.out.println("-------------");
		
		//중첩 반복문 시작할 때 라벨값 지정
		aaa:
			for (int i = 0; i <5; i++) {
				for (int j = 0; j < 10; j++) {
					if(i+j>10) {
						break aaa;
					}//--if
				}//--for2
			}//--for1
		System.out.println("-------------");
		
		//문제:중첩 for 문을 이용해서 i+j의 합이 30이 넘어면 빠져나오시오
		bbb:
			for (int i = 0;true; i++/*데드코드*/) {
				for (int j = 0;true; j++) {
					if(i+j>30) {break bbb;}
					System.out.println(i + "+" +j + "="+(i+j));
			}
		}
	}
}