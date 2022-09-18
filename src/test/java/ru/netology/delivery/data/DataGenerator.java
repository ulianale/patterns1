package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataGenerator {
    private DataGenerator() {
    }

    static Faker faker = new Faker((new Locale("ru")));

    public static String generateDate(int days) {
        String date = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateCity(String locale) {
        List<String> list = Arrays.asList("Абакан", "Анадырь", "Архангельск", "Астрахань", "Барнаул", "Благовещенск",
                "Брянск", "Владикавказ", "Владивосток", "Волгоград", "Вологда", "Воронеж", "Горно-Алтайск", "Йошкар-Ола",
                "Ижевск", "Казань", "Калининград", "Калуга", "Краснодар", "Курск", "Магадан", "Махачкала", "Москва",
                "Мурманск", "Нарьян-Мар", "Новосибирск", "Орёл", "Оренбург", "Ростов-на-Дону", "Рязань", "Салехард", "Самара",
                "Санкт-Петербург", "Саранск", "Саратов", "Ульяновск", "Хабаровск", "Ханты-Мансийск", "Чебоксары", "Южно-Сахалинск");
        Random rand = new Random();
        String city = list.get(rand.nextInt(list.size()));
        return city;

    }

    public static String generateName(String locale) {
        String name = faker.name().fullName().replaceAll("ё", "е");
        return name;
    }

    public static String generatePhone(String locale) {
        String phone = faker.phoneNumber().phoneNumber().replaceAll("\\D", "");
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            UserInfo user = new UserInfo(generateCity("ru"), generateName("ru"), generatePhone("ru"));
            return user;
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}

