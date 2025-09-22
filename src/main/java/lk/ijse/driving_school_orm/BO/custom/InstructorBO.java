package lk.ijse.driving_school_orm.BO.custom;

import lk.ijse.driving_school_orm.BO.SuperBO;
import lk.ijse.driving_school_orm.model.CourseDTO;
import lk.ijse.driving_school_orm.model.InstructorDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface InstructorBO extends SuperBO {
    boolean saveInstructorManage(InstructorDTO dto) throws Exception;
    boolean updateInstructorManage(InstructorDTO dto) throws Exception;
    boolean deleteInstructorManage(String id) throws Exception;
    InstructorDTO findById(String id) throws Exception;
    List<InstructorDTO> findAll() throws Exception;

    ArrayList<InstructorDTO> getAllInstructor() throws Exception;
}
