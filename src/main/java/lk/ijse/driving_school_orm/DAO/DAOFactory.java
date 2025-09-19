package lk.ijse.driving_school_orm.DAO;


import lk.ijse.driving_school_orm.DAO.custom.impl.CourseDAOImpl;
import lk.ijse.driving_school_orm.DAO.custom.impl.StudentDAOImpl;

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
        STUDENT, COURSE
    }
    public SuperDao getDAO(DAOtypes dao){
        switch(dao){
            case STUDENT:
                return new StudentDAOImpl();
//            case INSTRUCTOR:
//                return new InstructorDAOImpl();
            case COURSE:
                return new CourseDAOImpl();
            default:
                return null;
        }
    }
}