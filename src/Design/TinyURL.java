package Design;
import java.util.HashMap;
import java.util.*;
/**
 * 
 * @author koutei
 *
 */


public class TinyURL {
	HashMap<String,Integer> longtoshort = new HashMap<>();
	HashMap<Integer,String> shorttolong = new HashMap<>();
	String elements = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	int counter = 0;
	
	public String encode(String longUrl){
		String shortUrl = base10ToBase62(counter);
		longtoshort.put(longUrl, counter);
		shorttolong.put(counter, longUrl);
		counter++;
		return "http://tinyUrl.com/" + shortUrl;
	}
	
	public String base10ToBase62(int n){
		StringBuilder sb = new StringBuilder();
		while(n!=0){
			sb.insert(0, elements.charAt(n%62));
			n /=62;
		}
		while(sb.length()!=6){
			sb.insert(0,'0');
		}
		return sb.toString();
	}
	
	public String decode(String shortUrl){
		String Url = shortUrl.substring("http://tinyUrl.com/".length());
		int n = base62ToBase10(Url);
		return shorttolong.get(n);
	}
	
	public int base62ToBase10(String Url){
		int n=0;
		for(int i=0;i<Url.length();i++){
			n = n*62 + convert(Url.charAt(i));
		}
		return n;
	}
	
	public int convert(char c){
		if(c>='0' && c<='9')
			return c-'0';
		if(c>='a' && c<='z')
			return c-'a'+10;
		if(c>='A' && c<='Z')
			return c-'A'+36;
		return -1;
	}
	
	public static void main(String[] args) {
        TinyURL test = new TinyURL();
        String s1 = "https://leetcode.com/problems/encode-and-decode-tinyurl/#/description";
        String s2 = "https://zhuanlan.zhihu.com/p/27800261?group_id=867800594176430080";
        String short1 = test.encode(s1);
        String short2 = test.encode(s2);
        System.out.println(short1);
        System.out.println(short2);
        System.out.println(test.decode(short1));
        System.out.println(test.decode(short2));
    }
}
