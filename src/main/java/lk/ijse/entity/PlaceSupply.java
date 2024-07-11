package lk.ijse.entity;

import lk.ijse.entity.tm.InventoryCart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceSupply {
    private List<InventoryCart> supplyinventoryinfo;

    public PlaceSupply(String inventoryID, String supplierID, int supplyQuantity, String deliveryDate, int pricePerUnit, int totalPrice) {
        this.supplyinventoryinfo = List.of(new InventoryCart(inventoryID, supplierID, supplyQuantity, deliveryDate, pricePerUnit, totalPrice));
    }
}
