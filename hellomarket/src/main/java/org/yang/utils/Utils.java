package org.yang.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.yang.pojo.OperationLogs;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import sun.misc.BASE64Encoder;
public class Utils {
	public static HashMap sensitiveWordMap;
	public static String sendEmail(String emailAddress,HttpServletRequest request,String messageContent,String message) {
		String status="";
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.163.com");
			email.setCharset("UTF-8");
			email.addTo(emailAddress);
 
			email.setFrom("15143933787@163.com", "jd商城");
 
			email.setAuthentication("15143933787@163.com", "lvyang0507");
 
			email.setSubject("jd商城");
			email.setMsg(message + messageContent);
			email.send();
			status="发送成功请查看";
		}
		catch(Exception e){
			e.printStackTrace();
			status="发送失败";
		}
		return status;
	}
	public static String MD5Encode(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException {
			MessageDigest messageDigest=MessageDigest.getInstance("MD5");
		    BASE64Encoder base64en = new BASE64Encoder();
		    //加密后的字符串
		    String newString=base64en.encode(messageDigest.digest(string.getBytes("utf-8")));
		    return newString;
	}
	public static String MD5Decode(String newString) {
		char string[]=newString.toCharArray();
		for (int i = 0; i < string.length; i++) {
			string[i]=(char)(string[i]^'t');
		}
		String oldString=new String(string);
		return oldString;
	}
	 public static String getPinYin(String inputString) {
	        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
	        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
	        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	        format.setVCharType(HanyuPinyinVCharType.WITH_V);
	        char[] input = inputString.trim().toCharArray();
	        String output = "";
	        try {
	            for (int i = 0; i < input.length; i++) {
	                if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
	                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
	                    output += temp[0];
	                } else
	                    output += java.lang.Character.toString(input[i]);
	            }
	        } catch (BadHanyuPinyinOutputFormatCombination e) {
	            e.printStackTrace();
	        }
	        return output;
	    }
	 public static String judgeChiese(String string) {
		 Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
			Matcher matcher = pattern.matcher(string);
			if (!(matcher.find())) {
				try {
					string = new String(string.getBytes("iso8859-1"), "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return string;
	 }
	 public static String getCurrentTime() {
		 Calendar calendar=Calendar.getInstance();
		 int year = calendar.get(Calendar.YEAR);
		 int month = calendar.get(Calendar.MONTH)+1;
		 int day = calendar.get(Calendar.DAY_OF_MONTH);
		 int hour = calendar.get(Calendar.HOUR_OF_DAY);
		 int minute = calendar.get(Calendar.MINUTE);
		 int second = calendar.get(Calendar.SECOND);
		 return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
	 }
	 public static String getCurrentDate() {
		 Calendar calendar=Calendar.getInstance();
		 int year = calendar.get(Calendar.YEAR);
		 int month = calendar.get(Calendar.MONTH)+1;
		 int day = calendar.get(Calendar.DAY_OF_MONTH);
		 return year+"-"+month+"-"+day;
	 }
	 public static OperationLogs operationLogs(String currentTime,String content,String status,String username) {
		 Logger logger=Logger.getLogger(Utils.class.getName());
		 OperationLogs operationLogs=new OperationLogs();
		 operationLogs.setOperation_person(username);
		 operationLogs.setOperation_content(content);
		 operationLogs.setOperation_status(status);
		 operationLogs.setOperation_time(currentTime);
		 logger.info(username+"在"+currentTime+content+status);
		 return operationLogs;
	 }
	 public static boolean judgEmpty(String string) {
		 if(string==null||string.equals("")) {
			 return true;
		 }else {
			 return false;
		 }
	 }

	    /**
	     * 初始化敏感词库
	     *
	     * @param sensitiveWordSet 敏感词库
	     */
	    public static synchronized void init(Set<String> sensitiveWordSet) {
	        //初始化敏感词容器，减少扩容操作
	        sensitiveWordMap = new HashMap(sensitiveWordSet.size());
	        for (String sensitiveWord : sensitiveWordSet) {
	            sensitiveWordMap.put(sensitiveWord, sensitiveWord);
	        }
	    }

	    /**
	     * 判断文字是否包含敏感字符
	     *
	     * @param txt 文字
	     * @return 若包含返回true，否则返回false
	     */
	    public static boolean contains(String txt) throws Exception {
	        boolean flag = false;
	        List<String> wordList = segment(txt);
	        for (String word : wordList) {
	            if (sensitiveWordMap.get(word) != null) {
	                return true;
	            }
	        }
	        return flag;
	    }

	    /**
	     * 获取文字中的敏感词
	     *
	     * @param txt 文字
	     * @return
	     */
	    public static Set<String> getSensitiveWord(String txt) throws IOException {
	        Set<String> sensitiveWordList = new HashSet<>();

	        List<String> wordList = segment(txt);
	        for (String word : wordList) {
	            if (sensitiveWordMap.get(word) != null) {
	                sensitiveWordList.add(word);
	            }
	        }
	        return sensitiveWordList;
	    }

	    /**
	     * 替换敏感字字符
	     *
	     * @param txt         文本
	     * @param replaceChar 替换的字符，匹配的敏感词以字符逐个替换，如 语句：我爱中国人 敏感词：中国人，替换字符：*， 替换结果：我爱***
	     * @return
	     */
	    public static String replaceSensitiveWord(String txt, char replaceChar) throws IOException {
	        String resultTxt = txt;
	        //获取所有的敏感词
	        Set<String> sensitiveWordList = getSensitiveWord(txt);
	        String replaceString;
	        for (String sensitiveWord : sensitiveWordList) {
	            replaceString = getReplaceChars(replaceChar, sensitiveWord.length());
	            resultTxt = resultTxt.replaceAll(sensitiveWord, replaceString);
	        }
	        return resultTxt;
	    }

	/*    *//**
	     * 替换敏感字字符
	     *
	     * @param txt        文本
	     * @param replaceStr 替换的字符串，匹配的敏感词以字符逐个替换，如 语句：我爱中国人 敏感词：中国人，替换字符串：[屏蔽]，替换结果：我爱[屏蔽]
	     * @return
	     *//*
	    public static String replaceSensitiveWord(String txt, String replaceStr) throws IOException {
	        String resultTxt = txt;
	        //获取所有的敏感词
	        Set<String> sensitiveWordList = getSensitiveWord(txt);
	        for (String sensitiveWord : sensitiveWordList) {
	            resultTxt = resultTxt.replaceAll(sensitiveWord, replaceStr);
	        }
	        return resultTxt;
	    }
*/
	    /**
	     * 获取替换字符串
	     *
	     * @param replaceChar
	     * @param length
	     * @return
	     */
	    private static String getReplaceChars(char replaceChar, int length) {
	        String resultReplace = String.valueOf(replaceChar);
	        for (int i = 1; i < length; i++) {
	            resultReplace += replaceChar;
	        }

	        return resultReplace;
	    }

	    /**
	     * 对语句进行分词
	     *
	     * @param text 语句
	     * @return 分词后的集合
	     * @throws IOException
	     */
	    private static List segment(String text) throws IOException {
	        List<String> list = new ArrayList<>();
	        StringReader reader = new StringReader(text);
	        IKSegmenter iksegmenter = new IKSegmenter(reader, true);
	        Lexeme lex;//tokenStream
	        while ((lex = iksegmenter.next()) != null) {
	            list.add(lex.getLexemeText());
	        }
	        return list;
	    }
	 
}
