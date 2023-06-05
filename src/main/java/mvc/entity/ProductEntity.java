package mvc.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private double productPrice;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<OrderDetailsEntity> orderDetails;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public List<OrderDetailsEntity> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailsEntity> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public ProductEntity() {
    }

    public ProductEntity(String productName, double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public ProductEntity(int productId, String productName, double productPrice, List<OrderDetailsEntity> orderDetails) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.orderDetails = orderDetails;
    }
}
