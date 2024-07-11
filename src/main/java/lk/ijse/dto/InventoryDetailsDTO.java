package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDetailsDTO implements Serializable {

    private String inventoryID;
    private int quantity;
    private int minimumStockLevel;
    private String ingredients;
}
