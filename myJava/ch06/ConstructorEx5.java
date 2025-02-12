package ch06;

class Normal {
	
	int a;
	String str;
	
	Normal() {
		/*생성자 기본 기능, 추가*/
	}
	
	Normal(int a) {
		this.a = a;
		/*생성자 기본 기능, 추가*/
	}
	
	Normal(String str) {
		this.str = str;
		/*생성자 기본 기능, 추가*/
	}
	
	Normal(int a,String str) {
		this.a = a;
		this.str = str;
		/*생성자 기본 기능, 추가*/
	}
	
}

class Smart {
	
	int a;
	String str;
	
	Smart() {
		this(0, "Hi~");
	}
	
	Smart(int a) {
		this(a, "Hi~");
	}
	
	Smart(String str) {
		//this() 생성자 호출 시 super() 호출은 불필요
		//super와 동일하게 반드시 첫번째 라인에 호출
		//super도 첫번째, this도 첫번째 <- 절대 같이 호출 할 수 없음.
		this(0, str);
	}
	
	Smart(int a, String str) {
		super();
		this.a = a;
		this.str = str;
		/*생성자 기본 기능*/
	}
}

public class ConstructorEx5 {

	public static void main(String[] args) {

	}

}
