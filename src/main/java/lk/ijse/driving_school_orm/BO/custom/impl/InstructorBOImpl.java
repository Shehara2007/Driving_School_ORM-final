package lk.ijse.driving_school_orm.BO.custom.impl;

import lk.ijse.driving_school_orm.BO.custom.InstructorBO;
import lk.ijse.driving_school_orm.DAO.DAOFactory;
import lk.ijse.driving_school_orm.DAO.custom.InstructorDAO;
import lk.ijse.driving_school_orm.entity.Instructor;
import lk.ijse.driving_school_orm.model.InstructorDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InstructorBOImpl implements InstructorBO {
    private final InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.INSTRUCTOR);


    @Override
    public boolean saveInstructorManage(InstructorDTO dto) throws Exception {
        Instructor instructor = new Instructor(
                dto.getInstructorID(),
                dto.getInstructorName(),
                dto.getInstructorPhone(),
                dto.getInstructorEmail(),
                dto.getInstructorAvailability()
        );
        return instructorDAO.save(instructor);
    }

    @Override
    public boolean updateInstructorManage(InstructorDTO dto) throws Exception {
        Instructor instructor = new Instructor(
                dto.getInstructorID(),
                dto.getInstructorName(),
                dto.getInstructorPhone(),
                dto.getInstructorEmail(),
                dto.getInstructorAvailability()
        );

        return instructorDAO.update(instructor);
    }

    @Override
    public boolean deleteInstructorManage(String id) throws Exception {
        return instructorDAO.delete(id);
    }

    @Override
    public InstructorDTO findById(String id) throws Exception {
        Instructor instructor = instructorDAO.findById(id);
        if (instructor == null) return null;
        return new InstructorDTO(
                instructor.getInstructorID(),
                instructor.getInstructorName(),
                instructor.getInstructorPhone(),
                instructor.getInstructorEmail(),
                instructor.getInstructorAvailability()
        );
    }

    @Override
    public List<InstructorDTO> findAll() throws Exception {
        return instructorDAO.findAll().stream().map(instructor ->
                new InstructorDTO(
                        instructor.getInstructorID(),
                        instructor.getInstructorName(),
                        instructor.getInstructorPhone(),
                        instructor.getInstructorEmail(),
                        instructor.getInstructorAvailability()
                )).collect(Collectors.toList());
    }

    @Override
    public ArrayList<InstructorDTO> getAllInstructor() throws Exception {
        ArrayList<Instructor> instructor = (ArrayList<Instructor>) instructorDAO.findAll();

        ArrayList<InstructorDTO> instructorDTOS = new ArrayList<>();
        for (Instructor i : instructor) {
            instructorDTOS.add(new InstructorDTO(i.getInstructorID(),i.getInstructorName(),i.getInstructorPhone(),i.getInstructorEmail(),i.getInstructorAvailability()));
        }
        return instructorDTOS;
    }
}
