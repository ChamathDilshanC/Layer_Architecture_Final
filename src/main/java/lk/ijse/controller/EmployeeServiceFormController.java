package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import lk.ijse.Util.Regex;
import lk.ijse.Util.TextField;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.bo.custom.EmployyeServiceBO;
import lk.ijse.bo.custom.ServiceBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.dto.EmployeeServiceDTO;
import lk.ijse.dto.PlaceEmployeeServiceDTO;
import lk.ijse.dto.ServiceDTO;
import lk.ijse.entity.tm.EmployeeServiceCart;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class EmployeeServiceFormController {

    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnPlaceServiceInfo;

    @FXML
    private JFXComboBox<String> cmbEmployeeId;

    @FXML
    private JFXComboBox<String> cmbServiceID;

    @FXML
    private TableColumn<EmployeeServiceCart, String> colEmployeeID;

    @FXML
    private TableColumn<EmployeeServiceCart, String> colServiceID;

    @FXML
    private TableColumn<EmployeeServiceCart, String> colServiceDate;

    @FXML
    private TableColumn<EmployeeServiceCart, Double> colServiceprice;

    @FXML
    private TableColumn<EmployeeServiceCart, Double> colTotalServicePrice;

    @FXML
    private TableColumn<EmployeeServiceCart, Integer> colHoursAllocated;

    @FXML
    private TableColumn<EmployeeServiceCart, Void> colAction;

    @FXML
    private DatePicker datepickerserviceDate;

    @FXML
    private Label lableFristName;

    @FXML
    private Label lableLastName;

    @FXML
    private Label lableServiceName;

    @FXML
    private Label lableServicePrice;

    @FXML
    private TableView<EmployeeServiceCart> tableordercart;

    @FXML
    private JFXTextField txtEmployeephone;

    @FXML
    private JFXTextField txtHoursAlocated;

    private ObservableList<EmployeeServiceCart> employeeServiceList = FXCollections.observableArrayList();

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);
    ServiceBO serviceBO = (ServiceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SERVICE);
    EmployyeServiceBO employyeServiceBO = (EmployyeServiceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEESERVICE);

    public void initialize() {
        Platform.runLater(() -> txtEmployeephone.requestFocus());
        initializeComboBoxes();
        initializeTableColumns();
        tableordercart.setItems(employeeServiceList);
        setupFocusTransferHandlers();
        setDate();

        txtEmployeephone.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    loadEmployeeDetails(txtEmployeephone.getText());
                } catch (SQLException e) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to load employee details.");
                }
            }
        });

        cmbServiceID.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                loadServiceDetails(newVal);
            }
        });
    }

    private void setDate() {
        LocalDate today = LocalDate.now();
        datepickerserviceDate.setValue(today);
    }

    private void initializeComboBoxes() {
        try {
            List<String> employeeIds = employeeBO.getEmployeeIds();
            cmbEmployeeId.getItems().setAll(employeeIds);

            List<String> serviceIds = serviceBO.getServiceIds();
            cmbServiceID.getItems().setAll(serviceIds);
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load combo box data.");
        }
    }

    private void initializeTableColumns() {
        colEmployeeID.setCellValueFactory(cellData -> cellData.getValue().getEmployeeID());
        colServiceID.setCellValueFactory(cellData -> cellData.getValue().getServiceID());
        colHoursAllocated.setCellValueFactory(cellData -> cellData.getValue().getHoursAllocated().asObject());
        colServiceDate.setCellValueFactory(cellData -> cellData.getValue().getServiceDate());
        colServiceprice.setCellValueFactory(cellData -> cellData.getValue().getServicePrice().asObject());
        colTotalServicePrice.setCellValueFactory(cellData -> cellData.getValue().getTotalServicePrice().asObject());
        addButtonToTable();
    }

    private void addButtonToTable() {
        Callback<TableColumn<EmployeeServiceCart, Void>, TableCell<EmployeeServiceCart, Void>> cellFactory = param -> {
            final TableCell<EmployeeServiceCart, Void> cell = new TableCell<>() {
                private final Button btn = new Button("Remove");

                {
                    btn.getStyleClass().add("remove-button");
                    btn.setCursor(Cursor.HAND);
                    setAlignment(Pos.CENTER);

                    btn.setOnAction((ActionEvent event) -> {
                        EmployeeServiceCart data = getTableView().getItems().get(getIndex());
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this item?", ButtonType.YES, ButtonType.NO);
                        alert.showAndWait();

                        if (alert.getResult() == ButtonType.YES) {
                            employeeServiceList.remove(data);
                        }
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                    }
                }
            };
            return cell;
        };

        colAction.setCellFactory(cellFactory);
    }

    private boolean validateFields() {
        if (cmbEmployeeId.getValue() == null || cmbServiceID.getValue() == null || txtHoursAlocated.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields are required.");
            return false;
        }
        return true;
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        if (!validateFields()) return;

        String employeeID = cmbEmployeeId.getValue();
        String serviceID = cmbServiceID.getValue();
        LocalDate serviceDate = datepickerserviceDate.getValue();
        double servicePrice = Double.parseDouble(lableServicePrice.getText());
        int hoursAllocated = Integer.parseInt(txtHoursAlocated.getText());
        double totalServicePrice = servicePrice * hoursAllocated;

        EmployeeServiceCart employeeService = new EmployeeServiceCart(
                employeeID,
                serviceID,
                serviceDate.toString(),
                servicePrice,
                totalServicePrice,
                hoursAllocated
        );

        employeeServiceList.add(employeeService);
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) this.lableFristName.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Failed to open Dashboard", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadEmployeeDetails(String phone) throws SQLException {
        try {
            EmployeeDTO employee = employeeBO.searchEmployeeByPhone(phone);
            if (employee != null) {
                cmbEmployeeId.setValue(employee.getEmployeeID());
                lableFristName.setText(employee.getFirstName());
                lableLastName.setText(employee.getLastName());

                // Transfer focus to cmbServiceID after loading employee details
                Platform.runLater(() -> cmbServiceID.requestFocus());
            } else {
                lableFristName.setText("Not found");
                lableLastName.setText("Not found");
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load employee details.");
        }
    }

    private void loadServiceDetails(String serviceId) {
        try {
            ServiceDTO service = serviceBO.getServiceById(serviceId);
            if (service != null) {
                lableServiceName.setText(service.getName());
                lableServicePrice.setText(String.format("%.2f", service.getPrice()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load service details.");
        }
    }

    private void clearForm() {
        cmbServiceID.setValue(null);
        datepickerserviceDate.setValue(LocalDate.now());
        lableFristName.setText("");
        lableLastName.setText("");
        lableServiceName.setText("");
        lableServicePrice.setText("");
        txtEmployeephone.clear();
        txtHoursAlocated.clear();
    }

    @FXML
    void btnPlaceServiceInfoOnAction(ActionEvent event) {
        if (!validateFields()) return;

        List<EmployeeServiceDTO> empslist = new ArrayList<>();
        for (EmployeeServiceCart item : tableordercart.getItems()) {
            EmployeeServiceDTO employeeService = new EmployeeServiceDTO(
                    item.getEmployeeID().getValue(),
                    item.getServiceID().getValue(),
                    item.getServiceDate().getValue(),
                    item.getServicePrice().getValue(),
                    item.getTotalServicePrice().getValue(),
                    item.getHoursAllocated().getValue()
            );
            empslist.add(employeeService);
        }

        PlaceEmployeeServiceDTO placeEmployeeService = new PlaceEmployeeServiceDTO(empslist);
        try {
            boolean success = employyeServiceBO.placeEmployeeService(placeEmployeeService);
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Employee Service Information placed successfully.");
                clearForm();
                employeeServiceList.clear();
            } else {
                showAlert(Alert.AlertType.ERROR, "Failed", "Failed to place Employee Service Information.");
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to place Employee Service Information.");
        }
    }

    private void setupFocusTransferHandlers() {
        List<Control> controls = Arrays.asList(txtEmployeephone, cmbServiceID, txtHoursAlocated, datepickerserviceDate, btnAddToCart);
        for (Control control : controls) {
            control.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    transferFocusAndOpen(control);
                }
            });
        }
    }

    private void transferFocusAndOpen(Control currentControl) {
        Control nextControl = null;
        switch (currentControl.getId()) {
            case "txtEmployeephone":
                nextControl = cmbServiceID;
                break;
            case "cmbServiceID":
                nextControl = txtHoursAlocated;
                break;
            case "txtHoursAlocated":
                nextControl = datepickerserviceDate;
                break;
            case "datepickerserviceDate":
                nextControl = btnAddToCart;
                break;
            default:
                break;
        }
        if (nextControl != null) {
            if (nextControl instanceof ComboBox) {
                ComboBox<?> nextCombo = (ComboBox<?>) nextControl;
                Platform.runLater(() -> {
                    nextCombo.requestFocus();
                    nextCombo.show();
                });
            } else {
                Platform.runLater(nextControl::requestFocus);
            }
        }
    }

    @FXML
    public void TxtEmpPhoneOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.PHONE1, txtEmployeephone);
    }

    @FXML
    public void TxtHoursOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.Hours, txtHoursAlocated);
    }

    public void PrintBillOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/EmployeeServices.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> data = new HashMap<>();
        data.put("EmployeeID", cmbEmployeeId.getValue());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }
}
