package com.lut.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.lut.entity.AddApplyEntity;
import com.lut.service.impl.AddApplyServiceImpl;
import com.lut.service.impl.AdditionServiceImpl;
import com.lut.service.impl.EmployeeServiceImpl;
import com.lut.utils.JWTUtil;
import com.lut.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@RestController
public class AddApplyController {

    @Autowired
    private AddApplyServiceImpl addApplyService;
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private AdditionServiceImpl additionService;


    @PostMapping("/api/addition/reject")
    public String rejectApply(@RequestParam("id") Integer id) {
        addApplyService.rejectApply(id);
        AddApplyEntity addApplyEntity = addApplyService.selectAddApply(id);
        String applyType = addApplyEntity.getApplyType();
        Integer empId = addApplyEntity.getEmpId();
        Date applyDate = addApplyEntity.getApplyDate();
//        BigDecimal applyMoney = addApplyEntity.getApplyMoney();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(applyDate);

        HashMap<String, Object> argsMap = new HashMap<>();
        argsMap.put("applyType", applyType);
        argsMap.put("applyMoney", 0);
        argsMap.put("empId", empId);
        argsMap.put("additionYear", calendar.get(Calendar.YEAR));
//        argsMap.put("additionMonth", calendar.get(Calendar.MONTH) + 1);

        for (int i = calendar.get(Calendar.MONTH) + 1; i <= 12; i++) {
            argsMap.put("additionMonth", i);
            additionService.submitApply(argsMap);
        }
        return "";
    }

    @PostMapping("/api/addition/agree")
    public String agreeApply(@RequestParam("id") Integer id) {
        addApplyService.agreeApply(id);
        AddApplyEntity addApplyEntity = addApplyService.selectAddApply(id);
        String applyType = addApplyEntity.getApplyType();
        Integer empId = addApplyEntity.getEmpId();
        Date applyDate = addApplyEntity.getApplyDate();
        BigDecimal applyMoney = addApplyEntity.getApplyMoney();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(applyDate);

        HashMap<String, Object> argsMap = new HashMap<>();
        argsMap.put("applyType", applyType);
        argsMap.put("applyMoney", applyMoney);
        argsMap.put("empId", empId);
        argsMap.put("additionYear", calendar.get(Calendar.YEAR));
//        argsMap.put("additionMonth", calendar.get(Calendar.MONTH) + 1);

        for (int i = calendar.get(Calendar.MONTH) + 1; i <= 12; i++) {
            argsMap.put("additionMonth", i);
            additionService.submitApply(argsMap);
        }
        return "";
    }

    @PostMapping("/api/addition/download")
    public String downloadApplyFile(@RequestBody String fileName, HttpServletResponse response) {
        HashMap fileMap = JSON.parseObject(fileName, HashMap.class);
        String name = (String) fileMap.get("fileName");
        String resourcesPath = System.getProperty("user.dir");
        File file = new File(resourcesPath + name);
        if (file.exists()) { // 判断文件父目录是否存在
            byte[] buffer = new byte[10240];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            BufferedOutputStream bufferedOutputStream = null;
            try {
                bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int len;
                while ((len = bis.read(buffer)) != -1) {
                    bufferedOutputStream.write(buffer, 0, len);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                bufferedOutputStream.close();
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "ooook";
    }

    @GetMapping("/api/additionapply/search")
    public PageInfo listAddApplySearch(@RequestParam("condition") String condition, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HttpServletRequest request) {
        HashMap conditionMap = JSON.parseObject(condition, HashMap.class); //new TypeReference<HashMap<String,Object>>(){}  or  HashMap.class


        String getToken = request.getParameter("token");
        String username = JWTUtil.getClaimValueByToken(getToken, "username");
        String roleType = JWTUtil.getClaimValueByToken(getToken, "roleType");
        if (getToken != null) {
            if (roleType.equals("employee")) {
                conditionMap.put("username", username);
            }
        } else {
            return null;
        }
        return addApplyService.searchAddApply(conditionMap, pageNum, pageSize);
    }


    @PostMapping("/api/addition/alter")
    public String alterApply(@RequestParam(value = "file", required = false) MultipartFile file,
                             @RequestParam(value = "formData", required = false) String applyEntity,
                             HttpServletRequest request) throws IOException {

        AddApplyEntity JSONApplyEntity = JSON.parseObject(applyEntity, AddApplyEntity.class);

        // 修改记录
        addApplyService.alterApply(JSONApplyEntity);
        // 删文件
        String resourcesPath = System.getProperty("user.dir");
        File delFile = new File(resourcesPath + JSONApplyEntity.getApplyFile());

        if (delFile.exists() && delFile.isFile()) {
            delFile.delete();
        }

        String cookieToken = RequestUtils.getCookieToken(request);
        String username = JWTUtil.getClaimValueByToken(cookieToken, "username");

        String pathName = resourcesPath + "/src/main/resources/file/addition_apply/" + username;
        File path = new File(pathName);
        if (!path.exists()) {
            path.mkdirs();
        }

        // 文件名 存数据库
        String originalFileName = file.getOriginalFilename();
        String fileName = pathName + File.separator + JSONApplyEntity.getId() + "_" + originalFileName;
        String applyFile = "/src/main/resources/file/addition_apply/" + username + File.separator + JSONApplyEntity.getId() + "_" + originalFileName;
        addApplyService.setApplyFile(applyFile, JSONApplyEntity.getId());
        // 写文件
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        InputStream inputStream = file.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] buffer = new byte[10240];
        int len;

        while ((len = bufferedInputStream.read()) != -1) {
            bufferedOutputStream.write(buffer, 0, len);
        }

        bufferedInputStream.close();
        bufferedOutputStream.close();
        inputStream.close();
        fileOutputStream.close();
        return "done";
    }


    @PostMapping("/api/addition/apply")
    // @RequestParam(value = "formData", required = false) AddApplyEntity applyEntity,
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam(value = "formData", required = false) String applyEntity,
                             HttpServletRequest request) throws IOException {

        AddApplyEntity JSONApplyEntity = JSON.parseObject(applyEntity, AddApplyEntity.class);

        String cookieToken = RequestUtils.getCookieToken(request);

        String username = JWTUtil.getClaimValueByToken(cookieToken, "username");

        Integer empId = employeeService.getEmpIdFormUsername(username);
        JSONApplyEntity.setEmpId(empId);

        addApplyService.insertAddApply(JSONApplyEntity);

        String resourcesPath = System.getProperty("user.dir");
        String originalFileName = file.getOriginalFilename();
        String pathName = resourcesPath + "/src/main/resources/file/addition_apply/" + username;
        File path = new File(pathName);
        if (!path.exists()) {
            path.mkdirs();
        }

        String fileName = pathName + File.separator + JSONApplyEntity.getId() + "_" + originalFileName;
        String applyFile = "/src/main/resources/file/addition_apply/" + username + File.separator + JSONApplyEntity.getId() + "_" + originalFileName;

        addApplyService.setApplyFile(applyFile, JSONApplyEntity.getId());
        File newFile = new File(fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
        InputStream inputStream = file.getInputStream();
        byte[] bytes = new byte[10240];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, len);
        }
        fileOutputStream.close();
        inputStream.close();
        return "done";
    }
}
