import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import UI.Menu;
import UI.MenuSelect;
import gameaccounts.Account;

public class Main {

	public static void main(String[] args) {

		MenuSelect menuSelect = new MenuSelect();
		System.out.println("Welcome");
		//Recreates accountList objects from save file
		while(!menuSelect.exitCondition) {
			//check credentials
			menuSelect.entryPage();
			while(!menuSelect.exitCondition2){
				menuSelect.choiceSelection();
			}

			//if (finalIndex==100) {
			//	optimize();
			//}
		}
	}
}
