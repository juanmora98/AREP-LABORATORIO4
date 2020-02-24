package edu.escuelaing.arep.app.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBase{

    public static String InicializarDataBase()
    {
        String res = "";
        try {  
            Class.forName("org.postgresql.Driver");
            String host = "ec2-34-200-116-132.compute-1.amazonaws.com";
            String database = "d6768kis84tncl";
            String port = "5432";
            String user = "brqpwcxlrpyphk";
            String passwd = "42534f27ba7b1d5c22ae945d515588f09f98e532044cddcdac2ea38e63dda50c";
            Connection con = DriverManager.getConnection( "jdbc:postgresql://"+ host + ":" + port + "/" + database, user, passwd);
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from editores");
            int i = 0;
            while(rs.next()){
                res += "Line" + i + ": " + rs.getInt(1) + "  " + rs.getString(2) + "</br>";
                i++;
            }
                
            con.close();  
        } catch(Exception e) { 
            System.out.println(e);
        }
        return res;
    }

}