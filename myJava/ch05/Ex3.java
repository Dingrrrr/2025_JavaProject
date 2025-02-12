package ch05;

public class Ex3 {

	public static void main(String[] args) {
		//배열의 복사
		int arr1[] = {1, 2, 3, 4, 5};
		int arr2[] = new int[arr1.length]; //크기를 지정한다.
		System.arraycopy(arr1/*원본 배열*/, 0/*시작점*/, arr2/*카피 배열*/, 0, arr1.length);
		for (int i = 0; i < arr2.length; i++) {
			System.out.println(arr2[i]);
		}
		System.out.println("-----------------");
		for (int a : arr2) {
			System.out.println(a); // i 값을 사용하지 못한다는 단점이 있다.
		}
		System.out.println("-----------------");
		String[] subject = {"Java", "JSP", "Flutter"};
		String[] copySubject = new String[5];
		System.arraycopy(subject, 0, copySubject, 0, subject.length/*3*/);
		for (String st : copySubject) {
			System.out.println(st);
		}
	}

}
