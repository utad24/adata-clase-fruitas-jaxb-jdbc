package com.utad;

import com.utad.models.Fruit;
import com.utad.models.Fruits;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class Main {

    private static String path = "./src/main/resources/fruits.xml";

    public static void main(String[] args) {

        List<Fruit> fruits = readFruits();
        insertFruit(fruits);

    }

    public static void insertFruit(List<Fruit> fruits) {
        FruitDAO fruitDAO = new FruitDAO();
        for (Fruit fruit : fruits) {
            fruitDAO.createFruit(fruit.getName(), fruit.getColor(), fruit.getWeight());
        }
    }

    public static List<Fruit> readFruits() {
        try {
            // Crear el contexto JAXB basado en la clase Fruits (la raíz del XML)
            JAXBContext context = JAXBContext.newInstance(Fruits.class);

            // Crear el deserializador (Unmarshaller)
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Especificar el archivo XML a deserializar
            File file = new File(path);

            // Deserializar el archivo XML a un objeto Fruits
            Fruits fruits = (Fruits) unmarshaller.unmarshal(file);

            // Recorrer y mostrar las frutas y sus colores
            for (Fruit fruit : fruits.getFruits()) {
                System.out.println("Fruta: " + fruit.getName());
                System.out.println("  Color: " + fruit.getColor());
                System.out.println("  Peso: " + fruit.getWeight());
            }


            return fruits.getFruits();
        } catch (JAXBException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}