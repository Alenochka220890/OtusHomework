import animals.AbsAnimal;
import connector.MySqlDbConnector;
import data.AnimalTypeData;
import data.ColorData;
import data.CommandData;
import factory.AnimalFactory;
import sqlmethods.SqlMethods;
import tools.NumberValidator;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    //Создаем объект сканнера (считывание ввода из консоли)
    private static Scanner scanner = new Scanner(System.in);
    //Создаем объект класса NumberValidator (для определения какие числа для ввода будут приниматься)
    private static NumberValidator numberValidator = new NumberValidator();

    private static SqlMethods sqlMethods;

    public Main() throws SQLException, IOException {

    }


    public static void main(String... args) throws SQLException, IOException {


        try {
            sqlMethods = new SqlMethods();
        } catch (SQLException | IOException e) {
            System.err.println("Ошибка при подключении к БД: " + e.getMessage());
            return;
        }
        //Создаем списки с животными и  командами, которые будут выводиться на консоль для выбора и приниматься программой
        List<AbsAnimal> animals = new ArrayList<>();
        List<String> commandNames = new ArrayList<>();
        // Добавляем в список команды из enum в нижнем регистре
        for (CommandData commandData : CommandData.values()) {
            commandNames.add(commandData.name().toLowerCase());
        }
        //Выводим команды из спика enum CommandData. Если вводим команду не из списка, то выводим ошибку.
        while (true) {
            System.out.println(String.format("Введите команду: %s", String.join("/", commandNames)));

            String userCommandConsole = scanner.next().trim().toLowerCase();

            if (!commandNames.contains(userCommandConsole)) {
                System.out.println("Вы ввели неверную команду. Повторите ввод команды");

                continue;
            }
            //Переводим веденную команду в верхний регистр
            CommandData userCommand = CommandData.valueOf(userCommandConsole.toUpperCase());

            switch (userCommand) {
                case ADD: {

                    List<String> animalsTypeNames = new ArrayList<>();

                    for (AnimalTypeData animalTypeData : AnimalTypeData.values()) {
                        animalsTypeNames.add(animalTypeData.name().toLowerCase());
                    }

                    AnimalTypeData animalTypeData = null;

                    while (true) {
                        System.out.printf("Введите тип животного: %s\n", String.join(", ", animalsTypeNames));
                        String userAnimalTypeData = scanner.next();

                        if (!animalsTypeNames.contains(userAnimalTypeData)) {
                            System.out.println("Вы ввели неверный тип животного");
                            continue;
                        }
                        animalTypeData = AnimalTypeData.valueOf(userAnimalTypeData.toUpperCase());
                        break;
                    }
                    System.out.println("Введите имя вашего животного");

                    String name;
                    while (true) {

                        name = scanner.next().trim();
                        // Проверяем, что строка не пустая и состоит только из букв
                        if (!name.isEmpty() && name.matches("^[a-zA-Zа-яА-ЯёЁ]+$")) {
                            //Делаем так, чтобы имя записалось с заглавной буквы
                            name = name.substring(0, 1).toUpperCase()
                                    + name.substring(1).toLowerCase();
                            break;
                        } else {
                            System.out.println("Некорректный ввод. Введите имя, используя только буквы:");

                        }
                    }


                    int animalAge = getAnimalAgeWeight("Введите возраст животного", "Вы ввели неверный возраст животного. Повторите ввод");
                    int animalWeight = getAnimalAgeWeight("Введите вес животного", "Вы ввели неверный вес животного. Повторите ввод");

                    List<String> animalsColor = new ArrayList<>();

                    for (ColorData colorData : ColorData.values()) {
                        animalsColor.add(colorData.name().toLowerCase());
                    }

                    ColorData colorData = null;

                    while (true) {
                        System.out.printf("Введите цвет животного: %s\n", String.join(", ", animalsColor));
                        String userAnimalColor = scanner.next();

                        if (!animalsColor.contains(userAnimalColor)) {
                            System.out.println("Вы ввели неверный цвет животного");
                            continue;
                        }
                        colorData = ColorData.valueOf(userAnimalColor.toUpperCase());
                        break;
                    }

                    AbsAnimal animal = new AnimalFactory(name, animalAge, animalWeight, colorData, animalTypeData).create(animalTypeData);
                    animals.add(animal);
                    System.out.println(animal.toString());

                    // Сохраняем животное в БД
                    sqlMethods.addAnimal(animal);
                    break;


                }
                case LIST: {
                    // Выводим список животных
                    ResultSet result = sqlMethods.listAnimal();
                    sqlMethods.showAnimalResultSet(result);
                    break;

                }

                case SELECTTYPE: {

                    List<String> animalsTypeNames = new ArrayList<>();

                    for (AnimalTypeData animalTypeData : AnimalTypeData.values()) {
                        animalsTypeNames.add(animalTypeData.name().toLowerCase());
                    }

                    AnimalTypeData animalTypeData = null;
                    String userAnimalTypeData = "";
                    while (true) {
                        System.out.printf("Введите тип животного для выбора: %s\n", String.join(", ", animalsTypeNames));
                        userAnimalTypeData = scanner.next();

                        if (!animalsTypeNames.contains(userAnimalTypeData)) {
                            System.out.println("Вы ввели неверный тип животного");
                            continue;
                        }
                        animalTypeData = AnimalTypeData.valueOf(userAnimalTypeData.toUpperCase());

                        break;
                    }

                    // Выбираем из БД по типу животного
                    MySqlDbConnector dbConnector = null;
                    try {
                        dbConnector = new MySqlDbConnector();
                        ResultSet resultType = dbConnector.executeQuery("SELECT * FROM animals where type = '" + userAnimalTypeData.toLowerCase() + "'");

                        // Выводим таблицу с результатом поиска по типу животного
                        sqlMethods.showAnimalResultSet(resultType);

                    } catch (SQLException | IOException ex) {
                        System.out.println("Ошибка при запросе к БД: " + ex.getMessage());
                    }

                    break;


                }
                case UPDATEANIMAL: {

                    List<String> animalsTypeNames = new ArrayList<>();

                    for (AnimalTypeData animalTypeData : AnimalTypeData.values()) {
                        animalsTypeNames.add(animalTypeData.name().toLowerCase());
                    }

                    AnimalTypeData animalTypeData = null;
                    String userAnimalTypeData = "";
                    while (true) {
                        System.out.printf("Введите тип животного, которое хотите изменить: %s\n", String.join(", ", animalsTypeNames));
                        userAnimalTypeData = scanner.next();

                        if (!animalsTypeNames.contains(userAnimalTypeData)) {
                            System.out.println("Вы ввели неверный тип животного");
                            continue;
                        }

                        animalTypeData = AnimalTypeData.valueOf(userAnimalTypeData.toUpperCase());
                        break;
                    }

                    // Выводим из БД всех животных выбранного типа
                    MySqlDbConnector dbConnector = null;
                    try {
                        dbConnector = new MySqlDbConnector();
                        ResultSet resultType = dbConnector.executeQuery("SELECT * FROM animals where type = '" + userAnimalTypeData.toLowerCase() + "'");

                        // Выводим таблицу с результатом поиска по типу животного
                        sqlMethods.showAnimalResultSet(resultType);

                    } catch (SQLException | IOException ex) {
                        System.out.println("Ошибка при запросе к БД: " + ex.getMessage());
                    }


                    int userAnimalId = -1;
                    while (true) {
                        System.out.println("Введите ID животного из предложенных, которое хотите изменить: ");
                        if (!scanner.hasNextInt()) {
                            System.out.println("Ошибка: нужно ввести целое число!");
                            scanner.next(); // очищаем некорректный ввод
                            continue;
                        }
                        userAnimalId = scanner.nextInt();
                        break;
                    }

                    while (true) {
                        System.out.printf("Введите тип животного, на которое хотите изменить: %s\n", String.join(", ", animalsTypeNames));
                        userAnimalTypeData = scanner.next();

                        if (!animalsTypeNames.contains(userAnimalTypeData)) {
                            System.out.println("Вы ввели неверный тип животного");
                            continue;
                        }
                        animalTypeData = AnimalTypeData.valueOf(userAnimalTypeData.toUpperCase());
                        break;
                    }
                    System.out.println("Введите имя животного, на которое хотите изменить");

                    String name;
                    while (true) {

                        name = scanner.next().trim();
                        // Проверяем, что строка не пустая и состоит только из букв
                        if (!name.isEmpty() && name.matches("^[a-zA-Zа-яА-ЯёЁ]+$")) {
                            //Делаем так, чтобы имя записалось с заглавной буквы
                            name = name.substring(0, 1).toUpperCase()
                                    + name.substring(1).toLowerCase();
                            break;
                        } else {
                            System.out.println("Некорректный ввод. Введите имя, используя только буквы:");

                        }
                    }


                    int animalAge = getAnimalAgeWeight("Введите возраст животного, на который хотите изменить", "Вы ввели неверный возраст животного. Повторите ввод");
                    int animalWeight = getAnimalAgeWeight("Введите вес животного, на который хотите изменить", "Вы ввели неверный вес животного. Повторите ввод");

                    List<String> animalsColor = new ArrayList<>();

                    for (ColorData colorData : ColorData.values()) {
                        animalsColor.add(colorData.name().toLowerCase());
                    }

                    ColorData colorData = null;

                    while (true) {
                        System.out.printf("Введите цвет животного, на который хотите изменить: %s\n", String.join(", ", animalsColor));
                        String userAnimalColor = scanner.next();

                        if (!animalsColor.contains(userAnimalColor)) {
                            System.out.println("Вы ввели неверный цвет животного");
                            continue;
                        }
                        colorData = ColorData.valueOf(userAnimalColor.toUpperCase());
                        break;
                    }

                    AbsAnimal animal = new AnimalFactory(name, animalAge, animalWeight, colorData, animalTypeData).create(animalTypeData);
                    animals.add(animal);
                    System.out.println(animal.toString());
                    // Обновляем в БД
                    try {
                        dbConnector = new MySqlDbConnector();
                        String sql = String.format(
                                "UPDATE animals SET type = '%s', name = '%s', age = %d , weight = %d, color = '%s' where id = %d",
                                animalTypeData.name().toLowerCase(),
                                name,
                                animalAge,
                                animalWeight,
                                colorData.name().toLowerCase(),
                                userAnimalId
                        );
                        dbConnector.execute(sql);
                        System.out.println("Животное обновлено в БД!");
                    } catch (SQLException | IOException ex) {
                        System.out.println("Ошибка при обновлении в БД: " + ex.getMessage());
                    }

                    break;


                }
                case EXIT: {
                    //Закрываем соединение к БД
                    MySqlDbConnector dbConnector = null;
                    dbConnector = new MySqlDbConnector();
                    dbConnector.close();
                    System.exit(0);
                }
                break;
            }

        }
    }

    private static int getAnimalAgeWeight(String consoleMsg, String errorMessage) {
        while (true) {
            System.out.println(consoleMsg);
            String userAnimalAge = scanner.next();
            if (!numberValidator.isNumber(userAnimalAge)) {
                System.out.println(errorMessage);
                continue;
            }
            return Integer.parseInt(userAnimalAge);
        }

    }


}


