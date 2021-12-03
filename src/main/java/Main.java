import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {


        UserService userServiceImpl=new UserServiceImpl();

        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("Valli", "Kotikov", (byte) 45);
        userServiceImpl.saveUser("Irina", "Shemeleva", (byte) 25);
        userServiceImpl.saveUser("Maxim", "Penza", (byte) 543);
        userServiceImpl.saveUser("Kod", "Jonson", (byte) 34);
        System.out.println(userServiceImpl.getAllUsers());
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();



    }
}
