package animals.birds;

import animals.AbsAnimal;
import data.AnimalTypeData;
import data.ColorData;

public class Duck extends AbsAnimal implements IFlying {
    public Duck(String name, int age, int weight, ColorData colorData, AnimalTypeData animalTypeData) {
        super(name, age, weight, colorData, animalTypeData);
    }


    @Override
    public String say() {
        return "я крякаю";
    }

    @Override
    public String fly() {
        return "я умею летать";
    }

}