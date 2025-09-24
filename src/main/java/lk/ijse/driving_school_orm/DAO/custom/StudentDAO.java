package lk.ijse.driving_school_orm.DAO.custom;

import lk.ijse.driving_school_orm.DAO.CrudDao;
import lk.ijse.driving_school_orm.entity.Student;

public interface StudentDAO extends CrudDao<Student, String> {
    public Student findById(long id) throws Exception;

}
