package com.hunter.demo.annotaion;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ValueFilter;

import java.lang.reflect.Field;

/**
 * 基于fastjson 过滤器实现脱敏
 */
public class ValueDesensitizedFilter implements ValueFilter {

    @Override
    public Object process(Object object, String name, Object value) {

        if (null == value || !(value instanceof String) || ((String) value).length() == 0) {
            return value;
        }
        try {

            System.out.println("===:"+JSONObject.toJSONString(object)+"====" + name + "==="+value.toString());
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(object));
            if (jsonObject.containsKey("desensitization") && (boolean)jsonObject.get("desensitization") == true){
                Field field = object.getClass().getDeclaredField(name);
                Desensitized desensitized;
                if (String.class != field.getType() || (desensitized = field.getAnnotation(Desensitized.class)) == null) {

                    return value;
                }
                String valueStr = (String) value;
                DesensitizedTypeEnum typeEnum = desensitized.type();


                switch (typeEnum) {
                    case USERNAME:
                        return DesensitizedUtils.userName(valueStr);
                    case ACCOUNT_NO:
                        return DesensitizedUtils.accountNo(valueStr);
                    default:
                }
            }

        } catch (NoSuchFieldException e) {
            return value;
        }
        return value;
    }

}
