# <SimilarProducts>

## Description for SimilarProducts
Get details of products similar to a given product.
API rest with Spring Boot 2.3.0-release and Java 11.
Includes unit tests with Mockito.

Packages in /src: 
- /configuration -> Constructor parameter for integration url.
- /controller -> Process request and give a response.
- /exception ->  Exception handling.
- /integration -> Integration with other services.
- /model -> Structs of response for the integrations API..
- /service -> Package with logic.  

## Input parameter
Product id for which you want to get similar products
- String productId

## Output parameters
List with details of similar products
- String id
- String name
- Number price
- boolean availability