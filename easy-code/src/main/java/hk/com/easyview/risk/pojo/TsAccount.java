package hk.com.easyview.risk.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账户信息表
 * </p>
 *
 * @author Hunter
 * @since 2021-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ts_account")
public class TsAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 登录账户
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别：0女1男
     */
    private Integer sex;

    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 工作电话
     */
    private String workMobile;

    /**
     * 个人电话
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户头像 URI
     */
    private String portraitUri;

    /**
     * 客户办公地址
     */
    private String workStation;

    /**
     * 令牌
     */
    private String token;

    /**
     * 来源：0其他，1用户，2客户
     */
    private Integer source;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 状态：0删除，1已启用，2失效
     */
    private Integer status;

    /**
     * 登录时效
     */
    private Integer logAging;

    /**
     * 客户关键字
     */
    private Integer customerKey;

    /**
     * 员工唯一标识，链接员工端用户模块
     */
    private Integer userKey;

    /**
     * 引导修改密码0：需要修改，1：已经修改
     */
    private Integer guideUpdatePsd;

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 扩展字段1
     */
    private String ext1;

    /**
     * 扩展字段2
     */
    private String ext2;

    /**
     * 扩展字段3
     */
    private String ext3;

    /**
     * 扩展字段4
     */
    private String ext4;

    /**
     * 扩展字段5
     */
    private String ext5;

    /**
     * 融云token
     */
    private String imToken;

    /**
     * 机构id
     */
    private Integer institutionId;

    /**
     * 租户id
     */
    private Integer rootOrgId;


}
