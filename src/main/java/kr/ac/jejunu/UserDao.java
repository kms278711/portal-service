package kr.ac.jejunu;

import java.sql.*;

public class UserDao {
    private final ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public User findById(Integer id) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();
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



    public void insert(User user) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();
        //spl 작성
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        "insert into userinfo(name, password) values ( ?, ? )"
                        , Statement.RETURN_GENERATED_KEYS //쿼리를 실행하고 나서 관련된 결과를 나타냄
                );
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        //sql
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        //User에 데이터 매핑
        user.setId(resultSet.getInt(1));
        //자원 해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
