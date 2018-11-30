package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.SiteCode;

public interface SiteCodeMapper {
    int deleteByPrimaryKey(Long siteCodeId);

    int insert(SiteCode record);

    int insertSelective(SiteCode record);

    SiteCode selectByPrimaryKey(Long siteCodeId);

    int updateByPrimaryKeySelective(SiteCode record);

    int updateByPrimaryKey(SiteCode record);
    
    SiteCode selectSiteCode();

    //导出数据库  刘沧海 2017/10/13
	List<SiteCode> queryList();
}