package app.domain.model;
import java.util.HashMap;
import java.util.Map;
public class Entity implements Comparable<Entity> {

    private String iD; // Clientes-Produtores, ex: C1
    private Character ettyType; //tipo: C
    //Map<Day, Map<ProdID, Qty>, develop classes if necessary.
    //Develop a class Basket if necessary. (Sprint 2)
    private final Map<Integer, Map<Integer, Double>> basket; // cabazes
    public Entity() {
        this.iD = null;
        this.ettyType = null;
        basket = new HashMap<>();
    }

    public Entity(String iD) {
        setEttyType(iD.charAt(0));
        setiD(iD);
        basket = new HashMap<>();
    }

    public Entity(Entity entity) {
        setEttyType(entity.getEttyType());
        setiD(entity.getiD());
        basket = new HashMap<>(entity.getBasket());
    }

    //todo:validations inside set and exception control in controller
    public void setiD(String iD) {
        this.iD = iD;
    }

    public void setEttyType(Character ettyType) {
        this.ettyType = ettyType;
    }

    public Character getEttyType() {
        return ettyType;
    }

    public String getiD() {
        return iD;
    }

    public Map<Integer, Map<Integer, Double>> getBasket() {
        return basket;
    }

    @Override
    public String toString() {
        return String.format("Entity id: %s", iD);
    }

    @Override
    public int compareTo(Entity o) {
        return o.getiD().compareTo(this.iD);
    }

    @Override
    public int hashCode() {
        return this.iD.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        Entity otherObj = (Entity)obj;
        return this.iD.equals(otherObj.getiD()) && this.ettyType.equals(otherObj.getEttyType()) &&this.basket.equals(otherObj.getBasket());
    }




    // todo:verificar os necessarios restantes guardar num ficheiro para sprint 2
    public Map<Integer, Double> getProductsByDay(int day) {
        return basket.get(day);
    }

    public Double getProductQtyByDay(int day, int prodID) {
        return basket.get(day).get(prodID);
    }

    public void addProductToBasket(int day, int productID, double qty) {

        //verify if has an entity alocated //todo
        Map<Integer, Double> products = basket.get(day);
        if(products == null) {
            products = new HashMap<Integer, Double>();
            basket.put(day, products);
        }
        products.put(productID, qty);
    }


}