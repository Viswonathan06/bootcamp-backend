package com.example.bootcampproject.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
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

import com.example.bootcampproject.dto.ItemDTO;
import com.example.bootcampproject.entity.Employee;
import com.example.bootcampproject.entity.EmployeeCardDetails;
import com.example.bootcampproject.entity.EmployeeIssue;
import com.example.bootcampproject.entity.Item;
import com.example.bootcampproject.entity.LoanCard;
import com.example.bootcampproject.entity.LoanTransaction;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;
import com.example.bootcampproject.repository.EmployeeRepository;
import com.example.bootcampproject.repository.ItemRepository;
import com.example.bootcampproject.service.EmployeeServiceImpl;
import com.example.bootcampproject.service.ItemServiceImpl;



@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ItemTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private ItemRepository itemRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;
    @InjectMocks
    private ItemServiceImpl itemServiceImpl;
    private LoanTransaction loanTransaction;
    private EmployeeIssue employeeIssue;
    private EmployeeCardDetails employeeCardDetails;
    private LoanCard loanCard;
    private Item item;
    private  Employee employee;
    
    @BeforeEach void setUpItem(){
        
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
        itemServiceImpl.getAllItem();
        verify(itemRepository).findAll();
    }

    @Test
    public void givenItemId_thenReturnItemObj(){
        given(itemRepository.findById(123L)).willReturn(Optional.of(item));

        // when
        try{
            Item savedItem = itemServiceImpl.getItemById(Long.valueOf(item.getItemId())).getBody();
            assertThat(savedItem).isNotNull();
        }catch(ResourceNotFoundException e){
            System.out.println(e);
        }

    }

    @Test
    public void givenItemObject_whenUpdateItem_thenReturnUpdatedItem(){
        // given - precondition or setup
        given(itemRepository.save(item)).willReturn(item);
        item.setIssueStatus("COMPLETE");
        item.setItemCategory("FASHION");
        // when -  action or the behaviour that we are going test
        
        try{
            List<ItemDTO> updatedItemDTOs = itemServiceImpl.updateItem(Long.valueOf(item.getItemId()), item).getBody();
            ItemDTO updatedItem = new ItemDTO();
            for(ItemDTO temp : updatedItemDTOs){
                if(temp.getItemId() == item.getItemId()){
                    updatedItem = temp;
                }
            }
             
            // then - verify the output
            assertThat(updatedItem.getIssueStatus()).isEqualTo("COMPLETE"); 
            assertThat(updatedItem.getItemCategory()).isEqualTo("FASHION"); 

        }catch(ResourceNotFoundException E){
            System.out.println(E);
        }
    }


    @Test
    public void givenItemObject_whenSaveItem_thenReturnItemObject(){
        // given - precondition or setup
        given(itemRepository.findById(Long.valueOf(item.getItemId())))
                .willReturn(Optional.empty());

        
        System.out.println(itemRepository);
        System.out.println(itemServiceImpl);
        // when -  action or the behaviour that we are going test
        try{
            String responseString = itemServiceImpl.registerItem(item).getBody();
            // System.out.println(savedItem);
            assertThat(responseString).isEqualTo("Item Registered!");
            
        }catch(ResourceNotFoundException e){
            System.out.print(e);
        }

        // then - verify the output
    }

    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenNothing(){
        // given - precondition or setup
        long itemId = 1L;
        
        willDoNothing().given(itemRepository).deleteById(itemId);

        // when -  action or the behaviour that we are going test
        try{
            
            itemServiceImpl.deleteItem(itemId);
        }catch(ResourceNotFoundException e){
            System.out.println(e);
        }

        // then - verify the output
        verify(itemRepository, times(1)).deleteById(itemId);
    }


    
}
