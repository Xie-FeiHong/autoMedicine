package cn.nexuslink.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xfh on 2018/12/22.
 */
@Component
public class SendSms {

    //设置必要的参数，都已经固定不需要修改
    //参数1、产品名称
    private static final String product = "Dysmsapi";
    //参数2、产品域名
    private static final String domain = "dysmsapi.aliyuncs.com";
    //参数3、自己的Ak
    private static final String accessKeyId = "LTAIJWEWYaUFegX3";
    private static final String accessKeySecret = "Mzv65CoePIhe0BwL2svbFvlstSOD7E";

    //初始化ascClient,并返回
    public  static IAcsClient getAcsClient() throws ClientException {

        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",accessKeyId,accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou","cn-hangzhou",product,domain);
        return new DefaultAcsClient(profile);
    }



    //发送验证码
    public static Boolean sendSms2(String phoneNumber,String code) {
        System.out.println("寄来的");
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phoneNumber);
        request.setSignName("NMID招新系统");
        request.setTemplateCode("SMS_153326851");  //智能自动售卖保健中药站点手机验证
        JSONObject smsJson = new JSONObject();
        smsJson.put("code", code);
        request.setTemplateParam(smsJson.toString());

        SendSmsResponse sendSmsResponse = null;
        try {
            IAcsClient acsClient = getAcsClient(); // 初始化
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (Exception e) {
            // log.error("发送验证码初始化错误");
            // System.out.println("发送验证码初始化错误");
            e.printStackTrace();
            // 设置错误的日志文件
        } finally {
            //如果发送成功就，验证是否成功
            //查明细
//            boolean flag = false;
//            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//                QuerySendDetailsResponse querySendDetailsResponse = null;
//                try {
//                    Thread.sleep(2000L);
//                    querySendDetailsResponse = querySendDetails(sendSmsResponse.getBizId(), phoneNumber);
//                } catch (Exception e) {
//                    // log.error("调用发送验证码接口错误，phoneNumber = " + phoneNumber + "; code = " + code);
//                    e.printStackTrace();
//                }
//                // System.out.println("短信明细查询接口返回数据----------------");
//                for (QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs()) {
//                    System.out.println("这里：SendStatus=" + smsSendDetailDTO.getSendStatus());
//                    if (smsSendDetailDTO.getSendStatus() == 3 || smsSendDetailDTO.getSendStatus() == 1) {
//                        flag = true;
//                    }
//                }
//            }
//            if (flag)
//                return true;
//            else
//                return false;
        }
        return true;
    }



    public static QuerySendDetailsResponse querySendDetails(String bizId,String phoneNumber) throws ClientException {

        //初始化acsClient,暂不支持region化
        IAcsClient acsClient = getAcsClient();
        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber(phoneNumber);
        //可选-流水号
        request.setBizId(bizId);

        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        Calendar cal= Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);//得到年
        int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
        int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
        String time = ""+year+month+day;
        System.out.println(time);
        SimpleDateFormat ft = new SimpleDateFormat(time);
        request.setSendDate(ft.format(new Date()));
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);
        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
        return querySendDetailsResponse;
    }
}
