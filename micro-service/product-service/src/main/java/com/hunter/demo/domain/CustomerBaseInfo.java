package com.hunter.demo.domain;

import com.hunter.demo.annotaion.Desensitized;
import com.hunter.demo.annotaion.DesensitizedTypeEnum;
import lombok.Data;


@Data
public class CustomerBaseInfo {

    @Desensitized(type = DesensitizedTypeEnum.USERNAME)
    private String userName;

    @Desensitized(type = DesensitizedTypeEnum.ACCOUNT_NO)
    private String accountNo;

    private Integer userId;


    private boolean isDesensitization;

}
