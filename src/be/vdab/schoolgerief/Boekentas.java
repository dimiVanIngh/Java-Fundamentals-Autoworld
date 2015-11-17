/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.schoolgerief;

import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author dimi.vaningh
 */
public class Boekentas implements Laadbaar, Serializable {

    private String kleur;
    private Volume laadVolume;

    public Boekentas(String kleur, Volume laadVolume) {
        setKleur(kleur);
        setLaadvolume(laadVolume);
    }

    @Override
    public void setLaadvolume(Volume laadVolume) throws IllegalArgumentException {

        if (laadVolume == null) {
            throw new IllegalArgumentException();
        } else {
            this.laadVolume = laadVolume;
        }
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) throws IllegalArgumentException {
        if (kleur != null) {
            this.kleur = kleur;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.kleur);
        hash = 79 * hash + Objects.hashCode(this.laadVolume);
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
        final Boekentas other = (Boekentas) obj;
        if (!Objects.equals(this.kleur, other.kleur)) {
            return false;
        }
        return Objects.equals(this.laadVolume.getVolume(), other.laadVolume.getVolume());
    }

    @Override
    public String toString() {
        return String.format("boekentas %s %s",kleur, laadVolume);
    }

    @Override
    public Volume getLaadvolume() {
        return laadVolume;
    }

}
