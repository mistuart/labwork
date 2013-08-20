/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.StudentDAO;
import domain.Student;
import java.util.Collection;
import java.util.TreeSet;
import org.fest.swing.fixture.DialogFixture;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.junit.Assert.*;
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
        majors.add("Kint");
        majors.add("Ninj");
        
        dao = mock(StudentDAO.class);
        
        when(dao.getMajors()).thenReturn(majors);
    }
    
    @After
    public void tearDown() {
        fest.cleanUp();
    }
    
    @Test
    public void testEdit(){
        Student jack = new Student(1234, "Jack", "Knit");
        
        StudentDialog dialog = new StudentDialog(null, true, jack, dao);
        
        fest = new DialogFixture(dialog);
        fest.show();
        
        fest.robot.settings().delayBetweenEvents(75);
        
        fest.textBox("txtId").requireText("1234");
        fest.textBox("txtName").requireText("Jack");
        fest.comboBox("cmbMajor").requireSelection("Knit");
        
        fest.textBox("txtName").selectAll().enterText("Jim");
        fest.comboBox("cmbMajor").selectItem("Ninj");
        
        fest.button("btnSave").click();
        
        ArgumentCaptor<Student> argument = ArgumentCaptor.forClass(Student.class);
        
        verify(dao).save(argument.capture());
        
        Student edited = argument.getValue();
        
        assertEquals("ensure the name was changed", "Jim", edited.getName());
        assertEquals("ensure the major was changed", "Ninj", edited.getMajor());
    }
    
    @Test
    public void testSave(){
        StudentDialog dialog = new StudentDialog(null, true, dao);
        
        fest = new DialogFixture(dialog);
        fest.show();
        
        fest.robot.settings().delayBetweenEvents(75);
        
        fest.textBox("txtId").enterText("9876");
        fest.textBox("txtName").enterText("Johnny");
        fest.comboBox("cmbMajor").selectItem("Ninj");
        
        fest.button("btnSave").click();
        
        
        ArgumentCaptor<Student> argument = ArgumentCaptor.forClass(Student.class);
        
        verify(dao).save(argument.capture());
        
        Student saved = argument.getValue();
        
        assertEquals("check id saved", 9876, (int)saved.getId());
        assertEquals("check name saved", "Johnny", saved.getName());
        assertEquals("check major saved", "Ninj", saved.getMajor());
        
        
        
    }
}