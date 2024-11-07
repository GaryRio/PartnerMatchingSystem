package com.seproject.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SMSsend {

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // 此处为开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAI5tRL1R5TXUBe2Ua9cq2B";
    static final String accessKeySecret = "rdF9POkuk8ze8qOLV0JIpMI4dTJCsY";

    // 用于存储验证码和对应的手机号及过期时间
    // 方便起见，暂时作为public
    public static final Map<String, Map<String, Object>> verificationCodes = new HashMap<>();


    public static SendSmsResponse sendSms(String userPhone) throws ClientException {
        // 随机生成六位数的验证码
        long rand = (long)(Math.random()*900000+100000);
        String code = String.valueOf(rand);

        // 存储验证码，设置验证码有效期为5分钟
        Map<String, Object> internalMap = new HashMap<>();
        internalMap.put("code", code);
        internalMap.put("expiryTime", System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5));
        verificationCodes.put(userPhone, internalMap);


        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(userPhone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("课程设计");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_474955999");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\""+code+"\"}");


        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }

    public static boolean verifyCode(String userPhone, String inputCode) {
        Map<String, Object> codeInfo = verificationCodes.get(userPhone);
        if (codeInfo == null) {
            return false;
        }

        String storedCode = (String) codeInfo.get("code");
        long expiryTime = (Long) codeInfo.get("expiryTime");

        if (System.currentTimeMillis() > expiryTime) {
            // 验证码已过期
            verificationCodes.remove(userPhone);
            return false;
        }
        return inputCode.equals(storedCode);
    }

    public static void main(String[] args) throws ClientException {
        SendSmsResponse sendSms = sendSms("18072758059");

        if(sendSms.getCode().equals("OK")) {
            System.out.println("短信发送成功...."+sendSms.getCode());
        }else {
            System.out.println("短信发送失败...."+sendSms.getCode());
        }
    }
}
