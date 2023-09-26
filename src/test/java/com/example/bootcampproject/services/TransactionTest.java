package com.example.bootcampproject.services;

import com.example.bootcampproject.dto.LoanTransactionDTO;
import com.example.bootcampproject.entity.Employee;
import com.example.bootcampproject.entity.EmployeeCardDetails;
import com.example.bootcampproject.entity.EmployeeIssue;
import com.example.bootcampproject.entity.Item;
import com.example.bootcampproject.entity.LoanCard;
import com.example.bootcampproject.entity.LoanTransaction;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;
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
import static org.assertj.core.api.Assertions.assertThat;
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
        given(loanTransactionRepository.findById(1L)).willReturn(Optional.of(loanTransaction));

        // when
        try{
            LoanTransaction savedLoan;
            when(loanTransactionServiceImpl.getLoanTransactionById(Long.valueOf(loanTransaction.getTransactionId())).getBody()).thenReturn(savedLoan);
            assertThat(savedLoan).isNotNull();
        }catch(ResourceNotFoundException e){
            System.out.println(e);
        }

    }
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){
        // given - precondition or setup
        given(loanTransactionRepository.findById(Long.valueOf(loanTransaction.getTransactionId())))
                .willReturn(Optional.empty());

        given(loanTransactionRepository.save(loanTransaction)).willReturn(loanTransaction);

        System.out.println(loanTransactionRepository);
        System.out.println(loanTransactionServiceImpl);
        LoanTransactionDTO loanTransactionDTO = new LoanTransactionDTO(loanTransaction.getStatus(), false, loanTransaction.getLoanCard().getLoanType(),loanTransaction.getTransactionId(), 
        loanTransaction.getTimestamp(), loanTransaction.getAmount(), loanTransaction.getLoanCard().getLoanId(), 
        loanTransaction.getEmployee().getEmployeeId(), null , loanTransaction.getLoanCard().getDuration());;
        // when -  action or the behaviour that we are going test
        try{
            LoanTransaction savedTransaction = loanTransactionServiceImpl.registerLoanTransaction(loanTransactionDTO).getBody();
            System.out.println(savedTransaction);
            assertThat(savedTransaction).isNotNull();
            
        }catch(ResourceNotFoundException e){
            System.out.print(e);
        }

        // then - verify the output
    }



    
}
