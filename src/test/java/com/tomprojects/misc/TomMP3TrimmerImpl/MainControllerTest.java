package com.tomprojects.misc.TomMP3TrimmerImpl;

import com.tomprojects.misc.TomMP3TrimmerImpl.controller.MainController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MainController.class)
public class MainControllerTest {

  List<String> expectedList = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
  @Autowired private MockMvc mockMvc;

  @Test
  public void main() throws Exception {
    ResultActions resultActions =
        mockMvc
            .perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("main"))
            .andExpect(model().attribute("message", equalTo("Tom")))
            .andExpect(model().attribute("tasks", expectedList))
            .andExpect(content().string(containsString("Hello, Tom")));

    MvcResult mvcResult = resultActions.andReturn();
    ModelAndView mv = mvcResult.getModelAndView();
    //
  }

  // Get request with Param
  @Test
  public void hello() throws Exception {
    mockMvc
        .perform(get("/hello").param("name", "I Love Kotlin!"))
        .andExpect(status().isOk())
        .andExpect(view().name("main"))
        .andExpect(model().attribute("message", equalTo("I Love Kotlin!")))
        .andExpect(content().string(containsString("Hello, I Love Kotlin!")));
  }
}
