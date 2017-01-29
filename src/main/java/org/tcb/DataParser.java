package org.tcb;

import org.apache.kafka.streams.KeyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tcb.avro.type_a;
import org.tcb.avro.type_b;
import org.tcb.avro.type_c;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;


public class DataParser {
    private static Logger log = LoggerFactory.getLogger(org.tcb.DataParser.class);


    public static KeyValue<String, type_a> parseDataTypeA(String type, String line) {
        try {
            type_a data = new type_a();

            String[] matcher = line.split(";", -1);
            if (matcher[0].length() != 0) {
                data.setNUMERO(Long.parseLong(matcher[0]));
            }
            data.setID(matcher[1]);
            data.setCODEMESSAGE(matcher[2]);
            data.setMONTANTTTC(NumberFormat.getInstance(Locale.FRANCE).parse(matcher[3]).floatValue());
            data.setDATEECHEANCEFACTURE(matcher[4]);
            data.setDATEECHEANCEAPUREMENT(matcher[5]);

            data.setMARQUEUR4(matcher[6]);
            data.setMARQUEUR5(matcher[7]);


            return new KeyValue<String, type_a>(type, data);
        } catch (IllegalStateException e) {
            return new KeyValue<>(null, null);
        } catch (ParseException e) {
            return new KeyValue<>(null, null);
        }


    }


    public static KeyValue<String, type_b> parseDataTypeB(String type, String line) {
        try {
            type_b data = new type_b();

            String[] matcher = line.split(";", -1);
            if (matcher[0].length() != 0) {
                data.setNUMERO(Long.parseLong(matcher[0]));
            }
            data.setID(matcher[1]);
            data.setCODEMESSAGE(matcher[2]);

            data.setDATEACTIONCOMMERCIALE(matcher[4]);

            data.setCIVILITE(matcher[6]);
            data.setNOM(matcher[7]);


            return new KeyValue<String, type_b>(type, data);
        } catch (IllegalStateException e) {
            return new KeyValue<>(null, null);
        }


    }


    public static KeyValue<String, type_c> parseDataTypeC(String type, String line) {
        try {
            type_c data = new type_c();

            String[] matcher = line.split(";", -1);
            data.setNUMEROBP(Long.parseLong(matcher[0]));
            if (matcher[1].length() != 0) {
                data.setNUMEROPDL(Long.parseLong(matcher[1]));
            }
            data.setIDCOMMUNICATION(matcher[2]);
            data.setCIVILITE(matcher[3]);
            data.setNOMBP(matcher[4]);
            data.setPRENOMBP(matcher[5]);
            data.setADRESSEMAIL(matcher[6]);
            if (matcher[7].length() != 0) {
                data.setTELFIXE(Long.parseLong(matcher[7]));
            }
            if (matcher[8].length() != 0) {
                data.setTELBUREAU(Long.parseLong(matcher[8]));
            }
            if (matcher[9].length() != 0) {
                data.setTELPORTABLE(Long.parseLong(matcher[9]));
            }
            data.setTYPEMESSAGE(matcher[10]);
            data.setCANALDEFAUT(matcher[11]);
            data.setCODECAMPAGNE(matcher[12]);
            data.setCODEINCA(matcher[13]);
            data.setCODEELEMCAMPAGNE(matcher[14]);
            data.setDESIELEMCAMPAGNE(matcher[15]);
            data.setTEMOIN(matcher[16]);
            data.setVALEURVARIABLE1(matcher[17]);
            data.setVALEURVARIABLE2(matcher[18]);


            return new KeyValue<String, type_c>(type, data);
        } catch (IllegalStateException e) {
            return new KeyValue<>(null, null);
        }


    }


}