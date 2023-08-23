package com.example.codegymfoods.controller;

import com.example.codegymfoods.dto.cart.CartDTO;
import com.example.codegymfoods.model.customer.Customer;
import com.example.codegymfoods.model.employee.Employee;
import com.example.codegymfoods.model.product.Product;
import com.example.codegymfoods.model.product.ProductType;
import com.example.codegymfoods.service.blog.IBlogService;
import com.example.codegymfoods.service.customer.ICustomerService;
import com.example.codegymfoods.service.employee.IEmployeeService;
import com.example.codegymfoods.service.product.IProductService;
import com.example.codegymfoods.service.product.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/home")
@SessionAttributes(value = "cartDTO")
public class HomeController {
    @ModelAttribute(name = "cartDTO")
    private CartDTO initCartDTO() {
        return new CartDTO();
    }
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IBlogService iBlogService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductTypeService productTypeService;
    @GetMapping("")
    public String index(@RequestParam(defaultValue = "", required = false) String searchProduct,
                        @RequestParam(defaultValue = "", required = false) String searchBlog,
                        @RequestParam(required = false) Integer productID, Model model,
                        @RequestParam(defaultValue = "0") int pagePro,
                        @RequestParam(defaultValue = "0") int pageBlog,
                        HttpServletRequest request) {
        Pageable pageProParams = PageRequest.of(pagePro, 6, Sort.Direction.DESC, "id");
        Pageable pageBlogParams = PageRequest.of(pageBlog, 2, Sort.Direction.DESC, "id");

        Page<Product> productList;
        if (productID==null || productID==0){
            productList = productService.getProductPageName(searchProduct, pageProParams);
        } else {
            productList = productService.getProductByIdName(searchProduct, productID, pageProParams);
        }
        List<ProductType> productTypeList = productTypeService.getAll();
        model.addAttribute("blogList", this.iBlogService.getBlog(searchBlog,pageBlogParams));
        model.addAttribute("productTypeList", productTypeList);
        model.addAttribute("productList", productList);
        model.addAttribute("request",request);
        return "index";
    }
    @GetMapping("/success")
    public String disPlay(@RequestParam(defaultValue = "", required = false) String searchProduct,
                          @RequestParam(defaultValue = "", required = false) String searchBlog,
                          @RequestParam(required = false) Integer productID,Model model,
                          @RequestParam(defaultValue = "0") int pagePro,
                          @RequestParam(defaultValue = "0") int pageBlog,
                          HttpServletRequest request) {
        Pageable pageProParams = PageRequest.of(pagePro, 6, Sort.Direction.DESC, "id");
        Pageable pageBlogParams = PageRequest.of(pageBlog, 2, Sort.Direction.DESC, "id");

        Page<Product> productList;
        if (productID==null || productID==0){
            productList = productService.getProductPageName(searchProduct, pageProParams);
        } else {
            productList = productService.getProductByIdName(searchProduct, productID, pageProParams);
        }
        List<ProductType> productTypeList = productTypeService.getAll();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerService.findByUsername(user.getUsername());
        Employee employee = employeeService.findByUsername(user.getUsername());
        model.addAttribute("blogList", this.iBlogService.getBlog(searchBlog,pageBlogParams));
        model.addAttribute("customer",customer);
        model.addAttribute("employee",employee);
        model.addAttribute("productTypeList", productTypeList);
        model.addAttribute("productList", productList);
        model.addAttribute("request", request);
        return "index";
    }
}
