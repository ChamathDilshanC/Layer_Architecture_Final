package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Service;
import java.sql.SQLException;
import java.util.List;

public interface ServiceDAO  extends SuperDAO {
    List<String> getIds() throws SQLException, ClassNotFoundException ;

    double getPriceById(String serviceId) throws SQLException, ClassNotFoundException ;

    Service getById(String serviceId) throws SQLException, ClassNotFoundException ;
}
