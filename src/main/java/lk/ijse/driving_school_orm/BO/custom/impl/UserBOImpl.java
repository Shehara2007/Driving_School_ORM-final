package lk.ijse.driving_school_orm.BO.custom.impl;

import lk.ijse.driving_school_orm.BO.custom.UserBO;
import lk.ijse.driving_school_orm.DAO.DAOFactory;
import lk.ijse.driving_school_orm.DAO.custom.InstructorDAO;
import lk.ijse.driving_school_orm.DAO.custom.UserDA0;
import lk.ijse.driving_school_orm.entity.Instructor;
import lk.ijse.driving_school_orm.entity.Student;
import lk.ijse.driving_school_orm.entity.User;
import lk.ijse.driving_school_orm.model.StudentDTO;
import lk.ijse.driving_school_orm.model.UserDTO;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserBOImpl implements UserBO {

    private final UserDA0 userDAO = (UserDA0) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.USER);

    @Override
    public boolean saveUser(UserDTO dto) throws Exception {
        String hashedPassword = BCrypt.hashpw(dto.getUserPassword(), BCrypt.gensalt());  //BCript password hash karanw

        User user = new User(
                dto.getUserName(),
                hashedPassword,
                dto.getUserRole()
        );
        return userDAO.save(user);
    }

    @Override
    public boolean updateUser(UserDTO dto) throws Exception {
        String hashedPassword = BCrypt.hashpw(dto.getUserPassword(), BCrypt.gensalt());   //BCript password hash karanw

        User user = new User(
                dto.getUserID(),
                dto.getUserName(),
                hashedPassword,
                dto.getUserRole()
        );
        return userDAO.update(user);
    }

    @Override
    public boolean deleteUser(String id) throws Exception {
        return userDAO.delete(id);
    }

    @Override
    public UserDTO findById(String id) throws Exception {
        User user = userDAO.findById(id);
        if (user == null) return null;
        return new UserDTO(
                user.getUserID(),
                user.getUserName(),
                user.getUserPassword(),
                user.getUserRole()
                );
    }

    @Override
    public List<UserDTO> findAll() throws Exception {
        return userDAO.findAll().stream().map(user ->
                new UserDTO(
                        user.getUserID(),
                        user.getUserName(),
                        user.getUserPassword(),
                        user.getUserRole()
                )).collect(Collectors.toList());    }

    @Override
    public ArrayList<UserDTO> getAllUser() throws Exception {
        ArrayList<User> users = (ArrayList<User>) userDAO.findAll();

        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User c : users) {
            userDTOS.add(new UserDTO(c.getUserID(), c.getUserName(), c.getUserPassword(), c.getUserRole()));
        }
        return userDTOS;
    }

    @Override
    public UserDTO findByUserName(String userName) throws Exception {
        User user = userDAO.findByUserName(userName);
        if (user != null) {
            return new UserDTO(user.getUserID(), user.getUserName(), user.getUserPassword(), user.getUserRole());
        }
        return null;
    }
}
