package com.example.customer_service.service;

import com.example.customer_service.dto.TransactionDTO;
import com.example.customer_service.entity.Customer;
import com.example.customer_service.entity.Transaction;
import com.example.customer_service.repository.CustomerRepository;
import com.example.customer_service.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface TransactionService {
    TransactionDTO.Response createTransaction(String ssnId, TransactionDTO.Request request);
    List<TransactionDTO.Response> getAllTransactions();
    List<TransactionDTO.Response> getTransactionsBySsnId(String ssnId);
}

@Service
class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public TransactionDTO.Response createTransaction(String ssnId, TransactionDTO.Request request) {
    Customer customer = customerRepository.findBySsnId(ssnId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

    Transaction transaction = new Transaction();
    transaction.setCustomer(customer);
    transaction.setType(request.getType());
    transaction.setAmount(request.getAmount());

    Transaction saved = transactionRepository.save(transaction);

    return new TransactionDTO.Response(
            saved.getId(),
            customer.getSsnId(),
            saved.getType().name(),
            saved.getAmount(),
            saved.getCreatedAt()
    );
}


    @Override
public List<TransactionDTO.Response> getAllTransactions() {
    return transactionRepository.findAll().stream()
            .map(t -> new TransactionDTO.Response(
                    t.getId(),
                    t.getCustomer().getSsnId(),
                    t.getType().name(),
                    t.getAmount(),
                    t.getCreatedAt()))
            .collect(Collectors.toList());
}

@Override
public List<TransactionDTO.Response> getTransactionsBySsnId(String ssnId) {
    return transactionRepository.findByCustomer_SsnId(ssnId).stream()
            .map(t -> new TransactionDTO.Response(
                    t.getId(),
                    t.getCustomer().getSsnId(),
                    t.getType().name(),
                    t.getAmount(),
                    t.getCreatedAt()))
            .collect(Collectors.toList());
}

}
