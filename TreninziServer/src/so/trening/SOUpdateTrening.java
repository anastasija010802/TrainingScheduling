/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.trening;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Trening;
import javax.swing.JOptionPane;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOUpdateTrening extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Trening)) {
           throw new Exception("Prosledjeni objekat nije instanca klase Trening"); 
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
    
}
