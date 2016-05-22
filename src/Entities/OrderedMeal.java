package Entities;

/**
 * Created by hank9653 on 2016/5/16.
 */
public class OrderedMeal {
    int idOrderMeal;
    String orderMealName;
    String orderMealDescription;
    int orderMealPrice;
    String orderMealType;
    int orderMealStatus;

    public void setOrderedMeal(int idOrderMeal, String orderMealName, String orderMealDescription, int orderMealPrice, String orderMealType, int orderMealStatus){
        this.idOrderMeal = idOrderMeal;
        this.orderMealName = orderMealName;
        this.orderMealDescription = orderMealDescription;
        this.orderMealPrice = orderMealPrice;
        this.orderMealType = orderMealType;
        this.orderMealStatus = orderMealStatus;
    }

    public int getIdOrderMeal(){
        return this.idOrderMeal;
    }

    public String getOrderMealName(){
        return this.orderMealName;
    }

    public String getOrderMealDescription(){
        return this.orderMealDescription;
    }

    public int getOrderMealPrice(){
        return this.orderMealPrice;
    }

    public String getOrderMealType(){
        return this.orderMealType;
    }

    public int getOrderMealStatus(){
        return this.orderMealStatus;
    }
}
