package com.company;

import java.util.*;
import java.io.*;

public class Notepad {


    public static void main (String[]args){


        DoLogin dologin = new DoLogin();
        System.out.println("Numarul de utilizatori gasiti in baza de date: " + dologin.loginList.size());
        // daca searchul e ok apel altfel stai aici

        boolean isLogin = dologin.loginUntilSuccess();
        if(isLogin) {
            launchProgram();

        }

    }


    private static void launchProgram() {

        Runtime rs = Runtime.getRuntime();

        try {
            rs.exec("gedit"); // notepad for windows; gedit for linux
        } catch (IOException e) {
            System.out.println(e);
        }
    }


}
