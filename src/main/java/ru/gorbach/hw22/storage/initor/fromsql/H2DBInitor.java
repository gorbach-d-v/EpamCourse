package ru.gorbach.hw22.storage.initor.fromsql;

import org.apache.commons.collections4.CollectionUtils;
import ru.gorbach.hw22.common.business.database.HikariConnectionProvider;
import ru.gorbach.hw22.storage.initor.Initor;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class H2DBInitor implements Initor {
    private static final String DATABASE_CONFIG_PATH = "src/main/java/ru/gorbach/hw22/resources/database/config/config.properties";
    private static final String DDL_SCRIPT_PATH = "src/main/java/ru/gorbach/hw22/resources/database/ddl/create-schema.sql";
    private static final String DML_SCRIPT_PATH = "src/main/java/ru/gorbach/hw22/resources/database/dml/init-data.sql";


    @Override
    public void init() {
        try {
            prepareDataSourceConfig();
            createDataBaseStructure();
            fillDataBaseWithData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepareDataSourceConfig() throws Exception {
        HikariConnectionProvider.HikariConnectionProviderBuilder hikariCpDataSourceBuilder = new HikariConnectionProvider.HikariConnectionProviderBuilder();
        Map<DBConfig, String> dbConfigs = readDbConfigFromResources();

        dbConfigs.forEach((param, value) -> {
            switch (param) {

                case URL: {
                    hikariCpDataSourceBuilder.appendUrl(value);
                    break;
                }
                case USER: {
                    hikariCpDataSourceBuilder.appendUserName(value);
                    break;
                }
                case PASSWORD: {
                    hikariCpDataSourceBuilder.appendPassword(value);
                    break;
                }

                case DRIVER: {
                    hikariCpDataSourceBuilder.appendDriver(value);
                    break;
                }
            }
        });
        HikariConnectionProvider.init(hikariCpDataSourceBuilder);
    }

    private Map<DBConfig, String> readDbConfigFromResources() throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream(DATABASE_CONFIG_PATH));

        Map<DBConfig, String> result = new HashMap<>();
        Arrays.stream(DBConfig.values()).forEach(dbConfig ->
                result.put(dbConfig, props.getProperty(dbConfig.getPropName())));

        return result;
    }

    private void createDataBaseStructure() throws Exception {
        List<String> ddl = Files.readAllLines(Paths.get(DDL_SCRIPT_PATH));

        try (Connection con = HikariConnectionProvider.getInstance().getConnection();
             Statement statement = con.createStatement()) {
            statement.execute(String.join("", ddl));
        }
    }

    private void fillDataBaseWithData() throws Exception {
        List<String> sqls = prepareSqlsToInitData();
        if (CollectionUtils.isNotEmpty(sqls)) {
            for (String sql : sqls) {
                execSql(sql);
            }
        }
    }

    private List<String> prepareSqlsToInitData() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(DML_SCRIPT_PATH))){
            return stream.map(line -> {
                stringBuilder.append(line);
                if (line.endsWith(";")) {
                    String sql = stringBuilder.toString();
                    stringBuilder.setLength(0);
                    return sql;
                } else if (line.startsWith("-")) {
                    stringBuilder.setLength(0);
                    return null;
                }
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toList());
        }
    }

    private void execSql(String sql) throws Exception {
        try (Connection con = HikariConnectionProvider.getInstance().getConnection();
             Statement statement = con.createStatement()) {
            statement.execute(sql);
        }
    }
}
