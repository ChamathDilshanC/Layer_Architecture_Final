package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.EmployyeServiceBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.EmployeeServiceDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.EmployeeServiceDTO;
import lk.ijse.dto.PlaceEmployeeServiceDTO;
import lk.ijse.entity.EmployeeService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseEmployyeService implements EmployyeServiceBO {

    EmployeeServiceDAO employeeServiceDAO = (EmployeeServiceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEESERVICE);

    @Override
    public boolean saveEmployeeService(List<EmployeeServiceDTO> employees) throws SQLException {
        for (EmployeeServiceDTO employee : employees) {
             List<EmployeeService> all = new ArrayList<>();
            all.add(new EmployeeService(employee.getEmployeeID(),employee.getServiceID(),employee.getServiceDate(),employee.getServicePrice()
                    ,employee.getTotalServicePrice(),employee.getHoursAllocated()));
            boolean isAdded = employeeServiceDAO.save(all);
            if (!isAdded) {
                return false;
            }
        }
        return true;

    }

    @Override
    public  boolean placeEmployeeService(PlaceEmployeeServiceDTO placeEmployeeService) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isAdded = saveEmployeeService(placeEmployeeService.getEmployees());
            if (isAdded) {
                connection.commit();
                return true;
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;

        } finally {
            connection.setAutoCommit(true);
        }
    }


}
