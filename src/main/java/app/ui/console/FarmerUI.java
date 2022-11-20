package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class FarmerUI implements Runnable{
    public FarmerUI()
    {
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Option A ", new ShowTextUI("You have chosen Option A.")));
        options.add(new MenuItem("Option B ", new ShowTextUI("You have chosen Option B.")));
        options.add(new MenuItem("Option C ", new ShowTextUI("You have chosen Option C.")));

        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nFarmer Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}
