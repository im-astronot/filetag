package file.tagging.poc;

import java.sql.*;
import java.util.Scanner;
 
public class TaggingUsingSQL {
	//CRUD Operation Using Java with SQLite Database
	public static void main(String[] args) throws Exception {
            
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection("jdbc:sqlite:filetaginfo.db");
 
		Statement stmt =con.createStatement();
		ResultSet rs;
		PreparedStatement st;
		
                String qry = "";
                int choice;
		String file_name, tag;
 
		Scanner in = new Scanner(System.in);
		Scanner str = new Scanner(System.in);
 
		while(true)
		{
			System.out.println("SQLite Java CRUD Operation");
			System.out.println("1. Insert");
			System.out.println("2. Update");
			System.out.println("3. Delete");
			System.out.println("4. Select");
			System.out.println("5. Exit");
			System.out.print("Enter a choice: ");
			
                        choice = in.nextInt();
			System.out.println("-----------------------------------------");
			switch(choice){
			case 1:
				System.out.println("1. Insert New Data");
 
				System.out.println("Enter File Name : ");
				file_name=str.nextLine();
				System.out.println("Enter File Tag : ");
				tag=str.nextLine();
 
                                qry="UPDATE file_tag SET tag = '" + tag + "' WHERE file_name = '" + file_name + "'";
				st= con.prepareStatement(qry);
                                
				st.executeUpdate();
				System.out.println("Data Insert Success");
 
				break;
			case 2:
				System.out.println("2. Updating a Data");
 
				System.out.println("Enter File Name : ");
				file_name=str.nextLine();
				System.out.println("Enter File Tag : ");
				tag=str.nextLine();
 
                                
                               qry = "UPDATE file_tag SET tag = '" + tag + "' WHERE file_name = '" + file_name + "'";
				st= con.prepareStatement(qry);

				st.executeUpdate();
				System.out.println("Data Update Success");
 
 
				break;
			case 3:
				System.out.println("3. Deleting a Data");
 
				System.out.println("Enter File Name : ");
				file_name=str.nextLine();
 
				qry="delete from file_tag where file_name = '" + file_name + "'";
				st= con.prepareStatement(qry);
 
				st.executeUpdate();
				System.out.println("Data Deleted Success");
 
				break;
			case 4:
				System.out.println("4. Print all Records");
				qry="SELECT file_name, tag from file_tag where tag = 'tagname'";
				rs=stmt.executeQuery(qry);
                                
				while(rs.next())
				{
					file_name=rs.getString("file_name");
					tag = rs.getString("tag");
 
					System.out.print(file_name + " \t " + tag + "\n");
 
				}
				break;
			case 5:
				System.out.println("Thank You");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Selection");
				break;
			}
			System.out.println("-----------------------------------------");
		}
 
 
	}
 
}
 