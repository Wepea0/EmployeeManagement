//Modularized the create team menu
//Need to find a way to collect the extra information necessary for the teams (and the employees but that's for later)




import java.io.*;
import java.nio.channels.FileLockInterruptionException;
import java.util.*;

import javax.swing.event.SwingPropertyChangeSupport;


/**<h1> Employee Interface </h1>
 * Constructs a user interface for the Employee Management System 
 * @author Wepea Buntugu
 * @version 1.0
 * @since 2022-04-28
 */
public class EmployeeInterface extends Employee{

    public EmployeeInterface(String name) {
        super(name);
        //TODO Auto-generated constructor stub
    }
    /**Method to print welcome menu and redirect user based on their input */
    public static void welcomeMenu(){
        
        
        
        System.out.println("Hello! Welcome to the Company program "
        + "\nAre you looking to modify employee information or team information? "
        + "\n\nEnter '1' for Employee Operations "
        + "\nEnter '2' for Team Operations"
        + "\nEnter '3' to Exit");
        System.out.print(">>> ");

        Integer employeeOps = 1;
        Integer teamOps = 2;
        Integer exit = 3;

        Scanner firstInput = new Scanner(System.in);
        try{
        Integer firstUserChoice = firstInput.nextInt();
        if (firstUserChoice.equals(teamOps)){
            teamMenu();
        }
        else if(firstUserChoice.equals(employeeOps)){
            employeeMenu();
        }
        else if(firstUserChoice.equals(exit)){
           saveAllData();
          
        }
        else{
            System.out.println("Please enter a valid input");
            welcomeMenu();
        }
        firstInput.close();
    }
    catch(Exception e){
        System.out.println("Please enter a valid input");
        welcomeMenu();
    }



    }
    /**Method to print menu for operations on an employee and then take and user input */
    public static void employeeMenu(){
        System.out.println("\nWelcome to the Employee menu!\n"
        + "Enter '1' to Create New Employee\n"
        + "Enter '2' to Modify Employee Information\n"
        + "Enter '3' to View Employee Information\n"
        + "Enter '4' to Go Back to Main Menu\n");
        System.out.print(">>> ");
        Scanner secondInput =  new Scanner(System.in);
        try{
        Integer secondUserChoice = secondInput.nextInt();

        Integer createEmployee = 1;
        Integer modEmployee = 2;
        Integer viewEmployee = 3;
        Integer mainMenu = 4;

        if (secondUserChoice.equals(1)){
            Employee.codeGeneratorHelp = Employee.getAllEmployees().size();
            System.out.println("\nWelcome to the Create Employee menu!");
            Scanner thirdInput = new Scanner(System.in);
            System.out.print("\nWhat is the name of the new employee\n>>> ");
            String newEmployeeName = thirdInput.nextLine();

            Employee newTeamMember = new Employee(newEmployeeName);

            // System.out.println("\nDoes this employee belong to a team."
            // + "\nIf Yes, enter the name of the team\n"
            // + "If No, press enter");
            // String newEmployeeTeam = thirdInput.nextLine();
            // if (!newEmployeeTeam.isEmpty()){
            //     if (newTeamMember.assignToTeam(newEmployeeTeam)){
            //         System.out.println("Team assignment successful!");
            //     }
            //     else{
            //         Team newTeam = new Team(newEmployeeTeam);
            //         newTeamMember.assignToTeam(newTeam.getTeamName());
                
            //     } 
            // }

            System.out.println("\nDoes this employee have any qualifications?\n" 
            + "If Yes, enter the qualifications one by one separated by the enter key. Enter DONE to end.\n"
            + "If No, enter 'DONE'");
            Scanner fourthInput = new Scanner(System.in);
            String qualis = fourthInput.nextLine(); //Change to uppercase, lowercase etc
        
            while(!qualis.equals("DONE")){
                newTeamMember.addQualifications(qualis);
                qualis = fourthInput.nextLine();

            }

            System.out.println("\nDoes this employee have a task assigned to them\n"
            + "If Yes, enter the task\n"
            + "If No, press enter" );
            Scanner fifthInput = new Scanner(System.in);
            try{
                String newMemberTask = fifthInput.nextLine();
                if(!newMemberTask.isEmpty()){
                newTeamMember.assignTask(newMemberTask);
            }
            }
            catch (Exception e){
                System.out.println("Please enter a valid input");
                employeeMenu();
            }

            System.out.println("\nHow long has this employee been at the company?"
            + " Enter their tenure length in months.");
            System.out.print(">>> ");
            Scanner sixthInput = new Scanner(System.in);
            try{
                Integer newEmployeeTenure = sixthInput.nextInt();
                newTeamMember.changeTenure(newEmployeeTenure);
                opSuccess(true);
                employeeMenu();
            }
            catch(Exception e){
                System.out.println("Please enter a valid input");
                employeeMenu();
            }
            

            //newTeamMember.printFullDetails();

        }
        else if(secondUserChoice.equals(2)){
            System.out.println("\nWelcome to the Modify Employee menu!");
            modifyEmployeeMenu();


        }
        else if(secondUserChoice.equals(3)){
            viewInfoMenu();
            }
 
        else if(secondUserChoice.equals(4)){
            welcomeMenu();
        }
        else{
            System.out.println("Please enter a valid input");
            employeeMenu();
        }
    }
    catch(Exception e){
        System.out.println("Enter a valid input");
        employeeMenu();
    }
        

    }

    /**Method to print menu for operations on a team and then take and user input */
    public static void teamMenu(){
        System.out.println("\nWelcome to the Team menu!\n"
        + "Enter '1' to Create A Team\n"
        + "Enter '2' to Display The Company Hierarchy\n"
        + "Enter '3' to Modify Teams\n"
        + "Enter '4' to View All Teams\n"
        + "Enter '5' to Go to main menu");
        System.out.print(">>> ");
        Integer exit = 4;
        Scanner secondInput =  new Scanner(System.in);
        try{
            Integer nextInput = secondInput.nextInt();
            if(nextInput.equals(1)){
                createTeam();
            }
            
            else if(nextInput.equals(2)){
                if(Team.companyExists() != null){
                    Team company = Team.companyExists();
                    ProjectTree.createCompanyTree(company);
                    teamMenu();
                }
                else{
                 System.out.println("Please create the required company and at least one department");
                 teamMenu();
                }
            }
            else if(nextInput.equals(3)){
                modTeamMenu();
            }
            else if(nextInput.equals(4)){
                viewTeamMenu();
            }
            else if(nextInput.equals(5)){
                welcomeMenu();
            }
            else{
                enterValidInput();
                teamMenu();
            }
            

        }
        catch(Exception e){
            System.out.println("Enter a valid input");
            teamMenu();
        }

      
       
    }





    public static void main(String[] args){
        loadMenu();
        
        
    }



    /**************************************         Helper Methods                   *********************************************/
    /** Helper method to add Sub-Team to a department */
    public static void assignSubTeam(Team reqTeam){
        System.out.println("Welcome to the assign a subTeam menu!");
        System.out.println("All subteams");
        Integer count = 1;
        for(Team teams: Team.getAllTeams()){
            if(!teams.findIsDepartment() && !teams.isCompany()){
                System.out.println(count++ + ". Team Name - " + teams.getTeamName()
               + " Team Code - " + teams.getTeamCode());
            }
        }
        try{
            Scanner firstInput = new Scanner(System.in);
            System.out.println("Enter the code of the subteam you would like to assign to this team.");
            System.out.print(">>> ");
            String reqTeamCode = firstInput.nextLine();
            Team reqSubTeam = Team.findTeam(reqTeamCode);
            if(reqTeam.addSubTeam(reqSubTeam)){
                System.out.println("Success!");
            }
            else if (!reqTeam.addSubTeam(reqSubTeam)){
                System.out.println("Addition error!");
            }

        }
    catch(Exception e){
        System.out.println("Error detected!");
        return;
    }
        
    }


    /** Helper method to add Sub-Team to a department */
    public static void addSubTeamToDept(Team subTeam){
        System.out.println("Welcome to the assign a subTeam to a department menu!");
        System.out.println("All Department");
        Integer count = 1;
        for(Team teams: Team.getAllTeams()){
            if(teams.findIsDepartment()){
                System.out.println(count++ + ". Team Name - " + teams.getTeamName()
               + " | Team Code - " + teams.getTeamCode());
            }
        }
        try{
            Scanner firstInput = new Scanner(System.in);
            System.out.println("Enter the code of the department you would like this Subteam to fall under");
            System.out.print(">>> ");
            String reqTeamCode = firstInput.nextLine();
            Team reqTeam = Team.findTeam(reqTeamCode);
            if(reqTeam.addSubTeam(subTeam)){
                System.out.println("Success!");
            }
            else if (!reqTeam.addSubTeam(subTeam)){
                System.out.println("Addition error!");
            }

        }
    catch(Exception e){
        System.out.println("Error detected!");
        return;
    }
        
    }

    /**Helper method to add team members to a team*/
    public static void addTeamMembersTo(Team team){
        System.out.println("Welcome to the add team members menu!");
        String emp = "Employee List";
        System.out.println(String.format("%10s", emp));
        if(Employee.getAllEmployees().size() > 0){
            for(Employee employees: Employee.getAllEmployees()){
                System.out.println(employees);
            }
            System.out.println("Enter the employee code of the employee you would like to add to this department"
           +  "\nEnter '1' to skip and Go To Team Menu");
            System.out.print(">>> ");
            Scanner firstInput = new Scanner(System.in);

            try{
                String reqEmpCode = firstInput.nextLine();
                if (reqEmpCode.equals("1")){
                    System.out.println("Team created successfully!");
                    teamMenu();
                }
                Employee reqEmp = findEmployee(reqEmpCode);
                if (team.addTeamMember(reqEmp)){
                    System.out.println("Addition successful!");
                    
                    System.out.println("Enter '1' to add another employee"
                    + "\nEnter '2' to Go Back to Team Menu");
                    Scanner secondInput = new Scanner(System.in);
                    Integer userChoice = secondInput.nextInt();
                    try{
                        if(userChoice.equals(1)){
                            addTeamMembersTo(team);
                        }
                        else if(userChoice.equals(2)){
                            System.out.println("Operation completed");
                            teamMenu();
                            
                        }
                        else{
                            System.out.println("Error detected!");
                        }
                        secondInput.close();
                    }
                    catch(Exception e){
                        System.out.println("Error detected!");
                    }
                }
                else{
                    System.out.println("Error detected!");
                    addTeamMembersTo(team);
                }
                firstInput.close();
            }
            catch(Exception e){
                System.out.println("Error detected!");
                return;
            }
           
        }
        else{
            System.out.println("Create an employee to add to this department"
            + "\nEnter '1' to create a new employee to add to this department"
            + "\nEnter '2' to skip this step");
            Scanner firstInput = new Scanner(System.in);
            try{
                String userChoice = firstInput.nextLine();
                if(userChoice.equals("1")){
                    System.out.println("Create a new employee to add to this department");
                }
                else if (userChoice.equals("2")){
                    return;
                }
                firstInput.close();

            }
            catch(Exception e){
                System.out.println("Error detected!");
            }
            
        } 
    }



    /** Assigns all existing departments as subteams of the company. This aids with company hierarchy process */
    public static void assignAllDepts(Team company){
        for(Team teams: Team.getAllTeams()){
            if (teams.findIsDepartment()){
                company.addSubTeam(teams);
            }
        }
    }

        /**Helper method to view all teams */
    public static void viewTeamMenu(){
        System.out.println("Welcome to the view team menu!");
        System.out.println("There have been " + Team.getAllTeams().size() + " teams created");
        int count = 0;
        for(Team teams: Team.getAllTeams()){
            System.out.println(count++ + ". Team Name - " + teams.getTeamName() + " || Team Code - " + teams.getTeamCode()
            + " || Description - " + teams.getTeamDesc());
        }
        System.out.println("\nEnter the team code of the team you would like to view");
        Scanner firstInput = new Scanner(System.in);
        try{
            String reqTeamCode = firstInput.nextLine();
            Team reqTeam = Team.findTeam(reqTeamCode);
            System.out.println("Full details of " + reqTeam.getTeamName() + " below...");
            reqTeam.printFullDetails();
            System.out.println("Operation successful. Redirecting to Team Menu...");
            teamMenu();

        }
        catch(Exception e){
            enterValidInput();
            teamMenu();
        }

    }

    /**Specialized menu for creating an employee within the modify team menu
     * @param reqTeam Team object of the team being modified
     * @returns Employee object of the employee that has been created 
     */
    public static Employee createEmployee(Team reqTeam){
        Employee.codeGeneratorHelp = Employee.getAllEmployees().size();
        System.out.println("\nWelcome to the Create Employee menu!");
        try{
            Scanner thirdInput = new Scanner(System.in);
            System.out.print("\nWhat is the name of the new employee\n>>> ");
            String newEmployeeName = thirdInput.nextLine();

            Employee newTeamMember = new Employee(newEmployeeName);

            System.out.println("Does this team member have any qualifications?"
            + "\nEnter these qualifications separated by the enter key, enter 'Done' to end.");
            System.out.print(">>> ");
            Scanner fourthInput = new Scanner(System.in);
            String empQualis = fourthInput.nextLine();
            String qualiChecker = empQualis.toLowerCase();
            while(!qualiChecker.equals("done")){
                newTeamMember.addQualifications(empQualis);
                System.out.print(">>> ");
                empQualis = fourthInput.nextLine();
                qualiChecker = empQualis.toLowerCase();
            }

            newTeamMember.printFullDetails();
            return newTeamMember;
        }
        catch(Exception e){
            enterValidInput();
            secModTeamMenu(reqTeam);
        }
        return null;
        
    }

    /**Function for displaying all employees and allowing user to pick one employee
     * @returns Employee object that the user selects
     */
    public static Employee selectEmployee(){
        System.out.println("\nThere have been " + Employee.getAllEmployees().size() + " employees created\n");
        int count = 1;
        for(Employee employees: Employee.getAllEmployees()){
            System.out.println(count++ + ". " + employees);
        }
        
        System.out.println("\nEnter the employee code of the employee you would like to select.");
        System.out.print(">>> ");
        Scanner seventhInput = new Scanner(System.in);
        try{
            String reqEmpCode = seventhInput.nextLine();
            Employee reqEmp = Employee.findEmployee(reqEmpCode);
            if(reqEmp != null){
                return reqEmp;
            }
            else{
                System.out.print("Employee not found. ");
                enterValidInput();
                selectEmployee();
            }
        }
        catch(Exception e){
            System.out.print("Employee not found. ");
            enterValidInput();
            selectEmployee();

        }

        return null;
    }

    
        

    /** Secondary team for extra options in team modification process 
     * @param reqTeam team to be modified
    */
    public static void secModTeamMenu(Team reqTeam){
        try{
            System.out.println("\nEnter '1' to change this team's name."
            + "\nEnter '2' to change this team's description."
            + "\nEnter '3' to assign a team lead to this team."
            + "\nEnter '4' to assign team members to this team."
            + "\nEnter '5' to remove team members from this team."
            + "\nEnter '6' to assign subteams to this team."
            + "\nEnter '7' to remove subteams from this team"
            /**Display all subteams in this team - If list is empty go back
             * Take input for one to remove
             * Remove it from this teams subteam list
             */
            + "\nEnter '8' to remove this team"
            + "\nEnter '9' to go back to Main Menu");
            System.out.print(">>> ");
            Scanner secondInput = new Scanner(System.in);
            Integer userChoice = secondInput.nextInt();
            if (userChoice.equals(1)){
                Scanner thirdInput = new Scanner(System.in);
                System.out.println("Enter the new team name.");
                System.out.print(">>> ");
                String newTeamName = thirdInput.nextLine();
                reqTeam.changeTeamName(newTeamName);
                //System.out.println("Operation successful!");
            }

            else if(userChoice.equals(2)){
                Scanner thirdInput = new Scanner(System.in);
                System.out.println("Enter the new team description.");
                System.out.print(">>> ");
                String newTeamDesc = thirdInput.nextLine();
                reqTeam.changeTeamDesc(newTeamDesc);
                //System.out.println("Operation successful!");
            }

            else if(userChoice.equals(3)){
                Scanner newInput = new Scanner(System.in);
                System.out.println("Enter '1' to select from the list of already created employees"
                + "\nEnter '2' to create a new employee");
                Integer userLeadChoice = newInput.nextInt();
                if(userLeadChoice.equals(1)){
                    if(Employee.getAllEmployees().size() == 0){
                        System.out.println("0 employees created. Redirecting to modify team menu...");
                        secModTeamMenu(reqTeam);
                    }
                    else{
                        Employee reqEmp = selectEmployee();
                        reqTeam.changeTeamLead(reqEmp);
                        secModTeamMenu(reqTeam);
                    }
                }
                else if(userLeadChoice.equals(2)){
                    Employee reqTeamLead = createEmployee(reqTeam);
                    reqTeam.changeTeamLead(reqTeamLead);
                    //reqTeam.printFullDetails();
                    secModTeamMenu(reqTeam);

                }
                else{
                    System.out.println("Not 1 or 2");
                    enterValidInput();
                    secModTeamMenu(reqTeam);
                }
                
            }
            if(userChoice.equals(4)){
                addExistingEmpToTeam(reqTeam);
            }

            if(userChoice.equals(5)){
                removeEmpFromTeam(reqTeam);
            }

            if(userChoice.equals(6)){
                mainAssignSubTeam(reqTeam);
            }
            if(userChoice.equals(7)){
                removeSubTeamLite(reqTeam);
            }
            if(userChoice.equals(8)){
                removeTeamLite(reqTeam);
                teamMenu();
            }

            if(userChoice.equals(9)){
                System.out.println("Redirecting to team menu...");
                teamMenu();
            }
            else{
                enterValidInput();
                secModTeamMenu(reqTeam);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            enterValidInput();
            secModTeamMenu(reqTeam);
        }

    }

    /**Function to delete a team 
     * @param reqTeam team to be removed
     */
    public static void removeTeamLite(Team reqTeam){
        Team.getAllTeams().remove(reqTeam);
        for(Team teams: Team.getAllTeams()){
            for(Team subTeams: teams.getSubTeams()){
                if(subTeams.getTeamCode().equals(reqTeam.getTeamCode())){
                    teams.getSubTeams().remove(subTeams);
                }
            }
        }
        System.out.println("Team successfully removed! Redirecting to team menu");

    }

    /**Function to remove a subteam from another team during the team modification process */
    public static void removeSubTeamLite(Team reqTeam){
        if(reqTeam.getSubTeams().size() == 0){
            System.out.println("No subteams present in this team.\nRedirecting to team modification menu...");
            secModTeamMenu(reqTeam);
        }
        else{
            int count = 1;
            ArrayList<Team> validTeams = new ArrayList<Team>();
            for(Team teams: reqTeam.getSubTeams()){
                System.out.println(count++ + ". Team Name - " + teams.getTeamName() + " || Team Code - " + teams.getTeamCode());
                validTeams.add(teams);
            }
            try{
                Scanner firstInput = new Scanner(System.in);
                System.out.println("Enter the team code of the team you would like to remove");
                System.out.print(">>> ");
                String reqTeamCode = firstInput.nextLine();
                if(Team.findTeam(reqTeamCode)!= null){
                    Team subTeamToRemove = Team.findTeam(reqTeamCode);
                    if(!validTeams.contains(subTeamToRemove)){
                        System.out.println("Team not found 7");
                        secModTeamMenu(reqTeam);
                    }
                    reqTeam.getSubTeams().remove(subTeamToRemove);
                    System.out.println("Success!");
                    secModTeamMenu(reqTeam);
                }
                else{
                    System.out.println("Team not found ");
                    secModTeamMenu(reqTeam);
                }
            }
            catch(Exception e){
                enterValidInput();
                secModTeamMenu(reqTeam);
            }
        }
    }

      /**Method for creating a subTeam that is then assigned to another team
       * @param reqTeam team to which the created subTeam will be assigned
      */
      public static void createSubTeamLite(Team reqTeam){
       System.out.println("Under construction");
    }

    /**Holding function for assigning new subteams to a team within the modify team menu
     * @param reqTeam Team Object to be modified
     */
    public static void mainAssignSubTeam(Team reqTeam){
        {
        System.out.println("Enter '1' to select from already available subteams"
        + "\nEnter '2' to create a new subteam");
        System.out.print(">>> ");
        try{
            Scanner firstInput = new Scanner(System.in);
            Integer userDecision = firstInput.nextInt();
            if(userDecision.equals(1)){
                assignSubTeam(reqTeam);
                System.out.println("Enter '1' to assign a new subteam"
                + "\nEnter '2' to go back to Team Modification Menu");
                System.out.print(">>> ");
                Scanner nextInput = new Scanner(System.in);
                Integer nextUserChoice = nextInput.nextInt();
                if(nextUserChoice.equals(1)){
                    mainAssignSubTeam(reqTeam);
                }
                else if(nextUserChoice.equals(2)){
                    secModTeamMenu(reqTeam);
                }
                else{
                    System.out.println("Wrong input detected.\nRedirecting to team modification menu...");
                    secModTeamMenu(reqTeam);
                }
            }
            else if(userDecision.equals(2)){

               try{
                Team.codeGeneratorHelp = Team.getAllTeams().size() + 1;
                Scanner firstInput1 = new Scanner(System.in);
                System.out.println("Enter the name of this subteam");
                System.out.print(">>> ");
                String newSubTeamName = firstInput1.nextLine();
                Team newSubTeam = new Team(newSubTeamName);
                System.out.println("Enter the description for the team");
                System.out.print(">>> ");
                Scanner secondInput1 = new Scanner(System.in);
                String newDesc = secondInput1.nextLine();
                newSubTeam.changeTeamDesc(newDesc);
                if(reqTeam.addSubTeam(newSubTeam)){
                    System.out.println("Success!");
                }
                else{
                    System.out.println("Addition error!");
                }
               }
               catch(Exception e){
                   enterValidInput();
                   secModTeamMenu(reqTeam);
               }
                


                System.out.println("Enter '1' to assign a new subteam"
                + "\nEnter '2' to go back to Team Modification Menu");
                System.out.print(">>> ");
                Scanner nextInput = new Scanner(System.in);
                Integer nextUserChoice = nextInput.nextInt();
                if(nextUserChoice.equals(1)){
                    mainAssignSubTeam(reqTeam);
                }
                else if(nextUserChoice.equals(2)){
                    secModTeamMenu(reqTeam);
                }
                else{
                    System.out.println("Wrong input detected.\nRedirecting to team modification menu...");
                   secModTeamMenu(reqTeam);
                }
            }
            else{
                enterValidInput();
                secModTeamMenu(reqTeam);
            }

            firstInput.close();
            
        }
        catch(Exception e){
            enterValidInput();
            secModTeamMenu(reqTeam);

        }
    }

    }

/**Selection employee function that is modified for the team that is required
 * @returns Employee object that is selected by the user
 */
    public static Employee selectEmployeeLite(Team reqTeam){
        System.out.println("\nThere have been " + reqTeam.getTeamMembers().size() + " employees created\n");
        int count = 1;
        for(Employee employees: reqTeam.getTeamMembers()){
            System.out.println(count++ + ". " + employees);
        }
        
        System.out.println("\nEnter the employee code of the employee you would like to select.");
        System.out.print(">>> ");
        Scanner seventhInput = new Scanner(System.in);
        try{
            String reqEmpCode = seventhInput.nextLine();
            Employee reqEmp = Employee.findEmployee(reqEmpCode);
            if(reqEmp != null){
                return reqEmp;
            }
            else{
                System.out.print("Employee not found. ");
                enterValidInput();
                selectEmployee();
            }
        }
        catch(Exception e){
            System.out.print("Employee not found. ");
            enterValidInput();
            selectEmployeeLite(reqTeam);

        }

        return null;
    }

    
/**Function for removing an employee from a required team */
    public static void removeEmpFromTeam(Team reqTeam){
          try{
              if (reqTeam.getTeamMembers().size() == 0){
                  System.out.println("There are no employees in this team");
                  secModTeamMenu(reqTeam);
              }
            Employee reqEmp = selectEmployeeLite(reqTeam);
            if(reqTeam.removeFromTeam(reqEmp.getEmployeeCode())){
                System.out.println("Do you want to remove another team member from this team"
                +"\nEnter '1' to remove another team member"
                +"\nEnter '2' to return to team modification menu");
                Scanner firstInput = new Scanner(System.in);
                Integer userDecision = firstInput.nextInt();
                if(userDecision.equals(1)){
                    removeEmpFromTeam(reqTeam);
                }
                else if(userDecision.equals(2)){
                    secModTeamMenu(reqTeam);
                }
                else{
                    enterValidInput();
                    System.out.print("Redirecting to modify team menu");
                    secModTeamMenu(reqTeam);
                }

            }
          }   
          catch(Exception e){
              enterValidInput();
              removeEmpFromTeam(reqTeam);
          }                                                                                                                                                                                        
    }

    /**Allows users to add team members to a team from the list of already existing employees */
    public static void addExistingEmpToTeam(Team reqTeam){
        try{
            Integer userDecision = 1;
            
                Employee reqEmp = selectEmployee();
                if(reqTeam.addTeamMember(reqEmp)){
                    System.out.println("Addition successfull");
                }
                else{
                    System.out.println("Addition failed");
                }
                System.out.println("Do you want to add another team member to this team"
                +"\nEnter '1' to add another team member"
                +"\nEnter '2' to return to team modification menu");
                Scanner firstInput = new Scanner(System.in);
                userDecision = firstInput.nextInt();
                if(userDecision.equals(1)){
                    addExistingEmpToTeam(reqTeam);

                }
                else if(userDecision.equals(2)){
                    secModTeamMenu(reqTeam);
                }
                else{
                    enterValidInput();
                    secModTeamMenu(reqTeam);
                }               
                    
            }
        catch(Exception e){
            enterValidInput();
            addExistingEmpToTeam(reqTeam);
        }  
    }

    /**Gives users options to modify a particular team  */
    public static void modTeamMenu(){
        System.out.println("Welcome to the Modify team menu!");
        if(Team.getAllTeams().size() == 0){
            System.out.println("There are no teams currently created.\nRedirecting to the teams menu...");
            teamMenu();

        }
        int count = 1;
        for(Team teams: Team.getAllTeams()){
            System.out.println(count++ + ". Team Name - " + teams.getTeamName() + " || Team Code - " + teams.getTeamCode()
            + " || Description - " + teams.getTeamDesc());
        }
        System.out.println("Enter team code of the team you would like to modify");
        try{
            Scanner firstInput = new Scanner(System.in);
            String reqEmpCode = firstInput.nextLine();
            Team reqTeam = Team.findTeam(reqEmpCode);
            if(reqTeam != null){
                reqTeam.printFullDetails();
                secModTeamMenu(reqTeam);
            }
            else{
                System.out.print("Team could not be found. ");
                enterValidInput();
                modTeamMenu();
            }
        }
        catch(Exception e){
            enterValidInput();
            modTeamMenu();
        }

    }

    /**Used to make a specialised team object which is a department */
    public static void createDepartment(){
        Team.codeGeneratorHelp = Team.getAllTeams().size() + 1;
        System.out.println("Welcome to the Create Department Menu!");
        try{
            Team newDept;
            Scanner firstInput = new Scanner(System.in);
            System.out.println("Enter the name of the department");
            System.out.print(">>> ");
            String deptName = firstInput.nextLine();
            if(!deptName.isEmpty()){
                newDept = new Team(deptName);
                newDept.makeDepartment();

                if(Team.companyExists()!=null){
                    Team.companyExists().addSubTeam(newDept);
                    System.out.println("Dept sorted");
                }

                Scanner secondInput = new Scanner(System.in);
                System.out.println("Enter a description for the department");
                System.out.print(">>> ");
                String deptDescription = secondInput.nextLine();
                if(!deptDescription.isEmpty()){
                    newDept.changeTeamDesc(deptDescription);
                    System.out.println(newDept.getTeamDesc());
                }
                else{
                    enterValidInput();
                    createDepartment();
                }
                Scanner thirdInput = new Scanner(System.in);
                System.out.println("\nDo you want to add team members to this department"
                + "\nEnter '1' to add team members to this department"
                + "\nEnter '2' to skip this step");
                Integer setLead = thirdInput.nextInt();
                if(setLead.equals(1)){
                    addTeamMembersTo(newDept);
                }
                else if(setLead.equals(2)){

                    System.out.println("Department created successfully!"
                    + "\nAccess extra department options in the modify team menu.");
                    saveTeamData();
                    teamMenu();
                }  
                thirdInput.close();
                secondInput.close();
                firstInput.close();   
            }
           
        }
        catch(Exception e){
            enterValidInput();
            createDepartment();
        }
    }

    /**Used to make a specialised team object which is a sub-team */
    public static void createSubTeam(){
        Team.codeGeneratorHelp = Team.getAllTeams().size() + 1;
        System.out.println("\nWelcome to the Create Sub-Team Menu!");
        Scanner firstInput = new Scanner(System.in);
        try{
            Team newSubTeam;
            System.out.println("Enter the name of this subteam");
            System.out.print(">>> ");
            String subTeamName = firstInput.nextLine();
            if(!subTeamName.isEmpty()){
                newSubTeam = new Team(subTeamName);
                Scanner secondInput = new Scanner(System.in);
                System.out.println("\nEnter a description for this team");
                System.out.print(">>> ");
                String newSubDesc = secondInput.nextLine();
                newSubTeam.changeTeamDesc(newSubDesc);
                System.out.println("Sub-team created successfully!");

                Scanner fourthInput = new Scanner(System.in);
                System.out.println("Enter '1' to add this team to a department"
                + "\nEnter '2' to skip this step");
                Integer addDept = fourthInput.nextInt();
                if(addDept.equals(1)){
                    addSubTeamToDept(newSubTeam);

                    Scanner fifthInput = new Scanner(System.in);
                    System.out.println("Enter '1' to add team members to this sub-team"
                    + "\nEnter '2' to skip this step");
                    Integer addMembers = fifthInput.nextInt();
                    if(addMembers.equals(1)){
                        addTeamMembersTo(newSubTeam);
                    }
                    else{
                        System.out.println("Sub-team created successfully!");
                        saveTeamData();
                        teamMenu();
                    }
                }
                else{
                    Scanner fifthInput = new Scanner(System.in);
                    System.out.println("Enter '1' to add team members to this sub-team"
                    + "\nEnter '2' to skip this step");
                    Integer addMembers = fifthInput.nextInt();
                    if(addMembers.equals(1)){
                        addTeamMembersTo(newSubTeam);
                    }
                    else{
                        System.out.println("Sub-team created successfully!");
                        teamMenu();
                    }
                }
                secondInput.close();
                fourthInput.close();
            }
            else{
                createSubTeam();
            }
            firstInput.close();
           
            
        } 
        catch(Exception e){
            enterValidInput();
            createSubTeam();
        }
        
        
    }


    /**Used to make a specialised team object which is a company */
    public static void createCompany(){
        Team.codeGeneratorHelp = 0;
        System.out.println("Enter the name of the company");
        System.out.print(">>> ");
        Scanner firstInput = new Scanner(System.in);
        try{
            String newCompanyName = firstInput.nextLine();
            if(!newCompanyName.isEmpty()){
                Team company = new Team(newCompanyName);
                company.makeCompany();
                Scanner secondInput = new Scanner(System.in);
                System.out.println("Enter a description for the company");
                System.out.print(">>> ");
                String deptDescription = secondInput.nextLine();
                if(!deptDescription.isEmpty()){
                    company.changeTeamDesc(deptDescription);
                    System.out.println(company.getTeamDesc());
                }
                else{
                    enterValidInput();
                    createCompany();
                }
                System.out.println("New company created successfully");
                assignAllDepts(company);
                teamMenu();
                firstInput.close();
                secondInput.close();
            }
            else{
                System.out.println("Enter valid input");
            createCompany();

            }
            
        }
        catch(Exception e){
            enterValidInput();
            createCompany();
        }
    }

     /** Prompts user to enter valid input */
     public static void enterValidInput(){
        System.out.println("Enter valid input");
    }
    
    
    public static void saveAllData() throws IOException{
        saveEmpData();
        saveTeamData();
         System.out.println("\nThank you for using our system!\n"
         + "- Alosius and Wepea ***");
         System.exit(0);  
    }
    /**Helper method to create a team */
    public static void createTeam(){
        System.out.println("\nEnter '1' to Create A Company"
        + "\nEnter '2' to Create A Department"
        + "\nEnter '3' to Create A Subteam"
        + "\nEnter '4' to Go Back To The Team Menu");
        System.out.print(">>> ");
        Scanner fifthInput = new Scanner(System.in);
        try{
            Integer teamType = fifthInput.nextInt();
            if(teamType.equals(1)){
                if(Team.companyExists() == null ){
                   createCompany();
                }

                else{
                    System.out.println("All departments  " + Team.companyExists().getSubTeams());
                    System.out.println("Company already exists. View company info below " + Team.companyExists());

                    teamMenu();
                    //Let user know they can start over if they want to change the company
                    Scanner sixthInput = new Scanner(System.in);
                    Integer companyChoice = sixthInput.nextInt();
                    if(companyChoice.equals(1)){
                        System.out.println("Welcome to company edit menu");
                        saveTeamData();//Take out when the view and edit is added
                        teamMenu();
                    }
                    if(companyChoice.equals(2)){
                        teamMenu();
                    }
                    else{
                        enterValidInput();  
                        createTeam();
                    }
                }
                
            }

            if(teamType.equals(2)){
                createDepartment();
            }
            
            if(teamType.equals(3)){
                createSubTeam();
            }
            if(teamType.equals(4)){
                teamMenu();
            }

            else{
                enterValidInput();
                createTeam(); 
            }
            fifthInput.close();

        }
        catch(Exception e){
            enterValidInput();
            createTeam();
            
        }
      
                         
      
    }   
    
    /**Helper menu to ask user if data from previous session should be loaded */
    public static void loadMenu(){
        System.out.println("Would you like to load your old data\n"
        + "Enter '1' to Load Previous Data\n"
        + "Enter '2' to Start New Session");
        System.out.print(">>> ");
        Scanner newInput = new Scanner(System.in);
        try{
            Integer toLoad = newInput.nextInt();
            if(toLoad.equals(1)){
                loadEmpData();
                loadTeamData();
                welcomeMenu();
            }
            else if(toLoad.equals(2)){
                welcomeMenu();
            }
            else{
                System.out.println("Enter valid input");
                loadMenu();
            }
        }
        catch(Exception e){
            System.out.println("Enter valid input");
            loadMenu();
            }
        }


    /**Helper method to load data from previous session */
    public static ArrayList<Employee> loadEmpData() throws IOException{
        ArrayList<Employee> Employees = null;
        try{
            
            FileInputStream fis = new FileInputStream("EmployeeInfo.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Employees = (ArrayList<Employee>)ois.readObject();
            ArrayList<Employee> empList = Employees;
            for(Employee employees: empList){
                Employee.getAllEmployees().add(employees);
            }
            return Employees;
        }
        catch(Exception e){
            System.out.println("Data load error - " + e.getMessage());
            welcomeMenu();
        }
        return null;
    }
       /**Helper method to load data from previous session */
       public static ArrayList<Team> loadTeamData() throws IOException{
        ArrayList<Team> Teams = null;
        try{
            
            FileInputStream fis = new FileInputStream("TeamInfo.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Teams = (ArrayList<Team>)ois.readObject();
            ArrayList<Team> empList = Teams;
            for(Team teams: empList){
                Team.getAllTeams().add(teams);
            }
            return Teams;
        }
        catch(Exception e){
            System.out.println("Data load error - " + e.getMessage());
            welcomeMenu();
        }
        return null;
    }


    /**Helper method to save user data */
    public static void saveEmpData() throws IOException {
        try{
        FileOutputStream fos = new FileOutputStream("EmployeeInfo.ser");
        ObjectOutputStream oos =  new ObjectOutputStream(fos);
        oos.writeObject(Employee.getAllEmployees());
        oos.close();
        fos.close();
        System.out.println("Data saved successfully");
        }
        catch(Exception e){
            System.out.println("Data save error");
            System.exit(1);
        }
    }

        /**Helper method to save user data */
        public static void saveTeamData() throws IOException {
            try{
            FileOutputStream fos = new FileOutputStream("TeamInfo.ser");
            ObjectOutputStream oos =  new ObjectOutputStream(fos);
            oos.writeObject(Team.getAllTeams());
            oos.close();
            fos.close();
            System.out.println("Data saved successfully");
            }
            catch(Exception e){
                System.out.println("Data save error");
                System.exit(1);
            }
        }
    
    
    
    /**Helper method to let user know whether operation was successful or not 
     * @param Boolean true or false prints successful message or unsuccessful message respectively
    */
    public static void opSuccess(boolean Boolean){
        if (Boolean){
            System.out.println("Operation was successful!");
        }
        else{
            System.out.println("Operation was unsuccessful");
        }
    }

    /**Helper method to print view info menu and take user input */
    public static void viewInfoMenu(){
        System.out.println("\nWelcome to the View Employee Information menu!");
        System.out.println("\nThere have been " + Employee.getAllEmployees().size() + " employees created\n");
        int count = 1;
        for(Employee employees: Employee.getAllEmployees()){
            System.out.println(count++ + ". " + employees);
        }
        
        System.out.println("\nEnter the employee code of the employee you would like to view");
        System.out.print(">>> ");
        Scanner seventhInput = new Scanner(System.in);
        try{
            String reqEmployeeCode = seventhInput.nextLine();
            Employee reqEmployee = Employee.findEmployee(reqEmployeeCode);
            if (reqEmployee != null){
                System.out.println("Here is your information\n");
                reqEmployee.printFullDetails();
                employeeMenu();
            }
            else{
                System.out.println("Employee not found. Kindly enter a valid employee code");
                //viewEmployee()
                viewInfoMenu();
            }
        }
        catch (Exception e){
            System.out.println("Kindly enter valid input");
            employeeMenu();
        }
    }
    
    /**Helper method to print modify employee info menu */
    public static void modifyEmployeeMenu(){
        System.out.println("\nThere have been " + Employee.getAllEmployees().size() + " employees created\n");
        int count = 0;
        for(Employee employees: Employee.getAllEmployees()){
            System.out.println(count++ + ". " + employees);
        }
        System.out.println("Enter the employee code of the employee whose information you want to modify");
        System.out.print(">>> ");
        Scanner eighthInput = new Scanner(System.in);
        try{
            String reqEmployeeCode = eighthInput.nextLine();
            Employee reqEmployee = Employee.findEmployee(reqEmployeeCode);
            if (reqEmployee != null){
                reqEmployee.printFullDetails(); 
                secondaryModifyMenu(reqEmployee);
                //  Menu to ask what to change
            }
            else{
                System.out.println("Employee not found. Kindly enter a valid employee code");
                modifyEmployeeMenu();
            }
        }
        catch (Exception e){
            System.out.println("Kindly enter valid input");
            modifyEmployeeMenu();
        }


    }

    /**Helper function to provide the modification option the user has 
     * @param reqEmployee employee object which will be operated on
    */
    public static void secondaryModifyMenu(Employee reqEmployee){
        System.out.println("\nWhat operation would you like to perform on this employee?\n"
        + "Enter '1' to Change Employee Name\n"
        + "Enter '2' to Change Employee Tenure\n"
        + "Enter '3' to Change Current Task\n"
        + "Enter '4' to Add Employee Qualification\n"
        + "Enter '5' to Remove Employee\n"
        + "Enter '6' to Go Back To Main Menu");
        System.out.print(">>> ");
        Scanner ninthInput = new Scanner(System.in);
        try{
            Integer userModChoice = ninthInput.nextInt();
            if (userModChoice.equals(1)){
                System.out.println("Enter the new employee name");
                System.out.print(">>> ");
                Scanner tenthInput = new Scanner(System.in);
                String newEmpName = tenthInput.nextLine();
                if (!newEmpName.isEmpty()){
                    reqEmployee.changeEmployeeName(newEmpName);
                    System.out.println(reqEmployee);
                    secondaryModifyMenu(reqEmployee);
                }
                else{
                    System.out.println("Wrong input");
                    secondaryModifyMenu(reqEmployee);

                }
            }

            if (userModChoice.equals(2)){
                System.out.println("Enter the new employee tenure");
                System.out.print(">>> ");

                Scanner elevenInput = new Scanner(System.in);
                try{
                    Integer newEmpTenure = elevenInput.nextInt();
                    reqEmployee.changeTenure(newEmpTenure);
                    System.out.println("Operation successful");
                    secondaryModifyMenu(reqEmployee);
                    elevenInput.close();
                }
                catch(Exception e){
                    System.out.println("Kindly enter a valid input");
                    secondaryModifyMenu(reqEmployee);
                }
            }

            if(userModChoice.equals(3)){
                System.out.println("Enter the employee's new task");
                System.out.print(">>> ");
                Scanner twelveInput = new Scanner(System.in);
                try{
                    String newEmpTask = twelveInput.nextLine();
                    reqEmployee.assignTask(newEmpTask);
                    System.out.println("Operation successful");
                    secondaryModifyMenu(reqEmployee);
                }
                catch(Exception e){
                    System.out.println("Kindly enter a valid input");
                    secondaryModifyMenu(reqEmployee);
                }
            }
            if(userModChoice.equals(4)){
                System.out.println("Enter the employee's new qualification");
                System.out.print(">>> ");
                Scanner thirteenInput = new Scanner(System.in);
                try{
                    String newEmpQuali = thirteenInput.nextLine();
                    reqEmployee.addQualifications(newEmpQuali);
                    System.out.println("Operation successful");
                    secondaryModifyMenu(reqEmployee);
                }
                catch(Exception e){
                    System.out.println("Kindly enter a valid input");
                    secondaryModifyMenu(reqEmployee);
                }
            }

            if(userModChoice.equals(5)){
                System.out.println("Enter '1' to confirm employee removal\n"
                + "Enter '2' to go back to Modify Employee Menu");
                Scanner twelveInput = new Scanner(System.in);
                System.out.print(">>> ");

            try{
                Integer toFire = twelveInput.nextInt();
                if (toFire.equals(1)){
                    Employee.fireEmployee(reqEmployee.getEmployeeCode());
                    employeeMenu();
                }
                else if(toFire.equals(2)){
                    secondaryModifyMenu(reqEmployee);
                }
                else{
                    System.out.println("Kindly enter a valid input");
                    secondaryModifyMenu(reqEmployee);
                }


            }
            catch(Exception e){
                System.out.println("Kindly enter a valid input");
                secondaryModifyMenu(reqEmployee);
            }
            }
        

            if (userModChoice.equals(6)){
                welcomeMenu();
            }
            else{
                System.out.println("Kindly enter a valid input");
                secondaryModifyMenu(reqEmployee);
            }
        }
        catch(Exception e){
            System.out.println("Kindly enter a valid input");
            secondaryModifyMenu(reqEmployee);
        }

    }
}