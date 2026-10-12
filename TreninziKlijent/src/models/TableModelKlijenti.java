/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.FitnesProfil;
import domain.Klijent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelKlijenti extends AbstractTableModel implements Runnable {

    private ArrayList<Klijent> lista;
    private String[] kolone = {"ID", "Ime", "Prezime", "Email", "Telefon",
        "Stav prema treningu", "Fitnes profil"};
    private String parametarIme = "";
    private String parametarPrezime = "";
    private Klijent klijent = new Klijent(0, "", "", "", "", "",
            new FitnesProfil(0, ""));

    public TableModelKlijenti() {
        try {
            lista = ClientController.getInstance().
                    getAllKlijent(new Klijent(0, "", "", "", "", "",
                            new FitnesProfil(0, "")));
        } catch (Exception ex) {
            Logger.getLogger(TableModelKlijenti.class.getName()).log(Level.SEVERE, null, ex);
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
        Klijent k = lista.get(row);

        switch (column) {
            case 0:
                return k.getKlijentID();
            case 1:
                return k.getIme();
            case 2:
                return k.getPrezime();
            case 3:
                return k.getEmail();
            case 4:
                return k.getTelefon();
            case 5:
                return k.getStavPremaTreningu();
            case 6:
                return k.getFitnesProfil();

            default:
                return null;
        }
    }

    public Klijent getSelectedKlijent(int row) {
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
            Logger.getLogger(TableModelKlijenti.class.getName()).log(Level.SEVERE, null, ex);
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

            klijent.setIme(parametarIme.toLowerCase());
            klijent.setPrezime(parametarPrezime.toLowerCase());

            lista = ClientController.getInstance().getAllKlijent(klijent);
            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Klijent> getLista() {
        return lista;
    }

}
