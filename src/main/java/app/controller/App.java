package app.controller;
import app.domain.model.Domain;
import app.domain.model.UserRole;
import app.interfaces.CONSTANT;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Adapted from pt.isep.lei.esoft / Paulo Maio <pam@isep.ipp.pt>
 */
public class App {

    private Domain domain;

    private App()
    {
        Properties props = getProperties();
        this.domain = new Domain(props.getProperty(CONSTANT.PARAMS_COMPANY_DESIGNATION));
        bootstrap();
    }

    public Domain getCompany()
    {
        return this.domain;
    }

    private Properties getProperties()
    {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(CONSTANT.PARAMS_COMPANY_DESIGNATION, "Biological Farms");


        // Read configured values
        try
        {
            InputStream in = new FileInputStream(CONSTANT.PARAMS_FILENAME);
            props.load(in);
            in.close();
        }
        catch(IOException ex)
        {

        }
        return props;
    }


    private void bootstrap()
    {
        this.domain.getUserRoleStore().add(new UserRole("001", "Manager"));
        this.domain.getUserRoleStore().add(new UserRole("002", "Farmer"));
    }

    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static App singleton = null;
    public static App getInstance()
    {
        if(singleton == null)
        {
            synchronized(App.class)
            {
                singleton = new App();
            }
        }
        return singleton;
    }
}
