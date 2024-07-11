package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.EmployeeServiceDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.entity.EmployeeService;
import lk.ijse.entity.PlaceEmployeeService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeServiceDAOImpl implements EmployeeServiceDAO {
    public  boolean save(List<EmployeeService> employees) throws SQLException {
        boolean isSuccessful = true;

        try {
            for (EmployeeService employee : employees) {
                ResultSet rst = SQLUtil.execute("SELECT COUNT(*) FROM employee_service_info WHERE employeeID = ? AND serviceID = ?", employee.getEmployeeID(), employee.getServiceID());
                int count = 0;
                if (rst.next()){
                    count = rst.getInt(1);
                }

                if (count == 0) {
                    boolean insert = SQLUtil.execute("INSERT INTO employee_service_info VALUES (?, ?, ?, ?, ?)", employee.getEmployeeID(), employee.getServiceID(), employee.getHoursAllocated(), employee.getServiceDate(), employee.getTotalServicePrice());
                    if (!insert){
                        isSuccessful = false;
                    }
                } else {
                    System.out.println("Duplicate entry found for employeeID: " + employee.getEmployeeID() + ", serviceID: " + employee.getServiceID());
                }
                boolean update = SQLUtil.execute("UPDATE employee SET salary = salary + ? WHERE employeeID = ?", employee.getTotalServicePrice(), employee.getEmployeeID());
                if (!update){
                    isSuccessful = false;
                }
            }

            return isSuccessful;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
