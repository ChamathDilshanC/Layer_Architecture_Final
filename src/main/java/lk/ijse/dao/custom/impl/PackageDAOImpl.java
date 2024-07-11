package lk.ijse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.PackageDAO;
import lk.ijse.entity.Package;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PackageDAOImpl implements PackageDAO {
    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT PackageID FROM package WHERE Name NOT LIKE '%Wedding%' ORDER BY PackageID ASC;");
        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString("PackageID"));
        }
        return idList;
    }


    @Override
    public Package searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM package WHERE PackageID = ?", id);
        if (resultSet.next()) {
            String packageID = resultSet.getString("PackageID");
            String packageName = resultSet.getString("Name");
            double packagePrice = resultSet.getDouble("Price");
            String servicesIncluded = resultSet.getString("ServicesIncluded");

            return new Package(packageID, packageName, packagePrice, servicesIncluded);
        }
        return null;
    }

    @Override
    public boolean save(Package entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Package entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Package searchByPhone(String phone) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<Package> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getDistinctDepartments() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public ObservableList<Package> getAllPackages() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT PackageID, Name, Price, ServicesIncluded FROM package");
        ObservableList<Package> packages = FXCollections.observableArrayList();
        while (resultSet.next()) {
            packages.add(new Package(
                    resultSet.getString("PackageID"),
                    resultSet.getString("Name"),
                    resultSet.getDouble("Price"),
                    resultSet.getString("ServicesIncluded")
            ));
        }
        return packages;
    }
}
