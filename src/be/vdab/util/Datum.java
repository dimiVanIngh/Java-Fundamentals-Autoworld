/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util;

import java.io.Serializable;

/**
 *
 * @author dimi.vaningh
 */
public class Datum implements Serializable, Comparable<Datum> {

    final int jaar, maand, dag;
    private static final int[] dagen = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] schrikkelDagen = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    //De class aanvaardt alleen geldige datums tussen 01/01/1583 en 31/12/4099. 
    public Datum(int dag, int maand, int jaar) throws DatumException {
        if (checkDatum(dag, maand, jaar)) {
            this.dag = dag;
            this.maand = maand;
            this.jaar = jaar;
        } else {
            throw new DatumException();
        }
    }

    public boolean checkDatum(int dag, int maand, int jaar) {
        return (checkJaar(jaar) && checkMaand(maand) && checkDag(dag, maand, jaar));
    }

    public boolean checkJaar(int jaar) {
        if (jaar >= 1583 && jaar <= 4099) {
            return true;
        } else {
            throw new DatumException();
        }
    }

    public boolean checkMaand(int maand) {
        if (maand >= 1 && maand <= 12) {
            return true;
        } else {
            throw new DatumException();
        }
    }

    public boolean checkDag(int dag, int maand, int jaar) {
        if (isSchrikkelJaar(jaar)) {
            if (dag >= 1 && dag <= schrikkelDagen[maand]) {
                return true;
            } else {
                throw new DatumException("Probleem met de dag");
            }
        } else if (dag >= 1 && dag <= dagen[maand]) {
            return true;
        } else {
            throw new DatumException("Probleem met de dag");
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.jaar;
        hash = 59 * hash + this.maand;
        hash = 59 * hash + this.dag;
        return hash;
    }

    @Override
    public boolean equals(Object obj
    ) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Datum other = (Datum) obj;
        if (this.jaar != other.jaar) {
            return false;
        }
        if (this.maand != other.maand) {
            return false;
        }
        return this.dag == other.dag;
    }

    @Override
    public String toString() {
        String formattedDag = String.format("%02d", dag);
        String formattedMaand = String.format("%02d", maand);
        return formattedDag + "/" + formattedMaand + "/" + jaar;
    }

    private boolean isSchrikkelJaar(int jaar) {
        return (((jaar % 4 == 0) && (jaar % 100 != 0)) || (jaar % 400 == 0));
    }

    @Override
    public int compareTo(Datum o) {
        if (this.getJaar() != o.getJaar()) {
            return this.getJaar() - o.getJaar();
        } else if (this.getMaand() != o.getMaand()) {
            return this.getMaand() - o.getMaand();
        } else {
            return this.getDag() - o.getDag();
        }
    }

    public int getJaar() {
        return jaar;
    }

    public int getMaand() {
        return maand;
    }

    public int getDag() {
        return dag;
    }

}
