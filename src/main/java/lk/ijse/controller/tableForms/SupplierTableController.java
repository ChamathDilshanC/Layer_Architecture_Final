package lk.ijse.controller.tableForms;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.dto.SupplierDTO;


import java.sql.SQLException;

public class SupplierTableController {

    @FXML
    private TableView<SupplierDTO> tablesupplier;

    @FXML
    private TableColumn<SupplierDTO, String> colSupplierID;
    @FXML
    private TableColumn<SupplierDTO, String> colName;
    @FXML
    private TableColumn<SupplierDTO, Integer> colPostalCode;
    @FXML
    private TableColumn<SupplierDTO, String> colEmailAddress;
    @FXML
    private TableColumn<SupplierDTO, String> colContactNumber;

    @FXML
    public void initialize() {
        setcellValueFactory();
        loadSuppliers();
    }

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    private void setcellValueFactory() {
        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        colEmailAddress.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
    }

    private void loadSuppliers() {
        try {
            tablesupplier.setItems(supplierBO.getAllSupplier());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
