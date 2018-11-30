package com.ycnet.frms.mapper;

import com.ycnet.frms.bean.RouteNode;

public interface RouteNodeMapper {
    int deleteByPrimaryKey(Long nodeId);

    int insert(RouteNode record);

    int insertSelective(RouteNode record);

    RouteNode selectByPrimaryKey(Long nodeId);

    int updateByPrimaryKeySelective(RouteNode record);

    int updateByPrimaryKey(RouteNode record);
    
    int deleteByRoutePrimaryKey(Long routeId);
}