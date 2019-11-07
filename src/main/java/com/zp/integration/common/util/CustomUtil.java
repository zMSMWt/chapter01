package com.zp.integration.common.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomUtil {
	
	public static String[] chars36 = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	public static String[] chars62 = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
 
	
	public static String random(int length) {
		
		String accountRandom = "QM";
		Random random = new Random();
		for(int i = 0; i < length; i++) {
			accountRandom += Integer.toString(random.nextInt(36), 36);
		}
		
		return accountRandom;
	}
	
	public static String unRepeatSixCode() {
		String sixChar = "QM";
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		Date date = new Date();
		String time = sdf.format(date);
		//System.out.println(time);
		for (int i = 0; i < time.length() / 2; i++) {
			String singleChar;
			String x = time.substring(i * 2, (i + 1) * 2);
			int b = Integer.parseInt(x);
			if (b < 10) {
				singleChar = Integer.toHexString(Integer.parseInt(x));
			} else if (b >= 10 && b < 36) {
				singleChar = String.valueOf((char) (Integer.parseInt(x) + 55));
			} else {
				singleChar = String.valueOf((char) (Integer.parseInt(x) + 61));
			}
			sixChar = sixChar + singleChar;

		}
		System.out.println("生成一个 8 位不可重复的字符编码是：" + sixChar);
		return sixChar;
	}
	
	
	public static String generateInviteCode()
	{
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++)
		{
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			// 如果是 chars62，则是 x % 62
			//shortBuffer.append(chars36[x % 36]);
			shortBuffer.append(chars62[x % 62]);
		}
		return shortBuffer.toString();
	}

	/**
	 * 验证是否为正确的邮箱号
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		// 1、\\w+表示@之前至少要输入一个匹配字母或数字或下划线 \\w 单词字符：[a-zA-Z_0-9]
		// 2、(\\w+\\.)表示域名. 如新浪邮箱域名是sina.com.cn
		// {1,3}表示可以出现一次或两次或者三次.
		String reg = "\\w+@(\\w+\\.){1,3}\\w+";
		Pattern pattern = Pattern.compile(reg);
		boolean flag = false;
		if (email != null && !"".equals(email)) {
			Matcher matcher = pattern.matcher(email);
			flag = matcher.matches();
		}
		return flag;
	}

	/**
	 * 正则校验是否为汉字
	 * @param param
	 * @return
	 */
	public static boolean isChinese(String param) {
		String regex = "^[\u4E00-\u9FFF()（）]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(param);
		return matcher.matches();
	}
	
	
	public static void main(String[] args) {
		
		// unRepeatSixCode();
		// System.out.println(generateInviteCode());
		
		/*Set<String> set = new HashSet<>();
		int times = 1000000;
		
		for(int i = 0; i < times; i++) {
			String accountRandom = unRepeatSixCode();
			set.add(accountRandom);
		}
		
		System.out.println("重复了 " + (times - set.size()) + " 次！");*/
		
/*		// 测试随机数问题
		int[] array = {1,2,3,1};
		int n = new Random().nextInt(4);
//		while(true) {
			for(int i = 0;i < array.length;i++) {
				System.out.println(array[i]);
				if(n != array[i]) {	
					n = new Random().nextInt(4);
				}else {
					break;
				}
			}
//			break;
//		}
		System.out.println("随机数：" + n);*/

		String type = "12";
		List<String> typeList = Arrays.asList(type.split(","));
		for(String list : typeList) {
			System.out.println(list);
		}

		String email = "xxx@allinkgo.com";
		boolean b = isEmail(email);
		System.out.println(b);

		String src = "全民出游";
		boolean res = isChinese(src);
		System.out.println(res);
		
	}

}
