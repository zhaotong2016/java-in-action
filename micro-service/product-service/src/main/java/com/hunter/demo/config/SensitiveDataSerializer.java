package com.hunter.demo.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.hunter.demo.annotaion.Desensitized;
import com.hunter.demo.annotaion.DesensitizedTypeEnum;
import com.hunter.demo.annotaion.DesensitizedUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import static com.hunter.demo.controller.AccountController.getThreadLocal;

/**
 * 基于jackson-实现脱敏
 */
public class SensitiveDataSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private Desensitized sensitiveData;

    public SensitiveDataSerializer(Desensitized sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    public SensitiveDataSerializer() {
    }


    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (StringUtils.isBlank(value)) {
            gen.writeString(value);
            return;
        }

        ThreadLocal<Boolean> threadLocal =   getThreadLocal();

       boolean result = threadLocal.get();
       if (result){
           if (sensitiveData != null) {
               final DesensitizedTypeEnum typeEnum = sensitiveData.type();

               switch (typeEnum) {
                   case USERNAME:
                       gen.writeString(DesensitizedUtils.userName(value));
                       break;
                   case ACCOUNT_NO:
                       gen.writeString(DesensitizedUtils.accountNo(value));
                       break;
                   default:
               }

           } else {
               gen.writeString(value);
           }

       }else {
           gen.writeString(value);
       }

    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
        Desensitized annotation = property.getAnnotation(Desensitized.class);

        if (annotation != null) {
            return new SensitiveDataSerializer(annotation);
        }
        return this;
    }




}
