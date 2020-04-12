package com.ex.screens;

import com.ex.app.Application;
import com.ex.services.io.ConsoleIn;
import com.ex.services.io.ConsoleOut;
import com.ex.app.InventoryAssistant;
import com.ex.types.Item;

public class NewItemScreen {
    public NewItemScreen(Application app){
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
