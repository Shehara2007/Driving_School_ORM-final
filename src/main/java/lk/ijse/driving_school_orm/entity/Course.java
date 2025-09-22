package lk.ijse.driving_school_orm.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private Long courseID;
    @Column(nullable = false)
    private String courseName;
    @Column(nullable = false)
    private String courseDuration;
    @Column(nullable = false)
    private String courseFee;

    public Course(String courseName, String courseDuration, String courseFee) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseFee = courseFee;
    }
}

