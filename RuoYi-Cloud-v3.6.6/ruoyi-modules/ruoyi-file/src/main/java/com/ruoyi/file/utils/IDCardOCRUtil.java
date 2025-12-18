package com.ruoyi.file.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.aliyun.ocr_api20210707.Client;
import com.aliyun.tea.TeaException;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: RuoYi-Vue-master
 * @BelongsPackage: com.ruoyi.web.utils
 * @CreateTime: 2025-08-29 10:27
 * @Description: TODO
 * @editor:巴巴巴巴子
 */
public class IDCardOCRUtil {


    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.endpoint}")
    private String endpoint;



    public static Client createClient() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID。
                .setAccessKeyId(accessKeyId)
                // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_SECRET。
                .setAccessKeySecret(accessKeySecret);
        config.endpoint = endpoint;
        return new Client(config);
    }
    public static Map<Object, Object> GetId(String imgPath){
        Map<Object, Object> map = new HashMap<>();
        try {
            Client client = createClient();
            // 需要安装额外的依赖库，直接点击下载完整工程即可看到所有依赖。
            java.io.InputStream bodyStream = com.aliyun.darabonba.stream.Client.readFromFilePath(imgPath);
            com.aliyun.ocr_api20210707.models.RecognizeIdcardRequest recognizeIdcardRequest = new com.aliyun.ocr_api20210707.models.RecognizeIdcardRequest()
                    .setBody(bodyStream);
            com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
            com.aliyun.ocr_api20210707.models.RecognizeIdcardResponse resp = client.recognizeIdcardWithOptions(recognizeIdcardRequest, runtime);
//            com.aliyun.teaconsole.Client.log(com.aliyun.teautil.Common.toJSONString(resp));
            JSONObject jsonObject = JSON.parseObject(resp.body.data);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data").getJSONObject("face").getJSONObject("data");
            map.put("name",jsonObject1.get("name"));
            map.put("sex",jsonObject1.get("sex"));
            map.put("ethnicity",jsonObject1.get("ethnicity"));
            map.put("birthDate",jsonObject1.get("birthDate"));
            map.put("address",jsonObject1.get("address"));
            map.put("idNumber",jsonObject1.get("idNumber"));
        } catch (TeaException error) {
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            System.out.println(error.getMessage());
            // 诊断地址
            System.out.println(error.getData().get("Recommend"));
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            System.out.println(error.getMessage());
            // 诊断地址
            System.out.println(error.getData().get("Recommend"));
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
        return map;
    }
}
