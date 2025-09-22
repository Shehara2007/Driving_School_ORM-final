package lk.ijse.driving_school_orm.DAO.custom.impl;

import lk.ijse.driving_school_orm.DAO.custom.InstructorDAO;
import lk.ijse.driving_school_orm.config.FactoryConfiguration;
import lk.ijse.driving_school_orm.entity.Instructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class InstructorDAOImpl implements InstructorDAO {
    @Override
    public boolean save(Instructor entity) throws Exception {
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
    public boolean update(Instructor entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.merge(entity);
        tx.commit();
        session.close();
        return true;    }

    @Override
    public boolean delete(String i) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        Instructor instructor = session.get(Instructor.class, i);
        if (instructor != null) {
            session.remove(instructor);
        }
        tx.commit();
        session.close();
        return instructor != null;    }

    @Override
    public Instructor findById(String i) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Instructor instructor= session.get(Instructor.class, i);
        session.close();
        return instructor;     }

    @Override
    public List<Instructor> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Instructor> list = session.createQuery("FROM Instructor ", Instructor.class).list();
        session.close();
        return list;
    }
}
