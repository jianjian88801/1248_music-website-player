package com.xunmaw.music.controller;
import com.alibaba.fastjson.JSONObject;
import com.xunmaw.music.entity.Admin;
import com.xunmaw.music.service.AdminService;
import com.xunmaw.music.utils.Consts;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 管理员(Admin)表控制层
 *
 * @author makejava
 * @since 2021-06-04 15:41:36
 */

@Configuration
@RestController
@RequestMapping("admin")
public class AdminController {
    /**
     * 服务对象
     */
    @Resource
    private AdminService adminService;

    @PostMapping("/login/status")
    public Object loginStatus(HttpServletRequest request, HttpSession session){

        JSONObject jsonObject =new JSONObject();

        String name =request.getParameter("name");
        String password=request.getParameter("password");

        boolean flag = adminService.verifyPassword(name,password);
        if(flag){
            //  System.out.println("here");
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登录成功");
            session.setAttribute(Consts.NAME,name);
            return  jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"用户名或密码错误");
        return  jsonObject;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectByKey")
    public Admin selectOne(Integer id) {
        return this.adminService.queryById(id);
    }

}
