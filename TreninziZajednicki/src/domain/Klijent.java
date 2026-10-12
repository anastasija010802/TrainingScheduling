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
public class Klijent extends AbstractDomainObject {

    private int klijentID;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private String stavPremaTreningu;
    private FitnesProfil fitnesProfil;

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    public Klijent(int klijentID, String ime, String prezime, String email, String telefon, String stavPremaTreningu, FitnesProfil fitnesProfil) {
        this.klijentID = klijentID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.stavPremaTreningu = stavPremaTreningu;
        this.fitnesProfil = fitnesProfil;
    }

    public Klijent() {
    }

    @Override
    public String nazivTabele() {
        return " Klijent ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return " JOIN FITNESPROFIL FP ON (FP.FITNESPROFILID = K.FITNESPROFILID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            FitnesProfil fp = new FitnesProfil(rs.getInt("FitnesProfilID"),
                    rs.getString("TipGradje"));

            Klijent k = new Klijent(rs.getInt("KlijentID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("email"), rs.getString("telefon"),
                    rs.getString("stavPremaTreningu"), fp);

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, email, telefon, stavPremaTreningu, FitnesProfilID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + email + "', '" + telefon + "', '" + stavPremaTreningu + "', "
                + fitnesProfil.getFitnesProfilID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " email = '" + email + "', telefon = '" + telefon + "', "
                + "stavPremaTreningu = '" + stavPremaTreningu + "', "
                + "FitnesProfilID = " + fitnesProfil.getFitnesProfilID() + " ";
    }

    @Override
    public String uslov() {
        return " klijentID = " + klijentID;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE LOWER(IME) LIKE '%" + ime + "%' "
                + "AND LOWER(PREZIME) LIKE '%" + prezime + "%' "
                + "AND LOWER(EMAIL) LIKE '%" + email + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY KLIJENTID ASC ";
    }

    public int getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(int klijentID) {
        this.klijentID = klijentID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getStavPremaTreningu() {
        return stavPremaTreningu;
    }

    public void setStavPremaTreningu(String stavPremaTreningu) {
        this.stavPremaTreningu = stavPremaTreningu;
    }

    public FitnesProfil getFitnesProfil() {
        return fitnesProfil;
    }

    public void setFitnesProfil(FitnesProfil fitnesProfil) {
        this.fitnesProfil = fitnesProfil;
    }

}
