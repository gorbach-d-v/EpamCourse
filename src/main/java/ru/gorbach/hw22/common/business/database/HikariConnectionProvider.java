package ru.gorbach.hw22.common.business.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.gorbach.hw22.common.business.exception.ConnectionAchiveError;

import java.sql.Connection;

public class HikariConnectionProvider implements ConnectionProvider {

    private static volatile HikariConnectionProvider INSTANCE = null;

    private HikariDataSource hikariDataSource;

    public static class HikariConnectionProviderBuilder {

        private String url;
        private String userName;
        private String password;
        private String driver;

        public HikariConnectionProviderBuilder() {
        }

        public HikariConnectionProviderBuilder appendUrl(String url) {
            this.url = url;
            return this;
        }

        public HikariConnectionProviderBuilder appendUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public HikariConnectionProviderBuilder appendPassword(String password) {
            this.password = password;
            return this;
        }

        public HikariConnectionProviderBuilder appendDriver(String driver) {
            this.driver = driver;
            return this;
        }

        public HikariConfig build() {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setUsername(this.userName);
            hikariConfig.setPassword(this.password);
            hikariConfig.setJdbcUrl(this.url);
            hikariConfig.setDriverClassName(this.driver);
            hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
            hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
            hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            return hikariConfig;
        }
    }

    @Override
    public Connection getConnection() {
        try {
            Connection connection = hikariDataSource.getConnection();
            connection.setAutoCommit(true);
            return connection;
        } catch (Exception e) {
            throw new ConnectionAchiveError(e);
        }
    }

    public static void init(HikariConnectionProviderBuilder hikariConnectionProviderBuilder) {
        HikariConnectionProvider result = INSTANCE;

        if (result == null) {

            synchronized (HikariConnectionProvider.class) {
                result = INSTANCE;
                if (result == null) {
                    result = new HikariConnectionProvider();
                    result.hikariDataSource = new HikariDataSource(hikariConnectionProviderBuilder.build());
                    INSTANCE = result;
                }
            }

        }
    }

    public static HikariConnectionProvider getInstance() {
        return INSTANCE;
    }

}
