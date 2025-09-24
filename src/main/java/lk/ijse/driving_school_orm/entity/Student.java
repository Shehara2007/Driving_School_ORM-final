package lk.ijse.driving_school_orm.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
        private Long id;
    @Column(nullable = false)
        private String name;
    @Column(nullable = false,unique = true)
        private String email;
    @Column(length = 15)
        private String phone;
    @Column(nullable = false)
        private String address;
    @Column(nullable = false)
        private Date date_of_birth;
    @Column(nullable = false)
        private Date registration_date;

    public Student(String name, String email, String phone, String address, Date date_of_birth, Date registration_date) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.date_of_birth = date_of_birth;
        this.registration_date = registration_date;
    }

    public Student(Long id, String name, String email, String phone, String address, Date date_of_birth, Date registration_date) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.date_of_birth = date_of_birth;
        this.registration_date = registration_date;

    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new java.util.ArrayList<>();

}

