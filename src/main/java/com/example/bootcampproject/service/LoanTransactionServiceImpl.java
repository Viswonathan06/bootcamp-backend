package com.example.bootcampproject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.bootcampproject.dto.LoanTransactionDTO;
import com.example.bootcampproject.entity.Employee;
import com.example.bootcampproject.entity.LoanTransaction;
import com.example.bootcampproject.entity.Item;
import com.example.bootcampproject.entity.LoanCard;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;
import com.example.bootcampproject.repository.LoanTransactionRepository;
import com.example.bootcampproject.repository.EmployeeRepository;
import com.example.bootcampproject.repository.ItemRepository;
import com.example.bootcampproject.repository.LoanCardRepository;

import jakarta.validation.Valid;

@Service
public class LoanTransactionServiceImpl implements LoanTransactionService{
    @Autowired
    private LoanTransactionRepository loanTransactionRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private LoanCardRepository loanCardRepository;
    @Autowired
    private LoanCardService loanCardService;
    

    @Override
    public List < LoanTransaction > getAllLoanTransaction() {
        List <LoanTransactionDTO> loanList = new ArrayList();
        for( LoanTransaction lt :ltrans){
            LoanTransactionDTO loanDto = new LoanTransactionDTO(lt.getLoanCard().getLoanType(),lt.getTransactionId(), 
            lt.getTimestamp(), lt.getAmount(), lt.getLoanCard().getLoanId(), 
            lt.getEmployee().getEmployeeId(), lt.getItem().getItemId(), lt.getLoanCard().getDuration());
            loanList.add(loanDto);
        }
        return loanTransactionRepository.findAll();
    }

    @Override
    public ResponseEntity<List < LoanTransactionDTO >> getLoanTransactionByEmployeeId(@PathVariable(value = "id") Integer employeeId) {
        List <LoanTransaction> ltrans = loanTransactionRepository.findByEmployee_EmployeeId(employeeId);
        List <LoanTransactionDTO> loanList = new ArrayList();
        for( LoanTransaction lt :ltrans){
            LoanTransactionDTO loanDto = new LoanTransactionDTO(lt.getLoanCard().getLoanType(),lt.getTransactionId(), 
            lt.getTimestamp(), lt.getAmount(), lt.getLoanCard().getLoanId(), 
            lt.getEmployee().getEmployeeId(), lt.getItem().getItemId(), lt.getLoanCard().getDuration());
            loanList.add(loanDto);
        }
        // .orElseThrow(() -> new ResourceNotFoundException("LoanTransaction not found for this id :: " + loanTransactionId));
        return ResponseEntity.ok().body(loanList);
    }
    
    @Override
    public ResponseEntity < LoanTransaction > getLoanTransactionById(@PathVariable(value = "id") Long loanTransactionId)
    throws ResourceNotFoundException {
        LoanTransaction loanTransaction = loanTransactionRepository.findById(loanTransactionId)
            .orElseThrow(() -> new ResourceNotFoundException("LoanTransaction not found for this id :: " + loanTransactionId));
        return ResponseEntity.ok().body(loanTransaction);
    }
    @Override
    public LoanTransaction createLoanTransaction(@Valid @RequestBody LoanTransaction loanTransaction) {
        return loanTransactionRepository.save(loanTransaction);
    }
    
    @Override
    public Map < String, Boolean > deleteLoanTransaction(@PathVariable(value = "id") Long loanTransactionId)
    throws ResourceNotFoundException {
        LoanTransaction loanTransaction = loanTransactionRepository.findById(loanTransactionId)
            .orElseThrow(() -> new ResourceNotFoundException("LoanTransaction not found for this id :: " + loanTransactionId));

        loanTransactionRepository.delete(loanTransaction);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @Override
    public ResponseEntity<LoanTransaction> registerLoanTransaction(@Valid @RequestBody LoanTransactionDTO loanTransactionDTO)
     throws ResourceNotFoundException {
        Item item = itemRepository.findById(Long.valueOf(loanTransactionDTO.getItemId()))
            .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + loanTransactionDTO.getItemId()));
        Employee employee = employeeRepository.findById(Long.valueOf(loanTransactionDTO.getEmployeeId()))
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + loanTransactionDTO.getEmployeeId()));
        LoanCard loanCard = new LoanCard(item.getItemCategory(), loanTransactionDTO.getDuration());
        // loanCardService.registerLoanCard(loanCard);
        LoanTransaction loanTransaction = new LoanTransaction(loanTransactionDTO.getTransactionId(),
            loanTransactionDTO.getTimestamp(), loanTransactionDTO.getAmount(), loanCard, employee, item);
        LoanTransaction savedLoanTransaction =  loanTransactionRepository.save(loanTransaction);
        return ResponseEntity.status(HttpStatus.OK).body(savedLoanTransaction);
    }
    @Override
    public ResponseEntity<LoanTransaction> registerLoanTransaction(@Valid @RequestBody LoanTransactionDTO loanTransactionDTO, Boolean loanOrNot)
     throws ResourceNotFoundException {
        // Item item = itemRepository.findById(Long.valueOf(loanTransactionDTO.getItemId()))
        //     .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + loanTransactionDTO.getItemId()));
        Employee employee = employeeRepository.findById(Long.valueOf(loanTransactionDTO.getEmployeeId()))
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this username :: " + loanTransactionDTO.getEmployeeId()));
        LoanCard loanCard = new LoanCard(loanTransactionDTO.getCategory(), loanTransactionDTO.getDuration());
        // loanCardService.registerLoanCard(loanCard);
        LoanTransaction loanTransaction = new LoanTransaction(loanTransactionDTO.getTransactionId(),
            loanTransactionDTO.getTimestamp(), loanTransactionDTO.getAmount(), loanCard, employee, null);
        LoanTransaction savedLoanTransaction =  loanTransactionRepository.save(loanTransaction);
        return ResponseEntity.status(HttpStatus.OK).body(savedLoanTransaction);
    }
}




