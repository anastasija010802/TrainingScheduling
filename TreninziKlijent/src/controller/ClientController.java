/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.FitnesProfil;
import domain.Klijent;
import domain.Program;
import domain.Trener;
import domain.Trening;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author PC
 */
public class ClientController {

    private static ClientController instance;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Trener login(Trener trener) throws Exception {
        return (Trener) sendRequest(Operation.LOGIN, trener);
    }

    public void logout(Trener ulogovani) throws Exception {
        sendRequest(Operation.LOGOUT, ulogovani);
    }

    public void addKlijent(Klijent klijent) throws Exception {
        sendRequest(Operation.ADD_KLIJENT, klijent);
    }

    public void addProgram(Program program) throws Exception {
        sendRequest(Operation.ADD_PROGRAM, program);
    }

    public void deleteKlijent(Klijent klijent) throws Exception {
        sendRequest(Operation.DELETE_KLIJENT, klijent);
    }

    public void deleteProgram(Program program) throws Exception {
        sendRequest(Operation.DELETE_PROGRAM, program);
    }

    public void updateKlijent(Klijent klijent) throws Exception {
        sendRequest(Operation.UPDATE_KLIJENT, klijent);
    }

    public void updateProgram(Program program) throws Exception {
        sendRequest(Operation.UPDATE_PROGRAM, program);
    }

    public ArrayList<Klijent> getAllKlijent(Klijent klijent) throws Exception {
        return (ArrayList<Klijent>) sendRequest(Operation.GET_ALL_KLIJENT, klijent);
    }

    public ArrayList<Program> getAllProgram(Program program) throws Exception {
        return (ArrayList<Program>) sendRequest(Operation.GET_ALL_PROGRAM, program);
    }

    public ArrayList<FitnesProfil> getAllFitnesProfil(FitnesProfil fitnesProfil) throws Exception {
        return (ArrayList<FitnesProfil>) sendRequest(Operation.GET_ALL_FITNES_PROFIL, fitnesProfil);
    }

    public ArrayList<Trening> getAllTrening(Trening trening) throws Exception {
        return (ArrayList<Trening>) sendRequest(Operation.GET_ALL_TRENING, trening);
    }

    public void addTrening(Trening trening) throws Exception {
        sendRequest(Operation.ADD_TRENING, trening);
    }

    private synchronized Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }

    }

    public void updateTrening(Trening t) throws Exception {
        sendRequest(Operation.UPDATE_TRENING, t);
    }

    public void deleteTrening(Trening t) throws Exception {
        sendRequest(Operation.DELETE_TRENING, t);
    }

}
