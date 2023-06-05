package mvc.controller;

import mvc.configuration.Config;
import mvc.entity.OrderDetailsEntity;
import mvc.entity.OrderEntity;
import mvc.entity.ProductEntity;
import mvc.repository.OrderDetailsRepository;
import mvc.repository.OrderRepository;
import mvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
    static OrderRepository orderRepository = (OrderRepository) context.getBean("orderRepository");
    static OrderDetailsRepository orderDetailsRepository = (OrderDetailsRepository) context.getBean("orderDetailsRepository");
    static ProductRepository productRepository = (ProductRepository) context.getBean("productRepository");

    public static void main(String[] args) {
        Main action = new Main();
//        action.createTestData();
//        action.createMultiProductData();
//        action.findProductByName("7");
        System.out.println(action.findProductById(17).getProductId());

    }

    public ProductEntity findProductById(int id){
        ProductEntity product = (ProductEntity) productRepository.findProductById(id);
        return product;
    }

    public List<Integer> allPageList(List<ProductEntity> productList, int lim) {
        int amountOfProduct = productList.size();
        int numberOfPage;
        if (amountOfProduct <= lim) {
            numberOfPage = 1;
        } else if (amountOfProduct % lim == 0) {
            numberOfPage = amountOfProduct / lim;
        } else {
            numberOfPage = (amountOfProduct / lim) + 1;
        }

        List<Integer> pageList = new ArrayList<>();

        for (int i = 1; i <= numberOfPage; i++) {
            pageList.add(i);
        }

        return pageList;
    }

    public List<ProductEntity> listProductOfPage(List<ProductEntity> productList, int lim, int pageNumber) {
        List<ProductEntity> listOfPage = new ArrayList<>();
        int beginAt = (pageNumber - 1) * lim;
        int endAt = beginAt + lim - 1;
        if (endAt > productList.size() - 1) {
            endAt = productList.size() - 1;
        }
        for (int i = beginAt; i <= endAt; i++) {
            listOfPage.add(productList.get(i));
        }
        return listOfPage;
    }

    public List<ProductEntity> findProductByName(String name) {
        List<ProductEntity> productList;
        if (name.matches("\\s*")) {
            productList = (List<ProductEntity>) productRepository.findAllProducts();
        } else {
            productList = (List<ProductEntity>) productRepository.findProductByName(name);
        }
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i).getProductId());
        }
        return productList;
    }

    public void createMultiProductData() {
        double a = 0;
        DecimalFormat df = new DecimalFormat("#.##");
        for (int i = 1; i <= 100; i++) {
            ProductEntity product = new ProductEntity();
            String productName = "";
            double productPrice = 0;
            if (i < 5) {
                int j = i + 5;
                productName = "product0" + j;
            } else {
                int j = i + 5;
                productName = "product" + j;
            }
            if (i == 1) {
                productPrice = 1787.96;
                a = productPrice;
            } else {

                if (i % 2 == 0) {
                    productPrice = Double.parseDouble(df.format(a * (1 + i / 186) + 56.71 - i * 5.33));
                } else {
                    productPrice = Double.parseDouble(df.format(a * (1 + i / 193) - 56.71 + i * 5.33));
                }
                a = productPrice;
            }

            product.setProductName(productName);
            product.setProductPrice(productPrice);
            productRepository.save(product);
        }
    }

    public void createTestData() {
        ProductEntity product1 = new ProductEntity("product01", 1997.23);
        productRepository.save(product1);
        ProductEntity product2 = new ProductEntity("product02", 2112.28);
        productRepository.save(product2);
        ProductEntity product3 = new ProductEntity("product03", 1789.51);
        productRepository.save(product3);
        ProductEntity product4 = new ProductEntity("product04", 2225.72);
        productRepository.save(product4);
        ProductEntity product5 = new ProductEntity("product05", 2183.14);
        productRepository.save(product5);

        OrderEntity order1 = new OrderEntity(LocalDate.parse("22-05-2023", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "Customer01", "Customer01-Address");

        OrderEntity order2 = new OrderEntity(LocalDate.parse("22-05-2023", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "Customer02", "Customer02-Address");

        OrderEntity order3 = new OrderEntity(LocalDate.parse("23-05-2023", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "Customer03", "Customer03-Address");

        OrderEntity order4 = new OrderEntity(LocalDate.parse("24-05-2023", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "Customer01", "Customer01-Address");

        //Order1
        OrderDetailsEntity details1Of1 = new OrderDetailsEntity(3);
        details1Of1.setProduct(product1);
        details1Of1.setOrder(order1);

        OrderDetailsEntity details2Of1 = new OrderDetailsEntity(4);
        details2Of1.setProduct(product2);
        details2Of1.setOrder(order1);

        OrderDetailsEntity details3Of1 = new OrderDetailsEntity(1);
        details3Of1.setProduct(product3);
        details3Of1.setOrder(order1);

        OrderDetailsEntity details4Of1 = new OrderDetailsEntity(2);
        details4Of1.setProduct(product4);
        details4Of1.setOrder(order1);

        orderRepository.save(order1);
        orderDetailsRepository.save(details1Of1);
        orderDetailsRepository.save(details2Of1);
        orderDetailsRepository.save(details3Of1);
        orderDetailsRepository.save(details4Of1);

        //Order2
        OrderDetailsEntity details1Of2 = new OrderDetailsEntity(1);
        details1Of2.setProduct(product1);
        details1Of2.setOrder(order2);

        OrderDetailsEntity details2Of2 = new OrderDetailsEntity(5);
        details2Of2.setProduct(product2);
        details2Of2.setOrder(order2);

        OrderDetailsEntity details3Of2 = new OrderDetailsEntity(1);
        details3Of2.setProduct(product5);
        details3Of2.setOrder(order2);

        orderRepository.save(order2);
        orderDetailsRepository.save(details1Of2);
        orderDetailsRepository.save(details2Of2);
        orderDetailsRepository.save(details3Of2);

        //Order3
        OrderDetailsEntity details1Of3 = new OrderDetailsEntity(1);
        details1Of3.setProduct(product1);
        details1Of3.setOrder(order3);

        OrderDetailsEntity details2Of3 = new OrderDetailsEntity(5);
        details2Of3.setProduct(product2);
        details2Of3.setOrder(order3);

        orderRepository.save(order3);
        orderDetailsRepository.save(details1Of3);
        orderDetailsRepository.save(details2Of3);

        //Order4
        OrderDetailsEntity details1Of4 = new OrderDetailsEntity(2);
        details1Of4.setProduct(product1);
        details1Of4.setOrder(order4);

        OrderDetailsEntity details2Of4 = new OrderDetailsEntity(3);
        details2Of4.setProduct(product2);
        details2Of4.setOrder(order4);

        OrderDetailsEntity details3Of4 = new OrderDetailsEntity(1);
        details3Of4.setProduct(product3);
        details3Of4.setOrder(order4);

        OrderDetailsEntity details4Of4 = new OrderDetailsEntity(2);
        details4Of4.setProduct(product4);
        details4Of4.setOrder(order4);

        OrderDetailsEntity details5Of4 = new OrderDetailsEntity(4);
        details5Of4.setProduct(product5);
        details5Of4.setOrder(order4);

        orderRepository.save(order4);
        orderDetailsRepository.save(details1Of4);
        orderDetailsRepository.save(details2Of4);
        orderDetailsRepository.save(details3Of4);
        orderDetailsRepository.save(details4Of4);
        orderDetailsRepository.save(details5Of4);
    }
}