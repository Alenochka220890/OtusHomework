package animals.birds;

import animals.AbsAnimal;
import data.ColorData;

public class Duck extends AbsAnimal implements IFlying{
    public Duck(String name, int age, int weight, ColorData colorData){
        super(name, age, weight, colorData);
    }


    @Override
    public void say() {
        System.out.println("Кря");
    };

    @Override
    public void fly() {
        System.out.println("Я лечу");
    };

}