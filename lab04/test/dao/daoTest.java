/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Student;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
/**
 *
 * @author user
 */
public class daoTest {
    
    public daoTest() {
    }
    
    private StudentDAO dao = new StudentJdbcDAO();
    
    private int jackId = 11111;
    private Student jack;
    
    private int jillId = 22222;
    private Student jill;
    
    @Before
    public void setUp() {
        jack = new Student(jackId, "Jack", "Knitting");
        jill = new Student(jillId, "Jill", "Ninjitsu");
        
        dao.save(jack);
        dao.save(jill);
        
    }
    
    @After
    public void tearDown() {
        dao.delete(jack);
        dao.delete(jill);
    }
    
    @Test
    public void testDaoSaveAndDelete(){
        
        Student fred = new Student(33333, "Fred", "Gregorian Chanting");
        
        dao.save(fred);
        
        Student retrieved = dao.getById(33333);
        
        assertEquals("Reterieved student should be the same one as saved", fred, retrieved);
        
        
        dao.delete(fred);
        
        retrieved = dao.getById(33333);
        
        assertNull("Student should no longer exist", retrieved);
        
    }
    
    @Test
    public void testDaoGetAll(){
        Collection<Student> students = dao.getAll();
        
        assertTrue("Jack should exist in result", students.contains(jack));
        assertTrue("Jill should exist in result", students.contains(jill));
        
        for (Student s: students){
            if (s.equals(jack)){
                assertEquals(jack.getId(), s.getId());
                assertEquals(jack.getMajor(), s.getMajor());
                assertEquals(jack.getName(), s.getName());
            }
        }
    }
    
    @Test
    public void testDaoGetById(){
        
        Student result = dao.getById(11111);
        
        assertEquals("should be jack",jack, result);
        
        assertEquals(jack.getId(), result.getId());
        assertEquals(jack.getMajor(), result.getMajor());
        assertEquals(jack.getName(), result.getName());
        
        
        result = dao.getById(55555);
        assertNull("should get null", result);
    }
    
    
    @Test
    public void testGetMajors(){
        Collection<String> result = dao.getMajors();
        assertTrue("should contain Knitting", result.contains("Knitting"));
        assertTrue("should contain Ninjitsu", result.contains("Ninjitsu"));
    }
    
    @Test
    public void testSaveToEdit(){
        jack.setName("Jenny");
        dao.save(jack);
        
        Student result = dao.getById(jackId);
        
        assertEquals("should still get same student", jack, result);
        assertEquals("Jack should be Jenny", jack.getName(), result.getName());
        

    }
}