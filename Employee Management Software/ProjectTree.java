import java.util.*;
import java.io.*;

public class ProjectTree{
  private MyNode root;
  private int treeSize = 1;
  
  // construct tree from root
  public ProjectTree(String head){
    root = new MyNode(head, null);
    root.setIsRoot();
  }
  
  /** creates nodes for the tree
    * Each node has a list of all other nodes subordinate to it
    * Applicable methods for acting upon the nodes
    * */
  public static class MyNode{
    private Boolean isRoot = false;
    private String value;
    private int numChildren = 0;
    private MyNode parent;
    ArrayList<MyNode> children = new ArrayList<MyNode>();
    // construct node
    public MyNode(String name, MyNode parent){
      this.value = name;
      this.parent = parent;
    }
    
    // add child to node subordinate list given constructor
    void addChild(MyNode child){
      this.children.add(child);
      this.numChildren++;
    }
    
    // add child given a string
    void addChild(String childName, MyNode parent){
      MyNode child = new MyNode(childName, parent);
      this.children.add(children.size(), child);
      this.numChildren++;
    }
    
    // remove child from children list
    void removeChild(int index){
      this.children.remove(children.size()-1);
      this.numChildren--;
    }
    
    // set value of node
    void setValue(String value){
      this.value = value;
    }
    
    // make node a root
    void setIsRoot(){
      this.isRoot = true;
    }
    
    Boolean isRoot(MyNode node){
      return node.isRoot;
    }
    
    // return parent of this node
    MyNode getParent(){
      return this.parent;
    }
    
    // get number of children
    int getNumChildren(){
      return this.numChildren;
    }
    
    // return list of children
    ArrayList<MyNode> getChildren(){
      return this.children;
    }
    
    // modify value of a child 
    void updateChild(int index, String newValue){
      this.children.get(numChildren).setValue(newValue); 
    }
    
    // return value of node
    String getValue(){
      return this.value;
    }
  }
  
  // return root
  MyNode getRoot(){
    return this.root;
  }
  
  // get parent of a given node in the tree
  MyNode parent(MyNode child){
    return child.getParent();
  }
  
  // get list of children of a given node
  ArrayList<MyNode> children(MyNode parent){
    return parent.getChildren();
  }
  
  // get number of children of a given node
  int numChildren(MyNode p){
    return p.getNumChildren();
  }
  
  // check if node has any child
  Boolean isInternal(MyNode p){
    Boolean state = (p.getNumChildren() == 0) ? false:true;
    return state;
  }
  
  // check if node has no child
  Boolean isExternal(MyNode p){
    Boolean state = (p.getNumChildren() == 0) ? true:false;
    return state;
  }
  
  // check if node is root of the tree
  Boolean isRoot(MyNode p){
    if (p.getParent() == null)
      return true;
    else
      return false;
  }
  
  // return number of nodes in tree
  int size(MyNode root){
    for (MyNode i: root.getChildren()){
      if (i.getNumChildren() != 0){
        this.treeSize++;
        this.size(i);
      }
      else
        this.treeSize++;
    }
    return treeSize;
  }
  
  // check if tree is empty
  Boolean isEmpty(){
    Boolean state = (this.root.getNumChildren() == 0) ? true: false;
    return state;
  }
  
  //create tree from multiple strings starting after the root
  public static ProjectTree createTree(Team team){
    if(team.getTeamLead() != null){
      ProjectTree company = new ProjectTree(team.getTeamName() + " | CEO - " + team.getTeamLead().getEmployeeName());
      return company;
    }
    else{
      ProjectTree company = new ProjectTree(team.getTeamName());
      return company;
    }
  }
  
  // create tree nodes from company root
  public static void createTreeNodes(Team team, MyNode parent){
    if (team.getSubTeams().size() != 0){
      for (Team subTeam: team.getSubTeams()){
        if( subTeam.getTeamLead()!= null && subTeam.findIsDepartment()){
          MyNode currentNode = new MyNode(subTeam.getTeamName() + " | Description - " + subTeam.getTeamDesc() + " | Team Lead - " + subTeam.getTeamLead().getEmployeeName(), parent);
          parent.addChild(currentNode);
          createTreeNodes(subTeam, currentNode);
        }
        else if (subTeam.getTeamLead() == null && subTeam.findIsDepartment()){
          MyNode currentNode = new MyNode(subTeam.getTeamName() + " | Description - " + subTeam.getTeamDesc() + " | Team Lead - N/A", parent);
          parent.addChild(currentNode);
          createTreeNodes(subTeam, currentNode);
        }
        else if(subTeam.getTeamLead()!= null && !subTeam.findIsDepartment()){
          MyNode currentNode = new MyNode(subTeam.getTeamName() + " | Team Lead - " + subTeam.getTeamLead().getEmployeeName(), parent);
          parent.addChild(currentNode);
          createTreeNodes(subTeam, currentNode);
        }
        else if(subTeam.getTeamLead() == null && !subTeam.findIsDepartment()){
          MyNode currentNode = new MyNode(subTeam.getTeamName() + " | Team Lead - N/A", parent);
          parent.addChild(currentNode);
          createTreeNodes(subTeam, currentNode);
        }
        
        }
      }
    else {
      for (Employee teamMember: team.getTeamMembers()){
        if(teamMember.getCurrTask() != null){
          MyNode currentNode = new MyNode(teamMember.getEmployeeName() + " | Active Task - " + teamMember.getCurrTask(), parent);
          parent.addChild(currentNode);
        }
        else {
          MyNode currentNode = new MyNode(teamMember.getEmployeeName() + " | Active Task - N/A" , parent);
        parent.addChild(currentNode);
        }
        
      }
    }
  }

  public static ProjectTree createCompanyTree(Team team) throws IOException{
    ProjectTree company = createTree(team);
    createTreeNodes(team, company.getRoot());
    WriteTree writer = new WriteTree();
    writer.writeTree(company.getRoot());
    return company;
  }
  
  public static void main(String[] args) throws IOException{
    // ProjectTree classA = new ProjectTree("Alloy");
    // MyNode root = classA.getRoot();
    // root.addChild("Wepea", root);
    // root.addChild("Akonteh", root);
    // root.addChild("Alosius", root);
    // root.getChildren().get(0).addChild("crazy", root.getChildren().get(0));
    // root.getChildren().get(1).addChild("crazy1", root.getChildren().get(1));
    // root.getChildren().get(1).addChild("crazy2", root.getChildren().get(1));
    // root.getChildren().get(0).addChild("crazy3", root.getChildren().get(0));
    // root.getChildren().get(0).getChildren().get(0).addChild("crazy4", root.getChildren().get(0));
    // root.getChildren().get(0).getChildren().get(1).addChild("crazy3", root.getChildren().get(0));
    
    // for (MyNode i: root.getChildren()){
    //   System.out.println(i.value);
    //   if (i.getNumChildren() != 0){
    //     for (MyNode j: i.getChildren()){
    //       System.out.println(j.value);
    //     }
    //   }
    // }
    // System.out.print(classA.size(root));
    // WriteTree writer = new WriteTree();
    // writer.writeTree(root);

    Team team1 = new Team("Facebook");
    team1.makeCompany();
    Team team2 = new Team("Software Engineering");
    team2.makeDepartment();
    Team team3 = new Team("Social Media");
    team3.makeDepartment();
    Team team4 = new Team("Human Resource");
    team4.makeDepartment();
    team1.addSubTeam(team2);
    team1.addSubTeam(team3);
    team1.addSubTeam(team4);
    Team team5 = new Team("Feature Shipping");
    team2.addSubTeam(team5);
    Team team6 = new Team("Feature Development");
    team2.addSubTeam(team6);
    Team team7 = new Team("Brand Awareness");
    team3.addSubTeam(team7);

    Employee emp1 = new Employee("Bob");
    Employee emp2 = new Employee("Dylan");
    Employee emp3 = new Employee("Ryan");
    Employee emp4 = new Employee("Jim");
    Employee emp5 = new Employee("Pam");
    Employee emp6 = new Employee("Kevin");
    Employee emp7 = new Employee("Michael");

    emp1.assignToTeam("Software Engineering");
    emp2.assignToTeam("Social Media");
    emp3.assignToTeam("Human Resource");
    emp4.assignToTeam("Feature Shipping");
    emp5.assignToTeam("Feature Shipping");
    emp6.assignToTeam("Feature Development");
    emp7.assignToTeam("Brand Awareness");

    createCompanyTree(team1);
  }


}