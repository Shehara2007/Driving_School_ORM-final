package lk.ijse.driving_school_orm.DAO.custom.impl;

import lk.ijse.driving_school_orm.DAO.custom.StudentDAO;
import lk.ijse.driving_school_orm.config.FactoryConfiguration;
import lk.ijse.driving_school_orm.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(Student entity) throws Exception {
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
    public boolean update(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.merge(entity);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        Student student = session.get(Student.class, s);
        if (student != null) {
            session.remove(student);
        }
        tx.commit();
        session.close();
        return student != null;
    }

    @Override
    public Student findById(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Student student= session.get(Student.class, s);
        session.close();
        return student;
    }

    @Override
    public List<Student> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Student> list = session.createQuery("FROM Student ", Student.class).list();
        session.close();
        return list;
    }

    @Override
    public Student findById(long id) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession().getSessionFactory().openSession()) {
            return session.get(Student.class, id);
        }
    }
}
