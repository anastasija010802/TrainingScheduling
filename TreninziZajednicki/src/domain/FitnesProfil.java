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
public class FitnesProfil extends AbstractDomainObject {

    private int fitnesProfilID;
    private String tipGradje;

    @Override
    public String toString() {
        return tipGradje;
    }

    public FitnesProfil(int fitnesProfilID, String tipGradje) {
        this.fitnesProfilID = fitnesProfilID;
        this.tipGradje = tipGradje;
    }

    public FitnesProfil() {
    }

    @Override
    public String nazivTabele() {
        return " FitnesProfil ";
    }

    @Override
    public String alijas() {
        return " fp ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            FitnesProfil fp = new FitnesProfil(rs.getInt("FitnesProfilID"),
                    rs.getString("TipGradje"));

            lista.add(fp);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (TipGradje) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + tipGradje + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " tipGradje = '" + tipGradje + "' ";
    }

    @Override
    public String uslov() {
        return " fitnesProfilID = " + fitnesProfilID;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE LOWER(TIPGRADJE) LIKE '%" + tipGradje + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY fitnesProfilID ASC ";
    }

    public int getFitnesProfilID() {
        return fitnesProfilID;
    }

    public void setFitnesProfilID(int fitnesProfilID) {
        this.fitnesProfilID = fitnesProfilID;
    }

    public String getTipGradje() {
        return tipGradje;
    }

    public void setTipGradje(String tipGradje) {
        this.tipGradje = tipGradje;
    }

}
