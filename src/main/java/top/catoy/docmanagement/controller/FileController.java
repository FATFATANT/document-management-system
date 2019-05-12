package top.catoy.docmanagement.controller;



import jdk.nashorn.internal.ir.ReturnNode;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.catoy.docmanagement.domain.DocTableInfo;
import top.catoy.docmanagement.domain.ResponseBean;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
public class FileController {

    @Value("${com.sxito.custom.windows-path}")
    private String windowsuploadPath;

    @Value("${com.sxito.custom.linux-path}")
    private String linuxuploadPath;


    /***
     * 单文件上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseBean uploadFile(@Param("file") MultipartFile file) {
        String upload = null;
        System.out.println(file.getOriginalFilename() + "------------00000---------");
        if (System.getProperty("os.name").equals("Windows 10")) {
            upload = windowsuploadPath;
        } else if (System.getProperty("os.name").equals("Linux")) {
            upload = linuxuploadPath;
        }
        else if (System.getProperty("os.name").equals("Windows 7")) {
            upload = windowsuploadPath;
        }
//        return null;
        if (Objects.isNull(file) || file.isEmpty()) {
            return new ResponseBean(ResponseBean.FAILURE, "文件为空,请重新上传", null);
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(upload + file.getOriginalFilename());
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(upload));
            }
            Files.write(path, bytes);
            return new ResponseBean(ResponseBean.SUCCESS, "上传成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(ResponseBean.FAILURE, "上传失败", null);
        }
    }


    /***
     *
     * @return
     */
    @RequestMapping(value = "/downLoadFile", method = RequestMethod.POST)
    public void downLoadFife(@RequestParam("docs") String filelist, HttpServletResponse response) {
        String download = null;
        System.out.println(filelist);
        JSONArray json = JSONArray.fromObject(filelist ); // 首先把字符串转成 JSONArray  对象
        String fileName = "";
        for(int i =0 ;i < json.size();i++){
            JSONObject job = json.getJSONObject(i);
            fileName = (String) job.get("fileName") +"."+ (String)job.get("type");
            if (System.getProperty("os.name").equals("Windows 10")) {
                download = windowsuploadPath;
            } else if (System.getProperty("os.name").equals("Linux")) {
                download = linuxuploadPath;
            }
            System.out.println(fileName);
            response.setHeader("content-type","application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            byte[] buff = new byte[1024];
            BufferedInputStream bis = null;
            OutputStream os = null;
            try {
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(new File(download
                        + fileName)));
                int k = bis.read(buff);
                while (k != -1) {
                    os.write(buff, 0, buff.length);
                    os.flush();
                    k = bis.read(buff);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }



        }


    }
}
