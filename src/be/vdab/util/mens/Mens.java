/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util.mens;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author dimi.vaningh
 */
public class Mens implements Comparable<Mens>, Serializable {

    private static final long serialVersionUId = 1L;
    private String name;
    private List<Rijbewijs> rijbewijzen;

    public Mens(String name) {
        this.name = name;
    }

    //public Mens(String name, Rijbewijs rijbewijs){}
    public Mens(String name, Rijbewijs... rijbewijzen) {
        this(name);
        this.rijbewijzen = new ArrayList(rijbewijzen.length);
        for (Rijbewijs rijbewijs : rijbewijzen) {
            if (!this.rijbewijzen.contains(rijbewijs)) {
                this.rijbewijzen.add(rijbewijs);
            }
        }
    }

    @Override
    public int compareTo(Mens o) {
        if( o == null) throw new NullPointerException();
        return this.name.compareTo(o.getNaam());
    }

    public String getNaam() {
        return name;
    }

    public Rijbewijs[] getRijbewijs() {
        if (rijbewijzen != null) {
            Rijbewijs[] tmp = new Rijbewijs[rijbewijzen.size()];
            int counter = 0;
            for (Rijbewijs rijbewijs : rijbewijzen) {
                tmp[counter] = rijbewijs;
                counter++;
            }
            return tmp;
        }
        return null;
    }

    /*@Override
     public String toString() {

     String rijbewijzenString = "";
     if (rijbewijzen != null) {
     rijbewijzenString = rijbewijzen.get(0).toString();
     if (rijbewijzen.size() > 1) {
     for (int i = 1; i < rijbewijzen.size(); i++) {
     if (rijbewijzen.get(i) != null) {
     rijbewijzenString = rijbewijzenString.concat(", " + rijbewijzen.get(i).toString());
     }
     }
     }
     }
     if (rijbewijzenString.length() > 0) {
     return name + "(" + rijbewijzenString + ")";
     } else {
     return name;
     }
     }*/
    @Override
    public final String toString() {
        String rijbewijzenString = "";
        if (getRijbewijs() == null) {
            return getNaam();
        } else {
            for (Rijbewijs elements : getRijbewijs()) {
                rijbewijzenString += " " + elements + ",";
            }
        }
        return getNaam() + "(" + rijbewijzenString.substring(1, rijbewijzenString.length() - 1) + ")";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.rijbewijzen);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mens other = (Mens) obj;
        if (!getNaam().equals(other.getNaam())) {
            return false;
        }
        return Arrays.equals(getRijbewijs(), other.getRijbewijs());

    }

}
