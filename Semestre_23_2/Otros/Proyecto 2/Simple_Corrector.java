/**
 * Proyecto 2, Programa 1
 * Autor: Hernánez Velázquez Emmanuel Alejandro
 * Grupo: 4CM13, Desarrollo de Sistemas Distribuidos
*/

import java.util.HashSet;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Simple_Corrector {

	// Set para guardar las palabras encontradas
	HashSet<String> foundWords = new HashSet<String>();
	public void crearDiccionario(){
		// Probando leer todos los archivos
		File libros =  new File("LIBROS_TXT");
		// Iteramos por todos los archivos del fichero
		for ( File archivo : libros.listFiles() ) {
			try{
				// Leemos toooooodo el libro en una simple caena
				String book = new String( Files.readAllBytes( Paths.get(archivo.getAbsolutePath()) ) );
				// Eliminamos los caracteres UTF especiales excepto acentos
				book = book.replaceAll("[^a-zA-Z0-9áéíóúÁÉÍÓÚñÑ]", " ");
				// Convertimos todo a minúsculas
				book = book.toLowerCase();
				// Convertimos la cadena en un arreglo de palabras
				String[] words = book.split("\\s+"); // Separado por uno o mas espacios
				// Iteramos por todas las palabras del arreglo
				for ( String word : words ) {
					if ( !foundWords.contains(word) ) {
						// Si la palabra no existe en el set, la agregamos
						foundWords.add(word);
					}
				}
			} catch (Exception e) {
				// Si ocurre un error, lo imprimimos
				System.out.println(e);
			}
		}
	}

	public static void main(String[] args) {

		// Creamos el diccionario mediante los libros descargados
		System.out.println("\033[1mCreando diccionario...");
		Simple_Corrector corrector = new Simple_Corrector();
		corrector.crearDiccionario();
		System.out.println("\033[1;92mDiccionario creado!\033[0m");

		// Iniciamos el corrector
		Scanner scanner = new Scanner(System.in);
		String input = "";
		do {
			System.out.print("> ");
			input = scanner.nextLine();
			// Limpiamos la entrada de cualquier caracter especial
			input = input.replaceAll("[^a-zA-Z0-9áéíóúÁÉÍÓÚñÑ]", " ");
			// La convertimos en minúscula
			input = input.toLowerCase();
			 // Separado por uno o mas espacios
			String[] words = input.split("\\s+");
			// Comparamos todas las palabas en la entrada el usuario
			for ( String word : words) {
				// Si no existe, imprimimos las sugerencias
				if ( !corrector.foundWords.contains(word) ) {
					System.out.println( "\033[1;91m" + word + " No existe\033[0m" );
					boolean sugerencias = false;
					for ( String foundWord : corrector.foundWords ) {
						// Si encontramos una palabra, la imprimimos
						if ( foundWord.contains(word) ) {
							if ( sugerencias == false ){
								System.out.println("\033[93mPosibles sugerencias:\033[0m");
								sugerencias = true;
							}
							System.out.println( "   > \033[93m" + foundWord + "\033[0m" );
						}
					}
				}
			}
		} while ( !input.equals("exit") );

	}

}