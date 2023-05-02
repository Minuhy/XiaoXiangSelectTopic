package minuhy.xiaoxiang.lectopic.bean.common;

import minuhy.xiaoxiang.lectopic.util.TimeUtil;

/**
 * 基础Bean类
 * 
 * @author y17mm
 * @time 2023-5-1 18:59:33
 * @version 1.0
 */
public class BaseBean {
	/**
	 * 最后一次更新时间
	 */
	private long lastUpdateTime;
	/**
	 * 更新时间间隔
	 */
	private int updateInterval;
	
	public BaseBean() {
		
	}
	
	/**
	 * 传入更新时间间隔并初始化
	 * @param updateInterval 更新时间间隔
	 */
	public BaseBean(int updateInterval) {
		this.updateInterval = updateInterval;
	}
	
	/**
	 * 重置更新时间（可立即更新）
	 * 
	 * @time 2023-5-1 19:00:29
	 */
	public void resetUpdateTime() {
		lastUpdateTime=TimeUtil.getTimestampSe();
	}
	
	/**
	 * 判断是否可以更新
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
