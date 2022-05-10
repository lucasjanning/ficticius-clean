package com.lucasJanning.ficticiusclean.controller;

import com.lucasJanning.ficticiusclean.model.CombustivelModel;
import com.lucasJanning.ficticiusclean.model.VeiculoModel;
import com.lucasJanning.ficticiusclean.service.CalculoCombustivelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CalculoCombustivelController {

    @Autowired
    CalculoCombustivelService calculoCombustivelService;

    @PostMapping(path = "/novoVeiculo")
    public ResponseEntity<VeiculoModel> novoVeiculo(@RequestBody VeiculoModel veiculo){
        return ResponseEntity.ok().body(calculoCombustivelService.novoVeiculo(veiculo));
    }

    @PostMapping(path = "/novosVeiculos")
    public ResponseEntity<List<VeiculoModel>> novoVeiculo(@RequestBody List<VeiculoModel> veiculos){
        return ResponseEntity.ok().body(calculoCombustivelService.novosVeiculos(veiculos));
    }

    @GetMapping(path = "/previsaoGastoCombustivel")
    public List<CombustivelModel> previsaoGastoCombustivel(
            @RequestParam("precoGasolina") Double precoCombustivel,
            @RequestParam("kmCidade") Double kmCidade,
            @RequestParam("kmRodovia") Double kmRodovia
            ){
        return calculoCombustivelService.previsaoGastoCombustivel(precoCombustivel, kmCidade, kmRodovia);
    }
}
