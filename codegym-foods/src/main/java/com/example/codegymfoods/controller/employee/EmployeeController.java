package com.example.codegymfoods.controller.employee;

import com.example.codegymfoods.dto.employee.EmployeeDTO;
import com.example.codegymfoods.model.employee.Employee;
import com.example.codegymfoods.service.customer.IUserRoleService;
import com.example.codegymfoods.service.employee.IEmployeeService;
import com.example.codegymfoods.service.employee.IPositionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IPositionService positionService;

    @GetMapping("")
    public String getEmployee(@RequestParam(defaultValue = "", required = false) String search,
                              @RequestParam(required = false) Integer positionId,
                              @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        Page<Employee> employeePage;
        if (positionId == null || positionId == 0) {
            employeePage = this.employeeService.findAllByName(search, pageable);
        } else {
            employeePage = this.employeeService.findAllByNameByPositionId(search, positionId, pageable);
        }

        List<Integer> pageNumberList = new ArrayList<>();
        for (int i = 1; i <= employeePage.getTotalPages(); i++) {
            pageNumberList.add(i);
        }
        model.addAttribute("pageNumberList", pageNumberList);
        model.addAttribute("employee", employeePage);
        model.addAttribute("position", this.positionService.findAll());
        return "/employee/list-employee";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam Integer idDelete,
                                 RedirectAttributes redirectAttributes) {
        employeeService.delete(idDelete, employeeService.findById(idDelete).get());
        redirectAttributes.addFlashAttribute("message", "Xoá thành công");
        return "redirect:/list-employee";
    }

    @GetMapping("/create")
    public String showCreate(
            Model model
    ) {
        model.addAttribute("employeeCreateDTO", new EmployeeDTO());
        model.addAttribute("position", this.positionService.findAll());
        return "/employee/create-employee";
    }

    @PostMapping("/create")
    public String createEmployee(@Valid @ModelAttribute("employeeCreateDTO") EmployeeDTO employeeDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes, Model model
    ) throws ParseException {
        model.addAttribute("position", this.positionService.findAll());
        if (bindingResult.hasErrors()) {
            return "/employee/create-employee";
        } else if (employeeService.existsByEmail(employeeDTO.getEmail())) {
            model.addAttribute("message", "Email đã tồn tại, vui lòng nhập email khác");
            return "/employee/create-employee";
        } else if (employeeService.existsByPhoneNumber(employeeDTO.getPhoneNumber())) {
            model.addAttribute("message1", "Số điện thoại đã tồn tại, vui lòng nhập số điện thoại khác");
            return "/employee/create-employee";
        } else if (employeeService.existsByAppUser_UserName(employeeDTO.getAppUser().getUserName())) {
            model.addAttribute("message2", "Tài khoản đã tồn tại, vui lòng nhập tài khoản khác");
            return "/employee/create-employee";
        } else {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDTO, employee);
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            employee.setDateOfBirth(String.valueOf(formatter.parse(employeeDTO.getDateOfBirth())));
            employeeService.save(employee);
            redirectAttributes.addFlashAttribute("message", "Thêm mới thành công");
            return "redirect:/admin/employee";
        }
    }
}
