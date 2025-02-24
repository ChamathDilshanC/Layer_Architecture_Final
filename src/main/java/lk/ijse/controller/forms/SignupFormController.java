package lk.ijse.controller.forms;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;
import lk.ijse.Util.TextField;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.EmployeeBO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class SignupFormController {

    private Connection conn;

    @FXML
    private JFXTextField TxtUserId, TxtEmpId, TxtUsername, TxtPassword, txtConfirmPassword;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    @FXML
    void CreateaccountOnAction(ActionEvent event) {
        String empId = TxtEmpId.getText();
        String userId = TxtUserId.getText();
        String username = TxtUsername.getText();
        String password = TxtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        if (empId.isEmpty() || userId.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert("All fields are required.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert("Passwords do not match.");
            return;
        }

        try {
            if (!isEmployeeIdValid(empId)) {
                showAlert("Invalid Employee ID.");
                return;
            }

            if (isUserIdOrEmpIdInUse(userId, empId)) {
                showAlert("User ID or Employee ID is already associated with a user.");
                return;
            }
            try {
                boolean saved = employeeBO.saveUSerAccount(userId, empId, password);
                if (saved) {
                    showAlert("Account successfully created.");
                    clearTextFields();
                } else {
                    showAlert("Failed to create an account. Please try again.");
                }
                } catch (SQLException e) {
                    showAlert("An error occurred while creating the account: " + e.getMessage());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
        } catch (SQLException e) {
            showAlert("An error occurred while creating the account: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isEmployeeIdValid(String empId) throws SQLException, ClassNotFoundException {
        return employeeBO.existEmployee(empId);
    }

    private boolean isUserIdOrEmpIdInUse(String userId, String empId) throws SQLException, ClassNotFoundException {
        return employeeBO.isUserIdOrEmpIdInUse(userId, empId);
    }

    private void clearTextFields() {
        TxtEmpId.setText("");
        TxtUserId.setText("");
        TxtUsername.setText("");
        TxtPassword.setText("");
        txtConfirmPassword.setText("");
        TxtEmpId.requestFocus();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void txtUserNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.NAME, TxtUsername);
    }
    @FXML
    public void txtUserIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.UserID, TxtUserId);
    }
    @FXML
    public void txtEmpIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.EMPID, TxtEmpId);
    }
    @FXML
    public void txtPasswordOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.PASSWORD, TxtPassword);
    }
    @FXML
    public void txtConfirmPasswordOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.PASSWORD, txtConfirmPassword);
    }


    @FXML
    public void BackbtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/LoginForm.fxml"));
        Scene scene = ((Node) actionEvent.getSource()).getScene();
        scene.setRoot(rootNode);
        Stage stage = (Stage) scene.getWindow();
        stage.setTitle("Sun & Moon Resort Login Form");
        stage.show();
    }

}
