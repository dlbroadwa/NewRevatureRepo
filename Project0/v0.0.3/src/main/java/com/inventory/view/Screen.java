package com.inventory.view;

import com.inventory.controller.system.ConsoleIn;
import com.inventory.model.SQL;

public interface Screen {
    ConsoleIn consoleIn = ConsoleIn.getInstance();
    SQL getNew();
}
