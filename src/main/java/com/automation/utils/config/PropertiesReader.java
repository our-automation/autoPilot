package com.automation.utils.config;

import com.automation.utils.SystemProperties;
import com.automation.utils.exceptions.APIException;
import com.automation.utils.logger.ILogger;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author Maheswara
 * @created on 28/06/21
 */

@Component
public class PropertiesReader implements ILogger {
    public static Properties properties = new Properties();

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

    static {

        List<File> files = new ArrayList<>();
        try {
            URI uri = PropertiesReader.class.getResource("/properties/framework.properties").toURI();
            files.add(new File(uri));
            files = Arrays.asList(new File(SystemProperties.USER_DIR + "/src/main/resources/properties").listFiles());
        } catch (Exception ex) {
            log.error("directory not found \n" + ex);
        }
        if (files != null) {
            for (File file : files) {
                properties.putAll(readPropertyFile(file));
            }
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


    public static Properties readPropertyFile(File filePath) {

        FileInputStream fis = null;
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

}
