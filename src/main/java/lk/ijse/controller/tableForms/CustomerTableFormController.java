package lk.ijse.controller.tableForms;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

import java.sql.Date;


public class CustomerTableFormController {

    @FXML
    private TableColumn<CustomerDTO, String> colCustomerID;
    @FXML
    private TableColumn<CustomerDTO, String> colFirstName;
    @FXML
    private TableColumn<CustomerDTO, String> colLastName;
    @FXML
    private TableColumn<CustomerDTO, String> colNationality;
    @FXML
    private TableColumn<CustomerDTO, String> colEmail;
    @FXML
    private TableColumn<CustomerDTO, String> colPhone;
    @FXML
    private TableColumn<CustomerDTO, String> colAddress;
    @FXML
    private TableColumn<CustomerDTO, Date> colRegistrationDate;
    @FXML
    private TableColumn<CustomerDTO, Integer> colTotalReservations;

    @FXML
    private TableView<Customer> tablecustomers;

    @FXML
    public void initialize() {
        setCellValueFactory();
        loadTableData();
    }

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    private  void setCellValueFactory(){
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        colNationality.setCellValueFactory(new PropertyValueFactory<>("Nationality"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colRegistrationDate.setCellValueFactory(new PropertyValueFactory<>("RegistrationDate"));
        colTotalReservations.setCellValueFactory(new PropertyValueFactory<>("TotalReservations"));
    }
    private void loadTableData() {
        try {
            tablecustomers.setItems(customerDAO.getAll());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact IT team");
            alert.show();
        }
    }
}
