package com.hunter.demo.annotaion;


import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hunter.demo.config.SensitiveDataSerializer;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@Target({ElementType.FIELD, ElementType.METHOD,ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@JsonSerialize(using = SensitiveDataSerializer.class)
public @interface Desensitized {

    DesensitizedTypeEnum type();
}
