/**
 * Screen interface is how the View in MVC manifests.
 * Instead of Renders we print to the command line.
 * Instead of Routing we return other Screen Objects.
 */
package com.williamchung.project0.screens;

import com.williamchung.project0.application.Application;

public interface Screen {
  Screen doScreen(Application app);
}