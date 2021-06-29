package com.frankcooper;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        int A = in.nextInt(), B = in.nextInt();
        String s = "O JOGO DUROU %d HORA(S)";
        if (A == B) System.out.println(String.format(s, 24));
        else {
            if (A > B) B += 24;
            System.out.println(String.format(s, B - A));
        }

    }

}
