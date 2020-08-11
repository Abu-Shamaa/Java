import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

interface Person            // java interface
{
    public String getName();
}

class Student implements Person    // student class implements person
{
    private String name;
    private String matric;
    private String cgpa;

    public Student(String name, String matric, String cgpa)    // public method student
    {
        this.name = name;
        this.matric = matric;
        this.cgpa = cgpa;
    }

    public String toString() {
        return "Name: "+name+", Matric Number: "+matric+", CGPA: "+cgpa;
    }

    @Override                        // override
    public String getName() {
        return this.name;
    }
}

public class Main {
	

    static class StudentRecords
    {
        private  ArrayList<Student> currentRecords;
        public StudentRecords() {
            currentRecords = new ArrayList<Student>();
        }
	 
        public StudentRecords(String filename) throws IOException {
			currentRecords = new ArrayList<Student>();
            BufferedReader breader = new BufferedReader(new FileReader("data.txt"));
            String line;
            while((line = breader.readLine()) != null) {
                String[] tokens = line.split(",");
                String[] data = new String[3];
				
                for(int i = 0; i < tokens.length; i++) {
                    String[] t = tokens[i].split(": ");
                    data[i] = t[t.length-1];
                }
                currentRecords.add(new Student(data[0], data[1], data[2]));
            }

        }
        public void addRecord(Student record) {      // add recods function
            currentRecords.add(record);
        }
        public int size() {
            return currentRecords.size();
        }
        public Student getRecord(int i) {
            return currentRecords.get(i);
        }
        public ArrayList<Student> searchByName(String name) {     // search method
            ArrayList<Student> stds = new ArrayList<Student>();
            for(int i = 0; i < currentRecords.size(); i++) {
                if (currentRecords.get(i).getName().equals(name))
                    stds.add(currentRecords.get(i));
            }
            return stds;
        }
    }

    public static void main(String[] args) throws IOException {            // main method
        BufferedReader breader;
        BufferedWriter bwriter;
        Scanner scanner = new Scanner(System.in);
        StudentRecords records = new StudentRecords();
        while(true) {
            System.out.print("1. Add Record\n2. View Record\n3. Search Record\n4. Exit\nSelect an Option: "); // output
            int option = scanner.nextInt();
            scanner.nextLine();
            switch(option) {
               case 1:
                    String moreRecords = "yes";
					while (moreRecords.equals("yes")) {
                    String name;
					while (true)
					{
                        System.out.print("Input name: ");
                         name = scanner.nextLine();
                         String regex = "^[a-zA-Z\\s]+$";   // string validation for name
                         if (name.matches(regex)){ 
                              break;
  						}
						System.out.println("Ivalid Name please Input Again"); // if name invalid  ask to input again 
					}
                       
					   String matric;
					   
						while(true)
                        {
                            System.out.print("Input Matric Number: ");
                            matric = scanner.nextLine();
							if (matric.matches("\\d+")){     // check the valid matric number 
								break;
							}
							 System.out.println("Ivalid Matric Number please Input Again");  // if  Matric Number invalid  ask to input again
                        }
							
                        String cgpa;		
						while(true)
                        {
                            System.out.print("Input CGPA: ");
                            cgpa = scanner.nextLine();
							if (cgpa.matches("[0-9]*\\.?[0-9]+"))   // check the valid cgpa
							{
								break;
							}
							 System.out.println("Ivalid CGPA please Input Again");  // if cgpa invalid  ask to input again 
                        }
             
                       Student student = new Student(name, matric, cgpa);
                       records.addRecord(student);

                        System.out.println("** All Records **");              // print all recods
                        breader = new BufferedReader(new FileReader("data.txt"));
                        String line;
                        while ((line = breader.readLine()) != null) {
                            System.out.println(line);
                        }
                        for (int i = 0; i < records.size(); i++)
                            System.out.println(records.getRecord(i));

                        System.out.print("Add more records? (yes/no): ");
                        moreRecords = scanner.nextLine();
                    }
                    bwriter = new BufferedWriter(new FileWriter("data.txt", true));
                    for (int i = 0; i < records.size(); i++)
                        bwriter.write(records.getRecord(i) + "\n");
                    bwriter.close();
                    break;
                case 2:
                    System.out.println("** All Records **");     // show all records 
                    breader = new BufferedReader(new FileReader("data.txt"));
                    String line;
                    while ((line = breader.readLine()) != null) {
                        System.out.println(line);
                    }
                    breader.close();
                    break;
                case 3:
                    System.out.println("Student Name: ");       // search by student name 
                    StudentRecords allRecords = new StudentRecords("data.txt");
                    String name = scanner.nextLine();
                    ArrayList<Student> srecords = allRecords.searchByName(name);
                    System.out.println(srecords.size()+" record(s) found.");
                    for(int i = 0; i < srecords.size(); i++)
                        System.out.println(srecords.get(i));
                    break;
                case 4:
				
                    return;
            }
        }
    } 
}


