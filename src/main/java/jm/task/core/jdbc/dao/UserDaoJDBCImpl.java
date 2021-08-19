package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {

            statement.execute("CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(45) NOT NULL," +
                "lastname VARCHAR(45) NOT NULL, age INT(3) NULL, PRIMARY KEY (`id`)) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {

            statement.execute("DROP TABLE IF EXISTS users;");

        } catch (SQLException throwables) {
            System.out.println("Не удалось удалить таблицу");
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO users(name, lastname, age) VALUES (?, ?, ?);")) {

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (SQLException throwables) {
            System.out.println("Не удалось добавить пользователя");
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?;")) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException throwables) {
            System.out.println("Не удалось удалить пользователья");
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {

            ResultSet result = statement.executeQuery("SELECT * FROM users;");
            while (result.next()) {
                users.add(new User(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("lastname"),
                        result.getByte("age")
                ));
            }

        } catch (SQLException throwables) {
            System.out.println("Не удалось получить всех пользователей");
            throwables.printStackTrace();
        }
        users.forEach(System.out::println);
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {

            statement.execute("TRUNCATE TABLE users;");

        } catch (SQLException throwables) {
            System.out.println("Не удалось очистить таблицу");
            throwables.printStackTrace();
        }
    }
}
