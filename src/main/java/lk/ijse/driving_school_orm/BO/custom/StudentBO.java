package lk.ijse.driving_school_orm.BO.custom;

import lk.ijse.driving_school_orm.BO.SuperBO;
import lk.ijse.driving_school_orm.model.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    boolean saveStudentManage(StudentDTO dto) throws Exception;
    boolean updateStudentManage(StudentDTO dto) throws Exception;
    boolean deleteStudentManage(String id) throws Exception;
    StudentDTO findById(String id) throws Exception;
    List<StudentDTO> findAll() throws Exception;
}
