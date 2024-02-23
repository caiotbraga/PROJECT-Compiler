# Compiler Documentation

## Introduction
This compiler is developed in Java and consists of three main parts: lexical analysis, syntactic analysis, and semantic analysis. The goal of the compiler is to analyze source code written in a specific language and produce an intermediate representation or perform actions based on that analysis.

## Lexical Analysis
Lexical analysis is the first phase of the compilation process, where the source code is scanned and converted into tokens. Each token represents a lexical element of the language, such as keywords, identifiers, operators, etc. The provided source code implements this analysis in two main classes: `Token` and `Lexicon`.

## Class `Token`
The `Token` class defines the possible token types and provides methods to get the lexeme and type of a token. The token types are:

- `INT_TYPE`: Integer type
- `DOUBLE_TYPE`: Double type
- `CHAR_TYPE`: Character type
- `IDENTIFIER_TYPE`: Identifier
- `RELATIONAL_OPERATOR_TYPE`: Relational operator
- `ARITHMETIC_OPERATOR_TYPE`: Arithmetic operator
- `SPECIAL_CHARACTER_TYPE`: Special character
- `RESERVED_WORD_TYPE`: Reserved word
- `ASSIGNMENT_OPERATOR_TYPE`: Assignment operator
- `RAISED_CAIO_TYPE`: Special token "RAISED CAIO"
- `TOKEN_A`: Special token "TOKEN A"
- `RING_0`: Special token "RING 0"
- `STRING_TYPE`: String type
- `END_CODE_TYPE`: Token indicating end of source code

The `Token` class also implements methods to represent the token as a string.

## Class `Lexicon`
The `Lexicon` class is responsible for scanning the source code and producing the corresponding tokens. It uses a state machine to traverse the source code and identify tokens. Tokens are identified based on the characters encountered and the current context. The `nextToken()` method traverses the source code and returns the next token encountered.

## Syntactic Analysis
Syntactic analysis is the second phase of the compilation process, where the tokens produced by lexical analysis are grouped and checked for compliance with the language's grammar. This phase is responsible for constructing the syntax tree of the source code. In the provided code, syntactic analysis is handled by the `Synthetic` class.

## Class `Synthetic`
The `Synthetic` class implements the grammar rules of the compiled language. It uses the tokens produced by lexical analysis to build the syntax tree and perform compliance checks. The `s()` method is the entry point for syntactic analysis and represents the start symbol of the grammar.

## Semantic Analysis
Semantic analysis is the third phase of the compilation process, where meaning and context checks are made in the source code. This phase ensures that the code is semantically correct. In the provided code, semantic analysis is handled by the `SinglyLinkedList` and `SinglyListNode` classes.

## Classes `SinglyLinkedList` and `SinglyListNode`
These classes implement a simple linked list, which can be used to perform more advanced semantic analyses. They provide methods for adding and searching elements in the list.

## Conclusion
This compiler implements the main phases of the compilation process and provides a basic structure for lexical, syntactic, and semantic analysis. It can be expanded and enhanced to handle a variety of programming languages.

## Supported Languages
Based on the current implementation, the compiler is able to handle a language that includes:

- Data types: int, double, char, string
- Arithmetic operators: +, -, *, /, %
- Relational operators: <, >, <=, >=, ==
- Assignment operator: =
- Keywords: int, double, float, char, String, Integer, if, else, while, for, main, Boolean
- Special characters: (, ), {, }, ,, ;
- Special tokens: RAISED CAIO, TOKEN A, RING 0
- Identifiers and character and string literals.

This compiler can be extended to support more language features and constructs as needed.

## License

This project is licensed under the [MIT License](LICENSE).
