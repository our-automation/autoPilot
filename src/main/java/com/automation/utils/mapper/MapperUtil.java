package com.automation.utils.mapper;

import com.automation.utils.exceptions.APIException;
import com.automation.utils.logger.ILogger;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;

/**
 * @author Maheswara
 * @created on 28/06/21
 */
@Slf4j
@Component
public class MapperUtil {
    private static ObjectMapper mapper = new ObjectMapper();
    private static ObjectMapper mapperDisableSnakeCase = new ObjectMapper();

    static {

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ssZ"));


        mapperDisableSnakeCase.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapperDisableSnakeCase.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapperDisableSnakeCase.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapperDisableSnakeCase.setDateFormat(new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ssZ"));
        mapperDisableSnakeCase.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static ObjectMapper getMapperDisableSnakeCase() {
        return mapperDisableSnakeCase;
    }

    public static <T> T getObject(String response, Class<T> classType) {
        try {
            return MapperUtil.getMapper().readValue(response, classType);
        } catch (IOException e) {
            log.error("Failed to convert to " + classType + "\n" + e);
            throw new APIException("Failed to convert to " + classType + "\n" + e);
        }
    }

    public static <T> T getObject(File file, Class<T> classType) {
        try {
            return MapperUtil.getMapper().readValue(file, classType);
        } catch (IOException e) {
            log.error("Failed to convert to " + classType + "\n" + e);
            throw new APIException("Failed to convert to " + classType + "\n" + e);
        }
    }

    public static <T> Collection<T> getCollectionObject(Class<T> classType, String response) {

        CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(Collection.class, classType);
        Collection<T> resultDto = null;
        try {
            return MapperUtil.getMapper().readValue(response, typeReference);
        } catch (IOException e) {
            log.error("Failed to convert to " + classType + "\n" + e);
            throw new APIException("Failed to convert to " + classType + "\n" + e);
        }
    }

    public static String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert to Json String \n" + e);
            throw new APIException("Failed to convert to Json String \n" + e);
        }
    }


    public static <T> T getDisableSnakeCaseObject(String response, Class<T> classType) {
        try {
            return mapperDisableSnakeCase.readValue(response, classType);
        } catch (IOException e) {
            log.error("Failed to convert to " + classType + "\n" + e);
            throw new APIException("Failed to convert to " + classType + "\n" + e);
        }
    }

    public static <T> T getDisableSnakeCaseObject(File file, Class<T> classType) {
        try {
            return mapperDisableSnakeCase.readValue(file, classType);
        } catch (IOException e) {
            log.error("Failed to convert to " + classType + "\n" + e);
            throw new APIException("Failed to convert to " + classType + "\n" + e);
        }
    }

    public static <T> Collection<T> getDisableSnakeCaseCollectionObject(Class<T> classType, String response) {

        CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(Collection.class, classType);
        Collection<T> resultDto = null;
        try {
            return mapperDisableSnakeCase.readValue(response, typeReference);
        } catch (IOException e) {
            log.error("Failed to convert to " + classType + "\n" + e);
            throw new APIException("Failed to convert to " + classType + "\n" + e);
        }
    }

    public static String toDisableSnakeCaseJson(Object object) {
        try {
            return mapperDisableSnakeCase.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert to Json String \n" + e);
            throw new APIException("Failed to convert to Json String \n" + e);
        }
    }
}
