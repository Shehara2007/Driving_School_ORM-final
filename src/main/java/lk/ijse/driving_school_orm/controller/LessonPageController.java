package lk.ijse.driving_school_orm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.driving_school_orm.BO.custom.LessonBO;
import lk.ijse.driving_school_orm.BO.custom.impl.BOFactory;
import lk.ijse.driving_school_orm.model.LessonDTO;
import lk.ijse.driving_school_orm.view.tdm.InstructorTM;
import lk.ijse.driving_school_orm.view.tdm.LessonTM;
import lk.ijse.driving_school_orm.view.tdm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class LessonPageController implements Initializable {
    private final LessonBO lessonBO = (LessonBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.LESSON);

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbCourseID;

    @FXML
    private ComboBox<String> cmbInstructorID;

    @FXML
    private ComboBox<String> cmbStatus;

    @FXML
    private ComboBox<String> cmbStudentID;

    @FXML
    private TableColumn<LessonTM, Long> colCourseId;

    @FXML
    private TableColumn<LessonTM, Date> colDate;

    @FXML
    private TableColumn<InstructorTM, Long> colInstructorId;

    @FXML
    private TableColumn<LessonTM, Long> colLessonId;

    @FXML
    private TableColumn<LessonTM, String> colStatus;

    @FXML
    private TableColumn<StudentTM, Long> colStudentId;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<LessonTM> tblLessons;

    @FXML
    private TextField txtLessonID;

    @FXML
    private TextField txtTime;

    @FXML
    void manageSaveLesson(ActionEvent event) {
        try {
            LessonDTO dto = new LessonDTO(
                    Date.valueOf(datePicker.getValue()),
                    txtTime.getText(),
                    cmbStatus.getSelectionModel().getSelectedItem(),
                    Long.parseLong( cmbStudentID.getSelectionModel().getSelectedItem()),
                    Long.parseLong( cmbCourseID.getSelectionModel().getSelectedItem()),
                    Long.parseLong( cmbInstructorID.getSelectionModel().getSelectedItem())
            );
            if (lessonBO.saveLesson(dto)) {
                showInfo("Lesson added successfully!");
                loadAllLessons();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error saving Lesson: " + e.getMessage());
        }
    }

    @FXML
    void manageClearLesson(ActionEvent event) {
        clearFields();
    }

    @FXML
    void manageDeleteLesson(ActionEvent event) {
        try {
            if (lessonBO.deleteLesson(txtLessonID.getText())) {
                showInfo("Lesson deleted successfully!");
                loadAllLessons();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error deleting Lesson: " + e.getMessage());
        }
    }

    @FXML
    void manageUpdateLesson(ActionEvent event) {
        try {
            long id = Long.parseLong(txtLessonID.getText());
            LessonDTO dto = new LessonDTO(
                    id,
                    Date.valueOf(datePicker.getValue()),
                    txtTime.getText(),
                    cmbStatus.getSelectionModel().getSelectedItem(),
                    Long.parseLong( cmbStudentID.getSelectionModel().getSelectedItem()),
                    Long.parseLong( cmbCourseID.getSelectionModel().getSelectedItem()),
                    Long.parseLong( cmbInstructorID.getSelectionModel().getSelectedItem())
            );
            if (lessonBO.updateLesson(dto)) {
                showInfo("Lesson updated successfully!");
                loadAllLessons();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error updating Lesson: " + e.getMessage());
        }
    }


    private void loadAllLessons() {
        try {
            List<LessonDTO> all = lessonBO.findAll();
            ObservableList<LessonTM> list = FXCollections.observableArrayList();
            for (LessonDTO dto : all) {
                list.add(new LessonTM(
                        dto.getLessonID(),
                        dto.getDate(),
                        dto.getTime(),
                        dto.getStatus(),
                        dto.getStudentID(),
                        dto.getCourseID(),
                        dto.getInstructorID()
                ));
            }
            tblLessons.setItems(list);
        } catch (Exception e) {
            showError("Error loading Lessons: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtLessonID.clear();
        datePicker.setValue(null);
        txtTime.clear();
        cmbStatus.getSelectionModel().clearSelection();
        cmbStudentID.getSelectionModel().clearSelection();
        cmbCourseID.getSelectionModel().clearSelection();
        cmbInstructorID.getSelectionModel().clearSelection();
    }

    private void showInfo(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).show();
    }

    private void loadInstructorIds() {
        try {
            List<String> ids = lessonBO.getAllInstructorIds();
            ObservableList<String> list = FXCollections.observableArrayList(ids);
            cmbInstructorID.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCourseIds() {
        try {
            List<String> ids = lessonBO.getAllCourseIds();
            ObservableList<String> list = FXCollections.observableArrayList(ids);
            cmbCourseID.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadStudentIds() {
        try {
            List<String> ids = lessonBO.getAllStudentIds();
            ObservableList<String> list = FXCollections.observableArrayList(ids);
            cmbStudentID.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colLessonId.setCellValueFactory(new PropertyValueFactory<>("lessonID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        colInstructorId.setCellValueFactory(new PropertyValueFactory<>("instructorID"));

        loadAllLessons();

        cmbStatus.setItems(FXCollections.observableArrayList("Scheduled", "Completed", "Cancelled").sorted());

        loadCourseIds();
        loadInstructorIds();
        loadStudentIds();

    }

    public void handleBackButton(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/driving_school_orm/DashBoard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Dashboard");
        stage.show();
    }

    public void ClickOnAction(MouseEvent mouseEvent) {
        LessonTM selected = tblLessons.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtLessonID.setText(String.valueOf(selected.getLessonID()));
            datePicker.setValue(selected.getDate().toLocalDate());
            txtTime.setText(selected.getTime());
            cmbStatus.getSelectionModel().select(String.valueOf(selected.getStatus()));
            cmbStudentID.getSelectionModel().select(String.valueOf(selected.getStudentID()));
            cmbCourseID.getSelectionModel().select(String.valueOf(selected.getCourseID()));
            cmbInstructorID.getSelectionModel().select(String.valueOf(selected.getInstructorID()));

        }
        cmbStatus.setItems(FXCollections.observableArrayList("Scheduled", "Completed", "Cancelled").sorted());

        loadCourseIds();
        loadInstructorIds();
        loadStudentIds();
    }
}