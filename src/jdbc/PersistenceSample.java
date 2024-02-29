package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseOperations {

    public static void main(String[] args) {
        try (Connection connection  = JdbcConnection.getConnection();) {
            System.out.println("Database connection established.");

            // 示例：查询所有单位信息
            System.out.println("Fetching all units...");
            fetchAllUnits(connection);

            // 示例：查询所有职业信息
            System.out.println("Fetching all professions...");
            fetchAllProfessions(connection);

            // 示例：查询所有玩家信息
            System.out.println("Fetching all players...");
            fetchAllPlayers(connection);

            // 示例：查询所有物品信息
            System.out.println("Fetching all items...");
            fetchAllItems(connection);

            // 示例：查询所有背包中物品信息
            System.out.println("Fetching all items in bags...");
            fetchAllBagItems(connection);

            // 示例：查询所有动物种类信息
            System.out.println("Fetching all animal types...");
            fetchAllAnimalTypes(connection);

            // 其他操作...
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 以下是示例数据库查询方法，您可以根据需要添加更多方法

    private static void fetchAllUnits(Connection connection) throws SQLException {
        String query = "SELECT * FROM Unit";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String unitId = resultSet.getString("Unit_id");
                String unitName = resultSet.getString("Unit_name");
                int unitHP = resultSet.getInt("Unit_HP");
                int unitATK = resultSet.getInt("Unit_ATK");
                String unitLocation = resultSet.getString("Unit_location");
                String timeId = resultSet.getString("Time_id");
                System.out.println("Unit ID: " + unitId + ", Name: " + unitName + ", HP: " + unitHP + ", ATK: " + unitATK + ", Location: " + unitLocation + ", Time ID: " + timeId);
            }
        }
    }

    // 其他查询方法类似...

    // 以下是示例的插入数据方法，您可以根据需要添加更多方法

    private static void insertNewPlayer(Connection connection, String playerId, String playerStatus, String playerName, int playerEnergyPoint, int playerActionPoint, int playerWeightBearing, double playerMoney, String professionId) throws SQLException {
        String query = "INSERT INTO Player (Player_id, Player_status, Player_name, Player_EnergyPoint, Player_ActionPoint, Player_weightbearing, Player_money, Profession_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, playerId);
            preparedStatement.setString(2, playerStatus);
            preparedStatement.setString(3, playerName);
            preparedStatement.setInt(4, playerEnergyPoint);
            preparedStatement.setInt(5, playerActionPoint);
            preparedStatement.setInt(6, playerWeightBearing);
            preparedStatement.setDouble(7, playerMoney);
            preparedStatement.setString(8, professionId);
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New player inserted successfully.");
            }
        }
    }

    // 其他插入数据方法类似...
}
