package app.interfaces;

public interface CONSTANT {
    String PARAMS_FILENAME = "config.properties";

    final String HEADER_DISTANCES = "Loc id 1,Loc id 2, length (m)";

    final String HEADER_CLIENTES_PRODUTORES = "Loc id,lat,lng,Clientes-Produtores";

    final String HEADER_CABAZES =
            "Clientes-Produtores,Dia,Prod1,Prod2,Prod3,Prod4,Prod5,Prod6,Prod7,Prod8,Prod9," + "Prod10,Prod11,Prod12";

    final String FILE_DISTANCES = "DISTANCIAS";
    final String FILE_CLIENTES_PRODUTORES = "CLIENTES-PRODUTORES";
    final String FILE_CABAZES = "CABAZES";

    final String DELIMITERC = "comma";
    final String DELIMITER = "other";

    final int MAXSTRINGSIZE = 200;
    final String LOCID = "CT";
    final char Client = 'C';
    final char Company = 'E';
    final char Produtor = 'P';
    final int MAXDISTANCE = 999999;

    final int LOC_ID = 0;
    final int LATITUDE = 1;
    final int LONGITUDE = 2;
    final int CLIENTES_PRODUTORES = 3;

    final int LOC_ID_I = 0;
    final int LOC_ID_F = 1;
    final int DISTANCE = 2;

    final String ROLE_MANAGER = "Manager";
    final String ROLE_FARMER = "Farmer";
    final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";


}

