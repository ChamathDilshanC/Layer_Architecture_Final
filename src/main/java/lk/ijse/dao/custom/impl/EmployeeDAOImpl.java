package lk.ijse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.entity.Employee;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

        public  List<String> getIds() throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT EmployeeID FROM employee");
            List<String> ids = new ArrayList<>();
            while (resultSet.next()){
                ids.add(resultSet.getString("EmployeeID"));
            }
            return ids;
        }

        public Employee searchById(String id) throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee WHERE EmployeeID = ?",id);
            if (resultSet.next()) {
                String employeeID = resultSet.getString("EmployeeID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String position = resultSet.getString("Position");
                String email = resultSet.getString("Email");
                String phone = resultSet.getString("Phone");
                double salary = resultSet.getDouble("Salary");
                Date hireDate = resultSet.getDate("HireDate");
                String department = resultSet.getString("Department");

                return new Employee(employeeID, firstName, lastName, position, email, phone, hireDate.toLocalDate(), salary, department);
            }
            return null;
        }

        public  boolean save(Employee employee) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("INSERT INTO employee (EmployeeID, FirstName, LastName, Position, Email, Phone, HireDate, Salary, Department) VALUES (?,?,?,?,?,?,?,?,?)",
                    employee.getEmployeeID(), employee.getFirstName(), employee.getLastName(), employee.getPosition(), employee.getEmail(),
                    employee.getPhone(), employee.getHireDate(), employee.getSalary(), employee.getDepartment());
        }

        public  boolean update(Employee employee) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("UPDATE employee SET FirstName =?, LastName =?, Position =?, Email =?, Phone =?, HireDate =?, Salary =?, Department =? WHERE EmployeeID =?",
                    employee.getFirstName(), employee.getLastName(), employee.getPosition(), employee.getEmail(), employee.getPhone(),
                    employee.getHireDate(), employee.getSalary(), employee.getDepartment(), employee.getEmployeeID());
        }


        public  String getNextId() throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT EmployeeID FROM employee ORDER BY EmployeeID DESC LIMIT 1");
            if (resultSet.next()) {
                String lastId = resultSet.getString("EmployeeID");
                int num = Integer.parseInt(lastId.substring(3)) + 1;
                return String.format("EMP%03d", num);
            }
            return "EMP001";

        }

        public  List<String> getDistinctDepartments() throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT DISTINCT Department FROM employee");
            List<String> departments = new ArrayList<>();
            while (resultSet.next()) {
                departments.add(resultSet.getString("Department"));
            }
            return departments;
        }

        public  Employee searchByPhone(String phone) throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee WHERE phone = ?",phone);
            if (resultSet.next()) {
                String employeeID = resultSet.getString("EmployeeID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String position = resultSet.getString("Position");
                String email = resultSet.getString("Email");
                int phone1 = resultSet.getInt("Phone");
                LocalDate hireDate = resultSet.getDate("HireDate").toLocalDate();
                double salary = resultSet.getDouble("Salary");
                String department = resultSet.getString("Department");

                return new Employee(employeeID, firstName, lastName, position, email, phone1, hireDate, salary, department);
            }
            return null;
        }

        public  ObservableList<Employee> getAll() throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee");
            ObservableList<Employee> employees = FXCollections.observableArrayList();
            while (resultSet.next()) {
                String employeeID = resultSet.getString("EmployeeID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String position = resultSet.getString("Position");
                String email = resultSet.getString("Email");
                String phone = resultSet.getString("Phone");
                LocalDate hireDate = resultSet.getDate("HireDate").toLocalDate();
                double salary = resultSet.getDouble("Salary");
                String department = resultSet.getString("Department");

                employees.add(new Employee(employeeID, firstName, lastName, position, email, phone, hireDate, salary, department));
            }
            return employees;
        }

        public  boolean Delete(String id) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("DELETE FROM employee WHERE EmployeeID = ?",id);

        }

        public boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee WHERE EmployeeID = ?",id);
            return resultSet.next();
        }

        public boolean isUserIdOrEmpIdInUse(String userId, String empId) throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM user WHERE UserId = ? OR EmployeeId = ?",userId, empId);
            return resultSet.next();
        }

        public boolean saveUSerAccount(String userId, String empId, String password) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("INSERT INTO user (UserId, EmployeeId, Password) VALUES (?,?,?)",userId, empId, password);
        }
}
