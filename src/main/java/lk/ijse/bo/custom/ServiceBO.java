package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ServiceDTO;
import lk.ijse.entity.Service;

import java.sql.SQLException;
import java.util.List;

public interface ServiceBO extends SuperBO {
    List<String> getServiceIds() throws SQLException, ClassNotFoundException ;

    double getServicePriceById(String serviceId) throws SQLException, ClassNotFoundException ;

    ServiceDTO getServiceById(String serviceId) throws SQLException, ClassNotFoundException ;
}
