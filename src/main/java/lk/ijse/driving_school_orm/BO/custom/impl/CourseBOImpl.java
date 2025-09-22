package lk.ijse.driving_school_orm.BO.custom.impl;

import lk.ijse.driving_school_orm.BO.custom.CourseBO;
import lk.ijse.driving_school_orm.DAO.DAOFactory;
import lk.ijse.driving_school_orm.DAO.custom.CourseDAO;
import lk.ijse.driving_school_orm.entity.Course;
import lk.ijse.driving_school_orm.model.CourseDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseBOImpl implements CourseBO {

    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.COURSE);

    @Override
    public boolean saveCourseManage(CourseDTO dto) throws Exception {
        Course course = new Course(
                dto.getCourseName(),
                dto.getCourseDuration(),
                dto.getCourseFee()
        );
        return courseDAO.save(course);
    }

    @Override
    public boolean updateCourseManage(CourseDTO dto) throws Exception {
        Course course = new Course(
                dto.getCourseID(),
                dto.getCourseName(),
                dto.getCourseDuration(),
                dto.getCourseFee()
        );

        return courseDAO.update(course);
    }

    @Override
    public boolean deleteCourseManage(String id) throws Exception {
        return courseDAO.delete(id);
    }

    @Override
    public CourseDTO findById(String id) throws Exception {
        Course course = courseDAO.findById(id);
        if (course == null) return null;
        return new CourseDTO(
               course.getCourseName(),
               course.getCourseDuration(),
               course.getCourseFee()
        );
    }

    @Override
    public List<CourseDTO> findAll() throws Exception {
        return courseDAO.findAll().stream().map(course ->
                new CourseDTO(
                        course.getCourseID(),
                        course.getCourseName(),
                        course.getCourseDuration(),
                        course.getCourseFee()
                )).collect(Collectors.toList());
    }

    @Override
    public ArrayList<CourseDTO> getAllCourse() throws Exception {
        ArrayList<Course> course = (ArrayList<Course>) courseDAO.findAll();

        ArrayList<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course c : course) {
            courseDTOS.add(new CourseDTO(c.getCourseID(),c.getCourseName(),c.getCourseDuration(),c.getCourseFee()));
        }
        return courseDTOS;
    }
}
