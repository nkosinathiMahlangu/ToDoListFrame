/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package za.ac.tut.todolist;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import za.ac.tut.list.List;

/**
 *
 * @author Student
 */



public class ToDoList extends JFrame{
    
     //Panels
     private JPanel modulePnl;
    private JPanel descPnl;
    private JPanel timePnl;
    private JPanel comboPnl;//grid panel
    private JPanel areaPnl;
    private JPanel areaPnlGrid;
    private JPanel buttonsPnl;
    private JPanel mainPnl;
    //Labels
    private JLabel moduleLb;
    private JLabel descLb;
    private JLabel timeLb;
    //text fields
    private JTextField moduleTx;
    private JTextField descTx;
    //combo box
    private JComboBox box;
    //text area
    private JTextArea comment;
    //Scrooll pane
    private JScrollPane pane;
    //Buttons
    private JButton add;
    private JButton save;
    private JButton exit;
    //Menu bar
    private JMenuBar mainBar;
    //Menu 
    private JMenu file;
    private JMenu edit;
    //Menu Items
    private JMenuItem recTask;
    private JMenuItem proTask;
    private JMenuItem clear;
    
    private JMenuItem getTask;
    private JMenuItem modTask;
    //ArrayList
    private ArrayList<List> myList;
    
    public ToDoList() {
        //Setting Frame
        setTitle("TO LIST");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //Insta list
        myList = new ArrayList<List>();
        // Inst BarMenus
        mainBar = new JMenuBar();
        //Insta Menus
        file = new JMenu("File");
        edit = new JMenu("Edit");
        //Insta the menu items for file
        recTask = new JMenuItem("Recently Added Tasks");
        recTask.addActionListener(new RecentTaskButton());
        proTask = new JMenuItem("Progress on tasks");
        clear = new JMenuItem("Clear");
        clear.addActionListener(new ClearingButton());
        //adding list to file
        file.add(recTask);
        file.add(proTask);
        file.addSeparator();
        file.add(clear);
        //items for edit menu
        getTask = new JMenuItem("Get All Tasks");
        getTask.setEnabled(false);
        getTask.addActionListener(new GetAllTask());
        modTask = new JMenuItem("Modify Tasks");
        modTask.setEnabled(false);
        modTask.addActionListener(new ModicationButton());
        //adding to list
        edit.add(getTask);
        edit.addSeparator();
        edit.add(modTask);
        //adding menu bar
        mainBar.add(file);
        mainBar.add(edit);
        //Inst panels 
         modulePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
         descPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        timePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
       comboPnl = new JPanel(new GridLayout(4,1,1,1));//grid panel
       areaPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
       areaPnlGrid = new JPanel(new GridLayout(1,1,1,1));
       areaPnlGrid.setBorder(new TitledBorder(new LineBorder(Color.BLUE,1),"Tasks",TitledBorder.TOP,TitledBorder.CENTER));
       buttonsPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
       mainPnl = new JPanel(new BorderLayout());
       //Inst labels
       moduleLb = new JLabel("Module: ");
       descLb = new JLabel("Enter description of task: ");
       timeLb = new JLabel("Time needed to finish task: ");
       //text fields
       moduleTx = new JTextField(15);
       descTx = new JTextField(15);
       //Combo
       box = new JComboBox();
       box.addItem("--Select Option");
       box.addItem("30 mintes");
       box.addItem("1 Hour");
       box.addItem("1 hour and 30 minutes");
       box.addItem("2 hours");
       box.addItem("2 hours and 30 minutes");
       box.addItem("3 hours");
       //Text area
       comment = new JTextArea(20,40);
       comment.setEditable(false);
        //adding pane on text area
        pane = new JScrollPane(comment,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //butons
        add = new JButton("Add Task");
        add.addActionListener(new addTaskButton());
        save = new JButton("Save");
        save.addActionListener(new saveButton());
        exit = new JButton("Exit");
        exit.addActionListener(new exitButton());
        //Adding comb to res panels
        modulePnl.add(moduleLb);
        modulePnl.add(moduleTx);
        descPnl.add(descLb);
        descPnl.add(descTx);
        timePnl.add(timeLb);
        timePnl.add(box);
        comboPnl.add(modulePnl);
        comboPnl.add(descPnl);
        comboPnl.add(timePnl);
        areaPnl.add(pane);
        areaPnlGrid.add(areaPnl);
        buttonsPnl.add(add);
         buttonsPnl.add(save);
          buttonsPnl.add(exit);
          //adding to main panl
          mainPnl.add(comboPnl,BorderLayout.NORTH);
          mainPnl.add(areaPnlGrid,BorderLayout.CENTER);
          mainPnl.add(buttonsPnl,BorderLayout.SOUTH);
         //ADDING TO FRAME
         add(mainPnl);
        
        //adding bar to frame
        setJMenuBar(mainBar);
        pack();
        setVisible(true);
        
        
    }

    private class ModicationButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
          //make areaText Ediable
          comment.setEditable(true);
        }

       
    }

    private class GetAllTask implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
          //Varibles
          BufferedReader br;
          File file;
          JFileChooser jc;
          int val;
          String data,record="";
          //Insta JChooser
          jc = new JFileChooser();
          //Open the saved files
          val = jc.showSaveDialog(ToDoList.this);
          if(val == JFileChooser.APPROVE_OPTION){
              //getSelectd file
              file =jc.getSelectedFile();
              try {
                  //Reading the file
                  br = new BufferedReader(new FileReader(file));
                  //
                  while((data = br.readLine())!= null){
                      record = record + data +"\n";
                  }
                  br.close();
                  //files desils on area
                  comment.setText(record);
                  //Enable mod task menu
                  modTask.setEnabled(true);
              } catch (FileNotFoundException ex) {
                  Logger.getLogger(ToDoList.class.getName()).log(Level.SEVERE, null, ex);
              }catch(IOException e){
                  Logger.getLogger(ToDoList.class.getName()).log(Level.SEVERE, null, e);
              }
          }
        }
    }

    private class ClearingButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //clearing fields
            moduleTx.setText("");
            descTx.setText("");
            box.setSelectedIndex(0);
            comment.setText("");
            
        }

     
    }

    private class RecentTaskButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
         //Varibles
         String output="";
         for(int x=0;x<myList.size();x++){
             output = myList.get(x).getModuleName()+","+myList.get(x).getDesc()+
                    " time spend is "+myList.get(x).getTime();
         }
         //Setting text area
         comment.setText(output);
        }

        
    }

    private class saveButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
         //Variables
         File file;
         JFileChooser jc;
         BufferedWriter bw;
         List list;
         int val;
         //Getting deails frm feilds
         String ar = comment.getText();
        
         //Insta JfileC
         jc = new JFileChooser();
         //save dialog
         val = jc.showSaveDialog(ToDoList.this);
         if(val == JFileChooser.APPROVE_OPTION){
             //Get the selected file
             file = jc.getSelectedFile();
             try {
                 //Write on the file
                 bw = new BufferedWriter(new FileWriter(file,true));
                 bw.write(ar);
                 //Every there's new details write on new line
                 bw.newLine();
                 //after wrting close
                 bw.close();
                 //Setting the get all task menu to be enable
                 getTask.setEnabled(true);
                 //clearing feild 
                 comment.setText("");
                 
             } catch (IOException ex) {
                 Logger.getLogger(ToDoList.class.getName()).log(Level.SEVERE, null, ex);
             }
         
        }else{
             JOptionPane.showMessageDialog(null,"User has click cancel button");
         }
        }

      
    }

    private class addTaskButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Variables
            List obj;
            //Getting details on the fields
            String name = moduleTx.getText();
            String des = descTx.getText();
            String tim = (String) box.getSelectedItem();
            //inst class obj
            obj = new List(name,des,tim);
            //adding to list
            myList.add(obj);
            //Display message show that data is rec
            JOptionPane.showMessageDialog(null,"Details received");
            //Clearing the files
            moduleTx.setText("");
            descTx.setText("");
            box.setSelectedIndex(0);
        }

        
    }

    private class exitButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

        
    }
    
}
    



