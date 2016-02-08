package tpJdbc;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;
public class TPJdbc {
/**
* la variable JDBC_DRIVER consiste à donner le nom de classe Pilote qui
* permet de communiquer avec la base de données dans notre CAS c ORACLE
*/
private final static String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
/**
* la variable JDBC_URL consiste à pointer l'emplacement de la base
*/
private final static String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
/**
* la variable d'instance "conn" permet d'ouvrir une connection (session) à
* la base de donner
*/
private Connection conn = null;
private SimpleDateFormat dateFormat = null;
public TPJdbc() {
dateFormat = new SimpleDateFormat("dd/MM/yyyyHH:mm", Locale.FRANCE);
}
/**
* La méthode initConnection permet d'ouvrir la connection vers la base en
* données à traversle login et le password
*
* @param login
* : l'utilisateur dela base de données dans notre cas c "tpjee"
* @param password
* :le mot de passe de la base de données dans notre cas c
* "tpjee"
* @throws SQLException
*/
public void initConnection(String login, String password) throws SQLException {
try {
	Class.forName(JDBC_DRIVER);
	conn = DriverManager.getConnection(JDBC_URL, login, password);
}
catch (ClassNotFoundException e) 
	{
	throw new SQLException("Impossible de trouver le driver JDBC : "+ e.getMessage());
	}
}
/**
* La méthode close Connection permet de fermer la connection depuis la base
* en données
*
*/
public void closeConnection() {
try {
	if (conn != null) 
		{conn.close();}
}catch (SQLException e) {
// Exception ignoree
}
}
public void supprimer() throws SQLException {

	Statement stmt = conn.createStatement();
	Scanner s=new Scanner(System.in);
	System.out.println("Donner le numero de vol :\n");
	String Numvol=s.nextLine();
	String    sql= "DELETE FROM VOL WHERE Numvol='"+Numvol+"'";
	stmt.executeUpdate(sql);
	stmt.close();
	
}
public void insertion() throws SQLException {

	Statement stmt = conn.createStatement();
	Scanner s=new Scanner(System.in);
	System.out.println("Donner Numero de Vol : ");
	String Numvol=s.nextLine();
	System.out.println("Donner Heure de depart : ");
	String Heure_depart=s.nextLine();
	System.out.println("Donner Heur d'arriver : ");
	String Heure_arrive=s.nextLine();
	System.out.println("Donner Ville  de depart: ");
	String Ville_depart=s.nextLine();
	System.out.println("Donner Ville  d`\"arriver: ");
	String Ville_arrivee =s.nextLine();
	
	stmt.executeQuery("INSERT INTO VOL values('"+Numvol+"','"+Heure_depart+"','"+Heure_arrive+"','"+Ville_depart+"','"+Ville_arrivee+"')");
	stmt.close();

	
}
public void modifier() throws SQLException {
	String sql;
	Statement stmt = conn.createStatement();
	Scanner s=new Scanner(System.in);Scanner s1=new Scanner(System.in);
	System.out.println("Donner numero de vol :");
	String Numvol=s.nextLine();
	int x=1;
	while(x!=0)
	{	
	System.out.println("Taper :\n1=>pour Modifier l'Heure de depart\n2=>pour Modifier l'Heure d'arrivee\n3=>pour Modifier la Ville de depart\n4=>pour Modifier la Ville d'arrivee\n0=>pour Quitter ");
	x=s.nextInt();
	switch(x)
	{case 1:	System.out.println("Donner Heure de depart : \n");
				String Heure_depart=s1.nextLine();
				sql = "UPDATE VOL " +
		                   "SET Heure_depart = '"+Heure_depart+"' WHERE Numvol='"+Numvol+"'";
			      stmt.executeUpdate(sql);	 
		break;
	case 2:		System.out.println("Donner Heur d'arrivee : ");
				String Heure_arrive=s1.nextLine();
				sql = "UPDATE VOL " +
		                   "SET Heure_arrive = '"+Heure_arrive+"' WHERE Numvol='"+Numvol+"'";
			      stmt.executeUpdate(sql);	break;
	case 3:
		System.out.println("Donner Ville  de depart: ");
		String Ville_depart=s1.nextLine();
		sql = "UPDATE VOL " +
                "SET Ville_depart = '"+Ville_depart+"' WHERE Numvol='"+Numvol+"'";
	      stmt.executeUpdate(sql);break;
	case 4:
		System.out.println("Donner Ville  d'arrivee: ");
		String Ville_arrivee =s1.nextLine();
		sql = "UPDATE VOL " +
                "SET Ville_arrivee = '"+Ville_arrivee+"' WHERE Numvol='"+Numvol+"'";
	      stmt.executeUpdate(sql);	break;
	}
	}
	stmt.close();

}

public void displayFlightInformation(String id) throws SQLException {
// à remplir!
	Statement stmt=null;
	stmt=conn.createStatement();
	ResultSet rs= stmt.executeQuery("SELECT * FROM VOL WHERE NUMVOL='"+id+"'");
	while(rs.next())
	{
		String Num_vol=rs.getString("Numvol");
		String Heure_depart=rs.getString("Heure_depart");
		String Heure_arrive=rs.getString("Heure_arrive");
		String Ville_depart=rs.getString("Ville_depart");
		String Ville_arrivee=rs.getString("Ville_arrivee");
		
		System.out.println("Numero de Vol : "+Num_vol);
		System.out.println("Heure de depart : "+Heure_depart);
		System.out.println("Heure d'arriver : "+Heure_arrive);
		System.out.println("Ville de depart: "+Ville_depart);
		System.out.println("Ville d'arriver : "+Ville_arrivee);
		
	}
	rs.close();
	stmt.close();

	
	
}
public void Consulter_vol(String ville) throws SQLException {
	// à remplir!
		Statement stmt=null;
		stmt=conn.createStatement();
		ResultSet rs= stmt.executeQuery("SELECT * FROM VOL WHERE Ville_arrivee='"+ville+"'");
		while(rs.next())
		{
			String Num_vol=rs.getString("Numvol");
			String Heure_depart=rs.getString("Heure_depart");
			String Heure_arrive=rs.getString("Heure_arrive");
			String Ville_depart=rs.getString("Ville_depart");
			String Ville_arrivee=rs.getString("Ville_arrivee");
			
			System.out.println("Numero de Vol : "+Num_vol);
			System.out.println("Heure de depart : "+Heure_depart);
			System.out.println("Heure d'arriver : "+Heure_arrive);
			System.out.println("Ville de depart: "+Ville_depart);
			System.out.println("Ville d'arriver : "+Ville_arrivee);
			
		}
		rs.close();
		stmt.close();

	}

public static void main(String[] args) {
String login = "tpjee"; String password = "tpjee";
String numVol = "TA215";
TPJdbc myTp = new TPJdbc();
try { myTp.initConnection(login, password);
myTp.Consulter_vol("Tunis");;
} catch (SQLException e) {
e.printStackTrace();
} finally {
myTp.closeConnection();
}
}
}