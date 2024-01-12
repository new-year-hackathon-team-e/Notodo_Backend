package com.example.notodo_backend.domain.teamTodo.repository;


import com.example.notodo_backend.domain.teamTodo.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

}
