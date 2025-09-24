# CRUD - NARUTO 2 - MONGODB e WIREMOCK

O crud – naruto 2 é uma refatoração da api que simula o anime naruto para o treinamento na empresa db, utiliza a arquitetura em camadas mvc. Segue abaixo descrições dos pacotes e classes. 
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

- ataque: permite que personagem ataque

- defesa: permite que o personagem se defenda

- derrota: permite a derrota do personagem  

## BANCO DE DADOS: 

- O banco utilizado foi Mongo community para produção e Mongo embedded para testes
-  Coleções dentro de outras coleções
- POSTGRES NO DOCKER: O Volume não foi persistido

## TESTES: 

- Testes unitários da camada service e controller  

- Testes integrados completos e reais utilizando a anotação @springboottest @MockMvc e @Wiremock, da camada controller até o banco. 

## O QUE É WIREMOCK?
- O Wiremock é uma ferramenta usada para simular api externa, para usá-la no projeto foi criada AldeiaClient com RestTemplate.
 
## FERRAMENTAS: 

- SPRINGBOOT
- MOCKMVC
- WIREMOCK
- SPRING DATA MONGO
- MONGODB
- MONGO EMBEDDED
- DOCKER 
- LOMBOK 
- MAPSTRUCT 
- MOCKITO 
