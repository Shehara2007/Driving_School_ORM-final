package lk.ijse.driving_school_orm.BO.custom.impl;

import lk.ijse.driving_school_orm.BO.SuperBO;

public class BOFactory {
    private static lk.ijse.driving_school_orm.BO.custom.impl.BOFactory instance;
    public static lk.ijse.driving_school_orm.BO.custom.impl.BOFactory getInstance(){
        if(instance==null){
            instance=new lk.ijse.driving_school_orm.BO.custom.impl.BOFactory();

        }
        return instance;
    }
    private BOFactory(){

    }
    public enum BOtypes{
        STUDENT, COURSE,INSTRUCTOR, USER, LESSON, PAYMENT
    }
    public SuperBO getBO(BOtypes bo){
        switch(bo){
            case STUDENT:
                return new StudentBOImpl();
            case COURSE:
                return new CourseBOImpl();
            case INSTRUCTOR:
                return new InstructorBOImpl();
            case USER:
                return new UserBOImpl();
            case LESSON:
                return new LessonBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            default:
                return null;
        }
    }
}