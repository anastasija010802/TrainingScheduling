/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.FitnesProfil;
import domain.Klijent;
import domain.Program;
import domain.Trener;
import domain.Trening;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author PC
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
               
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
               
                Response response = handleRequest(request);
              
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.ADD_KLIJENT:
                    ServerController.getInstance().addKlijent((Klijent) request.getData());
                    break;
                case Operation.ADD_PROGRAM:
                    ServerController.getInstance().addProgram((Program) request.getData());
                    break;
                case Operation.DELETE_KLIJENT:
                    ServerController.getInstance().deleteKlijent((Klijent) request.getData());
                    break;
                case Operation.DELETE_PROGRAM:
                    ServerController.getInstance().deleteProgram((Program) request.getData());
                    break;
                case Operation.UPDATE_KLIJENT:
                    ServerController.getInstance().updateKlijent((Klijent) request.getData());
                    break;
                case Operation.UPDATE_PROGRAM:
                    ServerController.getInstance().updateProgram((Program) request.getData());
                    break;
                case Operation.GET_ALL_FITNES_PROFIL:
                    response.setData(ServerController.getInstance().getAllFitnesProfil((FitnesProfil) request.getData()));
                    break;
                case Operation.GET_ALL_KLIJENT:
                    response.setData(ServerController.getInstance().getAllKlijent((Klijent) request.getData()));
                    break;
                case Operation.GET_ALL_PROGRAM:
                    response.setData(ServerController.getInstance().getAllProgram((Program) request.getData()));
                    break;
                case Operation.GET_ALL_TRENING:
                    response.setData(ServerController.getInstance().getAllTrening((Trening) request.getData()));
                    break;
                    case Operation.ADD_TRENING:
                    ServerController.getInstance().addTrening((Trening) request.getData());
                    break;
                    case Operation.DELETE_TRENING:
                    ServerController.getInstance().deleteTrening((Trening)request.getData());
                    break;
                    case Operation.UPDATE_TRENING:
                    ServerController.getInstance().updateTrening((Trening) request.getData());
                    break;
                case Operation.LOGIN:
                    Trener trener = (Trener) request.getData();
                    Trener admin = ServerController.getInstance().login(trener);
                    response.setData(admin);
                    break;
                case Operation.LOGOUT:
                    Trener ulogovani = (Trener) request.getData();
                    ServerController.getInstance().logout(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception ex) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(ex);
        }
        return response;
    }

}
