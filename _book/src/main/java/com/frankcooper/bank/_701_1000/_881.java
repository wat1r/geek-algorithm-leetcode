package com.frankcooper.bank._701_1000;

import java.util.Arrays;

public class _881 {


    static _881 handler = new _881();

    public static void main(String[] args) {
        int[] people = {3, 2, 2, 1};
        int limit = 3;
        handler.numRescueBoats(people, limit);
    }


    public int numRescueBoats(int[] people, int limit) {

        Arrays.sort(people);
        int l = 0, r = people.length - 1;
        int ans = 0;
        while (l <= r) {
            if (people[r] == limit) {
                r--;
                ans++;
                continue;
            }
            if (people[l] + people[r] <= limit) {
                l++;
                r--;
                ans++;
            } else {
                r--;
                ans++;
            }
        }
        return ans;
    }
}
