package org.teipir.softeng;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
/*
This class create JTable from Database table.
 */
public class TableToJTable{
    //private String table;
    private Connection con;
    public TableToJTable(Connection con){
        this.con=con;
    }
    /**
     * This method return JTable object created from Database table having same data
    */
    
    public JTable getTable(String table)throws Exception{
        JTable t1=new JTable();
        DefaultTableModel dm=new DefaultTableModel();
        Statement st=con.createStatement();   
        ResultSet rs=st.executeQuery("select * from "+table);
        ResultSetMetaData rsmd=rs.getMetaData();
        //Coding to get columns-
        int cols=rsmd.getColumnCount();
        String c[]=new String[cols];
        for(int i=0;i<cols;i++){
            c[i]=rsmd.getColumnName(i+1);
            dm.addColumn(c[i]);
        }
        //get data from rows
        Object row[]=new Object[cols];
        while(rs.next()){
             for(int i=0;i<cols;i++){
                    row[i]=rs.getString(i+1);
                }
            dm.addRow(row);
        }
        t1.setModel(dm);
        con.close();
        return t1;
    }
 
    public JTable getTable(String table,String query)throws Exception{
        JTable t1=new JTable();
        DefaultTableModel dm=new DefaultTableModel();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        ResultSetMetaData rsmd=rs.getMetaData();
        //Coding to get columns-
        int cols=rsmd.getColumnCount();
        String c[]=new String[cols];
        for(int i=0;i<cols;i++){
            c[i]=rsmd.getColumnName(i+1);
            dm.addColumn(c[i]);
        }


        //get data from rows
        Object row[]=new Object[cols];
        while(rs.next()){
             for(int i=0;i<cols;i++){
                    row[i]=rs.getString(i+1);
                }
            dm.addRow(row);
        }
        t1.setModel(dm);
        con.close();
        return t1;
    }


    public static void main(String ar[])throws Exception{
        JFrame f=new JFrame("Title");
        f.setTitle("Auction Manager");
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl="jdbc:mysql://localhost:3306/warehouse";
    	String username="root";
    	String password="12345";
        Connection con=DriverManager.getConnection(connectionUrl,username,password);
        org.teipir.softeng.TableToJTable obj = new org.teipir.softeng.TableToJTable(con);
        JScrollPane sp=new JScrollPane(obj.getTable("warehousefactory"));
        f.getContentPane().add(sp);
        f.setBounds(200,200,400,349);
        f.setVisible(true);       
    }
}

