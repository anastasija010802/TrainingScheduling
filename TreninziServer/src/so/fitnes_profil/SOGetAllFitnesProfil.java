/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.fitnes_profil;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.FitnesProfil;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllFitnesProfil extends AbstractSO {

    private ArrayList<FitnesProfil> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof FitnesProfil)) {
            throw new Exception("Prosledjeni objekat nije instanca klase FitnesProfil!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> fitnesProfili = DBBroker.getInstance().select(ado);
        lista = (ArrayList<FitnesProfil>) (ArrayList<?>) fitnesProfili;
    }

    public ArrayList<FitnesProfil> getLista() {
        return lista;
    }

}
