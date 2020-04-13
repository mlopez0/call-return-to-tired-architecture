/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mergedapp;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.*;

/**
 *
 * @author Batyr Seventy
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setLabelFor(jTextField1);
        jLabel1.setText("Login");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setLabelFor(jTextField2);
        jLabel2.setText("Password");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Authenticate to access the app");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(92, 92, 92))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(54, 54, 54))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String login = jTextField1.getText();
        String password = jTextField2.getText();
        String databaseName = null;
        String msgString = "";
        String whole_err = "";
        Boolean fieldError = false;
        // CONNECTION PARAMETERS

        Boolean connectError = false;   // Error flag
        Connection DBConn = null;       // MySQL connection handle
        String description;             // Inventory item description
        Boolean executeError = false;   // Error flag
        String errString = null;        // String for displaying errors
        int executeUpdateVal;           // Return value from execute indicating effected rows
        ResultSet res = null;           // SQL query result set pointer
        String tableSelected = null;    // String used to determine which data table to use
        Integer quantity;               // Quantity of inventory item
        Float perUnitCost;              // Cost per unit item
        String productID = null;        // Product id of item
        java.sql.Statement s = null;    // SQL statement pointer
        String SQLstatement = null;     // String for building SQL queries
        
        
        databaseName = "authentication";
        

        //Make sure there is a price
        if ( jTextField1.getText().length() == 0 )
        {
            fieldError = true;
            msgString = "Must enter a user name. ";
            whole_err = whole_err + msgString;
        } //else {
        if ( jTextField2.getText().length() == 0 )
        {
            fieldError = true;
            msgString = "Must enter a Password. ";
            whole_err = whole_err + msgString;
        }
 //   }     
        
        if (fieldError){
            javax.swing.JOptionPane.showMessageDialog(null, whole_err);
        }
        
     
        
        if ( !fieldError )
        {
            try
            {
                //load JDBC driver class for MySQL
                Class.forName( "com.mysql.jdbc.Driver" );
                //define the data source
                String SQLServerIP = "localhost"; // MUST BE CHANGED TO A VARIABLE FOR WEB ENVIROMENT
                String sourceURL = "jdbc:mysql://" + SQLServerIP + ":3306/" + databaseName;
                //create a connection to the db
                DBConn = DriverManager.getConnection(sourceURL,"remote","remote_pass");
            javax.swing.JOptionPane.showMessageDialog(null, "connected!");

            } catch (Exception e) {

                errString =  "\nProblem connecting to database:: " + e;
                connectError = true;
                javax.swing.JOptionPane.showMessageDialog(null, errString);

            } // end try-catch
        } // fieldError check        
        
        

        if (!connectError && !fieldError )
        {
            try
            {
                s = DBConn.createStatement();
                String myquery =  "select UserName, Pass from Users where UserName ='"+ login+"' and Pass = '"+password+"' ;" ;
                System.out.println(myquery);
                res = s.executeQuery(myquery);
                String passN = " ";//res.getString("UserName");
                String userN = " "; //res.getString("Pass");

              while (res.next()) {
                        userN = res.getString("UserName");
                        passN = res.getString("Pass");

                        System.out.println(passN + "\t" + userN);
                    }
                System.out.println(login);
                System.out.println(password);
                
                System.out.println(userN);
                System.out.println(passN);

                if (login.equals(userN) && password.equals(passN)) {
                    this.setVisible(false);
                    new MenuJFrame().setVisible(true);
                }
                else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Login or password is incorrect");
                }

                
            } catch (Exception e) {

                errString =  "\nProblem with " + tableSelected +" query:: " + e;
                executeError = true;
                
                javax.swing.JOptionPane.showMessageDialog(null,errString);

            } // try

        } //execute SQL check        
        
        
        
        
        
        
        
    }                                        

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration                   
}
