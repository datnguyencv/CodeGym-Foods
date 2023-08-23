package com.example.codegymfoods.controller.cart;


import com.example.codegymfoods.dto.cart.CartDTO;
import com.example.codegymfoods.dto.product.ProductFromCartDTO;
import com.example.codegymfoods.model.bill.Bill;
import com.example.codegymfoods.model.bill.BillDetail;
import com.example.codegymfoods.model.customer.Customer;
import com.example.codegymfoods.model.product.Product;
import com.example.codegymfoods.model.product.ProductType;
import com.example.codegymfoods.service.bill.IBillDetailService;
import com.example.codegymfoods.service.bill.IBillService;
import com.example.codegymfoods.service.cart.ICartService;
import com.example.codegymfoods.service.cart.IProductFromCartService;
import com.example.codegymfoods.service.customer.ICustomerService;
import com.example.codegymfoods.service.product.IProductService;
import com.example.codegymfoods.service.product.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductTypeService productTypeService;
    @Autowired
    private IBillService billService;
    @Autowired
    private IBillDetailService billDetailService;

    @GetMapping("")
    public String disPlayBlog(Model model, @PageableDefault() Pageable pageable) {
        Page<Product> productList = productService.getBlogPage(pageable);
        List<ProductType> productTypeList = productTypeService.getAll();
        model.addAttribute("productTypeList", productTypeList);
        model.addAttribute("productList", productList);
        return "redirect:/home/success";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable(name = "id") int id, @SessionAttribute(name = "cartDTO") CartDTO cartDTO, RedirectAttributes redirectAttributes) {
        Product product = productService.getById(id);
        cartService.addToCart(product, cartDTO);
        redirectAttributes.addFlashAttribute("message", "Thêm vào giỏ hàng thành công");
        return "redirect:/home/success";

    }


    @GetMapping("/changeQuantity")
    public String changeQuantity(@RequestParam(value = "id") int id,
                                 @RequestParam(value = "quantity") int quantity,
                                 @SessionAttribute(name = "cartDTO") CartDTO cartDTO, Model model,
                                 RedirectAttributes redirectAttributes) {
        if (cartService.checkQuantity(id,quantity)){
            cartService.changeQuantity(id, quantity, cartDTO);
            redirectAttributes.addFlashAttribute("message", "Cập nhật số lượng thành công");
        }else {
            Product product = productService.getById(id);
            model.addAttribute("quantityOfProduct",product.getQuantity());
            redirectAttributes.addFlashAttribute("message", "Hiện sản phẩm còn: \n" + product.getQuantity() +" sản phẩm");
        }
        return "redirect:/cart/home";
    }

    @GetMapping("/deleteInCart")
    public String delete(@RequestParam(value = "idDelete") int id, @SessionAttribute(name = "cartDTO") CartDTO cartDTO,
                         RedirectAttributes redirectAttributes) {
        Map<Integer, Integer> cartMap = cartDTO.getSelectedProducts();
        cartMap.remove(id);
        redirectAttributes.addFlashAttribute("message", "Xoá sản phẩm thành công");

        return "redirect:/cart/home";
    }

    @GetMapping("/home")
    public String getProductsFromCart(@SessionAttribute(name = "cartDTO") CartDTO cartDTO, Model model) {
        Set<Integer> productsIds = cartDTO.getSelectedProducts().keySet();
        Map<Integer, Product> mapProducts = productService.getProductsById(productsIds).stream().collect(Collectors.toMap(Product::getId, product -> product));
        List<ProductFromCartDTO> productFromCartDTOList = cartDTO.getSelectedProducts().entrySet().stream()
                .map(e -> new ProductFromCartDTO(e.getKey(),
                        mapProducts.get(e.getKey()).getPicture(),
                        mapProducts.get(e.getKey()).getName(),
                        mapProducts.get(e.getKey()).getPrice(),
                        e.getValue(),
                        mapProducts.get(e.getKey()).getPrice() * e.getValue()))
                .collect(Collectors.toCollection(LinkedList::new));
        double totalBill = cartService.totalBill(productFromCartDTOList);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerService.findByUsername(user.getUsername());
        model.addAttribute("customer", customer.getName());
        model.addAttribute("totalBill", totalBill);
        model.addAttribute("productFromCartDTOList", productFromCartDTOList);
        return "/cart";
    }

    @GetMapping("/payment/{totalPrice}")
    String payment(@PathVariable(value = "totalPrice") double totalPrice, @SessionAttribute(name = "cartDTO") CartDTO cartDTO,
                   RedirectAttributes redirectAttributes) {

        Set<Integer> productsIds = cartDTO.getSelectedProducts().keySet();
        Map<Integer, Product> mapProducts = productService.getProductsById(productsIds).stream().collect(Collectors.toMap(Product::getId, product -> product));
        List<ProductFromCartDTO> productFromCartDTOList = cartDTO.getSelectedProducts().entrySet().stream()
                .map(e -> new ProductFromCartDTO(e.getKey(),
                        mapProducts.get(e.getKey()).getPicture(),
                        mapProducts.get(e.getKey()).getName(),
                        mapProducts.get(e.getKey()).getPrice(),
                        e.getValue(),
                        mapProducts.get(e.getKey()).getPrice() * e.getValue()))
                .collect(Collectors.toCollection(LinkedList::new));
        productService.reduceQauntity(productFromCartDTOList);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerService.findByUsername(user.getUsername());
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = formatter.format(currentDate);
        Bill bill = new Bill(date, true, totalPrice, customer);
        billService.saveBill(bill);
        for (int i = 0; i < productFromCartDTOList.size(); i++) {
            Product product = productService.getById(productFromCartDTOList.get(i).getId());
            BillDetail billDetail = new BillDetail(productFromCartDTOList.get(i).getQuantityProductFromCartDTO(),bill,product);
            billDetailService.saveBillDetail(billDetail);
        }
        Map<Integer, Integer> cartMap = cartDTO.getSelectedProducts();
        cartMap.clear();
        redirectAttributes.addFlashAttribute("message", "Thanh toán thành công");

        return "redirect:/home/success";
    }

    @GetMapping("/saveBill/{totalPrice}")
    String saveBill(@PathVariable(value = "totalPrice") double totalPrice, @SessionAttribute(name = "cartDTO") CartDTO cartDTO,
                    RedirectAttributes redirectAttributes) {

        Set<Integer> productsIds = cartDTO.getSelectedProducts().keySet();
        Map<Integer, Product> mapProducts = productService.getProductsById(productsIds).stream().collect(Collectors.toMap(Product::getId, product -> product));
        List<ProductFromCartDTO> productFromCartDTOList = cartDTO.getSelectedProducts().entrySet().stream()
                .map(e -> new ProductFromCartDTO(e.getKey(),
                        mapProducts.get(e.getKey()).getPicture(),
                        mapProducts.get(e.getKey()).getName(),
                        mapProducts.get(e.getKey()).getPrice(),
                        e.getValue(),
                        mapProducts.get(e.getKey()).getPrice() * e.getValue()))
                .collect(Collectors.toCollection(LinkedList::new));
        productService.reduceQauntity(productFromCartDTOList);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerService.findByUsername(user.getUsername());
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = formatter.format(currentDate);
        Bill bill = new Bill(date, false, totalPrice, customer);
        billService.saveBill(bill);

        for (int i = 0; i < productFromCartDTOList.size(); i++) {
            Product product = productService.getById(productFromCartDTOList.get(i).getId());
            BillDetail billDetail = new BillDetail(productFromCartDTOList.get(i).getQuantityProductFromCartDTO(),bill,product);
            billDetailService.saveBillDetail(billDetail);
        }

        Map<Integer, Integer> cartMap = cartDTO.getSelectedProducts();
        cartMap.clear();
        redirectAttributes.addFlashAttribute("message", "Lưu Hoá đơn thành công");
        return "redirect:/home/success";
    }

    @GetMapping("/paymentAfter/{id}")
    String paymentAfter(@PathVariable(value = "id") int id ,@SessionAttribute(name = "cartDTO") CartDTO cartDTO,
                        RedirectAttributes redirectAttributes) {

        Bill bill = billService.getBillById(id);
        bill.setStatus(true);
        billService.saveBill(bill);
        Map<Integer, Integer> cartMap = cartDTO.getSelectedProducts();
        cartMap.clear();
        redirectAttributes.addFlashAttribute("message", "Thanh toán thành công");
        return "redirect:/user/detail";
    }

}
