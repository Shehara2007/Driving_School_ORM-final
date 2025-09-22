package lk.ijse.driving_school_orm.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class InstructorTM {
        private Long instructorID;
        private String instructorName;
        private String instructorPhone;
        private String instructorEmail;
        private String instructorAvailability;

        public InstructorTM(String instructorName, String instructorPhone, String instructorEmail, String instructorAvailability) {
                this.instructorName = instructorName;
                this.instructorPhone = instructorPhone;
                this.instructorEmail = instructorEmail;
                this.instructorAvailability = instructorAvailability;
        }
}
