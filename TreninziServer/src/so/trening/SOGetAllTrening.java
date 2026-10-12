/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class SOGetAllTrening extends AbstractSO {

    private ArrayList<Trening> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Trening)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Trening!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> treninzi = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Trening>) (ArrayList<?>) treninzi;
    }

    public ArrayList<Trening> getLista() {
        return lista;
    }

}
