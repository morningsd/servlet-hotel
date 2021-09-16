package edu.demian.wp.model.dao.impl;

import edu.demian.wp.model.dao.ApplicationDao;
import edu.demian.wp.model.entity.Application;
import edu.demian.wp.model.exception.DaoException;
import edu.demian.wp.model.util.DBManager;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDaoImpl implements ApplicationDao {
    private final DataSource DATASOURCE = DBManager.getInstance();

    private static final String SQL_APPLICATION_ID = "application_id";
    private static final String SQL_APPLICATION_NO_ROOMS = "no_rooms";
    private static final String SQL_APPLICATION_NO_ADULTS = "no_adults";
    private static final String SQL_APPLICATION_NO_CHILDREN = "no_children";
    private static final String SQL_APPLICATION_CHECK_IN = "check_in";
    private static final String SQL_APPLICATION_CHECK_OUT = "check_out";
    private static final String SQL_APPLICATION_WISHES = "wishes";
    private static final String SQL_APPLICATION_TYPE_ID = "application_type";
    private static final String SQL_APPLICATION_ACCOUNT_ID = "application_account";
    private static final String SQL_APPLICATION_APARTMENTS_ID = "application_apartments";

    private static final String SQL_FIND_APPLICATION_BY_ID = "SELECT * FROM application WHERE application_id=?";
    private static final String SQL_INSERT_APPLICATION = "INSERT INTO application " +
            "(no_rooms, no_adults, no_children, check_in, check_out, wishes, application_type, application_account) " +
            "VALUES (?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_ALL_APPLICATIONS = "SELECT * FROM application";
    private static final String SQL_UPDATE_APPLICATION = "UPDATE application SET " +
            "no_rooms=?, no_adults=?, no_children=?, check_in=?, check_out=?, wishes=?, application_type=?," +
            "application_account=?, application_apartments=? WHERE application_id=?";
    private static final String SQL_FIND_ACCOUNT_APPLICATIONS = "SELECT * FROM application WHERE " +
            "application_account=?";
    private static final String SQL_DELETE_APPLICATION = "DELETE FROM application WHERE application_id=?";

    @Override
    public void save(Application entity) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt =
                         connection.prepareStatement(SQL_INSERT_APPLICATION, Statement.RETURN_GENERATED_KEYS)) {
                int k = 1;
                pstmt.setInt(k++, entity.getNoRooms());
                pstmt.setInt(k++, entity.getNoAdults());
                pstmt.setInt(k++, entity.getNoChildren());
                pstmt.setObject(k++, entity.getCheckIn());
                pstmt.setObject(k++, entity.getCheckOut());
                pstmt.setString(k++, entity.getWishes());
                pstmt.setInt(k++, entity.getTypeId());
                pstmt.setLong(k, entity.getAccountId());
                if (pstmt.executeUpdate() == 1) {
                    ResultSet rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        long id = rs.getLong(1);
                        entity.setId(id);
                    } else {
                        throw new DaoException("Can't save application");
                    }
                } else {
                    throw new DaoException("Can't save application");
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can't save application", e);
        }
    }

    @Override
    public void update(Application entity) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE_APPLICATION)) {
                int k = 1;
                pstmt.setInt(k++, entity.getNoRooms());
                pstmt.setInt(k++, entity.getNoAdults());
                pstmt.setInt(k++, entity.getNoChildren());
                pstmt.setObject(k++, entity.getCheckIn());
                pstmt.setObject(k++, entity.getCheckOut());
                pstmt.setString(k++, entity.getWishes());
                pstmt.setInt(k++, entity.getTypeId());
                pstmt.setLong(k++, entity.getAccountId());
                pstmt.setLong(k++, entity.getApartmentsId());
                pstmt.setLong(k, entity.getId());
                if (pstmt.executeUpdate() != 1) {
                    throw new DaoException("Can't update application");
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can't update application", e);
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_DELETE_APPLICATION)) {
                pstmt.setLong(1, id);
                if (pstmt.executeUpdate() != 1) {
                    throw new DaoException("Can't delete an application");
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can't delete an application", e);
        }
    }

    @Override
    public Application find(long id) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_FIND_APPLICATION_BY_ID)) {
                pstmt.setLong(1, id);
                return toApplication(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find an application", e);
        }

    }

    @Override
    public List<Application> findAll() {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                ResultSet resultSet = stmt.executeQuery(SQL_SELECT_ALL_APPLICATIONS);
                return toApplicationsList(resultSet.getMetaData(), resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find applications", e);
        }
    }

    @Override
    public List<Application> findAccountApplications(long id) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_FIND_ACCOUNT_APPLICATIONS)) {
                pstmt.setLong(1, id);
                return toApplicationsList(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find account's applications", e);
        }
    }

    private Application toApplication(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        Application application = new Application();

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case SQL_APPLICATION_ID:
                    application.setId(resultSet.getLong(i));
                    break;
                case SQL_APPLICATION_NO_ROOMS:
                    application.setNoRooms(resultSet.getInt(i));
                    break;
                case SQL_APPLICATION_NO_ADULTS:
                    application.setNoAdults(resultSet.getInt(i));
                    break;
                case SQL_APPLICATION_NO_CHILDREN:
                    application.setNoChildren(resultSet.getInt(i));
                    break;
                case SQL_APPLICATION_CHECK_IN:
                    application.setCheckIn(resultSet.getObject(i, LocalDateTime.class));
                    break;
                case SQL_APPLICATION_CHECK_OUT:
                    application.setCheckOut(resultSet.getObject(i, LocalDateTime.class));
                    break;
                case SQL_APPLICATION_WISHES:
                    application.setWishes(resultSet.getString(i));
                    break;
                case SQL_APPLICATION_TYPE_ID:
                    application.setTypeId(resultSet.getInt(i));
                    break;
                case SQL_APPLICATION_ACCOUNT_ID:
                    application.setAccountId(resultSet.getLong(i));
                    break;
                case SQL_APPLICATION_APARTMENTS_ID:
                    application.setApartmentsId(resultSet.getLong(i));
                    break;
                default:
                    // No operations
            }
        }
        return application;
    }

    private List<Application> toApplicationsList(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        List<Application> applicationsList = new ArrayList<>();

        while (true) {
            Application application = toApplication(metaData, resultSet);
            if (application == null) {
                break;
            }
            applicationsList.add(application);
        }

        return applicationsList;
    }
}
