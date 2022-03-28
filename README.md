# kimetsuStore-api
# Iniciando o projeto
### Tecnologias
#### 1. Java SDK 17 
<https://docs.microsoft.com/pt-br/java/openjdk/download>
#### 2. Maven 
<https://maven.apache.org/download.cgi>
#### 3. Docker <https://www.docker.com/products/docker-desktop>
### Instalando o docker no windows 💻
###### Abra o prompt como administrador e digite:
```
$ wsl --install
```
## Instale o Docker
<https://www.docker.com/products/docker-desktop>

### Executando o Projeto 💻

#### Suba o banco com o docker
###### Na raiz do projeto digite
```
$ docker-compose up -d
```
#### Subir aplicação com maven
```
$ ./mvnw spring-boot:run
```


# Team Style Guide - Casa do código
### 1. Criação de branch:
		Crie a branch baseada no número do seu card no Trello

Código da tarefa + / + nome-do-card
```
US0xx/nome-do-card
```

**Exemplo**

```
US004/Home-Page-Code
```
<br>

**\*OBSERVAÇÃO: Cuide para sempre criar a branch à partir da branch de desenvolvimento**
<br>
## 2. Commit:
<br>
O commit deve constar o nome do arquivo a modificação que foi realizada, caso tenha modificação em mais de um arquivo, se possível adicionar ao commit. Caso a mensagem seja muito longa fazer o commit e modificiar a mensagem manualmente diretamente no GitLab

<br>

**Exemplo**
<br>

$ git commit -m "Readme: adicionado Style Guides"
<br>
$ git commit -m "Autor-AutorService: Criado model e Service do autor"
<br>

    O merge para main deverá ser realizado ao final da sprint 
## 3. Padrões de escrita de código

- Apenas os termos relacionados ao negócio devem ser mantidos em *PT-BR, sem abreviações.* Verbos e demais termos não relacionados ao negócio, devem ser mantidos em *EN-US*.

**Exemplo**

```Java
...
public List<DocumentoDTO> findAllDocumentos() {
    return this.service.findAllDocumentos();
}
...
public void audit(final User usuario) {  
    auditConta(contaService.findByUser(usuario);  
}
...
```
<br>

- Usar Optionals até a camada de controllers

**Exemplo**

```Java

//No service
	@Override
	public Optional<CartaInicio> findById(UUID id) throws DataAccessException {
		return cartaInicioRepository.findById(id);
	}

//No Controller
@GetMapping("/{id}")
    public ResponseEntity<CartaInicio> findCartaInicio(@PathVariable UUID id){
    	
    	Optional<CartaInicio> cartaInicio;
    	cartaInicio=cartaService.findById(id);
    	
    	if (cartaInicio.isEmpty()) {
    		return new ResponseEntity<CartaInicio> (HttpStatus.NOT_FOUND);		
		}
    	return new ResponseEntity<CartaInicio>(cartaInicio.get(),HttpStatus.OK);
    }
```

- Usar @NonNull ao invés de usar dentro do @Column a propriedade nullable = false

## 4. Nomeando métodos

Ao nomear um método, deve-se garantir que seu nome seja auto explicativo e que o verbo seja condizente com o que o método faz.

### Exemplos

### Find (findAll, findById, findOne)

O verbo *find* (do Inglês encontrar ou achar) deve ser empregado quando o intuito do método for **trazer informações ainda não presentes**, como por exemplo no caso de buscas em bancos de dados ou por acesso a serviços externos.

```Java
...
public List<CategoriaProjection> findAll() {
    return this.repository.findAll();  
}
...

```

### Get (getNom, getVlr, getDocumento)

O verbo *get* (do Inglês obter ou receber) deve ser empregado quando o intuito da função for **acessar informação já presentes**, como por exemplo para acessar propriedades de objetos ou realizar pequenas manipulações a alguma propriedade da classe.

```Java
...
public String getDescricao() {  
   return this.descricao;  
}
...

```

### Add

O verbo *add* (do Inglês adicionar ou acrescentar) deve ser empregado quando o intuito da função for **acrescentar algo a um objeto já existente**, como por exemplo para inserir um valor em uma lista ou a uma String.

```Java
...
public void addConteudoParaDescricao(final String conteudo) {
   this.descricao.append(content).append(QUEBRA_LINHA);  
}
...
```

### Build

O verbo *build* (do Inglês construir) deve ser empregado quando o intuito da função for **construir e criar um objeto**, geralmente complexo, como por exemplo para descrever uma função que gera uma instância usando o padrão Builder.

```Java
private Conta buildConta(String nome,TipoConta classificacao) {
    return  Conta.builder()
                    .classificacao(classificacao)
                    .titular(nome)
                    .build();        
}
```

### Set

O verbo *set* (do Inglês definir) deve ser empregado quando o intuito da função for **definir alguma propriedade de um objeto**.

```Java
...
public void setDescricao(Descricao descricao) {  
    this.descricao = descricao;
}
...
```

### To

O verbo *to* (do Inglês para) deve ser empregado quando o intuito da função for **converter um objeto**, como transformar um *DTO* em entidade e vice-versa.

```Java
...
private Conta toDTO(ContaRequest request) {
    return Conta.builder()
        .idDocumento(request.getIdDocumento())  
        .classificacao(request.getClassificacao())  
				// Construir todo o objeto
        .build();  
}
...
```

### Save

O verbo *save* (do Inglês salvar) deve ser empregado quando o intuito da função for **persistir um objeto na base**. O comportamento padrão de um *save* num controller, por exemplo, é:

- Se não receber um ID no *body* do request, criar um novo registro.
- Se receber um ID no *body* do request, atualizar o registro com o ID especificado.

O método save poderá ser usado para chamar métodos auxiliares de inserção (como *create*) ou edição (*update*).

```Java
@Override
public Conta save(ContaRequest request) {
    return isNull(request.getId()) ? insert(request) : update(request);
}
```

## 5. Testes unitários

Os testes unitários devem ser mantidos em *PT-BR*, garantindo que o nome do teste seja auto explicativo evitando obviedades.

### Pontos a serem observados

- Testar (e tratar) todas as condições (por exemplo: nulo, lista vazia, valores negativos ou zerados)
- Testar retornos exatos (por exemplo: comparar conteúdos e não tamanhos de listas)

### Exemplo

```Java
@Test
public void buscarMunicipiosPorEstadoTeste() {
    final String uf = "SP";
    final String nom = "SAO PAULO";
    final String nomExpected = "Sao Paulo";
	
    final MunicipioDto municipio = MunicipioDto.builder()
    	.codigo("007388")
    	.uf(uf)
    	.nom(nom)
    	.build();
	
	when(localizacaoAcesso.buscarMunicipiosPorEstado(anyString()))
		.thenReturn(List.of(municipio));
	
	final List<MunicipioDto> result = localizacaoService.buscarMunicipiosPorEstado(uf);
	
	assertThat(result)
	    .describedAs("Verifica se a lista de municípios tem a quantidade de registros esperada [%s]", List.of(municipioDto).size())
	    .hasSameSizeAs(Collections.singleton(municipio));
	
	assertThat(result)
	    .describedAs("Verifica se o nome do município está com a formatação esperada init-cap [%s]", nomExpected)
	    .extracting(MunicipioDto::getNom)
	    .containsOnly(nomeExpected);
}
```
