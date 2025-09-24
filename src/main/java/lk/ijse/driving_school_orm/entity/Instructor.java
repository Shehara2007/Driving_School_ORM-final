package lk.ijse.driving_school_orm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    public Instructor(long instructorID, String instructorName, String instructorEmail, String instructorPhone, String instructorAvailability) {
        this.instructorID = instructorID;
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this.instructorPhone = instructorPhone;
        this.instructorAvailability = instructorAvailability;
    }

    // 🔹 One instructor can have many lessons
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new java.util.ArrayList<>();
}
