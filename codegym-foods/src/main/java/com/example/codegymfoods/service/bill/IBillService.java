package com.example.codegymfoods.service.bill;

import com.example.codegymfoods.model.bill.Bill;

import java.util.List;

public interface IBillService {
    void saveBill(Bill bill);

    Bill getBillById(int id);

    List<Bill> getBillByIdUser(int idOfCustomer);
}
