package compiler.haskell;

public class TypeFunction extends Type {

	private Type gauche;
	private Type droite;

	public TypeFunction(Type gauche, Type droite) {
		this.gauche = gauche;
		this.droite = droite;
	}

	public Type getGauche() {
		return gauche;
	}

	public Type getDroite() {
		return droite;
	}

	@Override
	public String toSimpleStringName() {
		return gauche.toSimpleStringName() + droite.toSimpleStringName();
	}
}