package compiler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import compiler.haskell.Program;

public class Haskeller {
	public static void main(String[] args) {
		Program notreAST = ExampleProgram.getAST();
		String notreHaskell = notreAST.toHaskell();
		try {
			Files.deleteIfExists(Paths.get("NotreASTtoHaskell.hs"));
			Files.write(Paths.get("NotreASTtoHaskell.hs"), notreHaskell.getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			System.out.println("Erreur lors de l'écriture du fichier: " + e);
		}

		Program notreASTCompile = new Compiler().compile(notreAST);
		;
		String notreHaskellCompile = notreASTCompile.toHaskell();
		try {
			Files.deleteIfExists(Paths.get("NotreASTtoHaskellCompile.hs"));
			Files.write(Paths.get("NotreASTtoHaskellCompile.hs"), notreHaskellCompile.getBytes(),
					StandardOpenOption.CREATE);
		} catch (IOException e) {
			System.out.println("Erreur lors de l'écriture du fichier: " + e);
		}
	}
}
