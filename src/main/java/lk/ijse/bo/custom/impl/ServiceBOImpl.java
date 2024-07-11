package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ServiceBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.dao.custom.ServiceDAO;
import lk.ijse.dto.ServiceDTO;
import lk.ijse.entity.Service;

import java.sql.SQLException;
import java.util.List;

public class ServiceBOImpl  implements ServiceBO {

    ServiceDAO serviceDAO = (ServiceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SERVICE);
    @Override
    public List<String> getServiceIds() throws SQLException, ClassNotFoundException {
        return serviceDAO.getIds();
    }

    @Override
    public double getServicePriceById(String serviceId) throws SQLException, ClassNotFoundException {
        return serviceDAO.getPriceById(serviceId);
    }

    @Override
    public ServiceDTO getServiceById(String serviceId) throws SQLException, ClassNotFoundException {
        Service service = serviceDAO.getById(serviceId);
        if (service == null) {
            return null;
        }
        return new ServiceDTO(service.getServiceID(),service.getName(),service.getPrice());
    }
}
