package minuhy.xiaoxiang.lectopic.util;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �ı�����
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

		return s.substring(0, len - 1) + "��";
	}

	/**
	 * �ѳ����ֱ�Ϊ ����ʼ....������ �ĸ�ʽ
	 * 
	 * @param s   ����
	 * @param len ѹ���󳤶�
	 * @return ѹ��������
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
			// ���ȿ���β�ͣ���ֻ��������ʱ�� n...
			b -= 1;
		}

		// System.out.println(a+":"+b);

		String h = s.substring(0, a);
		String t = s.substring(s.length() - b);

		return h + "��" + t;
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

		return "��" + s.substring(s.length() - len + 1);
	}

	/**
	 * �����obj�ַ������򷵻��ַ��������򷵻ؿ�
	 * 
	 * @param obj Ҫ�жϵĶ���
	 * @return null or obj
	 */
	public static String getString(Object obj) {
		return getString(obj, null);
	}

	/**
	 * ���obj���ַ������򷵻��ַ��������򷵻�def
	 * 
	 * @param obj Ҫ�жϵĶ���
	 * @param def Ĭ��ֵ
	 * @return def or ��string��obj
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
	 * ���html��ʽ��ֻ�����ı�
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static String delHtmlTag(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // ����script��������ʽ
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // ����style��������ʽ
		String regEx_html = "<[^>]+>"; // ����HTML��ǩ��������ʽ

		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // ����script��ǩ

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // ����style��ǩ

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // ����html��ǩ

		return htmlStr.trim(); // �����ı��ַ���
	}

	/**
	 * �ж�һ���ַ����Ƿ�������̳���
	 * 
	 * @param str �ַ���
	 * @param len ��̳��ȣ�������
	 * @return true������������false������������
	 * @time 2023-4-29 22:09:11
	 */
	public static boolean stringMinLen(Object str, int len) {
		if (str instanceof String) {
			return ((String) str).length() >= len;
		}
		return false;
	}

	/**
	 * �ж�һ���ַ����Ƿ����������
	 * 
	 * @param str �ַ���
	 * @param len ����ȣ�������
	 * @return true������������false������������
	 * @time 2023-4-29 22:10:53
	 */
	public static boolean stringMaxLen(Object str, int len) {
		if (str instanceof String) {
			return ((String) str).length() <= len;
		}
		return false;
	}

	/**
	 * �ж�һ���ַ����Ƿ��ڳ���1�ͳ���2֮��
	 * 
	 * @param str  �ַ���
	 * @param len1 ����1��������
	 * @param len2 ����2��������
	 * @return true������������false������������
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
	 * ��ȡ����ַ���
	 * 
	 * @param length �ַ�������
	 * @return һ������ַ���
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