/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.MensException;
import be.vdab.util.mens.Rijbewijs;
import be.vdab.voertuigen.div.DIV;
import be.vdab.voertuigen.div.Nummerplaat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author dimi.vaningh
 */
public abstract class Voertuig implements Comparable<Voertuig>, Serializable {

    private final Nummerplaat NUMMERPLAAT;
    private String merk;
    private Datum datumEersteIngebruikname;
    private int aankoopprijs;
    private final int AANTAL_ZITPLAATSEN;
    private List<Mens> passagiers;

    public Voertuig(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int aantalZitplaatsen, Mens bestuurder) {
        NUMMERPLAAT = DIV.INSTANCE.getNummerplaat();
        setMerk(merk);
        this.datumEersteIngebruikname = datumEersteIngebruikname;
        setAankoopprijs(aankoopprijs);
        if (aantalZitplaatsen > 0) {
            this.AANTAL_ZITPLAATSEN = aantalZitplaatsen;
        } else {
            throw new IllegalArgumentException();
        }
        if (heeftGeldigRijbewijs(bestuurder)) {
            passagiers = new ArrayList();
            passagiers.add(bestuurder);
        }
    }

    //checken niet teveel passagiers, eerst dubbele verwijderen ; of toevoegen en checken meer dan aantal
    public Voertuig(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int aantalZitplaatsen, Mens bestuurder, Mens... passagiers) {
        this(merk, datumEersteIngebruikname, aankoopprijs, aantalZitplaatsen, bestuurder);
        for (Mens passagier : passagiers) {
            if (!this.passagiers.contains(passagier)) {
                this.passagiers.add(passagier);
            }
        }
        if (this.passagiers.size() > getAantalZitplaatsen()) {
            throw new MensException();
        }
    }

    protected int getMAX_ZITPLAATSEN() {
        return getAantalZitplaatsen();
    }

    protected abstract Rijbewijs[] getToegestaneRijbewijzen();

    public Nummerplaat getNummerplaat() {
        return NUMMERPLAAT;
    }

    public String getMerk() {
        return merk;
    }

    public Datum getDatumEersteIngebruikname() {
        return datumEersteIngebruikname;
    }

    public int getAankoopprijs() {
        return aankoopprijs;
    }

    public int getAantalZitplaatsen() {
        return AANTAL_ZITPLAATSEN;
    }

    public Mens getBestuurder() {
        return (Mens) passagiers.get(0);
    }

    public List getPassagiers() {
        return passagiers;
    }

    public void setDatumEersteIngebruikname(Datum d) {
        this.datumEersteIngebruikname = d;
    }

    public void setAankoopprijs(int aankoopPrijs) throws IllegalArgumentException {
        if (aankoopPrijs > 0) {
            this.aankoopprijs = aankoopPrijs;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setMerk(String merk) {
        if (merk != null) {
            this.merk = merk;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Voertuig other = (Voertuig) obj;
        return Objects.equals(this.NUMMERPLAAT, other.NUMMERPLAAT);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.NUMMERPLAAT);
        return hash;
    }

    public int compareTo(Voertuig o) {
        return this.NUMMERPLAAT.compareTo(o.getNummerplaat());
    }

    private boolean heeftGeldigRijbewijs(Mens m) {
        if (m.getRijbewijs() != null) {
            //The disjoint(Collection<?>, Collection<?>) method is used to 'true' if the two specified collections have no elements in common.
            //if(CollectionUtils.containsAny(Arrays.asList(m.getRijbewijs()), Arrays.asList(getToegestaneRijbewijzen())))
            if (!Collections.disjoint(Arrays.asList(m.getRijbewijs()), Arrays.asList(getToegestaneRijbewijzen()))) {
                return true;
            } else {
                throw new MensException();
            }
        } else {
            throw new MensException();
        }
    }

    public void setBestuurder(Mens bestuurder) {
        if (heeftGeldigRijbewijs(bestuurder)) {
            if (isIngezetene(bestuurder)) {
                passagiers.add(0, bestuurder);
            } else if (passagiers.size() < getAantalZitplaatsen()) {
                passagiers.add(0, bestuurder);
            } else {
                throw new MensException();
            }
        } else {
            throw new MensException();
        }
    }

    public static Comparator<Voertuig> getMerkComparator() {
        return new Comparator<Voertuig>() {
            @Override
            public int compare(Voertuig m1, Voertuig m2) {
                return m1.getMerk().equals(m2.getMerk()) ? m1.compareTo(m2) : m1.getMerk().compareTo(m2.getMerk());
            }
        };
    }

    public static Comparator<Voertuig> getAankoopprijsComparator() {
        return new Comparator<Voertuig>() {
            @Override
            public int compare(Voertuig m1, Voertuig m2) {
                return m1.getAankoopprijs() == m2.getAankoopprijs() ? m1.compareTo(m2) : m1.getAankoopprijs() - m2.getAankoopprijs();
            }
        };
    }

    // isIngezetene() geen error; voertuig vol, Mensexception
    public void addIngezetene(Mens m) {
        if (!isIngezetene(m)) {
            if (getAantalZitplaatsen() > passagiers.size()) {
                passagiers.add(m);
            } else {
                throw new MensException();
            }
        }
    }

    public boolean isIngezetene(Mens m) {
        return passagiers.contains(m);
    }

    public List getIngezetenen() {
        List<Mens> tmp = passagiers;
        Collections.sort(tmp);
        return tmp;
    }

    public List getIngezeteneExclusiefBestuurder() {
        List<Mens> tmp = new ArrayList();
        for (int i = 1; i < this.passagiers.size(); i++) {
            tmp.add(passagiers.get(i));
        }
        Collections.sort(tmp);
        return tmp;
    }

    /*@Override
     public String toString() {
     String result = String.format("%s %s %s %s %s", nummerplaat, merk, datumEersteIngebruikname, aankoopprijs, passagiers.get(0));
     String passagiers = "";
     if (this.passagiers.size() > 1) {
     passagiers = passagiers + " [";
     for (int i = 1; i < this.passagiers.size(); i++) {
     if (i != this.passagiers.size() - 1) {
     passagiers = passagiers.concat(this.passagiers.get(i).toString()) + ",";
     } else {
     passagiers = passagiers.concat(this.passagiers.get(i).toString()) + "]";
     }
     }
     return result + passagiers;
     }
     return result;
     }*/
    @Override
    public String toString() {
        String string = String.format("%s %s %s %s %s", NUMMERPLAAT, this.merk, this.datumEersteIngebruikname.toString(), this.aankoopprijs, this.passagiers.get(0).toString());
        if (getIngezeteneExclusiefBestuurder().isEmpty()) {
            return string;
        } else {
            return new StringBuilder(string).append(String.format("%s %s", "", this.getIngezeteneExclusiefBestuurder().toString())).toString();
        }
    }
    
   /* private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        nummerplaat = (Nummerplaat) stream.readObject();
        merk = (String) stream.readObject();
        datumEersteIngebruikname = (Datum) stream.readObject();
        aankoopprijs = stream.readInt();
        aantalZitplaatsen = stream.readInt();
        passagiers = (List<Mens>) stream.readObject();
    } */
}
