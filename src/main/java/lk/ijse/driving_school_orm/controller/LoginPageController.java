package lk.ijse.driving_school_orm.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.driving_school_orm.BO.custom.UserBO;
import lk.ijse.driving_school_orm.BO.custom.impl.BOFactory;
import lk.ijse.driving_school_orm.model.UserDTO;
import lk.ijse.driving_school_orm.util.Regex;
import org.mindrot.jbcrypt.BCrypt;

public class LoginPageController {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassword;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.USER);



    @FXML
    public void handleLogin() {
        if (isValid()) {
            String username = txtUserName.getText().trim();
            String password = txtPassword.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please enter both username and password!").show();
                return;
            }

            try {
                UserDTO user = userBO.findByUserName(username);

                if (user != null && BCrypt.checkpw(password, user.getUserPassword())) {
                    new Alert(Alert.AlertType.INFORMATION, "Login Successful!").show();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/driving_school_orm/DashBoard.fxml"));
                    Parent parent = loader.load();

                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    stage.setScene(new Scene(parent));
                    stage.centerOnScreen();
                    stage.setTitle("Driving School - Dashboard");
                    stage.show();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid username or password!").show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Error during login: " + e.getMessage()).show();
            }
        }

    }


    @FXML
    public void handleForgotPassword() {
        new Alert(Alert.AlertType.INFORMATION, "Forgot Password functionality is not implemented yet!").show();
    }

    @FXML
    public void handleRegister() {
        new Alert(Alert.AlertType.INFORMATION, "Register functionality is not implemented yet!").show();
    }


    @FXML
    void UserNameKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.driving_school_orm.util.TextField.NAME, txtUserName);

    }

    private boolean isValid() {
        return Regex.setTextColor(lk.ijse.driving_school_orm.util.TextField.NAME, txtUserName);

    }
}
