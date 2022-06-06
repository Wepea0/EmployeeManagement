import java.util.*;
import java.io.*;
/**
 * Tasks class
 * Author: Alosius and Wepea
 * Creates a task with following specs
 * A name, a description, a completion state, 
 * an assigned property to know who is handling it, a defer property to pass it on to someone else
 * a help function to request help from someone.
 * */

class Task {
  private String name;
  private String description;
  private Boolean completionState;
  private ArrayList<Employee> assigned;
  private int numberOfAssigned = 0;
  private static ArrayList<Task> allTasks;

  // construct Task class
  public Task(String name, String description, Employee assigned){
    // use agree function to see if team member is available for task
      this.name = name;
      this.description = description;
      this.assigned = new ArrayList<Employee>();
      this.assigned.add(assigned);
      this.numberOfAssigned += 1;
      this.completionState = false;
  }  
    
  public ArrayList<Employee> getAssignedEmployees(){
    return this.assigned;
  }
  
  public void assignEmployee(Employee toBeAssigned){
    assigned.add(toBeAssigned);
  }
  
  public String getTaskName(){
    return this.name;
  }
  
  public String getDescription(){
    return this.description;
  }
  
  public int getNumAssigned(){
    return assigned.size();
  }
  
  // marks task as completed
  public void completed(){
    this.completionState = true;
  }
  
  // mark task as uncompleted
  public void uncompleted(){
    this.completionState = false;
  }
  
  // says whether task is completed or not
  public Boolean isComplete(){
    return this.completionState;
  }
}