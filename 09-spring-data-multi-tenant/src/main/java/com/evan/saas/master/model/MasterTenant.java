package com.evan.saas.master.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "MASTER_TENANT")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterTenant implements Serializable{

    @Id
    @Column(name="ID")
    private String id;

    @Column(name = "TENANT")
    @NotEmpty(message = "Tenant identifier must be provided")
    private String tenant;

    @Column(name = "URL")
    @Size(max = 256)
    @NotEmpty(message = "Tenant jdbc url must be provided")
    private String url;

    @Column(name = "USERNAME")
    @Size(min = 4,max = 30,message = "db username length must between 4 and 30")
    @NotEmpty(message = "Tenant db username must be provided")
    private String username;

    @Column(name = "PASSWORD")
    @Size(min = 4,max = 30)
    @NotEmpty(message = "Tenant db password must be provided")
    private String password;

    @Version
    private int version = 0;
}
