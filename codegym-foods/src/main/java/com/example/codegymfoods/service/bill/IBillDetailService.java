package com.example.codegymfoods.service.bill;

import com.example.codegymfoods.controller.billDetail.BilDetail;
import com.example.codegymfoods.model.bill.BillDetail;

import java.util.List;

public interface IBillDetailService {
    void saveBillDetail(BillDetail billDetail);

    List<BilDetail> disPlayBill(int id);
}
