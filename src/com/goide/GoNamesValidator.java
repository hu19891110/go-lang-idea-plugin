package com.goide;

import com.goide.lexer.GoLexer;
import com.intellij.lang.refactoring.NamesValidator;
import com.intellij.openapi.project.Project;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;

public class GoNamesValidator implements NamesValidator {
  public boolean isKeyword(String name, Project project) {
    return GoParserDefinition.KEYWORDS.contains(getLexerType(name));
  }

  public boolean isIdentifier(String name, Project project) {
    return getLexerType(name) == GoTypes.IDENTIFIER;
  }

  @Nullable
  private static IElementType getLexerType(String text) {
    GoLexer lexer = new GoLexer();
    lexer.start(text);
    return lexer.getTokenEnd() == text.length() ? lexer.getTokenType() : null;
  }
}
