Funcionalidades
Conversão entre diversas moedas internacionais
Taxas de câmbio em tempo real através da ExchangeRate-API
Interface de usuário em português
Suporte para moedas principais incluindo Real Brasileiro (BRL)
Opções de conversão personalizadas
Formato de exibição claro e informativo

## Descrição

O programa interage com o usuário via console, apresentando um menu com diversas opções de conversão entre moedas como USD, BRL, EUR, GBP, JPY e CAD. As taxas de câmbio são dinamicamente obtidas da API [exchangerate-api.com](https://www.exchangerate-api.com/).

## Desafios Enfrentados (Perspectiva de Iniciante)

Durante o desenvolvimento deste projeto, como iniciante em Java e consumo de APIs, enfrentei alguns desafios notáveis:

1.  **Importação e Uso de Bibliotecas Externas:** Um dos primeiros obstáculos foi entender como incluir bibliotecas de terceiros (como a Gson para processamento de JSON) em um projeto Java que não utiliza um gerenciador de dependências como Maven ou Gradle. Foi necessário baixar o arquivo JAR da biblioteca e configurá-lo corretamente no classpath para compilar e executar o código.

2.  **Consumo de API HTTP:** Realizar requisições HTTP para a API externa exigiu aprender a usar classes como `HttpClient`, `HttpRequest` e `HttpResponse` do pacote `java.net.http` (disponível a partir do Java 11). Entender o fluxo de enviar uma requisição e receber a resposta foi um passo importante.

3.  **Análise de Resposta JSON (Gson):** A resposta da API vem em formato JSON, que é uma estrutura de dados diferente das que eu estava acostumado. Utilizar a biblioteca Gson para analisar o JSON e mapeá-lo para objetos Java (como a classe `ApiResponse`) foi um desafio. Foi preciso entender como a estrutura do JSON se traduzia em classes Java e como acessar os dados aninhados (como as taxas de câmbio dentro do objeto "rates") usando `JsonObject`.

## Menu de Conversão

O programa apresenta o seguinte menu de opções para o usuário:

1. USD para BRL
2. BRL para USD
3. USD para EUR
4. EUR para USD
5. USD para GBP
6. GBP para USD
7. EUR para BRL
8. BRL para EUR
9. JPY para CAD
10. CAD para JPY
11. Sair

## Como Executar

1.  Certifique-se de ter o Java Development Kit (JDK) instalado (preferencialmente versão 11 ou superior).
2.  Baixe o arquivo JAR da biblioteca Gson (por exemplo, `gson-2.x.y.jar`) e coloque-o em uma pasta `lib` na raiz do projeto.
3.  Compile o código Java a partir do terminal, incluindo o JAR da Gson no classpath:
# Java API Service Starter

 (Substitua `x.y.z` pela versão da Gson. Use `;` em vez de `:` no Windows).
4.  Execute o programa, incluindo o JAR da Gson no classpath:

```(Substitua `x.y.z` pela versão da Gson. Use `;` em vez de `:` no Windows).
5.  O conversor será iniciado no console. Siga as instruções para escolher a conversão e inserir o valor.
