# Movie API Client

Detta projekt är en Java-baserad klient för att interagera med ett film-API. Det använder Spring Boot och Maven för beroendehantering och byggande.

## Funktioner

- Lista alla filmer
- Hämta film efter ID
- Hämta film efter titel
- Hämta filmer efter genre
- Hämta filmer efter år
- Lägg till en ny film
- Uppdatera en befintlig film
- Ta bort en film
- Lista alla regissörer
- Hämta regissör efter ID
- Hämta regissör efter namn
- Lägg till en ny regissör
- Uppdatera en befintlig regissör
- Ta bort en regissör

## Använda teknologier

- Java
- Spring Boot
- Maven

## Förutsättningar

- Java 11 eller högre
- Maven 3.6.0 eller högre

## Komma igång

1. Klona repot:
    ```sh
    git clone https://github.com/Marwin436/movie-api-client.git
    cd movie-api-client
    ```

2. Bygg projektet med Maven:
    ```sh
    mvn clean install
    ```

3. Kör applikationen:
    ```sh
    mvn spring-boot:run
    ```

## Användning

När applikationen körs kommer du att se huvudmenyn i konsollen. Följ instruktionerna för att navigera genom menyerna och använda de olika funktionerna.

### Huvudmeny

- **Visa huvudmenyn:**
    ```sh
    Välj ett alternativ:
    1. Filmer
    2. Regissörer
    3. Avsluta
    ```

### Filmmeny

- **Visa filmmenyn:**
    ```sh
    Välj ett alternativ:
    1. Lista alla filmer
    2. Hämta film efter ID
    3. Hämta film efter titel
    4. Hämta filmer efter genre
    5. Hämta filmer efter år
    6. Lägg till en ny film
    7. Uppdatera en befintlig film
    8. Ta bort en film
    9. Tillbaka till huvudmenyn
    ```

### Regissörmeny

- **Visa regissörmenyn:**
    ```sh
    Välj ett alternativ:
    1. Lista alla regissörer
    2. Hämta regissör efter ID
    3. Hämta regissör efter namn
    4. Lägg till en ny regissör
    5. Uppdatera en befintlig regissör
    6. Ta bort en regissör
    7. Tillbaka till huvudmenyn
    ```

## Licens

Detta projekt är licensierat under MIT-licensen.