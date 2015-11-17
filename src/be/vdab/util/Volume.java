/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author dimi.vaningh
 */
public class Volume implements Comparable<Volume>, Serializable {

    private static final long serialVersionUId = 1L;
    private final int hoogte;
    private final int breedte;
    private final int diepte;
    private final Maat maat;

    public Volume(int hoogte, int breedte, int diepte, Maat maat) throws VolumeException {
        if (hoogte < 0 || breedte < 0 || diepte < 0) {
            throw new VolumeException();
        } else {
            this.hoogte = hoogte;
            this.breedte = breedte;
            this.diepte = diepte;
            this.maat = maat;
        }
    }

    public Long getVolume(){
        int volume = hoogte * breedte * diepte;
        switch (this.maat.toString()) {
            case "centimeter":
                return (long) volume;
            case "decimeter":
                return (long) volume * 1000;
            case "meter":
                 return (long) volume * 1000000;
            default: return 0L;
        }
        /*
         if (Maat.toString().equals("centimeter"))) {
         return (long) hoogte * breedte * diepte;
         }
         if (Maat.centimeter) {
         return (long) hoogte * breedte * diepte;
         }
         if (Maat.centimeter) {
         return (long) hoogte * breedte * diepte;
         }*/
    }

    public int getHoogte() {
        return hoogte;
    }

    public int getBreedte() {
        return breedte;
    }

    public int getDiepte() {
        return diepte;
    }

    public Maat getMaat() {
        return maat;
    }

    @Override
    public int compareTo(Volume o){
        return safeLongToInt(getVolume() - o.getVolume());
    }

    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.hoogte;
        hash = 67 * hash + this.breedte;
        hash = 67 * hash + this.diepte;
        hash = 67 * hash + Objects.hashCode(this.maat);
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
        final Volume other = (Volume) obj;
        if (this.hoogte != other.hoogte) {
            return false;
        }
        if (this.breedte != other.breedte) {
            return false;
        }
        if (this.diepte != other.diepte) {
            return false;
        }
        return this.maat == other.maat;
    }

    @Override
    public String toString() {
        return hoogte + "(h)x" + breedte + "(b)x" + diepte + "(d) " + this.maat.toString();
    }
    
    
}
