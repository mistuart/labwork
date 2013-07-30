package dao;

import domain.Student;
import java.util.Collection;

public interface StudentDAO {

   /**
    * Adds a student to the DAO.
    *
    * @param student - the student to add.
    */
   void save(Student student);

   /**
    * Returns all students that have been added to the DAO.
    *
    * @return The collection of students.
    */
   Collection<Student> getAll();
}
