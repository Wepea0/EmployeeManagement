import java.awt.Desktop;
import java.io.*;

public class WriteTree{
  BufferedWriter bw;
  File f;
  public WriteTree() throws IOException{
    f = new File("tree.html");
    bw = new BufferedWriter(new FileWriter(f));
  }
  
  // write generic content
  void writer(ProjectTree.MyNode node) throws IOException{
    if (node.isRoot(node))
      bw.write("<div class='root'>" + node.getValue());
    else
      bw.write("<div>" + node.getValue()); // add condition to check if node is root
    
    for (ProjectTree.MyNode i: node.getChildren()){
      if (i.getNumChildren() != 0){
        this.writer(i);
      }
      else
        bw.write("<p>" + i.getValue() + "</p>");
    }
    bw.write("</div>");
  }
  
  void writeTree(ProjectTree.MyNode root) throws IOException{
    bw.write("<head> Company Tree </head> <link rel='stylesheet' href='tree.css'/>");
    writer(root);
    bw.close();
    Desktop.getDesktop().browse(f.toURI());
  }
}