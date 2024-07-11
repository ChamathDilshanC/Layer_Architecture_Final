package lk.ijse.entity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderMealCart  implements Serializable {
    private String mealId;
    private String inventoryID;
    private double mealPrice;
    private int quantity;
    private double totalPrice;

}
