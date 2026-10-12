/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author PC
 */
public interface Operation {

    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;

    public static final int ADD_KLIJENT = 2;
    public static final int DELETE_KLIJENT = 3;
    public static final int UPDATE_KLIJENT = 4;
    public static final int GET_ALL_KLIJENT = 5;

    public static final int ADD_TRENING = 6;
    public static final int DELETE_TRENING = 7;
    public static final int UPDATE_TRENING = 8;
    public static final int GET_ALL_TRENING = 9;

    public static final int ADD_PROGRAM = 10;
    public static final int DELETE_PROGRAM = 11;
    public static final int UPDATE_PROGRAM = 12;
    public static final int GET_ALL_PROGRAM = 13;

    public static final int GET_ALL_FITNES_PROFIL = 14;

}
