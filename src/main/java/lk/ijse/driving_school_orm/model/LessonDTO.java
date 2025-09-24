package lk.ijse.driving_school_orm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class LessonDTO {
    private long lessonID;
    private Date date;
    private String time;
    private String status;
    private long studentID;
    private long courseID;
    private long instructorID;

    public LessonDTO(Date date, String time, String status, long studentID, long courseID, long instructorID) {
        this.date = date;
        this.time = time;
        this.status = status;
        this.studentID = studentID;
        this.courseID = courseID;
        this.instructorID = instructorID;
    }

}


