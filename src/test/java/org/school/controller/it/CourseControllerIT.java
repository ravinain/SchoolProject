package org.school.controller.it;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.school.controller.CourseController;
import org.school.model.Course;
import org.school.response.MessageList;
import org.school.util.test.AbstractIT;
import org.school.util.test.TestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * Course Controller IT.
 * @author cdacr
 *
 */
public class CourseControllerIT extends AbstractIT {

  /** */
  private MockMvc mockMvc;

  /** */
  @Autowired
  private CourseController courseController;
  /**
   * Setup.
   * @throws Exception
   *           Exception
   */
  @Before
  public final void setUp() throws Exception {
    mockMvc = standaloneSetup(courseController).build();
  }

  /**
   * Test get all courses.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testGetAllCourses() throws Exception {
    final MvcResult result = mockMvc.perform(get("/courses.json")).andReturn();
    final List<Course> courses =
        (List<Course>) TestUtil.asStringJson(
        result.getResponse().getContentAsString(), List.class, Course.class);

    assertNotNull(courses);
    assertThat(courses.size(), is(3));
  }

  /**
   * Test get course by course id.
   * @throws Exception
   *           Exception
   */
  @Test
  @Ignore
  public final void testGetCourseInt() throws Exception {
    final MvcResult result =
        mockMvc.perform(get("/course/{id}.json", 1)).andReturn();
    final Course course = (Course) TestUtil
        .asStringJson(result.getResponse().getContentAsString(), Course.class);

    assertNotNull(course);
    assertThat(course.getDescription(), is(eq("BCA")));
  }

  /**
   * Test get course by course name.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testGetCourseString() throws Exception {
    final MvcResult result =
        mockMvc.perform(get("/course/BCA.json")).andReturn();
    final Course course = (Course) TestUtil
        .asStringJson(result.getResponse().getContentAsString(), Course.class);

    assertNotNull(course);
    assertThat(course.getId(), is(1));
  }

  /**
   * Test get course by course name not found condition.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testGetCourseStringNotFound() throws Exception {
    final MvcResult result =
        mockMvc.perform(get("/course/BBA.json")).andReturn();
    final MessageList messages = (MessageList) TestUtil.asStringJson(
        result.getResponse().getContentAsString(), MessageList.class);
    assertNotNull(messages);
    assertNotNull(messages.getMessages());
    assertFalse(messages.getMessages().isEmpty());
  }

  /**
   * Test add course success.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testAddCourse() throws Exception {
    final Course course = new Course();
    course.setDescription("MBA");
    mockMvc
        .perform(
            post("/course/add.json").content(TestUtil.asJsonString(course))
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
    final Course course = new Course();
    course.setDescription("MCA");
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
    final Course course = new Course();
    course.setId(1);
    course.setDescription("MCA");
    mockMvc
        .perform(
            put("/course/update/{id}.json", 1)
                .content(TestUtil.asJsonString(course))
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
    final Course course = new Course();
    course.setId(5);
    course.setDescription("MCA");
    mockMvc
        .perform(put("/course/update/{id}.json", 1)
            .content(TestUtil.asJsonString(course))
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
    mockMvc.perform(delete("/course/delete/{id}", 1))
        .andExpect(status().isOk());
  }

  /**
   * Test delete course not found.
   * @throws Exception
   *           Exception
   */
  @Test
  public final void testDeleteCourseNotFound() throws Exception {
    mockMvc.perform(delete("/course/delete/{id}", 5))
        .andExpect(status().isNotFound());
  }
}
