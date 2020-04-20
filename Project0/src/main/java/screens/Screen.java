package screens;

import app.Application;

/**
 *  Project 0:<br>
 * <br>
 *  The Screen interface serves as an interface for actions available within an Application in terms of functionality,
 *    options, interactions and so on.
 *  For the context of LibraryApplication, each Screen subclass instance will represent available options in that menu.
 *    As of the creation of this interface, a Screen subclass will exist for: searching for an Item, checking an Item
 *    in, checking an Item out, adding an Item into the system Catalog and removing an Item from the system catalog.
 *
 *  <br> <br>
 *  Created: <br>
 *     18 April 2020, Barthelemy Martinon<br>
 *     With assistance from: August Duet<br>
 *  Modifications: <br>
 *     18 April 2020, Barthelemy Martinon,    Created class.
 *     										  Added signatures for doScreen method.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 18 April 2020
 */
public interface Screen {
    Screen doScreen(Application app);
}
