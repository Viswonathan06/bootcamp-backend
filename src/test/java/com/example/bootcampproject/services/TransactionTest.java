package com.example.bootcampproject.services;

import com.example.bootcampproject.entity.Employee;
import com.example.bootcampproject.entity.EmployeeCardDetails;
import com.example.bootcampproject.entity.EmployeeIssue;
import com.example.bootcampproject.entity.Item;
import com.example.bootcampproject.entity.LoanCard;
import com.example.bootcampproject.entity.LoanTransaction;
import com.example.bootcampproject.repository.LoanTransactionRepository;
import com.example.bootcampproject.service.LoanTransactionService;
import com.example.bootcampproject.service.LoanTransactionServiceImpl;

import org.junit.jupiter.api.extension.*;

import static org.mockito.Mockito.verify;

import java.util.Optional;
import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.InjectMocks;
import static org.mockito.BDDMockito.*;
import org.mockito.Mock;
import org.mockito.ArgumentMatchers.*;

import org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class TransactionTest {

    @Mock
    private LoanTransactionRepository loanTransactionRepository;
    @InjectMocks
    private LoanTransactionServiceImpl loanTransactionServiceImpl;
    private LoanTransaction loanTransaction;
    private EmployeeIssue employeeIssue;
    private EmployeeCardDetails employeeCardDetails;
    private LoanCard loanCard;
    private Item item;
    private  Employee employee;
    
    @BeforeEach void setUpLoan(){
        
        employee = new Employee(
            "U143245",
            "ab123c@gmail.com",
            "123456",
            "Abu Sharma",
            "Developer",
            "CSBBT",
            "Male",
            Date.valueOf("1978-12-17"),
            Date.valueOf("2014-08-22"),
            employeeCardDetails,
            employeeIssue);
        item = new Item();
        loanTransaction = new LoanTransaction("pending", 123, Date.valueOf("2023-09-15"), 1234, loanCard, employee, item);
        
        System.out.println("Initialized!");
    }

    @Test void getAllLoan(){
        loanTransactionServiceImpl.getAllLoanTransaction();
        verify(loanTransactionRepository).findAll();
    }

    @Test
    public void givenTransactionId_thenReturnTransactionObj(){
        given(loanTransactionRepository.findById(Long.valueOf(1))).willReturn(Optional.of(loanTransaction));
        LoanTransaction savedTransaction = loanTransactionServiceImpl.getLoanTransactionById(Long.valueOf(loanTransaction.getTransactionId())).get(0);
        assertThat(savedTransaction).isnotNull();
    }

    
}
