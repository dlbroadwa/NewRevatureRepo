package com.ex.views;

import com.ex.apps.Application;
import com.ex.controllers.io.ConsoleIn;
import com.ex.controllers.io.ConsoleOut;
import com.ex.apps.InventoryAssistant;
import com.ex.models.Item;

public class NewItem {
    public NewItem(Application app){
        ((InventoryAssistant) app).items.add(new Item(getName(), getID()));
    }

    private String getName(){
        do{
            try{
                ConsoleOut.print("Name: ");
                String name = ConsoleIn.next();
                ConsoleOut.println("You entered: " + name + ".");
                return name;
            } catch (java.util.NoSuchElementException e) {
                ConsoleOut.println("Try again.");
            } catch (Exception e){
                e.printStackTrace(ConsoleOut.err);
            }
            ConsoleIn.nextLine();
        } while(true);
    }

    private int getID(){
        do{
            try{
                ConsoleOut.print("ID number: ");
                int num = ConsoleIn.nextInt();
                ConsoleOut.println("You entered " + num + ".");
                return num;
            } catch (java.util.InputMismatchException e) {
                ConsoleOut.println("That wasn't a number. Please enter just a number.");
            } catch (Exception e){
                e.printStackTrace(ConsoleOut.err);
            }
            ConsoleIn.nextLine();
        } while(true);
    }
}
