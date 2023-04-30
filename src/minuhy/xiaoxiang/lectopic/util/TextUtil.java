package minuhy.xiaoxiang.lectopic.util;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文本工具
 * 
 * @author y17mm
 * @time 2023-2-17 23:16:11
 * @version 1.1
 */
public class TextUtil {

	public static boolean isEmpty(String s) {
		return s == null || s.equals("");
	}

	public static long getStringLenByUtf8(String str) {
		return str.getBytes(StandardCharsets.UTF_8).length;
	}

	public static String maxLen(String s, int len) {
		if (len < 1) {
			return "";
		}

		if (null == s) {
			return "";
		}

		if (s.length() <= len) {
			return s;
		}

		return s.substring(0, len - 1) + "…";
	}

	/**
	 * 把长文字变为 “开始....结束” 的格式
	 * 
	 * @param s   文字
	 * @param len 压缩后长度
	 * @return 压缩后文字
	 */
	public static String maxLenJustify(String s, int len) {
		if (len < 1) {
			return "";
		}

		if (null == s) {
			return "";
		}

		if (s.length() <= len) {
			return s;
		}

		int a = len / 2;
		int b = len - a;

		// System.out.println(a+":"+b);

		if (a > b) {
			a -= 1;
		} else if (b >= a) {
			// 优先砍掉尾巴，在只有两个的时候 n...
			b -= 1;
		}

		// System.out.println(a+":"+b);

		String h = s.substring(0, a);
		String t = s.substring(s.length() - b);

		return h + "…" + t;
	}

	public static String maxLenRight(String s, int len) {
		if (len < 1) {
			return "";
		}

		if (null == s) {
			return "";
		}

		if (s.length() <= len) {
			return s;
		}

		return "…" + s.substring(s.length() - len + 1);
	}

	/**
	 * 如果是obj字符串，则返回字符串，否则返回空
	 * 
	 * @param obj 要判断的对象
	 * @return null or obj
	 */
	public static String getString(Object obj) {
		return getString(obj, null);
	}

	/**
	 * 如果obj是字符串，则返回字符串，否则返回def
	 * 
	 * @param obj 要判断的对象
	 * @param def 默认值
	 * @return def or （string）obj
	 */
	public static String getString(Object obj, String def) {
		if (obj != null) {
			if (obj instanceof String) {
				String s = (String) obj;
				return s;
			}
		}
		return def;
	}

	/**
	 * 清除html格式，只保留文本
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static String delHtmlTag(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		return htmlStr.trim(); // 返回文本字符串
	}

	/**
	 * 判断一个字符串是否满足最短长度
	 * 
	 * @param str 字符串
	 * @param len 最短长度（包括）
	 * @return true：满足条件，false：不满足条件
	 * @time 2023-4-29 22:09:11
	 */
	public static boolean stringMinLen(Object str, int len) {
		if (str instanceof String) {
			return ((String) str).length() >= len;
		}
		return false;
	}

	/**
	 * 判断一个字符串是否满足最长长度
	 * 
	 * @param str 字符串
	 * @param len 最长长度（包括）
	 * @return true：满足条件，false：不满足条件
	 * @time 2023-4-29 22:10:53
	 */
	public static boolean stringMaxLen(Object str, int len) {
		if (str instanceof String) {
			return ((String) str).length() <= len;
		}
		return false;
	}

	/**
	 * 判断一个字符串是否在长度1和长度2之间
	 * 
	 * @param str  字符串
	 * @param len1 长度1（包括）
	 * @param len2 长度2（包括）
	 * @return true：满足条件，false：不满足条件
	 * @time 2023-4-29 22:12:30
	 */
	public static boolean stringBetweenLen(Object str, int len1, int len2) {
		if (len1 > len2) {
			return stringMaxLen(str, len1) && stringMinLen(str, len2);
		} else {
			return stringMaxLen(str, len2) && stringMinLen(str, len1);
		}
	}

	/**
	 * 获取随机字符串
	 * 
	 * @param length 字符串长度
	 * @return 一串随机字符串
	 * @time 2023-4-30 1:28:50
	 */
	public static String getRandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt();
			number = (number < 0 ? -number : number) % str.length();
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}
}