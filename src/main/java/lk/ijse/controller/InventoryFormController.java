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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;
import lk.ijse.Util.TextField;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.InventoryBO;
import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.InventoryDTO;
import lk.ijse.dto.PlaceSupplyDTO;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.dto.tm.InventoryCartDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class InventoryFormController {

    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXComboBox<String> cmbinventoryid, cmbsupplierId;

    @FXML
    private TableView<InventoryCartDTO> tableinventorycart;

    @FXML
    private TableColumn<InventoryCartDTO, String> colInventoryID, colSupplierID;

    @FXML
    private TableColumn<InventoryCartDTO, Integer> colSupplyQuantity, colPricePerUnit, colTotalPrice;

    @FXML
    private TableColumn<InventoryCartDTO, String> colDeliveryDate;

    @FXML
    private TableColumn<InventoryCartDTO, Void> colAction;

    @FXML
    private DatePicker datepikerDeliverydate;

    @FXML
    private Label labelMealId, labelSupEmail, labelSupName, lableAvaQty, lableIngredients, lableMealName, lableMinimumStockLevel;

    @FXML
    private JFXTextField txtPricePerunit, txtsupQty, txtsupplierphone;

    InventoryBO inventoryBO = (InventoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.INVENTORY);
    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);


    public void initialize() {
        setupSupplierPhoneFieldListener();
        Platform.runLater(() -> txtsupplierphone.requestFocus());
        setupDatePicker();
        loadSupplierIdsIntoComboBox();
        loadInventoryIds();
        setupTableColumns();

    }

    private void setupSupplierPhoneFieldListener() {
        txtsupplierphone.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                fetchSupplierDetailsByPhone(txtsupplierphone.getText().trim());
            }
        });
    }

    private void fetchSupplierDetailsByPhone(String phone) {
        try {
            SupplierDTO supplier = supplierBO.searchBySupplierPhone(phone);
            if (supplier != null) {
                labelSupName.setText(supplier.getName());
                labelSupEmail.setText(supplier.getEmailAddress());
                cmbsupplierId.setValue(supplier.getSupplierId());
                setupFocusTransferHandlers();

            } else {
                showAlert(Alert.AlertType.INFORMATION, "No Supplier Found", "No supplier found with the provided phone number.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to retrieve supplier details: " + e.getMessage());
        }
    }

    private void loadInventoryIds() {
        try {
            List<String> ids =inventoryBO.getIds();
            ObservableList<String> ids1 = FXCollections.observableArrayList(ids);
            for (String id : ids) {
                ids1.add(id);
            }

            cmbinventoryid.setItems(ids1);
            cmbinventoryid.setOnAction(this::handleInventorySelectionChanged); // Set listener
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Load Error", "Failed to load inventory IDs: " + e.getMessage());
        }
    }

    private void handleInventorySelectionChanged(ActionEvent event) {
        String inventoryId = cmbinventoryid.getValue();
        updateInventoryDetails(inventoryId);
    }

    private void updateInventoryDetails(String inventoryId) {
        try {
            InventoryDTO item = inventoryBO.searchById(inventoryId);
            if (item != null) {
                labelMealId.setText(item.getMealID());
                lableMealName.setText(item.getName());
                lableAvaQty.setText(String.valueOf(item.getQuantity()));
                lableIngredients.setText(item.getIngredients());
                lableMinimumStockLevel.setText(String.valueOf(item.getMinimumStockLevel()));
            } else {
                clearLabels();
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to retrieve inventory details: " + e.getMessage());
        }
    }

    private void clearLabels() {
        labelMealId.setText("");
        lableMealName.setText("");
        lableAvaQty.setText("");
        lableIngredients.setText("");
        lableMinimumStockLevel.setText("");
    }


    private void loadSupplierIdsIntoComboBox() {
        try {
           List<String> supplierId = supplierBO.getSupplierIds();
            ObservableList<String> supplierIds = FXCollections.observableArrayList();
            for (String id : supplierId) {
                supplierIds.add(id);
            }

            cmbsupplierId.setItems(supplierIds);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Failed to load supplier IDs", e.getMessage());
        }
    }

    private void setupTableColumns() {
        colInventoryID.setCellValueFactory(new PropertyValueFactory<>("inventoryID"));
        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colSupplyQuantity.setCellValueFactory(new PropertyValueFactory<>("supplyQuantity"));
        colPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("pricePerUnit"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        colDeliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));

        colAction.setCellFactory(col -> new TableCell<InventoryCartDTO, Void>() {
            private final Button btn = new Button("Remove");
            {
                btn.setOnAction(event -> {
                    InventoryCartDTO item = getTableRow().getItem();
                    if (item != null) {
                        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION, "Remove this item?", ButtonType.YES, ButtonType.NO);
                        confirmDialog.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.YES) {
                                getTableView().getItems().remove(item);
                            }
                        });
                    }
                });
                btn.getStyleClass().add("remove-button");
                btn.setCursor(Cursor.HAND);
                setAlignment(Pos.CENTER);
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        });
    }


    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String inventoryId = cmbinventoryid.getValue();
        String supplierId = cmbsupplierId.getValue();
        int supplyQuantity = Integer.parseInt(txtsupQty.getText());
        int pricePerUnit = Integer.parseInt(txtPricePerunit.getText());
        int totalPrice = supplyQuantity * pricePerUnit;
        String deliveryDate = datepikerDeliverydate.getValue().toString();

        InventoryCartDTO cartItem = new InventoryCartDTO(
                inventoryId, supplierId, supplyQuantity, deliveryDate, pricePerUnit, totalPrice
        );
        tableinventorycart.getItems().add(cartItem);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) labelMealId.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Failed to open Dashboard", e.getMessage());
        }
    }

    @FXML
    void btnNewSupplierOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SupplierForm.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Failed to open new supplier form", e.getMessage());
        }
    }
    void setupDatePicker() {
        LocalDate today = LocalDate.now();
        datepikerDeliverydate.setValue(today);
    }
    public void InventoryTableOnAction(ActionEvent actionEvent) {
    }
    public void cmbSupplierOnAction(ActionEvent actionEvent) {
        Platform.runLater(() -> cmbinventoryid.requestFocus());
    }

    public void cmbinventoryidonAction(ActionEvent actionEvent) {
        Platform.runLater(() -> txtsupQty.requestFocus());
    }

    public void handleSupplierPhoneAction(ActionEvent actionEvent) {
        Platform.runLater(() -> cmbinventoryid.requestFocus());
    }
    public void btnPlacesupply(ActionEvent actionEvent) throws SQLException {
        List<InventoryCartDTO> inventorySupplierList = new ArrayList<>();

        for (InventoryCartDTO item : tableinventorycart.getItems()) {
            InventoryCartDTO inventoryCart = new InventoryCartDTO(
                    item.getInventoryID(),
                    item.getSupplierID(),
                    item.getSupplyQuantity(),
                    item.getDeliveryDate(),
                    item.getPricePerUnit(),
                    item.getTotalPrice()

            );
            inventorySupplierList.add(inventoryCart);

        }
        System.out.println(inventorySupplierList);
        PlaceSupplyDTO placeSupply = new PlaceSupplyDTO(inventorySupplierList);

        boolean isPlaced = inventoryBO.placeInventorySupply(placeSupply);
        if(isPlaced) {
            new Alert(Alert.AlertType.CONFIRMATION, "Supply Order Placed!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, " Supply Order  Placed Unsuccessfully!").show();
        }

    }
    private void setupFocusTransferHandlers() {
        List<Control> controls = Arrays.asList(txtsupplierphone, labelSupEmail,cmbinventoryid, cmbsupplierId, txtsupQty, txtPricePerunit, datepikerDeliverydate, btnAddToCart);
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
            case "labelSupEmail":
                setupSupplierPhoneFieldListener();
                nextControl = cmbinventoryid;
                cmbinventoryid.requestFocus();
                cmbinventoryid.show();
                break;
            case "cmbinventoryid":
                nextControl = txtsupQty;
                txtsupQty.requestFocus();
                break;
            case "txtsupQty":
                nextControl = datepikerDeliverydate;
                datepikerDeliverydate.requestFocus();
                break;
            case "datepikerDeliverydate":
                nextControl = txtPricePerunit;
                txtPricePerunit.requestFocus();
                break;
            case "txtPricePerunit":
                nextControl = btnAddToCart;
                btnAddToCart.requestFocus();
                break;
            default:
                break;
        }
        if (nextControl instanceof ComboBox) {
            ComboBox<?> nextCombo = (ComboBox<?>) nextControl;
            Platform.runLater(() -> {
                nextCombo.requestFocus();
                nextCombo.show();
            });
        } else if (nextControl instanceof DatePicker) {
            DatePicker nextDatePicker = (DatePicker) nextControl;
            Platform.runLater(() -> {
                nextDatePicker.requestFocus();
                nextDatePicker.show();
            });
        } else if (nextControl instanceof Button) {
            Platform.runLater(nextControl::requestFocus);
        }
    }
    @FXML
    public void txtPhoneOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.PHONE, txtsupplierphone);
    }
    @FXML
    public void TxtqtyOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.QTY,txtsupQty);
    }
    @FXML
    public void txtPriceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.QTY, txtPricePerunit);
    }

    public void PrintBillOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/SupplyReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> data = new HashMap<>();
        data.put("SupplierID", cmbsupplierId.getValue());


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }
}