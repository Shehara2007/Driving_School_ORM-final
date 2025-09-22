package lk.ijse.driving_school_orm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {
    private Long userID;
    private String userName;
    private String userPassword;
    private String userRole;

    public UserDTO(String userName, String userPassword, String userRole) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }
}
