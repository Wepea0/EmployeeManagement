
import java.io.*;
import java.util.*;

/** <h1>Team Member class</h1>
 * Class describing an object that contains information about an employee in a company
 * 
 * @author Wepea Buntugu
 * @version 1.0
 * @since 2022-04-17
 */

//Change team methods to team objects not strings
//Complete task method - when a task has been done
//add team head variable somehow
//All tasks 
//CurrTeam arraylist variable and AssignToTeam function
 
public class Employee implements Serializable{
    
    private static final long serialVersionUID = 3462152188121693529L;

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

    /**Array of tasks the employee has been assigned to */
    private ArrayList<String> allTasks; //Will become Array List of task objects

    /**Static list to store all created team member objects*/
    private static ArrayList<Employee> allEmployees = new ArrayList<Employee>();



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
        this.allTasks = new ArrayList<String>();
        this.position = null;
        allEmployees.add(this);
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
        this.allTasks = new ArrayList<String>();
        allEmployees.add(this);

    }

    /**Returns list of all employees
     * @returns ArrayList of all employees
      */
      public static ArrayList<Employee> getAllEmployees(){
        return allEmployees;
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

    /** Method for changing the tenure of an employee
     * @param newTenure The employee's new tenure.
     */
    public void changeTenure(Integer newTenure){
        this.tenure = newTenure;
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
            this.currTask = newTask;
            this.allTasks.add(currTask);
            Set<String> fixTaskList = new LinkedHashSet<String>(this.allTasks);
            ArrayList<String> holdTasks = new ArrayList<String>(fixTaskList);
            this.allTasks = holdTasks;
            return true;
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


    /**Removes an employee from a team
     * @param team which the employee will be removed from
     * @returns true if the removal operation is successful and false if it is not
     */
    public boolean removeTeam(String team){
        for(String teams: currTeams){
            if (team.equals(teams)){
                currTeams.remove(teams);
                return true;
            }
        }
        return false;
    }

    /**Gets number of teams an employee is currently in
     * @returns number of teams an employee is currently in
     */
    public Integer getNoCurrTeams(){
        return this.currTeams.size();
    }

    /**Assigns a team member to the specified team
     * @param newTeam the new team this team member will be assigned to 
     * @returns true if the assignment is successful and false otherwise
     */
    public boolean assignToTeam(String newTeam) {
        for (Team teams: Team.allTeams){
            if(teams.getTeamName().equals(newTeam)){
                if(teams.addTeamMember(this)){
                    this.currTeams.add(teams.getTeamName());
                    this.allTeams.add(teams.getTeamName());
                    return true;
                }
            }
        }
        return false;
    }
    
    /**Removes employee from the list of all employees
     * @param employeeCode
     * @returns true if the operation was successful and false otherwise
     */
    public static boolean fireEmployee(String employeeCode){
        Employee toRemove = null;
        ArrayList<Team> exTeams = new ArrayList<Team>();
        for(Employee employees: allEmployees){
            if(employees.getEmployeeCode().equals(employeeCode)){
                toRemove = employees;
            }
        }
        if(toRemove != null){
            for(Team teams: Team.getAllTeams()){
                if(teams.isInTeam(employeeCode)){
                    exTeams.add(teams);
                }
            }
            if(exTeams.size() != 0){
                for(Team teams: exTeams){
                    teams.removeFromTeam(employeeCode);
                }

            }
            allEmployees.remove(toRemove);
            return true;
        }
        else{
            return false;
        }
        
    }

    /**
     * 
     * @param employeeCode
     * @return
     */
    public static Employee findEmployee(String employeeCode){
        for(Employee employees: allEmployees){
            if (employees.getEmployeeCode().equals(employeeCode)){
                return employees;
            }
        }
        System.out.println("Employee not found");
        return null;
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
        + " || Tenure - " + this.tenure 
        + " || All tasks - " + this.allTasks);
    }

    public static void main(String[] args) throws IOException{
        Employee Wepea = new Employee("Deacon");
       
        Employee Sara = new Employee("MayDay");

        Employee bob = new Employee("Bob");
       

        
        FileOutputStream fos = new FileOutputStream("EmployeeInfo.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Wepea);
        oos.close();
        System.out.println("Object info saved");

        System.out.println("Do you want to load your data");
        Scanner one = new Scanner(System.in);
        Integer newInt = one.nextInt();
        if (newInt.equals(1)){
            try{
                Employee newEmp = null;
                FileInputStream fis = new FileInputStream("EmployeeInfo.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                newEmp = (Employee) ois.readObject();
                ois.close();
                fis.close();
                System.out.println(newEmp.getEmployeeName() + " - Name");
                allEmployees.add(newEmp);
               long getSerialVersionUID = ObjectStreamClass.lookup(newEmp.getClass()).getSerialVersionUID();
                System.out.println("All employees " + allEmployees + "\n UID = " + getSerialVersionUID);
            }
            catch(Exception e){
                System.out.println("Failed");
            }
            
        }







        // Wepea.addQualifications("MBA");
        // Wepea.addQualifications("Debate Champion");
        // Wepea.assignTask("Design frontend");
        // System.out.println(Wepea);
        // Sara.addQualifications("Bsc Computer Science");
        // Sara.addQualifications("Talker");
        
        // Sara.assignToTeam("Human Resource");
        // Sara.assignToTeam("Social Media");
        // Sara.assignToTeam("Febreze reveal party");

        // Sara.removeTeam("Social Media");

        // Sara.printFullDetails();
        // System.out.println(allEmployees);
        
    }
}