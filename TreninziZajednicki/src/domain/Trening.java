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
public class Trening extends AbstractDomainObject {

    private int treningID;
    private String naziv;
    private String opis;

    @Override
    public String toString() {
        return naziv;
    }

    public Trening(int treningID, String naziv, String opis) {
        this.treningID = treningID;
        this.naziv = naziv;
        this.opis = opis;
    }

    public Trening() {
    }

    @Override
    public String nazivTabele() {
        return " Trening ";
    }

    @Override
    public String alijas() {
        return " tr ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Trening tr = new Trening(rs.getInt("TreningID"),
                    rs.getString("Naziv"), rs.getString("Opis"));

            lista.add(tr);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Naziv, Opis) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', '" + opis + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "', opis = '" + opis + "' ";
    }

    @Override
    public String uslov() {
        return " treningID = " + treningID;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE LOWER(NAZIV) LIKE '%" + naziv + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY TRENINGID ASC ";
    }

    public int getTreningID() {
        return treningID;
    }

    public void setTreningID(int treningID) {
        this.treningID = treningID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

}
