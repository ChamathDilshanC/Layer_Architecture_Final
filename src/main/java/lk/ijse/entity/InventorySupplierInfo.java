package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InventorySupplierInfo {
    private  String InventoryID;
    private  String SupplierID;
    private  int SupplyQuantity;
    private  String DeliveryDate;
    private  int PricePerUnit;
    private  int TotalPrice;

}
