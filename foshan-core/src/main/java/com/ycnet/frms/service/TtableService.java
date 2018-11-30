package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.bean.Tdisc;
import com.ycnet.frms.bean.Tjumper;

public interface TtableService {

	int saveDisc(List<Tdisc> list);
	
	int saveJumper(List<Tjumper> list);
	
	void deleteByUser(Long userId);
	
	void deleteByDev(String devCode);
}
