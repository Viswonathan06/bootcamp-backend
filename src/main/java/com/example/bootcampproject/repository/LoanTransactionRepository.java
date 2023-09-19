
package com.example.bootcampproject.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bootcampproject.entity.Employee;
import com.example.bootcampproject.entity.LoanTransaction;

@Repository
public interface LoanTransactionRepository extends JpaRepository<LoanTransaction, Long>{
    List<LoanTransaction> findByEmployee_EmployeeId(Integer employeeId);
    
}
