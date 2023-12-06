import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.*;

public class Main {
    private static final Cache CACHE = Cache.getInstance();
    public static void main(String[] args) {
        while (true){
            showUserInterfaceData();
            String fromConsole = readFromConsole();
            int number = 0;
            try{
                number = Integer.valueOf(fromConsole);
            }catch (Throwable e){
                System.out.println("Ошибка, введены некорректные данные");
                break;
            }

            if(number==0){
                return;
            }else if(number==1){
                System.out.println(CACHE.getPhones());
            }else if(number == 2){
                addNewMobileToCache();
            }else if(number == 3){
                deleteMobileFromCacheByModel();
            }else if(number==4){
                saveDataFromCacheToFile();
            }else if(number==5){
                showMobilesByPriceASC();
            }else if(number==6){
                showMobilesByPriceDESC();
            }else if(number==7){
                uploadDataFromFile();
            }
        }
    }
    private static String readFromConsole(){
        return new Scanner(System.in).nextLine();
    }
    private static void uploadDataFromFile(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("data.txt"));
            String line = bufferedReader.readLine();
            while (line!=null){
                if(line.isEmpty() || line.isBlank()){
                    break;
                }
                String[] data = line.split(" ");
                CACHE.add(new Phone(data[0],Double.valueOf(data[1]),Integer.valueOf(data[2])));
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }
    private static void showMobilesByPriceASC(){
        List<Phone> phones = CACHE.getPhones();
        Collections.sort(phones,Comparator.comparingDouble(Phone::getPrice));
        System.out.println(phones);
    }
    private static void showMobilesByPriceDESC(){
        List<Phone> phones = CACHE.getPhones();
        Collections.sort(phones,Comparator.comparingDouble(Phone::getPrice));
        Collections.reverse(phones);
        System.out.println(phones);
    }
    private static void saveDataFromCacheToFile(){
        for(Phone phone : CACHE.getPhones()){
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("data.txt")));
                bufferedWriter.write(phone.getModel() + " " + phone.getPrice() + " " + phone.getWeight());
                bufferedWriter.write("\n");
                bufferedWriter.flush();
            } catch (IOException e) {
                System.out.println("Ошибка создания файла");
            }

        }
    }
    private static void deleteMobileFromCacheByModel(){
        System.out.println("Введите название модели ");
        String model = readFromConsole();
        boolean isDeleted = CACHE.delete(model);
        if(isDeleted){
            System.out.println("Телефон успешно удален");
        }else {
            System.out.println("Не удалось удалить телефон, возможно такой модели не существует");
        }
    }
    private static void addNewMobileToCache(){
        System.out.println("Добавление нового телефона " + "\n" + "Введите модель телефона(должна быть уникальна)");
        String model = readFromConsole();
        boolean modelIsPresent = CACHE.getPhones().stream().filter(phone -> phone.getModel().equals(model)).count() > 0;
        if(modelIsPresent){
            System.out.println("Такая модель телефона уже присутствует в нашей коллекции ");
            return;
        }
        System.out.println("Введите стоимость телефона ");
        String price = readFromConsole();
        double doublePrice = 0.0;
        try{
            doublePrice = Double.valueOf(price);
        }catch (Throwable e){
            System.out.println("Ошибка, проверьте корректность введенных данных");
            return;
        }
        System.out.println("Введите вес телефона");
        String weight = readFromConsole();
        int intWeight = 0;
        try{
            intWeight = Integer.valueOf(weight);
        }catch (Throwable e){
            System.out.println("Ошбика, проверьте правильность введенных данных");
            return;
        }
        CACHE.add(new Phone(model,doublePrice,intWeight));
        System.out.println("Телефон успешно добавлен");
    }
    private static void showUserInterfaceData(){
        System.out.println("Выберите операцию: " + "\n" +
                "1. Посмотреть все телефоны в кеше" + "\n" +
                "2. Добавить новый телефон " + "\n" +
                "3. Удалить телефон по модели " + "\n" +
                "4. Cохранить данные из кеша в файл " + "\n" +
                "5. Отобразить мобильные телефоны по стоимости по возрастанию" + "\n" +
                "6. Отобразить мобильные телефоны по стоимости по убыванию " + "\n" +
                "7. Загрузить данные из файла в кеш " + "\n" +
                "0. Выход из программы" + "\n");
    }
}