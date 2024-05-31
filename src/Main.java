import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main { 
    static Log myLog;
    static {
        try {
            myLog = new Log("logger.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        myLog.logger.info("Пользователь в главном меню");
        System.out.println("""
        Добро пожаловать в меню управления замком!
        Выберите отдел для управления:
        ================================================================
        1. Жители
        2. Армия и война
        3. Казна
        4. Отношения
        5. Выйти
        """);
        try  {
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    myLog.logger.info("Выбрано меню народа");
                    peopleMenu();
                    break;
                case 2:
                    myLog.logger.info("Выбрано меню армии");
                    armyMenu();
                    break;
                case 3:
                    myLog.logger.info("Выбрано меню казны");
                    treasuryMenu();
                    break;
                case 4:
                    myLog.logger.info("Выбрано меню отношений");
                    relationsMenu();
                    break;
                case 5:
                    myLog.logger.info("Выход из программы");
                    System.exit(0);
                    break;
                default:
                    myLog.logger.warning("Некорректный ввод");
                    System.out.println("Некорректный ввод");
                    break;
            };
        } 
        catch (Exception e)  {
            myLog.logger.severe(e.toString());
        }
        sc.close();
    }
    public static void peopleMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("""
        Выберите действие:
        ================================================================
        1. Добавить жителей
        2. Казнить жителей
        3. Выйти
        """);
        int num;
        try{
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Введите количество: ");
                    num = sc.nextInt();
                    new People().addPopulation(num);
                    break;
                case 2:
                    System.out.println("Введите количество: ");
                    num = sc.nextInt();
                    new People().execution(num);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Некорректный ввод");
                    break;
            }
        }
        catch(Exception e){
            myLog.logger.severe(e.toString());
        }
    }
    public static void armyMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("""
        Выберите действие:
        ================================================================
        1. Объявить войну другому королевству
        2. Капитулировать
        3. Сформировать армию
        4. Расформировать армию
        5. Выйти
        """);
        int num;
        try
        {int choice = sc.nextInt();
        switch(choice){
            case 1:
                new Army().declareWar();
                break;
            case 2:
                new Army().capitulation();
                break;
            case 3:
                System.out.println("Введите количество: ");
                num = sc.nextInt();
                new Army().addArmy(num);
                break;
            case 4:
                System.out.println("Введите количество: ");
                num = sc.nextInt();
                new Army().removeArmy(num);
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Некорректный ввод");
                break;
        }}
        catch (Exception e){
            myLog.logger.severe(e.toString());
        }
    }
    public static void treasuryMenu(){
        Scanner sc = new Scanner(System.in);
        int num;
        System.out.println("""
        Выберите действие:
        ================================================================
        1. Добавить деньги
        2. Потратить деньги
        3. Выйти
        """);
        try
        {int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Введите количество: ");
                num = sc.nextInt();
                new Treasury().addTreasury(num);
            case 2:
                System.out.println("Введите количество: ");
                num = sc.nextInt();
                new Treasury().removeTreasure(num);
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Некорректный ввод");
                break;
        }}
        catch (Exception e){
            myLog.logger.severe(e.toString());
        }
    }
    public static void relationsMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("""
        Выберите действие:
        ================================================================
        1. Установить отношения
        2. Отменить отношения
        3. Выйти
        """);
        try
        {int choice = sc.nextInt();
        switch (choice){
            case 1:
                new Relations().addTorgRelations();
                break;
            case 2:
                new Relations().removeTorgRelations();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Некорректный ввод");
                break;
        }}
        catch (Exception e){
            myLog.logger.severe(e.toString());
        }
    }
}

class MedievalCastle{
    protected static ArrayList<String> _relations = new ArrayList<>();
    protected static int _countPopulation = 0;
    protected static float _treasury = 0;
    protected static int _countArmy = 0;
}

class People extends MedievalCastle {
    static Log myLog;
    static {
        try {
            myLog = new Log("logger.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPopulation(int population) {
        myLog.logger.info("Выбрано добавление жителей");
        if (population < 0) {
            System.out.println("Число не должно быть отрицательным");
        }
        else{
            _countPopulation += population;
            myLog.logger.info("Добавлено " + population + " жителей");
            
            System.out.println("В замке " + _countPopulation + " жителей");
        }
        Main.main(null);
    }

    public void execution(int count){
        myLog.logger.info("Выбрана казнь");
        if (count < 0){
            System.out.println("Число не должно быть отрицательным");
            myLog.logger.info("Введено отрицательное число");
        }
        else if(count > _countPopulation) {
            System.out.println("Нельзя казнить людей больше чем есть");
            myLog.logger.info("Количество жителей меньше введенного числа");
        }
        else{
            _countPopulation -= count;
            myLog.logger.info(String.format("Казнено %s жителей", count));
            System.out.println("Казьнено " + count + " людей\nОсталось " + _countPopulation + " людей");
        }
        Main.main(null);
    }
}

class Army extends MedievalCastle {
    static Log myLog;
    static {
        try {
            myLog = new Log("logger.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void declareWar(){
        Random rand = new Random();
        boolean victory = rand.nextBoolean();
        myLog.logger.info("Объявлена война");
        if(victory){
            int death = rand.nextInt(_countArmy);
            _countArmy -= death;
            System.out.println("Ваше королевство победило\nПотери составляют " + death + " человек");
            myLog.logger.info("Война выиграна. Потери составляют " + death + " человек");
        }
        else{
            _countArmy = 0;
            System.out.println("Ваше королевство проиграло\nВся армия уничтожена");
            myLog.logger.info("Война проиграна. Вся армия уничтожена");
        }
        Main.main(null);
    }

    public void addArmy(int count) {
        if(count > _countPopulation) {
            System.out.println("У вас недостаточно жителей");
            myLog.logger.info("Недостаточно жителей");
        }
        else if(count > 0) {
            _countArmy += count;
            _countPopulation -= count;

            myLog.logger.info("Добавлено  "  + count  +  " человек в армию");
            
            System.out.println("Численность армии " + _countArmy + " человек");
        }
        Main.main(null);
    }
    
    public void removeArmy(int count) {
        if (count < 0) {
            System.out.println("Число должно быть больше нуля");
        } else if (count > _countArmy) {
            System.out.println("Ваша армия меньше");
        }
        else{
            _countArmy -= count;
            _countPopulation += count;
            myLog.logger.info("Удалено  "  + count  +  " человек в армию");
            System.out.println("Численность армии " + _countArmy + " человек");
        }
        Main.main(null);
    }

    public void capitulation(){
        _countPopulation = 0;
        _countArmy = 0;
        _treasury = 0;
        System.out.println("Вы капитулировали :(");
        myLog.logger.info("Капитуляция.");
    }
}

class Treasury extends MedievalCastle {
    static Log myLog;
    static {
        try {
            myLog = new Log("logger.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addTreasury(float money) {
        if (money < 0){
            System.out.println("Число не должно быть отрицательным");
        }
        else {
            _treasury += money;
    
            myLog.logger.info("Добавлено  "  + money  +  " золотых");
            System.out.println("В казне " + _treasury + " золота");
        }
        Main.main(null);
    }

    public void removeTreasure(float money){
        if (money < 0){
            System.out.println("Число не должно быть отрицательным");
        }
        else if(money > _treasury) {
            System.out.println("В казне нет столько золота");
        }
        else{
            _treasury -= money;
            myLog.logger.info("Удалено  "  + money  +  " золотых");
            System.out.println("В казне осталось " + _treasury + " золота");
        }
        Main.main(null);
    }
}

class Relations extends MedievalCastle {
    static Log myLog;
    static {
        try {
            myLog = new Log("logger.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addTorgRelations(){
        System.out.println("С каким королевством будем торговать?");
        Scanner sc = new Scanner(System.in);
        String rel = sc.nextLine();
        _relations.add(rel);
        myLog.logger.info("Добавлены отношения с королевством " + rel);
        sc.close();
        Main.main(null);
    }
    
    public static void removeTorgRelations(){
        if(_relations.isEmpty()) {
            System.out.println("Вы и так ни с кем не торгуете");
        }
        
        Scanner sc = new Scanner(System.in);
        System.out.println("С каким королевством хотите разорвать торговые отношения");
        for (int i = 1; i <= _relations.size(); i++) {
            System.out.println(i + ". " + _relations.get(i-1));
        }
        int index = sc.nextInt();
        myLog.logger.info("Убраны отношения с королевством " + _relations.get(index-1));
        _relations.remove(index-1);
        sc.close();
        Main.main(null);
    }
}