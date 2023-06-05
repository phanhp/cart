package mvc.controller;

import mvc.entity.ProductEntity;
import mvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAllProduct(Model model) {
        Main action = new Main();
        List<ProductEntity> allProductsList = productRepository.findAllProducts();
        List<Integer> pageList = action.allPageList(allProductsList, 10);
        List<ProductEntity> productList = action.listProductOfPage(allProductsList, 10, 1);
        model.addAttribute("productList", productList);
        model.addAttribute("pageList", pageList);
        return "home/home";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String findProductWhichNameContains(Model model, @RequestParam("searchInput") String searchName) {
        Main action = new Main();
        List<ProductEntity> searchProductList;
        if (searchName.matches("\\s*")) {
            searchProductList = productRepository.findAllProducts();
        } else {
            searchProductList = productRepository.findProductByName(searchName);
        }
        List<Integer> pageList = action.allPageList(searchProductList, 10);
        List<ProductEntity> productList = action.listProductOfPage(searchProductList, 10, 1);
        model.addAttribute("productList", productList);
        model.addAttribute("pageList", pageList);
        return "home/home";
    }

    @RequestMapping(value = "page/{page}", method = RequestMethod.GET)
    public String allProductListAtPage(Model model, @PathVariable int page) {
        Main action = new Main();
        List<ProductEntity> allProductsList = productRepository.findAllProducts();
        List<Integer> pageList = action.allPageList(allProductsList, 10);
        List<ProductEntity> productList = action.listProductOfPage(allProductsList, 10, page);
        model.addAttribute("productList", productList);
        model.addAttribute("pageList", pageList);
        return "home/home";
    }

}
