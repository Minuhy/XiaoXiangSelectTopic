package minuhy.xiaoxiang.lectopic.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 正则工具箱
 * 
 * @author y17mm
 * @time 2023-5-5 21:20:44
 * @version 1.0
 */
public class RegularUtil {
	private static final Logger log = LoggerFactory.getLogger(RegularUtil.class);

	/**
	 * 判断一个字符串是否符合正则表达式
	 * @param reg 正则表达式
	 * @param txt 字符串
	 * @return 符合true或者不符合false
	 * @time 2023-5-5 21:21:04
	 */
	public static boolean contains(String reg, String txt) {
		if (reg != null && txt != null) {
			try {
				Pattern r = Pattern.compile(reg);
				Matcher m = r.matcher(txt);
				return m.matches();
			} catch (PatternSyntaxException patternSyntaxException) {
				log.debug("正则表达式解析错误：{}，{}", reg, patternSyntaxException);
			}
		}
		return false;
	}

}
