/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.StavkaPrograma;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelStavkePrograma extends AbstractTableModel {

    private ArrayList<StavkaPrograma> lista;
    private String[] kolone = {"Rb", "Trening", "Dan"};
    private int rb;

    public TableModelStavkePrograma() {
        lista = new ArrayList<>();
    }

    public TableModelStavkePrograma(ArrayList<StavkaPrograma> stavkePrograma) {
        lista = stavkePrograma;
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
        StavkaPrograma sp = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        switch (column) {
            case 0:
                return sp.getRb();
            case 1:
                return sp.getTrening();
            case 2:
                return sdf.format(sp.getDan());

            default:
                return null;
        }
    }

    public ArrayList<StavkaPrograma> getLista() {
        return lista;
    }

    public void dodajStavku(StavkaPrograma sp) {
        rb = lista.size();
        sp.setRb(++rb);
        lista.add(sp);
        fireTableDataChanged();
    }

    public void obrisiStavku(int row) {
        lista.remove(row);

        rb = 0;
        for (StavkaPrograma stavkaPrograma : lista) {
            stavkaPrograma.setRb(++rb);
        }

        fireTableDataChanged();
    }

    public boolean postojiDan(Date dan) {
        for (StavkaPrograma stavkaPrograma : lista) {
            if (stavkaPrograma.getDan().equals(dan)) {
                return true;
            }
        }
        return false;
    }

}
