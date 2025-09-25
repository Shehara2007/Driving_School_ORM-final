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
import lk.ijse.driving_school_orm.BO.custom.InstructorBO;
import lk.ijse.driving_school_orm.BO.custom.impl.BOFactory;
import lk.ijse.driving_school_orm.model.InstructorDTO;
import lk.ijse.driving_school_orm.view.tdm.InstructorTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InstructorPageController implements Initializable {
    private final InstructorBO instructorBO = (InstructorBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.INSTRUCTOR);

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<InstructorTM, Long> colInstructorId;

    @FXML
    private TableColumn<InstructorTM, String> colInstructorAvailability;

    @FXML
    private TableColumn<InstructorTM, String> colInstructorName;

    @FXML
    private TableColumn<InstructorTM, String> colInstructorPhone;

    @FXML
    private TableColumn<InstructorTM, String> colInstructorEmail;

    @FXML
    private TableView<InstructorTM> tblInstructor;

    @FXML
    private TextField txtAvailability;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtInstructorID;

    @FXML
    private TextField txtInstructorName;

    @FXML
    private TextField txtPhone;

    @FXML
    void manageClearInstructor(ActionEvent event) {
        clearFields();

    }

    private void clearFields() {
        txtInstructorID.clear();
        txtInstructorName.clear();
        txtPhone.clear();
        txtEmail.clear();
        txtAvailability.clear();
    }

    @FXML
    void manageDeleteInstructor(ActionEvent event) {
        try {
            if (instructorBO.deleteInstructorManage(txtInstructorID.getText())) {
                showInfo("Instructor deleted successfully!");
                loadAllInstructors();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error deleting instructor: " + e.getMessage());
        }

    }

    private void showError(String i) {
        new Alert(Alert.AlertType.ERROR, i).show();

    }

    private void loadAllInstructors() {
        try {
            List<InstructorDTO> all = instructorBO.findAll();
            ObservableList<InstructorTM> list = FXCollections.observableArrayList();
            for (InstructorDTO dto : all) {
                list.add(new InstructorTM(
                        dto.getInstructorID(),
                        dto.getInstructorName(),
                        dto.getInstructorPhone(),
                        dto.getInstructorEmail(),
                        dto.getInstructorAvailability()
                ));
            }
            tblInstructor.setItems(list);
        } catch (Exception e) {
            showError("Error loading instructor: " + e.getMessage());
        }

    }

    private void showInfo(String i) {
        new Alert(Alert.AlertType.INFORMATION, i).show();

    }

    @FXML
    void manageSaveInstructor(ActionEvent event) {
        try {
            InstructorDTO dto = new InstructorDTO(
                    txtInstructorName.getText(),
                    txtPhone.getText(),
                    txtEmail.getText(),
                    txtAvailability.getText()
            );
            if (instructorBO.saveInstructorManage(dto)) {
                showInfo("Instructor added successfully!");
                loadAllInstructors();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error saving instructor: " + e.getMessage());
        }

    }

    @FXML
    void manageUpdateInstructor(ActionEvent event) {
        try {
            long id = Long.parseLong(txtInstructorID.getText());
            InstructorDTO dto = new InstructorDTO(
                    id,
                    txtInstructorName.getText(),
                    txtPhone.getText(),
                    txtEmail.getText(),
                    txtAvailability.getText()
            );
            if (instructorBO.updateInstructorManage(dto)) {
                showInfo("Instructor updated successfully!");
                loadAllInstructors();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error updating instructor: " + e.getMessage());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println( );
        colInstructorId.setCellValueFactory(new PropertyValueFactory<>("instructorID"));
        colInstructorName.setCellValueFactory(new PropertyValueFactory<>("instructorName"));
        colInstructorPhone.setCellValueFactory(new PropertyValueFactory<>("instructorPhone"));
        colInstructorEmail.setCellValueFactory(new PropertyValueFactory<>("instructorEmail"));
        colInstructorAvailability.setCellValueFactory(new PropertyValueFactory<>("instructorAvailability"));
        loadAllInstructors();
    }

    public void MouseClickOnAction(MouseEvent mouseEvent) {
        InstructorTM selected = tblInstructor.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtInstructorID.setText(String.valueOf(selected.getInstructorID()));
            txtInstructorName.setText(selected.getInstructorName());
            txtPhone.setText(selected.getInstructorPhone());
            txtEmail.setText(selected.getInstructorEmail());
            txtAvailability.setText(selected.getInstructorAvailability());

        }
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
}
