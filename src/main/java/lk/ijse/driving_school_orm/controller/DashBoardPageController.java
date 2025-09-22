package lk.ijse.driving_school_orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.driving_school_orm.BO.custom.CourseBO;
import lk.ijse.driving_school_orm.BO.custom.InstructorBO;
import lk.ijse.driving_school_orm.BO.custom.StudentBO;
import lk.ijse.driving_school_orm.BO.custom.impl.BOFactory;
import lk.ijse.driving_school_orm.model.CourseDTO;
import lk.ijse.driving_school_orm.model.InstructorDTO;
import lk.ijse.driving_school_orm.model.StudentDTO;

import java.util.ArrayList;

public class DashBoardPageController {

    @FXML
    private AnchorPane Ank1;

    @FXML
    private Button btnManageCourses;

    @FXML
    private Button btnManageInstructors;

    @FXML
    private Button btnManageLessons;

    @FXML
    private Button btnManagePayments;

    @FXML
    private Button btnManageStudents;

    @FXML
    private Button btnManageUsers;

    @FXML
    private Button btnSearch;

    @FXML
    private VBox courseId;

    @FXML
    private Label dSchool;

    @FXML
    private VBox instructorId;

    @FXML
    private Label lblTotalCourses;

    @FXML
    private Label lblTotalInstructors;

    @FXML
    private Label lblTotalLessons;

    @FXML
    private Label lblTotalPayments;

    @FXML
    private Label lblTotalStudents;

    @FXML
    private Label lblTotalUsers;

    @FXML
    private VBox lessonId;

    @FXML
    private VBox paymentId;

    @FXML
    private VBox studentId;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox userId;

    @FXML
    private VBox v1;

    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.STUDENT);
    InstructorBO instructorBO = (InstructorBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.INSTRUCTOR);
    CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.COURSE);
    @FXML
    void handleManageCourses(ActionEvent event) {
        nevigateTo("/lk/ijse/driving_school_orm/Course_Manage.fxml");
    }

    @FXML
    void handleManageInstructors(ActionEvent event) {
        nevigateTo("/lk/ijse/driving_school_orm/instructor_Manage.fxml");
    }

    @FXML
    void handleManageLessons(ActionEvent event) {
        nevigateTo("/lk/ijse/driving_school_orm/lesson_Manage.fxml");
    }

    @FXML
    void handleManagePayments(ActionEvent event) {
        nevigateTo("/lk/ijse/driving_school_orm/payment_Manage.fxml");
    }

    @FXML
    void handleManageStudents(ActionEvent event) {
        nevigateTo("/lk/ijse/driving_school_orm/student_Manage.fxml");
    }

    @FXML
    void handleManageUsers(ActionEvent event) {
        nevigateTo("/lk/ijse/driving_school_orm/User_Manage.fxml");
    }

    @FXML
    void handleSearch(ActionEvent event) {

    }

    public void initialize() throws Exception {
        setStudent();
        setInstructor();
        setCourse();
    }
    private void nevigateTo(String s) {
        try {
            Ank1.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(s));

            pane.prefWidthProperty().bind(Ank1.widthProperty());
            pane.prefHeightProperty().bind(Ank1.heightProperty());

            Ank1.getChildren().add(pane);
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Page Not Found!").show();
            e.printStackTrace();

        }
    }

    public void setStudent () throws Exception {
        ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();
        lblTotalStudents.setText(String.valueOf(allStudent.size()));
    }
    public void setInstructor () throws Exception {
        ArrayList<InstructorDTO> allInstructor = instructorBO.getAllInstructor();
        lblTotalInstructors.setText(String.valueOf(allInstructor.size()));
    }
    public void setCourse () throws Exception {
        ArrayList<CourseDTO> allCourse = courseBO.getAllCourse();
        lblTotalCourses.setText(String.valueOf(allCourse.size()));
    }

}