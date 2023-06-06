import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Department;
import model.Employee;

import java.util.ArrayList;

public class VytvaranieJson {
    public static void main(String[] args) {

        // * Vytvorenie JSON
        System.out.println("\n\n* Vytvorenie JSON cez JsonNode prostredníctvom metódy put");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.createObjectNode()
                .put("name", "John")
                .put("age", 30)
                .put("city", "New York");
        String newJsonString = jsonNode.toString();
        System.out.println(newJsonString);



        // * Pristup k hodnotam
        System.out.println("\n\n* A prístup k hodnotám prostredníctvom get");
        String name = jsonNode.get("name").asText();
        int age = jsonNode.get("age").asInt();
        String city = jsonNode.get("city").asText();

        // Vypisanie konkretnych hodnot
        System.out.println("name: " + name);
        System.out.println("age: " + age);
        System.out.println("city: " + city);



        // * Konverzia ArrayList na JSON
        System.out.println("\n\n* Konverzia ArrayList na JSON");
        ArrayList<String> list = new ArrayList<>();
        list.add("John");
        list.add("Jane");
        list.add("Joe");

        ObjectMapper objectMapper2 = new ObjectMapper();
        String jsonString2;
        try {
            jsonString2 = objectMapper2.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(jsonString2);



        // * Konverzia prepojenych objektov do JSON
        System.out.println("\n\n* Konverzia prepojených objektov do JSON");
        // Vytvorit zamestnanca a priradit ho k oddeleniu
        Employee employee1 = new Employee(); // Vytvorili sme "prazdneho" zamestnanca
        employee1.setId(1);
        employee1.setName("Ján");
        employee1.setAge(27);

        Department department1 = new Department();
        department1.setId(1);
        department1.setName("IT oddelenie");

        employee1.setDepartment(department1); // Zamestnancovi employee1 sme nastavili department1

        ObjectMapper objectMapper3 = new ObjectMapper();
        String jsonString3;
        try {
            jsonString3 = objectMapper2.writeValueAsString(employee1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(jsonString3);



        System.out.println("\n\n* Ako získať JSON (prepojené) objekty. Pozn.: Zobrazenie výsledného objektu ovplyvnené metódou toString()");
        // * Ako ziskat z JSON (prepojene) objekty. Pozn.: Zobrazenie výsledného objektu ovplyvnené metódou toString().
        String jsonEmployee = "{\"id\":1,\"name\":\"Ján\",\"age\":27,\"departmentId\":0,\"department\":{\"id\":1,\"name\":\"IT oddelenie\"}}";
        ObjectMapper objectMapper4 = new ObjectMapper();
        Employee employeeFromJson;
        try {
            employeeFromJson = objectMapper.readValue(jsonEmployee, Employee.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Prekonvertovaný Employee z JSON: \n" + employeeFromJson);

    }
}
