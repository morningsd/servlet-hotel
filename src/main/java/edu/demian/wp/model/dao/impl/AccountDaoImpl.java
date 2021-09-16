package edu.demian.wp.model.dao.impl;

import edu.demian.wp.model.dao.AccountDao;
import edu.demian.wp.model.entity.Account;
import edu.demian.wp.model.exception.DaoException;
import edu.demian.wp.model.util.DBManager;

import javax.sql.DataSource;
import java.sql.*;

public class AccountDaoImpl implements AccountDao {
    private final DataSource DATASOURCE = DBManager.getInstance();

    private static final String SQL_ACCOUNT_ID = "account_id";
    private static final String SQL_ACCOUNT_FNAME = "fname";
    private static final String SQL_ACCOUNT_LNAME = "lname";
    private static final String SQL_ACCOUNT_EMAIL = "email";
    private static final String SQL_ACCOUNT_ROLE_ID = "account_role";

    private static final String SQL_SELECT_ACCOUNT_BY_ID = "SELECT * FROM account WHERE account_id=?";
    private static final String SQL_SELECT_ACCOUNT_BY_EMAIL = "SELECT * FROM account WHERE email=?";
    private static final String SQL_SELECT_ACCOUNT_BY_EMAIL_AND_PASSWORD = "SELECT * FROM account WHERE email=?" +
            " AND passwordSha=?";
    private static final String SQL_INSERT_ACCOUNT = "INSERT INTO account (" +
            "fname, lname, email, passwordSha, account_role) VALUES (?,?,?,?,?)";


    @Override
    public Account find(long id) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_ACCOUNT_BY_ID)) {
                pstmt.setLong(1, id);
                return toAccount(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find account", e);
        }
    }

    @Override
    public Account findByEmail(String email) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_ACCOUNT_BY_EMAIL)) {
                pstmt.setString(1, email);
                return toAccount(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find account", e);
        }
    }

    @Override
    public Account findByEmailAndPassword(String email, String passwordSha) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_ACCOUNT_BY_EMAIL_AND_PASSWORD)) {
                pstmt.setString(1, email);
                pstmt.setString(2, passwordSha);
                return toAccount(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find account", e);
        }
    }


    @Override
    public void save(Account account, String passwordSha) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt =
                         connection.prepareStatement(SQL_INSERT_ACCOUNT, Statement.RETURN_GENERATED_KEYS)) {
                int k = 1;
                pstmt.setString(k++, account.getFirstName());
                pstmt.setString(k++, account.getLastName());
                pstmt.setString(k++, account.getEmail());
                pstmt.setString(k++, passwordSha);
                pstmt.setInt(k, account.getRoleId());
                if (pstmt.executeUpdate() == 1) {
                    ResultSet rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        long id = rs.getLong(1);
                        account.setId(id);
                    } else {
                        throw new DaoException("Can't save account");
                    }
                } else {
                    throw new DaoException("Can't save account");
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can't save account", e);
        }
    }


    private Account toAccount(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        Account account = new Account();

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case SQL_ACCOUNT_ID:
                    account.setId(resultSet.getLong(i));
                    break;
                case SQL_ACCOUNT_FNAME:
                    account.setFirstName(resultSet.getString(i));
                    break;
                case SQL_ACCOUNT_LNAME:
                    account.setLastName(resultSet.getString(i));
                    break;
                case SQL_ACCOUNT_EMAIL:
                    account.setEmail(resultSet.getString(i));
                    break;
                case SQL_ACCOUNT_ROLE_ID:
                    account.setRoleId(resultSet.getInt(i));
                    break;
                default:
                    // No operations
            }
        }
        return account;
    }
}
