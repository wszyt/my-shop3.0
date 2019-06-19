package com.zyt.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 文件上传控制器
 */

@Controller
public class UploadController {

    private static final String UPLOAD_PATH = "/static/upload/";

    /**
     *
     * @param dropFile dropzone
     * @param editorFiles wangEditor
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Map<String, Object> upload(MultipartFile dropFile, MultipartFile[] editorFiles, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<> ();

        //Dropzone上传
        if (dropFile != null) {
            result.put ("fileName", writeFile(dropFile, request));
        }


        //wangEditor上传
        if (editorFiles != null && editorFiles.length > 0) {
            List<String> fileNames = new ArrayList<> ();

            for (MultipartFile editorFile : editorFiles) {
                fileNames.add (writeFile(editorFile, request));
            }

            result.put("errno", 0);
            result.put("data", fileNames);

        }

        return result;

    }


    /**
     * 将图片写入指定目录
     * @param multipartFile
     * @param request
     * @return 返回文件完整路径
     */
    private String writeFile(MultipartFile multipartFile, HttpServletRequest request) {
        //获取文件后缀
        String fileName = multipartFile.getOriginalFilename ();
        String fileSuffix = fileName.substring (fileName.lastIndexOf ("."));

        //文件存放路径
        String filePath = request.getSession ().getServletContext ().getRealPath (UPLOAD_PATH);
        File file = new File (filePath);
        if (!file.exists ()) {
            file.mkdir ();
        }

        //将文件写入目标目录
        file = new File (filePath, UUID.randomUUID () + fileSuffix);
        try {
            multipartFile.transferTo (file);
        } catch (IOException e) {
            e.printStackTrace ();
        }


            String serverPath = request.getScheme () + "://" + request.getServerName () + ":" + request.getServerPort ();
            return serverPath + UPLOAD_PATH + file.getName ();


    }
}
