package org.example;
public class Configuration {
    public Configuration(String configVersion, int buildCount, String author, Mode appMode, Encoding encoding) {
        this.configVersion = configVersion;
        this.buildCount = buildCount;
        this.author = author;
        this.appMode = appMode;
        this.encoding = encoding;
    }




    private final String configVersion;
    private final int buildCount;
    private final String author;
    private final Mode appMode;
    private final Encoding encoding;


}

