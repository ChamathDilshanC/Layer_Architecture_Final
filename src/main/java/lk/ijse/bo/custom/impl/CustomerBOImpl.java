package lk.ijse.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;
import java.sql.SQLException;
import java.util.List;

public class  CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getIds();
    }

    public CustomerDTO searchCustomerById(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.searchById(id);
        if (customer == null) {
            return null;
        }
        return new CustomerDTO(
                customer.getCustomerID(), customer.getFirstName(), customer.getLastName(), customer.getNationality(), customer.getEmail(), customer.getPhone(), customer.getAddress(), customer.getTotalReservations(), customer.getRegistrationDate()
        );
    }


    public void saveCustomer(CustomerDTO entity) throws SQLException, ClassNotFoundException {
        customerDAO.save(new Customer(entity.getCustomerID(), entity.getFirstName(), entity.getLastName(), entity.getNationality(), entity.getEmail(), entity.getPhone(), entity.getAddress(), entity.getTotalReservations(), entity.getRegistrationDate()));
    }

    public boolean updateCustomer(CustomerDTO entity) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(entity.getCustomerID(), entity.getFirstName(), entity.getLastName(), entity.getNationality(), entity.getEmail(), entity.getPhone(), entity.getAddress(), entity.getTotalReservations(), entity.getRegistrationDate()));
    }

    public String getNextCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.getNextId();
    }

    public CustomerDTO searchCustomerByPhone(String phone) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.searchByPhone(phone);
        return new CustomerDTO(customer.getCustomerID(), customer.getFirstName(), customer.getLastName(), customer.getNationality(), customer.getEmail(), customer.getPhone(), customer.getAddress(), customer.getTotalReservations(), customer.getRegistrationDate());
    }

    public ObservableList<CustomerDTO> getAllCustomer() {
        try {
            List<Customer> all = customerDAO.getAll();
            ObservableList<CustomerDTO> customers = FXCollections.observableArrayList();
            for (Customer customer : all) {
                customers.add(new CustomerDTO(customer.getCustomerID(), customer.getFirstName(), customer.getLastName(), customer.getNationality(), customer.getEmail(), customer.getPhone(), customer.getAddress(), customer.getTotalReservations(), customer.getRegistrationDate()));
            }
            return customers;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean DeleteCustomer(String id) {
        try {
            return customerDAO.Delete(id);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
