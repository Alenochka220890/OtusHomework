package animals;
import data.AnimalTypeData;
import data.ColorData;
import data.CommandData;
import factory.AnimalFactory;
import tools.NumberValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static NumberValidator numberValidator = new NumberValidator();

    public static void main(String... args) {
        List<AbsAnimal> animals = new ArrayList<>();
        List<String> commandNames = new ArrayList<>();

        for (CommandData commandData : CommandData.values()) {
            commandNames.add(commandData.name().toLowerCase());
        }

        while (true) {
            System.out.println(String.format("Введите команду: %s", String.join("/", commandNames)));

            String userCommandConsole = scanner.next().trim().toLowerCase();


            if (!commandNames.contains(userCommandConsole)) {
                System.out.println("Вы ввели неверную команду. Повторите ввод команды");

                continue;
            }

            CommandData userCommand = CommandData.valueOf(userCommandConsole.toUpperCase());

            switch (userCommand) {
                case ADD: {

                    List<String> animalsTypeNames = new ArrayList<>();

                    for (AnimalTypeData animalTypeData : AnimalTypeData.values()) {
                        animalsTypeNames.add(animalTypeData.name().toLowerCase());
                    }

                    AnimalTypeData animalTypeData = null;
                    while(true){
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

                        name = scanner.next();
                        // Проверяем, что строка не пустая и состоит только из букв
                        if (!name.isEmpty() && name.matches("[a-zA-Zа-яА-Я]+")) {
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

                    while(true){
                        System.out.printf("Введите цвет животного: %s\n", String.join(", ", animalsColor));
                        String userAnimalColor = scanner.next();

                        if (!animalsColor.contains(userAnimalColor)) {
                            System.out.println("Вы ввели неверный цвет животного");
                            continue;
                        }
                        colorData = ColorData.valueOf(userAnimalColor.toUpperCase());
                        break;
                    }

                    AbsAnimal animal = new AnimalFactory(name, animalAge, animalWeight, colorData).create(animalTypeData);
                    animals.add(animal);


                }
                case LIST: {

                    for(AbsAnimal animal:animals){
                        System.out.println(animal.toString());
                    }
                    break;

                }
                case EXIT: {
                    System.exit(0);

                }
                break;
            }

        }
    }

    private static int getAnimalAgeWeight(String consoleMsg, String errorMessage){
        while(true){
            System.out.println(consoleMsg);
            String userAnimalAge = scanner.next();
            if(!numberValidator.isNumber(userAnimalAge)){
                System.out.println(errorMessage);
                continue;
            }
            return Integer.parseInt(userAnimalAge);
        }

    }
}