package com.ex.view;

import com.ex.app.Application;
import com.ex.controller.system.ConsoleIn;
import com.ex.controller.system.ConsoleOut;
import com.ex.app.InventoryAssistant;
import com.ex.model.Item;

public class NewItem {
    public NewItem(Application app){
        getID();
        getName();
        getValue();
        getShelfLife();
        ((InventoryAssistant) app).items.add(new Item(id, name, value, shelfLife));
    }

    private static final String YOU_ENT = "You entered: ";
    private static final String NUM_REQ = "That wasn't a number. Please enter just a number.";
    private static final String INT_REQ = "The number that you entered may have been too large, had decimals, " +
            "or some other problem. Please try again";
    private static final String TRY_AGN = "Try again.";
    private int id;
    private String name;
    private double value;
    private short shelfLife;

    public void getInput(String beforeMsg, @org.jetbrains.annotations.NotNull Runnable inputFunction){
        ConsoleOut.print(beforeMsg);
        inputFunction.run();
    }
    private void getName(){
        do{
            try{
                getInput("Name: ", () -> name = ConsoleIn.next());
                ConsoleOut.println(YOU_ENT + name + ".");
                break;
            } catch (Exception e){
                e.printStackTrace(ConsoleOut.err);
                ConsoleOut.println(TRY_AGN);
            }
            ConsoleIn.nextLine();
        } while(true);
    }
    private void getID(){
        do{
            try{
                getInput("ID: ", () -> id = ConsoleIn.nextInt());
                ConsoleOut.println(YOU_ENT + id + ".");
                break;
            } catch (java.util.InputMismatchException e) {
                ConsoleOut.println(INT_REQ);
            } catch (Exception e){
                e.printStackTrace(ConsoleOut.err);
                ConsoleOut.println(TRY_AGN);
            }
            ConsoleIn.nextLine();
        } while(true);
    }
    private void getValue(){
        do{
            try{
                getInput("Value: ", () -> value = ConsoleIn.nextDouble());
                ConsoleOut.println(YOU_ENT + value + ".");
                break;
            } catch (java.util.InputMismatchException e) {
                ConsoleOut.println(NUM_REQ);
            } catch (Exception e){
                e.printStackTrace(ConsoleOut.err);
                ConsoleOut.println(TRY_AGN);
            }
            ConsoleIn.nextLine();
        } while(true);
    }
    private void getShelfLife(){
        do{
            try{
                getInput("Shelf life (days): ", () -> shelfLife = ConsoleIn.nextShort());
                ConsoleOut.println(YOU_ENT + shelfLife + ".");
                break;
            } catch (java.util.InputMismatchException e) {
                ConsoleOut.println(INT_REQ);
            } catch (Exception e){
                e.printStackTrace(ConsoleOut.err);
                ConsoleOut.println(TRY_AGN);
            }
            ConsoleIn.nextLine();
        } while(true);
    }
}
