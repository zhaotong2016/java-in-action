package hk.com.easyview.risk.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 汇率数据录入表
 * </p>
 *
 * @author Hunter
 * @since 2021-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_exchange_rate")
public class TbExchangeRate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 源货币对
     */
    private String toCurrency;

    /**
     * 目标货币对
     */
    private String fromCurrency;

    /**
     * 汇率
     */
    private String exchangeRate;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新标记位：0否，1是
     */
    private Integer updateFlag;

    /**
     * 0:可用,1:禁用
     */
    private Integer disabled;


}
