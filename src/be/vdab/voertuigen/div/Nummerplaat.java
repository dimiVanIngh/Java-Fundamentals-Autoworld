/*
 * @Autor Frank
 * Dit is een oefening.
 */
package be.vdab.voertuigen.div;

import java.io.Serializable;
import java.util.Objects;

public final class Nummerplaat implements Comparable<Nummerplaat>, Serializable {

    private static final long serialVersionUId = 1L;
    private final String plaat;

    Nummerplaat(String plaat) {
        this.plaat = plaat;
    }

    public String getPlaat() {
        return plaat;
    }

    @Override
    public String toString() {
        return getPlaat();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.plaat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        /*
         if (obj == null) {
         return false;
         }
         if (getClass() != obj.getClass()) {
         return false;
         }
         final Nummerplaat other = (Nummerplaat) obj;
         return Objects.equals(this.plaat, other.plaat); */

        if (obj instanceof Nummerplaat) {
            Nummerplaat tmp = (Nummerplaat) obj;
            return tmp.getPlaat().equals(this.plaat);
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Nummerplaat o) {
        return this.plaat.compareTo(o.getPlaat());
    }

}
