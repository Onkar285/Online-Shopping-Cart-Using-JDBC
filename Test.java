package jdbcproject;
import java.sql.*;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.util.Scanner;

public class Test2 
{
	
	Scanner sc=new Scanner(System.in);
	
	public void sell(Connection con)throws Exception{
		System.out.println("Enter id here:");
		int id=sc.nextInt();
		System.out.println("Enter your product name:");
		String nm=sc.next();
		System.out.println("Enter price:");
		int price=sc.nextInt();
		System.out.println("Enter category:");
		String cat=sc.next();
		String insertData="insert into product values(?,?,?,?)";
		PreparedStatement psi=con.prepareStatement(insertData);
		psi.setInt(1, id);
		psi.setString(2, nm);
		psi.setInt(3, price);
		psi.setString(4, cat);
		psi.execute();
		psi.close();
		System.out.println("Data inserted to database");
	}
	
	public void buy(Connection con)throws Exception{
		String retrive="select *from product";
		PreparedStatement ps=con.prepareStatement(retrive);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			System.out.print("[ Product id:"+rs.getInt(1));
			System.out.print(", Product name:"+rs.getString(2));
			System.out.print(", Product price:"+rs.getInt(3));
			System.out.println(", Product category:"+rs.getString(4)+" ]");
			System.out.println("________________________________________");
				
		}
		
		System.out.println("Enter id of product you want to buy");
		int i=sc.nextInt();
		String retriveSingle="Select id from product;";
		PreparedStatement ps1=con.prepareStatement(retriveSingle);
		rs=ps1.executeQuery();
		boolean flag=false;
		while(rs.next()) {
			if(i==rs.getInt(1)) {
				String deleteData="delete from product where id='"+i+"'";
				Statement smt=con.createStatement();
				smt.execute(deleteData);
				flag=true;
				break;
			}
		}
		if (flag) {
			System.out.println("Congrats! You added one product");				
		}else {
			System.out.println("You entered wrong id is here");
		}
	}
	
	public void retrive(Connection con) throws Exception{
		String retrive="select *from product";
		PreparedStatement ps=con.prepareStatement(retrive);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			System.out.print("[ Product id:"+rs.getInt(1));
			System.out.print(", Product name:"+rs.getString(2));
			System.out.print(", Product price:"+rs.getInt(3));
			System.out.println(", Product category:"+rs.getString(4)+" ]");
			System.out.println("____________________________________________________________________________________");
				
		}
	}

	
	public void update1(Connection con)throws Exception{
        
		System.out.println("Enter ID to update: ");
		int id = sc.nextInt();
		System.out.println("What to update you \n1.id \n2.name \n3.price \n4.category");
		int num=sc.nextInt();
		switch (num)
		{ 
		case 1:
		System.out.println("Enter update id: ");
		int idd=sc.nextInt();
		
		String update ="update product set id= '"+idd+"' where id = '"+id+"'";
		 
		PreparedStatement psi=con.prepareStatement(update);
		
		psi.execute();
		
		System.out.println("Data update to database");
		break;
		
		case 2:
			System.out.println("Enter update name: ");
			String nm=sc.next();
			
			String update1 ="update product set name= '"+nm+"' where id = '"+id+"'";
			 
			PreparedStatement psii=con.prepareStatement(update1);
			
			psii.execute();
			
			System.out.println("Data update to database");
			break;
			
		case 3:
			System.out.println("Enter update price: ");
			int pr=sc.nextInt();
			
			String update2 ="update product set price= '"+pr+"' where id = '"+id+"'";
			 
			PreparedStatement psiii=con.prepareStatement(update2);
			
			psiii.execute();
			
			System.out.println("Data update to database");
			break;
			
		case 4:
			System.out.println("Enter update category: ");
			String cg=sc.next();
			
			String update3 ="update product set category= '"+cg+"' where id = '"+id+"'";
			 
			PreparedStatement psiiii=con.prepareStatement(update3);
			
			psiiii.execute();
			
			System.out.println("Data update to database");
			break;
			
		}
	}

	public static void main(String[] args) throws Exception {
		
		Scanner sc=new Scanner(System.in);
		Test2 t=new Test2();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cashify","root","Root@123");
		while(true) {
			System.out.println("**__**__**__**__**@ Menu @**__**__**__**__**");
			System.out.println("1.Sell your product\n2.Buy your product\n3.update your product\n4.Retrive");
			System.out.println("Enter your choice");
			int ch=sc.nextInt();
			switch(ch) {
			case 1:
				t.sell(con);
				break;
				
			case 2:
				t.buy(con);
				break;
				
			case 3:
				t.update1(con);
				break;
			
			case 4:
			t.retrive(con);
				default:
				System.out.println("Exist");
				
				
			}
		} 

	}

}
