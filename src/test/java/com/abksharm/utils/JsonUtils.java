package com.abksharm.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper mapper = new ObjectMapper();


    public static <T> T getTestData(String path, Class<T> type){
        try(InputStream inputStream = ResourceLoader.getResource(path)){
        return mapper.readValue(inputStream,type);
        } catch (IOException e) {
            logger.error("unable to read test data: {}", path, e);
        }
        return null;
    }

}
