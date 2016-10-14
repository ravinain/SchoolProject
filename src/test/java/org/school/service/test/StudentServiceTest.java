package org.school.service.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.school.util.test.TestUtil.unwrapProxy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.school.dao.StudentDao;
import org.school.model.Student;
import org.school.response.MessageList;
import org.school.service.StudentService;
import org.school.util.test.AbstractUnitTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindingResult;

/**
 * Student Service Test.
 * @author cdacr
 */
public class StudentServiceTest extends AbstractUnitTest {

  /** */
  private StudentService studentService;

  /** */
  @Mock
  private BindingResult result;

  /** */
  @Mock
  private StudentDao studentDao;

  /**
   * Setup.
   * @throws Exception
   *           Exception
   */
  @Before
  public final void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    studentService = (StudentService) unwrapProxy(
        getContext().getBean(StudentService.class));
  }

  /**
   * Test get students.
   */
  @Test
  public final void testGetStudents() {
    setPrivateFields();
    final List<Student> students = new ArrayList<Student>();
    students.add(new Student());
    students.add(new Student());

    when(studentDao.getStudents()).thenReturn(students);
    assertNotNull(students);
    assertFalse(students.isEmpty());
    assertThat(students.size(), is(2));
  }

  /**
   * Test get student by id.
   */
  @Test
  public final void testGetStudent() {
    setPrivateFields();
    final Student student = new Student();
    student.setId(1);
    student.setName("Abcde");

    when(studentDao.getStudent(1)).thenReturn(student);

    final Student actualStudent = studentService.getStudent(1);
    assertNotNull(actualStudent);
    assertEquals(actualStudent.getId(), student.getId());
    assertEquals(actualStudent.getName(), student.getName());
  }

  /**
   * Test add student.
   */
  @Test
  public final void testAddStudent() {
    setPrivateFields();
    final Student student = new Student();
    student.setId(1);
    student.setName("Abbbb");

    when(result.hasErrors()).thenReturn(false);
    when(studentDao.isStudentExists(1)).thenReturn(false);
    when(studentDao.addStudent(student)).thenReturn(student);

    final MessageList messages = studentService.addStudent(student, result);
    assertNotNull(messages);
    assertNotNull(messages.getMessages());
    assertTrue(messages.getMessages().isEmpty());
  }

  /**
   * Test add existing student.
   */
  @Test
  public final void testAddExistingStudent() {
    setPrivateFields();
    final Student student = new Student();
    student.setId(1);
    student.setName("Abbbb");

    when(result.hasErrors()).thenReturn(false);
    when(studentDao.isStudentExists(1)).thenReturn(true);
    when(studentDao.addStudent(student)).thenReturn(student);

    final MessageList messages = studentService.addStudent(student, result);
    assertNotNull(messages);
    assertNotNull(messages.getMessages());
    assertFalse(messages.getMessages().isEmpty());
  }

  /**
   * Test update existing student.
   */
  @Test
  public final void testUpdateStudent() {
    setPrivateFields();
    final Student student = new Student();
    student.setId(1);
    student.setName("Abbbb");

    when(result.hasErrors()).thenReturn(false);
    when(studentDao.isStudentExists(1)).thenReturn(true);
    when(studentDao.updateStudent(student)).thenReturn(student);

    final MessageList messages =
        studentService.updateStudent(student.getId(), student, result);
    assertNotNull(messages);
    assertNotNull(messages.getMessages());
    assertTrue(messages.getMessages().isEmpty());
  }

  /**
   * Test update non existing student.
   */
  @Test
  public final void testUpdateNonExistingStudent() {
    setPrivateFields();
    final Student student = new Student();
    student.setId(1);
    student.setName("Abbbb");

    when(result.hasErrors()).thenReturn(false);
    when(studentDao.isStudentExists(1)).thenReturn(false);
    when(studentDao.updateStudent(student)).thenReturn(student);

    final MessageList messages =
        studentService.updateStudent(student.getId(), student, result);
    assertNotNull(messages);
    assertNotNull(messages.getMessages());
    assertFalse(messages.getMessages().isEmpty());
  }

  /**
   * Test delete existing student.
   */
  @Test
  public final void testDeleteStudent() {
    setPrivateFields();
    when(studentDao.isStudentExists(1)).thenReturn(true);
    doNothing().when(studentDao).deleteStudent(1);
    final boolean flag = studentService.deleteStudent(1);
    assertTrue(flag);
  }

  /**
   * Test delete non existing student.
   */
  @Test
  public final void testDeleteNonExistingStudent() {
    setPrivateFields();
    when(studentDao.isStudentExists(1)).thenReturn(false);
    doNothing().when(studentDao).deleteStudent(1);
    final boolean flag = studentService.deleteStudent(1);
    assertFalse(flag);
  }

  /**
   * Test existing student.
   */
  @Test
  public final void testIsStudentExists() {
    setPrivateFields();
    when(studentDao.isStudentExists(1)).thenReturn(true);
    final boolean flag = studentService.isStudentExists(1);
    assertTrue(flag);
  }

  /**
   * Test non existing student.
   */
  @Test
  public final void testStudentNotExists() {
    setPrivateFields();
    when(studentDao.isStudentExists(1)).thenReturn(false);
    final boolean flag = studentService.isStudentExists(1);
    assertFalse(flag);
  }

  /**
   * Set Private Fields dependencies.
   */
  private void setPrivateFields() {
    ReflectionTestUtils.setField(studentService, "studentDao", studentDao,
        StudentDao.class);
    ReflectionTestUtils.setField(studentService, "context", getContext(),
        ApplicationContext.class);
  }
}
