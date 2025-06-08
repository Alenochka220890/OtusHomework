package tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberValidator {
    //Применяем паттерн для определения двузначного числа
    public boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^\\d{1,2}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
