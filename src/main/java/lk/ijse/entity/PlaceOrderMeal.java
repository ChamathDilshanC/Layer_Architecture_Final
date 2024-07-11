package lk.ijse.entity;

import lk.ijse.entity.tm.OrderMealCart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceOrderMeal {
    List<OrderMealCart> ordermealinfo;
}
