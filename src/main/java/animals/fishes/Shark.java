package animals.fishes;

import animals.AbsAnimal;
import data.AnimalTypeData;
import data.ColorData;

public class Shark extends AbsAnimal implements ISwimming {
    public Shark(String name, int age, int weight, ColorData colorData, AnimalTypeData animalTypeData) {
        super(name, age, weight, colorData, animalTypeData);
    }

    @Override
    public String swim() {
        return "я умею плавать";
    }

}