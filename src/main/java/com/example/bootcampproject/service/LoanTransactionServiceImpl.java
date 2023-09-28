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
    public List < LoanTransactionDTO > getAllLoanTransaction() {
        List <LoanTransactionDTO> loanList = new ArrayList();
        List <LoanTransaction> ltrans =  loanTransactionRepository.findAll();
        LoanTransactionDTO loanDto= new LoanTransactionDTO();
        for( LoanTransaction lt :ltrans){
            if(lt.getItem() == null){
                 loanDto = new LoanTransactionDTO(lt.getStatus(), false, lt.getLoanCard().getLoanType(),lt.getTransactionId(), 
            lt.getTimestamp(), lt.getAmount(), lt.getLoanCard().getLoanId(), 
            lt.getEmployee().getEmployeeId(), null , lt.getLoanCard().getDuration());
            }else{
                 loanDto = new LoanTransactionDTO(lt.getStatus(), true, lt.getLoanCard().getLoanType(),lt.getTransactionId(), 
            lt.getTimestamp(), lt.getAmount(), lt.getLoanCard().getLoanId(), 
            lt.getEmployee().getEmployeeId(),lt.getItem().getItemId() , lt.getLoanCard().getDuration());
            }
            
            loanList.add(loanDto);
        }
        return loanList;
    }

    @Override
    public ResponseEntity < List<LoanTransactionDTO> > updateLoanTransaction(@PathVariable(value = "id") Long loanTransactionId,
        @Valid @RequestBody LoanTransactionDTO loanTransaction) throws ResourceNotFoundException{
        LoanTransaction loanTransactionNew = loanTransactionRepository.findById(loanTransactionId)
            .orElseThrow(() -> new ResourceNotFoundException("Loan not found for this id :: " + loanTransactionId));

        loanTransactionNew.setStatus(loanTransaction.getStatus());
        final LoanTransaction updatedTransaction = loanTransactionRepository.save(loanTransactionNew);
        loanTransaction.setStatus(updatedTransaction.getStatus());
        List<LoanTransactionDTO> listObj = getAllLoanTransaction();
        return ResponseEntity.ok(listObj);
    }

    @Override
    public ResponseEntity<List < LoanTransactionDTO >> getLoanTransactionByEmployeeId(@PathVariable(value = "id") Integer employeeId) {
        List <LoanTransaction> ltrans = loanTransactionRepository.findByEmployee_EmployeeId(employeeId);
        List <LoanTransactionDTO> loanList = new ArrayList();
        LoanTransactionDTO loanDto= new LoanTransactionDTO();
        for( LoanTransaction lt :ltrans){
            if(lt.getItem() == null){
                 loanDto = new LoanTransactionDTO(lt.getStatus(),false, lt.getLoanCard().getLoanType(),lt.getTransactionId(), 
            lt.getTimestamp(), lt.getAmount(), lt.getLoanCard().getLoanId(), 
            lt.getEmployee().getEmployeeId(), null , lt.getLoanCard().getDuration());
            }else{
                 loanDto = new LoanTransactionDTO(lt.getStatus(), true , lt.getLoanCard().getLoanType(),lt.getTransactionId(), 
            lt.getTimestamp(), lt.getAmount(), lt.getLoanCard().getLoanId(), 
            lt.getEmployee().getEmployeeId(),lt.getItem().getItemId() , lt.getLoanCard().getDuration());
            }
            
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
        loanTransactionDTO.setStatus("PENDING");
        Item item = itemRepository.findById(Long.valueOf(loanTransactionDTO.getItemId()))
            .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + loanTransactionDTO.getItemId()));
        Employee employee = employeeRepository.findById(Long.valueOf(loanTransactionDTO.getEmployeeId()))
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + loanTransactionDTO.getEmployeeId()));
        LoanCard loanCard = new LoanCard(item.getItemCategory(), loanTransactionDTO.getDuration());
        // loanCardService.registerLoanCard(loanCard);
        LoanTransaction loanTransaction = new LoanTransaction(loanTransactionDTO.getStatus(),loanTransactionDTO.getTransactionId(),
            loanTransactionDTO.getTimestamp(), loanTransactionDTO.getAmount(), loanCard, employee, item);
        LoanTransaction savedLoanTransaction =  loanTransactionRepository.save(loanTransaction);
        return ResponseEntity.status(HttpStatus.OK).body(savedLoanTransaction);
    }
    @Override
    public ResponseEntity<LoanTransaction> registerLoanTransaction(@Valid @RequestBody LoanTransactionDTO loanTransactionDTO, Boolean loanOrNot)
     throws ResourceNotFoundException {
        loanTransactionDTO.setStatus("PENDING");
        Employee employee = employeeRepository.findById(Long.valueOf(loanTransactionDTO.getEmployeeId()))
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this username :: " + loanTransactionDTO.getEmployeeId()));
        LoanCard loanCard = new LoanCard(loanTransactionDTO.getCategory(), loanTransactionDTO.getDuration());
        // loanCardService.registerLoanCard(loanCard);
        LoanTransaction loanTransaction = new LoanTransaction(loanTransactionDTO.getStatus(),loanTransactionDTO.getTransactionId(),
            loanTransactionDTO.getTimestamp(), loanTransactionDTO.getAmount(), loanCard, employee, null);
        LoanTransaction savedLoanTransaction =  loanTransactionRepository.save(loanTransaction);
        employee.setBalance(employee.getBalance()+loanTransactionDTO.getAmount());
        System.out.println(loanTransactionDTO.getAmount()+employee.getBalance());
        return ResponseEntity.status(HttpStatus.OK).body(savedLoanTransaction);
    }
}




