package lk.ijse.driving_school_orm.BO.custom;

import lk.ijse.driving_school_orm.BO.SuperBO;
import lk.ijse.driving_school_orm.entity.Course;
import lk.ijse.driving_school_orm.model.CourseDTO;
import lk.ijse.driving_school_orm.model.StudentDTO;

import java.util.List;


public interface CourseBO extends SuperBO {
    boolean saveCourseManage(CourseDTO dto) throws Exception;
    boolean updateCourseManage(CourseDTO dto) throws Exception;
    boolean deleteCourseManage(String id) throws Exception;
    CourseDTO findById(String id) throws Exception;
    List<CourseDTO> findAll() throws Exception;

    
}
