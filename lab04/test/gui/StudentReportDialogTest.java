/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.StudentDAO;
import domain.Student;
import gui.helpers.SimpleListModel;
import java.util.Collection;
import java.util.TreeSet;
import org.fest.swing.fixture.DialogFixture;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;
import static org.fest.swing.core.matcher.JButtonMatcher.withText;
import static org.fest.swing.core.matcher.DialogMatcher.withTitle;
/**
 *
 * @author user
 */
public class StudentReportDialogTest {
    private Collection<Student> students;
    
    private Student jack;
    private Student jill;
    
    private StudentDAO dao;
    private StudentReportDialog dialog;
    
    private DialogFixture fest;
                
    public StudentReportDialogTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        jack = new Student(1234, "Jack", "Knit");
        jill = new Student(4321, "Jill", "Ninj");
        
        students = new TreeSet<>();
        students.add(jack);
        students.add(jill);
        
        dao = mock(StudentDAO.class);
        
        when(dao.getAll()).thenReturn(students);
        
        when(dao.getById(1234)).thenReturn(jack);
        when(dao.getById(4321)).thenReturn(jill);
        
        dialog = new StudentReportDialog(null, true, dao);
        
    }
    
    @After
    public void tearDown() {
        fest.cleanUp();
    }
    
    
    @Test
    public void testReportView(){
        
        fest = new DialogFixture(dialog);
        fest.show();
                
        fest.robot.settings().delayBetweenEvents(75);
        
        verify(dao).getAll();
        
        SimpleListModel model = (SimpleListModel) fest.list().component().getModel();
        
        assertTrue("ensure list contains Jack", model.contains(jack));
        assertTrue("ensure list contains Jill", model.contains(jill));
        assertTrue("ensure list contains correct number of students", model.getSize() == students.size());
        
    }
    
    @Test
    public void testReportDelete(){
        
        fest = new DialogFixture(dialog);
        fest.show();
        
        fest.robot.settings().delayBetweenEvents(75);
        
        fest.list().selectItem(jill.toString());
        
        fest.button("btnDelete").click();
        
        DialogFixture confirmDialog = fest.dialog(withTitle("Select an Option").andShowing()).requireVisible();
        
        confirmDialog.button(withText("No")).click();
        
        verify(dao, never()).delete(null);
        
        fest.list().selectItem(jill.toString());
        
        fest.button("btnDelete").click();
        
        confirmDialog = fest.dialog(withTitle("Select an Option").andShowing()).requireVisible();
        
        confirmDialog.button(withText("Yes")).click();
        
        ArgumentCaptor<Student> argument = ArgumentCaptor.forClass(Student.class);
        
        verify(dao).delete(argument.capture());
        
        Student deletedStudent = argument.getValue();
        
        assertEquals("deleted student should be Jill", jill, deletedStudent);
        
    }
    
    @Test
    public void testReportSearch(){
        
        fest = new DialogFixture(dialog);
        fest.show();
        
        fest.robot.settings().delayBetweenEvents(75);
        
        fest.textBox("txtSearch").selectAll().enterText("1234");
        fest.button("btnSearch").click();
        
        verify(dao).getById(jack.getId());
        
        SimpleListModel model = (SimpleListModel) fest.list().component().getModel();
        
        assertTrue("ensure lit contains jack", model.contains(jack));
        assertTrue("ensure only jack is showing", model.getSize() == 1);
        
    }
    
    @Test
    public void testReportEdit(){
        fest = new DialogFixture(dialog);
        fest.show();
        
        fest.robot.settings().delayBetweenEvents(75);
        
        fest.list().selectItem(jill.toString());
        fest.button("btnEdit").click();
        
        DialogFixture editDialog = fest.dialog("studentDialog").requireVisible();
        
        editDialog.textBox("txtId").requireText("4321");
        
    }
}