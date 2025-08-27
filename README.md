# CRUD - NARUTO 2

O crud – naruto 2 é uma refatoração da api que simula o anime naruto para o treinamento na empresa db, utiliza a arquitetura em camadas mvc. Segue abaixo descrições dos pacotes e classes. 


## MODEL
Classe Personagem: classe pai, é a única tabela no banco.

Classe NinjaDeNinjutsu: implementa a interface Ninja conforme seu jutsu 

Classe NinjaDeTaijutsu: implementa a interface Ninja conforme seu jutsu

Classe Jutsu: em associação com classe Personagem através de um map.

## INTERFACES
Ninja: possui dois métodos, usarJutsu e desviar, este recebe um personagem e um boolean se conseguiu desviar.

## DTO 

Classe PersonagemRequestDto: objeto de request com os atributos de personagem sem o id; 

Classe PersonagemResponseDto: objeto de response com os atributos de personagem sem o id.

JutsuDto: Os atributos da classe Jutsu sem id;

## MAPPER 

Interface PersonagemMapper: mapeia objetos da request para entity Personagem, da entity para response, lista de objetos da entity para response, atualiza Personagem e de personagem para as classes ninjas. 

## EXCEPTION 

- JutsuNaoEncontradoException(404): lançada quando jutsu não é encontrado; 

- PersonagemNaoEncontradoException(404): lançada quando personagem não é encontrado; 

## SERVICE 

	É a camada das regras de negócio 

- CriarPersonagem: recebe uma request, mapeia para entity,antes salva o Jutsu, salva no banco e retorna a response; 

- EditarPersonagem: recebe uma request e um id, busca o personagem,antes salva o Jutsu, atualiza e salva no banco e retorna a response; 

- DeletarPersonagem: recebe um id, busca o id no banco  e deleta. Não há retorno. 

- ListarPersonagens: busca todos os personagens no banco e retorna uma lista de response 

- AdicionarJutsu: recebe um id, e um Jutsu, salva o jutsu no banco,busca o id no banco e adiciona o jutsu ao personagem e salva no banco. Retorna boolean. 

- AumentarChakra: recebe um id, e uma quantidade, busca o id no banco e adiciona quantidade ao chakra personagem e salva no banco. Retorna o chakra. 

- UsarJutsu: recebe um id, verifica se o personagem tem ninjutsu ou taijutsu, então, transforma-o em um objeto das classes filhas e chama o método usarJutsu; Se não houver jutsu lança JutsuNaoEncontradoException; 

- desviar: recebe um id, verifica se o personagem tem genjutsu, ninjutsu ou taijutsu, então, transforma-o em um objeto das classes filhas e chama o método desviar; Se não houver jutsu lança JutsuNaoEncontradoException; 

## REPOSITORY 

Interface PersonagemRepository estende a JpaRepository.
Interface JutsuRepository estende a JpaRepository.

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

- Testes integrados completos e reais utilizando a anotação @springboottest, da camada controller até o banco. 

 
## FERRAMENTAS: 

- SPRINGBOOT 
- SPRING DATA JPA 
- DOCKER 
- POSTGRESQL 
- LOMBOK 
- MAPSTRUCT 
- MOCKITO 