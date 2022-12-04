package app.domain.utils;

import app.interfaces.CONSTANT;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @authors Daniel Aires, Fernando Ribeiro, José Silva, Manuel Marques, Mariana Rocha
 */
public abstract class Configurations implements CONSTANT {

    public static String getFile(String type)
            throws IOException {
        Properties properties;
        InputStream in = new FileInputStream(new File(PARAMS_FILENAME));
        properties = new Properties();
        properties.load(in);
        String file=null;
        if(type.equals(FILE_DISTANCES)){
            file=properties.getProperty("fileNameDistancias");
        }else if(type.equals(FILE_CLIENTES_PRODUTORES)){
            file=properties.getProperty("fileNameClientesProdutores");
        }else if(type.equals(FILE_CABAZES)){
            file=properties.getProperty("fileNameCabazes");
        }
        return file;
    }

    public static String getDelimiter(String type)
            throws IOException  {
        Properties properties;
        InputStream in = new FileInputStream(new File(PARAMS_FILENAME));
        properties = new Properties();
        properties.load(in);
        String delimiter=null;

        if(type.equals(DELIMITERC)){
            delimiter=properties.getProperty("delimiterComma");
        }else if(type.equals(DELIMITER)){
            delimiter=properties.getProperty("delimiter");
        }

        return delimiter;
    }


}
