package com.roomflow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
public class DataSourceTest {

    @Autowired
    DataSource dataSource;

    @Test
    void connectionTest() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println("connection = " + connection);
    }
}
