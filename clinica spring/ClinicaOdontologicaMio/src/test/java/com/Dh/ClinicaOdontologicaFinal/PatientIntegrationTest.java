package com.Dh.ClinicaOdontologicaFinal;

import com.Dh.ClinicaOdontologicaFinal.entities.Patient;
import com.Dh.ClinicaOdontologicaFinal.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientService patientService;

    @Test
    public void testFindByLastname() throws Exception {
        Patient patient1 = new Patient();
        patient1.setLastname("Perez");
        patient1.setName("Juan");
        patient1.setDni("12345");


        Patient patient2 = new Patient();
        patient2.setLastname("Perez");
        patient2.setName("Ana");
        patient2.setDni("67890");


        patientService.save(patient1);
        patientService.save(patient2);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/patient/lastname/Perez")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals("[{"id":1,"name":"Juan","lastname":"Perez","dni":"12345","dateofadmission":null,"address":null},{"id":2,"name":"Ana","lastname":"Perez","dni":"67890","dateofadmission":null,"address":null}]", content);
    }
}
