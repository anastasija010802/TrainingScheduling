/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author PC
 */
public class Trener extends AbstractDomainObject {

    private int trenerID;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;

    public Trener() {
    }

    public Trener(int trenerID, String ime, String prezime, String username, String password) {
        this.trenerID = trenerID;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = username;
        this.lozinka = password;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trener other = (Trener) obj;
        if (!Objects.equals(this.trenerID, other.trenerID)) {
            return false;
        }
        return true;
    }

    public int getTrenerID() {
        return trenerID;
    }

    public void setTrenerID(int trenerID) {
        this.trenerID = trenerID;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
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
    
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String nazivTabele() {
        return " trener ";
    }

    @Override
    public String alijas() {
        return " t ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Trener t = new Trener(rs.getInt("TrenerID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            lista.add(t);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Username, Password) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + korisnickoIme + "', '" + lozinka + "'";
    }
    
    @Override
    public String vrednostiZaUpdate() {
        return " Ime = '" + ime + "', Prezime = '" + prezime + "', "
                + "Username = '" + korisnickoIme + "', Password = '" + lozinka + "' ";
    }
    
    @Override
    public String uslov() {
        return " trenerID = " + trenerID;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE LOWER(IME) LIKE '%" + ime + "%' "
                + "AND LOWER(PREZIME) LIKE '%" + prezime + "%' "
                + "AND LOWER(USERNAME) LIKE '%" + korisnickoIme + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY TRENERID ASC ";
    }

}
