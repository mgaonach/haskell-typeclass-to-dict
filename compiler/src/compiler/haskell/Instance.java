package compiler.haskell;

import java.util.Arrays;
import java.util.List;

//Instance := Type(only App) Attribution*
public class Instance {

    private TypeApplication instanceType;
    private List<Attribution> attributions;

    public Instance(TypeApplication instanceType, Attribution... attributions) {
        this.instanceType = instanceType;
        this.attributions = Arrays.asList(attributions);
    }

}
