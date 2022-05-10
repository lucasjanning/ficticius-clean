package com.lucasJanning.ficticiusclean.service;

import com.lucasJanning.ficticiusclean.model.CombustivelModel;
import com.lucasJanning.ficticiusclean.model.VeiculoModel;
import com.lucasJanning.ficticiusclean.repository.CalculoCombustivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CalculoCombustivelService {

    @Autowired
    CalculoCombustivelRepository calculoCombustivelRepository;

    public VeiculoModel novoVeiculo(VeiculoModel veiculo){
        return calculoCombustivelRepository.save(veiculo);
    }

    public List<VeiculoModel> novosVeiculos(List<VeiculoModel> veiculos){
        return calculoCombustivelRepository.saveAll(veiculos);
    }

    public List<CombustivelModel> previsaoGastoCombustivel(Double precoCombustivel, Double kmCidade, Double kmRodovia){
        List<VeiculoModel> veiculos = calculoCombustivelRepository.findAll();
        List<CombustivelModel> combustivelModelList = new ArrayList<>();

        for(VeiculoModel veiculo : veiculos){
            combustivelModelList.add(calculaValores(veiculo, precoCombustivel, kmCidade, kmRodovia));
        }

        combustivelModelList.sort(Comparator.comparing(CombustivelModel::getValorGasto));

        return  combustivelModelList;
    }

    public CombustivelModel calculaValores(VeiculoModel veiculo, Double precoCombustivel, Double kmCidade, Double kmRodovia){
        CombustivelModel combustivelModel = new CombustivelModel();

        combustivelModel.setNome(veiculo.getNome());
        combustivelModel.setMarca(veiculo.getMarca());
        combustivelModel.setModelo(veiculo.getModelo());
        combustivelModel.setAno(veiculo.getDataFabricacao().getYear());
        combustivelModel.setCombustivelGasto(calculaCombustivelGasto(veiculo.getConsumoKmCidade(), veiculo.getConsumoKmRodovia(), kmCidade, kmRodovia));
        combustivelModel.setValorGasto(calculaValorGasto(combustivelModel.getCombustivelGasto(), precoCombustivel));

        return combustivelModel;
    }

    public Double calculaCombustivelGasto(Double veiculoKmCidade, Double veiculoKmRodovia, Double kmCidade, Double kmRodovia){
        return (kmCidade / veiculoKmCidade) + (kmRodovia / veiculoKmRodovia);
    }

    public Double calculaValorGasto(Double combustivelGasto, Double precoCombustivel){
        return precoCombustivel * combustivelGasto;
    }
}
