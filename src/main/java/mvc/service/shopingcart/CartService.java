package mvc.service.shopingcart;

import mvc.entity.OrderDetailsEntity;

import java.util.List;

public interface CartService {
    List<OrderDetailsEntity> viewCart();

    void addToCart(OrderDetailsEntity buyIn);

    void removeFromCart(int productId);

    void emptyCart();

    double totalPrice();

    void saveToDataBase();
}
