package compiler.haskell;

public class TypeConstraint extends Type {

	private TypeApplication gauche;
	private Type droite;

	public TypeConstraint(TypeApplication gauche, Type droite) {
		this.gauche = gauche;
		this.droite = droite;
	}

	public TypeApplication getGauche() {
		return gauche;
	}

	public Type getDroite() {
		return droite;
	}

	@Override
	public String toHaskell() {
		return "Consraint? " + this.getGauche().toHaskell() + " " + this.getDroite().toHaskell();
	}
}