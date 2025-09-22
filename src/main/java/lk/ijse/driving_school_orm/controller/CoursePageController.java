package lk.ijse.driving_school_orm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import lk.ijse.driving_school_orm.BO.custom.CourseBO;
import lk.ijse.driving_school_orm.BO.custom.impl.BOFactory;
import lk.ijse.driving_school_orm.model.CourseDTO;
import lk.ijse.driving_school_orm.view.tdm.CourseTM;
import lk.ijse.driving_school_orm.view.tdm.StudentTM;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CoursePageController implements Initializable {
    private final CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.COURSE);

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colCourseName;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colFees;

    @FXML
    private HBox imagehbox;

    @FXML
    private TableView<CourseTM> tblCourses;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFees;

    @FXML
    void manageClear(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtCourseId.clear();
        txtCourseName.clear();
        txtDuration.clear();
        txtFees.clear();
    }

    @FXML
    void manageDeleteCourse(ActionEvent event) {
        try {
            if (courseBO.deleteCourseManage(txtCourseId.getText())) {
                showInfo("Course deleted successfully!");
                loadAllCourses();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error deleting course: " + e.getMessage());
        }
    }

    private void loadAllCourses() {
        try {
            List<CourseDTO> all = courseBO.findAll();
            ObservableList<CourseTM> list = FXCollections.observableArrayList();
            for (CourseDTO dto : all) {
                list.add(new CourseTM(
                        dto.getCourseID(),
                        dto.getCourseName(),
                        dto.getCourseDuration(),
                        dto.getCourseFee()
                ));
            }
            tblCourses.setItems(list);
        } catch (Exception e) {
            showError("Error loading courses: " + e.getMessage());
        }
    }

    private void showInfo(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).show();

    }

    @FXML
    void manageSaveCourse(ActionEvent event) {
        try {
            CourseDTO dto = new CourseDTO(
                    txtCourseName.getText(),
                    txtDuration.getText(),
                    txtFees.getText()
            );
            if (courseBO.saveCourseManage(dto)) {
                showInfo("Course added successfully!");
                loadAllCourses();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error saving course: " + e.getMessage());
        }

    }

    @FXML
    void manageUpdateCourse(ActionEvent event) {
        try {
            long id = Long.parseLong(txtCourseId.getText());
            CourseDTO dto = new CourseDTO(
                    id,
                    txtCourseName.getText(),
                    txtDuration.getText(),
                    txtFees.getText()
            );
            if (courseBO.updateCourseManage(dto)) {
                showInfo("Course updated successfully!");
                loadAllCourses();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error updating course: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("courseDuration"));
        colFees.setCellValueFactory(new PropertyValueFactory<>("courseFee"));
        loadAllCourses();
    }

    public void MouseClickOnAction(MouseEvent mouseEvent) {
        CourseTM selected = tblCourses.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtCourseId.setText(String.valueOf(selected.getCourseID()));
            txtCourseName.setText(selected.getCourseName());
            txtDuration.setText(selected.getCourseDuration());
            txtFees.setText(selected.getCourseFee());

        }
    }
}
