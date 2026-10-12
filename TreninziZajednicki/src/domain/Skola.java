/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class Skola extends AbstractDomainObject {
    
    private int skolaID;
    private String stepenStudija;
    private String naziv;

    @Override
    public String toString() {
        return naziv;
    }

    public Skola(int skolaID, String stepenStudija, String naziv) {
        this.skolaID = skolaID;
        this.stepenStudija = stepenStudija;
        this.naziv = naziv;
    }

    public Skola() {
    }
    
    @Override
    public String nazivTabele() {
        return " Skola ";
    }

    @Override
    public String alijas() {
        return " s ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Skola s = new Skola(rs.getInt("SkolaID"),
                    rs.getString("StepenStudija"), rs.getString("Naziv"));

            lista.add(s);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (StepenStudija, Naziv) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + stepenStudija + "', '" + naziv + "' ";
    }
    
    @Override
    public String vrednostiZaUpdate() {
        return " stepenStudija = '" + stepenStudija + "', naziv = '" + naziv + "' ";
    }
    
    @Override
    public String uslov() {
        return " skolaID = " + skolaID;
    }

    @Override
    public String dodatniUslov() {
        return "";
    }

    @Override
    public String orderBy() {
        return " ORDER BY SKOLAID ASC ";
    }

    public int getSkolaID() {
        return skolaID;
    }

    public void setSkolaID(int skolaID) {
        this.skolaID = skolaID;
    }

    public String getStepenStudija() {
        return stepenStudija;
    }

    public void setStepenStudija(String stepenStudija) {
        this.stepenStudija = stepenStudija;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
}
