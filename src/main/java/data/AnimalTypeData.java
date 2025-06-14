package data;

public enum AnimalTypeData {
    CAT ("кошка"),
    DOG ("собака"),
    DUCK ("утка"),
    SHARK ("акула");

    private String name;

    //Пишем конструктор
    AnimalTypeData(String name) {
        this.name = name;
    }

    //Метод, который возвращает имя
    public String getName(){
        return name;
    }

}


