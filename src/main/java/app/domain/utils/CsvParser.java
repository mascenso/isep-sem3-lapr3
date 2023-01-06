package app.domain.utils;


import app.domain.model.Domain;
import app.domain.model.Entity;
import app.domain.model.Route;
import app.domain.model.Spot;
import app.interfaces.GlobalConstants;
import app.enums.EntityType;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


/**
 * @authors Daniel Aires, Fernando Ribeiro, José Silva, Manuel Marques, Mariana Rocha
 */
public class CsvParser implements GlobalConstants {

    /***
     * Reads a CSV file and returns a list of String arrays
     * @param fileName the name of the file to be read
     * @return a list of String w/ the data from the file
     */

    public String getCSVdata(String fileName, String delimiter, Domain qcl) {
        BufferedReader br = null;
        FileInputStream fis = null;
        InputStreamReader isr = null;
        String line = "";
        String firstLine = "";

        try {

            fis = new FileInputStream(fileName);
            //isr = new InputStreamReader(fis, "Cp1252");
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);

            firstLine = br.readLine();
            firstLine = firstLine.replace("\uFEFF", "");
            if(firstLine.isEmpty()) {
                return "Empty File";
            }

            //Initialize a counter of number of lines read
            int linesRead = 0;

            //Initialize a counter of valid lines
            int loadedData = 0;

            //check if the file header is valid or not
            if(firstLine.equals(HEADER_CLIENTES_PRODUTORES) || firstLine.equals(HEADER_DISTANCES) ||
               firstLine.equals(HEADER_CABAZES)) {
                linesRead = linesRead + 1;

                //read all lines in file
                while((line = br.readLine()) != null) {
                    linesRead = linesRead + 1;

                    //Create auxiliar objects to not repeat objects in information structure
                    Entity entity_aux = new Entity();
                    Route route_aux = new Route();
                    Spot spot_aux = new Spot();

                    //Declare objects
                    Entity entity;
                    Route route;
                    Spot spot;

                    //remove all " in line
                    line = line.replaceAll("^\"|\"$", "");

                    //split information by delimiter
                    String[] data = line.split(delimiter);

                    //remove the remaining "
                    for(int i = 0; i < data.length; i++) {
                        data[i] = data[i].replace("\"", "");
                    }

                    //check if the number of data is the same of the header
                    if(data.length == firstLine.split(delimiter).length) {

                        //Different behaviour
                        switch(firstLine) {
                            case GlobalConstants.HEADER_CLIENTES_PRODUTORES:
                                if(dataValidationsClientesProdutores(data)) {
                                    loadedData = loadedData + 1;
                                    // if all data is valid the objects are created and added to Graph
                                    entity_aux.setiD(data[CLIENTES_PRODUTORES]);
                                    //C: Client, P: Producer, E: Company
                                    Character type = data[CLIENTES_PRODUTORES].charAt(0);
                                    if(type.equals('C')) {
                                        entity_aux.setEttyType(EntityType.CLIENT);
                                    } else if(type.equals('P')) {
                                        entity_aux.setEttyType(EntityType.PRODUCER);
                                    } else if(type.equals('E')) {
                                        entity_aux.setEttyType(EntityType.EMPRESA);
                                    }

                                    spot_aux.setSpotID(data[LOC_ID]);
                                    spot_aux.setLng(Double.parseDouble(data[LONGITUDE]));
                                    spot_aux.setLat(Double.parseDouble(data[LATITUDE]));
                                    spot_aux.setEntity(entity_aux);

                                    if(!qcl.getSpotsNet().getSpots().validVertex(spot_aux)) {
                                        entity = new Entity(entity_aux);
                                        spot_aux.setEntity(entity);
                                        spot = new Spot(spot_aux);
                                        qcl.getSpotsNet().addSpot(spot);
                                    }
                                }
                                break;
                            case GlobalConstants.HEADER_DISTANCES:
                                //update data already loaded
                                if(dataValidationsDistancias(data)) {
                                    // if all data is valid the objects are created and added to Graph
                                    route_aux.setMeters(Integer.parseInt(data[DISTANCE]));

                                    if(qcl.getSpotsNet().getSpotStore().get(data[LOC_ID_I]) != null &&
                                       qcl.getSpotsNet().getSpotStore().get(data[LOC_ID_F]) != null) {
                                        route = new Route(route_aux);
                                        qcl.getSpotsNet()
                                           .addRoute(route, qcl.getSpotsNet().getSpotStore().get(data[LOC_ID_I]),
                                                     qcl.getSpotsNet().getSpotStore().get(data[LOC_ID_F]));
                                    }
                                }
                                break;
                            case GlobalConstants.HEADER_CABAZES:

                                //todo:sprint2
                                if(dataValidationsCabazes(data)) {

                                }
                                break;
                        }
                    }
                }
                System.out.println(String.format("%d lines read\n%d loaded", linesRead, loadedData));
                return null;

            } else {
                return "Invalid Header!";
            }
        }
        catch(Exception e) {
            return e.getMessage();
        }
        finally {
            if(br != null) {
                try {
                    fis.close();
                    isr.close();
                    br.close();
                }
                catch(IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
    }

    /***
     *
     * @param data is an array which contains the information that is read on the file's line
     * @return true if data is valid
     */
    private static boolean dataValidationsClientesProdutores(String[] data) {

        if(Validations.validLocID(data[LOC_ID]) && Validations.validLatitude(data[LATITUDE]) &&
           Validations.validLongitude(data[LONGITUDE]) && Validations.validEntity(data[CLIENTES_PRODUTORES])) {
            return true;
        }
        return false;
    }

    /***
     *
     * @param data is an array which contains the information that is read on the file's line
     * @return true if data is valid
     */
    private static boolean dataValidationsDistancias(String[] data) {

        if(Validations.validLocID(data[LOC_ID_I]) && Validations.validLocID(data[LOC_ID_F]) &&
           Validations.validDistance(data[DISTANCE])) {
            return true;
        }
        return false;
    }

    /***
     *
     * @param data is an array which contains the information that is read on the file's line
     * @return true if data is valid
     */
    private static boolean dataValidationsCabazes(String[] data) {
        return false;
    }


}



