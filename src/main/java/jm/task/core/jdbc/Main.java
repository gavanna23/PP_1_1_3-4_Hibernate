package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {

        Util.getConnection();
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Анна", "Петрова", (byte) 36);
        userService.saveUser("Иван", "Иванов", (byte) 25);
        userService.saveUser("Светлана", "Сидорова", (byte) 31);
        userService.saveUser("Константин", "Костин", (byte) 38);
        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}


