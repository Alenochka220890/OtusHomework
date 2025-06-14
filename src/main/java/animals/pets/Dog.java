package animals.pets;

import animals.AbsAnimal;
import data.AnimalTypeData;
import data.ColorData;

public class Dog extends AbsAnimal {
    public Dog(String name, int age, int weight, ColorData colorData, AnimalTypeData animalTypeData){
        super(name, age, weight, colorData, animalTypeData);
    }


    @Override
    public String say() {
        return "я гавкаю";
    };

}
