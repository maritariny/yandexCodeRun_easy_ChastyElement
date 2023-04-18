package ru.maritariny;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] a = new int[n];
        String[] parts = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(parts[i]);
        }

        //System.out.println(solve(a));
        System.out.println(solveSimple(a));

        reader.close();
    }

    public static int solveSimple(int[] a) {
        int ans = 0;
        Arrays.sort(a);
        int count = 0;
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            count++;
            if ((i == a.length-1) || (a[i] != a[i+1])) {
                if (count >= max) {
                    max = count;
                    ans = a[i];
                }
                count = 0;
            }
        }
        return ans;
    }

    public static int solve(int[] a) {
        int max = 0;
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < a.length; i++) {
            int val = 0;
            if (map.containsKey(a[i])) {
                val = map.get(a[i]);
            }
            map.put(a[i], ++val);
            max = Math.max(max, val);
        }
        int finalMax = max;
        Set<Integer> keys = map.keySet().stream()
                .filter(key -> finalMax == map.get(key))
                .collect(Collectors.toSet());
        return keys.stream().max(Integer::compareTo).get();
    }
}
