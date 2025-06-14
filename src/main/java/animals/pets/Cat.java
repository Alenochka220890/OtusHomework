package animals.pets;

import animals.AbsAnimal;
import data.AnimalTypeData;
import data.ColorData;

public class Cat extends AbsAnimal {
    public Cat(String name, int age, int weight, ColorData colorData, AnimalTypeData animalTypeData) {
        super(name, age, weight, colorData, animalTypeData);
    }


    @Override
    public String say() {
        return "я мяукаю";
    }

}
