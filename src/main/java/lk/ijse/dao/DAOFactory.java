package lk.ijse.dao;


import lk.ijse.dao.custom.impl.*;
import lk.ijse.dao.custom.impl.custom.forms.forms.DashboardDAOImpl;
import lk.ijse.dao.custom.impl.custom.forms.forms.ForgetPasswordDAOImpl;
import lk.ijse.dao.custom.impl.custom.forms.forms.LoginFormDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,EMPLOYEE,EVENT,PAYMENT,DASHBOARD,FORGETPASSWORD,LOGIN,SERVICE,EMPLOYEESERVICE,RESERVATION,
        INVENTORY,SUPPLIER,RESERVATIONPACKAGEINFO,ROOM,SUPPLYINVENTORYINFO,ORDERMEAL,RESERVATIONROOMINFO,PACKAGE,
        RESERVATIONSERVICEINFO
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case EVENT:
                return new EventDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case DASHBOARD:
                return new DashboardDAOImpl();
            case FORGETPASSWORD:
                return new ForgetPasswordDAOImpl();
            case LOGIN:
                return new LoginFormDAOImpl();
            case SERVICE:
                return new ServiceDAOImpl();
            case EMPLOYEESERVICE:
                return new EmployeeServiceDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            case INVENTORY:
                return new InventoryDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case RESERVATIONPACKAGEINFO:
                return new ReservationpackageinfoDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case SUPPLYINVENTORYINFO:
                return new SupplyInventoryInfoDAOImpl();
            case ORDERMEAL:
                return new OrderMealDAOImpl();
            case RESERVATIONROOMINFO:
                return new ReservationroominfoDAOImpl();
            case PACKAGE:
                return new PackageDAOImpl();
            case RESERVATIONSERVICEINFO:
                return new ReservationserviceinfoDAOImpl();

            default:
                return null;
        }
    }


}
