/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.StudentDAO;
import java.util.Collection;
import java.util.TreeSet;
import org.fest.swing.fixture.DialogFixture;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.mockito.Mockito.*;

/**
 *
 * @author user
 */
public class StudentDialogTest {
    private StudentDAO dao;
    private DialogFixture fest;
    public StudentDialogTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Collection<String> majors = new TreeSet<>();
        majors.add("Kintting");
        majors.add("Ninjitsu");
        
        dao = mock(StudentDAO.class);
        
        when(dao.getMajors()).thenReturn(majors);
    }
    
    @After
    public void tearDown() {
        fest.cleanUp();
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}