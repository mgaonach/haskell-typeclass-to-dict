package compiler.haskell;

public class Attribution implements Instruction{

	private Term gauche;
	private Term droite;

	public Attribution(Term gauche, Term droite) {
		this.gauche = gauche;
		this.droite = droite;
	}

	public Term getLeft() {
		return gauche;
	}

	public Term getRight() {
		return droite;
	}

}