package minuhy.xiaoxiang.lectopic.bean.common;

import minuhy.xiaoxiang.lectopic.util.TimeUtil;

/**
 * ����Bean��
 * 
 * @author y17mm
 * @time 2023-5-1 18:59:33
 * @version 1.0
 */
public class BaseBean {
	/**
	 * ���һ�θ���ʱ��
	 */
	private long lastUpdateTime;
	/**
	 * ����ʱ����
	 */
	private int updateInterval;
	
	public BaseBean() {
		
	}
	
	/**
	 * �������ʱ��������ʼ��
	 * @param updateInterval ����ʱ����
	 */
	public BaseBean(int updateInterval) {
		this.updateInterval = updateInterval;
	}
	
	/**
	 * ���ø���ʱ�䣨���������£�
	 * 
	 * @time 2023-5-1 19:00:29
	 */
	public void resetUpdateTime() {
		lastUpdateTime=TimeUtil.getTimestampSe();
	}
	
	/**
	 * �ж��Ƿ���Ը���
	 * @return
	 * @time 2023-5-1 19:00:42
	 */
	public boolean isUpdatable() {
		long curTime = TimeUtil.getTimestampSe();
		if(curTime-lastUpdateTime>updateInterval) {
			lastUpdateTime = curTime;
			return true;
		}
		return false;
	}
}
