package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.Rijbewijs;

public class Vrachtwagen extends Voertuig implements Laadbaar {

    private int maximaleMassa, aantalAssen, zitplaatsen;
    private Volume laadvolume;

    public Vrachtwagen(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Volume laadvolume, int maximaleMassa, int aantalAssen, Mens bestuurder, Mens... ingezetenen) {
        super(merk, datumEersteIngebruikname, aankoopprijs, zitplaatsen, bestuurder, ingezetenen);
        this.maximaleMassa = maximaleMassa;
        this.aantalAssen = aantalAssen;
        this.laadvolume = laadvolume;
        this.zitplaatsen = zitplaatsen;

        if (this.zitplaatsen > 3) {
            throw new IllegalArgumentException();
        }

    }

    @Override
    protected Rijbewijs[] getToegestaneRijbewijzen() {
        return new Rijbewijs[]{Rijbewijs.C, Rijbewijs.CE, Rijbewijs.D, Rijbewijs.DE};
    }

    @Override
    public void setLaadvolume(Volume laadvolume) {
        this.laadvolume = laadvolume;
    }

    @Override
    public Volume getLaadvolume() {
        return this.laadvolume;
    }

    public int getMaximaalToegelatenMassa() {
        return maximaleMassa;
    }

    public void setMaximaalToegelatenMassa(int maximaleMassa) {
        this.maximaleMassa = maximaleMassa;
    }

    public int getAantalAssen() {
        return aantalAssen;
    }

    public void setAantalAssen(int aantalAssen) {
        this.aantalAssen = aantalAssen;
    }

    @Override
    public String toString() {
        return String.format("%s assen:%s, maximaal toegelaten massa:%s, laadvolume:%s", super.toString(), this.aantalAssen, this.maximaleMassa, this.laadvolume);
    }

}
