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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.driving_school_orm.BO.custom.StudentBO;
import lk.ijse.driving_school_orm.BO.custom.impl.BOFactory;
import lk.ijse.driving_school_orm.model.StudentDTO;
import lk.ijse.driving_school_orm.util.Regex;
import lk.ijse.driving_school_orm.view.tdm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class StudentPageController implements Initializable {
    private final StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.STUDENT);

    public TableView<StudentTM> tblStudent;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<StudentTM, String> colAddress;

    @FXML
    private TableColumn<StudentTM, Date> colDob;

    @FXML
    private TableColumn<StudentTM, String> colEmail;

    @FXML
    private TableColumn<StudentTM, String> colName;

    @FXML
    private TableColumn<StudentTM, String> colPhone;

    @FXML
    private TableColumn<StudentTM, Date> colRegDate;

    @FXML
    private TableColumn<StudentTM, Long> colStudentId;

    @FXML
    private DatePicker dpDob;

    @FXML
    private DatePicker dpRegDate;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtStudentId;

    @FXML
    void manageClearStudent(ActionEvent event) {
        clearFields();
    }
    private void clearFields() {
        txtStudentId.clear();
        txtName.clear();
        txtEmail.clear();
        txtPhone.clear();
        txtAddress.clear();
        Date.valueOf(dpDob.getValue());
        Date.valueOf(dpRegDate.getValue());
    }

    @FXML
    void manageDeleteStudent(ActionEvent event) {
        try {
            if (studentBO.deleteStudentManage(txtStudentId.getText())) {
                showInfo("Student deleted successfully!");
                loadAllStudents();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error deleting student: " + e.getMessage());
        }
    }
    private void showInfo(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).show();

    }

    @FXML
    void manageSaveStudent(ActionEvent event) {
        if (isValid()) {
            try {
                StudentDTO dto = new StudentDTO(
                        txtName.getText(),
                        txtEmail.getText(),
                        txtPhone.getText(),
                        txtAddress.getText(),
                        Date.valueOf(dpDob.getValue()),
                        Date.valueOf(dpRegDate.getValue())
                );
                if (studentBO.saveStudentManage(dto)) {
                    showInfo("Student added successfully!");
                    loadAllStudents();
                    clearFields();
                }
            } catch (Exception e) {
                showError("Error saving student: " + e.getMessage());
            }

        }
    }

    @FXML
    void manageUpdateStudent(ActionEvent event) {
        try {
            long id = Long.parseLong(txtStudentId.getText());
            StudentDTO dto = new StudentDTO(
                    id,
                    txtName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    txtAddress.getText(),
                    Date.valueOf(dpDob.getValue()),
                    Date.valueOf(dpRegDate.getValue())
            );
            if (studentBO.updateStudentManage(dto)) {
                showInfo("Student updated successfully!");
                loadAllStudents();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error updating student: " + e.getMessage());
        }

    }
    private void loadAllStudents() {
        try {
            List<StudentDTO> all = studentBO.findAll();
            ObservableList<StudentTM> list = FXCollections.observableArrayList();
            for (StudentDTO dto : all) {
                list.add(new StudentTM(
                        dto.getId(),
                        dto.getName(),
                        dto.getEmail(),
                        dto.getPhone(),
                        dto.getAddress(),
                        dto.getDate_of_birth(),
                        dto.getRegistration_date()
                ));
            }
            tblStudent.setItems(list);
        } catch (Exception e) {
            showError("Error loading students: " + e.getMessage());
        }
    }


    public void MouseClickOnAction(MouseEvent mouseEvent) {
        StudentTM selected = tblStudent.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtStudentId.setText(String.valueOf(selected.getId()));
            txtName.setText(selected.getName());
            txtEmail.setText(selected.getEmail());
            txtPhone.setText(selected.getPhone());
            txtAddress.setText(selected.getAddress());
            Date.valueOf(dpDob.getValue());
            Date.valueOf(dpRegDate.getValue());

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("Date_of_birth"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("Registration_date"));

        loadAllStudents();
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

    @FXML
    void StudentAddressKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.driving_school_orm.util.TextField.ADDRESS, txtAddress);

    }

    @FXML
    void StudentEmailKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.driving_school_orm.util.TextField.EMAIL, txtEmail);

    }

    @FXML
    void StudentNameKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.driving_school_orm.util.TextField.NAME, txtName);

    }

    @FXML
    void StudentPhoneKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.driving_school_orm.util.TextField.CONTACT, txtPhone);

    }


    private boolean isValid() {
        return Regex.setTextColor(lk.ijse.driving_school_orm.util.TextField.NAME, txtName)&&
                Regex.setTextColor(lk.ijse.driving_school_orm.util.TextField.EMAIL, txtEmail)&&
                Regex.setTextColor(lk.ijse.driving_school_orm.util.TextField.CONTACT, txtPhone)&&
                Regex.setTextColor(lk.ijse.driving_school_orm.util.TextField.ADDRESS, txtAddress);

    }

}
