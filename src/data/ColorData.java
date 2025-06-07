package data;

public enum ColorData {
    WHITE("Белый"),
    RED("Красный");

    private String name;

   //Пишем конструктор
    ColorData(String name) {
        this.name = name;
    }

    //Метод, который возвращает имя
    public String getName(){
        return name;
    }

}
