package org.tcb.utils;

import org.apache.avro.Schema;
import org.tcb.avro.type_a;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarak on 24/01/17.
 */
public class test {

    public static void main(String[] args) throws ClassNotFoundException {

        Schema schema = type_a.getClassSchema();

        List<String> fieldValues = new ArrayList<>();
        for(Schema.Field field : schema.getFields()) {
            fieldValues.add(field.name());
        }

        //Iterate the arraylist
        for(String value: fieldValues)  {
           System.out.println(value);


        }
        for(String value: fieldValues)  {
            System.out.println(value);


        }
    }


    }

