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
public class Program extends AbstractDomainObject {

    private int programID;
    private Date datumPocetka;
    private Date datumZavrsetka;
    private String cilj;
    private double cena;
    private Klijent klijent;
    private Trener trener;
    private ArrayList<StavkaPrograma> stavkePrograma;

    public Program(int programID, Date datumPocetka, Date datumZavrsetka, String cilj, double cena, Klijent klijent, Trener trener, ArrayList<StavkaPrograma> stavkePrograma) {
        this.programID = programID;
        this.datumPocetka = datumPocetka;
        this.datumZavrsetka = datumZavrsetka;
        this.cilj = cilj;
        this.cena = cena;
        this.klijent = klijent;
        this.trener = trener;
        this.stavkePrograma = stavkePrograma;
    }

    @Override
    public String toString() {
 return cilj;
    }
    
    

    public Program() {
    }

    @Override
    public String nazivTabele() {
        return " Program ";
    }

    @Override
    public String alijas() {
        return " p ";
    }

    @Override
    public String join() {
        return " JOIN KLIJENT K ON (K.KLIJENTID = P.KLIJENTID)\n"
                + "JOIN TRENER T ON (T.TRENERID = P.TRENERID)\n"
                + "JOIN FITNESPROFIL FP ON (K.FITNESPROFILID = FP.FITNESPROFILID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Trener t = new Trener(rs.getInt("TrenerID"),
                    rs.getString("t.Ime"), rs.getString("t.Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            FitnesProfil fp = new FitnesProfil(rs.getInt("FitnesProfilID"),
                    rs.getString("TipGradje"));

            Klijent k = new Klijent(rs.getInt("KlijentID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("email"), rs.getString("telefon"),
                    rs.getString("stavPremaTreningu"), fp);

            Program p = new Program(rs.getInt("programID"), rs.getDate("datumPocetka"),
                    rs.getDate("datumZavrsetka"), rs.getString("cilj"),
                    rs.getDouble("cena"), k, t, new ArrayList<>());

            lista.add(p);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumPocetka, datumZavrsetka, cilj, cena, klijentID, trenerID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new java.sql.Date(datumPocetka.getTime()) + "', "
                + "'" + new java.sql.Date(datumZavrsetka.getTime()) + "', "
                + "'" + cilj + "', " + cena + ", " + klijent.getKlijentID()
                + ", " + trener.getTrenerID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumPocetka = '" + new java.sql.Date(datumPocetka.getTime()) + "', "
                + "datumZavrsetka = '" + new java.sql.Date(datumZavrsetka.getTime()) + "', "
                + "cilj = '" + cilj + "', cena = " + cena + " ";
    }

    @Override
    public String uslov() {
        return " programID = " + programID;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE LOWER(K.IME) LIKE '%" + klijent.getIme() + "%' "
                + "AND LOWER(K.PREZIME) LIKE '%" + klijent.getPrezime() + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY PROGRAMID ASC ";
    }

    public int getProgramID() {
        return programID;
    }

    public void setProgramID(int programID) {
        this.programID = programID;
    }

    public Date getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public Date getDatumZavrsetka() {
        return datumZavrsetka;
    }

    public void setDatumZavrsetka(Date datumZavrsetka) {
        this.datumZavrsetka = datumZavrsetka;
    }

    public String getCilj() {
        return cilj;
    }

    public void setCilj(String cilj) {
        this.cilj = cilj;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public ArrayList<StavkaPrograma> getStavkePrograma() {
        return stavkePrograma;
    }

    public void setStavkePrograma(ArrayList<StavkaPrograma> stavkePrograma) {
        this.stavkePrograma = stavkePrograma;
    }

}
