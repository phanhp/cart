package mvc.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "order_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate orderDate;

    @Column(name = "customer_name")
    private String  customerName;

    @Column(name = "customer_address")
    private String customerAddress;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderDetailsEntity> orderDetails;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public List<OrderDetailsEntity> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailsEntity> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderEntity() {
    }

    public OrderEntity(LocalDate orderDate, String customerName, String customerAddress) {
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
    }

    public OrderEntity(int orderId, LocalDate orderDate, String customerName, String customerAddress, List<OrderDetailsEntity> orderDetails) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.orderDetails = orderDetails;
    }
}
