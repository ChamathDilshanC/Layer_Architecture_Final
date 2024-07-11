package lk.ijse.controller.forms;

import com.gluonhq.charm.glisten.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.impl.custom.forms.LoginBO;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class LoginFormController {
    @FXML
    private PasswordField TxtPassword;

    @FXML
    private TextField TxtUserId;

    @FXML
    private void initialize() {
        TxtUserId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                TxtPassword.requestFocus();
                event.consume();
            }
        });
        TxtPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                LoginOnAction(new ActionEvent());
                event.consume();
            }
        });
    }
    LoginBO loginBO  = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);

    @FXML
    void ForgetPasswordOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/ForgetPasswordForm.fxml"));
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(rootNode);
        Stage stage = (Stage) scene.getWindow();
        stage.setTitle("Sun & Moon Resort Forget Password Form");
        stage.show();
    }

    @FXML
    void LoginOnAction(ActionEvent event) {
        String userId = TxtUserId.getText();
        String password = TxtPassword.getText();
        DashboardController.UserId = userId;
        DashboardFormController.UserId = userId;

        ResultSet rs = null;
        try {
            rs = loginBO.checkLogin(userId);
            if (rs.next()) {
                if (rs.getString("Password").equals(password)) {
                    updateLoginTimestamp(userId);
                    navigateToTheDashboard();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Incorrect password. Please try again.");
                    TxtPassword.setText("");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "User ID does not exist. Please try again or sign up.");
                TxtUserId.setText("");
                TxtPassword.setText("");
            }
        } catch (SQLException | IOException e) {
            showAlert(Alert.AlertType.ERROR, "Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateLoginTimestamp(String userId) throws SQLException, ClassNotFoundException {
        boolean IsUpdated = loginBO.updateLoginTimestamp(userId, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));
        if (!IsUpdated) {
            throw new RuntimeException("Failed to update the login timestamp");
        }else {
            System.out.println("Login Timestamp Updated");
        }
    }

    private void showAlert(Alert.AlertType alertType, String content) {
        Alert alert = new Alert(alertType);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void SignUpOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/SignupForm.fxml"));
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(rootNode);
        Stage stage = (Stage) scene.getWindow();
        stage.setTitle("Sun & Moon Resort SignUp Form");
        stage.show();
    }

    private void navigateToTheDashboard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashboardForm.fxml"));

        if (loader.getLocation() == null) {
            throw new IllegalStateException("Dashboard FXML file not found");
        }
        Parent rootNode = loader.load();
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) TxtUserId.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Sun & Moon Resort Dashboard");
        stage.show();
        stage.centerOnScreen();
    }
}
