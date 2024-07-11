package lk.ijse.controller.tableForms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.PackageDAO;
import lk.ijse.dto.PackageDTO;
import lk.ijse.entity.Package;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackageTableFormController {

    @FXML
    private TableView<PackageDTO> tablepackage;

    @FXML
    private TableColumn<PackageDTO, String> colPackageID;
    @FXML
    private TableColumn<PackageDTO, String> colName;
    @FXML
    private TableColumn<PackageDTO, Double> colPrice;
    @FXML
    private TableColumn<PackageDTO, String> colServicesIncluded;

    private PackageDAO packageDAO = (PackageDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PACKAGE);

    @FXML
    public void initialize() {
        setCellValueFactory();
        try {
            loadTableData();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Error loading packages: " + ex.getMessage());
        }
    }

    private void setCellValueFactory() {
        colPackageID.setCellValueFactory(new PropertyValueFactory<>("packageID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("packageName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("packagePrice"));
        colServicesIncluded.setCellValueFactory(new PropertyValueFactory<>("servicesIncluded"));
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        List<Package> packageList = packageDAO.getAllPackages();
        List<PackageDTO> packageDTOList = new ArrayList<>();

        for (Package pkg : packageList) {
            packageDTOList.add(new PackageDTO(pkg.getPackageID(), pkg.getPackageName(), pkg.getPackagePrice(), pkg.getServicesIncluded()));
        }

        ObservableList<PackageDTO> observableList = FXCollections.observableArrayList(packageDTOList);
        tablepackage.setItems(observableList);
    }
}
