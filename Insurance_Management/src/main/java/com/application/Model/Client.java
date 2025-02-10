package com.application.Model;

import java.sql.Date;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    @SequenceGenerator(name = "client_seq", sequenceName = "client_seq", allocationSize = 1)
    private Integer clientId;

    @Length(min=5, max=10)
    private String name;

    @Past
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dob;

    @Length(min = 5, max = 20)
    private String address;

    @Length(min=10, max=10)
    private String phone;

    @Length(min=8) 
    private String password;
}