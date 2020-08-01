package com.homework.sws.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class CSVLineMapper {

    private static final Logger LOG = LoggerFactory.getLogger(CSVLineMapper.class);

    <T> List<T> mapFrom(Stream<List<String>> csvDataLines, List<String> csvHeaderLine, Class<T> cls)
            throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<Field> fields = new ArrayList<>();
        for (String s : csvHeaderLine) {
            String toLowerCase = s.toLowerCase();
                Field declaredField = cls.getDeclaredField(toLowerCase);
                declaredField.setAccessible(true);
                fields.add(declaredField);
        }
        List<T> instancesList = new ArrayList<>();
        Iterator<List<String>> csvDataLinesIterator = csvDataLines.iterator();
        while (csvDataLinesIterator.hasNext()) {
            instancesList.add(this.getInstance(csvDataLinesIterator.next(), cls, fields));
        }
        fields.forEach(field -> field.setAccessible(false));
        return instancesList;
    }

    private <T> T getInstance(List<String> csvLineElements, Class<T> cls, List<Field> clsFields)
            throws IllegalAccessException, InstantiationException {
        Iterator<Field> fieldsIterator = clsFields.iterator();
        Iterator<String> csvElementsIterator = csvLineElements.iterator();
        T newInstance = cls.newInstance();
        while (fieldsIterator.hasNext() && csvElementsIterator.hasNext()) {
            Field field = fieldsIterator.next();
            field.set(newInstance, this.toObject(field.getType(),csvElementsIterator.next()));
        }
        return newInstance;
    }

    private Object toObject( Class clazz, String value ) {
        if( Boolean.class == clazz ) return Boolean.parseBoolean( value );
        if( Byte.class == clazz ) return Byte.parseByte( value );
        if( Short.class == clazz ) return Short.parseShort( value );
        if( Integer.class == clazz ) return Integer.parseInt( value );
        if( Long.class == clazz ) return Long.parseLong( value );
        if( Float.class == clazz ) return Float.parseFloat( value );
        if( Double.class == clazz ) return Double.parseDouble( value );
        return value;
    }
}
