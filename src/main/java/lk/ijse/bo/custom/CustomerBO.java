package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CustomerDTO;


import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {

    CustomerDTO searchCustomerById(String id) throws SQLException, ClassNotFoundException;

    void saveCustomer(CustomerDTO entity) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO entity) throws SQLException, ClassNotFoundException;

    String getNextCustomerId() throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomerByPhone(String phone) throws SQLException, ClassNotFoundException;

    boolean DeleteCustomer(String id);

    List<String> getCustomerIds() throws SQLException, ClassNotFoundException ;
}
