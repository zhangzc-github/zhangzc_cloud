package com.zhangzc.cloud.admin;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhangzc.cloud.admin.service.SysUserService;
import com.zhangzc.cloud.upms.api.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudAdminApplicationTests {
    @Autowired
    SysUserService sysUserService;
    @Test
    void contextLoads() {
        SysUser user = sysUserService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, "admin"));
        System.out.println(user);
    }

}
