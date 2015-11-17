/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.Rijbewijs;
import java.awt.Color;

/**
 *
 * @author dimi.vaningh
 */
public class Personenwagen extends Voertuig{

    private Color kleur;

    public Personenwagen(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int aantalZitplaatsen, Color kleur, Mens bestuurder, Mens... passagiers) {
        super(merk, datumEersteIngebruikname, aankoopprijs, aantalZitplaatsen, bestuurder, passagiers);
        this.kleur = kleur;
        if (getAantalZitplaatsen() > 8) {
            throw new IllegalArgumentException();
        }
    }

    public int getZitplaatsen() {
        return getAantalZitplaatsen();
    }

    public Color getKleur() {
        return kleur;
    }

    public void setKleur(Color kleur) {
        this.kleur = kleur;
    }

    @Override
    protected Rijbewijs[] getToegestaneRijbewijzen() {
        return new Rijbewijs[]{Rijbewijs.B, Rijbewijs.BE};
    }

    @Override
    public String toString(){
        return super.toString() + " " + getAantalZitplaatsen();
    }

}
