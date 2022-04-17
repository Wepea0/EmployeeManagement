import java.util.*;
/** <h1>Team Member class</h1>
 * Class describing an object that contains information about an employee in a company
 * 
 * @author Wepea Buntugu
 * @version 1.0
 * @since 2022-04-17
 */

class Employee{
    /**Employee Name */
    private String name;

    /**Employee code */
    private String employeeCode;

    /**Prefix for all employee codes */
    static Integer codeGenerator = 2022;

    /**Initial value for the first employee code, will be incremented with each new employee */
    static Integer codeGeneratorHelp = 1;

    /**Length of time employee has spent in company (in months) */
    private Integer tenure;

    /** Number of tasks an employee has fully completed */
    private Integer noTasksCompleted;

    /**The current task that has been assigned to an employee */
    private String currTask; //Will change to task object

    /**Employee educational qualificationss */
    private ArrayList<String> qualifications;

    /**Employee position in the company
     */
    private String position;

    /** Array of teams the employee has been a part of */
    private ArrayList<String> allTeams; //Will become Array List of team objects

    /**Array of teams the employee is currently a part of */
    private ArrayList<String> currTeams; //Will become Array List of team objects



    /**Constructor for team member object
     * @param Name name of the employee
     */
    public Employee(String name){
        this.name = name;
        String finalCode = String.format("%03d", codeGeneratorHelp++);
        this.employeeCode = codeGenerator + "" + finalCode;
        this.tenure = null;
        this.noTasksCompleted = 0;
        this.currTask = null;
        this.qualifications = new ArrayList<String>();
        this.allTeams = new ArrayList<String>();
        this.currTeams = new ArrayList<String>();
        this.position = null;
    }

    /**Constructor for team member object
     * @param Name name of the employee
     * @param employeeCode
     * @param tenure
     * @param noTasksCompleted
     * @param currTask
     * @param qualifications 
     */
    public Employee(String name, String employeeCode, Integer tenure, Integer noTasksCompleted, 
    String currTask, ArrayList<String> qualifications, String position){
        this.name = name;
        this.employeeCode = employeeCode;
        this.tenure = tenure;
        this.noTasksCompleted = noTasksCompleted;
        this.currTask = currTask;
        this.qualifications =  qualifications;
        this.position = position;
        this.allTeams = new ArrayList<String>();
        this.currTeams = new ArrayList<String>();
    }
    
    /**Returns the name of the employee
     * @returns Employee name
      */
    public String getEmployeeName(){
        return this.name;
    }

    /** Method for changing the name of an employee
     * @param newName The employee's new name.
     */
    public void changeEmployeeName(String newName){
        this.name = newName;
    }

    /**Returns the code of the employee
     * @returns Employee code
      */
    public String getEmployeeCode(){
        return this.employeeCode;
    }

    /**Returns tenure of the employee
     * @returns Tenure of the employee
     */
    public Integer getTenure(){
        return this.tenure;
    }

    /**Returns the number of tasks the employee has completed
     * @returns number of tasks completed by a user
     */
    public Integer getNoTasksCompleted(){
        return this.noTasksCompleted;
    }

    /**Changes the number of tasks completed by an employee
     */
    public void incNoTasksCompleted(){
        this.noTasksCompleted++;
    }

    /**Get the current task assigned to the team member
     * @return Task assigned to team member
     */
    public String getCurrTask(){
        if (this.currTask != null){
            return this.currTask;
        }
        else{
            System.out.println("No task currently assigned");
            return null;
        }
    }

    /** Assign a new task to a team member
     * @param newTask the task to be assigned to the team member
     */
    public boolean assignTask(String newTask){ //Will change to a task object
        if(this.currTask == null){
            this.currTask = newTask;
            return true;
        }
        else{
            System.out.println("Task assignment failed");
            return false;
        }
    }

    /** Returns the Qualifications a team member has
     * @returns Qualifications a team member has
     */
    public ArrayList<String> getQualifications(){
        return this.qualifications;
    }

    /** Add new qualifications to a team member 
     * @param newQualis The new qualifications to be added
     */
    public void addQualifications(String newQualis){
        qualifications.add(newQualis);
    }

    /**Removes the indicated qualification
     * @param Position The position of the qualification to be removed
     */
    public boolean removeQualification(int Position){
        try{
            if(this.qualifications != null){
                if(this.qualifications.size() >= Position){
                    int actualPosition = Position -1;
                    this.qualifications.remove(actualPosition);

                    return true;
                }

            }
            System.out.println("Remove qualification operation failed");
            return false;
        }
        catch(Exception e){
            System.out.println("Remove qualification operation failed");

        }
        return false;
    }

    /** Gets the position an employee holds 
     * @returns Position employee occupies in the company
    */
    public String getPosition(){
        return this.position;
    }

    /** Changes the position of the employee in the company
     * @param newPosition The employee's new position
     */
    public void changePosition(String newPosition){
        this.position = newPosition;
    }

    /**Gets the names of all the teams this employee is currently a member of 
     * @returns List of all teams this employee is currently a member of
    */
    public ArrayList<String> getCurrTeams(){
        return this.currTeams;
    }

    /**Gets the names of all the teams this employee has been a member of 
     * @returns List of all teams this employee has been a member of
    */
    public ArrayList<String> getAllTeams(){
        return this.allTeams;
    }

    /**Assigns an employee to a team
     * @param team that that employee will be added to
     */
    public void assignToTeam(String team){ //will eventually be a team object
        this.currTeams.add(team);
        this.allTeams.add(team);
    }


    /**Removes an employee from a team
     * @returns true if the removal operation is successful and false if it is not
     */
    public boolean removeFromTeam(String team){
        for(String teams: currTeams){
            if (team.equals(teams)){
                currTeams.remove(teams);
                return true;
            }
        }
        System.out.println("Team removal operation failed");
        return false;
    }

    /**Gets number of teams an employee is currently in
     * @returns number of teams an employee is currently in
     */
    public Integer getNoCurrTeams(){
        return this.currTeams.size();
    }



    /**Returns string representation of the team member object
    */
    public String toString(){
        return "Employee - " + this.name + " || Employee Code - " + this.employeeCode 
        + " || Current task - " + this.currTask;
    }

    /**Prints out all information related to the team member object */
    public void printFullDetails(){
        System.out.println("Employee - " + this.name + " || Employee Code - " + this.employeeCode 
        + " || Current task - " + this.currTask + " || Number of tasks completed - " + this.noTasksCompleted
        + " || Qualifications - " + this.qualifications 
        + " || Current teams - " + this.currTeams
        + " || Tenure - " + this.tenure );
    }

    public static void main(String[] args){
        Employee Wepea = new Employee("Wepea");
        Wepea.addQualifications("MBA");
        Wepea.addQualifications("Debate Champion");
        Wepea.assignTask("Design frontend");
        System.out.println(Wepea);
        Employee Sara = new Employee("Sara");
        Sara.addQualifications("Bsc Computer Science");
        Sara.addQualifications("Talker");
        
        Sara.assignToTeam("Human Resource");
        Sara.assignToTeam("Social Media");
        Sara.assignToTeam("Febreze reveal party");

        Sara.removeFromTeam("Social Media");

        Sara.printFullDetails();
        System.out.println(Sara.getNoCurrTeams());
        
    }
}