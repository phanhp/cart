package mvc.service.shopingcart;

import mvc.entity.OrderDetailsEntity;
import mvc.entity.OrderEntity;
import mvc.repository.OrderDetailsRepository;
import mvc.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@SessionScope
public class CartServiceImpl implements CartService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    Map<Integer, OrderDetailsEntity> cart = new HashMap<>();

    @Override
    public List<OrderDetailsEntity> viewCart() {
        List<OrderDetailsEntity> orderDetailsEntityList = new ArrayList<>(cart.values());
        return orderDetailsEntityList;
    }

    @Override
    public void addToCart(OrderDetailsEntity buyIn) {
        OrderDetailsEntity isInCart = cart.get(buyIn.getProduct().getProductId());
        if (isInCart == null) {
            cart.put(buyIn.getProduct().getProductId(), buyIn);
        } else {
            cart.get(buyIn.getProduct().getProductId()).setQuantity(isInCart.getQuantity() + buyIn.getQuantity());
        }
    }

    @Override
    public void removeFromCart(int productId) {
        cart.remove(productId);
    }

    @Override
    public void emptyCart() {
        cart.clear();
    }

    @Override
    public double totalPrice() {
        return cart.values().stream().mapToDouble(allDetails -> allDetails.getQuantity() * allDetails.getProduct().getProductPrice()).sum();
    }

    @Override
    public void saveToDataBase() {
        List<OrderDetailsEntity> orderDetailsList = new ArrayList<>(cart.values());
        OrderEntity order = new OrderEntity();
        order.setCustomerName("New Buyer");
        order.setCustomerAddress("New Buyer-Address");
        order.setOrderDate(LocalDate.now());
        orderRepository.save(order);
        for (int i = 0; i < orderDetailsList.size(); i++) {
            orderDetailsList.get(i).setOrder(order);
            orderDetailsRepository.save(orderDetailsList.get(i));
        }
    }
}

