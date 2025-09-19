package lk.ijse.driving_school_orm.view.tdm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CourseTM {
    private Long courseID;
    private String courseName;
    private String courseDuration;
    private String courseFee;

    public CourseTM(String courseName, String courseDuration, String courseFee) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseFee = courseFee;
    }
}

