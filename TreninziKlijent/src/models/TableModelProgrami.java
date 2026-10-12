/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.FitnesProfil;
import domain.Klijent;
import domain.Program;
import domain.Trener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelProgrami extends AbstractTableModel implements Runnable {

    private ArrayList<Program> lista;
    private String[] kolone = {"ID", "Klijent", "Datum pocetka", "Datum zavrsetka", "Cena"};
    private String parametarIme = "";
    private String parametarPrezime = "";
    private Program program = new Program(0, null, null, "", 0,
            new Klijent(0, "", "", "", "", "", new FitnesProfil(0, "")),
            new Trener(0, "", "", "", ""), null);

    public TableModelProgrami() {
        try {
            lista = ClientController.getInstance().
                    getAllProgram(new Program(0, null, null, "", 0,
                            new Klijent(0, "", "", "", "", "", new FitnesProfil(0, "")),
                            new Trener(0, "", "", "", ""), null));
        } catch (Exception ex) {
            Logger.getLogger(TableModelProgrami.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Program p = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        
        switch (column) {
            case 0:
                return p.getProgramID();
            case 1:
                return p.getKlijent();
            case 2:
                return sdf.format(p.getDatumPocetka());
            case 3:
                return sdf.format(p.getDatumZavrsetka());
            case 4:
                return p.getCena() + "€";

            default:
                return null;
        }
    }

    public Program getSelectedProgram(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();

            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelProgrami.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametarIme(String ime) {
        this.parametarIme = ime;
        refreshTable();
    }

    public void setParametarPrezime(String prezime) {
        this.parametarPrezime = prezime;
        refreshTable();
    }

    public void refreshTable() {
        try {

            program.getKlijent().setIme(parametarIme.toLowerCase());
            program.getKlijent().setPrezime(parametarPrezime.toLowerCase());

            lista = ClientController.getInstance().getAllProgram(program);
            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Program> getLista() {
        return lista;
    }

}
