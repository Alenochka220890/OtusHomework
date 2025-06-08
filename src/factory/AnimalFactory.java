package factory;

import animals.AbsAnimal;
import animals.birds.Duck;
import animals.fishes.Shark;
import animals.pets.Cat;
import animals.pets.Dog;
import data.AnimalTypeData;
import data.ColorData;


public class AnimalFactory {
    private String name = "";
    private int age = -1;
    private int weight = -1;
    private ColorData color = null;
    private AnimalTypeData type = null;

    //Создаем конструктор
    public AnimalFactory(String name, int age, int weight, ColorData colorData, AnimalTypeData animalTypeData) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = colorData;
        this.type = animalTypeData;
    }

    //Создаем фабрику
    public AbsAnimal create(AnimalTypeData animalTypeData) {
        switch (animalTypeData) {
            case DOG: {
                return new Dog(name, age, weight, color, type);
            }
            case CAT: {
                return new Cat(name, age, weight, color, type);
            }
            case DUCK: {
                return new Duck(name, age, weight, color, type);
            }
            case SHARK: {
                return new Shark(name, age, weight, color, type);
            }
        }
        throw new RuntimeException(String.format("Animal %s not supported", animalTypeData.name().toLowerCase()));
    }
}
