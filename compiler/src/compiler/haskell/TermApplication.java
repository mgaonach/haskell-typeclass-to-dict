package compiler.haskell;

public class TermApplication extends Term {

	private Term gauche;
	private Term droite;

	public TermApplication(Term gauche, Term droite) {
		this.gauche = gauche;
		this.droite = droite;
	}

	public Term getGauche() {
		return gauche;
	}

	public Term getDroite() {
		return droite;
	}

	@Override
	public String toHaskell() {
		return this.getGauche().toHaskell() + " (" + this.getDroite().toHaskell() + ")";
	}
}