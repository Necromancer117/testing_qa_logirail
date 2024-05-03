package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.jar.Attributes.Name;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

class GildedRoseTest {

    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String STANDARD = "Standard product";
    private static final String BRIE = "Aged Brie";
    private static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SUMMONED = "Summoned";
	@Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }
    @Test
    void sulfuras() {
    	Item[] items = new Item[] { new Item(SULFURAS, 22, 80) };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        assertNotNull(items);
		assertAll("Proiedades", 
			() -> assertEquals(SULFURAS, app.items[0].name, "name"),
			() -> assertEquals(22, app.items[0].sellIn, "Sellin"),
			() -> assertEquals(80, app.items[0].quality, "Quality")
			);
    }
    @Nested
	@DisplayName("Standard")
    class Standard{
    	
    	@Test
    	@DisplayName("Con calidad y fecha, se queda a 0")
        void standard_CP1 () {
        	Item[] items = new Item[] { new Item(STANDARD, 1, 1) };
            GildedRose app = new GildedRose(items);
            
            app.updateQuality();
            
            assertNotNull(items);
    		assertAll("Proiedades", 
    			() -> assertEquals(STANDARD, app.items[0].name, "name"),
    			() -> assertEquals(0, app.items[0].sellIn, "Sellin"),
    			() -> assertEquals(0, app.items[0].quality, "Quality")
    			);
        }
    	
    	@Test
    	@DisplayName("Con calidad y fecha, calidad final>0")
        void standard_CP2 () {
        	Item[] items = new Item[] { new Item(STANDARD, 22, 2) };
            GildedRose app = new GildedRose(items);
            
            app.updateQuality();
            
            assertNotNull(items);
    		assertAll("Proiedades", 
    			() -> assertEquals(STANDARD, app.items[0].name, "name"),
    			() -> assertEquals(21, app.items[0].sellIn, "Sellin"),
    			() -> assertEquals(1, app.items[0].quality, "Quality")
    			);
        }
    	
    	@Test
    	@DisplayName("sin calidad y con fecha")
        void standard_CP3 () {
        	Item[] items = new Item[] { new Item(STANDARD, 34, 0) };
            GildedRose app = new GildedRose(items);
            
            app.updateQuality();
            
            assertNotNull(items);
    		assertAll("Proiedades", 
    			() -> assertEquals(STANDARD, app.items[0].name, "name"),
    			() -> assertEquals(33, app.items[0].sellIn, "Sellin"),
    			() -> assertEquals(0, app.items[0].quality, "Quality")
    			);
        }
    	
    	@Test
    	@DisplayName("Error: calidad negativa")
        void standard_CP4 () {
        	Item[] items = new Item[] { new Item(STANDARD, 53, -1) };
            GildedRose app = new GildedRose(items);
            
            app.updateQuality();
            
            assertNotNull(items);
    		assertAll("Proiedades", 
    			() -> assertEquals(STANDARD, app.items[0].name, "name"),
    			() -> assertEquals(51, app.items[0].sellIn, "Sellin"), //52?
    			() -> assertEquals(-1, app.items[0].quality, "Quality")
    			);
        }
    	
    	@Test
    	@DisplayName("con calidad sin fecha, calidad final = 0")
        void standard_CP5 () {
        	Item[] items = new Item[] { new Item(STANDARD, 0, 1) };
            GildedRose app = new GildedRose(items);
            
            app.updateQuality();
            
            assertNotNull(items);
    		assertAll("Proiedades", 
    			() -> assertEquals(STANDARD, app.items[0].name, "name"),
    			() -> assertEquals(-1, app.items[0].sellIn, "Sellin"),
    			() -> assertEquals(0, app.items[0].quality, "Quality")
    			);
        }
    	
    	@Test
    	@DisplayName("con calidad sin fecha")
        void standard_CP6 () {
        	Item[] items = new Item[] { new Item(STANDARD, 0, 3) };
            GildedRose app = new GildedRose(items);
            
            app.updateQuality();
            
            assertNotNull(items);
    		assertAll("Proiedades", 
    			() -> assertEquals(STANDARD, app.items[0].name, "name"),
    			() -> assertEquals(-1, app.items[0].sellIn, "Sellin"),
    			() -> assertEquals(1, app.items[0].quality, "Quality")
    			);
        }
    	
    	@Test
    	@DisplayName("sin calidad y fecha negativa")
        void standard_CP7 () {
        	Item[] items = new Item[] { new Item(STANDARD, -24, 0) };
            GildedRose app = new GildedRose(items);
            
            app.updateQuality();
            
            assertNotNull(items);
    		assertAll("Proiedades", 
    			() -> assertEquals(STANDARD, app.items[0].name, "name"),
    			() -> assertEquals(-25, app.items[0].sellIn, "Sellin"),
    			() -> assertEquals(0, app.items[0].quality, "Quality")
    			);
        }
    	
    	@Test
    	@DisplayName("Error: Fecha y calidad negativa")
        void standard_CP8 () {
        	Item[] items = new Item[] { new Item(STANDARD, -31, -1) };
            GildedRose app = new GildedRose(items);
            
            app.updateQuality();
            
            assertNotNull(items);
    		assertAll("Proiedades", 
    			() -> assertEquals(STANDARD, app.items[0].name, "name"),
    			() -> assertEquals(-31, app.items[0].sellIn, "Sellin"),  //-32??
    			() -> assertEquals(-1, app.items[0].quality, "Quality")
    			);
        }
    	
    }
    
    @Nested
	@DisplayName("Aged Brie")
    class Brie{
    	
    	@Test
    	@DisplayName("Con fecha y calidad<50, se queda a 50")
        void standard_CP1 () {
        	Item[] items = new Item[] { new Item(BRIE, 1, 49) };
            GildedRose app = new GildedRose(items);
            
            app.updateQuality();
            
            assertNotNull(items);
    		assertAll("Proiedades", 
    			() -> assertEquals(BRIE, app.items[0].name, "name"),
    			() -> assertEquals(0, app.items[0].sellIn, "Sellin"),
    			() -> assertEquals(50, app.items[0].quality, "Quality")
    			);
        }
    	
    	@Test
    	@DisplayName("Con fecha y calidad<50")
        void standard_CP2 () {
        	Item[] items = new Item[] { new Item(BRIE, 1, 48) };
            GildedRose app = new GildedRose(items);
            
            app.updateQuality();
            
            assertNotNull(items);
    		assertAll("Proiedades", 
    			() -> assertEquals(BRIE, app.items[0].name, "name"),
    			() -> assertEquals(0, app.items[0].sellIn, "Sellin"),
    			() -> assertEquals(49, app.items[0].quality, "Quality")
    			);
        }
    	
    	@Test
    	@DisplayName("Con fecha y calidad=50, se queda en 50")
        void standard_CP3 () {
        	Item[] items = new Item[] { new Item(BRIE, 1, 50) };
            GildedRose app = new GildedRose(items);
            
            app.updateQuality();
            
            assertNotNull(items);
    		assertAll("Proiedades", 
    			() -> assertEquals(BRIE, app.items[0].name, "name"),
    			() -> assertEquals(0, app.items[0].sellIn, "Sellin"),
    			() -> assertEquals(50, app.items[0].quality, "Quality")
    			);
        }
    	
    	@Test
    	@DisplayName("ERROR: Calidad>50")
        void standard_CP4 () {
        	Item[] items = new Item[] { new Item(BRIE, 40, 82) };
            GildedRose app = new GildedRose(items);
            
            app.updateQuality();
            
            assertNotNull(items);
    		assertAll("Proiedades", 
    			() -> assertEquals(BRIE, app.items[0].name, "name"),
    			() -> assertEquals(40, app.items[0].sellIn, "Sellin"),
    			() -> assertEquals(82, app.items[0].quality, "Quality")
    			);
        }
    	
    	@Test
    	@DisplayName("Sin fecha y calidad<50, final 50")
        void standard_CP5 () {
        	Item[] items = new Item[] { new Item(BRIE, 0, 49) };
            GildedRose app = new GildedRose(items);
            
            app.updateQuality();
            
            assertNotNull(items);
    		assertAll("Proiedades", 
    			() -> assertEquals(BRIE, app.items[0].name, "name"),
    			() -> assertEquals(-1, app.items[0].sellIn, "Sellin"),
    			() -> assertEquals(50, app.items[0].quality, "Quality")
    			);
        }
    	
    	@Test
    	@DisplayName("Sin fecha y calidad<50")
        void standard_CP6 () {
        	Item[] items = new Item[] { new Item(BRIE, 0, 47) };
            GildedRose app = new GildedRose(items);
            
            app.updateQuality();
            
            assertNotNull(items);
    		assertAll("Proiedades", 
    			() -> assertEquals(BRIE, app.items[0].name, "name"),
    			() -> assertEquals(-1, app.items[0].sellIn, "Sellin"),
    			() -> assertEquals(49, app.items[0].quality, "Quality")
    			);
        }
    	
    	@Test
    	@DisplayName("Sin fecha y calidad=50")
        void standard_CP7 () {
        	Item[] items = new Item[] { new Item(BRIE, 0, 50) };
            GildedRose app = new GildedRose(items);
            
            app.updateQuality();
            
            assertNotNull(items);
    		assertAll("Proiedades", 
    			() -> assertEquals(BRIE, app.items[0].name, "name"),
    			() -> assertEquals(-1, app.items[0].sellIn, "Sellin"),
    			() -> assertEquals(50, app.items[0].quality, "Quality")
    			);
        }
    	
    	@Test
    	@DisplayName("Error: Sin fecha y calidad>50")
        void standard_CP8 () {
        	Item[] items = new Item[] { new Item(BRIE, 0, 51) };
            GildedRose app = new GildedRose(items);
            
            app.updateQuality();
            
            assertNotNull(items);
    		assertAll("Proiedades", 
    			() -> assertEquals(BRIE, app.items[0].name, "name"),
    			() -> assertEquals(0, app.items[0].sellIn, "Sellin"),
    			() -> assertEquals(51, app.items[0].quality, "Quality")
    			);
        }
    	
    }
     	
    	@ParameterizedTest(name = "Caso {index} fecha {0} calidad {1}.")
        @CsvSource({
        	
                "11, 49, 10, 50",
                "6, 49, 5, 50",
                "1, 49, 0, 50",
                "0, 51, 0, 51",
                "22, 50, 21, 50",
                "7, 50, 6, 50",
                "4, 50, 3, 50",
                "-21, 50, -22, 0",
                "33, 10, 32, 11",
                "8, 4, 7, 6",
                "2, 42, 1, 45",
                "-567, 47, -568, 0",
                "62, 51, 62, 51",
                
                
        })
        @DisplayName("Backsatge")
        void Backstage(int initialSellIn, int initialQuality, int expectedSellIn, int expectedQuality) {
            Item[] items = new Item[] { new Item(BACKSTAGE, initialSellIn, initialQuality) };
            GildedRose app = new GildedRose(items);

            app.updateQuality();

            assertNotNull(items);
            assertAll("Propiedades",
                    () -> assertEquals(BACKSTAGE, app.items[0].name, "name"),
                    () -> assertEquals(expectedSellIn, app.items[0].sellIn, "Sellin"),
                    () -> assertEquals(expectedQuality, app.items[0].quality, "Quality")
            );
        }
    	
    	@ParameterizedTest(name = "Caso {index} fecha {0} calidad {1}.")
        @CsvSource({
        	
                "1, 2, 0, 0",
                "22, 7, 21, 5",
                "34, 0, 33, 0",
                "53, -1, 53, -1",
                "0, 4, -1, 0",
                "-33, 5, -34, 1",
                "-24, 0, -25, 0",
                "-31, -1, -31, -1",    
                
        })
        @DisplayName("Summoned")
        void Summoned(int initialSellIn, int initialQuality, int expectedSellIn, int expectedQuality) {
            Item[] items = new Item[] { new Item(SUMMONED, initialSellIn, initialQuality) };
            GildedRose app = new GildedRose(items);

            app.updateQuality();

            assertNotNull(items);
            assertAll("Propiedades",
                    () -> assertEquals(SUMMONED, app.items[0].name, "name"),
                    () -> assertEquals(expectedSellIn, app.items[0].sellIn, "Sellin"),
                    () -> assertEquals(expectedQuality, app.items[0].quality, "Quality")
            );
        }

}
