/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.core.dao.actual;

import bank.core.Account;
import bank.core.actual.ActualCustomer;
import bank.core.exception.DataAccessException;
import bank.core.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import bank.core.Customer;
import bank.core.actual.ActualAccount;
import java.util.ArrayList;
import bank.core.dao.DataAccess;

/**
 *
 * @author Przemek DELL
 */
public class ActualDataAccess implements DataAccess {

    @Override
    public void saveCustomer(Customer customer) {

        final String SQL = "insert into zawadzki.customer values (DEFAULT,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setEscapeProcessing(true);

            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getPesel());
            statement.setString(4, customer.getAddress());
            statement.setString(5, customer.getEmail());

            statement.executeUpdate();
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                customer.setId(rs.getInt(1));
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
    public void removeCustomer(Customer customer) {
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
    public void updateCustomer(Customer customer) {
        final String SQL = "update zawadzki.customer "
                + "set FIRSTNAME=?, LASTNAME=?, PESEL=?, ADDRESS=?, EMAIL=?"
                + "where id=?";

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setEscapeProcessing(true);
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getPesel());
            statement.setString(4, customer.getAddress());
            statement.setString(5, customer.getEmail());
            statement.setInt(6, customer.getId());
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
    public Customer findCustomer(Integer id) {
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
    public Customer findCustomer(String pesel) {
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
    public List<Customer> findAllCustomers() {
        final String SQL = "select * from zawadzki.customer";
        List<Customer> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(SQL);
            rs = statement.executeQuery();
            while (rs.next()) {
                resultList.add(new ActualCustomer());
                resultList.get(resultList.size()).setId(rs.getInt("id"));
                resultList.get(resultList.size()).setFirstName(rs.getString("firstName"));
                resultList.get(resultList.size()).setLastName(rs.getString("lastName"));
                resultList.get(resultList.size()).setAddress(rs.getString("address"));
                resultList.get(resultList.size()).setPesel(rs.getString("pesel"));
                resultList.get(resultList.size()).setEmail(rs.getString("email"));
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
        return resultList;
    }

    @Override
    public void saveAccount(Account account) {
        final String SQL = "insert into zawadzki.account values (DEFAULT,?,0.0,?)";

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setEscapeProcessing(true);

            statement.setString(1, account.getNotes());
            statement.setInt(2, account.getCustomerId());

            statement.executeUpdate();
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                account.setId(rs.getInt(1));
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
    public void removeAccount(Account account) {
        final String SQL = "delete from zawadzki.account where id=?";

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setEscapeProcessing(true);
            statement.setInt(1, account.getId());
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
    public void updateAccount(Account account) {
        final String SQL = "update zawadzki.account "
                + "set NOTES = ?, BALANCE =?, ID_CUSTOMER=?"
                + "where id=?";

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setEscapeProcessing(true);
            statement.setString(1, account.getNotes());
            statement.setDouble(2, account.getBalance());
            statement.setInt(3, account.getCustomerId());
            statement.setInt(4, account.getId());
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
    public Account findAccount(Integer id) {
        final String SQL = "select * from zawadzki.customer where id = ?";
        Account result = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(SQL);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                result = new ActualAccount();
                result.setId(rs.getInt("id"));
                result.setNotes(rs.getString("notes"));
                result.setBalance(rs.getDouble("balance"));
                result.setCustomerId(rs.getInt("id_customer"));
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
    public List<Account> findAllCustomerAccounts(Customer customer) {
        final String SQL = "select * from zawadzki.account where id_customer = ?";
        List<Account> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;

        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(SQL);
            statement.setInt(1, customer.getId());
            rs = statement.executeQuery();
            while (rs.next()) {
                resultList.add(new ActualAccount());
                resultList.get(resultList.size()).setId(rs.getInt("id"));
                resultList.get(resultList.size()).setNotes(rs.getString("notes"));
                resultList.get(resultList.size()).setBalance(rs.getDouble("balance"));
                resultList.get(resultList.size()).setCustomerId(rs.getInt("id_customer"));
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
        return resultList;
    }

    @Override
    public List<Account> findAllAccounts() {
        final String SQL = "select * from zawadzki.account";
        List<Account> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;

        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(SQL);
            rs = statement.executeQuery();
            while (rs.next()) {
                resultList.add(new ActualAccount());
                resultList.get(resultList.size()).setId(rs.getInt("id"));
                resultList.get(resultList.size()).setNotes(rs.getString("notes"));
                resultList.get(resultList.size()).setBalance(rs.getDouble("balance"));
                resultList.get(resultList.size()).setCustomerId(rs.getInt("id_customer"));
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
        return resultList;
    }

}
