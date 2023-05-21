package com.bobocode.fp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    private static List<List<Integer>> staticTreeList = new ArrayList<>();

    public int solution(Tree t) {
        int[] numberOfVisibleNodes = new int[1];

        staticTreeList.add(new ArrayList<>(Arrays.asList(t.x)));
        buildTreeList(new ArrayList<>(Arrays.asList(t.x)), t);

        staticTreeList.stream().forEach(i -> {
            Integer lastElement = i.get(i.size() - 1);

            if (i.size() == 1) {
                numberOfVisibleNodes[0]++;
            } else if (i.stream().filter(j -> j < lastElement).collect(Collectors.toList()).size() > 0) {
                numberOfVisibleNodes[0]++;
            }

        });

        return numberOfVisibleNodes[0];
    }

    public void buildTreeList(List<Integer> list, Tree t) {
        List<Integer> list2 = new ArrayList<>(list);
        List<List<Integer>> treeList = new ArrayList<>();
        if (null != t.l) {
            list.add(t.l.x);
            treeList.add(new ArrayList<Integer>(list));
            buildTreeList(list, t.l);
        }
        list = list2;
        if (null != t.r) {
            list.add(t.r.x);
            treeList.add(new ArrayList<Integer>(list));
            buildTreeList(list, t.r);
        }
        staticTreeList.addAll(treeList);
    }
}
