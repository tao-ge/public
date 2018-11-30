package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.core.util.KVBean;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.PipingSection;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.Well;
import com.ycnet.frms.vo.PipingSectionBean;

public interface PipingSectionService {

	Object queryPipingSections(PipingSectionBean pipingSectionBean, PageBean pb, Users user);

	void insertpipingSection(PipingSectionBean pipingSectionBean, Users user);

	void deletePipingSection(Long pipingSectId);
	
	PipingSection selectById(Long pipingSectId);

	void update(PipingSection pipingSection);

	List<Well> queryWell();

}
