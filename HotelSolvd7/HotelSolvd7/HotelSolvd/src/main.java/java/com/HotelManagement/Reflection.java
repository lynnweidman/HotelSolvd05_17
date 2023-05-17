package com.HotelManagement;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static java.lang.System.out;

public class Reflection {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

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
    }
}
