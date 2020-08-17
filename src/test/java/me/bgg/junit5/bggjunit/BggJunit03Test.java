package me.bgg.junit5.bggjunit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BggJunit03Test {

    @Test
    void create(){
        BggJunit03 bggJunit03 = new BggJunit03();
        assertNotNull(bggJunit03);
        System.out.println("create");
    }

    @Test
    void create1(){
        System.out.println("create1");
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("before each");
    }

    @AfterEach
    void afterEach(){
        System.out.println("after each");
    }

}