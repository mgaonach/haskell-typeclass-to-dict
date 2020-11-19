package compiler.haskell;

public class TypeApplication extends Type {

	private Type gauche;
	private Type droite;

	public TypeApplication(Type gauche, Type droite) {
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
	

	@Override
	public String toHaskell() {
		// pas besoin des ()
		return "(" + this.getGauche().toHaskell() + " " + this.getDroite().toHaskell() + ")";
	}
}