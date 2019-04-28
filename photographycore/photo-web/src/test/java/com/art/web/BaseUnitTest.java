package com.art.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class BaseUnitTest {
    @Autowired
    protected WebApplicationContext context;
    @Autowired
    protected MockMvc mockMvc;
    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void test()
    {
    }

}
