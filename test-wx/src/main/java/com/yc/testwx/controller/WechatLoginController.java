package com.yc.testwx.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/wechat")
public class WechatLoginController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取微信登录二维码
     */
    @GetMapping("/login")
    public String getLoginQrCode(Model model) {
        // 使用测试账号的AppID和AppSecret
        String appId = "wxf33d1fe5e10321da";
        String appSecret = "7525c39b58af836b252741dce2be9c9f";

        // 获取微信登录二维码的相关信息
        String state = UUID.randomUUID().toString();
        String redirectUrl = "http://localhost:8080/wechat/login/callback";
        String qrCodeUrl = "https://open.weixin.qq.com/connect/qrconnect?appid=" + appId +
                "&redirect_uri=" + URLEncoder.encode(redirectUrl) +
                "&response_type=code&scope=snsapi_login&state=" + state + "#wechat_redirect";

        // 将二维码URL和state参数传递给前端页面
        model.addAttribute("qrCodeUrl", qrCodeUrl);
        model.addAttribute("state", state);

        return "login";
    }

    /**
     * 处理微信登录回调
     */
    @GetMapping("/login/callback")
    public String handleLoginCallback(@RequestParam String code,
                                      @RequestParam String state,
                                      HttpServletResponse response) throws Exception {
        // 使用测试账号的AppID和AppSecret
        String appId = "your_appid";
        String appSecret = "your_appsecret";

        // 获取Access Token和Open ID
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId +
                "&secret=" + appSecret +
                "&code=" + code +
                "&grant_type=authorization_code";
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("获取Access Token失败");
        }
        String result = responseEntity.getBody();
        JSONObject json = JSON.parseObject(result);
        String accessToken = json.getString("access_token");
        String openId = json.getString("openid");

        // 将Access Token和Open ID存储到Cookie中，以便后续使用
        Cookie accessTokenCookie = new Cookie("access_token", accessToken);
        accessTokenCookie.setMaxAge(7200);
        accessTokenCookie.setPath("/");
        response.addCookie(accessTokenCookie);

        Cookie openIdCookie = new Cookie("open_id", openId);
        openIdCookie.setMaxAge(7200);
        openIdCookie.setPath("/");
        response.addCookie(openIdCookie);
        // 构建重定向URL
        URI redirectUri = UriComponentsBuilder.fromUriString("http://localhost:8080/wechat/login/check")
                .queryParam("state", state)
                .build()
                .encode()
                .toUri();

        // 重定向到检查登录状态的接口
        return "redirect:" + redirectUri.toString();

    }

    /**
     * 检查微信登录状态
     */
    @GetMapping("/login/check")
    public String checkLoginState(@RequestParam String state,
                                  @CookieValue(required = false) String access_token,
                                  @CookieValue(required = false) String open_id,
                                  Model model) {
        // 判断Access Token和Open ID是否存在
        if (StringUtils.isEmpty(access_token) || StringUtils.isEmpty(open_id)) {
            model.addAttribute("message", "登录失败：无法获取Access Token或Open ID");
            return "error";
        }

        // 使用Access Token和Open ID获取用户信息
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token +
                "&openid=" + open_id +
                "&lang=zh_CN";
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("获取用户信息失败");
        }
        String result = responseEntity.getBody();
        JSONObject json = JSON.parseObject(result);
        String nickname = json.getString("nickname");
        String headimgurl = json.getString("headimgurl");

        // 将用户信息传递给前端页面
        model.addAttribute("nickname", nickname);
        model.addAttribute("headimgurl", headimgurl);

        return "index";
    }
}