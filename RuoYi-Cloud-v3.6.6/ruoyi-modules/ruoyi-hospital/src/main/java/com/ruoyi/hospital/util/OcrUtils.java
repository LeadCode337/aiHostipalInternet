package com.ruoyi.hospital.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.aliyun.tea.TeaException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;

/**
 * @BelongsProject: untitled423
 * @BelongsPackage: com.cykj.util
 * @Author: ch
 * @CreateTime: 2025/5/10 14:32
 * @Description: 阿里云ocr身份证识别
 * @Version: 1.0
 */

@Component
public class OcrUtils {

    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.endpoint}")
    private String endpoint;

    public HashMap<String, String> recognition(String path) {
        com.aliyun.ocr_api20210707.Client client = createClient();
        // 需要安装额外的依赖库，直接点击下载完整工程即可看到所有依赖。
        InputStream bodyStream = null;
        try {
            bodyStream = com.aliyun.darabonba.stream.Client.readFromFilePath(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        com.aliyun.ocr_api20210707.models.RecognizeIdcardRequest recognizeIdcardRequest = new com.aliyun.ocr_api20210707.models.RecognizeIdcardRequest()
                .setBody(bodyStream);
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            com.aliyun.ocr_api20210707.models.RecognizeIdcardResponse resp = client.recognizeIdcardWithOptions(recognizeIdcardRequest, runtime);
            //com.aliyun.teaconsole.Client.log(com.aliyun.teautil.Common.toJSONString(resp));

            JSONObject jsonObject = JSON.parseObject(resp.getBody().getData())
                    .getJSONObject("data")
                    .getJSONObject("face")
                    .getJSONObject("data");


            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("address", jsonObject.getString("address"));
            hashMap.put("ethnicity", jsonObject.getString("ethnicity"));
            hashMap.put("sex", jsonObject.getString("sex"));
            hashMap.put("name", jsonObject.getString("name"));
            hashMap.put("idNumber", jsonObject.getString("idNumber"));
            hashMap.put("birthDate", jsonObject.getString("birthDate"));

            return hashMap;

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
        return null;
    }


    private com.aliyun.ocr_api20210707.Client createClient() {
        try {
            com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                    // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID。
                    .setAccessKeyId(accessKeyId)
                    // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_SECRET。
                    .setAccessKeySecret(accessKeySecret);
            // Endpoint 请参考 https://api.aliyun.com/product/ocr-api
            config.endpoint = endpoint;

            return new com.aliyun.ocr_api20210707.Client(config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
