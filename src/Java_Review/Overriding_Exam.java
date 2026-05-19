package Java_Review;

public class Overriding_Exam {
    static class Employee {
        int baseSalary;
        String name;

        public Employee(String name, int baseSalary) {
            this.baseSalary = baseSalary;
            this.name = name;
        }

        public int calculateSalary() {
            return baseSalary;
        }

        public void displayInfo() {
            System.out.println("이름:" + name + ", 급여:" + calculateSalary() + "원");
        }
    }

    static class Manager extends Employee {
        String department;
        int bonus;

        public Manager(String name, int baseSalary, String department, int bonus) {
            super(name, baseSalary);
            this.department = department;
            this.bonus = bonus;
        }

        public int calculateSalary() {
            return baseSalary + bonus;
        }

        public void displayInfo() {
            System.out.println("이름:" + name + ", 부서:" + department + ", 급여:" + calculateSalary() + "원");
        }
    }

    public static void main(String[] args){
        Employee[] e;
        e = new Employee[4];
        e[0] = new Employee("김철수",3000000);
        e[1] = new Manager("박영희",3000000,"개발팀",1500000);
        e[2] = new Manager("정민아", 3200000, "기획팀", 1800000);
        e[3] = new Employee("이민수", 2800000);
        for (int i=0;i< e.length;i++){
            e[i].displayInfo();
            if (e[i] instanceof Manager){
                System.out.println("->관리자 입니다.");}
        }
    }
}
