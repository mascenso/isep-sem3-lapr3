package app.domain.model;

import java.util.HashMap;
import java.util.Map;

public class Entity {   // implements client/ Producer/ Wtv

    private String iD;

            //Map<Day, Map<ProdID, Qty>, develop classes if necessary.
            //Develop a class Basket if necessary.
    private final Map<Integer, Map<Integer, Double>> basket;

    public Entity(){
        this.iD = "NO_ENTITY";
        basket = new HashMap<>();
    }

    public Entity(String ID) {
        this.iD = iD;
        basket = new HashMap<>();
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
