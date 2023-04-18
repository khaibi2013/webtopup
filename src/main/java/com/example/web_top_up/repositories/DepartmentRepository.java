package com.example.web_top_up.repositories;

import com.example.web_top_up.model.DepartmentData;
import com.example.web_top_up.model.IdeaData;
import com.example.web_top_up.model.entities.DepartmentEntity;
import com.example.web_top_up.model.entities.IdeaEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {



    @Query(value = "SELECT * FROM department i where i.category_id = :categoryId", nativeQuery = true)
    DepartmentEntity testcodeDepartment(long categoryId);

    @Query(value = "select count(d.id) as countDepartment from department d join users u on u.department_id = d.id" +
            " join idea_table i on i.user_id = u.id where d.id = :id", nativeQuery = true)
    DepartmentData countDepartmentEntitiesById(Long id);

    @Query(value = "select i.title as title, i.id as id, i.create_at as createAt  from department d " +
            "join users u on u.department_id = d.id" +
            " join idea_table i on i.user_id = u.id where d.id = :id", nativeQuery = true)
    List<IdeaData> findByUserAndIdea(Long id);

    @Modifying
    @Query(value = "update department d set d.id = :id, d.name_department = :address  where d.id = :id", nativeQuery = true)
    int updateIsActive(Long id, String address);



}
