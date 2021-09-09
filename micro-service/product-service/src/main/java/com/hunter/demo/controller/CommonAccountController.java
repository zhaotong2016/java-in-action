package com.hunter.demo.controller;

import com.hunter.demo.domain.CommonAccount;
import com.hunter.demo.service.CommonAccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 账户(CommonAccount)表控制层
 *
 * @author makejava
 * @since 2021-08-05 10:37:51
 */
@RestController
@RequestMapping("commonAccount")
public class CommonAccountController {
    /**
     * 服务对象
     */
    @Resource
    private CommonAccountService commonAccountService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public CommonAccount selectOne(Long id) {
        return this.commonAccountService.queryById(id);
    }

}