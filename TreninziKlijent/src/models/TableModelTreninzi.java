/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Trening;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelTreninzi extends AbstractTableModel implements Runnable{
    ArrayList<Trening> lista;
    String [] kolone={"ID","Naziv","Opis"};
    String naziv="";
    Trening trening= new Trening(0, "", "");
    
    public  TableModelTreninzi(){
        try {
            lista= ClientController.getInstance().getAllTrening(new Trening(0, "", ""));
        } catch (Exception ex) {
            Logger.getLogger(TableModelTreninzi.class.getName()).log(Level.SEVERE, null, ex);
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
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
        refreshTable();
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Trening t= lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getTreningID();
            case 1:
                return t.getNaziv();
            case 2:
                return t.getOpis();
            default:
                return null;
        }
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

    public void refreshTable() {
        try {
            trening.setNaziv(naziv);
           lista= ClientController.getInstance().getAllTrening(trening);
            fireTableDataChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Trening getTrening(int row) {
        return lista.get(row);
    }
    
}
