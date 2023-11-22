## Vídeo de apresentação
https://www.youtube.com/watch?v=uOHk59Dc1kM

## Integrantes
rm94265 | Daniel Ferreira dos Santos - 2TDST <br/>
rm94269 | Douglas Welber - 2TDSS <br/>
rm88383 | Felipe Jardim - 2TDST<br/>
rm94717 | Tarcisio Ferreira Couto - 2TDST<br/>

# Testando a API Spring Boot

## Pré-requisitos

Certifique-se de ter o Insomnia instalado no seu sistema. Você pode baixá-lo [aqui](https://insomnia.rest/download).

## Passos para Testar a API

1. Abra o Insomnia.

2. Importe as configurações da API usando o arquivo Insomnia fornecido no próprio repositório. Ele pode ser identificado como 'Insomnia_2023-09-10.json'.

3. Substitua `localhost:8080` pelo link de produção da API nos endpoints. Mantenha o que estiver após `8080/`. Por exemplo:
   - De: `localhost:8080/users/registrar`
   - Para: `https://sup-rm88383.azurewebsites.net/api/users/registrar`

4. Comece registrando um usuário:
   - Endpoint: `https://sup-rm88383.azurewebsites.net/api/users/registrar`
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
   - Endpoint: `https://sup-rm88383.azurewebsites.net/api/users/login`
   - Método: `POST`
   - Corpo da Requisição (JSON):
     ```json
     {
       "email": "user@email.com",
       "senha": "senha123"
     }
     ```

6. Copie o token recebido.

7. Para consumir endpoints privados, adicione o token na área "Bearer Token" no Insomnia.

8. Para acessar endpoints que exigem o ID do usuário ou empresa, inclua o ID no final da URL. Por exemplo:
   - `https://sup-rm88383.azurewebsites.net/api/users/12`

Lembre-se de adaptar as instruções conforme necessário para refletir os detalhes específicos do seu projeto e da API Spring Boot.


# SUP! Endpoints

- Usuários
  - [POST](#registrando-um-novo-usuário)
  - [GET ALL](#encontrando-todos-os-usuários)
  - [GET ID](#detalhes-do-usuário)
  - [PUT](#atualizando-usuário)
  - [DELETE](#apagando-usuário)
  
- Empresa
  - [POST](#registrando-nova-empresa-com-id-do-usuário)
  - [GET ALL](#encontre-todas-empresas)
  - [GET ID](#detalhes-da-empresa)
  - [PUT](#atualizando-empresa)
  - [DELETE](#apagando-empresa)


# Usuários
## Registrando um novo usuário

`POST` - https://sup-rm88383.azurewebsites.net/api/users/registrar

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

```js
{
  "nome": "Felipe Jardim",
  "email": "felipejardim@gmail.com",
  "senha": "123456"
}
```

```js
{
    nome: "Manuel Gomes",
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
[
    {
    id: 1
    nome: "Douglas Welber",
    email: 'user@gmail.com',
    senha: "123456789"
    },
    {
    "id": 31,
    "nome": "Felipe Jardim",
    "email": "felipejardim@gmail.com",
    "senha": "123456"
    },
    {
    id: 00256
    nome: "Manuel Gomes",
    email: 'user@gmail.com',
    senha: "123456789"
    }
]
```

---

## Detalhes do usuário

`GET` - https://sup-rm88383.azurewebsites.net/api/users/{id}

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

`PUT` - https://sup-rm88383.azurewebsites.net/api/users/{id}

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

`DELETE` https://sup-rm88383.azurewebsites.net/api/users/{id}

**Respostas**

| código | descrição                    |
| ------ | ---------------------------- |
| 201    | Usuário apagado com sucesso. |
| 401    | Campos inválidos.            |

---

# Empresa
## Registrando nova empresa com ID do usuário

`POST` - https://sup-rm88383.azurewebsites.net/api/users/addCompany/1

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

```js
{
    user: 2,
    nome: "Coca Cola",
    email: "CocaCola@contato.com",
    cargo: "Vendedor",
}
```

```js
{
    user: 3,
    nome: "Amazon",
    email: "Amazon@amazoncontato.com",
    cargo: "CTO",
}
```

**Respostas**

| código | descrição                       |
| ------ | ------------------------------- |
| 201    | Empresa registrada com sucesso. |
| 401    | Campos inválidos.               |

---

## Encontre todas empresas

`GET` https://sup-rm88383.azurewebsites.net/api/empresas

**Exemplo corpo da requisição (retorno)**

```js
[
  {
    user: 31,
    nome: "Apple",
    email: "Apple@apple.com",
    cargo: "Gerente",
  },
  {
    user: 2,
    nome: "Coca Cola",
    email: "CocaCola@contato.com",
    cargo: "Vendedor",
  },
  {
    user: 3,
    nome: "Amazon",
    email: "Amazon@amazoncontato.com",
    cargo: "CTO",
  },
];
```

---

## Detalhes da empresa

`GET` - https://sup-rm88383.azurewebsites.net/api/empresas/{id}

**Exemplo corpo da requisição (retorno)**

```js
    {
        id: 1
        nome: "Amazon Brasil",
        email: "Amazon@amazoncontato.com",
        cargo: "CTO"
    }
```

**Respostas**

| código | descrição                                   |
| ------ | ------------------------------------------- |
| 201    | Detalhes da empresa resgatados com sucesso. |
| 401    | Campos inválidos.                           |

---

## Atualizando Empresa

`PUT` - https://sup-rm88383.azurewebsites.net/api/empresas/{id}

**Exemplo corpo da requisição**

```js
    {
        id: 31
        nome: "Amazon Brasil",
        email: "Amazon@amazoncontato.com",
        cargo: "CTO"
    }
```

| código | descrição                    |
| ------ | ---------------------------- |
| 201    | Empresa editada com sucesso. |
| 401    | Campos inválidos.            |

---

## Apagando empresa

`DELETE` https://sup-rm88383.azurewebsites.net/api/empresas/{id}

**Respostas**

| código | descrição                     |
| ------ | ----------------------------- |
| 201    | Empresa deletada com sucesso. |
| 401    | Campos inválidos              |

---
