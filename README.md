# ReloadCare™
O ReloadCare™ é um app criado para ajudar dependentes químicos, servindo de controle diário (em relação ao uso e estado físico/mental), auxiliando no dia a dia e no acompanhamento médico. 

O app registra informações preenchidas pelo usuário, por meio de um formulário, durante sua jornada para uma vida mais saudável (onde o médico também pode ter acesso, para acompanhar em tempor real) e o auxilia com sua interface amigável e intuitiva, oferecendo também um painel para o usuário do app receber dicas e orientações, visando melhorar suas condições físicas e mentais.

## Vídeos
### Vídeo de apresentação do app
colocaraqui
### Vídeo de realização do CRUD com POSTMAN, retornando dados em nuvem
https://youtu.be/dFcpAgpq7Is
### Vídeo de realização do CRUD pelo frontend, retornando dados em nuvem, mostrando Deploy e conexão entre API E front
colocaraqui

## Integrantes
rm94265 | Daniel Ferreira dos Santos - 2TDST <br/>
rm94269 | Douglas Welber - 2TDSS <br/>
rm88383 | Felipe Jardim - 2TDST<br/>
rm95749 | João Vitor Braz - 2TDST<br/>
rm94717 | Tarcisio Ferreira Couto - 2TDST<br/>

# Testando a API Spring Boot

## Pré-requisitos

Certifique-se de ter o Postman instalado no seu sistema. Você pode baixá-lo [aqui](https://www.postman.com/downloads/).

## Passos para Testar a API

1. Abra o Postman.

2. Importe as configurações da API usando o arquivo Postman fornecido no próprio repositório (localizado na raiz). Ele pode ser identificado como 'ReloadCare.postman_collection.json'.

3. Comece registrando um usuário:
   - Endpoint: `https://reloadcare-rm88383-v2.azurewebsites.net/api/usuarios/registrar`
   - Método: `POST`
   - Corpo da Requisição (JSON):
     ```json
     {
       "nome": "Nome do Usuário",
       "age": "25"
       "email": "user@email.com",
       "senha": "senha123"
     }
     ```

4. Após o registro, faça login para obter um token:
   - Endpoint: `https://reloadcare-rm88383-v2.azurewebsites.net/api/usuarios/login`
   - Método: `POST`
   - Corpo da Requisição (JSON):
     ```json
     {
       "email": "user@email.com",
       "senha": "senha123"
     }
     ```

5. Copie o token recebido.

6. Para consumir endpoints privados, adicione o token no campo "Token" localizado na aba "Authorization", no Postman (selecione "Bearer Token" no campo "Type")

7. Para acessar endpoints que exigem o ID do usuário ou empresa, inclua o ID no final da URL. Por exemplo:
   - `https://reloadcare-rm88383-v2.azurewebsites.net/api/usuarios/12`

Lembre-se de adaptar as instruções conforme necessário para refletir os detalhes específicos da API Spring Boot.


# ReloadCare™ Endpoints

- Usuários
  - [POST](#registrando-um-novo-usuário)
  - [GET ALL](#encontrando-todos-os-usuários)
  - [PUT](#atualizando-usuário)
  - [DELETE](#deletando-usuário)
  
- Formulário médico
  - [POST](#preenchendo-formulário-de-acompanhamento-médico)


# Usuários
## Registrando um novo usuário

`POST` - https://reloadcare-rm88383-v2.azurewebsites.net/api/usuarios/registrar

**Exemplos corpo da requisição**

```js
{
    "nome": "Felipe Jardim",
    "age": "21",
    "email": "fj@gmail.com",
    "senha": "123456"
}
```
---

## Encontrando todos os usuários

`GET` https://sup-rm88383.azurewebsites.net/api/usuarios

**Exemplo corpo da requisição (retorno)**

```js
{
    "nome": "Felipe Jardim",
    "age": "21",
    "email": "fj@gmail.com",
    "senha": "123456"
}
```
---

## Atualizando usuário

`PUT` - https://reloadcare-rm88383-v2.azurewebsites.net/api/usuarios/{id}

**Exemplo corpo da requisição**

```js
{
    "nome": "Felipe Carvalho",
    "age": "22",
    "email": "felipecarvalho@gmail.com",
    "senha": "12345678910"
}
```
---

## Deletando usuário

`DELETE` https://reloadcare-rm88383-v2.azurewebsites.net/api/usuarios/{id}

---

# Formulário Health
## Preenchendo formulário de acompanhamento médico

`POST` - https://reloadcare-rm88383-v2.azurewebsites.net/api/usuarios/{id}/health

**Campos da requisição**

| campo   | tipo    | obrigatório | descrição                             |
| ------- | ------- | ----------- | ------------------------------------- |
| health  | string | sim | Como o paciente se sente. |
| mentalHealth   | string  | sim | Como o paciente se sente mentalmente. |
| substances    | string  | sim | Substâncias utilizadas no dia. |
| substanceFrequencies | string | sim | Frequência que ela vem sendo usada. |
| goals | string | sim | Objetivos do paciente.|

**Exemplo corpo da requisição**

```js
{
    "health": "Estável",
    "mentalHealth": "Feliz",
    "substances": "Cafeína",
    "substanceFrequencies": "1 vez por dia",
    "goals": "Diminuir cafeína"
}
```
