package com.example.HogwartsForum.Util;

import com.example.HogwartsForum.Model.UserDao;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseManager {
    UserDao userDao;

    public DatabaseManager(){

    }

    public void run(){
        try {
            setup();
        } catch (SQLException throwables) {
            System.err.println("Could not connect to the database.");
            return;
        }
    }

    private void setup() throws SQLException {
        DataSource dataSource = connect();
//        userDao = new AuthorDaoJdbc(dataSource);
//        bookDao = new BookDaoJdbc(dataSource, authorDao);

    }

    private DataSource connect() throws SQLException{
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName(FileReader.getValueByKeyFromConfigProperties("psql.dbName"));
        dataSource.setUser(FileReader.getValueByKeyFromConfigProperties("psql.username"));
        dataSource.setPassword(FileReader.getValueByKeyFromConfigProperties("psql.password"));

        System.out.println("Trying to connect...");
        dataSource.getConnection().close();
        System.out.println("Connection OK");

        return dataSource;
    }
}
