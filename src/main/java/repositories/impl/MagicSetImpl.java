package repositories.impl;

import entities.MagicSet;
import infrastructure.DatabaseConfig;
import repositories.Loggable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MagicSetImpl implements Loggable {
    private final DatabaseConfig databaseConfig = new DatabaseConfig();
    public MagicSetImpl() {
        Initialize();
    }
    public void Initialize() {
        String dropTableSQL = "DROP TABLE MAGIC_SET ";
        String createTableSQL =
                "CREATE TABLE MAGIC_SET (" +
                        "SET_ID NUMBER PRIMARY KEY, " +
                        "SET_NAME VARCHAR2(255) NOT NULL, " +
                        "QUANTITY NUMBER NOT NULL, " +
                        "PATCH VARCHAR2(255) NOT NULL, " +
                        "RELEASE_DATE DATE NOT NULL" +
                        ")";
        try (Statement stmt = databaseConfig.getConnection().createStatement()) {
            stmt.execute(dropTableSQL);
            stmt.execute(createTableSQL);
            logInfo("Table MAGIC_SET created successfully");
            databaseConfig.closeConnection();
        } catch (Exception e) {
            logError("Error when creating table MAGIC_SET: " + e.getMessage());
            databaseConfig.closeConnection();
        }
    }
    public void Create(MagicSet set) {
        String insertSetSQL = "INSERT INTO MAGIC_SET(SET_ID, SET_NAME, QUANTITY, PATCH, RELEASE_DATE) " +
                "VALUES(" + set.getId() +
                ", '" + set.getName() +
                "', " + set.getQuantity() +
                ", '" + set.getPatch() +
                "', '" + set.getReleaseDate() +
                "')";
        try(Statement stmt = databaseConfig.getConnection().createStatement()){
            stmt.execute(insertSetSQL);
            logInfo("Set " + set + " created successfully");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            logError("Error when creating Set " + set + ": " + e.getMessage());
            databaseConfig.closeConnection();
        }
    }
    public MagicSet Read(int readId) {
        MagicSet set = new MagicSet();
        String selectSQL = "SELECT * FROM MAGIC_SET WHERE SET_ID = ";
        try(PreparedStatement pstmt = databaseConfig.getConnection().prepareStatement(selectSQL + readId)) {

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            Date date = rs.getDate("RELEASE_DATE");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
            String formattedDate = simpleDateFormat.format(date);

            set = new MagicSet(rs.getInt("SET_ID"), rs.getString("SET_NAME"), rs.getInt("QUANTITY"), rs.getString("PATCH"), formattedDate);

            logInfo("Set read successfully: " + set);
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            logError("Error when reading Set " + readId + ": " + e.getMessage());
            databaseConfig.closeConnection();
        }
        return set;
    }
    public List<MagicSet> ReadAll() {
        List<MagicSet> setList = new ArrayList<>();
        String selectAllSQL = "SELECT * FROM MAGIC_SET";
        try(PreparedStatement pstmt = databaseConfig.getConnection().prepareStatement(selectAllSQL)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Date date = rs.getDate("RELEASE_DATE");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
                String formattedDate = simpleDateFormat.format(date);

                MagicSet set = new MagicSet(rs.getInt("SET_ID"), rs.getString("SET_NAME"), rs.getInt("QUANTITY"), rs.getString("PATCH"), formattedDate);
                setList.add(set);
            }
            logInfo("Set read successfully");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            logError("Error when trying to read Sets: " + e.getMessage());
            databaseConfig.closeConnection();
        }
        return setList;
    }
    public void Update(MagicSet set) {
        String updateSQL = "UPDATE MAGIC_SET SET " +
                "SET_ID = " + set.getId() +
                ", SET_NAME = '" + set.getName() +
                "', QUANTITY = " + set.getQuantity() +
                ", PATCH = '" + set.getPatch() +
                "', RELEASE_DATE = '" + set.getReleaseDate() +
                "' WHERE SET_ID = " + set.getId();
        try(Statement stmt = databaseConfig.getConnection().createStatement()){
            stmt.execute(updateSQL);
            logInfo("Set ID number " + set.getId() + " updated successfully");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            logError("Error when trying to update Set ID number " + set.getId() + ": " + e.getMessage());
            databaseConfig.closeConnection();
        }
    }
    public void Delete(int deleteId) {
        String deleteSQL = "DELETE FROM MAGIC_SET WHERE SET_ID = " + deleteId;
        try(Statement stmt = databaseConfig.getConnection().createStatement()){
            stmt.execute(deleteSQL);
            logInfo("Set with ID number " + deleteId + " successfully deleted");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            logError("Error when trying to delete Set ID number " + deleteId + ": " + e.getMessage());
            databaseConfig.closeConnection();
        }
    }
}
