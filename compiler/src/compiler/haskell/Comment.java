package compiler.haskell;

public class Comment implements Instruction {

    private String content;

    public Comment(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toHaskell() {
        return "\n-- " + this.getContent();
    }
}
