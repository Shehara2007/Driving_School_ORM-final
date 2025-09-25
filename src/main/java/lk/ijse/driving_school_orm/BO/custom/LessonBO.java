package lk.ijse.driving_school_orm.BO.custom;

import lk.ijse.driving_school_orm.BO.SuperBO;
import lk.ijse.driving_school_orm.model.LessonDTO;

import java.util.ArrayList;
import java.util.List;

public interface LessonBO extends SuperBO {

    boolean saveLesson(LessonDTO dto) throws Exception;

    boolean updateLesson(LessonDTO dto) throws Exception;

    boolean deleteLesson(String id) throws Exception;

    List<LessonDTO> findAll() throws Exception;
    List<String> getAllInstructorIds() throws Exception;
    List<String> getAllCourseIds() throws Exception;
    List<String> getAllStudentIds() throws Exception;
    ArrayList<LessonDTO> getAllLessons() throws Exception;
}
