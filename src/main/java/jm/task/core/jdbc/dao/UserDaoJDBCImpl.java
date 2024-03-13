package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    Connection util = Util.getConnection();
    User user = new User();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() { //создавать таб пользователей
        PreparedStatement preparedStatement = null;
        String sql = "CREATE TABLE IF NOT EXISTS user (id int auto_increment primary key, name varchar(45), lastName varchar(45), age int)";
        try {
            preparedStatement = util.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Таблица создана или уже существует");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() { // удалить структуру таб
        PreparedStatement preparedStatement = null;
        String sql = "DROP TABLE IF EXISTS user";
        try {
            preparedStatement = util.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        //сохранить польз
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)";
        try {
            preparedStatement = util.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) { //удалить по айди
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM user WHERE id=?";
        try {
            preparedStatement = util.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() { //получить всех пользователей
        List<User> userList = new ArrayList<>();
        Statement statement = null;
        String sql = "SELECT * FROM user";
        try {
            statement = util.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() { //очистить таб пользов
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM user";
        try {
            preparedStatement = util.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

