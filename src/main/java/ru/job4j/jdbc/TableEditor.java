package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class
                .getClassLoader()
                .getResourceAsStream(
                        "table_editor.properties"
                )) {
            config.load(in);
        }
        TableEditor tableEditor = new TableEditor(config);
        tableEditor.initConnection();
        tableEditor.dropTable("TableEditor");
        tableEditor.createTable("TableEditor");
        tableEditor.addColumn("TableEditor",
                "№", "text");
        tableEditor.addColumn("TableEditor",
                "name", "text");
        tableEditor.addColumn("TableEditor",
                "count", "text");
        tableEditor.renameColumn("TableEditor",
                "count", "quantity");
        tableEditor.dropColumn("TableEditor",
                "name");
        System.out.println(
                getTableScheme(tableEditor.connection, "TableEditor")
        );
    }

    public void statement(String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver_class"));
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password"));
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                "create table if not exists %s();",
                tableName
        );
        statement(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format("drop table if exists %s", tableName);
        statement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "alter table %s add %s %s", tableName, columnName, type
        );
        statement(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "alter table %s drop column %s", tableName, columnName
        );
        statement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format(
                "alter table %s rename column %s to %s",
                tableName, columnName, newColumnName
        );
        statement(sql);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
