package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Dmitry", "Krasilnikov", (byte) 40);
        userService.saveUser("Peter", "Petrov", (byte) 30);
        userService.saveUser("Илья", "Муромец", (byte) 20);
        userService.saveUser("Юрий", "Гагарин", (byte) 10);

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
