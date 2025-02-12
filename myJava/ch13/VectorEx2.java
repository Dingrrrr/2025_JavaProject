package ch13;

import java.util.Vector;

public class VectorEx2 {
	public static void main(String[] args) {
		String city[] = {"노르웨이","스위스","뉴질랜드","덴마크","캐나다"};
		Vector vec1 = new Vector();
		// Vector<String> <- 제네릭(Generic : 포괄적인) : 처음부터 벡터에 저장할 타입을 지정하는 것
		Vector<String> vec2 = new Vector<String>();
		for (int i = 0; i < city.length; i++) {
			vec1.add(city[i]);
			vec2.add(city[i]);
		}
		for (int i = 0; i < vec1.size(); i++) {
			String str1 = (String)vec1.get(i);
			String str2 = vec2.get(i);
			System.out.println(str1 + " : " + str2);
		}
		//foreach 사용법
		for (Object obj : vec1) {}
		for (String str : vec2) {}
		//명시적으로 지정해서 삭제
		vec2.remove(0);
		System.out.println(vec2.size());
		//모든 요소 삭제
		vec2.removeAllElements();
		System.out.println(vec2.size());
		System.out.println(vec2.capacity());
		//용량을 요소 크기 만큼 줄이기
		System.out.println(vec2.capacity());
	}
}
