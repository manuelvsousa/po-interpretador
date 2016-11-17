package pex.app.main;

import pex.app.main.Interpreter;
import pex.app.main.Program;
import pex.core.expressions.*;
import pex.core.parser.Parser;

import pex.AppIO;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.io.Serializable;


public class Interpreter implements Serializable {
	private AppIO _app;
	private List<Program> _programs;
	private List<Identifier> _identifiers;
	private List<Expression> _values;
	private boolean _saved;
	private String _fileName;

	public Interpreter(AppIO app) {
		_app = app;
		_programs = new ArrayList<Program>();
		_identifiers = new ArrayList<Identifier>();
		_values = new ArrayList<Expression>();
		_saved = false;
		_fileName = "";

	}

	public void setIdentifierValue(Identifier id, Expression value) {
		if (!_identifiers.contains(id)) {
			_identifiers.add(id);
			_values.add(value);
		} else {
			_values.set(_identifiers.indexOf(id), value);
		}
	}

	public Expression getIdentifierValue(Identifier id) {
		int index = _identifiers.indexOf(id);
		if (index != -1) {
			return _values.get(index);
		}
		//FIX ME : Erro a encontrar identificador
		return new LiteralInt(5);
	}

	public void addProgram(Program program) {
		_programs.add(program);
	}

	public Program getProgram(String name) {
		for (Program program : _programs) {
			if (program.getAsText() == name) {
				return program;
			}
		}
		//FIX ME : Erro a encontrar programa
		return new Program("Erro", this);
	}

	public AppIO getAppIO() {
		return _app;
	}

	public boolean getSaved() {
		return _saved;
	}

	public void setSaved() {
		_saved = true;
	}

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String file) {
		_fileName = file;
	}
}