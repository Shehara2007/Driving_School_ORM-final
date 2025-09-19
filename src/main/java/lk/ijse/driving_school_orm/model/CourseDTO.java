package lk.ijse.driving_school_orm.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CourseDTO {
    private Long courseID;
    private String courseName;
    private String courseDuration;
    private String courseFee;

    public CourseDTO(String courseName, String courseDuration, String courseFee) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseFee = courseFee;
    }
}

