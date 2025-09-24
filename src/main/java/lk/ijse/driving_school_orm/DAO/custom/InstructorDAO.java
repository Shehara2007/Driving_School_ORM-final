package lk.ijse.driving_school_orm.DAO.custom;

import lk.ijse.driving_school_orm.DAO.CrudDao;
import lk.ijse.driving_school_orm.entity.Instructor;
import java.util.List;


public interface InstructorDAO extends CrudDao<Instructor, String> {
    public Instructor findById(long id) throws Exception;
}
