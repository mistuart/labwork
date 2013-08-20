/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author stumi579
 */
public class StudentJdbcDAO implements StudentDAO{
   @Override
   public void save(Student student){
     try (
             Connection connection = JdbcConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "merge into students (id, name, major) values (?,?,?)")
             ){ 
        
        stmt.setInt(1, student.getId());
        stmt.setString(2, student.getName());
        stmt.setString(3, student.getMajor());
        
        stmt.executeUpdate();
     } catch (SQLException ex){
        throw new DAOException(ex.getMessage(), ex);
     }
      
   }
   @Override
   public Collection<String> getMajors(){
      try (
              Connection connection = JdbcConnection.getConnection();
              PreparedStatement stmt = connection.prepareStatement(
                      "select distinct major from students order by major");
              
              ResultSet rs = stmt.executeQuery();
              ){
         Collection<String> majors = new ArrayList<>();
         while (rs.next()){
            majors.add(rs.getString("major"));
         }
         return majors;
         
         } catch (SQLException ex){
            throw new DAOException(ex.getMessage(),ex);
         }  
   }
@Override
   public Collection<Student> getAll(){
      try (
              Connection connection = JdbcConnection.getConnection();
              PreparedStatement stmt = connection.prepareStatement(
                      "select * from students order by id");
              
              ResultSet rs = stmt.executeQuery();
              ){
         Collection<Student> students = new ArrayList<>();
         while (rs.next()){
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String major = rs.getString("major");
            
            Student s = new Student(id, name, major);
            
            students.add(s);
         }
         return students;
         
         } catch (SQLException ex){
            throw new DAOException(ex.getMessage(), ex);
         }  
   }
      
   
   @Override
   public void delete(Student student){
   try (
              Connection connection = JdbcConnection.getConnection();
              PreparedStatement stmt = connection.prepareStatement(
                      "delete from students where id = ?");
              
              ){
        stmt.setInt(1, student.getId());        
        stmt.executeUpdate();
         } catch (SQLException ex){
            throw new DAOException(ex.getMessage(), ex);
         } 
   }
   @Override
   public Student getById(int id){
   try (
              Connection connection = JdbcConnection.getConnection();
              PreparedStatement stmt = connection.prepareStatement(
                      "select * from students where id = ?");
              
              ){
         stmt.setInt(1, id);
           
         ResultSet rs = stmt.executeQuery();
         Student s = null;
         if (rs.next()){
            String name = rs.getString("name");
            String major = rs.getString("major");
             s = new Student(id, name, major);
         }
         return s;
         
         } catch (SQLException ex){
            throw new DAOException(ex.getMessage(), ex);
         } 
   }
}
