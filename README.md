> <h2 align="center"> Ficticius Clean - API </h2>



### Desafio back-end fluig

Desenvolver uma API para atender os problemas de consumo de combustível da empresa <b>Ficticius Clean</b>.

### Tecnologias utilizadas

* Java 11
* Spring Boot
* Spring Data JPA
* Mockito
* JUnit
* H2 Database

### Instalar e iniciar a aplicação

Primeiramente certifique-se que você possui essas tecnologias instaladas

* Java 11
* IntelliJ ou Eclipse
* Git

Após isso clone o projeto em sua workspace

```bash
$ git clone https://github.com/lucasjanning/ficticius-clean.git
```

Com o projeto aberto na sua IDE execute o Maven Goal e rode o seguinte comando

```bash
mvn clean install
```

Depois do build é só rodar a aplicação também pelo Maven Goal

```bash
mvn spring-boot:start
```

Pronto, o projeto estará rodando com o seguinte endereço

```bash
http://localhost:8080
```

### Como utilizar

#### Para cadastrar veículos existem duas formas

* #### Cadastrar um veiculo

Com o Postman dê um <b>POST</b> para o seguinte endereço

```bash
http://localhost:8080/api/novoVeiculo
```

Com o JSON

```bash
{
    "nome": "Uno",
    "marca": "Fiat",
    "modelo": "Milli",
    "dataFabricacao": "01-03-2010",
    "consumoKmCidade": 10.00,
    "consumoKmRodovia": 15.00
}
```

* #### Cadastrar veiculos em lote

Com o Postman dê um <b>POST</b> para o seguinte endereço

```bash
http://localhost:8080/api/novosVeiculos
```

Com o JSON

```bash
[
    {
        "nome": "Celta",
        "marca": "Chevrolet",
        "modelo": "1.4",
        "dataFabricacao": "06-11-2004",
        "consumoKmCidade": 12.25,
        "consumoKmRodovia": 15.15
    },
    {
        "nome": "Gol",
        "marca": "Volkswagen",
        "modelo": "G2",
        "dataFabricacao": "01-03-1996",
        "consumoKmCidade": 11.50,
        "consumoKmRodovia": 14.50
    }
]
```

#### Consultar gasto de combustível

Para consultar o gasto de combustível dê um <b>GET</b> no Postman no seguinte endereço

```bash
http://localhost:8080/api/previsaoGastoCombustivel?precoGasolina=5.00&kmCidade=10.00&kmRodovia=15.00
```

Feito isso será retornada uma lista em JSON com uma lista ordenada do carro mais econômico para o que mais gasta

---

Feito por <b>Lucas Janning</b>.

    
