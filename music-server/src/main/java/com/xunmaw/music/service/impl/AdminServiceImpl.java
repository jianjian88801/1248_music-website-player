package com.xunmaw.music.service.impl;

import com.xunmaw.music.entity.Admin;
import com.xunmaw.music.dao.AdminDao;
import com.xunmaw.music.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理员(Admin)表服务实现类
 *
 * @author makejava
 * @since 2021-06-04 15:45:00
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;

    /**
     * 验证密码是否正确
     * @param username
     * @param password
     * @return
     */
    @Override
    public Boolean verifyPassword(String username, String password) {
        Admin admin = adminDao.verifyPassword(username, password);
        //System.out.println(admin);
        if(admin == null){
            return false;
        }
        return  true;
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Admin queryById(Integer id) {
        return this.adminDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Admin> queryAllByLimit(int offset, int limit) {
        return this.adminDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(Admin admin) {
        int insert = this.adminDao.insert(admin);
        if(insert == 1){
            return true;
        }
        return false;
    }

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(Admin admin) {
        int update = this.adminDao.update(admin);
        if(update == 1){
            return true;
        }
        return false;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.adminDao.deleteById(id) > 0;
    }
}
