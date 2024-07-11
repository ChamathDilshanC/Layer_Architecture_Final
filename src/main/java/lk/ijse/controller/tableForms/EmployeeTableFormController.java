package lk.ijse.controller.tableForms;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Employee;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class EmployeeTableFormController {

    @FXML
    private TableView<EmployeeDTO> tableEmployees;

    @FXML
    private TableColumn<EmployeeDTO, String> colEmployeeID, colFirstName, colLastName, colPosition, colEmail, colPhone, colDepartment;

    @FXML
    private TableColumn<EmployeeDTO, LocalDate> colHireDate;

    @FXML
    private TableColumn<EmployeeDTO, Double> colSalary;

    @FXML
    public void initialize() {
        setCellValueFactory();
        try {
            loadEmployees();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR , "Failed to load employees");
            alert.show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    private void setCellValueFactory() {
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colHireDate.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
    }

    private void loadEmployees() throws SQLException, ClassNotFoundException {
        List<Employee> allEmployees = employeeDAO.getAll();
        for (Employee employee : allEmployees) {
            tableEmployees.getItems().add(new EmployeeDTO(employee.getEmployeeID(), employee.getFirstName(), employee.getLastName(), employee.getPosition(), employee.getEmail(), employee.getPhone(), employee.getHireDate(), employee.getSalary(), employee.getDepartment()));
        }

    }
}
