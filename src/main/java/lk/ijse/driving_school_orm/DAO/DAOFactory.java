package lk.ijse.driving_school_orm.DAO;


import lk.ijse.driving_school_orm.DAO.custom.impl.CourseDAOImpl;
import lk.ijse.driving_school_orm.DAO.custom.impl.InstructorDAOImpl;
import lk.ijse.driving_school_orm.DAO.custom.impl.StudentDAOImpl;
import lk.ijse.driving_school_orm.DAO.custom.impl.UserDAOImpl;

import static lk.ijse.driving_school_orm.BO.custom.impl.BOFactory.BOtypes.INSTRUCTOR;

public class DAOFactory {
    private static DAOFactory instance;
    public static DAOFactory getInstance(){
        if(instance==null){
            instance=new DAOFactory();

        }
        return instance;
    }
    private DAOFactory(){

    }
    public enum DAOtypes{
        STUDENT, COURSE, INSTRUCTOR, USER
    }
    public SuperDao getDAO(DAOtypes dao){
        switch(dao){
            case STUDENT:
                return new StudentDAOImpl();
            case COURSE:
                return new CourseDAOImpl();
            case INSTRUCTOR:
                return new InstructorDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;

        }
    }
}