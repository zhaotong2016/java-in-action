package hk.com.easyview.risk.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 基于客户风控日志表
 * </p>
 *
 * @author Hunter
 * @since 2021-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ts_risk_client_logs")
public class TsRiskClientLogs implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * isin
     */
    private Integer isin;

    /**
     * 客户key
     */
    private Integer customerKey;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 头寸金额占有率
     */
    private BigDecimal positionRatio;

    /**
     * 风控配置阈值
     */
    private BigDecimal limitValue;

    /**
     * 描述信息
     */
    private String desc;

    /**
     * 状态：0=已触发
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

    /**
     * 租户id
     */
    private Integer rootOrgId;


}
