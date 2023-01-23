package io.benlewis.dropin.configuration;

public enum ConfigType {
    CONFIG ("config.yml"),
    MAPS ("maps.yml");

    private final String fileName;

    ConfigType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}