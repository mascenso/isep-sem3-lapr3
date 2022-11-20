package app.ui;
import app.ui.console.MainMenuUI;

/**
 * Adapted from pt.isep.lei.esoft.auth / Paulo Maio <pam@isep.ipp.pt>
 */
public class Main {

    public static void main(String[] args)
    {
        try
        {
            MainMenuUI menu = new MainMenuUI();

            menu.run();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
