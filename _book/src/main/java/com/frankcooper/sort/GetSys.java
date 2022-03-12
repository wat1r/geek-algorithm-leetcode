package com.frankcooper.sort;

public class GetSys {
    public static void main(String[] args) {
        System.out.printf("bigbang:%s\n", System.getenv("bigbang"));
        System.out.printf("SMALLBANG:%s\n", System.getenv("SMALLBANG"));
        System.out.printf("AIRFLOW_HOME:%s\n", System.getenv("AIRFLOW_HOME"));
        System.out.printf("PATH:%s\n", System.getenv("PATH"));
    }
}
