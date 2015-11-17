/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen.div;

/**
 *
 * @author dimi.vaningh
 */
//FIXME goede implementatie? static?
public enum DIV {

    INSTANCE;

    private int counterCijfers;

    public Nummerplaat getNummerplaat() {

        if (counterCijfers < 999) {
            counterCijfers++;
        } else {
            counterCijfers = 1;
        }

        String begin = "AAA";
        String formatted = String.format("%03d", counterCijfers);
        return new Nummerplaat(begin + formatted);
    }

}
