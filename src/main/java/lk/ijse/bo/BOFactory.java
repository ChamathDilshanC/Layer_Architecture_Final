package lk.ijse.bo;


import lk.ijse.bo.custom.impl.*;
import lk.ijse.bo.custom.impl.custom.forms.impl.DashboardBOImpl;
import lk.ijse.bo.custom.impl.custom.forms.impl.ForgetPasswordBOImpl;
import lk.ijse.bo.custom.impl.custom.forms.impl.LoginBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,EMPLOYEE,EVENT,PAYMENT,DASHBOARD,FORGETPASSWORD,LOGIN,SERVICE,EMPLOYEESERVICE,RESERVATION,INVENTORY,
        SUPPLIER,PLACESUPPLY,ORDERMEAL,ROOM,PACKAGE
    }

    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case EVENT:
                return new PurchaseEvent();
            case PAYMENT:
                return new PaymentBOImpl();
            case DASHBOARD:
                return new DashboardBOImpl();
            case FORGETPASSWORD:
                return new ForgetPasswordBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case SERVICE:
                return new ServiceBOImpl();
            case EMPLOYEESERVICE:
                return new PurchaseEmployyeService();
            case RESERVATION:
                return new ReservationBOImpl();
            case INVENTORY:
                return new InventoryBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case ORDERMEAL :
                return new PurchaseOrderMeal();
            case ROOM:
                return new RoomBOImpl();
            case PACKAGE:
                return new PackageBOImpl();
            default:
                return null;
        }
    }

}
