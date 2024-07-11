package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.EmployeeServiceDTO;
import lk.ijse.dto.PlaceEmployeeServiceDTO;
import java.sql.SQLException;
import java.util.List;

public interface EmployyeServiceBO extends SuperBO {
    boolean saveEmployeeService(List<EmployeeServiceDTO> employees) throws SQLException;
    boolean placeEmployeeService(PlaceEmployeeServiceDTO placeEmployeeService) throws SQLException ;
}
