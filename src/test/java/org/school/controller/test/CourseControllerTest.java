package org.school.controller.test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.school.controller.CourseController;
import org.school.model.Course;
import org.school.response.Message;
import org.school.response.MessageList;
import org.school.service.CourseService;
import org.school.util.test.AbstractUnitTest;
import org.school.util.test.TestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

/**
 * Course Controller Test.
 * @author cdacr
 *
 */
public class CourseControllerTest extends AbstractUnitTest {

  /** */
  @Autowired
  private ApplicationContext context;
  /** */
  private MockMvc mockMvc;

  /** */
  private CourseController courseController;

  /** */
  @Mock
  private CourseService courseService;

  /**
   * Setup.
   * @throws Exception
   *           Exception
   */
  @Before
  public final void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    courseController = context.getBean(CourseController.class);
    mockMvc = standaloneSetup(courseController).build();
  }

  /**
   * Test get all courses.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testGetAllCourses() throws Exception {
    setPrivateFields();
    final List<Course> courses = new ArrayList<Course>();
    courses.add(new Course());
    courses.add(new Course());

    when(courseService.getAllCourses()).thenReturn(courses);

    mockMvc.perform(get("/courses.json")).andExpect(status().isOk());
  }

  /**
   * Test get all courses not found.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testGetAllCoursesNotFound() throws Exception {
    setPrivateFields();
    final List<Course> courses = new ArrayList<Course>();

    when(courseService.getAllCourses()).thenReturn(courses);

    mockMvc.perform(get("/courses.json")).andExpect(status().isNotFound());
  }

  /**
   * Test get course by course id.
   * @throws Exception
   *           Exception
   */
  @Test
  @Ignore
  public final void testGetCourseInt() throws Exception {
    setPrivateFields();
    final Course course = new Course();
    when(courseService.getCourse(anyInt())).thenReturn(course);
    mockMvc.perform(get("/course/{id}.json", 1)).andExpect(status().isOk());
  }

  /**
   * Test get course by course name.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testGetCourseString() throws Exception {
    setPrivateFields();
    final Course course = new Course();
    when(courseService.getCourse(anyString())).thenReturn(course);
    mockMvc.perform(get("/course/{name}.json", "1")).andExpect(status().isOk());
  }

  /**
   * Test get course by course name not found.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testGetCourseStringNotFound() throws Exception {
    setPrivateFields();
    when(courseService.getCourse(anyString())).thenReturn(null);
    mockMvc.perform(get("/course/{name}.json", "1"))
        .andExpect(status().isNotFound());
  }

  /**
   * Test add course success.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testAddCourse() throws Exception {
    setPrivateFields();
    final MessageList messages = context.getBean(MessageList.class);
    // messages.getMessages().add(context.getBean(Message.class));
    when(courseService.addCourse(any(Course.class), any(BindingResult.class)))
        .thenReturn(messages);
    final Course course = new Course();
    course.setDescription("BCA");
    mockMvc
        .perform(post("/course/add.json").content(TestUtil.asJsonString(course))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  /**
   * Test add course bad request.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testAddCourseBadRequest() throws Exception {
    setPrivateFields();
    final MessageList messages = context.getBean(MessageList.class);
    messages.getMessages().add(context.getBean(Message.class));
    when(courseService.addCourse(any(Course.class), any(BindingResult.class)))
        .thenReturn(messages);
    final Course course = new Course();
    course.setDescription("BCA");
    mockMvc
        .perform(post("/course/add.json").content(TestUtil.asJsonString(course))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  /**
   * Test update course success.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testUpdateCourse() throws Exception {
    setPrivateFields();
    final MessageList messages = context.getBean(MessageList.class);
    // messages.getMessages().add(context.getBean(Message.class));
    when(
        courseService.updateCourse(anyInt(), any(Course.class),
            any(BindingResult.class)))
        .thenReturn(messages);
    final Course course = new Course();
    course.setId(1);
    course.setDescription("BCA");
    mockMvc
        .perform(
            put("/course/update/1.json").content(TestUtil.asJsonString(course))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  /**
   * Test update course bad request.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testUpdateCourseBadRequest() throws Exception {
    setPrivateFields();
    final MessageList messages = context.getBean(MessageList.class);
    messages.getMessages().add(context.getBean(Message.class));
    when(courseService.updateCourse(anyInt(), any(Course.class),
        any(BindingResult.class))).thenReturn(messages);
    final Course course = new Course();
    course.setId(1);
    course.setDescription("BCA");
    mockMvc
        .perform(
            put("/course/update/1.json").content(TestUtil.asJsonString(course))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  /**
   * Test delete course success.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testDeleteCourse() throws Exception {
    setPrivateFields();
    when(courseService.deleteCourse(anyInt())).thenReturn(true);

    mockMvc.perform(delete("/course/delete/1.json")).andExpect(status().isOk());
  }

  /**
   * Test delete course not found.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testDeleteCourseNotFound() throws Exception {
    setPrivateFields();
    when(courseService.deleteCourse(anyInt())).thenReturn(false);

    mockMvc.perform(delete("/course/delete/1.json"))
        .andExpect(status().isNotFound());
  }

  /**
   * Set Private Fields dependencies.
   */
  private void setPrivateFields() {
    ReflectionTestUtils.setField(courseController, "courseService",
        courseService, CourseService.class);
  }
}
