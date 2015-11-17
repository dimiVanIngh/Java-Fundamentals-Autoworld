/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util.mens;

/**
 *
 * @author dimi.vaningh
 */
public enum Rijbewijs {

    A("A"), B("B"), BE("B+E"), C("C"), CE("C+E"), D("D"), DE("D+E");
    
    //private String value = "";
    private final String value;
    
    private Rijbewijs(String value){
        this.value = value;
    }
    
    @Override
    public String toString(){
        return value;
    }    
}

