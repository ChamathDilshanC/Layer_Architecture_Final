package lk.ijse.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public List<String> getEmployeeIds() throws SQLException, ClassNotFoundException {
        return employeeDAO.getIds();

    }

    @Override
    public EmployeeDTO searchEmployeeById(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.searchById(id);
        if (employee == null) {
            return null;
        }
        return new EmployeeDTO(
        employee.getEmployeeID(), employee.getFirstName(), employee.getLastName(), employee.getPosition(), employee.getEmail(), employee.getPhone(), employee.getHireDate(), employee.getSalary(), employee.getDepartment());
    }

    @Override
    public void saveEmployee(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        employeeDAO.save(new Employee(employee.getEmployeeID(), employee.getFirstName(), employee.getLastName(), employee.getPosition(), employee.getEmail(), employee.getPhone(), employee.getHireDate(), employee.getSalary(), employee.getDepartment()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(employee.getEmployeeID(), employee.getFirstName(), employee.getLastName(), employee.getPosition(), employee.getEmail(), employee.getPhone(), employee.getHireDate(), employee.getSalary(), employee.getDepartment()));
    }

    @Override
    public String getNextEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getNextId();
    }

    @Override
    public List<String> getDistinctDepartments() throws SQLException, ClassNotFoundException {
        return employeeDAO.getDistinctDepartments();
    }

    @Override
    public EmployeeDTO searchEmployeeByPhone(String phone) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.searchByPhone(phone);
        if (employee == null) {
            return null;
        }
        return new EmployeeDTO(
                employee.getEmployeeID(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getPosition(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getHireDate(),
                employee.getSalary(),
                employee.getDepartment()
        );
    }


    @Override
    public ObservableList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        List<Employee> all = employeeDAO.getAll();
        ObservableList<EmployeeDTO> employees = javafx.collections.FXCollections.observableArrayList();
        for (Employee employee : all) {
            employees.add(new EmployeeDTO(employee.getEmployeeID(), employee.getFirstName(), employee.getLastName(), employee.getPosition(), employee.getEmail(), employee.getPhone(), employee.getHireDate(), employee.getSalary(), employee.getDepartment()));
        }
        return employees;
    }

    @Override
    public boolean DeleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.Delete(id);
    }

    @Override
    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.existEmployee(id);
    }

    @Override
    public boolean isUserIdOrEmpIdInUse(String userId, String empId) throws SQLException, ClassNotFoundException {
        return employeeDAO.isUserIdOrEmpIdInUse(userId, empId);
    }

    @Override
    public boolean saveUSerAccount(String userId, String empId, String password) throws SQLException, ClassNotFoundException {
        return employeeDAO.saveUSerAccount(userId, empId, password);
    }
}
