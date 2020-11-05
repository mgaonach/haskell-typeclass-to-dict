package compiler.haskell;

import java.util.List;

public class Instance {

    private TypeApplication type;
    private List<Attribution> functions;

    public Instance(TypeApplication type, List<Attribution> functions) {
        this.type = type;
        this.functions = functions;
    }

    public TypeApplication getType() {
        return type;
    }

    public List<Attribution> getFunctions() {
        return functions;
    }

}
