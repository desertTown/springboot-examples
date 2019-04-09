package com.evan.pojo;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "SWAGGER_USER")
@Data
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;

}
