package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
//    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
  //  UserDao userDaoHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDaoHibernate.createUsersTable();
        System.out.println("Таблица создана или уже существует");

    }

    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();
        System.out.println("Таблица удалена");
    }

    public void saveUser(String name, String lastName, byte age){
        userDaoHibernate.saveUser(name, lastName, age);
        System.out.println("User с именем " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id){
        userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers(){
        List<User> users = userDaoHibernate.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
        return users;
    }

    public void cleanUsersTable(){
        userDaoHibernate.cleanUsersTable();
        System.out.println("Таблица очищена");
    }
}
