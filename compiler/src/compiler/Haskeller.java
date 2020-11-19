package compiler;

import compiler.haskell.Program;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Haskeller {
    public static void main(String[] args) {
        Program notreAST = ExampleProgram.getAST();
        String notreHaskell = notreAST.toHaskell();
        try
        {
            Files.write(Paths.get("NotreASTtoHaskell.hs"), notreHaskell.getBytes(), StandardOpenOption.CREATE
            );
        }
        catch (IOException e)
        {
            System.out.println("Erreur lors de l'écriture du fichier: " + e);
        }
    }
}
