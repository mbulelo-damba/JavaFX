package application;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class A {
	
	// started to insert at 10:26 AM  04 March 2017.
	
	static ResultSet rs;
    static PreparedStatement ps;
    static Connection conn = null;;
  
    
    
    public static String randomNumber() {
		int myrand;
		
			Random rand = new Random();
			 myrand = rand.nextInt(1000) + 1;
		//	System.out.println(myrand); //this works too;
		//	System.out.println(Integer.toString(myrand)); 
			return Integer.toString(myrand);
	}
    
    
    public static int abc() {
		
			Random rand = new Random();
			int myrand = rand.nextInt(100) + 1;
		 return myrand;
	}
   
    
    public static String randomAge() {
		
			int myrand;
			
			Random rand = new Random();
			myrand = rand.nextInt(100) + 1;
			// System.out.println(myrand); //this works too;
			// System.out.println(Integer.toString(myrand));
			
			if (myrand<10){
				myrand = myrand+20;
			} else if(myrand>=10 && myrand<20){
				myrand = myrand+15;
			} else if (myrand>=20 && myrand<30) {
				myrand = myrand+6;
			} else if (myrand>=60 && myrand<70 ){
				myrand = myrand-18;
			}else if (myrand>=70 && myrand<80){
				myrand = myrand-28;
			}else if (myrand>=80 && myrand<90){
				myrand = myrand-35;
			}else if (myrand>=90 && myrand<=100){
				myrand = myrand-55;
			}else{
				myrand = myrand+0;
			}
			 
			return Integer.toString(myrand);		
	}

	
    public static String usernameHash() {
//		String salt = txtPassword.getText();
			String salt = randomNumber() + randomNumber()+ randomNumber();
			String passwordToBeHashed = randomNumber()+salt;
			String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// Add password bytes to digest
			md.update(passwordToBeHashed.getBytes());
			//Get the hash's bytes
			byte[] bytes = md.digest();
			//This bytes[] has bytes in decimal format;
			//Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<bytes.length; i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff)+ 0x100, 16).substring(1));
			}
			//Get complete hashed password in hex format
			generatedPassword = sb.toString();
			return generatedPassword;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(generatedPassword);
		return generatedPassword;

	}
    
    
    public static String passwordHash() {
//		String salt = txtPassword.getText();
			String salt = randomNumber() + randomNumber()+ randomNumber();
			String passwordToBeHashed = randomNumber()+salt;
			String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// Add password bytes to digest
			md.update(passwordToBeHashed.getBytes());
			//Get the hash's bytes
			byte[] bytes = md.digest();
			//This bytes[] has bytes in decimal format;
			//Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<bytes.length; i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff)+ 0x100, 16).substring(1));
			}
			//Get complete hashed password in hex format
			generatedPassword = sb.toString();
			return generatedPassword;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(generatedPassword);
		return generatedPassword;

	}
    

  public static Connection getSqliteConnection() throws Exception {
    String driver = "org.sqlite.JDBC";
    String url = "jdbc:sqlite:C://Login//Employee.db";
 //   String username = "userName";
 //   String password = "pass";
    Class.forName(driver); // load Sqlite driver
    Connection conn = DriverManager.getConnection(url);
    return conn;
  }

  public static Connection getMySqlConnection() throws Exception {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost/employeedata";
    String username = "root";
    String password = "mdantsane";
    Class.forName(driver); // load MySQL driver
    Connection conn = DriverManager.getConnection(url, username, password);
    return conn;
  }

  public static void main(String[] args) {

    Connection sqliteConn = null;
    Connection mysqlConn = null;
    try {
      sqliteConn = getSqliteConnection();
      mysqlConn = getMySqlConnection();
      System.out.println("sqliteConn=" + sqliteConn);
      System.out.println("mysqlConn=" + mysqlConn);
      int x = 1;
		do {
			
			if (abc()<=10){
				String SQL = "insert into employee (name,surname,age,username,password) values ('Mbulelo','Damba',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
				ps = sqliteConn.prepareStatement(SQL);
				ps.execute();
			} else if (abc()>10 && abc()<=15){
				String SQL = "insert into employee (name,surname,age,username,password) values ('Steve','Harvey',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
				ps = sqliteConn.prepareStatement(SQL);
				ps.execute();
			} else if (abc()>15 && abc()<=20){
				String SQL = "insert into employee (name,surname,age,username,password) values ('James','Harrison',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
				ps = sqliteConn.prepareStatement(SQL);
				ps.execute();
			} else if (abc()>20 && abc()<=25){
				String SQL = "insert into employee (name,surname,age,username,password) values ('Sebastian','Veron',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
				ps = sqliteConn.prepareStatement(SQL);
				ps.execute();
			} else if (abc()>25 && abc()<=30){
				String SQL = "insert into employee (name,surname,age,username,password) values ('Simphiwe','Tshabalala',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
				ps = sqliteConn.prepareStatement(SQL);
				ps.execute();
			} else if (abc()>30 && abc()<=35){
				String SQL = "insert into employee (name,surname,age,username,password) values ('Oupa','Manyisa',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
				ps = sqliteConn.prepareStatement(SQL);
				ps.execute();
			} else if (abc()>35 && abc()<=40){
				String SQL = "insert into employee (name,surname,age,username,password) values ('Steven','Pienaar',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
				ps = sqliteConn.prepareStatement(SQL);
				ps.execute();
			} else if (abc()>40 && abc()<=45){
				String SQL = "insert into employee (name,surname,age,username,password) values ('Itumeleng','Khune',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
				ps = sqliteConn.prepareStatement(SQL);
				ps.execute();
			} else if (abc()>45 && abc()<=50){
				String SQL = "insert into employee (name,surname,age,username,password) values ('David','Obua',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
				ps = sqliteConn.prepareStatement(SQL);
				ps.execute();
			} else if (abc()>50 && abc()<=55){
				String SQL = "insert into employee (name,surname,age,username,password) values ('Thabang','Lebese',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
				ps = sqliteConn.prepareStatement(SQL);
				ps.execute();
			} else if (abc()>55 && abc()<=60){
				String SQL = "insert into employee (name,surname,age,username,password) values ('Andile','Jali',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
				ps = sqliteConn.prepareStatement(SQL);
				ps.execute();
			} else if (abc()>60 && abc()<=65){
				String SQL = "insert into employee (name,surname,age,username,password) values ('Percy','Tau',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
				ps = sqliteConn.prepareStatement(SQL);
				ps.execute();
			} else if (abc()>65 && abc()<=70){
				String SQL = "insert into employee (name,surname,age,username,password) values ('Jimmy','Tau',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
				ps = sqliteConn.prepareStatement(SQL);
				ps.execute();
			} else if (abc()>70 && abc()<=75){
				String SQL = "insert into employee (name,surname,age,username,password) values ('Lucky','Lekgwathi',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
				ps = sqliteConn.prepareStatement(SQL);
				ps.execute();
			} else if (abc()>75 && abc()<=80){
				String SQL = "insert into employee (name,surname,age,username,password) values ('George','Lebese',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
				ps = sqliteConn.prepareStatement(SQL);
				ps.execute();
			} else if (abc()>80 && abc()<=85){
				String SQL = "insert into employee (name,surname,age,username,password) values ('Keagan','Dolly',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
				ps = sqliteConn.prepareStatement(SQL);
			} else if (abc()>85 && abc()<=90){
				String SQL = "insert into employee (name,surname,age,username,password) values ('Khama','Bhilliat',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
				ps = sqliteConn.prepareStatement(SQL);
				ps.execute();
			} else if (abc()>90 && abc()<=95){
				String SQL = "insert into employee (name,surname,age,username,password) values ('Steve','Komphela',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
				ps = sqliteConn.prepareStatement(SQL);
				ps.execute();
			} else if (abc()>95 && abc()<=100){
				String SQL = "insert into employee (name,surname,age,username,password) values ('Gavin','Hunt',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
				ps = sqliteConn.prepareStatement(SQL);
				ps.execute();
			}
			 System.out.println("Inserted data!!");
			 System.out.println("SQLITE ENTRY");
	//		 ps.execute();
	                                                
	     
	         ps.close();
	         
	         
	          
			  
	         try {
					
					   Statement st = sqliteConn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);

				   
	        	  st = sqliteConn.createStatement();
				  
				    rs = st.executeQuery("SELECT COUNT(*) FROM employee");
				    
				    // get the number of rows from the result set
				    rs.next();
				    int rowCount = rs.getInt(1);
				    System.out.println(rowCount);
				    System.out.println("		");
				    
					
				    rs.close();
				    st.close();
				

		
				} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	         
	         
	         if (abc()<=10){
					String SQL2 = "insert into employee (name,surname,age,username,password) values ('Mbulelo','Damba',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
					ps = mysqlConn.prepareStatement(SQL2);
					ps.execute();
				} else if (abc()>10 && abc()<=15){
					String SQL2 = "insert into employee (name,surname,age,username,password) values ('Steve','Harvey',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
					ps = mysqlConn.prepareStatement(SQL2);
					ps.execute();
				} else if (abc()>15 && abc()<=20){
					String SQL2 = "insert into employee (name,surname,age,username,password) values ('James','Harrison',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
					ps = mysqlConn.prepareStatement(SQL2);
					ps.execute();
				} else if (abc()>20 && abc()<=25){
					String SQL2 = "insert into employee (name,surname,age,username,password) values ('Sebastian','Veron',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
					ps = mysqlConn.prepareStatement(SQL2);
					ps.execute();
				} else if (abc()>25 && abc()<=30){
					String SQL2 = "insert into employee (name,surname,age,username,password) values ('Simphiwe','Tshabalala',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
					ps = mysqlConn.prepareStatement(SQL2);
					ps.execute();
				} else if (abc()>30 && abc()<=35){
					String SQL2 = "insert into employee (name,surname,age,username,password) values ('Oupa','Manyisa',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
					ps = mysqlConn.prepareStatement(SQL2);
					ps.execute();
				} else if (abc()>35 && abc()<=40){
					String SQL2 = "insert into employee (name,surname,age,username,password) values ('Steven','Pienaar',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
					ps = mysqlConn.prepareStatement(SQL2);
					ps.execute();
				} else if (abc()>40 && abc()<=45){
					String SQL2 = "insert into employee (name,surname,age,username,password) values ('Itumeleng','Khune',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
					ps = mysqlConn.prepareStatement(SQL2);
					ps.execute();
				} else if (abc()>45 && abc()<=50){
					String SQL2 = "insert into employee (name,surname,age,username,password) values ('David','Obua',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
					ps = mysqlConn.prepareStatement(SQL2);
					ps.execute();
				} else if (abc()>50 && abc()<=55){
					String SQL2 = "insert into employee (name,surname,age,username,password) values ('Thabang','Lebese',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
					ps = mysqlConn.prepareStatement(SQL2);
					ps.execute();
				} else if (abc()>55 && abc()<=60){
					String SQL2 = "insert into employee (name,surname,age,username,password) values ('Andile','Jali',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
					ps = mysqlConn.prepareStatement(SQL2);
					ps.execute();
				} else if (abc()>60 && abc()<=65){
					String SQL2 = "insert into employee (name,surname,age,username,password) values ('Percy','Tau',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
					ps = mysqlConn.prepareStatement(SQL2);
					ps.execute();
				} else if (abc()>65 && abc()<=70){
					String SQL2 = "insert into employee (name,surname,age,username,password) values ('Jimmy','Tau',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
					ps = mysqlConn.prepareStatement(SQL2);
					ps.execute();
				} else if (abc()>70 && abc()<=75){
					String SQL2 = "insert into employee (name,surname,age,username,password) values ('Lucky','Lekgwathi',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
					ps = mysqlConn.prepareStatement(SQL2);
					ps.execute();
				} else if (abc()>75 && abc()<=80){
					String SQL2 = "insert into employee (name,surname,age,username,password) values ('George','Lebese',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
					ps = mysqlConn.prepareStatement(SQL2);
					ps.execute();
				} else if (abc()>80 && abc()<=85){
					String SQL2 = "insert into employee (name,surname,age,username,password) values ('Keagan','Dolly',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
					ps = mysqlConn.prepareStatement(SQL2);
					ps.execute();
				} else if (abc()>85 && abc()<=90){
					String SQL2 = "insert into employee (name,surname,age,username,password) values ('Khama','Bhilliat',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
					ps = mysqlConn.prepareStatement(SQL2);
					ps.execute();
				} else if (abc()>90 && abc()<=95){
					String SQL2 = "insert into employee (name,surname,age,username,password) values ('Steve','Komphela',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
					ps = mysqlConn.prepareStatement(SQL2);
					ps.execute();
				} else if (abc()>95 && abc()<=100){
					String SQL2 = "insert into employee (name,surname,age,username,password) values ('Gavin','Hunt',"+randomAge()+",'"+usernameHash()+"', '"+passwordHash()+"') ";
					ps = mysqlConn.prepareStatement(SQL2);
					ps.execute();
				}
				 System.out.println("Inserted data!!");
				 System.out.println("MYSQL ENTRY");
		      //   ps.execute();
		                                                
		
		             
		         ps.close();
		         
		         
		         try {
						
						   Statement st = mysqlConn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);

					   
						 st = mysqlConn.createStatement();
					  
					    rs = st.executeQuery("SELECT COUNT(*) FROM employee");
					    
					    // get the number of rows from the result set
					    rs.next();
					    int rowCount = rs.getInt(1);
					    System.out.println(rowCount);
					    System.out.println("		");
					    
						
					    rs.close();
					    st.close();
					

					
					} catch (Exception e) {
					e.printStackTrace();
				}
	    	x++;
		 } while (x<=100);

      
            
    } catch (Exception e) {
      // handle the exception
      e.printStackTrace();
      System.exit(1);
    } finally {
      // release database resources
      try {
        sqliteConn.close();
        mysqlConn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
