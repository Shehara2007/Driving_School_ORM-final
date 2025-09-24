package lk.ijse.driving_school_orm.BO.custom;

import lk.ijse.driving_school_orm.BO.SuperBO;
import lk.ijse.driving_school_orm.model.PaymentDTO;

import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO {
    boolean savePayment(PaymentDTO dto) throws Exception;
    boolean updatePayment(PaymentDTO dto) throws Exception;
    boolean deletePayment(String id) throws Exception;
    List<PaymentDTO> findAll() throws Exception;
    ArrayList<PaymentDTO> getAllPayments() throws Exception;

    List<String> getAllStudentIds() throws Exception;
    List<String> getAllCourseIds() throws Exception;
}
