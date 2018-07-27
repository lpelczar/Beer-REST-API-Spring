package com.odrzuty.piworestapi.controller;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;

class BreweryRestControllerTest {

    @Test
    void get_breweries_resource_returns_200_with_expected_id() {
        when().
                get("/api/breweries/{id}", 5).
                then().
                statusCode(200).
                body("id", is(5));
    }

    @Test
    void get_breweries_resource_returns_400_when_incorrect_path() {
        when().
                get("/api/breweries/test").
                then().
                statusCode(400);
    }

    @Test
    void get_breweries_resource_returns_404_when_not_found() {
        when().
                get("/api/breweries/{id}", 2000000000).
                then().
                statusCode(404);
    }

    @Test
    void get_breweries_resource_returns_status_code_200() {
        when().
                get("/api/breweries/").
                then().
                statusCode(200);
    }

    @Test
    void post_brewery_return_status_code_201() {

        String jsonBrewery = "{ \"name\" : \"(512) New Company\", \"address1\" : \"407 Radam, F200\", " +
                "\"city\" : \"Austin\", \"state\" : \"Texas\", \"code\" : \"78745\", \"country\" : \"United States\", " +
                "\"phone\" : \"512.707.2337\", \"website\" : \"http://512brewing.com/\", \"descript\" : \"(512) Brewing"+
                " Company is a microbrewery located in the heart of Austin that brews for the community using as many " +
                "local, domestic and organic ingredients as possible.\" }";

        given().
                contentType("application/json").
                body(jsonBrewery).
                when().
                post("/api/breweries/").
                then().
                statusCode(200);
    }

    @Test
    void post_invalid_brewery_return_status_code_400() {

        String jsonBrewery = "{\"name\" : \"(512) New Company\", \"address1\" : \"407 Radam, F200\", " +
                "\"city\" : \"Austin\", \"state\" : \"Texas\", \"code\" : \"78745\", \"country\" : \"United States\", " +
                "\"phone\" : \"512.707.2337\", \"website\" : \"http://512brewing.com/\", \"descript\" : \"(512) Brewing"+
                " Company is a microbrewery located in the heart of Austin that brews for the community using as many " +
                "local, domestic and organic ingredients as possible.\" dsda}";

        given().
                contentType("application/json").
                body(jsonBrewery).
                when().
                post("/api/breweries/").
                then().
                statusCode(400);
    }

    @Test
    void post_brewery_with_id_return_status_code_200() {

        String jsonBrewery = "{\"id\" : 1, \"name\" : \"(512) Nowy nowy\", \"address1\" : \"407 Radam, F200\", " +
                "\"city\" : \"Austin\", \"state\" : \"Texas\", \"code\" : \"78745\", \"country\" : \"United States\", " +
                "\"phone\" : \"512.707.2337\", \"website\" : \"http://512brewing.com/\", \"descript\" : \"(512) Brewing"+
                " Company is a microbrewery located in the heart of Austin that brews for the community using as many " +
                "local, domestic and organic ingredients as possible.\" }";

        given().
                contentType("application/json").
                body(jsonBrewery).
                when().
                post("/api/breweries/").
                then().
                statusCode(200);
    }

    @Test
    void post_with_invalid_path_return_status_code_405() {
        when().
                post("/api/breweries/323").
                then().
                statusCode(405);
    }

    @Test
    void put_correct_brewery_return_status_code_200() {

        String jsonBrewery = "{\"id\" : 1, \"name\" : \"Nowe\", \"address1\" : \"407 Radam, F200\", " +
                "\"city\" : \"Austin\", \"state\" : \"Texas\", \"code\" : \"78745\", \"country\" : \"United States\", " +
                "\"phone\" : \"512.707.2337\", \"website\" : \"http://512brewing.com/\", \"descript\" : \"(512) Brewing"+
                " Company is a microbrewery located in the heart of Austin that brews for the community using as many " +
                "local, domestic and organic ingredients as possible.\" }";

        given().
                contentType("application/json").
                body(jsonBrewery).
                when().
                put("/api/breweries/{id}", 1).
                then().
                statusCode(200);
    }

    @Test
    void put_brewery_invalid_path_return_status_code_405() {
        when().
                put("/api/breweries/").
                then().
                statusCode(405);
    }

    @Test
    void put_brewery_with_empty_body_return_status_code_415() {
        given().
                body("").
                when().
                put("/api/breweries/{id}", 1).
                then().
                statusCode(415);
    }

    @Test
    void delete_brewery_with_invalid_path_return_status_code_404() {
        when().
                delete("/api/breweries/123/132").
                then().
                statusCode(404);
    }

    @Test
    void delete_brewery_with_invalid_id_return_status_code_404() {
        when().
                delete("/api/breweries/{id}", 200000000).
                then().
                statusCode(404);
    }
}