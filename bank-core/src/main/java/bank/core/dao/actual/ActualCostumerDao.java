/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.core.dao.actual;

import bank.core.actual.ActualCustomer;
import bank.core.dao.CostumerDao;
import bank.core.exception.DataAccessException;
import bank.core.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import bank.core.Customer;

/**
 *
 * @author Przemek DELL
 */
public class ActualCostumerDao implements CostumerDao {

    @Override
    public void save(Customer costumer) {

        final String SQL = "insert into zawadzki.customer values (DEFAULT,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setEscapeProcessing(true);
            
            statement.setString(1, costumer.getFirstName());
            statement.setString(2, costumer.getLastName());
            statement.setString(3, costumer.getPesel());
            statement.setString(4, costumer.getAddress());
            statement.setString(5, costumer.getEmail());
            
            statement.executeUpdate();
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                costumer.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new DataAccessException(ex);
            }
        }
    }

    @Override
    public void remove(Customer customer) {
        final String SQL = "delete from zawadzki.customer where id=?";

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setEscapeProcessing(true);
            
            statement.setInt(1, customer.getId());
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new DataAccessException(ex);
            }
        }
    }

    @Override
    public void update(Customer costumer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer findById(Integer id) {
        final String SQL = "select * from zawadzki.customer where id = ?";
        Customer result = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(SQL);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                result = new ActualCustomer();
                result.setId(rs.getInt("id"));
                result.setFirstName(rs.getString("firstName"));
                result.setLastName(rs.getString("lastName"));
                result.setAddress(rs.getString("address"));
                result.setPesel(rs.getString("pesel"));
                result.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new DataAccessException(ex);
            }
        }
        return result;
    }
    
    @Override
    public Customer findByPesel(String pesel) {
        final String SQL = "select * from zawadzki.customer where pesel = ?";
        Customer result = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(SQL);
            statement.setString(1, pesel);
            rs = statement.executeQuery();
            if (rs.next()) {
                result = new ActualCustomer();
                result.setId(rs.getInt("id"));
                result.setFirstName(rs.getString("firstName"));
                result.setLastName(rs.getString("lastName"));
                result.setAddress(rs.getString("address"));
                result.setPesel(rs.getString("pesel"));
                result.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new DataAccessException(ex);
            }
        }
        return result;
    }
    @Override
    public List<Customer> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
