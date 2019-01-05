import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyvmsapi.model.v20170525.SmartCallRequest;
import com.aliyuncs.dyvmsapi.model.v20170525.SmartCallResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 只能用于接收云通信的消息，不能用于接收其他业务的消息
 * 短信上行消息接收demo
 */
public class Main {

    public static void main(String[] args) throws ClientException {
        String accessKeyId = "appid";
        String accessKeySecret = "appsecret";
        String buyNumber = "buynumber";
        String phoneNumber = "phonenumber";

        String product = "Dyvmsapi";
        String domain = "dyvmsapi.aliyuncs.com";

        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        SmartCallRequest request = new SmartCallRequest();
        //必填-被叫显号,可在语音控制台中找到所购买的显号
        request.setCalledShowNumber(buyNumber);
        //必填-被叫号码
        request.setCalledNumber(phoneNumber);
        //必填-语音文件ID
        request.setVoiceCode("13677e25-eadc-4fa6-a3a4-f88a71714021.wav");
        //可选-外部扩展字段
//        request.setOutId("yourOutId");
        request.setSpeed(1);
        request.setVolume(2);
        request.setMuteTime(10000);
        request.setPauseTime(800);
        request.setActionCodeBreak(false);
//        request.setAsrModelId("2070aca1eff146f9a7bc826f1c3d4d31");
        //hint 此处可能会抛出异常，注意catch
        SmartCallResponse smartCallResponse = acsClient.getAcsResponse(request);
        System.out.println(smartCallResponse.getCallId());
        System.out.println(smartCallResponse.getCode());
        System.out.println(smartCallResponse.getMessage());
        System.out.println(smartCallResponse.getRequestId());
    }
}
