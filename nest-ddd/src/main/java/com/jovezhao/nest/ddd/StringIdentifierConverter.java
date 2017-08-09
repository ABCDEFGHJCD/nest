package com.jovezhao.nest.ddd;

import org.dozer.DozerConverter;

/**
 * Created by zhaofujun on 2017/8/9.
 */
public class StringIdentifierConverter extends DozerConverter<StringIdentifier, String> {


    public StringIdentifierConverter() {
        super(StringIdentifier.class, String.class);
    }

    @Override
    public String convertTo(StringIdentifier source, String destination) {
        if (source == null) return destination;
        return source.toValue();
    }

    @Override
    public StringIdentifier convertFrom(String source, StringIdentifier destination) {
        if (source == null) return destination;
        return new StringIdentifier(source);
    }
}
