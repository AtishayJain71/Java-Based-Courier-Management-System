public class check_jdbc {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver is correctly added to your classpath!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver is not found. Add it to your classpath");
            e.printStackTrace();
        }
    }
}