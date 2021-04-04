/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;


import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class CartDTO{
    private String customerEmail;
    private Map<String,CarDTO> cart;

    public CartDTO() {
    }

    public CartDTO(String customerEmail, Map<String, CarDTO> cart) {
        this.customerEmail = customerEmail;
        this.cart = cart;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }


    public Map<String, CarDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, CarDTO> cart) {
        this.cart = cart;
    }

    
    public void add(CarDTO dto,String key){
        if(cart==null){
            this.cart=new HashMap<String,CarDTO>();
        }
        if(this.cart.containsKey(key)){
            int quantity=this.cart.get(key).getQuantity();
            dto.setQuantity(quantity+dto.getQuantity());
        }
        cart.put(key, dto);      
    }
    
    public void delete(String id){
        if(this.cart==null)
            return;
        if(this.cart.containsKey(id)){
            this.cart.remove(id);
        }
    }

    public void update(String keys,int amount){
        if(this.cart!=null){
            if(this.cart.containsKey(keys)){
                this.cart.get(keys).setQuantity(amount);
            }
        }
    }
}
