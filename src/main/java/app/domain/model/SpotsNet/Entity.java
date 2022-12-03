package app.domain.model.SpotsNet;

import java.util.HashMap;
import java.util.Map;

public class Entity {

    private String iD;
    private Character ettyType;
            //Map<Day, Map<ProdID, Qty>, develop classes if necessary.
            //Develop a class Basket if necessary. (Sprint 2)
    private final Map<Integer, Map<Integer, Double>> basket;

    public Entity(){
        this.iD = "NO_ENTITY";
        basket = new HashMap<>();
    }

    public Entity(String iD) {
        this.ettyType = validateSpotTypeID(iD);
        this.iD = iD;
        basket = new HashMap<>();
    }

    public Character validateSpotTypeID(String spotID){

        //remove charAt(0) and check if the rest is a number
        String spotIDNumber = spotID.substring(1);
        try{
            Integer.parseInt(spotIDNumber);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("Invalid SpotID");
        }catch (NullPointerException e){
            throw new IllegalArgumentException("SpotID cannot be null");
        }
        if(spotID.charAt(0) == 'C' || spotID.charAt(0) == 'E' || spotID.charAt(0) == 'P'){
            return spotID.charAt(0);
        }
        else{
            throw new IllegalArgumentException("Invalid SpotID");
        }
    }

    public String getType(){
        return ettyType.toString();
    }

    //Add getters and setters, if necessary

    public Map<Integer, Double> getProductsByDay(int day){
        return basket.get(day);
    }

    public Double getProductQtyByDay(int day, int prodID){
        return basket.get(day).get(prodID);
    }

    public void addProductToBasket(int day, int productID, double qty){

        //verify if has an entity alocated //todo

        Map<Integer, Double> products = basket.get(day);
        if(products == null){
            products = new HashMap<Integer, Double>();
            basket.put(day, products);
        }
        products.put(productID, qty);
    }






}
