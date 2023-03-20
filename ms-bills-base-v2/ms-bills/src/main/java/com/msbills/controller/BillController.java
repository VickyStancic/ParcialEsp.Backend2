package com.msbills.controller;

import com.msbills.models.Bill;
import com.msbills.service.BillService;
import com.msbills.models.BillWithUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

  private final BillService service;

  @GetMapping("/all")
  @PreAuthorize("hasAuthority('GROUP_provider')")
  public ResponseEntity<List<Bill>> getAll() {
    return ResponseEntity.ok().body(service.getAllBill());
  }


  @PostMapping()
  @PreAuthorize("hasAuthority('GROUP_provider')")
  public ResponseEntity<Bill> saveBill(@RequestBody Bill bill) {
    return ResponseEntity.ok().body(service.saveBill(bill));
  }

  @GetMapping("/detail/{username}")
  @PreAuthorize("hasAuthority('GROUP_client')")
  public List<BillWithUser> findAllByUsername(@PathVariable("username") String username){
    return service.findAllByUsername(username);
  }
}
