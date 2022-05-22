package com.namedAlgos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KadaneAlgorithm {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] str = line.trim().split(",");
        int[] arr = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
        int max = applyKadaneAlgo(arr);
        System.out.println(max);
    }

    private static int applyKadaneAlgo(int arr[]) {
        int result = Integer.MIN_VALUE;
        int max = 0;
        for (int i : arr) {
            max = max + i;
            if (result < max)
                result = max;
            if (max < 0)
                max = 0;
        }
        return result;
    }
}
