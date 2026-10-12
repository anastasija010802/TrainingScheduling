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
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllProgram   {

    private ArrayList<Program> lista;
    
        public void templateExecute(AbstractDomainObject ado) throws Exception {
        try {
            validate(ado);
            execute(ado);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Program)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Program!");
        }
    }

    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> programi = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Program>) (ArrayList<?>) programi;

        for (Program trenutniProgram : lista) {

           
            StavkaPrograma stavkaPrograma = new StavkaPrograma();
            stavkaPrograma.setProgram(trenutniProgram);

            ArrayList<StavkaPrograma> stavkeTrenutnogPrograma
                    = (ArrayList<StavkaPrograma>) (ArrayList<?>) DBBroker.getInstance().select(stavkaPrograma);

            trenutniProgram.setStavkePrograma(stavkeTrenutnogPrograma);
        }
    }

    public ArrayList<Program> getLista() {
        return lista;
    }

    
     public void commit() throws SQLException {
        DBBroker.getInstance().getConnection().commit();
    }

    public void rollback() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    }
}
