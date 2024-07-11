package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PackageBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.PackageDAO;
import lk.ijse.dto.PackageDTO;
import lk.ijse.entity.Package;

import java.sql.SQLException;
import java.util.List;

public class PackageBOImpl implements PackageBO {

    PackageDAO packageDAO = (PackageDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PACKAGE);
    @Override
    public List<String> getPackageIds() throws SQLException, ClassNotFoundException {
        return packageDAO.getIds();
    }

    @Override
    public PackageDTO searchPackageById(String id) throws SQLException, ClassNotFoundException {
        Package p =  packageDAO.searchById(id);
        return new PackageDTO(p.getPackageID(),p.getPackageName(),p.getPackagePrice(),p.getServicesIncluded());

    }
}
