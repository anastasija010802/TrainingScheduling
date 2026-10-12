/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.program;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Program;
import domain.StavkaPrograma;
import java.util.ArrayList;
import java.util.HashMap;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOUpdateProgram extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Program)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Program!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Program p = (Program) ado;

        
        DBBroker.getInstance().update(p);

       
        ArrayList<StavkaPrograma> stareStavke
                = (ArrayList<StavkaPrograma>) (ArrayList<?>) DBBroker.getInstance()
                        .select(new StavkaPrograma(p, 0, null, null));

        HashMap<Integer, StavkaPrograma> mapaStarih = new HashMap<>();
        for (StavkaPrograma st : stareStavke) {
            mapaStarih.put(st.getRb(), st);
        }

       
        HashMap<Integer, StavkaPrograma> mapaNovih = new HashMap<>();
        for (StavkaPrograma nova : p.getStavkePrograma()) {
            mapaNovih.put(nova.getRb(), nova);
        }

       
        for (StavkaPrograma stara : stareStavke) {
            if (!mapaNovih.containsKey(stara.getRb())) {
                DBBroker.getInstance().delete(stara);
            }
        }

       
        for (StavkaPrograma nova : p.getStavkePrograma()) {
            if (mapaStarih.containsKey(nova.getRb())) {
                DBBroker.getInstance().update(nova);
            }
        }

       
        for (StavkaPrograma nova : p.getStavkePrograma()) {
            if (!mapaStarih.containsKey(nova.getRb())) {
                DBBroker.getInstance().insert(nova);
            }
        }
    }

}
