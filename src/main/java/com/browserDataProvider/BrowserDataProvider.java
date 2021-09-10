package com.browserDataProvider;



import com.util.Helper;

import java.io.IOException;
import java.util.Properties;

public class BrowserDataProvider {

   public Properties properties;


    //
    public BrowserDataProvider() throws IOException {
        properties = Helper.readProperties("src/main/resources/Browser-Data/browserData.properties");

    }
    //

    public String getBrowser() {
        return properties.getProperty("browser");


    }

    public String getUrl() {
        return properties.getProperty("url");


    }

}
