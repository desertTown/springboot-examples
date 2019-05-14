package com.evan.model;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "person")
@Data
@ToString
public class Person {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "p_id", unique = true, nullable = false)
    private Integer pId;

    @Column(name = "p_name", length = 45)
    private String pName;

    @Column(name = "p_age")
    private Integer pAge;

}
