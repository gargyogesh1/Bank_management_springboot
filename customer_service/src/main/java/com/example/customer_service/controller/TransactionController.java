package com.example.customer_service.controller;

import com.example.customer_service.dto.TransactionDTO;
import com.example.customer_service.entity.Transaction;
import com.example.customer_service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/{ssnId}")
    public ResponseEntity<TransactionDTO.Response> createTransaction(
            @PathVariable String ssnId,
            @RequestBody TransactionDTO.Request request) {
        return ResponseEntity.ok(transactionService.createTransaction(ssnId, request));
    }


    @GetMapping
public ResponseEntity<List<TransactionDTO.Response>> getAllTransactions() {
    return ResponseEntity.ok(transactionService.getAllTransactions());
}

@GetMapping("/ssn/{ssnId}")
public ResponseEntity<List<TransactionDTO.Response>> getTransactionsBySsnId(@PathVariable String ssnId) {
    return ResponseEntity.ok(transactionService.getTransactionsBySsnId(ssnId));
}
}
