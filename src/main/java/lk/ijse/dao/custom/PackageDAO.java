package lk.ijse.dao.custom;
import javafx.collections.ObservableList;
import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Package;
import java.sql.SQLException;
import java.util.List;

public interface PackageDAO extends CrudDAO<Package> {
    List<String> getIds() throws SQLException, ClassNotFoundException;

    Package searchById(String id) throws SQLException, ClassNotFoundException;

    ObservableList<Package> getAllPackages() throws SQLException, ClassNotFoundException;
}
