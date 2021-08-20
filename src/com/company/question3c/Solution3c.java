package com.company.question3c;
import java.math.BigInteger;
import java.util.HashMap;

public class Solution3c {


    public static int solution(String x){
        return solve(new BigInteger(x));
    }

    private static HashMap<BigInteger , Integer> hashMap = new HashMap<>();

    private static final BigInteger zero= new BigInteger("0");
    private static final BigInteger one= new BigInteger("1");
    private static final BigInteger two= new BigInteger("2");
    public static int solve(BigInteger x){
        if(x.equals(one)) return 0;
        if(x.equals(two)) return 1;
        int result ;

        if(hashMap.get(x) != null) return hashMap.get(x);
        if(x.mod(two).equals(zero))
            result =  1+solve(x.shiftRight(1) );
        else
            result =  1+Math.min(  solve(x.add(one)) , solve(x.subtract(one))  );

        hashMap.put(x , result);
        return  result;
    }




    public static void main(String[] args)
    {
        System.out.println(Solution3c.solution("3"));
    }

}
