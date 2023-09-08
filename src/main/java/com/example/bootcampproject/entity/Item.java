package com.example.bootcampproject.entity;

import jakarta.persistence.*;
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
    private String itemDescription;
    @Column(nullable = false)
    private String issueStatus;
    @Column(nullable = false)
    private String itemMake;
    private String itemCategory;
    @Column(nullable = false)
    private Integer itemValuation;

}
