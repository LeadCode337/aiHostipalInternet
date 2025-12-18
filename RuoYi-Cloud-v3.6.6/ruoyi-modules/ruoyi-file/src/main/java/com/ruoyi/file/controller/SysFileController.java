package com.ruoyi.file.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.file.config.FileConfig;
import com.ruoyi.file.utils.IDCardOCRUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.file.FileUtils;
import com.ruoyi.file.service.ISysFileService;
import com.ruoyi.system.api.domain.SysFile;

import java.io.File;
import java.util.Map;

/**
 * 文件请求处理
 * 
 * @author ruoyi
 */
@RestController
public class SysFileController
{
    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);

    @Autowired
    private ISysFileService sysFileService;

    @Autowired
    private FileConfig fileConfig;

    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    public R<SysFile> upload(MultipartFile file)
    {
        try
        {
            // 上传并返回访问地址
            String url = sysFileService.uploadFile(file);
            SysFile sysFile = new SysFile();
            sysFile.setName(FileUtils.getName(url));
            sysFile.setUrl(url);
            return R.ok(sysFile);
        }
        catch (Exception e)
        {
            log.error("上传文件失败", e);
            return R.fail(e.getMessage());
        }
    }


    @PostMapping("/uploadIdCard")
    public AjaxResult uploadIdCard(MultipartFile file) {
        try {
            // 1. 上传文件，获取访问 URL
            // 示例返回：http://127.0.0.1:9300/statics/2025/12/17/abc.jpg
            String accessUrl = sysFileService.uploadFile(file);

            // 2. 解析本地文件绝对路径
            String uploadPath = fileConfig.getPath(); // 例如：C:/ruoyi/uploadPath

            // 从 URL 中提取相对路径
            String relativePath = accessUrl;

            // 如果是完整 URL (http开头)，需要去掉域名部分
            if (relativePath.startsWith("http")) {
                // 找到第三个斜杠的位置 (http://host:port/...)
                int thirdSlashIndex = relativePath.indexOf("/", 8);
                if (thirdSlashIndex != -1) {
                    relativePath = relativePath.substring(thirdSlashIndex);
                }
            }

            // 去掉资源映射前缀 (适配 /statics 或 /profile/upload)
            // 根据报错信息，你的环境前缀是 /statics
            if (relativePath.startsWith("/statics")) {
                relativePath = relativePath.replaceFirst("/statics", "");
            } else if (relativePath.startsWith("/profile/upload")) {
                relativePath = relativePath.replaceFirst("/profile/upload", "");
            }

            // 拼接最终本地路径
            // 结果应为：C:/ruoyi/uploadPath/2025/12/17/abc.jpg
            String imPath = uploadPath + relativePath;

            // 3. 校验文件是否存在
            File checkFile = new File(imPath);
            if (!checkFile.exists()) {
                // 如果解析失败，尝试回退逻辑（仅供调试）
                return AjaxResult.error("解析文件路径失败，计算路径为：" + imPath);
            }

            // 4. 调用 OCR 识别
            Map<Object, Object> data = IDCardOCRUtil.GetId(imPath);
            log.info("身份证识别结果：{}", data);

            // 5. 返回结果
            return AjaxResult.success()
                    .put("fileUrl", accessUrl)
                    .put("idCardInfo", data);

        } catch (Exception e) {
            log.error("身份证上传/识别失败", e);
            return AjaxResult.error("身份证识别失败：" + e.getMessage());
        }
    }
}