package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.models.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    public void createUsersTable() {

        Transaction transaction = null;
        Session session = null;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.createSQLQuery("CREATE TABLE IF NOT EXISTS User (id MEDIUMINT NOT NULL AUTO_INCREMENT, name VARCHAR (30) NOT NULL, lastName VARCHAR (30) NOT NULL, age MEDIUMINT, PRIMARY KEY (id));").executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void dropUsersTable() {

        Transaction transaction = null;
        Session session = null;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void removeUserById(long id) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.createSQLQuery("DElETE FROM User WHERE id=?").setLong(1, id).executeUpdate();

            //Рабочий код Вариант 1
            //User user = session.load(User.class, id);
            //session.delete(user);

            /*//Рабочий код Вариант 2
            preparedStatement = connection.prepareStatement("DElETE FROM User WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

             */

            //Рабочий код Вариант 3
            //session.delete(session.get(User.class, id));

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Transaction transaction = null;
        Session session = null;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Пробую разные варианты
            //users =session.createSQLQuery("FROM User").list();

            Query query = session.createQuery("FROM User");
            users = query.list();


            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        Transaction transaction = null;
        Session session = null;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.createSQLQuery("TRUNCATE TABLE user").executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
