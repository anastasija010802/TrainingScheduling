/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.login;

import controller.ServerController;
import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Trener;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOLogin extends AbstractSO {

    Trener ulogovani;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Trener)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Trener!");
        }

        Trener t = (Trener) ado;

        for (Trener trener : ServerController.getInstance().getUlogovaniTreneri()) {
            if (trener.getKorisnickoIme().equals(t.getKorisnickoIme())) {
                throw new Exception("Ovaj trener je vec ulogovan na sistem!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Trener t = (Trener) ado;

        ArrayList<Trener> treneri
                = (ArrayList<Trener>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Trener trener : treneri) {
            if (trener.getKorisnickoIme().equals(t.getKorisnickoIme())
                    && trener.getLozinka().equals(t.getLozinka())) {
                ulogovani = trener;
                ServerController.getInstance().getUlogovaniTreneri().add(trener);
                ServerController.getInstance().setujBrojKlijenataa();
                return;
            }
        }

        throw new Exception("Ne postoji trener sa tim kredencijalima.");

    }

    public Trener getUlogovani() {
        return ulogovani;
    }

}
