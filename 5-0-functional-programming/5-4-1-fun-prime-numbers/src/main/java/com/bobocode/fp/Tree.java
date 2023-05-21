package com.bobocode.fp;

public class Tree {

    public int x;
    public Tree l;
    public Tree r;

    public static void main(String[] args) {

        Tree tree = new Tree();
        tree.x = 5;

        Tree left3_left20 = new Tree();
        left3_left20.x = 20;
        Tree left3_right21 = new Tree();
        left3_right21.x = 21;

        Tree right10_left1 = new Tree();
        right10_left1.x = 1;

        Tree left5_left3 = new Tree();
        left5_left3.x = 3;
        left5_left3.l = left3_left20;
        left5_left3.r = left3_right21;

        Tree left5_right10 = new Tree();
        left5_right10.x = 10;
        left5_right10.l = right10_left1;

        tree.l = left5_left3;
        tree.r = left5_right10;


        //design tree;

        System.out.println(new Solution().solution(tree));



    }

}
