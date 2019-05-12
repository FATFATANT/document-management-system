package top.catoy.docmanagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.catoy.docmanagement.domain.Log;
import top.catoy.docmanagement.domain.LogSearchParams;

import java.util.Date;
import java.util.List;

@Mapper
public interface LogMapper {
    int insertLog(Log log);

    Log selectLogById(@Param("logId") int logId);

    List<Log> getAllLogs();

//    List<Log> getLogsByUserOrLabel(@Param("searchParam") String searchParam);

//    List<Log> getLogsByTime(Date start,Date end);

    List<Log> getLogsBySearchParams(LogSearchParams logSearchParams);

}
