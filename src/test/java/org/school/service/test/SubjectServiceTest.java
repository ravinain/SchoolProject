/**
 * 
 */
package org.school.service.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.school.util.test.TestUtil.unwrapProxy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.school.dao.SubjectDao;
import org.school.model.Subject;
import org.school.response.MessageList;
import org.school.service.SubjectService;
import org.school.util.test.AbstractUnitTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindingResult;

/**
 * @author cdacr
 *
 */
public class SubjectServiceTest extends AbstractUnitTest {

  /** */
  private SubjectService subjectService;

  /** */
  @Mock
  private BindingResult result;

  /** */
  @Mock
  private SubjectDao subjectDao;

  /**
   * Setup.
   * @throws java.lang.Exception
   *           Exception
   */
  @Before
  public final void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    subjectService = (SubjectService) unwrapProxy(
        getContext().getBean(SubjectService.class));
  }

  /**
   * Test method for
   * {@link org.school.service.impl.SubjectServiceImpl#getSubjects()}.
   */
  @Test
  public final void testGetSubjects() {
    setPrivateFields();
    final List<Subject> subjects = new ArrayList<Subject>();
    subjects.add(new Subject());
    subjects.add(new Subject());

    when(subjectDao.getAllSubjects()).thenReturn(subjects);

    final List<Subject> actualSubjects = subjectService.getSubjects();
    assertNotNull(actualSubjects);
    assertFalse(actualSubjects.isEmpty());
    assertThat(actualSubjects.size(), is(subjects.size()));
  }

  /**
   * Test method to get subject by id.
   */
  @Test
  public final void testGetSubjectInt() {
    setPrivateFields();
    final Subject subject = new Subject();
    subject.setId(1);
    subject.setDescription("Abbb");

    when(subjectDao.getSubject(1)).thenReturn(subject);

    final Subject actualSub = subjectService.getSubject(1);
    assertNotNull(actualSub);
    assertEquals(actualSub.getDescription(), subject.getDescription());
  }

  /**
   * Test method for get subject by string.
   */
  @Test
  public final void testGetSubjectString() {
    setPrivateFields();
    final Subject subject = new Subject();
    subject.setId(1);
    subject.setDescription("Abbb");

    when(subjectDao.getSubject("Abbb")).thenReturn(subject);

    final Subject actualSub = subjectService.getSubject("Abbb");
    assertNotNull(actualSub);
    assertEquals(actualSub.getDescription(), subject.getDescription());
  }

  /**
   * Test method for saving subject.
   */
  @Test
  public final void testSaveSubject() {
    setPrivateFields();
    final Subject subject = new Subject();
    subject.setId(1);
    subject.setDescription("Abbbb");

    when(result.hasErrors()).thenReturn(false);
    when(subjectDao.isSubjectExists("Abbbb")).thenReturn(false);
    when(subjectDao.saveSubject(subject)).thenReturn(subject);

    final MessageList messages = subjectService.saveSubject(subject, result);
    assertNotNull(messages);
    assertNotNull(messages.getMessages());
    assertTrue(messages.getMessages().isEmpty());
  }

  /**
   * Test method for saving existing subject.
   */
  @Test
  public final void testSaveExistingSubject() {
    setPrivateFields();
    final Subject subject = new Subject();
    subject.setId(1);
    subject.setDescription("Abbbb");

    when(result.hasErrors()).thenReturn(false);
    when(subjectDao.isSubjectExists("Abbbb")).thenReturn(true);
    when(subjectDao.saveSubject(subject)).thenReturn(subject);

    final MessageList messages = subjectService.saveSubject(subject, result);
    assertNotNull(messages);
    assertNotNull(messages.getMessages());
    assertFalse(messages.getMessages().isEmpty());
  }

  /**
   * Test method for test update subject.
   */
  @Test
  public final void testUpdateSubject() {
    setPrivateFields();
    final Subject subject = new Subject();
    subject.setId(1);
    subject.setDescription("Abbbb");

    when(result.hasErrors()).thenReturn(false);
    when(subjectDao.isSubjectExists(1)).thenReturn(true);
    when(subjectDao.updateSubject(subject)).thenReturn(subject);

    final MessageList messages =
        subjectService.updateSubject(subject, result);
    assertNotNull(messages);
    assertNotNull(messages.getMessages());
    assertTrue(messages.getMessages().isEmpty());
  }

  /**
   * Test method for update non-existing subject.
   */
  @Test
  public final void testUpdateNonExistingSubject() {
    setPrivateFields();
    final Subject subject = new Subject();
    subject.setId(1);
    subject.setDescription("Abbbb");

    when(result.hasErrors()).thenReturn(false);
    when(subjectDao.isSubjectExists(1)).thenReturn(false);
    when(subjectDao.updateSubject(subject)).thenReturn(subject);

    final MessageList messages = subjectService.updateSubject(subject, result);
    assertNotNull(messages);
    assertNotNull(messages.getMessages());
    assertFalse(messages.getMessages().isEmpty());
  }

  /**
   * Test method to delete existing subject.
   */
  @Test
  public final void testDeleteSubject() {
    setPrivateFields();
    when(subjectDao.isSubjectExists(1)).thenReturn(true);
    final boolean flag = subjectService.deleteSubject(1);
    assertTrue(flag);
  }

  /**
   * Test method to delete non-existing subject.
   */
  @Test
  public final void testDeleteNonExistingSubject() {
    setPrivateFields();
    when(subjectDao.isSubjectExists(1)).thenReturn(false);
    final boolean flag = subjectService.deleteSubject(1);
    assertFalse(flag);
  }

  /**
   * Test method for existing subject by id.
   */
  @Test
  public final void testIsSubjectExistsInt() {
    setPrivateFields();
    when(subjectDao.isSubjectExists(1)).thenReturn(true);
    final boolean flag = subjectService.isSubjectExists(1);
    assertTrue(flag);
  }

  /**
   * Test method for non-existing subject by id.
   */
  @Test
  public final void testIsSubjectNotExistsInt() {
    setPrivateFields();
    when(subjectDao.isSubjectExists(1)).thenReturn(false);
    final boolean flag = subjectService.isSubjectExists(1);
    assertFalse(flag);
  }

  /**
   * Test method for existing subject by description.
   */
  @Test
  public final void testIsSubjectExistsString() {
    setPrivateFields();
    when(subjectDao.isSubjectExists("abbc")).thenReturn(true);
    final boolean flag = subjectService.isSubjectExists("abbc");
    assertTrue(flag);
  }

  /**
   * Test method for non-existing subject by description.
   */
  @Test
  public final void testIsSubjectNotExistsString() {
    setPrivateFields();
    when(subjectDao.isSubjectExists("abbc")).thenReturn(false);
    final boolean flag = subjectService.isSubjectExists("abbc");
    assertFalse(flag);
  }

  /**
   * Set Private Fields dependencies.
   */
  private void setPrivateFields() {
    ReflectionTestUtils.setField(subjectService, "subjectDao", subjectDao,
        SubjectDao.class);
    ReflectionTestUtils.setField(subjectService, "context", getContext(),
        ApplicationContext.class);
  }

}
