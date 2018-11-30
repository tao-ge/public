package com.ycnet.frms.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface CensusService {
	
	//PageBean queryByConditionBean(FlangePlate flangePlate,Users user,PageBean pb);
	
	
	List<String> saveExcel(MultipartFile[] files,Long userId,String flag);
	
	List<String> check(Long userId,Long orgId);
}
