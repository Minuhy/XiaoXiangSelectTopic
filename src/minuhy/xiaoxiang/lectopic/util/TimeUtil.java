package minuhy.xiaoxiang.lectopic.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ʱ�乤��
 * 
 * @author y17mm
 * @time 2023-2-17 12:44:11
 * @version 1.0
 */
public class TimeUtil {
	/**
	 * ��ȡʱ��������룩
	 * 
	 * @return
	 * @time 2023-4-30 15:59:11
	 */
	public static long getTimestampMs() {
		return System.currentTimeMillis();
	}

	/**
	 * ��ȡʱ��������룩
	 * 
	 * @return
	 * @time 2023-4-30 15:59:27
	 */
	public static String getTimestampMsStr() {
		return String.valueOf(System.currentTimeMillis());
	}

	/**
	 * ��ȡʱ������룩
	 * 
	 * @return
	 * @time 2023-4-30 15:58:57
	 */
	public static long getTimestampSe() {
		return getTimestampMs() / 1000;
	}

	/**
	 * yyyy-MM-dd HH:mm
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String timestamp2DateTime(long timestamp) {
		// ��ǰʱ������ʱ���ת��Ϊ����
		Date millisecondDate = new Date(timestamp);
		// ��ʽ��ʱ��
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String millisecondStrings = formatter.format(millisecondDate);
		return millisecondStrings;
	}
}
