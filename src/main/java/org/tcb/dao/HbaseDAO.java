package org.tcb.dao;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HbaseDAO {

    protected Configuration conf;
    protected Connection connection;
    protected Admin admin;
    protected boolean _DEBUG = false;

    public HbaseDAO() {
        try {

            conf = HBaseConfiguration.create();
            connection = ConnectionFactory.createConnection(conf);
            admin = connection.getAdmin();
        } catch (IOException e) {

        }
    }



    public void createNamespace(String _namespaceName) {
        try {
            TableName[] tbls = admin.listTableNamesByNamespace(_namespaceName);
            for (TableName tbl : tbls) {
                admin.disableTable(tbl);
                admin.deleteTable(tbl);
            }
            admin.deleteNamespace(_namespaceName);
            NamespaceDescriptor namespace = NamespaceDescriptor.create(_namespaceName).build();
            admin.createNamespace(namespace);
        } catch (IOException e) {
        }
        try {
            System.out.println("Creating namespace " + _namespaceName + "...");
            NamespaceDescriptor namespace = NamespaceDescriptor.create(_namespaceName).build();
            admin.createNamespace(namespace);
            System.out.println("Name Space: " + _namespaceName + " Created.");
        } catch (IOException e) {

        }

    }

    public void createTable(String _nameSpace, String _tableName,String... _columnFamilys) {
        TableName tableName = TableName.valueOf(_nameSpace+":"+_tableName);
        HTableDescriptor desc = new HTableDescriptor(tableName);

        try {
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
        } catch (IOException e1) {
            // TODO Auto-generated catch block

        }

        for(String colFamily: _columnFamilys){
            HColumnDescriptor coldef = new HColumnDescriptor(Bytes.toBytes(colFamily));
            desc.addFamily(coldef);
        }

        System.out.println("Creating table " +_nameSpace+":"+_tableName +"...");

        try {
            admin.createTable(desc);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Table: " + _nameSpace +":"+_tableName + " Created");

    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    public void save(String _tableName, String _columnFamily, String _columnName, String id,  String _value) {
        if (_DEBUG)
            System.out.println("Saving value...");
        try {
            Table table = connection.getTable(TableName.valueOf(_tableName));
            Put put = new Put(Bytes.toBytes(id));
            put.addColumn(Bytes.toBytes(_columnFamily), Bytes.toBytes(_columnName), Bytes.toBytes(_value));
            table.put(put);
            table.close();
            // connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (_DEBUG)
            System.out.println("Done...");
    }

    public void delete(String _tableName, String id) {
        if (_DEBUG)
            System.out.println("Deleting row...");
        try {
            Table table = connection.getTable(TableName.valueOf(_tableName));
            Get get = new Get(Bytes.toBytes(id));
            Result result = table.get(get);
            if (!result.isEmpty()) {
                Delete delete = new Delete(Bytes.toBytes(id));
                table.delete(delete);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (_DEBUG)
            System.out.println("Done...");

    }

    public String get(String _tableName, String _columnFamily, String _columnName, String id) {
        String value = "";
        try {
            Table table = connection.getTable(TableName.valueOf(_tableName));
            Get get = new Get(Bytes.toBytes(id));
            get.addColumn(Bytes.toBytes(_columnFamily), Bytes.toBytes(_columnName));
            Result result = table.get(get);
            if (!result.isEmpty()) {
                value = Bytes.toString(result.getValue(Bytes.toBytes(_columnFamily), Bytes.toBytes(_columnName)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     *
     * @param _tableName
     * @param id
     * @param _columnFamily
     * @return HashMap
     *
     *         This methode receive a tablename, an id, and one array of
     *         columnfamily and returns all the columns with the values under
     *         the columnsfamilies
     *
     */
    public Map<String, String> getValuesUnderFamiliys(String _tableName, String id, String... _columnFamily) {
        HashMap<String, String> resultMap = new HashMap<>();
        try {
            Table table = connection.getTable(TableName.valueOf(_tableName));
            Get get = new Get(Bytes.toBytes(id));
            for (String columnFam : _columnFamily)
                get.addFamily(Bytes.toBytes(columnFam));
            Result result = table.get(get);
            resultMap.put("key", id);
            for (byte[] columnFamily : result.getMap().keySet()) {
                for (byte[] column : result.getFamilyMap(columnFamily).keySet()) {
                    resultMap.put(Bytes.toString(column), Bytes.toString(result.getValue(columnFamily, column)));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    public static final void main(String... args) {
//        HbaseDAO hbDAO = new HbaseDAO();
//        hbDAO.createNamespace("myapp");
//        hbDAO.createTable("myapp","tableA","colFam");


    }

}
