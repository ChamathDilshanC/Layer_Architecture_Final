package lk.ijse.bo.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;

import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Employee;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {

    List<String> getEmployeeIds() throws SQLException, ClassNotFoundException;

    EmployeeDTO searchEmployeeById(String id) throws SQLException, ClassNotFoundException;

    void saveEmployee(EmployeeDTO employee) throws SQLException, ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO employee) throws SQLException, ClassNotFoundException;

    String getNextEmployeeId() throws SQLException, ClassNotFoundException;

    List<String> getDistinctDepartments() throws SQLException, ClassNotFoundException;

    EmployeeDTO searchEmployeeByPhone(String phone) throws SQLException, ClassNotFoundException;

    ObservableList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    boolean DeleteEmployee(String id) throws SQLException, ClassNotFoundException;

    boolean existEmployee(String id) throws SQLException, ClassNotFoundException;

    boolean isUserIdOrEmpIdInUse(String userId, String empId) throws SQLException, ClassNotFoundException;

    boolean saveUSerAccount(String userId, String empId, String password) throws SQLException, ClassNotFoundException;
}
