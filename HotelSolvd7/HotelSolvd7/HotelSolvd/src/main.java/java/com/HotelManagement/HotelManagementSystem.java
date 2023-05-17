package com.HotelManagement;

import enums.Division;
import enums.HotelGreeting;
import enums.MenuItems;
import enums.TrafficLight;
import exceptions.CheckCorrectNameFormatException;
import exceptions.CheckCorrectYearFormatException;
import exceptions.CheckDuplicateEmployeeIdException;
import exceptions.CheckHotelExistsException;
import lamdas.GetNameLambda;

import java.io.IOException;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static java.lang.System.out;

public abstract class HotelManagementSystem {
    //private static final Logger logger = Logger.getLogger(HotelManagementSystem.class);
    public static final String FILE_NAME = "target/log.log";

    //public ArrayList<File> file = new ArrayList<File>((Collection<? extends File>) fileName);

    public static void main(String[] args) throws CheckDuplicateEmployeeIdException, CheckCorrectYearFormatException,
            CheckHotelExistsException, CheckCorrectNameFormatException, IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        //Logs to "log4j.properties file.
        //logger.info("Hello world.");
        //logger.info("What a beautiful day.");

        //--Reflection--
        //Assigning the class "Card" to a reflection class named "cardClass"
        Class<?> cardClass = Class.forName("com.HotelManagement.Card");
        out.println("Reflection of Card class: " + cardClass);
        //Getting the super class of Card
        Class cardSuperClass = Card.class.getSuperclass();
        out.println("The Super class of Card is: " + cardSuperClass);
        //Printing the Card class
        Class classObj = Card.class;
        out.println("Printing Card.class: \n" + classObj);
        out.println("-----------------------------------------");

        //Creating a new Customer object and call fields, modifiers, and methods by reflection
        Customer customer1 = new Customer(1, "Lynn", 1, 1, 1);
        Class customerObj = customer1.getClass();
        out.println("Printing customer1.getClass(): \n" + customerObj);
        out.println("-----------------------------------------");
        //Calling the method getCustomerName
        String name = customer1.getCustomerName();
        out.println("Getting customer name = " + name);
        out.println("-----------------------------------------");

        //Getting customer1 modifiers
        int modifier = customer1.getClass().getModifiers();
        out.println("Original Modifier: " + modifier);
        String modToString = Modifier.toString(modifier);

        //Getting the string version of modifier
        out.println("Modifier to String: " + modToString);
        out.println("-----------------------------------------");

        //Get all declared methods of customerObj
        Method[] methods = customerObj.getDeclaredMethods();
        for (Method m : methods) {
            //Get name of methods
            out.println("Method Name: " + m.getName());
            //get the access modifier of methods
            modifier = m.getModifiers();
            out.println("Modifier: " + Modifier.toString(modifier));
            //Get the return types of method
            out.println("Return types: " + m.getReturnType());
            out.println("-----------------------------------------");
        }

        //Access the private field customerName
        Field field = customer1.getClass().getDeclaredField("customerName");

        //Enable changes to the private field
        field.setAccessible(true);

        //Change the value of the name
        out.println("Original customer name = " + name);
        field.set(customer1, "Julie");
        //Get the value of customerName
        String nameValue = (String) field.get(customer1);
        out.println("Field value with changed name: " + nameValue);

        //Get the access modifier of the field breed, then change it to String form
        modifier = field.getModifiers();
        String modifier1 = Modifier.toString(modifier);
        out.println("customerName modifier: " + modifier1);
        out.println("-----------------------------------------");

        //Get the constructor using reflection
        Constructor[] constructors = customer1.getClass().getDeclaredConstructors();

        for (Constructor constructor : constructors) {
            out.println("Constructor name: " + constructor.getName());
            modifier = constructor.getModifiers();
            modifier1 = Modifier.toString(modifier);
            out.println("Constructor modifier: " + modifier1);
            out.println("Parameters: " + Arrays.toString(constructor.getParameters()));
            out.println("Parameters: " + constructor.getParameterCount());
            out.println("-----------------------------------------");
        }

        //--Setting enums--
        //Creating a traffic light object
        PracticeTrafficLightEnum light1 = new PracticeTrafficLightEnum(1, TrafficLight.GREEN, TrafficLight.GREEN.getLightColorMeaning());
        out.println(light1);

        //MenuItems enum used with Order object
        Order order = new Order(1, 1, MenuItems.PASTA.getFoodType(), 16.99, 1, 1, 1);
        // out.println(order);

        //HotelGreeting enum used with Hotel object
        Hotel hotel = new Hotel(1, HotelGreeting.HILTON.getGreeting(), 100);
        //out.println(hotel);

        HotelEmployee<HouseCleaner> emp1 = new HotelEmployee<>(101, 1, "Jessica", "Marshal", 30000, 2010, Division.HOUSE_CLEANER);
        HotelEmployee<Manager> emp2 = new HotelEmployee<>(101, 1, "John", "Smith", 60000, 2022, Division.MANAGEMENT);
        HotelEmployee<Receptionist> emp3 = new HotelEmployee<>(103, 1, "Sally", "Johnson", 30000, 2020, Division.RECEPTIONIST);
        HotelEmployee.allHotelEmployees.add(emp2);
        HotelEmployee.allHotelEmployees.add(emp3);
        HotelEmployee.allHotelEmployees.add(emp1);

        //Calling custom lambda that prints selected employee's first name.
        GetNameLambda firstName = (allHotelEmployees) -> allHotelEmployees.getHotelEmployeeFirstName();
        //out.println(firstName.nameLambda(emp1));

        //CustomLambda that sorts Hotel names in alphabetical order.
        List<Hotel> h = new ArrayList<>();
        h.add(new Hotel(1, "Hilton", 200));
        h.add(new Hotel(0, "Super8", 50));
        h.add(new Hotel(3, "Hampton Inn", 150));
        h.sort((Hotel hotel1, Hotel hotel2) -> hotel1.getHotelName().compareTo(hotel2.getHotelName()));
        // out.println("Hotels in alphabetical order: " + h);

        //Custom lambda that finds the hotel with the max number of employees.
        Hotel maxNumEmployees = h.stream().max(Comparator.comparing(v -> v.getNumberOfEmployees())).get();
        // out.println("Hotel with the greatest amount of employees: " + maxNumEmployees);

        //Calling Predicate lambda that filters hotel employees that were hired before 2015.
        Predicate<HotelEmployee> filterByYearHired = (hotelEmployee) -> hotelEmployee.getYearHired() < 2015;
        // out.println(filterByYearHired.test(emp1));


        // public static final String INPUT_FILE = "src/main/resources/repeating_words.txt";
        //File file = new File("src/main/resources/repeating_words.txt");
         /*  File fileName = new File("repeating_words.txt");
        Scanner scanFile = new Scanner(fileName);
        while( scanFile.hasNextLine()) {
            String readingTheFile = scanFile.nextLine();
            List<String> readingTheFileToList = new ArrayList<>(Collections.singleton(readingTheFile));
               for(int i = 0; i < readingTheFile.length(); i++) {
                   if (readingTheFileToList.contains(i)) {
                       readingTheFileToList.remove(i);
                       Print.print(readingTheFileToList);
                   }
               }
        }*/

        //Throws custom CheckCorrectNameFormatException
        /*try {
            HotelEmployee<HouseCleaner> emp1 = new HotelEmployee<>(101, 1, "Jessica", "Marshal", 30000, 2010, Division.HOUSE_CLEANER);
            HotelEmployee.allHotelEmployees.add(emp1);
            emp1.setHotelEmployeeFirstName("Jes ica");
        } catch (CheckCorrectNameFormatException e) {
            e.printStackTrace();
        }*/

        //Throws custom CheckCorrectYearException
       /* try {
            HotelEmployee<HouseCleaner> emp1 = new HotelEmployee<>(101, 1, "Jessica", "Marshal", 30000, 2010, Division.HOUSE_CLEANER);
            HotelEmployee.allHotelEmployees.add(emp1);
            emp1.setYearHired(11111);
        } catch (CheckCorrectYearFormatException e) {
            e.printStackTrace();
        }*/

        //Throws custom DuplicateIdException
        /*try {

            HotelEmployee<HouseCleaner> emp1 = new HotelEmployee<>(101, 1, "Jessica", "Marshal", 30000, 2010, Division.HOUSE_CLEANER);
            HotelEmployee<Manager> emp2 = new HotelEmployee<>(101, 1, "John", "Smith", 60000, 2022, Division.MANAGEMENT);
            HotelEmployee<Receptionist> emp3 = new HotelEmployee<>(103, 1, "Sally", "Johnson", 30000, 2020, Division.RECEPTIONIST);
            HotelEmployee.allHotelEmployees.add(emp2);
            HotelEmployee.allHotelEmployees.add(emp3);
            HotelEmployee.allHotelEmployees.add(emp1);
            emp1.setHotelEmployeeId(101);
        } catch (DuplicateIdException e) {
            e.printStackTrace();
        }*/

        //Throws CheckHotelExistsException
        /*try {
         Hotel hotel1 = new Hotel(1, "Hilton", 0);
         Hotel hotel2 = new Hotel(0, "Super8", 0);
         Hotel hotel3 = new Hotel(3, "Hilton", 0);
         Hotel.allHotels.add(hotel1);
         Hotel.allHotels.add(hotel2);
         Hotel.allHotels.add(hotel3);
         hotel3.setHotelName("Hilton");

     }catch (CheckHotelExistsException e) {
         e.printStackTrace();
     }*/

        //Throws CheckHotelIdException
        /*try {
            Hotel hotel4 = new Hotel(1, "Super8", 0);
            hotel4.setHotelId(0);
        } catch (CheckHotelIdException e) {
            e.printStackTrace();
        }*/

        /*File fileName = new File("repeating_words.txt");
        ArrayList<Scanner> scanFile = new ArrayList<Scanner>((Collection<? extends Scanner>) fileName);
        while(int i = 0; i < scanFile; i++) {
            String readingTheFile = scanFile.nextLine();
            for(String f : readingTheFile);

            if(!readingTheFile.contains())
        }*/


        // Print.print(Hotel.allHotels);


//Working with the tables LinkedList.
        /*Table table1 = new Table(1, 1, 1);
        Table table2 = new Table(2, 2, 2);
        Table table3 = new Table(3, 3, 3);
        Table.tables.add(table1);
        Table.tables.add(table2);
        Table.tables.add(table3);
        System.out.println(Table.tables);
        Table.tables.remove(1);
        System.out.println(Table.tables);*/


//Working with Menu HashMap. Assigning price to a menu item.
        Menu.menuItemsWithPrice.put("Burger", 6.99);
        Menu.menuItemsWithPrice.put("PizzaSlice", 4.50);
        // System.out.println(Menu.menuItemsWithPrice);

        Bill b = new Bill(1, 1, 25.99);
        //System.out.println(b.calculateBill(b.getTotalBill()));

        /*logger.info("Hello world.");
        logger.info("What a beautiful day.");*/

        /*Create hotel employees of Types HouseCleaner, Manager, and Receptionist.
         * Add them all to List allHotelEmployees.
         */
        /*try {
            HotelEmployee<HouseCleaner> emp1 = new HotelEmployee<>(101, 1, "Jessica", "Marshal", 30000, 2010, Division.HOUSE_CLEANER);
            HotelEmployee<Manager> emp2 = new HotelEmployee<>(101, 1, "John", "Smith", 60000, 2022, Division.MANAGEMENT);
            HotelEmployee<Receptionist> emp3 = new HotelEmployee<>(103, 1, "Sally", "Johnson", 30000, 2020, Division.RECEPTIONIST);
            HotelEmployee.allHotelEmployees.add(emp2);
            HotelEmployee.allHotelEmployees.add(emp3);
            HotelEmployee.allHotelEmployees.add(emp1);
            emp1.setHotelEmployeeId(101);
        }*/


        // Calls the print method from the Print interface.
        //Print.print(HotelEmployee.allHotelEmployees);

        //Adds the 2 types of RestaurantEmployee (Chef and Server) to the list restaurantEmployees.
        RestaurantEmployee<Chef> restaurantEmployee1 = new RestaurantEmployee<>(1, "David", "Smith", 60000, 2000);
        RestaurantEmployee<Server> restaurantEmployee2 = new RestaurantEmployee<>(2, "Roger", "Brown", 30000, 2020);
        RestaurantEmployee.allRestaurantEmployees.add(restaurantEmployee1);
        RestaurantEmployee.allRestaurantEmployees.add(restaurantEmployee2);
        //Print.print(RestaurantEmployee.allRestaurantEmployees);

        CustomLinkedList<Table> listOfTables = new CustomLinkedList<>();
        listOfTables.insertFirst(new Table(1, 1, 1));
        listOfTables.insertLast(new Table(2, 2, 2));
        //System.out.println(listOfTables.toString());

        Bill bill = new Bill(1, 1, 35.99);
        //Get salary increase for emp1.
        //System.out.println(emp1.getSalaryIncrease(emp1.getSalary()));

        //Calculate the bill with tax.
        //System.out.println(bill.calculateBill(bill.getTotalBill()));

        //HouseCleaner emp1 = new HouseCleaner(101, 1, "Jessica", "Marshal", 30000, 2010, Division.HOUSE_CLEANER);
        //HotelEmployee.allHotelEmployees.add(emp1);
        //Hotel.addEmployee();
        //Manager emp2 = new Manager(102, 1, "John", "Smith", 60000, 2022, Division.MANAGEMENT);
        //HotelEmployee.allHotelEmployees.add(emp2);
        // System.out.println(HotelEmployee.allHotelEmployees);

    }
}


