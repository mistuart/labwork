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
    
    private StudentDAO dao = new StudentCollectionsDAO();
    
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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}