package top.catoy.docmanagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.catoy.docmanagement.domain.Log;
import top.catoy.docmanagement.domain.LogSearchParams;
import top.catoy.docmanagement.domain.ResponseBean;
import top.catoy.docmanagement.domain.User;
import top.catoy.docmanagement.mapper.LogMapper;
import top.catoy.docmanagement.mapper.UserMapper;
import top.catoy.docmanagement.service.LogService;
import top.catoy.docmanagement.service.UserService;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-27 15:25
 **/
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public ResponseBean insertLog(int userId, String opName, String opLabel) {
       try {
           String userName = "";
           User user = userMapper.selectUserById(userId);
           if(user != null){
               userName = user.getUserName();
           }
           Log log = new Log();
           log.setUserId(userId);
           log.setOpName(opName);
           log.setOpLabel(opLabel);
           log.setUserName(userName);
           int sum = logMapper.insertLog(log);
           if(sum > 0){
               return new ResponseBean(ResponseBean.SUCCESS,"日志插入成功",null);
           }else {
               return new ResponseBean(ResponseBean.FAILURE,"日志插入失败",null);
           }
       }catch (RuntimeException r){
           return new ResponseBean(ResponseBean.ERROR,"错误",null);
       }
    }

    @Override
    public ResponseBean getAllLogs(int currentPage, int pageSize) {
        try {
            PageHelper.startPage(currentPage, pageSize);
            List<Log> logs = logMapper.getAllLogs();
            if(logs!=null){
                PageInfo<Log> pageInfo = new PageInfo<>(logs);
                return new ResponseBean(ResponseBean.SUCCESS,"查询成功",pageInfo);
            }else {
                return new ResponseBean(ResponseBean.FAILURE,"查询失败",null);
            }
        }catch (RuntimeException r){
            return new ResponseBean(ResponseBean.ERROR,"错误",null);
        }
    }

    @Override
    public ResponseBean getLogsBySearchParam(LogSearchParams logSearchParams) {
        try {
//            if(logSearchParams.getUserName().length()>0){
//                User user = logMapper.ge(logSearchParams.getUserName());
//                System.out.println("user-----"+user!=null);
//                if(user != null){
//                    int userId = user.getUserId();
//                    logSearchParams.setUserId(userId);
//                }else {
//                    logSearchParams.setUserId(-1);
//                    System.out.println("logSearchParams.setUserId(-1)");
//                }
//            }
//            System.out.println("UserId----------"+logSearchParams.getUserId());
            PageHelper.startPage(logSearchParams.getCurrentPage(), logSearchParams.getPageSize());
            List<Log> logs = logMapper.getLogsBySearchParams(logSearchParams);
            if(logs != null){
                logs.forEach((log) -> {
                    User u = userMapper.selectUserById(log.getUserId());
                    if(u != null){
                        String userName =u.getUserName();
                        log.setUserName(userName);
                    }
                });
                PageInfo<Log> pageInfo = new PageInfo<>(logs);
                return new ResponseBean(ResponseBean.SUCCESS,"查询成功",pageInfo);
            }else {
                return new ResponseBean(ResponseBean.FAILURE,"查询失败",null);
            }
        }catch (RuntimeException r){
            return new ResponseBean(ResponseBean.ERROR,"错误",null);
        }
    }

//    @Override
//    public ResponseBean getLogsBySearchParam(String searchParam,int currentPage,int pageSize) {
//        try {
//            PageHelper.startPage(currentPage, pageSize);
//            if(searchParam != null && searchParam.length()>0){
//                List<Log> logs = logMapper.getLogsByUserOrLabel("%"+searchParam+"%");
//                if(logs != null){
//                    logs.forEach((log) -> {
//                        User user = userMapper.selectUserById(log.getUserId());
//                        if(user != null){
//                            String userName =user.getUserName();
//                            log.setUserName(userName);
//                        }
//                    });
//                    PageInfo<Log> pageInfo = new PageInfo<>(logs);
//                    return new ResponseBean(ResponseBean.SUCCESS,"查询成功",pageInfo);
//                }else {
//                    return new ResponseBean(ResponseBean.FAILURE,"查询失败",null);
//                }
//            }else {
//                return new ResponseBean(ResponseBean.FAILURE,"查询条件不能为空",null);
//            }
//        }catch (RuntimeException r){
//            return new ResponseBean(ResponseBean.ERROR,"错误",null);
//        }
//    }

//    @Override
//    public ResponseBean getLogsByTime(Date start, Date end) {
//        return null;
//    }
}
