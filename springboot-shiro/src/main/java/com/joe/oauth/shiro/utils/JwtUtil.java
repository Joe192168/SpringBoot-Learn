package com.joe.oauth.shiro.utils;

import com.google.gson.Gson;
import com.joe.oauth.shiro.pojo.User;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Date;

public class JwtUtil {



    /**
     * 创建jwt
     * @param id        加的 盐（自定义的密文）
     * @param issuer    发布者的url地址   （jwt签发人）
     * @param subject   代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志
     * @param ttlMillis 有效时间（多长时间有效）
     * @return
     * @throws Exception
     */
    public String createJWT(String id, String issuer, String subject,String roles,String permissions, long ttlMillis) throws Exception {

        // 指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 生成签名的时候使用的秘钥secret，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。
        // 一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        SecretKey key = generalKey();

        // 下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder()             // 这里其实就是new一个JwtBuilder，设置jwt的body
                            .setId(id)                  // 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                            .setIssuedAt(now)           // iat: jwt的签发时间
                            .setIssuer(issuer)          // issuer：jwt签发人
                            .setSubject(subject)        // sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                            .claim("roles",roles)    //角色
                            .claim("permissions",permissions) //权限
                            .signWith(signatureAlgorithm, key); // 设置签名使用的签名算法和签名使用的秘钥

        // 设置到期时间，在当前的时间上增加+2.5小时
        if (ttlMillis>0) {
            builder.setExpiration(new Date(nowMillis+ttlMillis*1000));
        }
        return builder.compact();
    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public Claims parseJWT(String jwt) throws Exception {
        SecretKey key = generalKey();  //签名秘钥，和生成的签名的秘钥一模一样
        Claims claims = Jwts.parser()               //得到DefaultJwtParser
                .setSigningKey(key)                 //设置签名的秘钥
                .parseClaimsJws(jwt).getBody();     //设置需要解析的jwt

        return claims;
    }

    /**
     * 将盐 加密
     *
     * @return
     */
    public SecretKey generalKey() {
        String stringKey = Constant.JWT_SECRET;/*添加的  盐*/

        // 本地的密码解码
        byte[] encodedKey = Base64.decodeBase64(stringKey);

        // 根据给定的字节数组使用AES加密算法构造一个密钥
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");

        return key;
    }


    public static void main(String[] args) throws ParseException {

        User user = new User("小明","123");
        String subject = new Gson().toJson(user); //将java对象转换为JSON数据
        try {

            JwtUtil util = new JwtUtil();
            String jwt = util.createJWT(Constant.JWT_ID, "admin", subject,"","", Constant.JWT_TTL);
            System.out.println("JWT：" + jwt);

            System.out.println("解密");

            Claims c = util.parseJWT(jwt);
            System.out.println("token码：  "+c.getId());
            System.out.println("该jwt的发布时间 unix 时间戳:  "+c.getIssuedAt());
            System.out.println("发布者的url地址:  "+c.getIssuer());

        }catch (ExpiredJwtException e) {
            System.out.println("token 已失效");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}