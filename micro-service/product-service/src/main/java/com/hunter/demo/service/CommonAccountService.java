package com.hunter.demo.service;

import com.hunter.demo.domain.CommonAccount;
import java.util.List;

/**
 * 账户(CommonAccount)表服务接口
 *
 * @author makejava
 * @since 2021-08-05 10:37:50
 */
public interface CommonAccountService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CommonAccount queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<CommonAccount> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param commonAccount 实例对象
     * @return 实例对象
     */
    CommonAccount insert(CommonAccount commonAccount);

    /**
     * 修改数据
     *
     * @param commonAccount 实例对象
     * @return 实例对象
     */
    CommonAccount update(CommonAccount commonAccount);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}