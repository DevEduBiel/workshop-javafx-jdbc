package com.devedubiel.workshopjavafxjdbc.model.dao;


import com.devedubiel.workshopjavafxjdbc.database.DB;
import com.devedubiel.workshopjavafxjdbc.model.dao.impl.DepartmentDaoJDBC;
import com.devedubiel.workshopjavafxjdbc.model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }
    public static DepartmentDao createDepartmentDao() {
        return new DepartmentDaoJDBC(DB.getConnection());
    }
}
