package com.example.javafxlogin.repository;

import com.example.javafxlogin.connection.ConnectionFactory;
import com.example.javafxlogin.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginRepository {
    public static List<User> findByUserName(String username) {
        List<User> userList = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createdPreparedStatementFindByName(conn, username);
             ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                User user = User.UserBuilder.builder()
                        .idUser(rs.getInt("idUser"))
                        .firstName(rs.getString("firstName"))
                        .lastName(rs.getString("lastName"))
                        .userName(rs.getString("userName"))
                        .password(rs.getString("password"))
                        .build();
                userList.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    private static PreparedStatement createdPreparedStatementFindByName(Connection conn, String username) throws SQLException {
        String sql = "SELECT * FROM user_account WHERE username LIKE ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", username));
        return ps;
    }

    public static void save(User user) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createdPreparedStatementSave(conn, user)
        ) {
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static PreparedStatement createdPreparedStatementSave(Connection conn, User user) throws SQLException {
        String sql = "INSERT INTO user_account (firstName, lastName, userName, password) VALUES (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getUserName());
        ps.setString(4, user.getPassword());
        return ps;
    }
}
