package lk.ijse.driving_school_orm.BO.custom.impl;

import lk.ijse.driving_school_orm.BO.custom.StudentBO;
import lk.ijse.driving_school_orm.DAO.DAOFactory;
import lk.ijse.driving_school_orm.DAO.custom.StudentDAO;
import lk.ijse.driving_school_orm.entity.Student;
import lk.ijse.driving_school_orm.model.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentBOImpl implements StudentBO {
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.STUDENT);

    @Override
    public boolean saveStudentManage(StudentDTO dto) throws Exception {
        Student student = new Student(
                dto.getName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getAddress(),
                dto.getDate_of_birth(),
                dto.getRegistration_date()
        );
        return studentDAO.save(student);
    }

    @Override
    public boolean updateStudentManage(StudentDTO dto) throws Exception {
        Student student = new Student(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getAddress(),
                dto.getDate_of_birth(),
                dto.getRegistration_date()
        );
        return studentDAO.update(student);
    }

    @Override
    public boolean deleteStudentManage(String id) throws Exception {
        return studentDAO.delete(id);
    }

    @Override
    public StudentDTO findById(String id) throws Exception {
        Student student = studentDAO.findById(id);
        if (student == null) return null;
        return new StudentDTO(
        student.getId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getAddress(),
                student.getDate_of_birth(),
                student.getRegistration_date());
    }

    @Override
    public List<StudentDTO> findAll() throws Exception {
        return studentDAO.findAll().stream().map(student ->
                new StudentDTO(
                        student.getId(),
                        student.getName(),
                        student.getEmail(),
                        student.getPhone(),
                        student.getAddress(),
                        student.getDate_of_birth(),
                        student.getRegistration_date()
                )).collect(Collectors.toList());
    }

    @Override
    public ArrayList<StudentDTO> getAllStudent() throws Exception {
        ArrayList<Student> students = (ArrayList<Student>) studentDAO.findAll();

        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student s : students) {
            studentDTOS.add(new StudentDTO(s.getId(),s.getName(),s.getEmail(),s.getPhone(),s.getAddress(),s.getDate_of_birth(),s.getRegistration_date()));
        }
        return studentDTOS;
    }
}
