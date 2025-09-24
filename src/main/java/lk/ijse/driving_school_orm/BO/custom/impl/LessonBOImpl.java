package lk.ijse.driving_school_orm.BO.custom.impl;

import lk.ijse.driving_school_orm.BO.custom.LessonBO;
import lk.ijse.driving_school_orm.DAO.DAOFactory;
import lk.ijse.driving_school_orm.DAO.custom.CourseDAO;
import lk.ijse.driving_school_orm.DAO.custom.InstructorDAO;
import lk.ijse.driving_school_orm.DAO.custom.LessonDAO;
import lk.ijse.driving_school_orm.DAO.custom.StudentDAO;
import lk.ijse.driving_school_orm.entity.Course;
import lk.ijse.driving_school_orm.entity.Instructor;
import lk.ijse.driving_school_orm.entity.Lesson;
import lk.ijse.driving_school_orm.entity.Student;
import lk.ijse.driving_school_orm.model.LessonDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LessonBOImpl implements LessonBO {
    private final LessonDAO lessonDAO = (LessonDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.LESSON);
    private final InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.INSTRUCTOR);
    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.COURSE);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.STUDENT);


    @Override
    public boolean saveLesson(LessonDTO dto) throws Exception {
        Instructor instructor = instructorDAO.findById(dto.getInstructorID());
        Course course = courseDAO.findById(dto.getCourseID());
        Student student = studentDAO.findById(dto.getStudentID());
        Lesson lesson = new Lesson(
                dto.getDate(),
                dto.getTime(),
                dto.getStatus(),
                student,
                course,
                instructor
        );
        return lessonDAO.save(lesson);
    }

    @Override
    public boolean updateLesson(LessonDTO dto) throws Exception {
        Instructor instructor = instructorDAO.findById(dto.getInstructorID());
        Course  course = courseDAO.findById(dto.getCourseID());
        Student student = studentDAO.findById(dto.getStudentID());

        Lesson lesson = new Lesson(
                dto.getLessonID(),
                dto.getDate(),
                dto.getTime(),
                dto.getStatus(),
                student,
                course,
                instructor
        );
        return lessonDAO.update(lesson);
    }

    @Override
    public boolean deleteLesson(String id) throws Exception {
        return lessonDAO.delete(id);
    }

    @Override
    public List<LessonDTO> findAll() throws Exception {
        return lessonDAO.findAll().stream().map(lesson ->
                new LessonDTO(
                        lesson.getLessonID(),
                        lesson.getDate(),
                        lesson.getTime(),
                        lesson.getStatus(),
                        lesson.getStudent().getId(),
                        lesson.getCourse().getCourseID(),
                        lesson.getInstructor().getInstructorID()
                )).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllInstructorIds() throws Exception {
        List<Instructor> list = instructorDAO.findAll();
        List<String> idList = new ArrayList<>();
        for (Instructor i : list) {
            idList.add(String.valueOf(i.getInstructorID()));
        }
        return idList;
    }

    @Override
    public List<String> getAllCourseIds() throws Exception {
        List<Course> list = courseDAO.findAll();
        List<String> idList = new ArrayList<>();
        for (Course i : list) {
            idList.add(String.valueOf(i.getCourseID()));
        }
        return idList;
    }

    @Override
    public List<String> getAllStudentIds() throws Exception {
        List<Student> list = studentDAO.findAll();
        List<String> idList = new ArrayList<>();
        for (Student i : list) {
            idList.add(String.valueOf(i.getId()));
        }
        return idList;
    }


}
