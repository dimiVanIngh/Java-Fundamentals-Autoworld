/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import be.vdab.util.mens.Mens;
import java.awt.Color;

/**
 *
 * @author dimi.vaningh
 */
public class Pickup extends Personenwagen implements Laadbaar{
    
    private Volume volume;

    public Pickup(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int aantalZitplaatsen, Color kleur,Volume volume, Mens bestuurder, Mens... passagiers) {
        super(merk, datumEersteIngebruikname, aankoopprijs, aantalZitplaatsen, kleur, bestuurder, passagiers);
        setLaadvolume(volume);
    }

    @Override
    public void setLaadvolume(Volume laadVolume) {
        this.volume = laadVolume;
    }

    @Override
    public Volume getLaadvolume() {
        return this.volume;
    }
    
    @Override
    public String toString(){
        return super.toString() + " " + getLaadvolume();
    }
    
}
