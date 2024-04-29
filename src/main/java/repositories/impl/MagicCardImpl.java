package repositories.impl;

import entities.MagicCard;
import entities.MagicSet;
import infrastructure.DatabaseConfig;
import repositories.Loggable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MagicCardImpl implements Loggable {
    private final DatabaseConfig databaseConfig = new DatabaseConfig();
    public MagicCardImpl() {
        Initialize();
    }

    private void Initialize() {
        String dropTableSQL = "DROP TABLE MAGIC_CARD ";
        String createTableSQL =
                "CREATE TABLE MAGIC_CARD (" +
                        "CARD_ID NUMBER PRIMARY KEY, " +
                        "CARD_NAME VARCHAR2(255) NOT NULL, " +
                        "CARD_URI VARCHAR2(255) NOT NULL, " +
                        "MANA_COST NUMBER NOT NULL, " +
                        "CARD_TYPE VARCHAR2(255) NOT NULL, " +
                        "FLAVOR_TEXT VARCHAR2(255) NOT NULL, " +
                        "CARD_COLOR VARCHAR2(255) NOT NULL, " +
                        "CARD_SET VARCHAR2(255) NOT NULL)";

    try (Statement stmt = databaseConfig.getConnection().createStatement()) {
        stmt.execute(dropTableSQL);
        stmt.execute(createTableSQL);
        logInfo("Table MAGIC_CARD created successfully");
        databaseConfig.closeConnection();
    } catch (Exception e) {
        logError("Error when creating table MAGIC_CARD: " + e.getMessage());
        databaseConfig.closeConnection();
    }}
    public void Create(MagicCard magicCard){
        String insertCardSql = "INSERT INTO MAGIC_CARD(CARD_ID, CARD_NAME, CARD_URI, MANA_COST, CARD_TYPE, FLAVOR_TEXT, CARD_COLOR, CARD_SET)" +
                "VALUES(" + magicCard.getId() +
                ", '" + magicCard.getName() +
                ", '" + magicCard.getUri() +
                ", '" + magicCard.getMana_cost() +
                ", '" + magicCard.getType_line() +
                ", '" + magicCard.getOracle_text() +
                ", '" + magicCard.getColors() +
                ", '" + magicCard.getSet() +
                "')";
        try(Statement stmt = databaseConfig.getConnection().createStatement()){
            stmt.execute(insertCardSql);
            logInfo("Card " + magicCard + " created successfully");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            logError("Error when creating card " + magicCard + ": " + e.getMessage());
            databaseConfig.closeConnection();
        }

    }
    public MagicCard Read(int readId) {
        MagicCard magicCard = new MagicCard();
        String selectSQL = "SELECT * FROM MAGIC_CARD WHERE CARD_ID = ";
        try(PreparedStatement pstmt = databaseConfig.getConnection().prepareStatement(selectSQL + readId)) {

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            MagicSetImpl setRepository = new MagicSetImpl();
            MagicSet set = setRepository.Read(rs.getInt("SET_ID"));

            magicCard = new MagicCard();

            logInfo("Magic Card read successfully: " + magicCard);
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            logError("Error when reading card with ID " + readId + ": " + e.getMessage());
            databaseConfig.closeConnection();
        }
        return magicCard;
    }
    public List<MagicCard> ReadAll() {
        List<MagicCard> cardList = new ArrayList<>();
        String selectAllSQL = "SELECT * FROM MAGIC_CARD";
        try(PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(selectAllSQL)) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                MagicSetImpl magicSet = new MagicSetImpl();
                MagicSet set = magicSet.Read(rs.getInt("SET_ID"));

                MagicCard card = new MagicCard();
                cardList.add(card);
            }
            logInfo("Cards read successfully");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            logError("Error when trying to read: " + e.getMessage());
            databaseConfig.closeConnection();
        }
        return cardList;
    }
    public void Update(MagicCard card){
        String updateSQL = "UPDATE MAGIC_CARD SET " +
                "CARD_ID = " + card.getId() +
                ", CARD_NAME = " + card.getName() +
                ", CARD_URI = " + card.getUri() +
                ", MANA_COST = " + card.getMana_cost() +
                ", CARD_TYPE = " + card.getType_line() +
                ", FLAVOR_TEXT = " + card.getOracle_text() +
                ", CARD_COLOR = " + card.getColors() +
                ", CARD_SET = " + card.getSet() +
                "WHERE CARD_ID = " + card.getId();
        try (Statement stmt = databaseConfig.getConnection().createStatement()){
            stmt.execute(updateSQL);
            logInfo("Card with ID " + card.getId() + " updated successfully");
            databaseConfig.closeConnection();
        } catch (SQLException e){
            logError("Error when trying to update Card ID number " + card.getId() + ": " + e.getMessage());
            databaseConfig.closeConnection();
        }
    }
    public void Delete(int deleteId) {
        String deleteSQL = "DELETE FROM CP_CARD WHERE CARD_ID = " + deleteId;
        try(Statement stmt = databaseConfig.getConnection().createStatement()){
            stmt.execute(deleteSQL);
            logInfo("Card ID number " + deleteId + " deleted successfully");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            logError("Error when trying to delete Card ID number " + deleteId + ": " + e.getMessage());
            databaseConfig.closeConnection();
        }
    }
    }
