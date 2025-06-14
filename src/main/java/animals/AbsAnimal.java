package animals;

import data.AnimalTypeData;
import data.ColorData;

import static data.AnimalTypeData.*;

public abstract class AbsAnimal {

    private String name = "";
    private int age = -1;
    private int weight = -1;
    private ColorData color = null;
    private AnimalTypeData type = null;

    //Создаем конструктор
    public AbsAnimal(String name, int age, int weight, ColorData colorData, AnimalTypeData animalTypeData) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = colorData;
        this.type = animalTypeData;
    }

    //Пишем Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public ColorData getColor() {
        return color;
    }

    public AnimalTypeData getType() {
        return type;
    }

    public String say() {
        return "Я говорю";
    }

    public void go() {
        System.out.println("Я иду");
    }

    public void eat() {
        System.out.println("Я ем");
    }

    public void drink() {
        System.out.println("Я пью");
    }

    public String fly() {
        return "Я летаю";
    }

    public String swim() {
        return "Я плаваю";
    }


//    public String toString(){
//       return String.format("Привет! Меня зовут %s, мне %d %s, я вешу - %d кг, мой цвет - %s, %s, %s", name, age, getYearPadej(), weight, color.getName().toLowerCase(), say(), fly());
//   }

    public String toString() {
        if (this.type == AnimalTypeData.CAT || this.type == AnimalTypeData.DOG) {
            return String.format("Привет! Я %s. Меня зовут %s, мне %d %s, я вешу - %d кг, мой цвет - %s, %s", type.getName().toLowerCase(), name, age, getYearPadej(), weight, color.getName().toLowerCase(), say());
        } else if (this.type == AnimalTypeData.DUCK) {
            return String.format("Привет! Я %s. Меня зовут %s, мне %d %s, я вешу - %d кг, мой цвет - %s, %s, %s", type.getName().toLowerCase(), name, age, getYearPadej(), weight, color.getName().toLowerCase(), say(), fly());
        }
        return String.format("Привет! Я %s. Меня зовут %s, мне %d %s, я вешу - %d кг, мой цвет - %s, %s", type.getName().toLowerCase(), name, age, getYearPadej(), weight, color.getName().toLowerCase(), swim());
    }

    public String getYearPadej() {
        if (age >= 11 && age <= 14) {
            return "лет";
        }
        int ostatok = age % 10;
        if (ostatok == 1) {
            return "год";
        }
        if (ostatok >= 2 && ostatok <= 4) {
            return "года";
        }
        return "лет";

    }
}


