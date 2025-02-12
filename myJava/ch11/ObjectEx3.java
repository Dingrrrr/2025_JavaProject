package ch11;

class Member {
	
	String id;
	
	public Member(String id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		//instanceof 연산자 : 타입 비교 연산자
		if(obj instanceof Member) {
			Member member = (Member) obj; //메게변수 타입이 Member이기 때문에 당연히 가능
			//id도 String, member, id String 결국은 equals 메소드 String
			if(id.equals(member.id)) {
				return true;
			}
		}
		return false;
	}
}

public class ObjectEx3 {
	public static void main(String[] args) {
		Member m1 = new Member("aaa");
		Member m2 = new Member("aaa");
		Member m3 = new Member("bbb");
		if(m1.equals(m2))
			System.out.println("m1과 m2는 같습니다.");
		else
			System.out.println("m1과 m2는 다릅니다.");
		
		if(m1.equals(m3))
			System.out.println("m1과 m3는 같습니다.");
		else
			System.out.println("m1과 m3는 다릅니다.");
	}
}
