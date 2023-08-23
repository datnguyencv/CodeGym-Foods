package com.example.codegymfoods.repository.bill;

import com.example.codegymfoods.controller.billDetail.BilDetail;
import com.example.codegymfoods.model.bill.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBillDetailRepository extends JpaRepository<BillDetail,Integer> {
    @Query(value = "select * from bill_detail where bill_id = ?" ,nativeQuery = true)
    List<BilDetail> getAllBillDetail(int id);
}
