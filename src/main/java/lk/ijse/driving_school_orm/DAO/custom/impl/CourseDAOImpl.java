package lk.ijse.driving_school_orm.DAO.custom.impl;

import lk.ijse.driving_school_orm.DAO.custom.CourseDAO;
import lk.ijse.driving_school_orm.config.FactoryConfiguration;
import lk.ijse.driving_school_orm.entity.Instructor;
import lk.ijse.driving_school_orm.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    @Override
    public boolean save(Course entity) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Course entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.merge(entity);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String c) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        Course course = session.get(Course.class, c);
        if (course != null) {
            session.remove(course);
        }
        tx.commit();
        session.close();
        return course != null;
    }

    @Override
    public Course findById(String c) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Course course= session.get(Course.class, c);
        session.close();
        return course;
    }

    @Override
    public List<Course> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Course> list = session.createQuery("FROM Course ", Course.class).list();
        session.close();
        return list;
    }

    @Override
    public Course findById(long id) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession().getSessionFactory().openSession()) {
            return session.get(Course.class, id);
        }
    }
}
