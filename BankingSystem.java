
//crud using mysql
//menu driven programame

import java.sql.*;
import java.util.Scanner;

public class BankingSystem {

	
	 final static  String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	 final static String Db_URL="jdbc:mysql://localhost:3306/stmgmtsystem";
	 final static String USER="root";
	 final static String PASS="rkit2022";
	 
	static Connection conn=null;
	static Statement stmt=null;
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	 //Taking input from user
		 Scanner sc=new Scanner(System.in);
		 try
		 {
			Class.forName(JDBC_DRIVER) ;//load the driver
			
			conn=DriverManager.getConnection(Db_URL,USER,PASS);//CONNECTION establishment
			
			stmt=conn.createStatement();
			
			//menu
			
			while(true)
			{
				
				System.out.println("Menu for Bank Users");
				System.out.println("1.Open Account");
				System.out.println("2.Deposit money into Account");
				System.out.println("3.Withdrawl money from Account");
			
				System.out.println("4.Check balance");
				System.out.println("5.Close Account");
				System.out.println("6.exit");
				
				System.out.println("Enter your choice");
				int choice=sc.nextInt();//choice is taken from user
				
				//pass the user choice to switch csae
				switch(choice)
				{
				
				case 1:openAccount();//calling the method
						break;						
				case 2:deposit();				
						break;
				case 3: withdrawl();
						break;
				case 4: balance();
						break;
				case 5: closeAccount();
						break;
				case 6: sc.close();
						System.out.println("Exiting.....");
						System.exit(0);
				default:System.out.println("Wrong choice");
				
									
				
				}//switchcase
				
			}//while loop	 		 
			 
		 }//try 
		 catch(SQLException e) {}
		 catch(Exception e)	 {			
			 System.out.println(e);
					 }	
		 
		 finally
		 {
			 try
			 {
				if( stmt!=null)
				stmt.close();
				if(conn!=null)
					conn.close();
			 }
			 catch(SQLException e) {}
		 
			 
		 }
		
	}//main



//insert/create
static void openAccount() throws SQLException {
	
	Scanner sc=new Scanner(System.in);
	
	System.out.println("Create Account");

	System.out.println("Enter name");
	String name=sc.next();
	System.out.println("Enter balance");
	int balance=sc.nextInt();
	//System.out.println("Hello");
	
	String sql="Insert into account (holder_name,balance)values('"+name+"',"+balance+")";
	
	int r=stmt.executeUpdate(sql);
	System.out.println(r+"Account created successfully");
	
	
	
}
//deposit
static void deposit() throws SQLException
{
	System.out.println("Deposit Money");
	Scanner sc=new Scanner(System.in);
	
	System.out.println("Enter account no");
	int acno=sc.nextInt();
	
	
	System.out.println("Enter deposit money");
	int amount=sc.nextInt();
	
	String sql="update account set balance=balance+"+ amount+" where account_number="+acno;
	int r=stmt.executeUpdate(sql);
	
	if(r>0)
		System.out.println("Deposited successfully");
	else
		
		System.out.println("Account not found");
	
	
}

static void withdrawl() throws SQLException
{
	System.out.println("WithdrawlMoney");
	
Scanner sc=new Scanner(System.in);
	
	System.out.println("Enter account no");
	int acno=sc.nextInt();
	
	
	System.out.println("Enter amount of  money want to withdraw");
	int amount=sc.nextInt();
	
	String sql="update account set balance=balance-"+ amount+" where account_number="+acno;
	int r=stmt.executeUpdate(sql);
	
	if(r>0)
		System.out.println("Withdrawl successfull");
	else
		
		System.out.println("Account not found");
	
	
	
	
}
/*
static void withdrawl() throws SQLException
{
	Scanner sc=new Scanner(System.in);
	
	System.out.println("Enter account no");
	int acno=sc.nextInt();
	
	System.out.println("Enter amount of  money want to withdraw");
	int amount=sc.nextInt();
	
	String sql="select * from account where account_number="+acno;
	ResultSet rs=stmt.executeQuery(sql);
	
	if(rs.next())
	{
	int balance=rs.getInt("balance");
	
	if(balance>=amount)
	
	String sql="update account set balance=balance-"+ amount+" where account_number="+acno;
	int r=stmt.excuteUpdate(sql);
	System.out.println("withdawl success");
	
	
	}
	else
	System.out.println("Insufficient balance");
	
	
	




}*/






//CHECK BALANCE
static void balance() throws SQLException
{
	System.out.println("Check balance ");
	//TAKE ACNO AS INPUT
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter your account no ");
	int ac_no=sc.nextInt();
	
	String sql="select balance from account where account_number="+ac_no;
	
	ResultSet rs=stmt.executeQuery(sql);
	if(rs.next())
	{
		int balance=rs.getInt("balance");
		System.out.println("Current balance : "+balance);		
		
	}
	else
	{
		System.out.println("Account not found");
		
	}	

}

static void closeAccount() throws SQLException
{
	System.out.println("Close Account");
	
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter your account no ");
	int ac_no=sc.nextInt();
	
	String sql="delete from account where account_number="+ac_no;
	int r=stmt.executeUpdate(sql);
	
	if(r>0)
		System.out.println("Account closed");
	else
		System.out.println("Account not found");
	
}
}