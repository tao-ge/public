package com.ycnet.frms.service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.vo.TmpsConditionBean;

public interface TmpsService {
	
	PageBean queryByConditionBeans(TmpsConditionBean param,PageBean pb);
	
}
