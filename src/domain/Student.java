package domain;

public class Student implements Comparable{

   @Override
   public int compareTo(Object o) {
      if (this.getClass() != o.getClass()){
         throw new UnsupportedOperationException("Cannot compare these items.");
      }
      Student s = (Student)o;
      if (this.id > s.id){
         return 1;
      }else{
         return 0;
      }
}


   private Integer id;
   private String name;
   private String major;

   public Student() {
   }

   public Student(Integer id, String name, String major) {
      this.id = id;
      this.name = name;
      this.major = major;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getMajor() {
      return major;
   }

   public void setMajor(String major) {
      this.major = major;
   }

   @Override
   public String toString() {
      return this.id + ", " + this.name + ", " + this.major;
   }
}
