package lk.ijse.entity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor

@Data
public class InventoryCart {
    private final String InventoryID;
    private final String SupplierID;
    private final int SupplyQuantity;
    private final String DeliveryDate;
    private final int PricePerUnit;
    private final int TotalPrice;


}
