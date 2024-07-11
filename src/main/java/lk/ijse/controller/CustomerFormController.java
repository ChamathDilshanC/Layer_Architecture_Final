package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;
import lk.ijse.Util.TextField;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dto.CustomerDTO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerFormController {
    @FXML
    public JFXTextField txtsearchphone;
    @FXML
    private DatePicker RegistrationDatePicker;
    @FXML
    private JFXButton btnclear, btndelete, btnsave, btnupdate;
    @FXML
    private JFXTextField txtCustomerID, txtFirstName, txtLastName, txtNationality, txtEmail, txtPhone, txtAddress;

    @FXML
    public void initialize() {
        loadNextCustomerId();
        initializeTextFieldListeners();
        setupCustomerIdFieldListener();

    }
    CustomerBO customerBO  = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    private void setupCustomerIdFieldListener() {
        txtCustomerID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                try {
                    CustomerDTO customer = customerBO.searchCustomerById(newValue);
                    if (customer != null) {
                        fillCustomerForm(customer);
                    } else {
                        clearForm();
                    }
                } catch (SQLException e) {
                    showAlert("Database Error", "Failed to fetch customer details: " + e.getMessage());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private void clearForm() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtNationality.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        RegistrationDatePicker.setValue(null);

    }

    private void initializeTextFieldListeners() {
        txtsearchphone.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                searchCustomerByPhone(txtsearchphone.getText());
            }
        });

        txtsearchphone.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                searchCustomerByPhone(txtsearchphone.getText());
            }
        });
    }

    private void searchCustomerByPhone(String phone) {
        if (!phone.isEmpty()) {
            try {
                CustomerDTO customer = customerBO.searchCustomerByPhone(phone);
                if (customer != null) {
                    fillCustomerForm(customer);
                } else {
                    clearForm();
                }
            } catch (SQLException e) {
                showAlert("Database Error", "Failed to fetch customer details: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void loadNextCustomerId() {
        try {
            String nextId = customerBO.getNextCustomerId();
            txtCustomerID.setText(nextId);

            LocalDate today = LocalDate.now();
            RegistrationDatePicker.setValue(today);
        } catch (SQLException e) {
            showAlert("Database Connection Error", "Failed to load the next customer ID: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillCustomerForm(CustomerDTO customer) {
        txtCustomerID.setText(customer.getCustomerID());
        txtFirstName.setText(customer.getFirstName());
        txtLastName.setText(customer.getLastName());
        txtNationality.setText(customer.getNationality());
        txtEmail.setText(customer.getEmail());
        txtPhone.setText(customer.getPhone());
        txtAddress.setText(customer.getAddress());
        RegistrationDatePicker.setValue(customer.getRegistrationDate().toLocalDate());
    }

    @FXML
    void btnclearOnAction(ActionEvent event) {
        txtsearchphone.setText("");
        txtCustomerID.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtNationality.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        RegistrationDatePicker.setValue(null);
    }

    @FXML
    void btnsaveOnAction(ActionEvent event) {
        if (areFieldsValid()) {
            CustomerDTO customer = createCustomerFromForm();
            try {
                customerBO.saveCustomer(new CustomerDTO(customer.getCustomerID(), customer.getFirstName(), customer.getLastName(), customer.getNationality(), customer.getEmail(), customer.getPhone(), customer.getAddress(), customer.getTotalReservations(), customer.getRegistrationDate()));
                btnclearOnAction(null);
                showAlert("Save Successful", "Customer saved successfully.");
                refreshForm();
            } catch (SQLException e) {
                showAlert("Error", "Failed to save customer: " + e.getMessage());
                refreshForm();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            showAlert("Validation Error", "All fields are required.");
        }
    }
    @FXML
    void btnupdateOnAction(ActionEvent event) {
        String customerID = txtCustomerID.getText().trim();

        if (customerID.isEmpty()) {
            showAlert("Validation Error", "Customer ID is required.");
            return;
        }
        try {
            CustomerDTO existingCustomer = customerBO.searchCustomerById(customerID);
            if (existingCustomer == null) {
                showAlert("Error", "Not a valid customer ID. No such customer exists.");
                return;
            }

            CustomerDTO customerToUpdate = createCustomerFromForm();
            boolean success = customerBO.updateCustomer(customerToUpdate);
            if (success) {
                showAlert("Update Successful", "Customer updated successfully.");
                refreshForm();
            } else {
                showAlert("Update Failed", "Failed to update the customer.");
                refreshForm();
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to update customer: " + e.getMessage());
            refreshForm();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean areFieldsValid() {
        return !txtCustomerID.getText().isEmpty() &&
                !txtFirstName.getText().isEmpty() &&
                !txtLastName.getText().isEmpty() &&
                !txtEmail.getText().isEmpty() &&
                !txtPhone.getText().isEmpty() &&
                !txtAddress.getText().isEmpty() &&
                RegistrationDatePicker.getValue() != null;
    }

    private CustomerDTO createCustomerFromForm() {
        return new CustomerDTO(
                txtCustomerID.getText(),
                txtFirstName.getText(),
                txtLastName.getText(),
                txtNationality.getText(),
                txtEmail.getText(),
                txtPhone.getText(),
                txtAddress.getText(),
                0,
                Date.valueOf(RegistrationDatePicker.getValue())
        );
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void btnCustomerTableOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CustomerTableForm.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            stage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    stage.setMaximized(false);
                }
            });
        } catch (IOException e) {
           showAlert("Error", "Failed to open customer table form: " + e.getMessage());
        }
    }

    public void btndeleteOnAction(ActionEvent actionEvent) {
        String customerID = txtCustomerID.getText().trim();

        if (customerID.isEmpty()) {
            showAlert("Validation Error", "Customer ID is required.");
            return;
        }
        try {
            CustomerDTO existingCustomer = customerBO.searchCustomerById(customerID);
            if (existingCustomer == null) {
                showAlert("Error", "Not a valid customer ID. No such customer exists.");
                return;
            }
            boolean success = customerBO.DeleteCustomer(customerID);
            if (success) {
                showAlert("Delete Successful", "Customer deleted successfully.");
                refreshForm();
            } else {
                showAlert("Delete Failed", "Failed to delete the customer.");
                refreshForm();
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to delete customer: " + e.getMessage());
            refreshForm();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void refreshForm() {
        loadNextCustomerId();
        clearForm();
    }
    @FXML
    public void txtUserFristNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME, txtFirstName);
    }
    @FXML
    public void txtUserLastNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME, txtLastName);
    }
    @FXML
    public void txtCustIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.CUSTID, txtCustomerID);
    }
    @FXML
    public void TxtNationalityOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME, txtNationality);
    }
    @FXML
    public void txtEmailOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.EMAIL, txtEmail);
    }
    @FXML
    public void txtPhoneOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.PHONE, txtPhone);
    }
    @FXML
    public void TxtAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.ADDRESS, txtAddress);
    }
    @FXML
    public void TxtSearchPhoneOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.PHONE, txtsearchphone);
    }

}
