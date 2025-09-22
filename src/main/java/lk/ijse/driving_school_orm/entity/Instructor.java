package lk.ijse.driving_school_orm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "instructors")

public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instructor_id", nullable = false)
    private Long instructorID;
    @Column(nullable = false)
    private String instructorName;
    @Column(length = 15)
    private String instructorPhone;
    @Column(nullable = false)
    private String instructorEmail;
    @Column(nullable = false)
    private String instructorAvailability;

    public Instructor(String instructorName, String instructorPhone, String instructorEmail, String instructorAvailability) {
        this.instructorName = instructorName;
        this.instructorPhone = instructorPhone;
        this.instructorEmail = instructorEmail;
        this.instructorAvailability = instructorAvailability;
    }
}
