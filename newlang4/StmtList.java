package newlang4;
import java.util.*;

public class StmtList extends Node{

  static final Set<LexicalType> firstSet = EnumSet.of(
      LexicalType.NAME, LexicalType.FOR, LexicalType.END,
      LexicalType.WHILE, LexicalType.IF, LexicalType.DO
      );

  Environment env;
  LexicalUnit first;

  private StmtList(LexicalUnit first, Environment env) {
    this.env = env;
    this.first = first;
  }

  public static Node getHandler(LexicalUnit unit, Environment env){
    return new StmtList(unit, env);
  }

  public static boolean isFirst(LexicalUnit unit){
    return firstSet.contains(unit.getType());
  }

  // @Override
  public boolean parse() throws Exception{
    LexicalUnit first = env.getInput().get();

    while(true){
      if(Stmt.isFirst(first)){
        Node handler = Stmt.getHandler(first, env);
        return handler.parse();
      }else if(Block.isFirst(first)){
        Node handler = Block.getHandler(first, env);
        return handler.parse();
      }else break;
    }
    return false;
  }
}
