package com.example.demo.lambda;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class QueryGrantTypeService {

    private  static  Map<String, BiFunction<Integer,Integer,Integer>> grantTypeMap=new HashMap<>();

   static{
       grantTypeMap.put("add",(a,b)->Operator.add(a,b));
       grantTypeMap.put("subtract",(a,b)->Operator.subtract(a,b));
       grantTypeMap.put("multiply",(a,b)->Operator.multiply(a,b));
   }

    public Integer getResult(String resourceType,int a ,int b){
        BiFunction<Integer,Integer,Integer> result=grantTypeMap.get(resourceType);
            return result.apply(a,b);
    }
}
