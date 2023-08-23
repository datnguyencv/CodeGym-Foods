package com.example.codegymfoods.controller.login;

import com.example.codegymfoods.dto.customer.CustomerDTO;
import com.example.codegymfoods.model.customer.Customer;
import com.example.codegymfoods.model.login.AppRole;
import com.example.codegymfoods.model.login.AppUser;
import com.example.codegymfoods.model.login.UserRole;
import com.example.codegymfoods.service.customer.impl.CustomerService;
import com.example.codegymfoods.service.customer.impl.UserRoleService;
import com.example.codegymfoods.utils.EncrytedPasswordUtils;
import com.example.codegymfoods.utils.WebUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/account")
public class LoginController extends EncrytedPasswordUtils {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EncrytedPasswordUtils encrytedPasswordUtils;
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("customerDto", new CustomerDTO());
//        model.addAttribute("customerType", customerTypeService.findAllCustomerType());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("customerDto") CustomerDTO customerCreateDTO, BindingResult bindingResult, Model model, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
//            model.addAttribute("customerType", customerTypeService.findAllCustomerType());
            return "register";
        } else if (customerService.existsByEmail(customerCreateDTO.getEmail())) {
            model.addAttribute("message", "Email đã tồn tại, vui lòng nhập email khác");
            return "register";
        } else if (customerService.existsByPhoneNumber(customerCreateDTO.getPhoneNumber())) {
            model.addAttribute("message1", "Số điện thoại đã tồn tại, vui lòng nhập số điện thoại khác");
//            model.addAttribute("customerType", customerTypeService.findAllCustomerType());
            return "register";
        } else if (customerService.existsByAppUser_UserName(customerCreateDTO.getAppUser().getUserName())) {
            model.addAttribute("message2", "Tài khoản đã tồn tại, vui lòng nhập tài khoản khác");
//            model.addAttribute("customerType", customerTypeService.findAllCustomerType());
            return "register";
        } else {
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerCreateDTO, customer);
            customer.getAppUser().setEncrytedPassword(encrytePassword(customer.getAppUser().getEncrytedPassword()));
            customerService.saveCustomer(customer);
            AppUser appUser = customer.getAppUser();
            AppRole appRole = new AppRole(2, "ROLE_USER");
            userRoleService.saveUserRole(new UserRole(appUser, appRole));
            redirect.addFlashAttribute("msg", "Đăng ký thành công");
            return "redirect:/account/login";
        }
    }

    @GetMapping(value = "/")
    public String homePage(Model model) {
        return "index";
    }
    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping(value = "/login")
    public String loginSuccessfulPage(Model model, RedirectAttributes redirect){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user",user.getUsername());
        redirect.addFlashAttribute("message", "Đăng nhập thành công");
//        return "redirect:/";
        return "/index";
    }
    @GetMapping(value = "/logout")
    public String logoutSuccessfulPage(Model model, RedirectAttributes redirect) {
//        model.addAttribute("message", "Đăng xuất thành công");
        redirect.addFlashAttribute("message", "Đăng xuất thành công");
        return "redirect:/account/login";
    }


    @GetMapping(value = "/userInfo")
    public String userInfo(Model model, Principal principal) {

        String userName = principal.getName();


        System.out.println("User Name: " + userName);

        User loginUser = (User)((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString((org.springframework.security.core.userdetails.User) loginUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }


    @GetMapping(value = "/admin")
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User)((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString((org.springframework.security.core.userdetails.User) loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User)((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Xin chào " + principal.getName() //
                    + "<br> Bạn không có quyền truy cập vào trang này!";
            model.addAttribute("message", message);

        }
        return "403Page";
    }

}