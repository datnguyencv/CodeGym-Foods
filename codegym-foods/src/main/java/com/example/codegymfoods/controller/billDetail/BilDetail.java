package com.example.codegymfoods.controller.billDetail;

import com.example.codegymfoods.service.bill.IBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/billDetail")
public class BilDetail {
    @Autowired
    IBillDetailService billDetailService;
    @GetMapping("/{id}")
    String disPlayBillDetail(@PathVariable(value = "id")int id, Model model){
        List<BilDetail> bilDetailList = billDetailService.disPlayBill(id);
        model.addAttribute("billDetail",bilDetailList);
        return "/userInfoPage";
    }

}
