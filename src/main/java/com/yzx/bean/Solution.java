package com.yzx.bean;

class Solution {
    public double myPow(double x, int n) {
        long N = Math.abs((long)n);
        double ans = 1.0;
        double xx = x;
        while (N != 0){
            if(N % 2 == 1){
                ans *= xx;
            }
            xx *= xx;
            N /= 2;
        }
        return n > 0 ? ans : (1.0) / ans ;

        Math.pow()
    }
}