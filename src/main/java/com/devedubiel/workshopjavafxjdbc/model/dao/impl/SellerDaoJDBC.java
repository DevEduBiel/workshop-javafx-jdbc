package com.devedubiel.workshopjavafxjdbc.model.dao.impl;



import com.devedubiel.workshopjavafxjdbc.database.DB;
import com.devedubiel.workshopjavafxjdbc.database.DbException;
import com.devedubiel.workshopjavafxjdbc.model.dao.SellerDao;
import com.devedubiel.workshopjavafxjdbc.model.entities.Department;
import com.devedubiel.workshopjavafxjdbc.model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller object) {
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement("INSERT INTO seller\n" +
                    "(Name, Email, BirthDate, BaseSalary, DepartmentId)\n" +
                    "VALUES\n" +
                    "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, object.getName());
            pstm.setString(2, object.getEmail());
            pstm.setDate(3, new Date(object.getBirthDate().getTime()));
            pstm.setDouble(4, object.getBaseSalary());
            pstm.setInt(5, object.getDepartment().getId());

            int rows = pstm.executeUpdate();
            if (rows > 0) {
                ResultSet rs = pstm.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    object.setId(id);
                }
                DB.closeStatement(pstm);
            } else {
                throw new DbException("Unexpected error: no rows affected");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pstm);
        }
    }

    @Override
    public void update(Seller object) {
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement("UPDATE seller\n" +
                    "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ?\n" +
                    "WHERE Id = ?", Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, object.getName());
            pstm.setString(2, object.getEmail());
            pstm.setDate(3, new Date(object.getBirthDate().getTime()));
            pstm.setDouble(4, object.getBaseSalary());
            pstm.setInt(5, object.getDepartment().getId());
            pstm.setInt(6, object.getId());

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pstm);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement("DELETE FROM seller\n" +
                    "WHERE Id = ?");

            pstm.setInt(1, id);

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pstm);
        }
    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement("SELECT seller.*,department.Name as DepName\n" +
                    "FROM seller INNER JOIN department\n" +
                    "ON seller.DepartmentId = department.Id\n" +
                    "WHERE seller.Id = ?");
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            if (rs.next()) {
                Department dep = instantiateDepartment(rs);

                Seller obj = instantiateSeller(rs, dep);
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pstm);
        }

    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setDepartment(dep);
        return obj;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement("SELECT seller.*,department.Name as DepName\n" +
                    "FROM seller INNER JOIN department\n" +
                    "ON seller.DepartmentId = department.Id\n" +
                    "ORDER BY Name");
            rs = pstm.executeQuery();

            List<Seller> list = new ArrayList<Seller>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {

                Department dep = map.get(rs.getInt("DepartmentID"));

                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentID"), dep);
                }

                Seller obj = instantiateSeller(rs, dep);
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pstm);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement("SELECT seller.*,department.Name as DepName\n" +
                    "FROM seller INNER JOIN department\n" +
                    "ON seller.DepartmentId = department.Id\n" +
                    "WHERE DepartmentId = ?\n" +
                    "ORDER BY Name");
            pstm.setInt(1, department.getId());
            rs = pstm.executeQuery();

            List<Seller> list = new ArrayList<Seller>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {

                Department dep = map.get(rs.getInt("DepartmentID"));

                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentID"), dep);
                }

                Seller obj = instantiateSeller(rs, dep);
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pstm);
        }

    }

}
