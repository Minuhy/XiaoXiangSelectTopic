package minuhy.xiaoxiang.lectopic.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * ���򹤾���
 * 
 * @author y17mm
 * @time 2023-5-5 21:20:44
 * @version 1.0
 */
public class RegularUtil {
	private static final Logger log = LoggerFactory.getLogger(RegularUtil.class);

	/**
	 * �ж�һ���ַ����Ƿ����������ʽ
	 * @param reg ������ʽ
	 * @param txt �ַ���
	 * @return ����true���߲�����false
	 * @time 2023-5-5 21:21:04
	 */
	public static boolean contains(String reg, String txt) {
		if (reg != null && txt != null) {
			try {
				Pattern r = Pattern.compile(reg);
				Matcher m = r.matcher(txt);
				return m.matches();
			} catch (PatternSyntaxException patternSyntaxException) {
				log.debug("������ʽ��������{}��{}", reg, patternSyntaxException);
			}
		}
		return false;
	}

}
