package compiler.haskell;

public class TypeConstraint extends Type {

	private TypeApplication gauche;
	private TypeApplication droite;

	public TypeConstraint(TypeApplication gauche, TypeApplication droite) {
		this.gauche = gauche;
		this.droite = droite;
	}

	public TypeApplication getGauche() {
		return gauche;
	}

	public TypeApplication getDroite() {
		return droite;
	}

}