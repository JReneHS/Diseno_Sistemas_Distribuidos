import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Proyecto 2, Programa 2
 * Autor: Hernánez Velázquez Emmanuel Alejandro
 * Grupo: 4CM13, Desarrollo de Sistemas Distribuidos
*/
public class Palindrome_Dect {

	// Elabore un programa que reciba como parámetro el nombre de un archivo de texto,
	// lo analice e imprima todos los palíndromos de 2 a n palabras que existan en el texto;
	// donde n es el número total de palabras en el texto. 

	public static void recorrer( String[] palabras, int left, int right ){
		try { 
			// Iniciamos el "centro" del palindromo
			String palabra = palabras[left] == palabras[right] ? palabras[left] : palabras[left] + palabras[right];
			// Recorremos todo el texto
			while( left >= 0 && right < palabras.length ) {
				// Verificamos si la palabra es palíndromo
				// Siempre y cuando sean más ed 2 palabras
				if ( (right - left + 1) > 1 && palabra.equals( new StringBuilder(palabra).reverse().toString() ) ) {
					System.out.println( "\033[1m" + String.join(" ", Arrays.copyOfRange(palabras, left, right + 1)) + "\033[0m");
				}
				// Avanzamos los apuntadores
				right ++;
				left --;
				// Concatenamos las palabras para verificar si estas nuevas son palíndromos
				palabra = palabras[left] + palabra + palabras[right];
			}
		} catch (IndexOutOfBoundsException ob) {
			return;
		}
	}

	public static void main(String[] args) {
		try {
			// Leemos el texto completo a una cadena en RAM
			String libro = new String( 
				Files.readAllBytes( 
					Paths.get("LIBROS_TXT/" + args[0])
				)
			) ;

			// Eliminamos los caracteres UTF especiales excepto acentos
			libro = libro.replaceAll("[^a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s]", "");
			// Convertimos todo a minúsculas
			libro = libro.toLowerCase();
			// Reemplazamos toos los acentos por caracteres sin acento
			libro = libro.replaceAll("á", "a");
			libro = libro.replaceAll("é", "e");
			libro = libro.replaceAll("í", "i");
			libro = libro.replaceAll("ó", "o");
			libro = libro.replaceAll("ú", "u");
			
			String[] palabras = libro.split("\\s+");
			String palabra;
			
			// Iteramos por todas las palabras del texto
			for ( int i = 0; i < palabras.length; i++ ) {
				palabra = palabras[i];
				// Palíndromo de palabras pares
				recorrer( palabras, i, i );
				// Palíndromo de palabras impares
				recorrer( palabras, i, i + 1 );
			}
		} catch (IOException e) {
			System.err.println("Error: " + e);
		} catch (IndexOutOfBoundsException ex ){
			System.err.println("Uso: Palindrome_Dect <nombre_archivo>");
		}
	}

}
