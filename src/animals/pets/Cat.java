package animals.pets;

import animals.AbsAnimal;
import data.ColorData;

public class Cat extends AbsAnimal {
    public Cat(String name, int age, int weight, ColorData colorData){
        super(name, age, weight, colorData);
    }


    @Override
    public void say() {
        System.out.println("Мяу");
    };

}
