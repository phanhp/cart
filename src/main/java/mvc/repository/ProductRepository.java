package mvc.repository;

import mvc.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    @Query(value = "select * from products", nativeQuery = true)
    List<ProductEntity> findAllProducts();

    @Query(value = "select * from products where product_id = ?1", nativeQuery = true)
    ProductEntity findProductById(int productId);

    @Query(value = "select * from products where product_name like %?1%", nativeQuery = true)
    List<ProductEntity> findProductByName(String productName);
}
