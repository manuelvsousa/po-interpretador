package pex.core;

import java.io.*;

import pex.AppIO;
import pex.core.Interpreter;

import pex.core.parser.Parser;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.io.Serializable;

/**
 * Classe usada para representar um handler, sobre o qual podem ser executados
 * comando por parte do utilizador.
 *
 * @author Manuel e Goncalo
 */
public class Handler implements Serializable {
	/** Serial number for serialization. */
	private static final long serialVersionUID = 201608241029L;
	private AppIO _app;
	private Interpreter _interpretador;
	private boolean _changed;
	private Parser _parser;

	/**
	 * Contrutor: Inicia _interpretador com o interpretador recebido
	 */
	public Handler(AppIO app) {
		_app = app;
		_interpretador = new Interpreter(this);
		_changed = true;
		_parser = new Parser();
	}

	/**
	*	COMANDOS
	*/

	/**
	 * Cria um novo interpretador
	 */
	public void newInterpreter() {
		_interpretador = new Interpreter(this);
		_changed = true;
	}

	/**
	 * Devolve o interpretador atual
	 *
	 * @return Interpreter Retorna o interpretador atual
	 */
	public Interpreter getInterperter() {
		return _interpretador;
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
	 * Devolve uma int lida pela AppIO
	 *
	 * @param str A string a mostrar ao pedir a int
	 * @return int A int lida pelo AppIO
	 */
	public int requestInt(String str) {
		return _app.readInteger(str);
	}

	/**
	 * Devolve uma string lida pela AppIO
	 *
	 * @param str A string a mostrar ao pedir a string
	 * @return String A string lida pelo AppIO
	 */
	public String requestString(String str) {
		return _app.readString(str);
	}

	/**
	 * Pede que a AppIO imprima a string dada
	 *
	 * @param str A string a imprimir pela AppIO
	 */
	public void requestPrint(String str) {
		_app.println(str);
	}

	/**
	 * Associa um interpretador localizado num ficheiro atraves da serializacao
	 *
	 * @param file O nome do ficheiro no qual esta guardado o interpretador
	 */
	public void openInterpreter(String file) throws WriteAbortedException, IOException, ClassNotFoundException {
		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		try {
			fileIn = new FileInputStream(file);
			in = new ObjectInputStream(fileIn);
			_interpretador = (Interpreter)in.readObject();
		} catch (FileNotFoundException e) {
			throw e;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (fileIn != null) {
					fileIn.close();
				}
			} catch (Exception e) {
				throw e;
			}

		}
	}

	/**
	 * Guarda o interpredator atual num ficheiro
	 *
	 * @param file O nome do ficheiro no qual vai estar guardado o interpretador
	 */
	public void saveInterpreter(String file) throws IOException {
		if (_changed) {
			FileOutputStream fileOut = null;
			ObjectOutputStream out = null;
			try {
				fileOut = new FileOutputStream(file);
				out = new ObjectOutputStream(fileOut);
				_interpretador.setSaved();
				_interpretador.setFileName(file);
				_changed = false;
				out.writeObject(_interpretador);
				out.flush();
			} catch (Exception e) {
				throw e;
			} finally {
				try {
					if (out != null) {
						out.close();
					}
					if (fileOut != null) {
						fileOut.close();
					}
				} catch (Exception e) {
					throw e;
				}

			}
		}
	}

	/**
	 * Guarda o interpredator atual no seu ficheiro associado
	 */
	public void saveInterpreter() throws IOException {
		if (_changed) {
			FileOutputStream fileOut = null;
			ObjectOutputStream out = null;
			try {
				fileOut = new FileOutputStream(_interpretador.getFileName());
				out = new ObjectOutputStream(fileOut);
				_changed = false;
				out.writeObject(_interpretador);
			} catch (Exception e) {
				throw e;
			} finally {
				try {
					if (out != null) {
						out.close();
					}
					if (fileOut != null) {
						fileOut.close();
					}
				} catch (Exception e) {
					throw e;
				}

			}
		}
	}

	/**
	 * Devolve true se o interpredator ja foi guardado num ficheiro
	 *
	 * @return boolean Retorna true se o interpretador ja tiver sido guardado num ficheiro
	 */
	public boolean checkSaved() {
		return _interpretador.getSaved();
	}

	/**
	 * Cria um novo programa e adiciona-o ao interpretador
	 *
	 * @param name Nome do programa a criar
	 */
	public void createProgram(String name) {
		Program prog = new Program(name, _interpretador, _parser);
		_interpretador.addProgram(prog, _parser);
		_changed = true;
	}

	/**
	 * Adiciona um novo programa ao interpretador
	 *
	 * @param programa Nome do programa a acidionar
	 */
	public void addProgram(Program programa) {
		_interpretador.addProgram(programa, _parser);
		_changed = true;
	}

	/**
	 * Le um programa de um ficheiro e adiciona-o ao interpretador
	 *
	 * @param file Nome do ficheiro a ler
	 */
	public void readProgram(String file) throws WriteAbortedException, IOException, ClassNotFoundException {
		try {
			_interpretador.addProgram(_parser.parseFile(file, file, _interpretador), _parser);
			_changed = true;
		} catch (Exception e) {
			throw new FileNotFoundException(file);
		}
	}

	/**
	 * Escreve o programa indicado no ficheiro indicado
	 *
	 * @param name Nome do programa a guardar
	 * @param file Nome do ficheiro onde guardar
	 */
	public void writeProgram(String name, String file) throws IOException {

		Program prog = _interpretador.getProgram(name);
		if (prog != null) {
			try {
				prog.saveProgram(file);
				_changed = true;
			} catch (Exception e) {
				throw new IOException();
			}
		}
	}

	/**
	 * Devolve o programa com o nome indicado para poder ser editado
	 *
	 * @param name Nome do programa a devolver
	 * @return Program Retorna o programa a editar
	 */
	public Program editProgram(String name) {
		return _interpretador.getProgram(name);
	}

	/**
	 * Verifica se o programa indicado existe
	 *
	 * @param boolean name Nome do programa a verificar
	 */
	public boolean checkProgram(String name) {
		Program prog = _interpretador.getProgram(name);
		return (prog != null);
	}
}
