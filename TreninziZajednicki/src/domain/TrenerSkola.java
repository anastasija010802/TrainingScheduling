/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PC
 */
public class TrenerSkola extends AbstractDomainObject {

    private Skola skola;
    private Trener trener;
    private Date datumZavrsetka;

    public TrenerSkola(Skola skola, Trener trener, Date datumZavrsetka) {
        this.skola = skola;
        this.trener = trener;
        this.datumZavrsetka = datumZavrsetka;
    }

    public TrenerSkola() {
    }

    @Override
    public String nazivTabele() {
        return " TrenerSkola ";
    }

    @Override
    public String alijas() {
        return " ts ";
    }

    @Override
    public String join() {
        return " JOIN TRENER T ON (T.TRENERID = TS.TRENERID)\n"
                + "JOIN SKOLA S ON (S.SKOLAID = TS.SKOLAID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Trener t = new Trener(rs.getInt("TrenerID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Skola s = new Skola(rs.getInt("SkolaID"),
                    rs.getString("StepenStudija"), rs.getString("Naziv"));

            TrenerSkola ts = new TrenerSkola(s, t, rs.getDate("datumZavrsetka"));

            lista.add(ts);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public String dodatniUslov() {
        return "";
    }

    @Override
    public String orderBy() {
        return "";
    }

    public Skola getSkola() {
        return skola;
    }

    public void setSkola(Skola skola) {
        this.skola = skola;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public Date getDatumZavrsetka() {
        return datumZavrsetka;
    }

    public void setDatumZavrsetka(Date datumZavrsetka) {
        this.datumZavrsetka = datumZavrsetka;
    }

}
