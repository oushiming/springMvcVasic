package com.imooc.common;

import org.codehaus.jackson.map.ObjectMapper;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

public class JsonUtil {



    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        // 对象的所有字段全部列入
        objectMapper.setSerializationInclusion(Inclusion.ALWAYS);

        // 取消默认转换timestamps形式
        objectMapper.configure(
                SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);

        // 忽略空Bean转json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,
                false);

        // 所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        // objectMapper.setDateFormat(new
        // SimpleDateFormat(DateTimeUtil.STANDARD_FORMAT));

        // 忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper
                .configure(
                        DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                        false);
    }

    public static <T> String objToString(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper
                    .writeValueAsString(obj);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> String objToStringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper
                    .writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T stringToObj(String str, Class<T> clazz) {
        if (str == null || clazz == null) {
            return null;
        }

        try {
            return clazz.equals(String.class) ? (T) str : objectMapper
                    .readValue(str, clazz);
        } catch (Exception e) {

            return null;
        }
    }

    public static <T> T stringToObj(String str, TypeReference<T> typeReference) {
        if (str == null || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str
                    : objectMapper.readValue(str, typeReference));
        } catch (Exception e) {

            return null;
        }
    }

    public static <T> T stringToObj(String str, Class<?> collectionClass,
                                    Class<?>... elementClasses) {
        JavaType javaType = objectMapper.getTypeFactory()
                .constructParametricType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (Exception e) {
            return null;
        }
    }
}
