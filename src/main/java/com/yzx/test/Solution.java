package com.yzx.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        dfs(res,new ArrayList<>(),target,0,candidates);
        return res;
    }
    
    public void dfs(List<List<Integer>> res,List<Integer> path,int target,int index,int[] candidatese){
        if(target == 0){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidatese.length; i++) {
            if(i > index && candidatese[i - 1] == candidatese[i]){
                continue;
            }
            if(target - candidatese[i] < 0){
                break;
            }
            path.add(candidatese[i]);
            dfs(res,path,target - candidatese[i],i + 1,candidatese);
            path.remove(path.size() - 1);
        }
    }
}