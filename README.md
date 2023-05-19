# Imigo
App que conecta empresas e clientes na jornada pós-venda.

Com o Imigo, o cliente pode receber suporte em tempo real, como num processo de devolução. Também pode dar feedbacks a empresa, aproximando-se assim da marca.

O Imigo será capaz de avaliar o histórico de compras e de conversas do cliente, para melhorar e personalizar ofertas, além de descobrir o melhor jeito de tratar o consumidor. Com o histórico ainda será possibilitado informar quando um produto estará no prazo de validade ou até mesmo qual produto repor.
Outras funcionalidades do pós-compra feita pelo Imigo será acompanhar um produto depois da venda, servindo para dar dicas ao cliente e ao produto. Também o Imigo pode ajudar o cliente na primeira interação de cliente e empresa, pavimentando um caminho de fidelização do cliente.
Além disso, o projeto auxiliará consumidores na prestação de serviços de assistência técnica, o qual conectará de forma objetiva e fará um processo de triagem com as unidades mais confiáveis e autorizadas pelas marcas parceiras.

## Visão geral
![Diagram](https://i.imgur.com/26UAQYk.png)



## Endpoints

* Cliente
    * Cadastrar
    * Atualizar Cliente
    * Excluir Cliente
    * Listar todos
    
* Empresa
    * Cadastrar
    * Atualizar Empresa
    * Excluir Empresa
    * Listar todos

* Endereço
    * Cadastrar
    * Atualizar Endereço
    * Excluir Endereço
    * Listar todos
    
* Suporte técnico
    * Cadastrar
    * Atualizar Suporte técnico
    * Excluir Suporte técnico
    * Listar todos

---

### Cadastrar Cliente

`POST` /api/cliente

**Campo de Requisição**

campo | tipo | obrigatório | descrição
|---|---|:---:|---|
nome | String | sim | Informa o nome do Cliente
senha | String | sim | Informa a senha cadastrada
email | String | sim | Informa o email do Cliente
genero | String | não | Informa o genero do Cliente
cpf | String | sim | Informa o cpf do Cliente

**Exemplo de Campo de Requisição**

```js
    "nome": "Silvio",
    "senha": "123456",
    "email": "silvio@gmail.com"
    "genero": ""
    "cpf": "555.555.555-55"
```

**Código de Resposta**

código | descrição
|---|---
200 | Ok
201 | Cliente criado com sucesso
400 | Campos enviados são inválidos

---

### Mostrar Cliente

`GET` /api/cliente/{id}

```js
    "nome": "Silvio",
    "senha": "123456",
    "email": "silvio@gmail.com",
    "genero": "",
    "cpf": "555.555.555-55"
```

---

### Cadastrar Empresa

`POST` /api/empresa

**Campo de Requisição**

campo | tipo | obrigatório | descrição
|---|---|:---:|---|
nome | String | sim | Informa o nome da empresa
email | String | sim | Informa o email da empresa cadastrada
cnpj_inscricao | String | sim | Informa o corpo do CNPJ da empresa



**Exemplo de Campo de Requisição**

```js
    "nome": "Sentio Celulares",
    "cnpj_inscricao": "41.389.072",
    "email": "contato@sentio.com.br"
```

**Código de Resposta**

código | descrição
|---|---
200 | Ok
201 | Empresa criada com sucesso
400 | Campos enviados são inválidos

---

### Mostrar Empresa

`GET` /api/empresa/{id}

```js
    "nome": "Sentio Celulares",
    "cnpj_inscricao": "41.389.072",
    "email": "contato@sentio.com.br"
```

---

### Cadastrar Endereço

`POST` /api/endereço

**Campo de Requisição**

campo | tipo | obrigatório | descrição
|---|---|:---:|---|
numero | Double | sim | Informa o número do endereço
cep | String | sim | Informa o CEP do endereço
ponto_referencia | String | não | Informa descrição sobre ponto de referência
logradouro | String | sim | Informa o nome do logradouro
bairro | String | sim | Informa o Bairro
cidade | String | sim | Informa a Cidade
estado | String | sim | Informa o Estado
regiao | String | sim | Informa a região


**Exemplo de Campo de Requisição**

```js
    "numero": 105,
    "cep": "48400-000",
    "ponto_referencia": null,
    "logradouro": "Rua Conselheiro Moreira de Barros",
    "bairro": "Santana",
    "cidade": "São Paulo",
    "estado": "São Paulo",
    "regiao": "Grande São Paulo"
    
```

**Código de Resposta**

código | descrição
|---|---
200 | Ok
201 | Endereço criado com sucesso
400 | Campos enviados são inválidos

---

### Mostrar Endereço

`GET` /api/endereco/{id}

```js
    "numero": 105,
    "cep": "48400-000",
    "ponto_referencia": null,
    "logradouro": "Rua Conselheiro Moreira de Barros",
    "bairro": "Santana",
    "cidade": "São Paulo",
    "estado": "São Paulo",
    "regiao": "Grande São Paulo"
```

---

### Cadastrar Suporte técnico

`POST` /api/suporte

**Campo de Requisição**

campo | tipo | obrigatório | descrição
|---|---|:---:|---|
descricao | String | sim | Informa a descrição do suporte técnico
data_inicio | LocalDate | sim | Informa a data de inicio
data_fim | LocalDate | sim | Informa a data final

**Exemplo de Campo de Requisição**

```js
    "descricao": "Troca de bateria e módulo de som",
    "data_inicio": "15/04/2023",
    "data_fim": "23/04/2023"
```

**Código de Resposta**

código | descrição
|---|---
200 | Ok
201 | Suporte criado com sucesso
400 | Campos enviados são inválidos

---

### Mostrar Suporte

`GET` /api/suporte/{id}

```js
    "descricao": "Troca de bateria e módulo de som",
    "data_inicio": "15/04/2023",
    "data_fim": "23/04/2023"
```
