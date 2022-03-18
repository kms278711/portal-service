package kr.ac.jejunu;

import java.sql.*;

public class UserDao {
    public User findById(Integer id) throws ClassNotFoundException, SQLException {
        //드라이버 로딩
        Class.forName("com.mysql.cj.jdbc.Driver");
        //커넥션
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/portal_class?serverTimezone=UTC",
                "root",
                "1234"
        );
        //spl 작성
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                "select * from userinfo where id = ?"
                );
        preparedStatement.setInt(1, id);
        //sql
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        //User에 데이터 매핑
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        //자원 해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
        //User 리턴
        return user;
    }
}
