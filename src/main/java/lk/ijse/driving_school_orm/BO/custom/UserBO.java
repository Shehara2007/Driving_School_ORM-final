package lk.ijse.driving_school_orm.BO.custom;

import lk.ijse.driving_school_orm.BO.SuperBO;
import lk.ijse.driving_school_orm.model.StudentDTO;
import lk.ijse.driving_school_orm.model.UserDTO;

import java.util.ArrayList;
import java.util.List;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDTO dto) throws Exception;
    boolean updateUser(UserDTO dto) throws Exception;
    boolean deleteUser(String id) throws Exception;
    UserDTO findById(String id) throws Exception;
    List<UserDTO> findAll() throws Exception;

    ArrayList<UserDTO> getAllUser() throws Exception;

    UserDTO findByUserName(String userName) throws Exception;

}
