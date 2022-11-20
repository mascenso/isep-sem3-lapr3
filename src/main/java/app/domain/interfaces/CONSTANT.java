package app.domain.interfaces;

public interface CONSTANT {
    String PARAMS_FILENAME = "config.properties";

    final int AREACODE = 0;
    final int AREACODEM49 = 1;
    final int COUNTRY = 2;
    final int ITEMCODE = 3;
    final int ITEMCODECPC = 4;
    final int ITEM = 5;
    final int ELEMENTCODE = 6;
    final int ELEMENT = 7;
    final int YEARCODE = 8;
    final int YEAR = 9;
    final int UNITS = 10;
    final int QT = 11;
    final int FLAG = 12;

    final int COUNTRYCODE = 0;
    final int LATITUDE = 1;
    final int LONGITUDE = 2;
    final int COUNTRYAREA = 3;

    final int FLAGFILE = 0;
    final int FLAGDESC = 1;

    int MINAREACODE = 0;
    int MAXAREACODE = 1000;
    int MINITEMCODE = 0;
    int MAXITEMCODE = 3000;
    final int MAXSTRINGSIZE = 200;
    final int COUNTRYCODESIZE = 2;
    final int MAXQUANTITY = 100000000;

    final String HEADER =
            "Area Code,Area Code (M49),Area,Item Code,Item Code (CPC),Item,Element Code,Element,Year Code,Year,Unit," +
            "Value,Flag";
    final String HEADERAREACOORDINATES = "country,latitude,longitude,area";
    final String HEADERFLAGS = "Flag,Description";

    final String FILEPRINCIPAL = "CropsLivestock";
    final String FILEFLAGS = "FlagDescription";
    final String FILEAREACOOR = "AreaCoordinates";
    final String FILEITEMCODES = "ItemCodes";

    final String DELIMITERC = "comma";
    final String DELIMITER = "other";

}
