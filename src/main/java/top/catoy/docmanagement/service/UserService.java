package top.catoy.docmanagement.service;

import top.catoy.docmanagement.domain.ResponseBean;
import top.catoy.docmanagement.domain.User;


/**
 * @description:
 * @author: xjn
 * @create: 2019-04-26 16:13
 **/
public interface UserService{
   User getUserByName(String userName);

   ResponseBean getAllUsers();

   ResponseBean Login(String username, String password);
}
