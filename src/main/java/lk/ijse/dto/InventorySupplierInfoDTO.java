package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InventorySupplierInfoDTO implements Serializable {
    private  String InventoryID;
    private  String SupplierID;
    private  int SupplyQuantity;
    private  String DeliveryDate;
    private  int PricePerUnit;
    private  int TotalPrice;

}
