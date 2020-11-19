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
		StringBuilder sb = new StringBuilder();
		if (this.getGauche() instanceof TermApplication && this.getDroite() instanceof TermApplication){
			sb.append(" ").append(this.getGauche().toHaskell())
					.append(" ")
					.append(this.getDroite().toHaskell())
					.append(" ");
		} else {
			sb.append(" (")
					.append(this.getGauche().toHaskell())
					.append(this.getDroite().toHaskell())
					.append(") ");
		}
		return sb.toString();
	}
}