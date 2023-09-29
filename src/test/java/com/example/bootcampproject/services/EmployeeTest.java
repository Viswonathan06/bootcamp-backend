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

import com.example.bootcampproject.dto.EmployeeDTO;
import com.example.bootcampproject.entity.AdminCredentials;
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
public class EmployeeTest {

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
        employeeServiceImpl.getAllEmployee();
        verify(employeeRepository).findAll();
    }

    @Test
    public void givenemployeeId_thenReturnemployeeObj(){
        given(employeeRepository.findById(123L)).willReturn(Optional.of(employee));

        // when
        try{
            Employee savedLoan = employeeServiceImpl.getEmployeeById(Long.valueOf(employee.getEmployeeId())).getBody();
            assertThat(savedLoan).isNotNull();
        }catch(ResourceNotFoundException e){
            System.out.println(e);
        }

    }

    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        // given - precondition or setup
        given(employeeRepository.save(employee)).willReturn(employee);
        employee.setEmailId("ram@gmail.com");
        employee.setGender("Male");
        // when -  action or the behaviour that we are going test
        
        try{
            List<EmployeeDTO> updatedemployees= employeeServiceImpl.updateEmployee(Long.valueOf(employee.getEmployeeId()), employee).getBody();
            EmployeeDTO updatedemployee = new EmployeeDTO();
            for(EmployeeDTO temp : updatedemployees){
                if(temp.getEmployeeId() == employee.getEmployeeId()){
                    updatedemployee = temp;
                }
            }
             
            // then - verify the output
            assertThat(updatedemployee.getEmailId()).isEqualTo("ram@gmail.com"); 
            assertThat(updatedemployee.getGender()).isEqualTo("Male"); 

        }catch(ResourceNotFoundException E){
            System.out.println(E);
        }
    }


    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnNull(){
        // given - precondition or setup
        given(employeeRepository.findById(Long.valueOf(employee.getEmployeeId())))
                .willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        try{
            Employee savedemployee = employeeServiceImpl.registerEmployee(employee).getBody();
            assertThat(savedemployee).isNull();
            
        }catch(ResourceNotFoundException e){
            System.out.print(e);
        }

        // then - verify the output
    }

    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenNothing(){
        // given - precondition or setup
        long employeeId = 1L;
        
        willDoNothing().given(employeeRepository).deleteById(employeeId);

        // when -  action or the behaviour that we are going test
        try{
            
            employeeServiceImpl.deleteEmployee(employeeId);
        }catch(ResourceNotFoundException e){
            System.out.println(e);
        }

        // then - verify the output
        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

    @Test
    public void givenEmployeeObject_whenLogin_thenReturnUpdatedEmployeObject(){
        // given - precondition or setup
        given(employeeRepository.save(employee)).willReturn(employee);
        
        // when -  action or the behaviour that we are going test
        
        try{
            EmployeeDTO updatedemployee= employeeServiceImpl.verifyEmployee(employee).getBody();
            // then - verify the output
            assertThat(updatedemployee.getRole()).isEqualTo("EMPLOYEE"); 
        }catch(ResourceNotFoundException E){
            System.out.println(E);
        }
    }

    
    @Test
    public void givenAdminCredentialsObject_whenUnauthorized_thenReturnRoleUnauthorized(){
        // given - precondition or setup
        given(employeeRepository.save(employee)).willReturn(employee);
        employee.setPassword("182736");
        // when -  action or the behaviour that we are going test
        
        try{
            EmployeeDTO updatedemployee= employeeServiceImpl.verifyEmployee(employee).getBody();
            // then - verify the output
            assertThat(updatedemployee.getRole()).isEqualTo("UNAUTHORIZED"); 
        }catch(ResourceNotFoundException E){
            System.out.println(E);
        }
    }


    
}
