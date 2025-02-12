package ch10;

public class ExceptionEx7 {
	int a = 100;		
		public void divide(int b) throws Exception {
			if(b==0) {
				throw new Exception("a를 0으로 나누면 안되요");
			}else {
				System.out.println(a + " / " + b + " = " + a/b);
			}
		}//--divide
		
		public static void main(String[] args) {
			ExceptionEx7 et = new ExceptionEx7();
			try {
				et.divide(10);
				et.divide(0);
			} catch (Exception e) {
				//e.printStackTrace();
				System.err.println(e.getMessage());
			}
		}
}
