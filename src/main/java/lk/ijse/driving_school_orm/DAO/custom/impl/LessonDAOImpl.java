package lk.ijse.driving_school_orm.DAO.custom.impl;

import lk.ijse.driving_school_orm.DAO.custom.LessonDAO;
import lk.ijse.driving_school_orm.config.FactoryConfiguration;
import lk.ijse.driving_school_orm.entity.Lesson;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LessonDAOImpl implements LessonDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(Lesson entity) {
        try (Session session = factoryConfiguration.getSession()) {
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
    public boolean update(Lesson entity) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        session.merge(entity);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        Lesson lesson = session.get(Lesson.class, Long.parseLong(id));
        if (lesson != null) {
            session.remove(lesson);
        }
        tx.commit();
        session.close();
        return lesson != null;
    }

    @Override
    public Lesson findById(String s) throws Exception {
        return null;
    }

    @Override
    public List<Lesson> findAll() throws Exception {
        Session session = factoryConfiguration.getSession();
        List<Lesson> list = session.createQuery("FROM Lesson", Lesson.class).list();
        session.close();
        return list;
    }

}
