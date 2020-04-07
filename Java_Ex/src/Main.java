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
	static Account[] accountList = new Account[100];
	static int finalIndex = 0;
	static boolean exitCondition=false;

	public static void main(String[] args) {

		Menu menu = new Menu();
		MenuSelect menuSelect = new MenuSelect(100);
		System.out.println("Hello Administrator");
		//Recreates accountList objects from save file
		int choice=0;
		Scanner in = new Scanner(System.in);
		while(!menuSelect.exitCondition) {
			menu.textOptions();
			//overload textOptions so that users are showed different options than administers
			choice=in.nextInt();
			
			//overload choiceSelection so that users are led to different cases than administers
			menuSelect.choiceSelection();
			//if (finalIndex==100) {
			//	optimize();
			//}
		}
	}
}
