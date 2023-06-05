package mvc.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_details")
public class OrderDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private int orderDetailsId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "quantity")
    private int quantity;

    public int getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(int orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderDetailsEntity() {
    }

    public OrderDetailsEntity(int quantity) {
        this.quantity = quantity;
    }

    public OrderDetailsEntity(int orderDetailsId, OrderEntity order, ProductEntity product, int quantity) {
        this.orderDetailsId = orderDetailsId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }
}
