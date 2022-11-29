package app.externalModule;

import app.domain.interfaces.CONSTANT;
import app.domain.model.Domain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CsvParserEdges {

    //TODO : Improve
    public String getCSVdata(String fileName, String del, Domain qcl) {
        BufferedReader br = null;
        String line = "";
        String firstLine = "";

        try {

            FileInputStream fis = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);

            //br = new BufferedReader(new FileReader(fileName));
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
            if(firstLine.equals(CONSTANT.HEADER_DISTANCES)) {
                linesRead = linesRead + 1;

                //read all lines in file
                while((line = br.readLine()) != null) {
                    linesRead = linesRead + 1;

                    //remove all " in line
                    line = line.replaceAll("^\"|\"$", "");

                    //split information by delimiter
                    String delimiter = del;
                    String[] data = line.split(delimiter);

                    //remove the remaining "
                    for(int i = 0; i < data.length; i++) {
                        data[i] = data[i].replace("\"", "");
                    }
                    if(data.length == firstLine.split(",").length) {
                        //first validations
                        if(dataValidations(data)) {

                            loadedData = loadedData + 1;

                            //add data to the domain Loc id 1,Loc id 2, length (m)
                            qcl.addRoute(data[0], data[1], Double.parseDouble(data[2]));

                        }
                    }
                }
                return String.format("%d lines read\n%d loaded", linesRead, loadedData);
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
    private static boolean dataValidations(String[] data) {


        return true;
    }
}

