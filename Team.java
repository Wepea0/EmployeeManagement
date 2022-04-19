import java.util.*;


/**<h1> Team class </h1> 
 * Class for creating and maintaining different types of teams in an organization
 * 
 * @author Wepea Buntugu
 * @version 1.0
 * @since 2022-04-18
*/

//Last comma during print out of subteam and team member names

class Team{
    /**
     * Name
     * Code
     * Department
     * Description
     * Number of people
     * Team members
     * Team Lead
     */
    private String teamName;
    private String teamCode;
    private Team department; 
    private Integer noTeamMembers;
    private String description;
    private Employee teamLead;
    private ArrayList<Employee> teamMembers;
    private ArrayList<Team> subTeams;

    /**Boolean to denote if team is a department. */
    private boolean isDepartment;

    //Helper variables
    private String codeGenerator = "TM";
    private static Integer codeGeneratorHelp = 1;

    /**Constructor for Team object 
     * @param teamName Name of the team object to be created
    */
    public Team(String teamName){
        this.teamName = teamName;
        String finalCode = String.format("%03d", codeGeneratorHelp++);
        this.teamCode = codeGenerator + "" + finalCode;
        this.description = "N/A";
        this.teamMembers = new ArrayList<Employee>();
        this.subTeams = new ArrayList<Team>();
        this.noTeamMembers = this.getNoTeamMembers();


    }

    /**Constructor for Team object
     * @param teamName Name of the team object to be created
     * @param departmentName Name of the department this team falls under
     * @param noTeamMembers Number of team members in the team
     * @param description Description of the team's job 
     * @param teamLead Employee object representing the lead employee for the team
     */
    public Team(String teamName, Team department, String description, Employee teamLead
    ){
        this.teamName = teamName;
        String finalCode = String.format("%03d", codeGeneratorHelp++);
        this.teamCode = codeGenerator + "" + finalCode;
        this.department = department;
        this.description = description;
        this.teamLead = teamLead;
        this.teamMembers = new ArrayList<Employee>();
        this.subTeams = new ArrayList<Team>();
        this.noTeamMembers = this.getNoTeamMembers();
    }

  
    /** Get the name of the team 
     * @returns the name of the team
    */
    public String getTeamName(){
        return this.teamName;
    }

    /** Change the name of a team
     * @param newTeamName the team's new name
     */
    public void changeTeamName(String newTeamName){
        this.teamName = newTeamName;
    }

    /**Get the team code 
     * @returns the team's code
    */
    public String getTeamCode(){
        return this.teamCode;
    }

    /**Get the name of the department that this team is associated with if it is not
     * a department itself. If so, the team object itself is returned.
     * @return Team object representing the department this team is associated with
     */
    public Team getDepartment(){
        if(!this.isDepartment){
            return this.department;
        }
        System.out.println("Team is already a department");
        return this;
    }

    /**Changes the department which a team is associated with
     * @param newDepartment the new department to which this team will now be associated
     */
    public void changeDepartment(Team newDepartment){
        if(this.isDepartment){
            this.isDepartment = false;
            this.department = newDepartment;
        }
        this.department = newDepartment;
    }

    /**Gets the number of members in a team 
     * @returns the number of members in a team
    */
    public Integer getNoTeamMembers(){
        return this.teamMembers.size();
    }

    /** Get the description of the team 
     * @returns the description of the team
    */
    public String getTeamDesc(){
        return this.description;
    }

    /** Change the description of a team
     * @param newTeamDesc the team's new name
     */
    public void changeTeamDesc(String newTeamDesc){
        this.description = newTeamDesc;
    }

    /**Gets the employee who is lead in this team
     * @returns Employee object representing the team lead
     */
    public Employee getTeamLead(){
        return this.teamLead;
    }
     
    /** Changes the team lead of this team
     * @param newTeamLead to be assigned as team lead to this team 
    */
    public void changeTeamLead(Employee newTeamLead){
        this.teamLead = newTeamLead;
    }

    /**Gets the members of this team 
     * @returns ArrayList of all team members
    */
    public ArrayList<Employee> getTeamMembers(){
        return this.teamMembers;
    }

    /**Assign an employee to this team
     * @param newTeamMember to be assigned to this team
     * @boolean true is operation is successful and false if it is not
     */
    public boolean addTeamMember(Employee newTeamMember){
        try{
            String code = newTeamMember.getEmployeeCode();
            if(!isInTeam(code)){
            this.teamMembers.add(newTeamMember);
            newTeamMember.assignToTeam(this.teamName);
            return true;
            }
            return false;
        }
        catch(Exception e){
            return false;
        }
    }

    /**Searches for a team member in this team by employee code
     * @param reqEmployeeCode the code of the employee being searched for
     * @returns true if employee is in the team and false if otherwise
     */
    public boolean isInTeam(String reqEmployeeCode){
        for(Employee employees: this.teamMembers){
            String code = employees.getEmployeeCode();
            if(code.equals(reqEmployeeCode)){
                return true;
            }
        }
        return false;
    }

    /** Removes a team member from this team (and any subteams if applicable) if the employee is found
     * @param codeToRemove the code of the employee to be removed
     * @return true if the employee is removed and false if the operation is not successful
     */
    public boolean removeFromTeam(String codeToRemove){
        ArrayList<Employee> toBeRemoved = new ArrayList<Employee>();
        if(!isInTeam(codeToRemove)){
            System.out.println("Employee not found");
            return false;
        }
        

        for(Employee employees: this.teamMembers){
            String code = employees.getEmployeeCode();
            if(code.equals(codeToRemove)){
               employees.removeTeam(this.teamName);
                toBeRemoved.add(employees);
            }
        }
        this.teamMembers.removeAll(toBeRemoved);
        for(int i = 0; i < this.subTeams.size(); i ++){
            Team currSubTeam = this.subTeams.get(i);
            currSubTeam.removeFromTeam(codeToRemove);
        }
        return true;
    }

    /**Gets the sub-teams present in a team
     * @returns ArrayList of sub-teams present in list
     */
    public ArrayList<Team> getSubTeams(){
        return this.subTeams;
    }

    /**Adds subteams to this team
     * @param teamToAdd the team object to be added
     * @return true if the team has been successfully added
     * @throws IllegalArgumentException if team is already in subteams list or is the same as this team
     */
    public boolean addSubTeam(Team teamToAdd) throws IllegalArgumentException{
        if (this.getTeamCode().equals(teamToAdd.getTeamCode())){
            // throw new IllegalArgumentException("Desired team is the same as current team");
            return false;
        }
        for(Team subTeams: this.subTeams){
            if(subTeams.getTeamCode().equals(teamToAdd.getTeamCode())){
                // throw new IllegalArgumentException("Team is already in sub teams list");
                return false;
            }
        }
        this.subTeams.add(teamToAdd);
        if (this.isDepartment){
            teamToAdd.department = this;
        }
        return true;
    }


    /**Searches for a sub-team in this team by team code
     * @param reqTeamCode the code of the team being searched for
     * @returns true if sub-team is in the team and false if otherwise
     */
    public boolean isSubTeamInTeam(String reqTeamCode){
        for(Team teams: this.subTeams){
            String code = teams.getTeamCode();
            if(code.equals(reqTeamCode)){
                return true;
            }
        }
        return false;
    }

    /** Removes a sub-team from this team if the sub-team is found
     * @param codeToRemove the code of the sub-Team to be removed
     * @return true if the sub-team is removed and false if the operation is not successful
     */
    public boolean removeSubTeamFromTeam(String codeToRemove){
        if(!isSubTeamInTeam(codeToRemove)){
            System.out.println("Sub-team not found");
            return false;
        }

        for(Team teams: this.subTeams){
            String code = teams.getTeamCode();
            if(code.equals(codeToRemove)){
                this.subTeams.remove(teams);
                return true;
            }
        }
        return true;
    }

    /**Gets all the tasks that have not yet been completed in a team
     * @returns ArrrayList of all tasks that are yet to be completed
     */
    public ArrayList<String> getActiveTasks(){
        ArrayList<String> activeTasks = new ArrayList<String>();
        for(Employee employees: this.teamMembers){
            if (employees.getCurrTask() != null){
                activeTasks.add(employees.getCurrTask());
            }
        }
        System.out.println("There are " + activeTasks.size() + " active tasks in "+
        this.teamName);
        return activeTasks;
    }









      /**Changes team object to a department instead of a normal team */
    public void makeDepartment(){
        this.isDepartment = true;
    }



    /**Returns a string representation for the team object
     * @returns String representation of team object
     */
    public String toString(){
        if (isDepartment){
            return "Department Name - " + this.teamName + "\nTeam Code - " + this.teamCode +
            "\nDescription - " + this.description
             + "\nNumber of team members - " + this.teamMembers.size() 
            + " members." + "\nNumber of sub-teams - " + this.subTeams.size() + " sub-teams";

        }
        return "Team Name - " + this.teamName + "\nTeam Code - " + this.teamCode +
        "\nDescription - " + this.description 
        + "\nDepartment - " + this.department.teamName 
         + "\nNumber of team members - " + this.teamMembers.size() 
        + " members." + "\nNumber of sub-teams - " + this.subTeams.size() + " sub-teams";
    }

    /**Prints full details associated with the team */
    public void printFullDetails(){
        System.out.println("\n\nTeam Name - " + this.teamName + "\nDescription - " + this.description
        + "\nNumber of team members - " + this.teamMembers.size()
        + " members." + "\nNumber of sub-teams - " + this.subTeams.size() + " sub-teams");
        System.out.print("Team members - " );
        for (Employee employees: this.teamMembers){
            System.out.print(employees.getEmployeeName());
            System.out.print(", ");
        }
        System.out.print("\nSub teams - " );
        for (Team subTeams: this.subTeams){
            System.out.print(subTeams.getTeamName());
            System.out.print(", ");
        }
        System.out.println();
        

    }

    public static void main(String[] args){
        Employee Jack = new Employee("Jack");
        Employee Lynn = new Employee("Lynn");
        Employee Bob = new Employee("Bob");
        Employee Pascal= new Employee("Pascal");
        Employee Fred = new Employee("Fred");
        Team SWE = new Team("Software Engineering");
        Team FE = new Team("FrontEnd Design");
        Team BE = new Team("Backend Design");
        Team UI = new Team("User Interaction");
        Team API = new Team("Application Programming Interface");
        Team DB = new Team("Database Management");
        SWE.makeDepartment();
        SWE.addSubTeam(FE);
        SWE.addSubTeam(BE);

        SWE.addTeamMember(Jack);
        SWE.addTeamMember(Lynn);
        SWE.addTeamMember(Bob);
        SWE.addTeamMember(Pascal);
        SWE.addTeamMember(Fred);

        FE.addSubTeam(UI);
        FE.addTeamMember(Jack);
        FE.addTeamMember(Bob);
        
        
        SWE.printFullDetails();
        Bob.printFullDetails();

        SWE.removeFromTeam("2022003");
        SWE.printFullDetails();

       
        FE.printFullDetails();
        Bob.printFullDetails();

        


        



    }



}