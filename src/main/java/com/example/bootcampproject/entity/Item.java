package com.example.bootcampproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer itemId;

    @OneToOne(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private EmployeeIssue employeeIssue;
    @NotBlank(message="Desc missing")
    private String itemDescription;
    @Column(nullable = false)
    @NotBlank(message="Status pending")
    private String issueStatus;
    @Column(nullable = false)
    @NotBlank(message="Item make cannot be blank")
    private String itemMake;
    @NotBlank(message="Item category cannot be blank")
    private String itemCategory;
    @Column(nullable = false)
    private Integer itemValuation;
    
    
}
