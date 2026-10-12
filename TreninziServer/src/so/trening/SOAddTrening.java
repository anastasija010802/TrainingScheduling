/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.trening;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Trening;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOAddTrening extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Trening) ) {
            throw new Exception("Prosledjeni objekat nije instanca klase Trening!");
        }
        
        Trening trening= (Trening) ado;
        
        ArrayList<Trening> treninzi= (ArrayList<Trening>) (ArrayList<?>)DBBroker.getInstance().select(ado);
        
        for (Trening trening1 : treninzi) {
            if (trening.getNaziv().equals(trening1.getNaziv())) {
                throw new Exception("Trening sa ovim nazivom vec postoji");
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
    
}
