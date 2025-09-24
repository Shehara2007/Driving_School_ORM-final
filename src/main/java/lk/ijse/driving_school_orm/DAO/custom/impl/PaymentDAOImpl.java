package lk.ijse.driving_school_orm.DAO.custom.impl;

import lk.ijse.driving_school_orm.DAO.custom.PaymentDAO;
import lk.ijse.driving_school_orm.config.FactoryConfiguration;
import lk.ijse.driving_school_orm.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();


    @Override
    public boolean save(Payment entity) throws Exception {
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
    public boolean update(Payment entity) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        session.merge(entity);
        tx.commit();
        session.close();
        return true;    }

    @Override
    public boolean delete(String p) throws Exception {
        try (Session session = factoryConfiguration.getSession()) {
            Transaction tx = session.beginTransaction();
            Payment payment = session.get(Payment.class, Long.parseLong(p));
            if (payment != null) {
                payment.setStudent(null);
                payment.setCourse(null);
                session.remove(payment);
            }
            tx.commit();
            return payment != null;
        }    }

    @Override
    public Payment findById(String s) throws Exception {
        return null;
    }

    @Override
    public List<Payment> findAll() throws Exception {
        Session session = factoryConfiguration.getSession();
        List<Payment> list = session.createQuery("FROM Payment", Payment.class).list();
        session.close();
        return list;    }
}
