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
public class StavkaPrograma extends AbstractDomainObject {

    private Program program;
    private int rb;
    private Date dan;
    private Trening trening;

    public StavkaPrograma(Program program, int rb, Date dan, Trening trening) {
        this.program = program;
        this.rb = rb;
        this.dan = dan;
        this.trening = trening;
    }

    public StavkaPrograma() {
    }

    @Override
    public String nazivTabele() {
        return " StavkaPrograma ";
    }

    @Override
    public String alijas() {
        return " sp ";
    }

    @Override
    public String join() {
        return " JOIN TRENING TR ON (TR.TRENINGID = SP.TRENINGID)\n"
                + "JOIN PROGRAM P ON (P.PROGRAMID = SP.PROGRAMID)\n"
                + "JOIN KLIJENT K ON (K.KLIJENTID = P.KLIJENTID)\n"
                + "JOIN TRENER T ON (T.TRENERID = P.TRENERID)\n"
                + "JOIN FITNESPROFIL FP ON (K.FITNESPROFILID = FP.FITNESPROFILID) ";
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
                    rs.getDouble("cena"), k, t, null);

            Trening tr = new Trening(rs.getInt("TreningID"),
                    rs.getString("Naziv"), rs.getString("Opis"));

            StavkaPrograma sp = new StavkaPrograma(p, rs.getInt("rb"),
                    rs.getDate("dan"), tr);

            lista.add(sp);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (programID, rb, dan, TreningID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + program.getProgramID() + ", " + rb + ", "
                + "'" + new java.sql.Date(dan.getTime()) + "', "
                + trening.getTreningID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " dan = '" + new java.sql.Date(dan.getTime()) + "', "
                + "treningID = " + trening.getTreningID();
    }

    @Override
    public String uslov() {
        return " programID = " + program.getProgramID() + " AND RB = " + rb;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE P.PROGRAMID = " + program.getProgramID();
    }

    @Override
    public String orderBy() {
        return " ORDER BY RB ASC ";
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Date getDan() {
        return dan;
    }

    public void setDan(Date dan) {
        this.dan = dan;
    }

    public Trening getTrening() {
        return trening;
    }

    public void setTrening(Trening trening) {
        this.trening = trening;
    }

}
