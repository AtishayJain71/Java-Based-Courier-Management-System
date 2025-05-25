import java.util.*;
import java.sql.*;

interface Test{
    void contact_us();
}

public class Main{
    static String intro="Welcome To Courier Services Department!";

    

    //Inner class Started
    class Call implements Test,Runnable{
        String type;
        String name1,con1,p_loc1,d_loc1,id1;
        int dis1,t1;
        double wgt1,amt1;
        static int k=-1;
        static ArrayList<String> id=new ArrayList<String>();
        static ArrayList<String> name=new ArrayList<String>();
        static ArrayList<String> con=new ArrayList<String>();
        static ArrayList<String> p_loc=new ArrayList<String>();
        static ArrayList<String> d_loc=new ArrayList<String>();
        static ArrayList<Integer> t=new ArrayList<Integer>();
        static ArrayList<Integer> dis=new ArrayList<Integer>();
        static ArrayList<Double> wgt=new ArrayList<Double>();
        static ArrayList<Double> amt=new ArrayList<Double>();

       
        Call(String type){
            this.type=type;
            name1=con1=p_loc1=d_loc1=id1="";
            dis1=t1=0;
            wgt1=amt1=0.0;
        }

        public void contact_us(){
            System.out.println("\nFor Further Inquiries, React out to us at:\nPhone Number: +0135 2759876\nEmail ID: 123@gmail.com\n");
        }

        public synchronized void adminCheck(){
            Scanner sc=new Scanner(System.in);
            final String login[]={"123@gmail.com","admin@123"};
            while(true){
                System.out.print("Enter Admin ID: ");
                String s=sc.nextLine();
                System.out.print("Enter Password: ");
                String ss=sc.nextLine();
                if(s.equals(login[0]) && ss.equals(login[1])){
                    inventory();
                    break;
                }
                else{
                    System.out.println("Admin ID or Password is INCORRECT\n");
                } 
            }
        }

        private void displayInventory(){
            if(k==-1) System.out.println("No Pending Consignments");
            else{
                int z=1;
                for (int i=0;i<=k;i++){
                    System.out.println("Consignmemnt Number "+z);
                    z++;
                    bill(i);
                }
            }
        }

        private void inventory(){
            Scanner sc=new Scanner(System.in);
            outerloop:
            while(true){
                System.out.println("\nEnter Choice:\n1:View Inventory\t2:Track A Specific Order\t3:Exit to Login Page");
                int choice=sc.nextInt();
                innerloop:
                while(true){
                    switch(choice){
                        case 1:
                            displayInventory();
                            break innerloop;


                        case 2:
                            status();
                            break innerloop;

                        case 3:
                            break outerloop;

                        default: System.out.println("Wrong Choice..!!!");
                                break innerloop;
                    }
                }
            }
        }

        public synchronized void userCheck(){
            Scanner sc=new Scanner(System.in);
            outerloop:
            while(true){
                System.out.println("\nEnter Choice:\n1:Apply for Shipping\t2:Status\t3:Contact Us\t4:Exit To Login Page");
                int choice1=sc.nextInt();
                innerloop:
                while(true){
                    switch(choice1){
                        case 1:
                            userInput();
                            break innerloop;

                        case 2:
                            status();
                            break innerloop;

                        case 3:
                            contact_us();
                            break innerloop;

                        case 4:
                            break outerloop;

                        default: System.out.println("Wrong Choice..!!!");
                                break innerloop;
                    }
                }
            }
        }

        public double calc(int dis,double wgt){
            double amt=100.0;
            if(wgt<=1)
            return amt+=1*dis;
            else if(wgt<=5)  
            return amt+=1.5*dis;
            else
            return amt+=((wgt-5)*10)+(1.5*dis);
        }
        public int calc(int dis){
            if(dis<=50) return 2;
            else if(dis<=500) return 3;
            else if(dis<=1500) return 4;
            else return 5;
        }

        public void userInput(){      
            Scanner sc=new Scanner(System.in);
            while(true){
                System.out.print("Enter Personal Details:\nName:");
                name1=sc.nextLine();
                System.out.print("Enter Contact Number: ");
                con1=sc.nextLine();
                System.out.print("Enter Pickup Location: ");
                p_loc1 = sc.nextLine();
                System.out.print("Enter Drop Location: ");
                d_loc1 = sc.nextLine();
                System.out.print("Enter Distance in kms: ");
                dis1=sc.nextInt();
                sc.nextLine();
                System.out.print("Enter approx Weight of the Consignment in kgs: ");
                wgt1=sc.nextDouble();
                sc.nextLine();

                amt1=calc(dis1,wgt1);
                System.out.println("\nAmount to be paid: "+amt1+" Rs.");
                t1=calc(dis1);
                System.out.println("Estimated Delivery in "+t1+" Days");

                Random rand=new Random();
                id1="u"+(rand.nextInt(9999-1000+1)+1000);

            
                System.out.println("\n1: To Confirm\n2: Update Details\n3: Return To Main Menu");
                int temp=sc.nextInt();
                sc.nextLine();
                if(temp==1){
                    k++;
                    id.add(id1);
                    t.add(t1);
                    name.add(name1);
                    con.add(con1);
                    p_loc.add(p_loc1);
                    d_loc.add(d_loc1);
                    dis.add(dis1);
                    amt.add(amt1);
                    wgt.add(wgt1);

                    addDB(name1,con1,p_loc1,d_loc1,dis1,wgt1,amt1,t1,id1);

                    System.out.println("\nConfirmed..!\nCourier will be picked up shortly.");
                    bill(k);
                    break;
                }
                else if(temp==2){
                    continue;
                }
                else{
                    break;
                }
            }
        }


        public void addDB(String name1, String con1, String p_loc1, String d_loc1, int dis1, double wgt1, double amt1, int t1, String id1) {
            try{
                    String url ="jdbc:mysql://localhost:3306/courierdb";
                    String user ="root";
                    String pass ="";

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection=DriverManager.getConnection(url,user,pass);
                    Statement statement=connection.createStatement();
                    String sql1="INSERT INTO inputcourier(name,contact,pickuplocation,droplocation,distance,weight,amount,time,uid) VALUES ('"+name1+"','"+con1+"','"+p_loc1+"','"+d_loc1+"',"+dis1+","+wgt1+","+amt1+","+t1+",'"+id1+"')";
                    statement.executeUpdate(sql1);
                    String sql="SELECT * FROM inputcourier";
                    ResultSet rs = statement.executeQuery(sql);
                    while(rs.next()){
                        System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(5)+" "+rs.getDouble(6)+" "+rs.getDouble(7)+" "+rs.getInt(8)+" "+rs.getString(9));
                    }
                    connection.close();
            }
            catch(Exception e){
                System.out.println("An Error Occured: "+e);
            }
        }

        public void bill(int k){
            System.out.println("\nBILL DETAILS\nName: "+name.get(k)+"\nContact Number: "+con.get(k)+"\nUnique ID: "+id.get(k)+"\nPickup Location: "+p_loc.get(k)+"\nDrop Location: "+d_loc.get(k)+"\nApprox Weight: "+wgt.get(k)+"\nBill Amount: "+amt.get(k)+"\nDelivery in "+t.get(k)+" days\n");
        }

        public void status(){
            Scanner sc= new Scanner (System.in);
            System.out.print("Enter Unique ID: ");
            String ss=sc.nextLine();
            int flag=0;
            for(int i=0;i<id.size();i++){
            if(id.get(i).equals(ss)){
                bill(i);
                flag=1;
                break;
                }
            }
            if(flag==0){
                System.out.println("Incorrect Unique ID");
            }
        }
        
        @Override
        public void run() {
            if ("admin".equals(type)) {
                adminCheck();
            } else if ("user".equals(type)) {
                userCheck();
            }
        }
    }
    //Inner class Closed

    public void Complete(){
        try{
            Scanner sc=new Scanner(System.in);
            while(true){
                System.out.println("Enter Choice:\n1:User Login\t2:Admin Login\t3:Exit");
                int choice=sc.nextInt();
                if(choice==2){
                    Call call=new Call("admin");
                    Thread thread=new Thread(call);
                    thread.start();
                    thread.join();
                }
                else if(choice==1){
                    Call call=new Call("user");
                    Thread thread=new Thread(call);
                    thread.start();
                    thread.join();
                }
                else if(choice==3)
                    break;
                else
                    System.out.println("Wrong Choice..!!!");  
            }
        } catch(Exception e){
            System.out.println("An Error Occured: "+e);
        }
    }
    public static void main(String[] args) {
        try{
            Main obj=new Main();
            System.out.println(Main.intro);
            obj.Complete();
        }
        catch(Exception e){
            System.out.println("An Error Occured: "+e);
        }
    }       
}