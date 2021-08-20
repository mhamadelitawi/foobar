package com.company.question2a;

import java.util.ArrayList;
import java.util.Collections;

public class Solution2 {

    public static String[] solution(String[] l){
        if(l.length == 1) return l;
        ArrayList<Digit> array =  Digit.toArrayListDigigit( l);
        Collections.sort(array);
        return Digit.ArrayToList(array);
    }

    public static void main(String[] args) {
       Solution2.print(  Solution2.solution(new String[]{"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"})  );
    }

    public static void print(String[] x)
    {
        System.out.println(x[0]);
        int s = x.length;
        for (int i = 1 ;  i < s ; i++)
            System.out.print(","+x[i]);

    }

}


class Digit implements Comparable<Digit>
{
    public Integer major;
    public Integer minor;
    public Integer revision;

    public String val;

    public Digit(String x)
    {

        String[] t = x.split("\\.");
        try {
            major = Integer.parseInt(t[0] );
            minor = Integer.parseInt(t[1] );
            revision = Integer.parseInt(t[2] );
        }catch (Exception e){}
        val = x;
     }

    public int subCompare(Integer x , Integer y)
    {
        if(x == null && y == null) return 0;
        if(x == null && y != null) return -1;
        if(x != null && y == null) return 1;
        return x.compareTo(y);
    }

    @Override
    public int compareTo(Digit o) {
        if(major < o.major) return -1;
        if(major > o.major) return 1;
        int res = subCompare(minor, o.minor);
        if(res != 0) return res;
        return subCompare(revision, o.revision);
    }

    public static ArrayList<Digit> toArrayListDigigit(String[] l)
    {
        ArrayList<Digit> digits = new ArrayList();
        for(String x : l)
            digits.add(new Digit(x));
        return digits;
    }

    public static String[] ArrayToList(ArrayList<Digit> array)
    {
        String[] ret = new String[array.size()];
        int s = array.size();
        for (int i = 0 ; i < array.size() ; i++)
            ret[i] = array.get(i).val;
        return ret;
    }

}