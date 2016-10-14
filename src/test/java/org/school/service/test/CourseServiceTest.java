package org.school.service.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
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
import org.school.dao.CourseDao;
import org.school.model.Course;
import org.school.response.MessageList;
import org.school.service.CourseService;
import org.school.util.test.AbstractUnitTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindingResult;

/**
 * CourseService.
 * @author cdacr
 */
public final class CourseServiceTest extends AbstractUnitTest {

  /** CourseDao Mock. */
  @Mock
  private CourseDao courseDao;

  /** */
  @Mock
  private BindingResult result;

  /** Course Service. */
  private CourseService courseService;

  /**
   * Setup.
   * @throws Exception
   *           Exception.
   */
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    courseService =
        (CourseService) unwrapProxy(getContext().getBean(CourseService.class));
  }

  /**
   * Test all required objects are not null.
   */
  @Test
  public void testCourseServiceNotNull() {
    assertNotNull(getContext());
    assertNotNull(courseService);
    assertNotNull(courseDao);
  }

  /**
   * Test add course.
   */
  @Test
  public void testAddCourse() {
    ReflectionTestUtils.setField(courseService, "courseDao", courseDao,
        CourseDao.class);
    ReflectionTestUtils.setField(courseService, "context", getContext(),
        ApplicationContext.class);

    final Course course = new Course();
    course.setDescription("BCA");

    final Course persistedCourse = new Course();
    course.setDescription("BCA");
    course.setId(123);

    when(result.hasErrors()).thenReturn(false);
    when(courseDao.isCourseExists(course.getDescription())).thenReturn(false);
    when(courseDao.saveCourse(course)).thenReturn(persistedCourse);

    final MessageList messages = courseService.addCourse(course, result);
    assertTrue(messages.getMessages().isEmpty());
  }

  /**
   * Test Add Course Error.
   */
  @Test
  public void testAlreadyExistingAddCourse() {
    ReflectionTestUtils.setField(courseService, "courseDao", courseDao,
        CourseDao.class);
    ReflectionTestUtils.setField(courseService, "context", getContext(),
        ApplicationContext.class);

    final Course course = new Course();
    course.setDescription("BCA");

    when(result.hasErrors()).thenReturn(false);
    when(courseDao.isCourseExists(course.getDescription())).thenReturn(true);

    final MessageList messages = courseService.addCourse(course, result);
    assertNotNull(messages);
    assertNotNull(messages.getMessages());
    assertTrue(!messages.getMessages().isEmpty());
    assertEquals("Course already exists!",
        messages.getMessages().get(0).getMessage());
  }

  /**
   * Update Course Test.
   */
  @Test
  public void testUpdateCourse() {
    ReflectionTestUtils.setField(courseService, "courseDao", courseDao,
        CourseDao.class);
    ReflectionTestUtils.setField(courseService, "context", getContext(),
        ApplicationContext.class);

    final Course course = new Course();
    course.setId(1);
    course.setDescription("BA");

    when(result.hasErrors()).thenReturn(false);
    when(courseDao.isCourseExists(course.getId())).thenReturn(true);
    when(courseDao.updateCourse(course)).thenReturn(course);

    final MessageList messages =
        courseService.updateCourse(course.getId(), course, result);
    assertNotNull(messages);
    assertNotNull(messages.getMessages());
    assertTrue(messages.getMessages().isEmpty());
  }

  /**
   * Update Course Test Fail.
   */
  @Test
  public void testNonExistingUpdateCourse() {
    ReflectionTestUtils.setField(courseService, "courseDao", courseDao,
        CourseDao.class);
    ReflectionTestUtils.setField(courseService, "context", getContext(),
        ApplicationContext.class);

    final Course course = new Course();
    course.setId(1);
    course.setDescription("BCA");

    when(result.hasErrors()).thenReturn(false);
    when(courseDao.isCourseExists(course.getId())).thenReturn(false);

    final MessageList messages =
        courseService.updateCourse(course.getId(), course, result);
    assertNotNull(messages);
    assertNotNull(messages.getMessages());
    assertTrue(!messages.getMessages().isEmpty());
    assertEquals("Course does not exists!",
        messages.getMessages().get(0).getMessage());
  }

  /**
   * Test delete course exists.
   */
  @Test
  public void testDeleteCourse() {
    ReflectionTestUtils.setField(courseService, "courseDao", courseDao,
        CourseDao.class);
    ReflectionTestUtils.setField(courseService, "context", getContext(),
        ApplicationContext.class);

    when(courseDao.isCourseExists(1)).thenReturn(true);
    doNothing().when(courseDao).deleteCourse(1);

    final boolean flag = courseService.deleteCourse(1);
    assertTrue(flag);
  }

  /**
   * Test delete course not exists.
   */
  @Test
  public void testDeleteCourseNotExists() {
    ReflectionTestUtils.setField(courseService, "courseDao", courseDao,
        CourseDao.class);
    ReflectionTestUtils.setField(courseService, "context", getContext(),
        ApplicationContext.class);

    when(courseDao.isCourseExists(1)).thenReturn(false);
    doNothing().when(courseDao).deleteCourse(1);

    final boolean flag = courseService.deleteCourse(1);
    assertTrue(!flag);
  }

  /**
   * Test get course.
   */
  @Test
  public void testGetCourse() {
    ReflectionTestUtils.setField(courseService, "courseDao", courseDao,
        CourseDao.class);
    ReflectionTestUtils.setField(courseService, "context", getContext(),
        ApplicationContext.class);

    final Course course = new Course();
    course.setId(123);
    course.setDescription("BBA");

    when(courseDao.getCourse(123)).thenReturn(course);

    final Course actualCourse = courseService.getCourse(123);
    assertNotNull(actualCourse);
    // assertEquals(123, actualCourse.getId());
    assertThat(actualCourse.getId(), is(course.getId()));
  }

  /**
   * Test get all courses.
   */
  @Test
  public void testGetAllCourses() {
    ReflectionTestUtils.setField(courseService, "courseDao", courseDao,
        CourseDao.class);
    ReflectionTestUtils.setField(courseService, "context", getContext(),
        ApplicationContext.class);
    final List<Course> courses = new ArrayList<Course>();
    courses.add(new Course());
    courses.add(new Course());

    when(courseDao.getAllCourse()).thenReturn(courses);
    final List<Course> actualCourses = courseService.getAllCourses();

    System.out.println(actualCourses);
    assertNotNull(actualCourses);
    assertThat(actualCourses.size(), is(2));
  }

}
