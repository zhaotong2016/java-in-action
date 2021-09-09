package com.hunter.demo.service.impl;

import com.hunter.demo.domain.CommonAccount;
import com.hunter.demo.mapper.CommonAccountDao;
import com.hunter.demo.service.CommonAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 账户(CommonAccount)表服务实现类
 *
 * @author makejava
 * @since 2021-08-05 10:37:51
 */
@Service("commonAccountService")
public class CommonAccountServiceImpl implements CommonAccountService {
    @Resource
    private CommonAccountDao commonAccountDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CommonAccount queryById(Long id) {
        return this.commonAccountDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<CommonAccount> queryAllByLimit(int offset, int limit) {
        return this.commonAccountDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param commonAccount 实例对象
     * @return 实例对象
     */
    @Override
    public CommonAccount insert(CommonAccount commonAccount) {
        this.commonAccountDao.insert(commonAccount);
        return commonAccount;
    }

    /**
     * 修改数据
     *
     * @param commonAccount 实例对象
     * @return 实例对象
     */
    @Override
    public CommonAccount update(CommonAccount commonAccount) {
        this.commonAccountDao.update(commonAccount);
        return this.queryById(commonAccount.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.commonAccountDao.deleteById(id) > 0;
    }
}