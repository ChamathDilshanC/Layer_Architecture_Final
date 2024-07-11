package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ServiceDAO;
import lk.ijse.entity.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAOImpl implements ServiceDAO {
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT ServiceID FROM Service");
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(rst.getString("ServiceID"));
        }
        return ids;
    }

    public double getPriceById(String serviceId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Price FROM Service WHERE ServiceID = ?", serviceId);
        if (rst.next()) {
            return rst.getDouble("Price");
        }
        return 0;
    }
    public Service getById(String serviceId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Service WHERE ServiceID = ?", serviceId);
        if (rst.next()) {
            return new Service(
                    rst.getString("ServiceID"),
                    rst.getString("Name"),
                    rst.getDouble("Price")
            );
        }
        return null;
    }
}
