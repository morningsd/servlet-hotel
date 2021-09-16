package edu.demian.wp.model.dao.impl;

import edu.demian.wp.model.dao.BillDao;
import edu.demian.wp.model.entity.Bill;
import edu.demian.wp.model.exception.DaoException;
import edu.demian.wp.model.util.DBManager;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl implements BillDao {
    private final DataSource DATASOURCE = DBManager.getInstance();

    private static final String SQL_BILL_ID = "bill_id";
    private static final String SQL_BILL_CHECK_IN = "check_in";
    private static final String SQL_BILL_CHECK_OUT = "check_out";
    private static final String SQL_BILL_TOTAL = "total";
    private static final String SQL_BILL_ACCOUNT_ID = "bill_account";
    private static final String SQL_BILL_APARTMENTS_ID = "bill_apartments";
    private static final String SQL_BILL_STATE_ID = "bill_state";

    private static final String SQL_INSERT_BILL = "INSERT INTO bill " +
            "(check_in, check_out, total, bill_account, bill_apartments, bill_state) VALUES " +
            "(?,?,?,?,?,?)";
    private static final String SQL_SELECT_BILL_BY_ID = "SELECT * FROM bill WHERE bill_id=?";
    private static final String SQL_SELECT_ALL_BILLS = "SELECT * FROM bill";
    private static final String SQL_SELECT_ACCOUNT_BILLS = "SELECT * FROM bill WHERE bill_account=?";
    private static final String SQL_UPDATE_BILL_STATE = "UPDATE bill SET bill_state=? WHERE bill_id=?";

    @Override
    public Bill find(long id) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_BILL_BY_ID)) {
                pstmt.setLong(1, id);
                return toBill(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find bill", e);
        }
    }

    @Override
    public List<Bill> findAll() {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                ResultSet resultSet = stmt.executeQuery(SQL_SELECT_ALL_BILLS);
                return toBillList(resultSet.getMetaData(), resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find bills", e);
        }
    }

    @Override
    public List<Bill> findAccountBills(long id) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_ACCOUNT_BILLS)) {
                pstmt.setLong(1, id);
                return toBillList(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find bills", e);
        }
    }

    @Override
    public Bill save(Bill bill) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt =
                         connection.prepareStatement(SQL_INSERT_BILL, Statement.RETURN_GENERATED_KEYS)) {
                int k = 1;
                pstmt.setObject(k++, bill.getCheckIn());
                pstmt.setObject(k++, bill.getCheckOut());
                pstmt.setBigDecimal(k++, bill.getTotal());
                pstmt.setLong(k++, bill.getAccountId());
                pstmt.setLong(k++, bill.getApartmentsId());
                pstmt.setInt(k, bill.getStateId());
                if (pstmt.executeUpdate() == 1) {
                    ResultSet rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        long id = rs.getLong(1);
                        bill.setId(id);
                        return bill;
                    } else {
                        throw new DaoException("Can't save bill");
                    }
                } else {
                    throw new DaoException("Can't save bill");
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can't save bill", e);
        }
    }

    @Override
    public void updateStateId(Bill bill) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE_BILL_STATE)) {
                pstmt.setInt(1, bill.getStateId());
                pstmt.setLong(2, bill.getId());
                if (pstmt.executeUpdate() != 1) {
                    throw new DaoException("Can't update bill's state");
                }
            } catch (SQLException e) {
                throw new DaoException("Can't update bill's state", e);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't update bill's state", e);
        }
    }

    private Bill toBill(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        Bill bill = new Bill();

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case SQL_BILL_ID:
                    bill.setId(resultSet.getLong(i));
                    break;
                case SQL_BILL_CHECK_IN:
                    bill.setCheckIn(resultSet.getObject(i, LocalDateTime.class));
                    break;
                case SQL_BILL_CHECK_OUT:
                    bill.setCheckOut(resultSet.getObject(i, LocalDateTime.class));
                    break;
                case SQL_BILL_TOTAL:
                    bill.setTotal(resultSet.getBigDecimal(i));
                    break;
                case SQL_BILL_ACCOUNT_ID:
                    bill.setAccountId(resultSet.getLong(i));
                    break;
                case SQL_BILL_APARTMENTS_ID:
                    bill.setApartmentsId(resultSet.getLong(i));
                    break;
                case SQL_BILL_STATE_ID:
                    bill.setStateId(resultSet.getInt(i));
                    break;
                default:
                    // No operations
            }
        }
        return bill;
    }

    private List<Bill> toBillList(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        List<Bill> billList = new ArrayList<>();

        while (true) {
            Bill bill = toBill(metaData, resultSet);
            if (bill == null) {
                break;
            }
            billList.add(bill);
        }

        return billList;
    }
}
