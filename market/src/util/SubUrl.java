package util;

public class SubUrl {
	public static String subUrl(String url){
		 String finalurl=url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
		 return finalurl;
	}
}
