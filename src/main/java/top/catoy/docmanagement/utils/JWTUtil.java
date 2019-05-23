package top.catoy.docmanagement.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import top.catoy.docmanagement.domain.User;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JWTUtil {

    // 过期时间5分钟
    private static final long EXPIRE_TIME = 24*60*60*1000;

    /**
     * 校验token是否正确
     * @param token 密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            String scrt = "gaoxuSecret";
            Algorithm algorithm = Algorithm.HMAC256(scrt);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    //获取用户信息
    public static User getUserInfo(String token){
        try{
            DecodedJWT jwt = JWT.decode(token);
            int id = jwt.getClaim("userId").asInt();
            String username = jwt.getClaim("username").asString();
            String password = jwt.getClaim("password").asString();
            int departmentId = jwt.getClaim("DepartmentId").asInt();
            String role = jwt.getClaim("role").asString();
            String permissions = jwt.getClaim("permission").asString();
            int isLock = jwt.getClaim("isLock").asInt();

            User user = new User();
            user.setUserId(id);
            user.setUserName(username);
            user.setUserPassword(password);
            user.setDepartmentId(departmentId);
            user.setRole(role);
            user.setPermission(permissions);
            user.setUserLock(isLock);
            return user;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 生成签名,5min后过期
     * @param user 用户
     * @param secret 用户的密码
     * @return 加密的token
     */
    public static String sign(User user, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("userId",user.getUserId())
                    .withClaim("username", user.getUserName())
                    .withClaim("password",user.getUserPassword())
                    .withClaim("DepartmentId",user.getDepartmentId())
                    .withClaim("role",user.getRole())
                    .withClaim("permission",user.getPermission())
                    .withClaim("isLock",user.getUserLock())
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
