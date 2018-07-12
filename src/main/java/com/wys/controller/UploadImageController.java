package com.wys.controller;

import com.wys.util.ImageUtil;
import com.wys.util.JsonResult;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by wangyushuai@fang.com on 2018/7/12.
 * WebUploader 实例之前我是用.net写的，由于有些小朋友看不懂C#，在这里补发一个java的demo
 */
@Controller
@RequestMapping("/upload")
public class UploadImageController {
    private final static String UPLOAD_IMAGES = "_upload/images/";

    @RequestMapping("")
    public String index() {
        return "/upload";
    }

    @ResponseBody
    @RequestMapping("/add")
    public String add(@RequestParam("file")MultipartFile image, HttpServletRequest request, HttpSession session) throws IOException {
        File imageFolder = new File(session.getServletContext().getRealPath(UPLOAD_IMAGES));
        String fileName = UUID.randomUUID().toString().replace("-","");//生成唯一标识，避免文件名重复
        File file = new File(imageFolder,fileName);
        if (! file.getParentFile().exists())
            file.getParentFile().mkdirs();
        if (image != null) {
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);//工具类，转成统一jpg 格式，不转也可以,此处可省略
            ImageIO.write(img,"jpg",file);
        }
        JsonResult<String> result = new JsonResult<>();
        result.setCode(100);
        result.setMessage("上传成功");
        return new JSONObject(result).toString();
    }
}
