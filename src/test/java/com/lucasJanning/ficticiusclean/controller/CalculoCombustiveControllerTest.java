package com.lucasJanning.ficticiusclean.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasJanning.ficticiusclean.model.CombustivelModel;
import com.lucasJanning.ficticiusclean.model.VeiculoModel;
import com.lucasJanning.ficticiusclean.service.CalculoCombustivelService;
import netscape.javascript.JSObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculoCombustiveControllerTest {

    @MockBean
    CalculoCombustivelService calculoCombustivelService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void novoVeiculoTest() throws Exception {
        VeiculoModel veiculo = criarVeiculo(1);

        mockMvc.perform(post("/api/novoVeiculo")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(veiculo)))
                .andExpect(status().isOk());
    }

    @Test
    public void novosVeiculosTest() throws Exception {
        List<VeiculoModel> veiculos = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            veiculos.add(criarVeiculo(i));
        }

        mockMvc.perform(post("/api/novosVeiculos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(veiculos)))
                .andExpect(status().isOk());
    }

    @Test
    public void previsaoGastoCombustivelTest() throws Exception {
        List<CombustivelModel> combustivelModelList = new ArrayList<>();
        CombustivelModel combustivelModel = new CombustivelModel();

        combustivelModel.setNome("Veiculo A");
        combustivelModel.setMarca("Marca B");
        combustivelModel.setModelo("Modelo C");
        combustivelModel.setAno(LocalDate.now().getYear());
        combustivelModel.setCombustivelGasto(4.0);
        combustivelModel.setValorGasto(20.0);

        combustivelModelList.add(combustivelModel);

        Mockito.when(calculoCombustivelService.previsaoGastoCombustivel(5.00, 10.00, 15.00)).thenReturn(combustivelModelList);

        mockMvc.perform(get("/api/previsaoGastoCombustivel?precoGasolina=5.00&kmCidade=10.00&kmRodovia=15.00").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(combustivelModelList)));
    }

    public VeiculoModel criarVeiculo(int i){
        VeiculoModel veiculo = new VeiculoModel();

        veiculo.setId(new Random().nextLong());
        veiculo.setNome("Veiculo "+i);
        veiculo.setMarca("Marca "+i);
        veiculo.setModelo("Modelo "+i);
        veiculo.setDataFabricacao(LocalDate.now());
        veiculo.setConsumoKmCidade(10.0);
        veiculo.setConsumoKmRodovia(15.0);

        return veiculo;
    }
}
