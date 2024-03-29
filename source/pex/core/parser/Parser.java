package pex.core.parser;

import pex.core.Interpreter;
import pex.core.Program;
import pex.core.expressions.*;
import pex.core.expressions.operators.*;
import pex.core.parser.*;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.io.IOException;
import java.io.StreamTokenizer;

import java.io.StringReader;
import java.io.Reader;
import java.io.Serializable;

import java.util.Collection;
import java.util.ArrayList;

public class Parser implements Serializable  {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 201608241029L;

    private Program _program;
    private transient StreamTokenizer _tokenizer;
    private Interpreter _interp;
    private boolean _toSet;
    private String _encoding = "UTF-8";

    public Parser() {
        _interp = null;
        _toSet = false;
    }

    private void initTokenizer(Reader reader) {
        _tokenizer = new StreamTokenizer(reader);
        _tokenizer.eolIsSignificant(false);
    }

    public Program parseFile(String fileName, String programName, Interpreter interpreter) throws BadSourceException, BadNumberException, InvalidExpressionException,
        MissingClosingParenthesisException, UnknownOperationException, EndOfInputException  {
        _program = new Program(programName, interpreter, this);
        _interp = interpreter;
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName), _encoding)) {

            initTokenizer(reader);

            Collection<Expression> expressions = new ArrayList<>();
            Expression exp;

            while ((exp = parseExpression()) != null)
                expressions.add(exp);

            _program.add(expressions);
        } catch (IOException ioe) {
            throw new BadSourceException(fileName, ioe);
        }

        return _program;
    }

    public Expression parseString(String expression, Program program) throws BadSourceException, BadNumberException, InvalidExpressionException,
        MissingClosingParenthesisException, UnknownOperationException, EndOfInputException {
        _program = program;
        _interp = _program.getInterpreter();

        try (StringReader reader = new StringReader(expression)) {
            initTokenizer(reader);
            return parseExpression();
        } catch (IOException ioe) {
            throw new BadSourceException(expression, ioe);
        }
    }

    private Expression parseExpression() throws IOException, BadNumberException, InvalidExpressionException, MissingClosingParenthesisException,
        UnknownOperationException, EndOfInputException {
        int token = _tokenizer.nextToken();

        switch (token) {
        case StreamTokenizer.TT_EOF:
            return null;

        case StreamTokenizer.TT_NUMBER: // Literal inteiro
            if (_tokenizer.nval < 0 || _tokenizer.nval - (int)_tokenizer.nval != 0)
                throw new BadNumberException("" + _tokenizer.nval);

            return new LiteralInt((int)_tokenizer.nval);

        case '"':
            return new LiteralString(_tokenizer.sval);

        case StreamTokenizer.TT_WORD:
            if (_toSet) {
                _toSet = false;
                return _program.fetchId(_tokenizer.sval, true);
            } else {
                return _program.fetchId(_tokenizer.sval, false);
            }

        case '(':
            Expression exp = parseCompositeExpression();
            // process closing parenthesis
            if (_tokenizer.nextToken() != ')')
                throw new MissingClosingParenthesisException(exp.toString());

            return exp;

        default:
            throw new InvalidExpressionException(_tokenizer.lineno());

        }
    }

    // Return value cannot be null
    private Expression parseArgument() throws IOException, BadNumberException, UnknownOperationException,
        MissingClosingParenthesisException, EndOfInputException, InvalidExpressionException {
        Expression exp = parseExpression();
        if (exp == null)
            throw new EndOfInputException();

        return exp;
    }

    // the opening '(' was already processed
    private Expression parseCompositeExpression() throws IOException, BadNumberException, UnknownOperationException, MissingClosingParenthesisException,
        EndOfInputException, InvalidExpressionException {
        int token = _tokenizer.nextToken();

        if (token != StreamTokenizer.TT_WORD)
            throw new InvalidExpressionException(_tokenizer.lineno());

        String operatorName = _tokenizer.sval;

        // The way each composite expression is inntanciated mey need to be changed since
        // this depends on the specific code of each group

        switch (operatorName) {

        // process no-args expressions
        case "reads":
            return new ReadS(_program);

        case "readi":
            return new ReadI(_program);

        // processing unary expressions
        case "neg":
            return new Neg(parseArgument());

        case "not":
            return new Not(parseArgument());

        case "call":
            try {
                return new Call((LiteralString)parseArgument(), _program.getInterpreter(), _program);
            } catch (ClassCastException cce) { // it is not a LiteralString
                throw new InvalidExpressionException(_tokenizer.lineno());
            }

        // processing binary expressions
        case "add":
            return new Add(parseArgument(), parseArgument());

        case "mul":
            return new Mul(parseArgument(), parseArgument());

        case "div":
            return new Div(parseArgument(), parseArgument());

        case "mod":
            return new Mod(parseArgument(), parseArgument());

        case "sub":
            return new Sub(parseArgument(), parseArgument());

        case "lt":
            return new Lt(parseArgument(), parseArgument());

        case "le":
            return new Le(parseArgument(), parseArgument());

        case "gt":
            return new Gt(parseArgument(), parseArgument());

        case "ge":
            return new Ge(parseArgument(), parseArgument());

        case "eq":
            return new Eq(parseArgument(), parseArgument());

        case "ne":
            return new Ne(parseArgument(), parseArgument());

        case "and":
            return new And(parseArgument(), parseArgument());

        case "or":
            return new Or(parseArgument(), parseArgument());

        case "set":
            _toSet = true;
            return new Set(parseArgument(), parseArgument(), _program);

        case "while":
            return new While(parseArgument(), parseArgument());

        // processing ternary expressions
        case "if":
            return new If(parseArgument(), parseArgument(), parseArgument());

        // processing variadic expressions
        case "seq":

        case "print":
            // process args
            ArrayList<Expression> args = new ArrayList<>();

            while (true) {
                try {
                    args.add(parseArgument());
                } catch (InvalidExpressionException iee) { // reaching the closing parenthisis of current composite expression
                    if (_tokenizer.ttype == ')') { // no more arguments
                        _tokenizer.pushBack();
                        break;
                    }
                    throw iee;
                }
            }

            if (operatorName.equals("seq"))
                return new Seq(args, _program);
            else
                return new Print(args, _program);

        default:
            throw new UnknownOperationException(_tokenizer.sval);
        }
    }
}
