package dao;

import domain.Student;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

/**
 * A DAO class for managing the storage of Students
 */
public class StudentListDAO implements StudentDAO {

   private static Collection<Student> students = new ArrayList<>();
   private static Collection<String> majors = new TreeSet();

   @Override
   public Collection<String> getMajors() {
      return majors;
   }

   /**
    * Adds a student to the DAO.
    *
    * @param student - the student to add.
    */
   @Override
   public void save(Student student) {
      students.add(student);
      majors.add(student.getMajor());
   }

   /**
    * Returns all students that have been added to the DAO.
    *
    * @return The collection of students.
    */
   @Override
   public Collection<Student> getAll() {
      return students;
   }
}
