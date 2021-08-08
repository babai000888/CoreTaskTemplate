package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                "`id` BIGINT NOT NULL AUTO_INCREMENT," +
                "`name` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci'," +
                "`lastName` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci'," +
                "`age` SMALLINT," +
                "PRIMARY KEY (`id`));";
        try {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            transaction.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        String sql = "DROP TABLE IF EXISTS Users";
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name,lastName,age));
            transaction.commit();
        } catch (JDBCException | NullPointerException e) {
            System.out.println("saveUser: Table not available");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Object user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("removeUserById: user id=" + id + " not found");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List list = null;
        Session session = Util.getSessionFactory().openSession();
        try {
            list = session.createQuery("FROM User").list();
        } catch (JDBCException e) {
            System.out.println("getAllUsers: Table not available");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE User").executeUpdate();
            transaction.commit();
        } catch (JDBCException e) {
            System.out.println("cleanUserTable: Table not available");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

