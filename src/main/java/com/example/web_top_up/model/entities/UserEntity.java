package com.example.web_top_up.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "first_name")
    public String firstName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private DepartmentEntity department;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    private Set<IdeaEntity>  ideaEntities;

    @Column(name = "role")
    public String role;


}
