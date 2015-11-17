package be.vdab;

import be.vdab.util.Datum;
import be.vdab.util.DatumException;
import be.vdab.util.Maat;
import be.vdab.util.Volume;
import be.vdab.util.VolumeException;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.Rijbewijs;
import be.vdab.voertuigen.Personenwagen;
import be.vdab.voertuigen.Pickup;
import be.vdab.voertuigen.Voertuig;
import be.vdab.voertuigen.Vrachtwagen;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.collections.CollectionUtils;

public class NewMain {

    private static Set<Voertuig> voertuigen = new TreeSet<>();
    private static Set<Voertuig> voertuigenPrijs = new TreeSet<>(Voertuig.getAankoopprijsComparator());
    private static Set<Voertuig> voertuigenMerk = new TreeSet<>(Voertuig.getMerkComparator());
    private static Set<Voertuig> voertuigenMerkFromFile = new TreeSet<>();

    private static void initialiseerVoertuigen() throws DatumException, VolumeException {
        voertuigen.add(new Personenwagen("Audi", new Datum(1, 1, 2011), 60000, 5, Color.BLUE, new Mens("Amadeus", Rijbewijs.A, Rijbewijs.B), new Mens("Anita")));
        voertuigen.add(new Personenwagen(" Audi ", new Datum(27, 2, 2001), 31480, 7, Color.BLACK, new Mens("Jef", Rijbewijs.B, Rijbewijs.BE), new Mens("Anita"), new Mens("Cindy")));
        voertuigen.add(new Pickup("Ford", new Datum(8, 6, 1996), 70000, 2, Color.ORANGE, new Volume(2, 2, 2, Maat.meter), new Mens("Tuur", Rijbewijs.B), new Mens("Bert")));
        voertuigen.add(new Pickup("BMG", new Datum(30, 3, 2010), 75000, 3, Color.YELLOW, new Volume(2, 2, 2, Maat.meter), new Mens("Ben", Rijbewijs.B), new Mens("Beerend")));
        voertuigen.add(new Vrachtwagen("DAF", new Datum(14, 1, 2013), 320000, 2, new Volume(2, 20, 4, Maat.meter), 75000, 2, new Mens("Louis", Rijbewijs.C, Rijbewijs.CE)));
        voertuigen.add(new Vrachtwagen("Audi", new Datum(5, 1, 1986), 160000, 3, new Volume(3, 18, 3, Maat.meter), 150000, 3, new Mens("An", Rijbewijs.C, Rijbewijs.CE), new Mens("Peter")));
        CollectionUtils.addAll(voertuigenPrijs, voertuigen.toArray());
        CollectionUtils.addAll(voertuigenMerk, voertuigen.toArray());
        voertuigenMerkFromFile.addAll(voertuigenMerk);
    }

    public static void main(String[] args) throws DatumException, VolumeException, FileNotFoundException, IOException, ClassNotFoundException {

        initialiseerVoertuigen();

        System.out.println(voertuigen);
        System.out.println(voertuigenPrijs);
        System.out.println(voertuigenMerk);

        //Set<Voertuig> voertuigenFile = Collections.unmodifiableSet(voertuigenMerk);
        //Voertuig[] set = voertuigenFile.toArray(new Voertuig[voertuigenFile.size()]);
        
        TreeSet<Voertuig> tmp = new TreeSet();
        tmp.addAll(voertuigenMerk);
        Collections.unmodifiableSet(tmp);
        
        File fileOut = new File("wagenpark.ser");
        FileOutputStream fos = new FileOutputStream(fileOut);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(tmp);
        oos.close();

        File fileIn = new File("wagenpark.ser");
        FileInputStream fis = new FileInputStream(fileIn);
        ObjectInputStream ois = new ObjectInputStream(fis);
        TreeSet<Voertuig> setFromFile = (TreeSet<Voertuig>) ois.readObject();
        //Voertuig[] setFromFile = (Voertuig[]) ois.readObject();
        ois.close();

        //voertuigenMerkFromFile.addAll(Arrays.asList(setFromFile));
        System.out.println(setFromFile);
    }
}
