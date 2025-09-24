package lk.ijse.driving_school_orm.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaymentTM {
    private long paymentId;
    private Date date;
    private String amount;
    private long studentID;
    private long courseID;

    public PaymentTM(Date date, String method, String amount, long studentID, long courseID) {
        this.date = date;
        this.amount = amount;
        this.studentID = studentID;
        this.courseID = courseID;
    }
}