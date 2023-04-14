package com.devedubiel.workshopjavafxjdbc.model.dao;



import com.devedubiel.workshopjavafxjdbc.model.entities.Department;

import java.util.List;

public interface DepartmentDao {

    void insert(Department object);
    void update(Department object);
    void deleteById(Integer id);
    Department findById(Integer id);
    List<Department> findAll();

}
