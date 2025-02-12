package ch03;

public class Ex7 {

	public static void main(String[] args) {
		// 1.배열선언(대괄호의 n개 많큼 배열 증가)
		int arr[];
		// 2.배열의 크기 할당(3개지만 인덱스 번호는 0부터 시작해서 0, 1, 2)
		arr = new int[3];
		// 3.배열 값 할다(배열은 한번 할당되면 고정임.)
		arr[0] = 10;
		arr[1] = 20;
		arr[2] = 30;
		//arr[4] = 40; 시스템 상에서는 문제가 없지만 실행하게되면 오류가 발생
		System.out.println(arr[0]+arr[1]+arr[2]);
		//new를 쓴 이유 : 배열은 내부적으로 Arrays 객체가 만들어지고 length는 배열의 길이의 변수
		int[] arr2 = new int[5];
		int sum2 = 0;
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = (i+1)*10;
			sum2+=arr2[i]; 
		}
		System.out.println(sum2);
	}
}
