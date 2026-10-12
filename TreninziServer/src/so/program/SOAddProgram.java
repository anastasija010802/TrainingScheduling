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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOAddProgram extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Program)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Program!");
        }

        Program p = (Program) ado;

        if (!p.getDatumPocetka().after(new Date())) {
            throw new Exception("Datum pocetka mora biti posle danasnjeg datuma!");
        }

        if (!p.getDatumPocetka().before(p.getDatumZavrsetka())) {
            throw new Exception("Datum pocetka mora biti pre datuma zavrsetka!");
        }

        if (p.getCena() <= 0) {
            throw new Exception("Cena mora biti veca od 0!");
        }

        if (p.getStavkePrograma().isEmpty()) {
            throw new Exception("Program mora imati barem jednu stavku!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);

        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        int noviProgramID = tableKeys.getInt(1);

       
        Program noviProgram = (Program) ado;
        noviProgram.setProgramID(noviProgramID);

       
        for (StavkaPrograma stavkaPrograma : noviProgram.getStavkePrograma()) {
            stavkaPrograma.setProgram(noviProgram);
            DBBroker.getInstance().insert(stavkaPrograma);
        }
    }

}
