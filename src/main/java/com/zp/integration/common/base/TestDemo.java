package com.zp.integration.common.base;

import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

public class TestDemo {
	/**
	 * 对手机号中间四位脱敏处理
	 * @param mobile
	 * @return
	 */
	public static String maskMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) return null;
        return StringUtils.replace(mobile, mobile.substring(3, mobile.length() - 4), "****", 1);
    }
	
	/**
	 * 创建 SHA1 签名
	 * @param signParams
	 * @return SHA1 签名
	 */
	public static String createSignature(SortedMap<String, String> signParams) {
		String sign = sha1Encrypt(sortParams(signParams)).toString().toUpperCase();
		return sign;
	}
	
	/**
	 * 创建 SHA1 签名
	 * @param TOKEN
	 * @param Timestamp
	 * @param AppID
	 * @return
	 */
	public static String createSignature(String TOKEN, String Timestamp, String AppID) {
		SortedMap<String, String> signParams = new TreeMap<String, String>();
		signParams.put("Token", TOKEN);
		signParams.put("Timestamp", Timestamp);
		signParams.put("AppID", AppID);
		return createSignature(signParams);
	}
	
	/**
	 * SHA1 加密
	 * @param ret
	 * @return
	 */
	public static String sha1Encrypt(String ret) {
		if (/*ret == null || ret.length() == 0 || */StringUtils.isEmpty(ret)) {
			return null;
		}
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(ret.getBytes("UTF-8"));
			byte[] md = mdTemp.digest();
			int i = md.length;
			char buf[] = new char[i * 2];
			int j = 0;
			for(int k = 0; k < i; k++) {
				byte b = md[k];
				buf[j++] = hexDigits[b >>> 4 & 0xf];
				buf[j++] = hexDigits[b & 0xf];
			}
			return new String(buf);
		}catch (Exception e) {
			return null;
		}
		
	}
	
	/**
	 * 生成时间戳
	 * @return
	 */
	public static String getTimeStamp() {
		//return String.valueOf(System.currentTimeMillis() / 1000);
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
	}
	
	/**
	 * 根据参数名称对参数进行字典排序
	 * @param params
	 * @return
	 */
	public static String sortParams(SortedMap<String, String> params) {
		StringBuffer sb = new StringBuffer();
		Set<Entry<String, String>> es = params.entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		while(it.hasNext()) {
			Entry<String, String> entry = it.next();
			String k = entry.getKey();
			String v = entry.getValue();
			sb.append(k + "=" + v + "&");
		}
		System.out.println(sb.substring(0, sb.lastIndexOf("&")));
		
		return sb.substring(0, sb.lastIndexOf("&"));
	}
	
	/**
	 * 测试案例
	 * @param path
	 * @throws FileNotFoundException
	 */
	public static void read(String path) throws FileNotFoundException{
		if (!path.equals("a.txt")) {
			throw new FileNotFoundException("文件不存在！");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome!!");
		
		/*System.out.println("*************** 分隔字符串 ***************");
		String a = "sd,sdf";
		String b = a.replaceAll(",", " \\| ");
		System.out.println(b);
		
		System.out.println("*************** 手机脱敏 ***************");
		String result = maskMobile("18969940213");
		System.out.println(result);
		
		System.out.println("*************** 缩短到某长度,用...结尾 ***************");
		String s1 = StringUtils.abbreviate("The quick brown fox jumps over the lazy dog.", 10);
		String s2 = StringUtils.abbreviate("The quick brown fox jumps over the lazy dog.", 15, 10);
		System.out.println("s1：" + s1 + "\ns2：" + s2);*/
		
		
		System.out.println("*************** try...catch...finally 含 return ***************");
		/*
		 * 重点：
		 * 	在 try 块或 catch 块中遇到 return 语句时，
		 * 	finally 语句块将在方法返回之前被执行，
		 * 	另外 finally 语句中也可以有 return 语句，
		 * 	但是尽量避免有 return 语句（会报警告）。
		 * 
		 * 异常注意小结：
		 *	1、运行时异常被抛出可以不处理。即不捕获也不声明抛出。
		 *	2、如果 finally 有 return 语句，永远返回 finally 中的结果，避免该情况。
		 *	3、如果父类抛出了多个异常，子类重写父类方法时，抛出和父类相同的异常或者是父类异常的子类或者不抛出异常。
		 *	4、父类方法没有抛出异常，子类重写父类该方法时也不可抛出异常。此时子类产生该异常，只能捕获处理，不能声明抛出。
		 */
//		try {
//			read("a.txt");
//			return;
//		} catch (FileNotFoundException e) {
//			throw new RuntimeException(e);
//		} finally {
//			System.out.println("最终都会执行？？？");
//			//return;
//		}
		
		System.out.println("*************** null+\"\" 和 null + null 的深入理解 ***************");	
		String str1 = null;
		String str2 = null;
		String str3 = str1 + str2;
		/*
		 * append(String) 源码：
		 * public AbstractStringBuilder append(String str) {    
		 *      if (str == null) 
		 *              str = "null";     
		 *      int len = str.length();       
		 *      ensureCapacityInternal(count + len);  
		 *      str.getChars(0, len, value, count);  
		 *      count += len;      
		 *      return this;   
		 * }
		 */
		StringBuilder sbBuilder = new StringBuilder();
		String str4 = sbBuilder.append((String)null)
				.append((String)null)
				.toString(); // 等价 str3
		String str5 = "" + str1;
		System.out.println(str3); // 结果：nullnull
		System.out.println(str4); // 结果：nullnull
		System.out.println(str5); // 结果：null
		
	}

}
