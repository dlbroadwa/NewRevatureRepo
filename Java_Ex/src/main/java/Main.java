import UI.MenuSelect;

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
