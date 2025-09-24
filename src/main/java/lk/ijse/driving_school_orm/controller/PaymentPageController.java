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
import lk.ijse.driving_school_orm.BO.custom.PaymentBO;
import lk.ijse.driving_school_orm.BO.custom.impl.BOFactory;
import lk.ijse.driving_school_orm.model.PaymentDTO;
import lk.ijse.driving_school_orm.view.tdm.PaymentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentPageController implements Initializable {
    private final PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.PAYMENT);

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbCourseId;

    @FXML
    private ComboBox<String> cmbStudentId;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TableView<PaymentTM> tblPayments;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtPaymentId;

    @FXML
    void manageClear(ActionEvent event) {
        clearFields();

    }

    @FXML
    void manageDeletePayment(ActionEvent event) {
        try {
            String id = txtPaymentId.getText();
            if (id.isEmpty()) {
                showError("Please select a payment to delete!"); // Warn if nothing selected
                return;
            }

            // Delete payment safely using BO
            if (paymentBO.deletePayment(id)) {
                showInfo("Payment deleted successfully!");
                loadAllPayments();
                clearFields();
            } else {
                showError("Payment not found or cannot be deleted!");
            }
        } catch (Exception e) {
            showError("Error deleting payment: " + e.getMessage()); // Show detailed error
        }

    }

    @FXML
    void manageSavePayment(ActionEvent event) {
        try {
            PaymentDTO dto = new PaymentDTO(
                    Date.valueOf(dpDate.getValue()),
                    txtAmount.getText(),
                    Long.parseLong(cmbStudentId.getSelectionModel().getSelectedItem()),
                    Long.parseLong(cmbCourseId.getSelectionModel().getSelectedItem())
            );
            if (paymentBO.savePayment(dto)) {
                showInfo("Payment added successfully!");
                loadAllPayments();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error saving payment: " + e.getMessage());
        }

    }

    private void showError(String p) {
        new Alert(Alert.AlertType.ERROR, p).show();
    }

    private void clearFields() {
        txtPaymentId.clear();
        dpDate.setValue(null);
        txtAmount.clear();
        cmbStudentId.getSelectionModel().clearSelection();
        cmbCourseId.getSelectionModel().clearSelection();
    }

    private void loadAllPayments() {
        try {
            List<PaymentDTO> all = paymentBO.findAll();
            ObservableList<PaymentTM> list = FXCollections.observableArrayList();
            for (PaymentDTO dto : all) {
                list.add(new PaymentTM(
                        dto.getPaymentId(),
                        dto.getDate(),
                        dto.getAmount(),
                        dto.getStudentID(),
                        dto.getCourseID()
                ));
            }
            tblPayments.setItems(list);
        } catch (Exception e) {
            showError("Error loading payments: " + e.getMessage());
        }

    }

    private void showInfo(String p) {
        new Alert(Alert.AlertType.INFORMATION, p).show();

    }

    @FXML
    void manageUpdatePayment(ActionEvent event) {
        try {
            long id = Long.parseLong(txtPaymentId.getText());
            PaymentDTO dto = new PaymentDTO(
                    id,
                    Date.valueOf(dpDate.getValue()),
                    txtAmount.getText(),
                    Long.parseLong(cmbStudentId.getSelectionModel().getSelectedItem()),
                    Long.parseLong(cmbCourseId.getSelectionModel().getSelectedItem())
            );
            if (paymentBO.updatePayment(dto)) {
                showInfo("Payment updated successfully!");
                loadAllPayments();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error updating payment: " + e.getMessage());
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseID"));

        loadAllPayments();
        loadStudentIds();
        loadCourseIds();

    }

    public void clickOnAction(MouseEvent mouseEvent) {
        PaymentTM selected = (PaymentTM) tblPayments.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtPaymentId.setText(String.valueOf(selected.getPaymentId()));
            dpDate.setValue(selected.getDate().toLocalDate());
            txtAmount.setText(selected.getAmount());
            cmbStudentId.getSelectionModel().select(String.valueOf(selected.getStudentID()));
            cmbCourseId.getSelectionModel().select(String.valueOf(selected.getCourseID()));
        }
    }

    private void loadStudentIds() {
        try {
            List<String> ids = paymentBO.getAllStudentIds();
            cmbStudentId.setItems(FXCollections.observableArrayList(ids).sorted());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCourseIds() {
        try {
            List<String> ids = paymentBO.getAllCourseIds();
            cmbCourseId.setItems(FXCollections.observableArrayList(ids));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
