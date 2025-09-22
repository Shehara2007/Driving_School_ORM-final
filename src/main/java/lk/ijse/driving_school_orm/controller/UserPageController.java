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
import lk.ijse.driving_school_orm.BO.custom.UserBO;
import lk.ijse.driving_school_orm.BO.custom.impl.BOFactory;
import lk.ijse.driving_school_orm.model.UserDTO;
import lk.ijse.driving_school_orm.view.tdm.UserTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserPageController implements Initializable {
    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.USER);

    public TableView tblUser;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtRole;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;

    @FXML
    void MouseClickOnAction(MouseEvent event) {

        UserTM selected = (UserTM) tblUser.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtUserId.setText(String.valueOf(selected.getUserID()));
            txtUserName.setText(selected.getUserName());
            txtPassword.setText(selected.getUserPassword());
            txtRole.setText(selected.getUserRole());
        }

    }

    @FXML
    void manageClear(ActionEvent event) {
            txtUserId.clear();
            txtUserName.clear();
            txtPassword.clear();
            txtRole.clear();
    }

    @FXML
    void manageDeleteUser (ActionEvent event){
        try {
            if (userBO.deleteUser(txtUserId.getText())) {
                showInfo("User deleted successfully!");
                loadAllUsers();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error deleting user: " + e.getMessage());
        }

    }

    private void showError(String c) {
        new Alert(Alert.AlertType.ERROR, c).show();

    }

    private void showInfo(String c) {
        new Alert(Alert.AlertType.INFORMATION, c).show();

    }

    private void clearFields() {

    }

    private void loadAllUsers() {
        try {
            List<UserDTO> all = userBO.findAll();
            ObservableList<UserTM> list = FXCollections.observableArrayList();
            for (UserDTO dto : all) {
                list.add(new UserTM(
                        dto.getUserID(),
                        dto.getUserName(),
                        dto.getUserPassword(),
                        dto.getUserRole()
                ));
            }
            tblUser.setItems(list);
        } catch (Exception e) {
            showError("Error loading users: " + e.getMessage());
        }

    }

    @FXML
    void manageSaveUser (ActionEvent event){
        try {
            UserDTO dto = new UserDTO(
                    txtUserName.getText(),
                    txtPassword.getText(),
                    txtRole.getText()
            );
            if (userBO.saveUser(dto)) {
                showInfo("User added successfully!");
                loadAllUsers();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error saving user: " + e.getMessage());
        }

    }

    @FXML
    void manageUpdateUser (ActionEvent event){
        try {
            long id = Long.parseLong(txtUserId.getText());
            UserDTO dto = new UserDTO(
                    id,
                    txtUserName.getText(),
                    txtPassword.getText(),
                    txtRole.getText()
            );
            if (userBO.updateUser(dto)) {
                showInfo("User updated successfully!");
                loadAllUsers();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error updating user: " + e.getMessage());
        }

    }

    @Override
    public void initialize (URL location, ResourceBundle resources){
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("userPassword"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("userRole"));

        loadAllUsers();

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
