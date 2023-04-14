package com.devedubiel.workshopjavafxjdbc.model.dao;

import com.devedubiel.workshopjavafxjdbc.model.entities.Department;
import com.devedubiel.workshopjavafxjdbc.model.entities.Seller;

import java.util.List;

public interface SellerDao {
    void insert(Seller object);
    void update(Seller object);
    void deleteById(Integer id);
    Seller findById(Integer id);
    List<Seller> findAll();
    List<Seller> findByDepartment(Department department);
}
