package top.catoy.docmanagement.service;

import top.catoy.docmanagement.domain.LogSearchParams;
import top.catoy.docmanagement.domain.ResponseBean;


public interface LogService {
    ResponseBean insertLog(int userId, String opName, String opLabel);

    ResponseBean getAllLogs(int currentPage, int pageSize);

    //根据用户名或操作类型模糊查询日志
//    ResponseBean getLogsBySearchParam(String searchParam,int currentPage,int pageSize);

    //根据时间段查询日志
//    ResponseBean getLogsByTime(Date start,Date end);

    //多条件查询
    ResponseBean getLogsBySearchParam(LogSearchParams logSearchParams);
}
