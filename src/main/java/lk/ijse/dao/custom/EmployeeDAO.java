package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Employee;

import java.sql.SQLException;

public interface EmployeeDAO  extends CrudDAO<Employee> {
     boolean existEmployee(String id) throws SQLException, ClassNotFoundException ;
     boolean isUserIdOrEmpIdInUse(String userId, String empId) throws SQLException, ClassNotFoundException;
     boolean saveUSerAccount(String userId, String empId, String password) throws SQLException, ClassNotFoundException ;
}
