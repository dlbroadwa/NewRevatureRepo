package com.company.consoleMenus;

import com.company.application.ATMApplication;

/***
 * The Screen interface is to provide a run method to be executed by the application.
 *
 * @author Shawyn Kane
 */
public interface Screen {
    /***
     * This method is to be implemented to provide the main execution of the program.
     * This method is mostly to contain the code that will make sure that data gets passed around correctly and make calls to other methods for getting user input, executing business logic, and etc.
     * @param app
     * @return
     */
    public Screen run(ATMApplication app);
}
