package com.example.bootcampproject.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.example.bootcampproject.entity.AdminCredentials;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;
import com.example.bootcampproject.repository.AdminRepository;
import com.example.bootcampproject.repository.ItemRepository;
import com.example.bootcampproject.service.AdminServiceImpl;
import com.example.bootcampproject.service.ItemServiceImpl;



@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AdminTest {

    @Mock
    private AdminRepository adminRepository;
    @Mock
    private ItemRepository itemRepository;
    @InjectMocks
    private AdminServiceImpl adminServiceImpl;
    @InjectMocks
    private ItemServiceImpl itemServiceImpl;
    private  AdminCredentials admin;
    
    @BeforeEach void setUpAdmin(){
        
        admin = new AdminCredentials(
            1,
            "U143245",
            "123456",
            "ab123c@gmail.com");
        System.out.println("Initialized!");
    }

    @Test void getAllLoan(){
        adminServiceImpl.getAllAdminCredentials();
        verify(adminRepository).findAll();
    }

    @Test
    public void givenadminId_thenReturnadminObj(){
        given(adminRepository.findById(123L)).willReturn(Optional.of(admin));

        // when
        try{
            AdminCredentials savedAdmin = adminServiceImpl.getAdminCredentialsById(Long.valueOf(admin.getId())).getBody();
            assertThat(savedAdmin).isNotNull();
        }catch(ResourceNotFoundException e){
            System.out.println(e);
        }

    }

    @Test
    public void givenAdminCredentialsObject_whenUpdateAdminCredentials_thenReturnUpdatedAdminCredentials(){
        // given - precondition or setup
        given(adminRepository.save(admin)).willReturn(admin);
        admin.setEmailId("ram@gmail.com");
        admin.setPassword("12345678");
        // when -  action or the behaviour that we are going test
        
        try{
            AdminCredentials updatedadmin= adminServiceImpl.updateAdmin(Long.valueOf(admin.getId()), admin).getBody();
            // then - verify the output
            assertThat(updatedadmin.getEmailId()).isEqualTo("ram@gmail.com"); 
            assertThat(updatedadmin.getPassword()).isEqualTo("12345678"); 

        }catch(ResourceNotFoundException E){
            System.out.println(E);
        }
    }


    @Test
    public void givenAdminCredentialsObject_whenSaveAdminCredentials_thenReturnNull(){
        // given - precondition or setup
        given(adminRepository.findById(Long.valueOf(admin.getId())))
                .willReturn(Optional.empty());
        // when -  action or the behaviour that we are going test
        try{
            AdminCredentials savedadmin = adminServiceImpl.registerAdminCredentials(admin).getBody();
            assertThat(savedadmin).isNull();
            
        }catch(ResourceNotFoundException e){
            System.out.print(e);
        }

        // then - verify the output
    }

    @Test
    public void givenAdminCredentialsId_whenDeleteAdminCredentials_thenNothing(){
        // given - precondition or setup
        long adminId = 1L;
        
        willDoNothing().given(adminRepository).deleteById(adminId);

        // when -  action or the behaviour that we are going test
        try{
            
            adminServiceImpl.deleteEmployee(adminId);
        }catch(ResourceNotFoundException e){
            System.out.println(e);
        }

        // then - verify the output
        verify(adminRepository, times(1)).deleteById(adminId);
    }

    @Test
    public void givenAdminCredentialsObject_whenLogin_thenReturnUpdatedAdminCredentials(){
        // given - precondition or setup
        given(adminRepository.save(admin)).willReturn(admin);
        
        // when -  action or the behaviour that we are going test
        
        try{
            AdminCredentials updatedadmin= adminServiceImpl.verifyAdminCredentials(admin).getBody();
            // then - verify the output
            assertThat(updatedadmin.getRole()).isEqualTo("ADMIN"); 
        }catch(ResourceNotFoundException E){
            System.out.println(E);
        }
    }

    
    @Test
    public void givenAdminCredentialsObject_whenUnauthorized_thenReturnRoleUnauthorized(){
        // given - precondition or setup
        given(adminRepository.save(admin)).willReturn(admin);
        admin.setPassword("182736");
        // when -  action or the behaviour that we are going test
        
        try{
            AdminCredentials updatedadmin= adminServiceImpl.verifyAdminCredentials(admin).getBody();
            // then - verify the output
            assertThat(updatedadmin.getRole()).isEqualTo("UNAUTHORIZED"); 
        }catch(ResourceNotFoundException E){
            System.out.println(E);
        }
    }

    
}
