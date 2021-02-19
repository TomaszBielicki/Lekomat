package com.example.ProjectLekomat;


import com.example.ProjectLekomat.controller.LockerController;
import com.example.ProjectLekomat.exception.IdNotFoundException;
import com.example.ProjectLekomat.model.locker.LockerResponse;
import com.example.ProjectLekomat.service.LockerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LockerController.class)
public class LockerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LockerService lockerService;
    private List<LockerResponse> lockerList = new ArrayList<>();

    @Test
    public void shouldReturnLockerById() throws Exception{
        lockerList.add( new LockerResponse(1234, true, "2021-02-15"));
        when(lockerService.findById(1L)).thenReturn(lockerList.get(0));
        RequestBuilder request = MockMvcRequestBuilders.get("/lock/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "   \"code\": 1234, \n" +
                        "   \"status\": true, \n" +
                        "   \"createAt\": 2021-02-15\n" +
                        "}")).andReturn();
    }

    @Test
    public void shouldNotReturnLockerByIdWhenNotFound() throws Exception{
        when(lockerService.findById(1L)).thenThrow(new IdNotFoundException(1));
        RequestBuilder request = MockMvcRequestBuilders.get("/lock/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andExpect(status().isNotFound())
                .andExpect(content().string("Id: 1 not found")).andReturn();
    }

    @Test
    public void shouldNotCreateLockerWhenCodeMissing() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.post("/lock")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"status\": true, \n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andExpect(status().isBadRequest()).andReturn();

    }

    @Test
    public void shouldNotCreateLockerWhenStatusMissing() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.post("/lock")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"code\": 1234, \n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andExpect(status().isBadRequest()).andReturn();

    }
    @Test
    public void shouldCreateLocker() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.post("/lock")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"code\": 1234, \n" +
                        "   \"status\": true \n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }
}
