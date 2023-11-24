## Vídeo de apresentação
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

3. Substitua `localhost:8080` pelo link de produção da API nos endpoints. Mantenha o que estiver após `8080/`. Por exemplo:
   - De: `localhost:8080/usuarios/registrar`
   - Para: `https://reloadcare-rm88383-v2.azurewebsites.net/api/usuarios/registrar`

4. Comece registrando um usuário:
   - Endpoint: `https://reloadcare-rm88383-v2.azurewebsites.net/api/users/registrar`
   - Método: `POST`
   - Corpo da Requisição (JSON):
     ```json
     {
       "nome": "Nome do Usuário",
       "email": "user@email.com",
       "senha": "senha123"
     }
     ```

5. Após o registro, faça login para obter um token:
   - Endpoint: `https://reloadcare-rm88383-v2.azurewebsites.net/api/users/login`
   - Método: `POST`
   - Corpo da Requisição (JSON):
     ```json
     {
       "email": "user@email.com",
       "senha": "senha123"
     }
     ```

6. Copie o token recebido.

7. Para consumir endpoints privados, adicione o token na área "Bearer Token" no Postman.

8. Para acessar endpoints que exigem o ID do usuário ou empresa, inclua o ID no final da URL. Por exemplo:
   - `https://reloadcare-rm88383-v2.azurewebsites.net/api/users/12`

Lembre-se de adaptar as instruções conforme necessário para refletir os detalhes específicos da API Spring Boot.


# ReloadCare™ Endpoints

- Usuários
  - [POST](#registrando-um-novo-usuário)
  - [GET ALL](#encontrando-todos-os-usuários)
  - [GET ID](#detalhes-do-usuário)
  - [PUT](#atualizando-usuário)
  - [DELETE](#apagando-usuário)
  
- Formulário médico
  - [POST](#registrando-nova-empresa-com-id-do-usuário)


# Usuários
## Registrando um novo usuário

`POST` - https://reloadcare-rm88383-v2.azurewebsites.net/api/users/registrar

**Campos da requisição**

| campo | tipo   | obrigatório | descrição                 |
| ----- | ------ | ----------- | ------------------------- |
| nome  | string | sim         | Nome completo do usuário. |
| email | string | sim         | E-mail do usuário.        |
| senha | string | sim         | Senha do usuário.         |

**Exemplos corpo da requisição**

```js
{
    nome: "Douglas Welber",
    email: 'user@gmail.com',
    senha: "123456789"
}
```

**Respostas**

| código | descrição                       |
| ------ | ------------------------------- |
| 201    | Usuário registrado com sucesso. |
| 401    | Campos inválidos.               |

---

## Encontrando todos os usuários

`GET` https://sup-rm88383.azurewebsites.net/api/users

**Exemplo corpo da requisição (retorno)**

```js

{
    id: 1
    nome: "Douglas Welber",
    email: 'user@gmail.com',
    senha: "123456789"
}
```

---

## Detalhes do usuário

`GET` - https://reloadcare-rm88383-v2.azurewebsites.net/api/users/{id}

**Exemplo corpo da requisição (retorno)**

```js
{
  "nome": "Felipe Jardim Aguiar Santos",
  "email": "felipejardim@gmail.com",
  "senha": "123456789"
}
```

**Respostas**

| código | descrição                                   |
| ------ | ------------------------------------------- |
| 201    | Detalhes do usuário resgatados com sucesso. |
| 401    | Campos inválidos.                           |

---

## Atualizando usuário

`PUT` - https://reloadcare-rm88383-v2.azurewebsites.net/api/users/{id}

**Exemplo corpo da requisição**

```js
{
  "nome": "Felipe Jardim Aguiar Santos",
  "email": "felipejardim@gmail.com",
  "senha": "123456789"
}
```

| código | descrição                       |
| ------ | ------------------------------- |
| 201    | Usuário atualizado com sucesso. |
| 401    | Campos inválidos.               |

---

## Apagando usuário

`DELETE` https://reloadcare-rm88383-v2.azurewebsites.net/api/users/{id}

**Respostas**

| código | descrição                    |
| ------ | ---------------------------- |
| 201    | Usuário apagado com sucesso. |
| 401    | Campos inválidos.            |

---

# Formulário Health
## Preenchendo formulário de acompanhamento médico

`POST` - https://reloadcare-rm88383-v2.azurewebsites.net/api/

**Campos da requisição**

| campo   | tipo    | obrigatório | descrição                             |
| ------- | ------- | ----------- | ------------------------------------- |
| user | decimal | sim         | Id do usuário para o link da empresa. |
| email   | string  | sim         | E-mail corporativo do usuário.        |
| nome    | string  | sim         | Nome da empresa.                      |
| cargo   | string  | sim         | Cargo que o usuário ocupa na empresa. |

**Exemplo corpo da requisição**

```js
{
    user: 31,
    nome: "Apple",
    email: "Apple@apple.com",
    cargo: "Gerente",
}
```
---
