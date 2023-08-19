import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class DoctorAppointment extends JFrame implements ActionListener {
    JLabel ltitle, lbooking_id,lfirstname,llastname,lmobile,lgender,ldate,lreference_doc,lupdate,ltuple,lapp;
    JTextField tbooking_id,tfirstname,tlastname,tmobile,tgender,tdate,treference_doc,tupdate,tapp;
    TextArea tarea,ttuple;
    JButton msubmit,mdisplay,mdelete,mshow,mapp;

    public DoctorAppointment(){
//        APPLICATION FORM
        super("UI_to_Database");
        setSize(800,600);
        setLayout(null);
        setVisible(true);
        Color color = new Color(51,153,255);
        getContentPane().setBackground(color);

        Font fonttitle = new Font("blue",Font.BOLD,25);
        Font fontlabel = new Font("blue",Font.BOLD,18);
        Font fonttext = new Font("blue",Font.BOLD,15);


        ltitle = new JLabel("Booking Doctor's Appointment");
        ltitle.setBounds(250,30,400,50);
        ltitle.setFont(fonttitle);
        ltitle.setForeground(Color.CYAN);
        add(ltitle);

        lbooking_id = new JLabel("Booking ID");
        lbooking_id.setBounds(250,100,200,30);
        lbooking_id.setFont(fontlabel);
        lbooking_id.setForeground(Color.BLACK);
        add(lbooking_id);

        tbooking_id = new JTextField();
        tbooking_id.setBounds(430,100,200,30);
        tbooking_id.setFont(fonttext);
        add(tbooking_id);

        lfirstname = new JLabel("First Name");
        lfirstname.setBounds(250,150,200,30);
        lfirstname.setFont(fontlabel);
        lfirstname.setForeground(Color.BLACK);
        add(lfirstname);

        tfirstname = new JTextField();
        tfirstname.setBounds(430,150,200,30);
        tfirstname.setForeground(Color.BLACK);
        add(tfirstname);

        llastname = new JLabel("Last Name");
        llastname.setBounds(250,200,200,30);
        llastname.setFont(fontlabel);
        llastname.setForeground(Color.BLACK);
        add(llastname);

        tlastname = new JTextField();
        tlastname.setBounds(430,200,200,30);
        tlastname.setForeground(Color.BLACK);
        add(tlastname);

        lmobile = new JLabel("Mobile Number");
        lmobile.setBounds(250,250,200,30);
        lmobile.setFont(fontlabel);
        lmobile.setForeground(Color.BLACK);
        add(lmobile);

        tmobile = new JTextField();
        tmobile.setBounds(430,250,200,30);
        tmobile.setForeground(Color.BLACK);
        add(tmobile);

        lgender = new JLabel("Gender");
        lgender.setBounds(250,300,200,30);
        lgender.setFont(fontlabel);
        lgender.setForeground(Color.BLACK);
        add(lgender);

        tgender = new JTextField();
        tgender.setBounds(430,300,200,30);
        tgender.setForeground(Color.BLACK);
        add(tgender);

        ldate= new JLabel("Appointment Date");
        ldate.setBounds(250,350,200,30);
        ldate.setFont(fontlabel);
        ldate.setForeground(Color.BLACK);
        add(ldate);

        tdate = new JTextField("DD/MM/YYYY");
        tdate.setBounds(430,350,200,30);
        tdate.setForeground(Color.BLACK);
        add(tdate);

        lreference_doc= new JLabel("Reference Doctor");
        lreference_doc.setBounds(250,400,200,30);
        lreference_doc.setFont(fontlabel);
        lreference_doc.setForeground(Color.BLACK);
        add(lreference_doc);

        treference_doc = new JTextField();
        treference_doc.setBounds(430,400,200,30);
        treference_doc.setForeground(Color.BLACK);
        add(treference_doc);

        tarea = new TextArea(10,30);
        tarea.setBounds(700,150,550,200);
        tarea.setFont(fontlabel);
        add(tarea);

        lupdate= new JLabel("Change");
        lupdate.setBounds(250,550,200,30);
        lupdate.setFont(fontlabel);
        lupdate.setForeground(Color.BLACK);
        add(lupdate);

        tupdate = new JTextField();
        tupdate.setBounds(430,550,200,30);
        tupdate.setForeground(Color.BLACK);
        add(tupdate);

        ltuple= new JLabel("Data");
        ltuple.setBounds(700,380,200,30);
        ltuple.setFont(fontlabel);
        ltuple.setForeground(Color.BLACK);

        ttuple = new TextArea();
        ttuple.setBounds(700,420,550,100);
        ttuple.setForeground(Color.BLACK);

        lapp= new JLabel("Change Appointment Date");
        lapp.setBounds(700,550,300,30);
        lapp.setFont(fontlabel);
        lapp.setForeground(Color.BLACK);
        add(lapp);

        tapp = new JTextField("DD/MM/YYYY");
        tapp.setBounds(700,600,200,30);
        tapp.setForeground(Color.BLACK);
        add(tapp);


        // Button
        mdisplay = new JButton("Display");
        mdisplay.setBounds(700,100,90,30);
        add(mdisplay);
        mdisplay.addActionListener(this);

        msubmit = new JButton("Submit");
        msubmit.setBounds(470,470,90,30);
        add(msubmit);
        msubmit.addActionListener(this);

        mdelete = new JButton("Delete");
        mdelete.setBounds(430,600,90,30);
        add(mdelete);
        mdelete.addActionListener(this);

        mshow = new JButton("Show");
        mshow.setBounds(540,600,90,30);
        add(mshow);
        mshow.addActionListener(this);

        mapp = new JButton("Update");
        mapp.setBounds(480,660,90,30);
        add(mapp);
        mapp.addActionListener(this);


    }

    public void actionPerformed(ActionEvent e){
        String s1 = tbooking_id.getText();
        String s2 = tfirstname.getText();
        String s3 = tlastname.getText();
        String s4 = tmobile.getText();
        String s5 = tgender.getText();
        String s6 = tdate.getText();
        String s7 = treference_doc.getText();
        String s = tupdate.getText();
        String s8 = tapp.getText();

        Connection con=null;
        Statement st1;
        ResultSet rs1;


            try {

                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hms","root","@Ryuk#007");
                st1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);


                if(e.getSource()==msubmit) {
                    try {
                        int book = Integer.parseInt(s1);
                     // Prepare the SQL statement
                        String sql = "INSERT INTO patient (BOOKING_ID, FIRST_NAME, LAST_NAME, MOBILE, GENDER, DATE, DOC_NAME) VALUES (?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement statement = con.prepareStatement(sql);
                        statement.setInt(1, book);
                        statement.setString(2, s2);
                        statement.setString(3, s3);
                        statement.setString(4, s4);
                        statement.setString(5, s5);
                        statement.setString(6, s6);
                        statement.setString(7, s7);

                     // Execute the SQL statement
                        statement.executeUpdate();


                        // Display a success message or perform other actions
                        JOptionPane.showMessageDialog(this, "Appointment saved successfully!");
                    }
                    catch(Exception a){
                        JOptionPane.showMessageDialog(this, "Appointment not saved !");
                    }

                }

                //Display from Database
                if(e.getSource()==mdisplay){
                    try {
                        String sql = "SELECT * FROM patient";
                        rs1 = st1.executeQuery(sql);
                        JOptionPane.showMessageDialog(this, "DATA IS FETCHED!");
                        tarea.append("BID | FIRST NAME | LAST NAME | MOBILE | GENDER | DATE | DOC NAME \n");
                        tarea.append("----------------------------------------------------------------------------------\n");
                        while (rs1.next()) {
                            int bid = rs1.getInt("BOOKING_ID");
                            String firstname = rs1.getString("FIRST_NAME");
                            String lastname = rs1.getString("LAST_NAME");
                            String mobile = rs1.getString("MOBILE");
                            String gender = rs1.getString("GENDER");
                            String date = rs1.getString("DATE");
                            String doc_name = rs1.getString("DOC_NAME");
                            tarea.append(bid + " " + firstname + " " + lastname + " " + mobile + " " + gender + " " + date + " " + doc_name + "\n");
                            tarea.append("------------------------------------------------------------------------------\n");
                        }
                    }
                    catch(Exception a){
                        JOptionPane.showMessageDialog(this, "DATA IS NOT FETCHING!");
                    }

                }
                //Delete from Database
                if(e.getSource()==mdelete){
                    try{
                        String sql ="DELETE FROM patient WHERE BOOKING_ID= "+s;
                        PreparedStatement preparedStatement = con.prepareStatement(sql);

                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(this, "DATA IS DELETED !");
                    }
                    catch(Exception a){
                        JOptionPane.showMessageDialog(this, "DATA IS NOT DELETED !");
                    }
                }
                //Show single tuple database
                if(e.getSource()==mshow){
                    int flag=0;
                    try{
                        String sql = "SELECT * FROM patient WHERE BOOKING_ID= "+s;
                        rs1 = st1.executeQuery(sql);
                        add(ltuple);
                        add(ttuple);
                        while (rs1.next()) {
                            ttuple.append("BID | FIRST NAME | LAST NAME | MOBILE | GENDER | DATE | DOC NAME \n");
                            ttuple.append("----------------------------------------------------------------------------------\n");
                            int bid = rs1.getInt("BOOKING_ID");
                            String firstname = rs1.getString("FIRST_NAME");
                            String lastname = rs1.getString("LAST_NAME");
                            String mobile = rs1.getString("MOBILE");
                            String gender = rs1.getString("GENDER");
                            String date = rs1.getString("DATE");
                            String doc_name = rs1.getString("DOC_NAME");
                            ttuple.append(bid + " " + firstname + " " + lastname + " " + mobile + " " + gender + " " + date + " " + doc_name + "\n");
                            ttuple.append("------------------------------------------------------------------------------\n");
                            flag=1;
                        }
                    }
                    catch(Exception a){
                        JOptionPane.showMessageDialog(this, "TUPLE NOT FOUND!");
                    }
                    if(flag==1){
                        JOptionPane.showMessageDialog(this, "TUPLE FOUND!");
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "TUPLE NOT FOUND!");
                    }

                }
                if (e.getSource() == mapp) {
                    try {
                        String sql = "UPDATE patient SET DATE = ? WHERE BOOKING_ID = ?";
                        PreparedStatement preparedStatement = con.prepareStatement(sql);
                        preparedStatement.setString(1, s8); // Set the new value for DATE
                        preparedStatement.setString(2, s); // Specify the BOOKING_ID of the tuple to update

                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(this, "DATE UPDATE SUCCESSFULLY!");
                        } else {
                            JOptionPane.showMessageDialog(this, "No tuple found with the specified BOOKING_ID!");
                        }
                    } catch (Exception a) {
                        JOptionPane.showMessageDialog(this, "An error occurred: " + a.getMessage());
                    }
                }

            }
            catch(Exception a){
                a.printStackTrace();
            }
        }

    public static void main(String[] args){
        DoctorAppointment doc = new DoctorAppointment();
    }


}
