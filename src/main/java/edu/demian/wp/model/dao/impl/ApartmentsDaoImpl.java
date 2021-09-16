package edu.demian.wp.model.dao.impl;

import edu.demian.wp.model.dao.ApartmentsDao;
import edu.demian.wp.model.entity.Apartments;
import edu.demian.wp.model.exception.DaoException;
import edu.demian.wp.model.util.DBManager;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartmentsDaoImpl implements ApartmentsDao {
    private final DataSource DATASOURCE = DBManager.getInstance();

    private static final String SQL_APARTMENTS_ID = "apartments_id";
    private static final String SQL_APARTMENTS_NO_ROOMS = "no_rooms";
    private static final String SQL_APARTMENTS_NO_ADULTS = "no_adults";
    private static final String SQL_APARTMENTS_NO_CHILDREN = "no_children";
    private static final String SQL_APARTMENTS_PRICE = "price";
    private static final String SQL_APARTMENTS_IMAGE_PATH = "image_path";
    private static final String SQL_APARTMENTS_ACCOUNT_ID = "apartments_account";
    private static final String SQL_APARTMENTS_STATUS_ID = "apartments_status";
    private static final String SQL_APARTMENTS_TYPE_ID = "apartments_type";

    private static final String SQL_SELECT_APARTMENTS_BY_ID = "SELECT * FROM apartments WHERE apartments_id=?";
    private static final String SQL_INSERT_APARTMENTS = "INSERT INTO apartments " +
            "(no_rooms, no_adults, no_children, price, image_path, apartments_account, apartments_status, apartments_type) VALUES (?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_ALL_APARTMENTS = "SELECT * FROM apartments";
    private static final String SQL_UPDATE_APARTMENTS = "UPDATE apartments SET no_rooms=?, no_adults=?, no_children=?, price=?, image_path=?, apartments_account=?," +
            "apartments_status=?, apartments_type=? WHERE apartments_id=?";
    private static final String SQL_DELETE_APARTMENTS = "DELETE FROM apartments WHERE apartments_id=?";
    private static final String SQL_UPDATE_APARTMENTS_STATUS = "UPDATE apartments SET apartments_status=? WHERE apartments_id=?";


    @Override
    public Apartments find(long id) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_APARTMENTS_BY_ID)) {
                pstmt.setLong(1, id);
                return toApartments(pstmt.getMetaData(), pstmt.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find apartments", e);
        }
    }

    @Override
    public List<Apartments> findAll() {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                ResultSet resultSet = stmt.executeQuery(SQL_SELECT_ALL_APARTMENTS);
                return toApartmentsList(resultSet.getMetaData(), resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find apartments' list", e);
        }
    }


    @Override
    public void delete(long id) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_DELETE_APARTMENTS)) {
                pstmt.setLong(1, id);
                if (pstmt.executeUpdate() != 1) {
                    throw new DaoException("Can't delete apartments");
                }
            } catch (SQLException e) {
                throw new DaoException("Can't delete apartments", e);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't delete apartments", e);
        }

    }

    @Override
    public void save(Apartments entity) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt =
                         connection.prepareStatement(SQL_INSERT_APARTMENTS, Statement.RETURN_GENERATED_KEYS)) {
                int k = 1;
                pstmt.setInt(k++, entity.getNoRooms());
                pstmt.setInt(k++, entity.getNoAdults());
                pstmt.setInt(k++, entity.getNoChildren());
                pstmt.setBigDecimal(k++, entity.getPrice());
                pstmt.setString(k++, entity.getImagePath());
                pstmt.setLong(k++, entity.getAccountId());
                pstmt.setInt(k++, entity.getStatusId());
                pstmt.setInt(k, entity.getTypeId());
                if (pstmt.executeUpdate() == 1) {
                    ResultSet rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        long id = rs.getLong(1);
                        entity.setId(id);
                    } else {
                        throw new DaoException("Can't save apartments");
                    }
                } else {
                    throw new DaoException("Can't save apartments");
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can't save apartments", e);
        }
    }


    @Override
    public void update(Apartments entity) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE_APARTMENTS)) {
                int k = 1;
                pstmt.setInt(k++, entity.getNoRooms());
                pstmt.setInt(k++, entity.getNoAdults());
                pstmt.setInt(k++, entity.getNoChildren());
                pstmt.setBigDecimal(k++, entity.getPrice());
                pstmt.setString(k++, entity.getImagePath());
                pstmt.setLong(k++, entity.getAccountId());
                pstmt.setInt(k++, entity.getStatusId());
                pstmt.setInt(k++, entity.getTypeId());
                pstmt.setLong(k, entity.getId());
                if (pstmt.executeUpdate() != 1) {
                    throw new DaoException("Can't update apartments");
                }
            } catch (SQLException e) {
                throw new DaoException("Can't update apartments", e);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't update apartments", e);
        }
    }

    @Override
    public void updateStatusId(Apartments entity) {
        try (Connection connection = DATASOURCE.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE_APARTMENTS_STATUS)) {
                pstmt.setInt(1, entity.getStatusId());
                pstmt.setLong(2, entity.getId());
                if (pstmt.executeUpdate() != 1) {
                    throw new DaoException("Can't update apartments' status");
                }
            } catch (SQLException e) {
                throw new DaoException("Can't update apartments' status", e);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't update apartments' status", e);
        }
    }


    private Apartments toApartments(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        Apartments apartments = new Apartments();

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case SQL_APARTMENTS_ID:
                    apartments.setId(resultSet.getLong(i));
                    break;
                case SQL_APARTMENTS_NO_ROOMS:
                    apartments.setNoRooms(resultSet.getInt(i));
                    break;
                case SQL_APARTMENTS_NO_ADULTS:
                    apartments.setNoAdults(resultSet.getInt(i));
                    break;
                case SQL_APARTMENTS_NO_CHILDREN:
                    apartments.setNoChildren(resultSet.getInt(i));
                    break;
                case SQL_APARTMENTS_PRICE:
                    apartments.setPrice(resultSet.getBigDecimal(i));
                    break;
                case SQL_APARTMENTS_IMAGE_PATH:
                    apartments.setImagePath(resultSet.getString(i));
                    break;
                case SQL_APARTMENTS_ACCOUNT_ID:
                    apartments.setAccountId(resultSet.getLong(i));
                    break;
                case SQL_APARTMENTS_STATUS_ID:
                    apartments.setStatusId(resultSet.getInt(i));
                    break;
                case SQL_APARTMENTS_TYPE_ID:
                    apartments.setTypeId(resultSet.getInt(i));
                    break;
                default:
                    // No operations
            }
        }
        return apartments;
    }

    private List<Apartments> toApartmentsList(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        List<Apartments> apartmentsList = new ArrayList<>();

        while (true) {
            Apartments apartments = toApartments(metaData, resultSet);
            if (apartments == null) {
                break;
            }
            apartmentsList.add(apartments);
        }

        return apartmentsList;
    }

}
