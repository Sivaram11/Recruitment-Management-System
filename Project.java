
/*             Project Title: Recruitment Management System                */
/*This program is used to demonstrate the recruitment Management System of an organization which offers interviews
 and select to skilled professional who match the job requirements 

 Author1: Naveen Sangana 
 Author2: Sivaram Vishnuvajjhala
 *
 */


//you need to import the java.sql package to use Java database connectivity
import java.sql.*;
import java.util.Scanner;
import java.io.*;
import java.math.BigInteger;
class Project
{
	static BufferedReader br;  // Needed for keyboard I/O.
	static Connection conn; // A connection to the DB must be established
	// before requests can be handled.  You should
	// have only one connection.
	static Statement stmt;  // Requests are sent via Statements.  You need
	// one statement for every request you have
	// open at the same time.

	// "main" is where the connection to the database is made, and
	// where I/O is presented to allow the user to direct requests to
	// the methods that actually do the work.
	public static void main (String args []) throws IOException,SQLException
	{
		String username="ns142", password = "gb86oEWm";
		String ename;

		//keyboard = new BufferedReader(new InputStreamReader (System.in));

		try { //Errors will throw a "SQLException" (caught below)

			// Load the Oracle JDBC driver
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

			System.out.println("Registered the driver...");

			// Connect to the database.  The first argument is the
			// connection string, the second is your username, the third is
			// your password.
			conn = DriverManager.getConnection (
					"jdbc:oracle:thin:@oracle1.wiu.edu:1521/toolman.wiu.edu",
					username, password);

			conn.setAutoCommit(false);

			System.out.println("logged into oracle as " + username);

			// Create a Statement; again, you may have/need more than one.
			stmt = conn.createStatement ();

			//function call to inputSelection method where the logic is
			inputSelection();

			//committing the transactions
			conn.commit();

			//closing the connection
			conn.close();

		} //end of try block

		catch(Exception e)
		{
			System.out.println("Caught SQL Exception: \n     " + e);
		}//end of catch block
	}//end of main function


	//the inputSelection Method is used to display a Menu to the users of RMS from which they will select accordingly to their respective roles
	public static void inputSelection() throws Exception{

		int inputEntered=0;
		br = new BufferedReader(new InputStreamReader(System.in));

		do  //beginning of do while loop
		{
			//Menu Items of the inputSelection Method
			System.out.println("*****************************************************************");
			System.out.println("            Welcome to Recruitment Management  System            ");
			System.out.println("------------------------------------------------------------------");
			System.out.println("            Please Select the Option from the Below Menu          ");
			System.out.println("******************************************************************");
			System.out.println("1.Enter 1 For Recruiter");
			System.out.println("2.Enter 2 For Job Applicant");
			System.out.println("3.Enter 3 for Exit");
			System.out.println("------------------------------------------------------------------");

			try  { //Errors will throw a "SQLException" (caught below)
				inputEntered = Integer.valueOf(br.readLine());

			}///end of try

			catch (Exception ex){
				System.out.println("Caught SQL Exception: \n " + ex);

				System.out.println("Please check the input entered, select options from 1 to 3");
				inputSelection(); //calling back the function to display the Menu for the user to select



			}//end of catch

			//begin of switch case
			switch (inputEntered){

			case 1 :
				try { //errors will throw an IO Exception (which are caught below
					getRecruiterActions();
				} //end of try
				catch (Exception Ex){
					System.out.println("Caught Exception: "+Ex);
				} //end of catch
				break;

			case 2 :
				try { //errors will throw an IO Exception (which are caught below
					getApplicantActions();
				}
				catch (Exception Ex){
					System.out.println("Caught Exception: "+Ex);
				} //end of catch
				break;

			case 3:  System.out.println("Adios Have a Nice Day !!!");
			System.exit(0); //Invoking thE System.exit call to exit from the program

			default:
				System.out.println("You entered a wrong input. Please select options from 1 to 3");
				inputSelection();
			}//end of switch-case

		}while(inputEntered!=3); //end of do while

	}//end of method inputSelection



	//the getRecruiterActions Method is used to display a Menu to the Recruitment Officer from which he will perform operations accordingly
	public static void getRecruiterActions() throws Exception{

		int inputEntered=0;
		br = new BufferedReader(new InputStreamReader(System.in));


		do //beginning of do while loop
		{
			//menu items
			System.out.println("************************************************************************************");
			System.out.println("                Welcome to Recruitment Management  System                            ");
			System.out.println("*************************************************************************************");
			System.out.println("              Please Select the Options from the Below Menu                         ");
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.println("1. Enter 1 To Insert Job Requirement.");
			System.out.println("2. Enter 2 To Insert Job Skills Required and Link them to the Job Requirement.");
			System.out.println("3. Enter 3 To Get the Count of Applciants For a Particular JobID.");
			System.out.println("4. Enter 4 To Update the Job Role for a Particular Job Requirement.");
			System.out.println("5. Enter 5 To Fetch the Job Applicants Who got an Interview call");
			System.out.println("6. Enter 6 To Update the Interview Status for Job Applicant.");
			System.out.println("7. Enter 7 to Fetch Details Of Applicants who have Interviews after a specific date.");
			System.out.println("8. Enter 8 To Delete the existing Job Requirement");
			System.out.println("9. Enter 9 To Delete the Applicants who have not turned up for the interview");
			System.out.println("10. Enter 10 To Exit !!");
			System.out.println("***************************************************************************************");

			try  { //Errors will throw a "SQLException" (caught below)
				inputEntered = Integer.valueOf(br.readLine());   

			}///end of try

			catch (Exception ex){
				System.out.println("Caught SQL Exception: \n " + ex);
				System.out.println("You entered a wrong input. Please select options from 1 to 10");
				getRecruiterActions(); //calling back the function to display the Menu for the user to select

			}//end of catch

			//begin of switch case this switch case has a set of method calls which performs respective actions
			switch (inputEntered){

			case 1:       insertJobRequirement();     
			break;
			case 2:       insertSkillsRequired();
			break;
			case 3:       getCountOfApplicants();
			break;
			case 4:       updateJobRole();
			break;
			case 5:       gotInterviewCall();
			break;
			case 6:       updateInterviewDecison();
			break;
			case 7:       getApplicants();
			break;
			case 8:       deleteRequirement();
			break;
			case 9:       deleteInterview();
			break;
			case 10:       System.out.println("Adios Have a Nice Day !!!");
			System.exit(0);
			default :     System.out.println("You entered a wrong input. Please select options from 1 to 10");
			getRecruiterActions();  //calling the method again to display the menu
			} //end of switch case

		}while(inputEntered!=9); //end of do while
	}//end of inputSelection


	//The insertJobRequirement method is used to performAnInsert operation into the  Job_Requirement table
	public static void insertJobRequirement() throws Exception{


		//variable declaration
		int job_id;
		int recruiter_id;
		String profile="";
		int experience;
		String location="";
		String contactPerson="";
		String contactEmail="";
		int positions;
		int salary;

		br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter The Job Requirement Details Below ");
		System.out.println("Enter The Job_Id [Ex: 92203]");
		job_id = Integer.valueOf(br.readLine());

		System.out.println("Enter RecruiterId as 12345 [ex: 12345]");
		recruiter_id= Integer.valueOf(br.readLine());

		System.out.println("Enter the Job Profile Name [Ex: Hadoop Developer]");
		profile = br.readLine();

		System.out.println("Enter the Years of Experience Required [Ex: 4]");
		experience = Integer.valueOf(br.readLine());

		System.out.println("Enter the Location of Job Requirement: [Ex: Chicago] ");
		location = br.readLine();

		System.out.println("Enter the Contact Person : [Ex:Jason Statham] ");
		contactPerson = br.readLine();

		System.out.println("Enter the Contact Email : [Ex:statham@gmail.com] ");
		contactEmail = br.readLine();

		System.out.println("Enter The Available Positions [Ex: 5]");
		positions = Integer.valueOf(br.readLine());

		System.out.println("Enter Salary [Ex: 90000]");

		salary= Integer.valueOf(br.readLine());

		String SQLQuery= "INSERT INTO Job_Requirement " +
				"VALUES ("+job_id+"," +
				recruiter_id+","+
				"'"+profile+"',"+
				experience+","+
				"'"+location+"',"+
				"'"+contactPerson+"',"+
				"'"+contactEmail+"',"+
				positions+","+
				salary+")";

		stmt.executeUpdate(SQLQuery);    //using the executeUpdate method of the statement class to perform an Insert into the database
		System.out.println("*******************************");
		System.out.println("       1 row Inserted"            );
		System.out.println("********************************");
		conn.commit();
	}//end of method insertJobRequirement

	
	//this Method is used to  insert and link the needed skills to the  particular job Requirement
	public static void insertSkillsRequired() throws Exception{


		//variable declaration
		int skillid=0;
		String skillname;
		int job_id;
		String input;
		br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter The skills you want to insert into the Database Below ");
		System.out.println("Enter The SkillId [Ex: 12224]");
		skillid = Integer.valueOf(br.readLine());

		System.out.println("Enter the Skill name [Ex: Cisco Admin]");
		skillname= br.readLine();

		String SQLQuery= "INSERT INTO Skills " +
				"VALUES ("+skillid+"," +
				"'"+skillname+"'"+")";

		stmt.executeUpdate(SQLQuery); //using the executeUpdate method of the statement class to perform an Insert into the database
		
		System.out.println("Successfully inserted Skill");

		System.out.println("Please enter the Job_ID you want this skill to be linked to: [Ex: 92203 ]");
		job_id = Integer.valueOf(br.readLine());

		String SQLQuery1= "INSERT INTO Need_skills " +
				"VALUES ("+skillid+"," +
				+job_id+")";
		
		stmt.executeUpdate(SQLQuery1);  //using the executeUpdate method of the statement class to perform an Insert into the database

		System.out.println("Successfully Mapped Skill to the Job");

		System.out.println("Do you want to insert more skills for this Job: Please enter Yes/No [Ex: Yes] ");

		input=String.valueOf(br.readLine());
		
		System.out.println("Input entered is :"+input);
		
        while (input.length()>2)	//while loop to check the length of yes or no	
		{	
			System.out.println("Enter The SkillId [Ex: 12225]");
			skillid = Integer.valueOf(br.readLine());

			System.out.println("Enter the Skill name [ex: Networking ]");
			skillname= br.readLine();

			String SQLQuery2= "INSERT INTO Skills " +
					"VALUES ("+skillid+"," +
					"'"+skillname+"'"+")";

			stmt.executeUpdate(SQLQuery2);  //using the executeUpdate method of the statement class to perform an Insert into the database
			System.out.println("Successfully inserted");

			System.out.println("Please enter the Job_ID you want this skill to be linked to: [ex: 92203 ]");
			job_id = Integer.valueOf(br.readLine());

			String SQLQuery3= "INSERT INTO Need_skills " +
					"VALUES ("+skillid+"," +
					+job_id+")";

			stmt.executeUpdate(SQLQuery3);  //using the executeUpdate method of the statement class to perform an Insert into the database
			System.out.println("Successfully Mapped Skill to the Job Requirement");
			
			System.out.println("Do you want to insert more skills for this Job: Please enter Yes/No ");
			input=br.readLine();

		}
        
		conn.commit(); //commit all the insert and updates to persist to the database
		getRecruiterActions();	//calling the method getRecruiterActions to display the Menu again
	
	}//end of method insertSkillsRequired


	//the getCountOfApplicants is used to fetch the number of Job applicants for all the Job Requirements published by the Recruitment officer
	public static void getCountOfApplicants() throws Exception{

		String SQLQuery = "SELECT Job_requirement.Job_id, "+
				"COUNT(*)" +"ApplicantCount "+ 
				"FROM   Job_applicant, Applied_job, Job_requirement "+ 
				"WHERE  Applied_job.Job_id = Job_requirement.Job_id "+
				"AND Applied_job.Applicant_id = Job_applicant.Applicant_id "+
				"GROUP  BY Job_requirement.Job_id ";

		//executeQuery Method for firing the SELECT query and storing the data in ResultSet
		ResultSet rset = stmt.executeQuery(SQLQuery);  

		System.out.printf("%-18s%-12s \n","Job Id","Application Count");

		System.out.printf("----------------------------------------------\n");

		//logic for printing the Rows of the table using the functions of ResultSet class
		while(rset.next()){ //begin of while 
			int jobid= rset.getInt("job_id");
			int count= rset.getInt(2);
			System.out.printf("%-18s%-12s \n",jobid,count);
		}//end of while
		System.out.println("");

		rset.close();

	}//end of getCountOfApplicants method

	//the  updateJobRole method is used for updating the Job Profile/role of a particular Job_Requirement 
	public static void updateJobRole() throws Exception{

		String profile;
		int id;
		br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please enter The Job_Id In Which You Need To Change The Job Profile [Ex: 4545 ]");
		id=Integer.valueOf(br.readLine());
		System.out.println("Enter The New Job Profile Name You want to Update [Ex:Database Developer ]");
		profile=br.readLine();
		String SQLQuery="UPDATE Job_requirement "+
				"SET    Job_profile ="+ "'"+profile+"'"+ 
				"WHERE  Job_id ="+ id; 

		stmt.executeUpdate(SQLQuery);  //using the executeUpdate method of the statement class to perform an Update into the database

		System.out.println("*****************************************");
		System.out.println("     Profile updation Successful....!!");
		System.out.println("*****************************************");

		conn.commit();  //commit the transaction

	}


	//the gotInterviewCall method retrieves the applicants who attends/got call for the interview
	public static void gotInterviewCall() throws Exception{

		String SQLQuery= "SELECT Has_skills.Applicant_id,"+
				"Interview.Interview_id,"+
				"Job_applicant.Firstname,"+
				"Job_applicant.Lastname,"+
				"Job_applicant.Email_address"+
				" FROM Has_skills,"+ 
				" Need_skills, "+ 
				" Job_applicant, "+
				" Interview "+
				" WHERE Has_skills.Skill_id = Need_skills.Skill_id \n"+
				" AND Has_skills.Applicant_id = Job_applicant.Applicant_id \n "+
				" AND Has_skills.Applicant_id = Interview.Applicant_id \n "+
				" GROUP BY Has_skills.Applicant_id, "+
				" Job_applicant.Firstname, "+
				" Job_applicant.Lastname, "+
				" Job_applicant.Email_address, "+
				" Interview.Interview_id" ;

		ResultSet rset = stmt.executeQuery(SQLQuery); //executeQuery Method for firing the SELECT query and storing the data in ResultSet

		System.out.printf("%-13s%-22s%-14s%-22s%-18s \n","Applicant_Id","Interview Id","firstname","lastname","Email_Address");

		System.out.printf("--------------------------------------------------------------------------------------------------------------------\n");

		//logic for printing the Rows of the table using the functions of ResultSet class
		while(rset.next()){ //while loop begins
			int id = rset.getInt("applicant_id");
			int int_id= rset.getInt("interview_id");
			String fname  = rset.getString("firstname");
			String lname = rset.getString("lastname");
			String email = rset.getString("email_address");
			System.out.printf("%-13s%-22s%-14s%-18s%-12s \n",id,int_id,fname,lname,email);
		}// end of while loop
		rset.close();

	}//end of method gotInterviewCall

	/*The updateInterviewDecison is a feature of this Recruitment Management system used by the Recruitment Officer to 
	Update the Status of Applicant's Interview to Selected if selected| Rejected if not selected | NULL If not attended*/
	public static void updateInterviewDecison() throws Exception{

		//variable declaration
		int int_id;
		int app_id;
		String Status="";

		br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please Enter The Interview_Id [Ex: 10001]");
		int_id = Integer.valueOf(br.readLine());

		System.out.println("Enter Applicant ID [Ex: 91694 ] ");
		app_id= Integer.valueOf(br.readLine());

		System.out.println("Enter the Decison below: [Ex: Selected if selected || Rejected if not selected || NULL If not attended: ] ");
		Status= br.readLine();

		String SQLQuery="UPDATE Interview "+
				"SET    STATUS_OF_INTERVIEW ="+ "'"+Status+"'"+ 
				"WHERE  INTERVIEW_ID ="+ int_id + 
				"AND  APPLICANT_ID ="+ app_id; 

		stmt.executeUpdate(SQLQuery); //using the executeUpdate method of the statement class to perform an Update into the database

		System.out.println("*****************************************");
		System.out.println("   Decision updation Successful..!!");
		System.out.println("*****************************************");
		conn.commit(); //commit the transaction 

	}//end of updateInterviewDecsion method


	//this getApplicants method feature is used to  fetch the applicants who have interviews in the Future Dates

	public static void getApplicants() throws Exception{

		String interviewDate="";
		String SQLQuery=null;
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please Enter The Date  in (DD-MON-YY) [Ex: 20-Dec-16 ] \n");
		interviewDate=br.readLine();

		SQLQuery= "SELECT interview.interview_Id, "+
				"Interview.Interview_Date, "+
				"job_applicant.applicant_id, "+
				"job_applicant.firstname,"+ 
				"job_applicant.lastname,"+ 
				"job_applicant.email_address,"+
				"job_applicant.designation "+ 
				"FROM job_applicant "+
				"INNER JOIN interview "+
				"ON  job_applicant.applicant_id = interview.applicant_id "+
				"AND interview_date > "+"'"+interviewDate+"'";


		//executeQuery Method for firing the SELECT query and storing the data in ResultSet
		ResultSet rset = stmt.executeQuery(SQLQuery);

		System.out.printf("%-13s%-22s%-28s%-12s%-18s%-30s%-38s \n","Interview ID","InterviewDate","applicant_id","firstname","lastname","email_address","designation");

		System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------\n");
		//logic for printing the Rows of the table using the functions of ResultSet class
		while(rset.next()){
			int int_id = rset.getInt("Interview_id");
			Date date= rset.getDate("Interview_Date");
			int id = rset.getInt("applicant_id");
			String fname  = rset.getString("firstname");
			String lname = rset.getString("lastname");
			String email = rset.getString("email_address");
			String designation = rset.getString("designation");
			System.out.printf("%-13s%-22s%-28s%-12s%-18s%-30s%-38s \n",int_id,date,id,fname,lname,email,designation);
		}
		rset.close();
	} //end of method getApplicants


	//This method  deleteRequirement is used for deleting the Requirements which have the available positions set to zero
	public static void deleteRequirement() throws Exception{

		int id;
		br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please enter The Job_Id In Which You want to Remove from the Database [Ex: 2222 ]");
		id=Integer.valueOf(br.readLine());
		String SQLQuery="DELETE FROM Job_requirement "+
				"WHERE  Job_id ="+ id + " AND POSITIONS_AVAILABLE =0";

		stmt.executeUpdate(SQLQuery); //using the executeUpdate method of the statement class to perform an Update into the database

		System.out.println("*****************************************");
		System.out.println("  Requirement Deletion Successful...!!");
		System.out.println("*****************************************");
		conn.commit(); //commit the transaction


	}

	//this method deleteInterview is used to acheive the data obsolescence feature where we remove the records of applicants who got interview scheduled and 
	//haven't attended the same
	public static void deleteInterview() throws Exception{

		String SQLQuery="DELETE FROM Interview "+
				"WHERE STATUS_OF_INTERVIEW ='NULL'";

		stmt.executeUpdate(SQLQuery); //using the executeUpdate method of the statement class to perform an Update into the database

		System.out.println("*****************************************");
		System.out.println("  Interview Entries Deletion Successful...!!");
		System.out.println("*****************************************");
		conn.commit(); //commit the transaction

	}


	//the getApplicantActions Method is used to display a Menu to the Job Applicant from which applicant will perform operations accordingly
	public static void getApplicantActions() throws Exception{

		int inputEntered=0;
		br = new BufferedReader(new InputStreamReader(System.in));

		do
		{
			System.out.println("*********************************************************************************************************");
			System.out.println("----------------------------------------------------------");
			System.out.println("           Enter your Choice From The Following           ");
			System.out.println("----------------------------------------------------------");
			System.out.println("1. Enter 1 to insert Job_Applciant Details.");
			System.out.println("2. Enter 2 to Fetch the Details of Job openings.");
			System.out.println("3. Enter 3 to Fetch The Skills Required For Specific Job Opening");
			System.out.println("4. Enter 4 To Fetch list Of Jobs Applied");
			System.out.println("5. enter 5 to Get The Skill Classification.");
			System.out.println("6. Enter 6 To Get The interview status of an Applicant.");
			System.out.println("7. Enter 7 To Update The Designation of an Applicant.");
			System.out.println("8. Enter 8 To Delete the Job Applied By You");
			System.out.println("9. Enter 9 To Exit !!");
			System.out.println("************************************************************************************************************");

			try { //try block begins
				inputEntered = Integer.valueOf(br.readLine());

			}///end of try

			catch (Exception ex){
				System.out.println("Caught SQL Exception: \n " + ex);
				System.out.println("You entered a wrong input. Please select options from 1 to 5");
				getApplicantActions();
			}//end of catch
			//switch case begins
			switch(inputEntered){
			case 1:  insertApplicantData();
			break;
			case 2:  listJobOpenings();
			break;
			case 3:  getSkillSet();
			break;
			case 4:  listAppliedJobs();
			break;
			case 5:  getSkillsClassification();
			break;
			case 6:  getApplicantStatus();
			break;
			case 7:  updateJobApplicantProfile();
			break;
			case 8:  deleteJobApplied();
			break;

			case 9:  System.out.println("Adios Have a Nice Day !!!");
			System.exit(0);
			default :  System.out.println("Enter valid choice from [1 to 7]");
			getApplicantActions(); //calling the method again to display the menu
			} //end of switch case
		}while(inputEntered!=9); //end of do while

	} //end of getApplicantActions method

	//the insertApplicantData method is used for inserting the details of the Job applicant into the database
	public static void insertApplicantData() throws Exception
	{

		int app_id;
		int exp;
		BigInteger contactNum;
		String fname, lname, designation,email;


		br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter The Job Applicant Details Below ");
		System.out.println("Enter The Applcant ID [Ex: 98098]");
		app_id = Integer.valueOf(br.readLine());

		System.out.println("Enter the First Name [Ex: John]");
		fname = br.readLine();

		System.out.println("Enter the Last Name [Ex: Stones]");
		lname = br.readLine();

		System.out.println("Enter The Experience [Ex: 5]");
		exp = Integer.valueOf(br.readLine());

		System.out.println("Enter the Designation: [Ex:BI Developer] ");
		designation = br.readLine();

		System.out.println("Enter the Contact Email : [Ex:john@gmail.com] ");
		email = br.readLine();

		System.out.println("Enter Contact Number [Ex: 9966850443]");
		contactNum= new BigInteger(br.readLine());

		String SQLQuery= "INSERT INTO Job_Applicant " +
				"VALUES ("+app_id+"," +
				"'"+fname+"',"+
				"'"+lname+"',"+
				exp+","+					
				"'"+designation+"',"+
				"'"+email+"',"+
				contactNum+")";

		stmt.executeUpdate(SQLQuery); //using the executeUpdate method of the statement class to perform an Update into the database

		System.out.println("*******************************");
		System.out.println("       1 row inserted          " );
		System.out.println("********************************");
		conn.commit();

	} //end of insertApplicantData method


	//The listJobOpenings method is  used to fetch the available job openings for the applicant to apply
	public static void listJobOpenings() throws Exception{

		String SQLQuery= "SELECT JOB_ID, "+
				"JOB_PROFILE, "+
				"EXPERIENCE, "+
				"POSITIONS_AVAILABLE, "+
				"SALARY_SCALE"+
				" FROM Job_Requirement job";

		//executeQuery Method for firing the SELECT query and storing the data in ResultSet
		ResultSet rset = stmt.executeQuery(SQLQuery); 

		System.out.printf("%-13s%-22s%-34s%-34s%-18s  \n","Job_Id","Job_Profile","Experience Required","Positions Available","Salary");

		System.out.printf("---------------------------------------------------------------------------------------------------------------------\n");
		//logic for printing the Rows of the table using the functions of ResultSet class
		while(rset.next()){ //while loop begins
			int job_id = rset.getInt("JOB_ID");
			String profile  = rset.getString("JOB_PROFILE");
			int exp = rset.getInt("EXPERIENCE");
			int positions = rset.getInt("POSITIONS_AVAILABLE");
			int salary=rset.getInt("SALARY_SCALE");
			System.out.printf("%-13s%-22s%-34s%-34s%-18s \n",job_id,profile,exp,positions,salary);
		}//end of while 

		rset.close();
	}///end of listJobOpenings method


	//the method getskillSet is used for fetching the skill set required for a particular job
	public static void getSkillSet() throws Exception{

		int job_id;
		br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please enter The Job_Id which you get from the Menu Option 2");
		job_id=Integer.valueOf(br.readLine());


		String SQLQuery= "SELECT sk.Skill_id, "+
				"sk.Skillname "+
				" FROM   Job_requirement job, "+
				" Need_skills ns, "+
				" Skills sk "+
				" WHERE  job.Job_id = ns.Job_id "+
				" AND ns.Skill_id = sk.Skill_id "+
				" AND job.Job_id ="+job_id; 

		//executeQuery Method for firing the SELECT query and storing the data in ResultSet
		ResultSet rset = stmt.executeQuery(SQLQuery);

		System.out.printf("%-13s%-22s \n ","Skill ID", "Skill Name");

		System.out.printf("------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

		while(rset.next()){ //while loop begins
			int skillid = rset.getInt("skill_id");
			String name  = rset.getString("Skillname");
			System.out.printf("%-13s%-22s \n",skillid,name);
		}//end of while loop

		rset.close();


	}

	//The method listAppliedJobs shows the jobs list applied by the applicant
	public static void listAppliedJobs() throws Exception{
		int appId;
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please Enter the applicant Id: [Ex: 91694");
		appId=Integer.valueOf(br.readLine());

		String SQLQuery= "SELECT   firstname, "+
				"lastname, "+
				"designation, "+
				"job_requirement.job_id, "+
				" job_profile,"+ 
				"salary_scale "+
				" FROM     job_applicant, "+
				" applied_job, "+
				" job_requirement "+
				"WHERE    applied_job.applicant_id=job_applicant.applicant_id "+
				"AND      applied_job.job_id=job_requirement.job_id "+
				"AND      applied_job.applicant_id="+appId+"\n" +
				"GROUP BY firstname, lastname, designation, job_requirement.job_id, job_profile, salary_scale";

		ResultSet rset = stmt.executeQuery(SQLQuery); //executeQuery Method for firing the SELECT query and storing the data in ResultSet

		System.out.printf("%-13s%-22s%-34s%-22s%-18s%-22s \n","FIRSTNAME","LASTNAME","DESIGNATION","JOB_ID","JOB_PROFILE","SALARY_SCALE");

		System.out.printf("------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

		while(rset.next()){ //while loop begins

			String fname  = rset.getString("firstname");
			String lname = rset.getString("lastname");
			String designation = rset.getString("designation");
			int job_id= rset.getInt("job_id");
			String profile  = rset.getString("job_profile");
			int salary=rset.getInt("salary_scale");
			System.out.printf("%-13s%-22s%-34s%-22s%-18s%-22s \n", fname,lname,designation,job_id,profile,salary);
		}//end of while loop
		rset.close();

	} //end of listAppliedJobs method


	//This method has the feature for the applicants to know what are the trending skills and get an idea on the market situation
	public static void getSkillsClassification() throws Exception{

		String SQLQuery= "SELECT   skillname, "+
				"COUNT(skills.skill_id) AS"+"#ofApplicants"+","+ 
				"DECODE(COUNT(*), "+
				" 1, 'Niche Skill', "+
				" 2, 'Middle of the Road', "+
				" 3, 'Trending Skill', "+
				" 'Popular') classification "+
				" FROM     job_applicant job, "+ 
				" has_skills, "+ 
				" skills "+
				" WHERE    has_skills.applicant_id=job.applicant_id \n "+
				" AND      skills.skill_id=has_skills.skill_id \n "+
				"GROUP BY skills.skillname";
		ResultSet rset = stmt.executeQuery(SQLQuery); //executeQuery Method for firing the SELECT query and storing the data in ResultSet

		System.out.printf("%-17s%-34s%-24s  \n","Skill Name","#ofApplicants Having Skill","Classification");

		System.out.printf("----------------------------------------------------------------------------------------------------\n");

		while(rset.next()){ 
			String name = rset.getString("SkillName");
			int count  = rset.getInt(2);
			String classification = rset.getString(3);
			System.out.printf("%-17s%-34s%-24s   \n",name,count,classification);
		} //end of while
		rset.close();

	} //end of getSkillsClassification method

	// this Method is used to fetch the details whether the job applicant is hired or not
	public static void getApplicantStatus() throws Exception{

		int appId;
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please Enter the applicant Id: [Ex: 91694 ] ");
		appId=Integer.valueOf(br.readLine());

		String SQLQuery= "SELECT Job_applicant.Firstname, "+
				"Job_applicant.Lastname, "+
				"Job_applicant.Email_address, "+
				"Job_applicant.Designation, "+
				"Applied_job.Applicant_id, "+
				"Interview.Interview_id, "+
				"Status_of_interview "+
				" FROM   Applied_job, Interview, Job_applicant "+
				" WHERE  Applied_job.Applicant_id = Interview.Applicant_id "+
				"AND Applied_job.Applicant_id = Job_applicant.Applicant_id "+
				"AND Interview.Applicant_id =" +appId+ "\n"+
				"GROUP  BY Applied_job.Applicant_id, "+
				"Interview.Interview_id, "+
				"Status_of_interview, "+
				"Job_applicant.Firstname, "+
				"Job_applicant.Lastname, "+
				"Job_applicant.Email_address, "+
				"Job_applicant.Designation";

		ResultSet rset = stmt.executeQuery(SQLQuery); //executeQuery Method for firing the SELECT query and storing the data in ResultSet

		System.out.printf("%-13s%-22s%-14s%-22s%-18s%-22s%-34s \n","firstname","lastname","Email_Address","Designation","Applicant_Id","Interview Id","Interview Status");

		System.out.printf("------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

		while(rset.next()){ //while begins
			String fname  = rset.getString("firstname");
			String lname = rset.getString("lastname");
			String email = rset.getString("email_address");
			String designation = rset.getString("designation");
			int id = rset.getInt("applicant_id");
			int int_id= rset.getInt("interview_id");
			String status=rset.getString("Status_of_interview");

			System.out.printf("%-13s%-22s%-14s%-18s%-12s%-18s%-28s \n",fname,lname,email,designation,id,int_id,status);
		} //end of while
		rset.close();

	} //end of getApplicantStatus method
	
	public static void updateJobApplicantProfile() throws Exception{

		//variable declaration
		
		int app_id;
		String email;
		String designation="";

		br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please Enter The Job Applicant_id [Ex: 12122 ]");
		app_id = Integer.valueOf(br.readLine());

		System.out.println("Enter email Address of Applicant  [Ex: sanju@gmail.com ] ");
		email= br.readLine();

		System.out.println("Enter the Designation String you want to Update[Ex: Software Engineer ] ");
		designation= br.readLine();

		String SQLQuery="UPDATE Job_Applicant "+
				"SET    Designation ="+ "'"+designation+"'"+ 
				" WHERE  Email_address ="+ "'"+email+"'"+ "\n"+
				" AND  Applicant_Id ="+ app_id; 

		stmt.executeUpdate(SQLQuery); //using the executeUpdate method of the statement class to perform an Update into the database

		System.out.println("*****************************************");
		System.out.println("   Designation updation Successful..!!");
		System.out.println("*****************************************");
		
		conn.commit(); //commit the transaction 

	}//end of updateJobApplicantProfile method
	
	
	//This Method feature is used by the applicants who want to withdraw their job applications on a particular date
	public static void deleteJobApplied() throws Exception{
		
		int appId;
		System.out.println("Please Enter The Job Applicant_id [Ex: 98473 ]");
		appId = Integer.valueOf(br.readLine());
		
		String date;
		System.out.println("Please Enter the Date for the Job application[Ex: 08-Dec-16 ] ");
		date= br.readLine();

		String SQLQuery="DELETE FROM Applied_Job "+
				" WHERE APPLICANT_ID ="+appId+ "\n"+
				" AND DATE_OF_APPLICATION="+"'"+date+"'";
		
		System.out.println(SQLQuery);

		stmt.executeUpdate(SQLQuery); //using the executeUpdate method of the statement class to perform an Update into the database

		System.out.println("*****************************************");
		System.out.println("  Applied Jobs Deleted Successfully...!!");
		System.out.println("*****************************************");
		
		conn.commit(); //commit the transaction

	} //end of deleteJobApplied



}//end of class
