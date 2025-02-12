package net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class URLEx1 {
	public static void main(String[] args) {
		String str = "https://search.naver.com:80/"
				+ "search.naver?where=nexearch"
				+ "&sm=top_hty&fbm=0&ie=utf8"
				+ "&query=java#top";
		try {
			//URL url = new URL(str);
			URI uri = new URI(str);
			URL url = uri.toURL();
			System.out.println("Protocol : " + url.getProtocol());
			System.out.println("Host : " + url.getHost());
			System.out.println("Port : " + url.getPort());
			System.out.println("Path : " + url.getPath());
			System.out.println("Query : " + url.getQuery());
			System.out.println("Filename : " + url.getFile());
			System.out.println("Ref : " + url.getRef());
			url = new URL("http://yahoo.co.kr");
			//지정한 URL에 소스를 리턴
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String line = "";
			while(true) {
				line = br.readLine();
				if(line==null) break;
				System.out.println(line);
			}
			br.close();
			System.out.println("End");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
