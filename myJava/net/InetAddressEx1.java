package net;

import java.net.InetAddress;

public class InetAddressEx1 {

	public static void main(String[] args) {
		try {
			//ip 및 도메인 객체화
			InetAddress add = InetAddress.getLocalHost();
			System.out.println("Host Name : " + add.getHostName());
			System.out.println("Host Address : " + add.getHostAddress());
			//도메인으로 ip주소 확인
			add = InetAddress.getByName("deu.ac.kr");
			System.out.println("deu : " + add.getHostAddress());
			InetAddress adds[] = InetAddress.getAllByName("naver.com");
			for (int i = 0; i < adds.length; i++) {
				System.out.println("Naver IP : " + adds[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
