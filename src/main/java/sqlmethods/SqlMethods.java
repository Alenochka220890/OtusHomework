package sqlmethods;

import animals.AbsAnimal;
import connector.MySqlDbConnector;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlMethods {


    private MySqlDbConnector dbConnector;

    public SqlMethods() throws SQLException, IOException {
        this.dbConnector = new MySqlDbConnector();
    }

    public void addAnimal(AbsAnimal animal) throws SQLException {
        MySqlDbConnector dbConnector = null;
        try {
            dbConnector = new MySqlDbConnector();
            String sql = String.format(
                    "INSERT INTO animals (type, name, age, weight, color) VALUES ('%s', '%s', %d, %d, '%s')",
                    animal.getType().name().toLowerCase(),
                    animal.getName(),
                    animal.getAge(),
                    animal.getWeight(),
                    animal.getColor().name().toLowerCase()
            );
            dbConnector.execute(sql);
            System.out.println("Животное сохранено в БД!");
        } catch (SQLException ex) {
            System.out.println("Ошибка при сохранении в БД: " + ex.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public ResultSet listAnimal() throws SQLException {
        return dbConnector.executeQuery("SELECT * FROM animals");


    }

    public void showAnimalResultSet(ResultSet result) throws SQLException {
        System.out.println("\nСписок животных из базы данных:");
        System.out.println("|----|---------------------|---------------------|-----------|--------|----------------|");
        System.out.printf("| %-2s | %-19s | %-19s | %-9s | %-6s | %-14s |\n",
                "ID", "Тип", "Имя", "Возраст", "Вес", "Цвет");
        System.out.println("|----|---------------------|---------------------|-----------|--------|----------------|");

        while (result.next()) {
            System.out.printf("| %-2d | %-19s | %-19s | %-9d | %-6d | %-14s |\n",
                    result.getInt("id"),
                    result.getString("type"),
                    result.getString("name"),
                    result.getInt("age"),
                    result.getInt("weight"),
                    result.getString("color")
            );
        }
        System.out.println("|----|---------------------|---------------------|-----------|--------|----------------|");
    }
}








