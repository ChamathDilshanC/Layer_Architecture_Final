package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.EmployeeService;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeServiceDAO extends SuperDAO {
    boolean save(List<EmployeeService> employees) throws SQLException ;
}
