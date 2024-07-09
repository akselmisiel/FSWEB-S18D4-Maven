package com.workintech.s18d1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "burger", schema = "public")
public class Burger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Double price;
    @Column(name = "is_vegan")
    private boolean isVegan;
    @Enumerated(EnumType.STRING)
    @Column(name = "bread_type")
    private BreadType breadType;
    @Column(name = "contents")
    private String contents;


}
