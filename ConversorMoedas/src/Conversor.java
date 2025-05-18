import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

// Classe para representar a resposta geral da API
class ApiResponse {
    String result;
    String documentation;
    String terms_of_use;
    long time_last_update_unix;
    String time_last_update_utc;
    long time_next_update_unix;
    String time_next_update_utc;
    String base_code;
    JsonObject rates;
}

public class Conversor {

    public static void main(String[] args) {
        System.out.println("Conversor de Moedas - Iniciado!");

        String baseCurrency = "USD"; // Moeda base para obter as taxas.
        String apiUrl = "https://open.er-api.com/v6/latest/" + baseCurrency;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonResponse = response.body();

            Gson gson = new Gson();
            ApiResponse apiResponse = gson.fromJson(jsonResponse, ApiResponse.class);

            if ("success".equals(apiResponse.result)) {
                JsonObject rates = apiResponse.rates;

                // Obter as taxas para as moedas que usaremos (relativas à moeda base)
                double rateUSD = rates.get("USD").getAsDouble();
                double rateBRL = rates.get("BRL").getAsDouble();
                double rateEUR = rates.get("EUR").getAsDouble();
                double rateGBP = rates.get("GBP").getAsDouble();
                double rateJPY = rates.get("JPY").getAsDouble();
                double rateCAD = rates.get("CAD").getAsDouble();

                // Exibir o menu com mais opções
                System.out.println("\nEscolha a opção de conversão:");
                System.out.println("1. USD para BRL");
                System.out.println("2. BRL para USD");
                System.out.println("3. USD para EUR");
                System.out.println("4. EUR para USD");
                System.out.println("5. USD para GBP");
                System.out.println("6. GBP para USD");
                System.out.println("7. EUR para BRL");
                System.out.println("8. BRL para EUR");
                System.out.println("9. JPY para CAD");
                System.out.println("10. CAD para JPY");
                System.out.println("11. Sair");

                Scanner scanner = new Scanner(System.in);

                while (true) {
                    System.out.print("\nDigite o número da opção desejada: ");
                    int option = scanner.nextInt();

                    if (option == 11) { // Opção de sair  11
                        System.out.println("Saindo do conversor. Até mais!");
                        break;
                    }

                    double amount = 0;
                    double convertedAmount = 0;
                    String fromCurrency = "";
                    String toCurrency = "";
                    boolean validOption = true; // Flag para verificar se a opção é válida

                    switch (option) {
                        case 1: // USD para BRL
                            System.out.print("Digite o valor em USD: ");
                            amount = scanner.nextDouble();
                            convertedAmount = amount * rateBRL;
                            fromCurrency = "USD";
                            toCurrency = "BRL";
                            break;
                        case 2: // BRL para USD
                            System.out.print("Digite o valor em BRL: ");
                            amount = scanner.nextDouble();
                            convertedAmount = amount / rateBRL; // Convertendo para a moeda base (USD)
                            fromCurrency = "BRL";
                            toCurrency = "USD";
                            break;
                        case 3: // USD para EUR
                            System.out.print("Digite o valor em USD: ");
                            amount = scanner.nextDouble();
                            convertedAmount = amount * rateEUR;
                            fromCurrency = "USD";
                            toCurrency = "EUR";
                            break;
                        case 4: // EUR para USD
                            System.out.print("Digite o valor em EUR: ");
                            amount = scanner.nextDouble();
                            convertedAmount = amount / rateEUR;
                            fromCurrency = "EUR";
                            toCurrency = "USD";
                            break;
                        case 5: // USD para GBP
                            System.out.print("Digite o valor em USD: ");
                            amount = scanner.nextDouble();
                            convertedAmount = amount * rateGBP;
                            fromCurrency = "USD";
                            toCurrency = "GBP";
                            break;
                        case 6: // GBP para USD
                            System.out.print("Digite o valor em GBP: ");
                            amount = scanner.nextDouble();
                            convertedAmount = amount / rateGBP;
                            fromCurrency = "GBP";
                            toCurrency = "USD";
                            break;
                        case 7: // EUR para BRL (Exemplo: EUR -> USD -> BRL)
                            System.out.print("Digite o valor em EUR: ");
                            amount = scanner.nextDouble();
                            convertedAmount = (amount / rateEUR) * rateBRL;
                            fromCurrency = "EUR";
                            toCurrency = "BRL";
                            break;
                        case 8: // BRL para EUR (Exemplo: BRL -> USD -> EUR)
                            System.out.print("Digite o valor em BRL: ");
                            amount = scanner.nextDouble();
                            convertedAmount = (amount / rateBRL) * rateEUR;
                            fromCurrency = "BRL";
                            toCurrency = "EUR";
                            break;
                        case 9: // JPY para CAD (Exemplo: JPY -> USD -> CAD)
                            System.out.print("Digite o valor em JPY: ");
                            amount = scanner.nextDouble();
                            convertedAmount = (amount / rateJPY) * rateCAD;
                            fromCurrency = "JPY";
                            toCurrency = "CAD";
                            break;
                        case 10: // CAD para JPY (Exemplo: CAD -> USD -> JPY)
                            System.out.print("Digite o valor em CAD: ");
                            amount = scanner.nextDouble();
                            convertedAmount = (amount / rateCAD) * rateJPY;
                            fromCurrency = "CAD";
                            toCurrency = "JPY";
                            break;
                        default:
                            System.out.println("Opção inválida. Por favor, digite um número válido.");
                            validOption = false; // Marca a opção como inválida
                            break; // Sai do switch, mas o loop while continua se validOption for false
                    }

                    if (validOption) { // Só exibe o resultado se a opção foi válida
                        // Exibir o resultado da conversão
                        System.out.printf("%.2f %s equivalem a %.2f %s%n", amount, fromCurrency, convertedAmount, toCurrency);
                    }


                }

                scanner.close();

            } else {
                System.err.println("Erro na resposta da API: " + apiResponse.result);
            }

        } catch (Exception e) {
            System.err.println("Erro ao consumir ou analisar a API, ou erro na entrada do usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
