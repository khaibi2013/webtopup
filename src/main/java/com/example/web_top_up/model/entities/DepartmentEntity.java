package com.example.web_top_up.model.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "department")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_department")
    private String nameDepartment;

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    private Set<UserEntity> userEntities;

}
