package com.hunter.demo.factory;

import com.hunter.demo.annotaion.Desensitized;
import com.hunter.demo.annotaion.DesensitizedFormatter;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class DesensitizedAnnotationFormatterFactory implements AnnotationFormatterFactory<Desensitized> {
    @Override
    public Set<Class<?>> getFieldTypes() {
        Set<Class<?>> hashSet = new HashSet<>();
        hashSet.add(String.class);
        return hashSet;
    }

    @Override
    public Printer<?> getPrinter(Desensitized desensitized, Class<?> fieldType) {
        return getFormatter(desensitized);
    }

    @Override
    public Parser<?> getParser(Desensitized desensitized, Class<?> fieldType) {
        return getFormatter(desensitized);
    }

    private DesensitizedFormatter getFormatter(Desensitized desensitized) {
        DesensitizedFormatter formatter = new DesensitizedFormatter();
        formatter.setTypeEnum(desensitized.type());
        return formatter;
    }

}
