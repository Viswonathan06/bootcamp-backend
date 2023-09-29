package com.example.bootcampproject.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.example.bootcampproject.dto.LoanTransactionDTO;
import com.example.bootcampproject.entity.Employee;
import com.example.bootcampproject.entity.EmployeeCardDetails;
import com.example.bootcampproject.entity.EmployeeIssue;
import com.example.bootcampproject.entity.Item;
import com.example.bootcampproject.entity.LoanCard;
import com.example.bootcampproject.entity.LoanTransaction;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;
import com.example.bootcampproject.repository.ItemRepository;
import com.example.bootcampproject.repository.LoanTransactionRepository;
import com.example.bootcampproject.service.ItemServiceImpl;
import com.example.bootcampproject.service.LoanTransactionServiceImpl;



@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TransactionTest {

    @Mock
    private LoanTransactionRepository loanTransactionRepository;
    @Mock
    private ItemRepository itemRepository;
    @InjectMocks
    private LoanTransactionServiceImpl loanTransactionServiceImpl;
    @InjectMocks
    private ItemServiceImpl itemServiceImpl;
    private LoanTransaction loanTransaction;
    private EmployeeIssue employeeIssue;
    private EmployeeCardDetails employeeCardDetails;
    private LoanCard loanCard;
    private Item item;
    private  Employee employee;
    
    @BeforeEach void setUpLoan(){
        
        employee = new Employee(
            1,
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
        item = new Item(1, "HIGH TOP","INSTOCK", "NIKE", "CLOTHING", 1234);
        loanCard = new LoanCard("CLOTHING", 123);
        loanTransaction = new LoanTransaction("pending", 123, Date.valueOf("2023-09-15"), 1234, loanCard, employee, item);
        System.out.println("Initialized!");
    }

    @Test void getAllLoan(){
        loanTransactionServiceImpl.getAllLoanTransaction();
        verify(loanTransactionRepository).findAll();
    }

    @Test
    public void givenTransactionId_thenReturnTransactionObj(){
        given(loanTransactionRepository.findById(123L)).willReturn(Optional.of(loanTransaction));

        // when
        try{
            LoanTransaction savedLoan = loanTransactionServiceImpl.getLoanTransactionById(Long.valueOf(loanTransaction.getTransactionId())).getBody();
            assertThat(savedLoan).isNotNull();
        }catch(ResourceNotFoundException e){
            System.out.println(e);
        }

    }

    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        // given - precondition or setup
        given(loanTransactionRepository.save(loanTransaction)).willReturn(loanTransaction);
        loanTransaction.setStatus("ACCEPTED");
        // when -  action or the behaviour that we are going test
        LoanTransactionDTO loanTransactionDTO = new LoanTransactionDTO(loanTransaction.getStatus(), false, loanTransaction.getLoanCard().getLoanType(),loanTransaction.getTransactionId(), 
        loanTransaction.getTimestamp(), loanTransaction.getAmount(), loanTransaction.getLoanCard().getLoanId(), 
        loanTransaction.getEmployee().getEmployeeId(),loanTransaction.getItem().getItemId() , loanTransaction.getLoanCard().getDuration());
        try{
            List<LoanTransactionDTO> updatedTransactions= loanTransactionServiceImpl.updateLoanTransaction(Long.valueOf(loanTransactionDTO.getTransactionId()), loanTransactionDTO).getBody();
            LoanTransactionDTO updatedTransaction = new LoanTransactionDTO();
            for(LoanTransactionDTO temp : updatedTransactions){
                if(temp.getTransactionId() == loanTransaction.getTransactionId()){
                    updatedTransaction = temp;
                }
            }
             
            // then - verify the output
            assertThat(updatedTransaction.getStatus()).isEqualTo("ACCEPTED"); 
        }catch(ResourceNotFoundException E){
            System.out.println(E);
        }
    }


    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){
        // given - precondition or setup
        given(loanTransactionRepository.findById(Long.valueOf(loanTransaction.getTransactionId())))
                .willReturn(Optional.empty());

        
        System.out.println(loanTransactionRepository);
        System.out.println(loanTransactionServiceImpl);
        LoanTransactionDTO loanTransactionDTO = new LoanTransactionDTO(loanTransaction.getStatus(), false, loanTransaction.getLoanCard().getLoanType(),loanTransaction.getTransactionId(), 
        loanTransaction.getTimestamp(), loanTransaction.getAmount(), loanTransaction.getLoanCard().getLoanId(), 
        loanTransaction.getEmployee().getEmployeeId(),loanTransaction.getItem().getItemId() , loanTransaction.getLoanCard().getDuration());;
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
