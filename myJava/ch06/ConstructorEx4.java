package ch06;

class Person {
	
	String name;
	int age;
	
	//this : 자신의 객체를 가르키는 키워드
	Person(String name, int age) {
		super(); //Object() <- 자식의 객체가 만들어지기 전에 부모 객체가 먼저 만들어 지기 위한 코드
		//매개변수로 받은 name을 필드 name 리턴하기 위해 this 사용
		this.name = name;
		this.age = age;
	}
	
	//Preson 객체의 정보를 출력하는 메소드
	void displayInfo() {
		System.out.println("Name : " + name);
		System.out.println("Age : " + age);
	}
}

class Employee extends Person {
	String department;
	
	Employee(String name, int age, String department) {
		super(name, age);
		this.department = department;
	}
	
	@Override
	//오버라이딩 : 부모클래스에서 정의한 메소드를 자식클래스에서 재정의 하는 것.
	void displayInfo() {
		super.displayInfo();
		System.out.println("Department : " + department);
	}
}

public class ConstructorEx4 {

	public static void main(String[] args) {
		Employee emp = new Employee("차은우", 27, "개발자");
		emp.displayInfo();
	}
}
