package hk.com.easyview.risk.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 风控配置
 * </p>
 *
 * @author Hunter
 * @since 2021-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ts_risk_config")
public class TsRiskConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    private Integer rootOrgId;

    /**
     * 类型：0-client 1-account
     */
    private Integer baseType;

    /**
     * 风控类型：0-isin
     */
    private Integer type;

    /**
     * 触发值
     */
    private Integer triggerValue;

    /**
     * 状态：0=正常，1=禁用
     */
    private Integer status;

    /**
     * 创建人
     */
    private Integer creator;

    /**
     * 更新人
     */
    private Integer updatePerson;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
