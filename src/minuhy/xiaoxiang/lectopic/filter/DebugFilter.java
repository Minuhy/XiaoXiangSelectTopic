package minuhy.xiaoxiang.lectopic.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;



import minuhy.xiaoxiang.lectopic.filter.common.HttpFilter;

@WebFilter("/*")
public class DebugFilter extends HttpFilter {

	@Override
	protected boolean httpIncoming() throws UnsupportedEncodingException, ServletException, IOException {
		/*
		 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
	final Logger log = LoggerFactory.getLogger(DebugFilter.class);
	import java.util.Enumeration;
	import minuhy.xiaoxiang.lectopic.config.CommonConfig;
		if(CommonConfig.isDebug()) {
			int i;
			Enumeration<String> names;
			
			log.debug("����·����{}",request.getRequestURI());
			
			log.debug("���������");
			i=0;
			names = request.getParameterNames();
			while(names.hasMoreElements()) {
				i++;
				String name = names.nextElement();
				String value = request.getParameter(name);
				log.debug("{}. {} = {}",i,name,value);
			}
			if(i==0) {
				log.debug("��");
			}
			
			log.debug("�Ự���ԣ�");
			i=0;
			names = session.getAttributeNames();
			while(names.hasMoreElements()) {
				i++;
				String name = names.nextElement();
				Object value = session.getAttribute(name);
				log.debug("{}. {} = {}",i,name,value);
			}
			if(i==0) {
				log.debug("��");
			}
		}
		*/
		
		return true;
	}
	
}
