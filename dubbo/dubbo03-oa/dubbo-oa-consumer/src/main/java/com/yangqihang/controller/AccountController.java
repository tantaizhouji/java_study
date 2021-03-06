package com.yangqihang.controller;

import com.yangqihang.entity.Account;
import com.yangqihang.entity.Role;
import com.yangqihang.service.IAccountService;
import com.yangqihang.service.IRoleService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/account")
public class AccountController {

    @Reference(version = "1.0.0")
    private IAccountService accSrv;

    @Reference(version = "1.0.0")
    private IRoleService roleSrv;

    /**
     * 跳转到用户登录页面
     *
     * @return 跳转到templates目录下的/account/login
     */
    @RequestMapping("/login")
    public String login() {
        return "account/login";
    }

    /**
     * 退出操作
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("account");
        return "index";
    }

    /**
     * 用户注册页面
     *
     * @return
     */
    @RequestMapping("/register")
    public String register() {
        return "account/register";
    }

    /**
     * 跳转到个人配置页面
     *
     * @return
     */
    @RequestMapping("/profile")
    public String profile() {
        return "account/profile";
    }

/**
     * 上传文件操作
     *
     * @param fileName
     * @return
     */
/*    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam MultipartFile fileName, HttpServletRequest request) {
        //获取当前登录账户
        Account account = (Account) request.getSession().getAttribute("account");

        Set<MetaData> metaDataSet = new HashSet<MetaData>();
        metaDataSet.add(new MetaData("Author", "yangqihang"));
        metaDataSet.add(new MetaData("CreateDate", "2020-01-06"));

        try {
            StorePath uploadFile = null;
//            uploadFile = fc.uploadFile(fileName.getInputStream(), fileName.getSize(), FilenameUtils.getExtension(fileName.getOriginalFilename()), metaDataSet);
            uploadFile = fc.uploadImageAndCrtThumbImage(fileName.getInputStream(), fileName.getSize(), FilenameUtils.getExtension(fileName.getOriginalFilename()), metaDataSet);
            System.out.println("uploadFile.getPath(): " + uploadFile.getPath());

            account.setLocation(uploadFile.getPath());

            accSrv.updateProfile(uploadFile.getPath(), account);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "/account/profile";
    }*/

    @RequestMapping("/modifyRole")
    public String modifyRole(@RequestParam(value = "id") int id, Model model) {
        Account account = accSrv.findById(id);
        List<Role> roleList = roleSrv.findAll();

        model.addAttribute("account", account);
        model.addAttribute("roleList", roleList);

        return "account/modifyRole";
    }

/*    @RequestMapping("/down")
    @ResponseBody
    public ResponseEntity<byte[]> down() {

        DownloadByteArray cb = new DownloadByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "aaa.xx");

        byte[] bs = fc.downloadFile("group1", "M00/00/00/wKhKb14TCqaASjKIAAPr16LLg6I985.jpg", cb);

        return new ResponseEntity<>(bs, headers, HttpStatus.OK);
    }*/
}
