package com.hunter.demo.annotaion;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class DesensitizedFormatter implements Formatter<String> {

    private DesensitizedTypeEnum typeEnum;

    public DesensitizedTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(DesensitizedTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    @Override
    public String parse(String str, Locale locale) throws ParseException {
        if (StringUtils.isNotBlank(str)) {
            switch (typeEnum) {
                case USERNAME:
                    str = DesensitizedUtils.userName(str);
                    break;
                case ACCOUNT_NO:
                    str = DesensitizedUtils.accountNo(str);
                    break;
                default:
            }
        }
        return str;
    }

    @Override
    public String print(String str, Locale locale) {
        return str;
    }
}
