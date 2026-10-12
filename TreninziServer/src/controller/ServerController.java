/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.FitnesProfil;
import domain.Klijent;
import domain.Program;
import domain.StavkaPrograma;
import domain.Trener;
import domain.Trening;
import forms.ServerForm;
import java.util.ArrayList;
import so.fitnes_profil.SOGetAllFitnesProfil;
import so.klijent.SOAddKlijent;
import so.klijent.SODeleteKlijent;
import so.klijent.SOGetAllKlijent;
import so.klijent.SOUpdateKlijent;
import so.login.SOLogin;
import so.program.SOAddProgram;
import so.program.SODeleteProgram;
import so.program.SOGetAllProgram;
import so.program.SOUpdateProgram;
import so.trening.SOAddTrening;
import so.trening.SODeleteTrening;
import so.trening.SOGetAllTrening;
import so.trening.SOUpdateTrening;

/**
 *
 * @author PC
 */
public class ServerController {

    private static ServerController instance;
    private ArrayList<Trener> ulogovaniTreneri = new ArrayList<>();
private ServerForm sf;
private  int brojKlijenata;
    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public ArrayList<Trener> getUlogovaniTreneri() {
        return ulogovaniTreneri;
    }

    public void setUlogovaniTreneri(ArrayList<Trener> ulogovaniTreneri) {
        this.ulogovaniTreneri = ulogovaniTreneri;
    }

    public Trener login(Trener administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getUlogovani();
    }

    public void addKlijent(Klijent klijent) throws Exception {
        (new SOAddKlijent()).templateExecute(klijent);
    }

    public void addProgram(Program program) throws Exception {
        (new SOAddProgram()).templateExecute(program);
    }

    public void deleteKlijent(Klijent klijent) throws Exception {
        (new SODeleteKlijent()).templateExecute(klijent);
    }

    public void deleteProgram(Program program) throws Exception {
        (new SODeleteProgram()).templateExecute(program);
    }

    public void updateKlijent(Klijent klijent) throws Exception {
        (new SOUpdateKlijent()).templateExecute(klijent);
    }

    public void updateProgram(Program program) throws Exception {
        (new SOUpdateProgram()).templateExecute(program);
    }

    public ArrayList<Klijent> getAllKlijent(Klijent klijent) throws Exception {
        SOGetAllKlijent so = new SOGetAllKlijent();
        so.templateExecute(klijent);
        return so.getLista();
    }

    public ArrayList<Program> getAllProgram(Program program) throws Exception {
        SOGetAllProgram so = new SOGetAllProgram();
        so.templateExecute(program);
        return so.getLista();
    }

    public ArrayList<FitnesProfil> getAllFitnesProfil(FitnesProfil fitnesProfil) throws Exception {
        SOGetAllFitnesProfil so = new SOGetAllFitnesProfil();
        so.templateExecute(fitnesProfil);
        return so.getLista();
    }

    public ArrayList<Trening> getAllTrening(Trening trening) throws Exception {
        SOGetAllTrening so = new SOGetAllTrening();
        so.templateExecute(trening);
        return so.getLista();
    }

    public void logout(Trener ulogovani) {
        brojKlijenata--;
        ServerController.getInstance().getSf().setBrojKlijenata(brojKlijenata);
        ulogovaniTreneri.remove(ulogovani);
    }

    public void addTrening(Trening trening) throws Exception{
        (new SOAddTrening()).templateExecute(trening);
    }

    public void deleteTrening(Trening trening) throws Exception {
        (new SODeleteTrening()).templateExecute(trening);
    }

    public void updateTrening(Trening trening) throws Exception {
        (new SOUpdateTrening()).templateExecute(trening);
    }

    public void setServerskaForma(ServerForm aThis) {
        sf= aThis;
    }

    public ServerForm getSf() {
        return sf;
    }

    public void setujBrojKlijenataa() {
        brojKlijenata++;
        ServerController.getInstance().getSf().setBrojKlijenata(brojKlijenata);
    }

    

}
