# Epic Energy Services

## Indice

- [Introduzione](#Introduzione)
- [Tecnologie utilizzate](#Tecnologie-utilizzate)
- [Versione](#Versione)
- [Breve descrizione funzionalità browser](#Breve-descrizione-funzionalità-browser)
- [Descrizione funzionalità Back-End](#Descrizione-funzionalità-Back-End)
- [Modelli utilizzati](#Modelli-utilizzati)
- [Implementate CRUD di base e metodi per migliorare la gestione degli elementi](#Implementate-CRUD-di-base-e-metodi-per-migliorare-la-gestione-degli-elementi)
- [Testing](#Testing)
- [Licenza](#Licenza)

## Introduzione

Questa è un'applicazione per un'azienda fornitrice di energia che gestisce i clienti e legatture.

### Tecnologie utilizzate: 

- Java + Spring Boot
- HTML5 (base)
- CSS (base)
- Thymeleaf
- JUnit
- Eclipse
- PostgreSQL
- REST Client
- Maven
- Postman (link per la collection con tutte le funzioni = https://www.getpostman.com/collections/6a0b09e07839cd817773)
- GitHub 
- Swagger

## Versione

Ultima versione stabile: 1.0  

## Breve descrizione funzionalità browser

- Homepage dove è possibile procedere con la gestione dei clienti o delle fatture (all'avvio del server sul browser bisogna inserire nell'URL localhost con assegnata la sua porta, default è localhost:8080)
- Visualizzazione della lista dei clienti
- Visualizzazione della lista delle fatture
- Gestione clienti e fatture disponibile tramite gli appositi pulsanti
- Salvataggio dati su database PostgreSQL

Le funzioni sopracitate si presenteranno in sequenza partendo dalla homepage, a seconda di quali elementi si vogliono gestire saranno poi disponibili le loro funzionalità principali:
- Aggiungi cliente/fattura = permette di aggiungere un elemento compilando un form per inserire i suoi dati
- Elimina cliente/fattura = permette di eliminare un elemento chiedendo conferma dell'operazione tramite un alert
- Aggiorna cliente/fattura = permette di aggiornare i dati dell'elemento scelto compilando un form

Inoltre è possibile ordinare i clienti:
- Ordina per ragione sociale
- Ordina per fatturato annuale
- Ordina per data inserimento
- Ordina per data ultimo contatto avuto con il cliente

## Descrizione funzionalità Back-End

Applicazione Java + Spring Boot.

### Modelli utilizzati
- Cliente
- Comune
- Fattura
- Indirizzo
- Modelli per login di utenti (security)
- Provincia
- Stato fattura (es: Pagata, Non pagata)
- Tipo cliente (es: PA, SAS, SPA, SRL)

### Implementate CRUD di base e metodi per migliorare la gestione degli elementi
- Aggiungi elemento
- Elimina elemento
- Aggiorna elemento
- Cerca elemento per id
- Cerca tutti gli elementi
- Ordinamenti citati nelle funzionalità browser
- Filtro ricerca cliente per fatturato annuale maggiore del valore inserito in input
- Filtro ricerca cliente per fatturato annuale minore del valore insesrito in input
- Filtro ricerca cliente per fatturato annuale compreso tra i due valori passati in input
- Filtro ricerca cliente per data inserimento maggiore del valore inserito in input
- Filtro ricerca cliente per data inserimento minore del valore inserito in input
- Filtro ricerca cliente per data inserimento compresa tra i due valori inseriti in input
- Filtro ricerca cliente per data ultimo contatto maggiore del valore inserito in input
- Filtro ricerca cliente per data ultimo contatto minore del valore inserito in input
- Filtro ricerca cliente per data ultimo contatto compresa tra i due valori inseriti in input
- Filtro ricerca cliente tramite nome intero o parziale inserito in input
- Funzionalità sopracitate adattate anche per le fatture

## Testing

- Postman
- Swagger
- Junit5

Java:  
- https://spring.io/

## Licenza
MIT ©
