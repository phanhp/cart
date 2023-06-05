package mvc.controller;

import mvc.entity.OrderDetailsEntity;
import mvc.entity.ProductEntity;
import mvc.repository.OrderDetailsRepository;
import mvc.repository.ProductRepository;
import mvc.service.shopingcart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class CartController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartService cartService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String viewCart(Model model){
        model.addAttribute("cartList",cartService.viewCart());
        model.addAttribute("totalCost",cartService.totalPrice());
        return "cart/cart";
    }

    @RequestMapping(value = "/addProduct/{productId}", method = RequestMethod.GET)
    public String addProduct(@PathVariable int productId){
        ProductEntity product = (ProductEntity) productRepository.findProductById(productId);
        if(product != null){
            OrderDetailsEntity orderDetails = new OrderDetailsEntity();
            orderDetails.setProduct(product);
            orderDetails.setQuantity(1);
            cartService.addToCart(orderDetails);
        }
        return "redirect:/cart";
    }

    @RequestMapping(value = "remove/{productId}", method = RequestMethod.GET)
    public String removeProduct(@PathVariable int productId){
        cartService.removeFromCart(productId);
        return "redirect:/cart";
    }

    @RequestMapping(value="emptyCart", method = RequestMethod.GET)
    public String clearCart(){
        cartService.emptyCart();
        return "redirect:/";
    }

    @RequestMapping(value = "buy", method = RequestMethod.GET)
    public String buyItemAndSaveOrder(){
        cartService.saveToDataBase();
        return "redirect:/emptyCart";
    }



}
