package com.example.codegymfoods.controller.customer.admin;

import com.example.codegymfoods.model.customer.Customer;
import com.example.codegymfoods.model.login.AppUser;
import com.example.codegymfoods.model.login.UserRole;
import com.example.codegymfoods.service.customer.impl.CustomerService;
import com.example.codegymfoods.service.customer.impl.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("")
    public String list(Model model, @PageableDefault(size = 2) Pageable pageable,
                       @RequestParam(name = "search", defaultValue = "" , required = false) String name) {
        Sort sort = Sort.by("id").descending();
        model.addAttribute("name", name);
        Pageable sortedPage = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Customer> customerPage = customerService.findByNameCustomer(name, sortedPage);
        List<Integer> pageNumberList = new ArrayList<>();
        for (int i = 1; i <= customerPage.getTotalPages(); i++) {
            pageNumberList.add(i);
        }
        model.addAttribute("pageNumberList", pageNumberList);
        model.addAttribute("customerList", customerPage);
        return "/customer/list-customer";
    }


    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam(name = "delete") Integer idCustomer, RedirectAttributes redirect) {
        Customer customer = customerService.findByIdCustomer(idCustomer);
        AppUser appUser = customer.getAppUser();
        UserRole userRole = userRoleService.findUserRoleByAppUser(appUser);
        userRoleService.deleteUserRole(userRole);
        customerService.deleteCustomer(idCustomer);
        redirect.addFlashAttribute("message", "Xóa thành công");
        return "redirect:/customer";
    }
}
