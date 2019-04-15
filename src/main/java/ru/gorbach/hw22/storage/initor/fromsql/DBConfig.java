package ru.gorbach.hw22.storage.initor.fromsql;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum DBConfig {
    URL("custom.database.url"),
    USER("custom.database.user"),
    PASSWORD("custom.database.password"),
    DRIVER("custom.database.driver");

    private String propName;
    private static Map<String, DBConfig> propNameDbConfigMap = new HashMap<>();

    static {
        for (DBConfig conf : DBConfig.values()) {
            propNameDbConfigMap.put(conf.propName, conf);
        }
    }

    DBConfig(String propName) {
        this.propName = propName;
    }

    public String getPropName() {
        return propName;
    }


    public static Optional<DBConfig> getConfigByPropName(String propName) {
        return Optional.ofNullable(propNameDbConfigMap.get(propName));
    }
}
