package com.example.ProjectLekomat;

import com.example.ProjectLekomat.controller.MedicineController;
import com.example.ProjectLekomat.exception.IdNotFoundException;
import com.example.ProjectLekomat.model.medicine.MedicineResponse;
import com.example.ProjectLekomat.service.MedicineService;
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
@WebMvcTest(MedicineController.class)
public class MedicineControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MedicineService medicineService;
    private List<MedicineResponse> medicineList = new ArrayList<>();

    @Test
    public void shouldReturnMedicineById() throws Exception{
        medicineList.add( new MedicineResponse("gripex"));
        when(medicineService.findById(1L)).thenReturn(medicineList.get(0));
        RequestBuilder request = MockMvcRequestBuilders.get("/medicine/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "   \"name\": gripex\n" +
                        "}")).andReturn();
    }

    @Test
    public void shouldNotReturnMedicineByIdWhenNotFound() throws Exception{
        when(medicineService.findById(1L)).thenThrow(new IdNotFoundException(1));
        RequestBuilder request = MockMvcRequestBuilders.get("/medicine/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andExpect(status().isNotFound())
                .andExpect(content().string("Id: 1 not found")).andReturn();
    }

    @Test
    public void shouldCreateMedicine() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.post("/medicine")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"name\": gripex\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }

}
