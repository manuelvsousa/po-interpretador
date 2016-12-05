package pex.core;

import pex.core.Interpreter;
import pex.core.Program;
import pex.core.expressions.*;
import pex.core.parser.Parser;

import pex.AppIO;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.io.Serializable;

/**
 * Classe usada para representar um interpretador, que vai correr programas
 *
 * @author Manuel e Goncalo
 */
public class Interpreter implements Serializable {
	private AppIO _app;
	private Map<String, Program> _programs;
	private Map<String, Identifier> _identifiers;
	private boolean _saved;
	private String _fileName;

	/**
	 * Construtor : Inicia os seus atributos e associa um AppIO
	 *
	 * @param app AppIO a associar a este interpretador
	 */
	public Interpreter(AppIO app) {
		_app = app;
		_programs = new HashMap<String, Program>();
		_identifiers = new HashMap<String, Identifier>();
		_saved = false;
		_fileName = "";
	}

	public Identifier checkIdentifier(String str) {
		
	}

	/**
	 * Adiciona um identificador ao interpretador com um valor associado
	 *
	 * @param id Nome do identificador
	 * @param value Valor do identificador
	 */
	public void setIdentifierValue(Identifier id, Expression value) {
		if (!_identifiers.contains(id)) {
			_identifiers.add(id);
		} else {
			//FIX ME
		}
	}

	/**
	 * Devolve o valor do identificador indicado
	 *
	 * @param id Nome do identificador
	 * @return Expression Valor do identificador indicado
	 */
	public Expression getIdentifierValue(Identifier id) {
		//FIX ME
		/*
		int index = _identifiers.indexOf(id);
		if (index != -1) {
		}
		*/
		return null;
	}

	/**
	 * Adiciona um programa ao interpretador
	 *
	 * @param program Programa a adicionar
	 */
	public void addProgram(Program program) {
		if (getProgram(program.getAsText()) == null) {
			_programs.put(program.getAsText(), program);
		}
		//Se o programa ja existir faz o que?
	}

	/**
	 * Devolve o programa com o nome indicado, se existir
	 *
	 * @param name Nome do programa
	 * @return Program programa a devolver
	 */
	public Program getProgram(String name) {
		return _programs.get(name);
	}

	/**
	 * Devolve o AppIO associado a este interpretador
	 *
	 * @return Devolve o AppIO associado a este interpretador
	 */
	public AppIO getAppIO() {
		return _app;
	}

	/**
	 * Devolve true se este interpretador ja foi guardado num ficheiro
	 *
	 * @return boolean Devolve true se este interpretador ja foi guardado num ficheiro
	 */
	public boolean getSaved() {
		return _saved;
	}

	/**
	 * Indica que este interpretador ja foi guardado
	 */
	public void setSaved() {
		_saved = true;
	}

	/**
	 * Devolve o nome do ficheiro em que este interpretador foi guardado
	 *
	 * @return String Nome do ficheiro em que este interpretador foi guardado
	 */
	public String getFileName() {
		return _fileName;
	}

	/**
	 * Associa um novo nome que representa o ficheiro em que este interpretador
	 * foi guardado
	 *
	 * @param file Nome do ficheiro em que este interpretador vai ser guardado
	 */
	public void setFileName(String file) {
		_fileName = file;
	}
}