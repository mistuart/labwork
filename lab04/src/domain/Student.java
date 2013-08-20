package domain;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class Student implements Comparable<Student>{


   @NotNull (message="ID must be provided.")
   @Range(min=100, max=9999999, message="ID must contain between 3 and 7 digits(inclusive).")
   private Integer id;
   
   @NotBlank(message="Name must be provided.")
   @Length(min=2,message="Name must contain at least two characters.")
   private String name;
   
   @NotBlank(message="Major must be provided.")
   @Length(min=4, max=4, message="Major must be a four character code.")
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

   @Override
   public int compareTo(Student o) {
      return this.id.compareTo(o.getId());
   }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
   
  
}
