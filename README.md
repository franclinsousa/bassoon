## Iniciando

``` shell
    $ ./gradlew clean run
```

## Endpoints

### Encurtador de urls
``` http request
    POST /api/v1/shorten
```
Body Requisição
``` json
{
    "url": "http://google.com/search?q=ktor+get+started"
}
```
Body Resposta
``` json
{
    "url": "http://contoso.me/{shorten}"
}
```
### Estatística de acesso
`{shorten}` é a parte URI da URL encurtada.
``` http request
    GET /api/v1/shorten/{shorten}/stats
```
Body Resposta
``` json
[
    {
        "link": {
            "target": "https://www.google.com/search?q=golang+api+rest+get+started"
        },
        "ip": "179.168.32.51",
        "continent": "South America",
        "continentCode": "SA",
        "country": "Brazil",
        "countryCode": "BR",
        "region": "RJ",
        "regionName": "Rio de Janeiro",
        "city": "Rio de Janeiro",
        "district": "",
        "zip": "20000",
        "lat": -22.9485,
        "lon": -43.3436,
        "timezone": "America/Sao_Paulo",
        "offset": "-10800",
        "currency": "BRL",
        "isp": "Vivo",
        "org": "TELEFÔNICA BRASIL S.A",
        "as": "AS26599 TELEFÔNICA BRASIL S.A",
        "asname": "TELEFÔNICA BRASIL S.A"
    }
]
```
