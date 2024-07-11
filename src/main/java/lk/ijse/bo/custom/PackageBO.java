package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.PackageDTO;
import java.sql.SQLException;
import java.util.List;

public interface PackageBO extends SuperBO {

    List<String> getPackageIds() throws SQLException, ClassNotFoundException ;

    PackageDTO searchPackageById(String id) throws SQLException, ClassNotFoundException ;

}
