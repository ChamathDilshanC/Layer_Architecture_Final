package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InventoryDTO  implements Serializable {
    private String inventoryID;
    private String mealID;
    private String Name;
    private int Quantity;
    private int MinimumStockLevel;
    private String Ingredients;

}
