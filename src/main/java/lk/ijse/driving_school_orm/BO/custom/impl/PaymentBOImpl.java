package lk.ijse.driving_school_orm.BO.custom.impl;

import lk.ijse.driving_school_orm.BO.custom.PaymentBO;
import lk.ijse.driving_school_orm.DAO.DAOFactory;
import lk.ijse.driving_school_orm.DAO.custom.*;
import lk.ijse.driving_school_orm.entity.Course;
import lk.ijse.driving_school_orm.entity.Payment;
import lk.ijse.driving_school_orm.entity.Student;
import lk.ijse.driving_school_orm.model.PaymentDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentBOImpl implements PaymentBO {
    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.COURSE);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.STUDENT);
    private final PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.PAYMENT);

    @Override
    public boolean savePayment(PaymentDTO dto) throws Exception {
        Student student = studentDAO.findById(dto.getStudentID());
        Course course = courseDAO.findById(dto.getCourseID());
        Payment payment = new Payment(dto.getDate(), dto.getAmount(), student, course);
        return paymentDAO.save(payment);
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws Exception {
        Student student = studentDAO.findById(dto.getStudentID());
        Course course = courseDAO.findById(dto.getCourseID());
        Payment payment = new Payment(dto.getPaymentId(), dto.getDate(), dto.getAmount(), student, course);
        return paymentDAO.update(payment);    }

    @Override
    public boolean deletePayment(String id) throws Exception {
        return paymentDAO.delete(id);
    }

    @Override
    public List<PaymentDTO> findAll() throws Exception {
        return paymentDAO.findAll().stream().map(payment ->
                new PaymentDTO(
                        payment.getPaymentId(),
                        payment.getDate(),
                        payment.getAmount(),
                        payment.getStudent().getId(),
                        payment.getCourse().getCourseID()
                )).collect(Collectors.toList());    }

    @Override
    public ArrayList<PaymentDTO> getAllPayments() throws Exception {
        ArrayList<Payment> payments = (ArrayList<Payment>) paymentDAO.findAll();
        ArrayList<PaymentDTO> dtos = new ArrayList<>();
        for (Payment p : payments) {
            dtos.add(new PaymentDTO(p.getPaymentId(), p.getDate(), p.getAmount(), p.getStudent().getId(), p.getCourse().getCourseID()));
        }
        return dtos;    }

    @Override
    public List<String> getAllStudentIds() throws Exception {
        List<Student> list = studentDAO.findAll();
        List<String> idList = new ArrayList<>();
        for (Student s : list) {
            idList.add(String.valueOf(s.getId()));
        }
        return idList;    }

    @Override
    public List<String> getAllCourseIds() throws Exception {
        List<Course> list = courseDAO.findAll();
        List<String> idList = new ArrayList<>();
        for (Course c : list) {
            idList.add(String.valueOf(c.getCourseID()));
        }
        return idList;
    }
}
