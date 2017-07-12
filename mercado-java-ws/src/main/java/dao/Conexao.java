package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	private static Conexao instance;

	private Conexao() {
	}

	public static Conexao getInstanceof() {
		if (instance == null) {
			instance = new Conexao();
		}
		return instance;
	}

	public Connection getConnection() throws Exception{
     
		try{
			String driver = "org.postgresql.Driver";
			String url = "jdbc:postgresql://localhost/mercado";
			String usr = "postgres";
            String pswrd = "123456";

			Class.forName(driver);

            return DriverManager.getConnection(url, usr, pswrd);
        } catch (Exception e){
            throw e;
        }
    }
}
