package org.tcb;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;


public class FileDecompressor {


    public static void main(String[] args) throws Exception {
        String uri = args[0];
        Configuration conf = new Configuration();

//        conf.set("fs.hdfs.impl",
//                org.apache.hadoop.hdfs.DistributedFileSystem.class.getName()
//        );
//        conf.set("fs.file.impl",
//                org.apache.hadoop.fs.LocalFileSystem.class.getName()
//        );

        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        Path[] paths = new Path[args.length];
        for (int i = 0; i < paths.length; i++) {
            paths[i] = new Path(args[i]);
        }

        FileStatus[] status = fs.listStatus(paths);
        Path[] listedPaths = FileUtil.stat2Paths(status);


        CompressionCodecFactory factory = new CompressionCodecFactory(conf);
        CompressionCodec codec = factory.getCodec(listedPaths[0]);

        if (codec == null) {
            System.err.println("No codec found for " + uri);
            System.exit(1);
        }


        KafkaDataProducer kafkaproducer = new KafkaDataProducer("data.raw");


        InputStream in = null;
        BufferedReader br = null;


        try {
            for (Path p : listedPaths) {
                System.out.println(p.toString());
                in = codec.createInputStream(fs.open(p));
                br = new BufferedReader(new InputStreamReader(in));

                String line = br.readLine();
                String type = "";

                while (line != null) {

                    if (line.contains("END") || line.contains(".txt") || (line.trim().length() == 0)) {
                        line = br.readLine();
                        continue;
                    }
                    if (line.contains("MONTANT_TTC;DATE_ECHEANCE_FACTURE")) {
                        type = "type_a";
                        line = br.readLine();
                        continue;
                    }
                    if (line.contains("DATE_ACTION_COMMERCIALE")) {
                        type = "type_b";
                        line = br.readLine();
                        continue;
                    }
                    if (line.contains("ADRESSE_MAIL;TEL_FIXE;TEL_BUREAU;TEL_PORTABLE")) {
                        type = "type_c";
                        line = br.readLine();
                        continue;
                    }

                    kafkaproducer.sendMessage(type, line);
                    line = br.readLine();

                }


            }

        } finally {
            br.close();
            IOUtils.closeStream(in);
            kafkaproducer.close();

        }

    }
}

