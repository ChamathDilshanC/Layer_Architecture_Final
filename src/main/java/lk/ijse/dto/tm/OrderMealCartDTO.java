package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderMealCartDTO {
    private String mealId;
    private String inventoryID;
    private double mealPrice;
    private int quantity;
    private double totalPrice;

}
