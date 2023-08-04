
public class Database_Importer {
    public static void main(String[] args) throws IOException, SQLException{
        String jdbcURL = "jdbc:mysql://localhost:3306/db_sql_tutorial";
        String username = "fola";
        String password = "fola";
        //String excelFilePath = "Student.xlsx";

        //FileInputStream inputStream = new FileInputStream(excelFilePath);
        //XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        //XSSFSheet sheet = workbook.getSheetAt(0);

        //Iterator<Row> iterator = sheet.iterator();

        Connection connection = DriverManager.getConnection(jdbcURL,username,password);

        //String sql = "INSERT INTO customers(customer_id, first_name, last_name, country, score) ";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1));
        }


        //int rows = statement.executeUpdate(sql);
    }
}
