package com.example.codegymfoods.controller.product;

import com.example.codegymfoods.dto.cart.CartDTO;
import com.example.codegymfoods.dto.product.ProductDto;
import com.example.codegymfoods.model.product.Product;
import com.example.codegymfoods.model.product.ProductType;
import com.example.codegymfoods.service.product.IProductService;
import com.example.codegymfoods.service.product.IProductTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProductControllor {

    @Autowired
   private IProductService productService;
    @Autowired
   private IProductTypeService productTypeService;

    @GetMapping("")
    public String disPlayBlog(Model model, @PageableDefault() Pageable pageable) {
        Page<Product> productList = productService.getBlogPage(pageable);
        List<ProductType> productTypeList = productTypeService.getAll();
        model.addAttribute("productTypeList", productTypeList);
        model.addAttribute("productList", productList);
        return "/index";
    }

    @GetMapping("/find/{id}")
    public String findProductByType(@PathVariable(value = "id")int id,Model model){
        List<Product> productList = productService.getAllByType(id);
        model.addAttribute("productList",productList);
        return "/index";
    }


    @GetMapping("/deleteProduct")

    public String deleteProduct(@RequestParam(value = "id") int id, RedirectAttributes redirectAttributes) {
        productService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Xoá thành công");
        return "redirect:/home/success";
    }

    @GetMapping("/creatProduct")
    public String creatProduct(Model model) {
        List<ProductType> productTypeList = productTypeService.getAll();
        ProductDto productDto = new ProductDto();
        model.addAttribute("productTypeList", productTypeList);
        model.addAttribute("productDto", productDto);
        return "/product/productCreat";
    }

    @GetMapping("/creat")
    public String creat(@Validated @ModelAttribute(value = "productDto") ProductDto productDto,
                        BindingResult bindingResult,
                        Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            List<ProductType> productTypeList = productTypeService.getAll();
            model.addAttribute("productTypeList", productTypeList);
            model.addAttribute("productDto", productDto);
            return "/product/productCreat";
        }
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        productService.creat(product);
        redirectAttributes.addFlashAttribute("message", "Thêm sản phẩm thành công");
        return "redirect:/home/success";
    }

    @GetMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable int id, Model model) {
        Product product = productService.getById(id);
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        List<ProductType> productTypeList = productTypeService.getAll();
        model.addAttribute("productTypeList", productTypeList);
        model.addAttribute("productDto", productDto);
        return "/product/productUpdate";
    }

    @GetMapping("/update")
    public String update(@Validated @ModelAttribute(value = "productDto") ProductDto productDto,
                         BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            List<ProductType> productTypeList = productTypeService.getAll();
            model.addAttribute("productTypeList", productTypeList);
            model.addAttribute("productDto", productDto);
            return "/product/productUpdate";
        }
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        productService.updateById(product);
        redirectAttributes.addFlashAttribute("message", "Cập nhật sản phẩm thành công");
        return "redirect:/home/success";
    }

}
