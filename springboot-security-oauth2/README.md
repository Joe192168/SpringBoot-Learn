访问授权页面：

localhost:8080/oauth/authorize?client_id=client&response_type=code&redirect_uri=http://www.baidu.com
此时浏览器会让你输入用户名密码，这是因为 Spring Security 在默认情况下会对所有URL添加Basic Auth认证。默认的用户名为user, 密码是随机生成的，在控制台日志中可以看到。

oauth2

画风虽然很简陋，但是基本功能都具备了。点击Authorize后，浏览器就会重定向到百度，并带上code参数：

这里写图片描述

拿到code以后，就可以调用

POST/GET http://client:secret@localhost:8080/oauth/token
来换取access_token了：

curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'grant_type=authorization_code&code=Li4NZo&redirect_uri=http://www.baidu.com' "http://client:secret@localhost:8080/oauth/token"
注意，URL中的client为上文中通过ClientDetailsServiceConfigurer类指定的clientId。由于authorization_code的授权方式不需要 client_secret, 因此secret可以填写任意值

返回如下：

{
  "access_token": "32a1ca28-bc7a-4147-88a1-c95abcc30556", // 令牌
  "token_type": "bearer",
  "expires_in": 2591999,
  "scope": "app"
}

https://www.cnblogs.com/pangguoming/p/8328731.html