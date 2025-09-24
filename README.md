# CRUD - NARUTO

O crud – naruto é uma api que simula o anime naruto para o treinamento na empresa db, utiliza a arquitetura em camadas mvc. Segue abaixo descrições dos pacotes e classes. 

## FUNCIONALIDADES

- CriarPersonagem: permite criar um personagem

- EditarPersonagem: permite editar todo o personagem de uma vez só

- DeletarPersonagem: permite deletar o personagem

- ListarPersonagens: retorna todos os personagens numa lista

- AdicionarJutsu: permite adicionar um jutsu a lista de jutsus do personagem

- AumentarChakra: permite aumentar o chakra do personagem

- UsarJutsu: permite usar o jutsu do personagem

- desviar: permite desviar do jutsu do personagem

## CONTROLLER 

- POST api/personagem: Cria personagem, 201 

- PUT api/personagem/{id}: Edita personagem, 200 

- DELETE api/personagem/{id}: Deleta personagem, 204 

- GET api/personagem: listar personagens, 200 

- PUT api/personagem/{id}/adiciona-jutsu: adicionar jutsu ao personagem, 200 

- PUT api/personagem/{id}/aumenta-chakra: aumentar chakra do personagem, 200 

- GET api/personagem/{id}/usa-jutsu: usar jutsu do personagem, 200 

- GET api/personagem/{id}/desvia: desviar do personagem, 200 

## BANCO DE DADOS: 

- Apenas uma tabela 

- POSTGRES NO DOCKER: O Volume foi persistido numa pasta local

## TESTES: 

- Testes unitários da camada service e controller  

- Testes integrados completos e reais utilizando a anotação @springboottest e @MockMvc, da camada controller até o banco. 

 
## FERRAMENTAS: 

- SPRINGBOOT
- MOCKMVC
- SPRING DATA JPA 
- DOCKER 
- POSTGRESQL 
- LOMBOK 
- MAPSTRUCT 
- MOCKITO 

## DESENVOLVIDO POR:
- Lisleny Torquato
