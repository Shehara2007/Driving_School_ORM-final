package lk.ijse.driving_school_orm.model;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Date date_of_birth;
    private Date registration_date;

    public StudentDTO(String name, String email, String phone, String address, Date date_of_birth, Date registration_date) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.date_of_birth = date_of_birth;
        this.registration_date = registration_date;
    }
}
