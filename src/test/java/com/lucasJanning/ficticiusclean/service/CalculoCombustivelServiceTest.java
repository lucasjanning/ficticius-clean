package com.lucasJanning.ficticiusclean.service;

import com.lucasJanning.ficticiusclean.model.CombustivelModel;
import com.lucasJanning.ficticiusclean.model.VeiculoModel;
import com.lucasJanning.ficticiusclean.repository.CalculoCombustivelRepository;
import com.lucasJanning.ficticiusclean.service.CalculoCombustivelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculoCombustivelServiceTest {

    @MockBean
    CalculoCombustivelRepository calculoCombustivelRepository;

    @Autowired
    CalculoCombustivelService calculoCombustivelService;

    @Test
    public void novoVeiculoTest(){
        VeiculoModel veiculo = criarVeiculo(1);

        Mockito.when(calculoCombustivelRepository.save(veiculo)).thenReturn(veiculo);

        calculoCombustivelService.novoVeiculo(veiculo);

        Mockito.verify(calculoCombustivelRepository, Mockito.times(1)).save(ArgumentMatchers.any(VeiculoModel.class));
    }

    @Test
    public void novosVeiculosTest(){
        List<VeiculoModel> veiculos = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            veiculos.add(criarVeiculo(i));
        }

        Mockito.when(calculoCombustivelRepository.saveAll(veiculos)).thenReturn(veiculos);

        calculoCombustivelService.novosVeiculos(veiculos);

        Mockito.verify(calculoCombustivelRepository, Mockito.times(1)).saveAll(ArgumentMatchers.anyList());
    }

    @Test
    public void calculaValoresTest(){
        List<VeiculoModel> veiculos = new ArrayList<>();

        VeiculoModel veiculo = criarVeiculo(1);

        veiculos.add(criarVeiculo(1));

        CombustivelModel combustivelModel = new CombustivelModel();

        combustivelModel.setNome(veiculo.getNome());
        combustivelModel.setMarca(veiculo.getMarca());
        combustivelModel.setModelo(veiculo.getModelo());
        combustivelModel.setAno(veiculo.getDataFabricacao().getYear());
        combustivelModel.setCombustivelGasto(4.0);
        combustivelModel.setValorGasto(20.0);

        Mockito.when(calculoCombustivelRepository.findAll()).thenReturn(veiculos);

        CombustivelModel combustivelModelReturn = calculoCombustivelService.previsaoGastoCombustivel(5.0, 20.0, 30.0).get(0);

        Assertions.assertEquals(combustivelModel.getNome(), combustivelModelReturn.getNome());
        Assertions.assertEquals(combustivelModel.getMarca(), combustivelModelReturn.getMarca());
        Assertions.assertEquals(combustivelModel.getModelo(), combustivelModelReturn.getModelo());
        Assertions.assertEquals(combustivelModel.getAno(), combustivelModelReturn.getAno());
        Assertions.assertEquals(combustivelModel.getCombustivelGasto(), combustivelModelReturn.getCombustivelGasto());
        Assertions.assertEquals(combustivelModel.getValorGasto(), combustivelModelReturn.getValorGasto());
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
