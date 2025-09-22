package lk.ijse.driving_school_orm.config;

import lk.ijse.driving_school_orm.entity.Course;
import lk.ijse.driving_school_orm.entity.Instructor;
import lk.ijse.driving_school_orm.entity.Student;
import lk.ijse.driving_school_orm.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private static FactoryConfiguration instance;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(Instructor.class);
        configuration.addAnnotatedClass(User.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return (instance == null) ? instance = new FactoryConfiguration()
                : instance;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}

