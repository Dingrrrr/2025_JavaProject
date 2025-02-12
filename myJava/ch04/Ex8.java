package ch04;

public class Ex8 {

	public static void main(String[] args) {
		/*문제1.for 문을 이용해서 1~10까지의 합을 구하시오.(식과 함께)
		 * 1 + 2 +.... + 10 = 55*/
		
		//문제2. 1~30사이의 값중에 3의 배수의 합을 구하시오.
		
		//문제3. 1~30사이의 값중에 짝수와 홀수의 합을 각각 구하시오.
		
		/*
		* 문제4. 1~50사이의 3,6,9 게임의 합은? 
		* Hint : %와 /를 사용. 33/10 => 3 
		* sum : 627
		/* 문제5. for문을 이용해서 1부터 200까지의 값 중에서
		 * 각 자리 숫자의 합이 10인 숫자들의 합을 구하시오.
		 * 예: 19, 28, 37...109, 118, 127...
         * sum :1990
		 */
		//문제 1번
		int sum = 0;
		int sum1 = 0;
		int sum2 = 0;
		for(int i = 0; i < 11; i++) {
			sum += i;
			System.out.println("1번 문제 정답 : " + i + "+" + sum);
		}
		System.out.println("----------------------");
		//문제 2번
		for(int i = 0; i<31; i++) {
			if(i%3==0) {
			sum1 += i;
			System.out.println("2번 문제 정답 : " + i + "+" + sum1);
			}
		}
		System.out.println("----------------------");
		//문제 3번
		for(int i=0; i<31; i++) {
			int a = 0;
			if(i%2==0) {
				sum1 += i ;
				System.out.println("짝수 합 : " + sum2);
		
			}
		}
	
		//문제 4
        for (int i = 1; i <= 50; i++) {
            int num = i;
            boolean isThreeSixNine = false;

            // 각 자리 숫자를 확인하며 3, 6, 9가 있는지 체크
            while (num > 0) {
                int digit = num % 10; // 끝자리 숫자
                if (digit == 3 || digit == 6 || digit == 9) {
                    isThreeSixNine = true;
                    break;
                }
                num /= 10; // 다음 자리로 이동
            }

            if (isThreeSixNine) {
                sum += i; // 3, 6, 9가 포함된 숫자라면 합에 추가
            }
        }

        System.out.println("1~50 사이의 3, 6, 9 게임의 합은: " + sum);

		//문제 5번
		/*
		 * for(int i = 0; i < 201; i++) { int digitSum = 0; int num = i; while(num > 0)
		 * { digitSum += num%10; num/=10; } if(digitSum==10) { sum+=i;
		 * //System.out.println(i + "\t"); } } System.out.println(sum);
		 */

        for (int i = 0; i <= 200; i++) {
            int digitSum = 0; // 각 숫자의 자릿수 합
            int num = i; // 현재 숫자를 임시 변수에 저장

            // 각 자릿수를 더하기
            while (num > 0) {
                digitSum += num % 10; // 끝자리 숫자를 더함
                num /= 10; // 숫자를 줄여 나감
            }
            // 자릿수 합이 10인 경우
            if (digitSum == 10) {
                sum += i; // 해당 숫자를 합계에 추가
            }
        }
        System.out.println("0~200 사이에서 자릿수의 합이 10인 숫자들의 합은: " + sum);
    }
}

