package com.automation.utils.file;

import com.automation.SystemProperties;
import com.automation.utils.exceptions.APIException;
import com.automation.utils.mapper.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * @author Maheswara
 * @created on 28/06/21
 */

@Component
@Slf4j
public class PropertiesReader {

    private static final Properties properties = new Properties();

    public Integer getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public Long getLong(String key) {
        return Long.parseLong(properties.getProperty(key));
    }

    public Double getDouble(String key) {
        return Double.parseDouble(properties.getProperty(key));
    }

    public Float getFloat(String key) {
        return Float.parseFloat(properties.getProperty(key));
    }

    public Boolean getBoolean(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    public String getString(String key) {
        return properties.getProperty(key);
    }

    public <T> T getList(String key, Class<T> cls) {
        return MapperUtil.getObject(properties.getProperty(key), cls);
    }

    public static Properties readPropertyFile(File filePath) {
        FileInputStream fis;
        try {
            fis = new FileInputStream(filePath);
            Properties prop = new Properties();
            prop.load(fis);
            fis.close();
            return prop;
        } catch (Exception ex) {
            throw new APIException(ex);
        }
    }

    static {

        try {
            URL resource = PropertiesReader.class.getClassLoader().getResource("/framework.properties");
            if (resource == null) {
                resource = PropertiesReader.class.getClassLoader().getResource("framework.properties");
            }
            doConfigure(resource);
            List<File> files;
            if (Objects.nonNull(SystemProperties.PROP_DIR_PATH)) {
                files = Arrays.asList(new File(SystemProperties.PROP_DIR_PATH).listFiles());
            } else {
                files = Arrays.asList(new File(SystemProperties.USER_DIR + "src/test/resources/properties").listFiles());
            }
            if (files.size() > 0) {
                files.stream().map(PropertiesReader::readPropertyFile).forEach(properties::putAll);
            }
        } catch (Exception ex) {
            log.error("directory not found \n" + ex);
        }
    }

    public static void doConfigure(URL configURL) {
        InputStream istream = null;
        URLConnection uConn;
        try {
            uConn = configURL.openConnection();
            uConn.setUseCaches(false);
            istream = uConn.getInputStream();
            properties.load(istream);
        } catch (Exception e) {
            if (e instanceof InterruptedIOException || e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }

        } finally {
            if (istream != null) {
                try {
                    istream.close();
                } catch (InterruptedIOException ignore) {
                    Thread.currentThread().interrupt();
                } catch (IOException ignore) {
                } catch (RuntimeException ignore) {
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("wewe");
    }
}
