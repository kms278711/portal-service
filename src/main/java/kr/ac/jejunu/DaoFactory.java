package kr.ac.jejunu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

@Configuration
public class DaoFactory {
    @Value("${db.drivername}")
    private String driverClassName; // = "com.mysql.cj.jdbc.Driver";
    @Value("${db.url}")
    private String url; // = "jdbc:mysql://localhost:3306/jeju?serverTimezone=UTC";
    @Value("${db.username}")
    private String username; // = "root";
    @Value("${db.password}")
    private String password; // = "1234";

    @Bean
    public UserDao userDao() throws ClassNotFoundException {
        return new UserDao(dataSource());
    }

    @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(
                (Class<? extends Driver>) Class.forName(driverClassName));
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }
}
