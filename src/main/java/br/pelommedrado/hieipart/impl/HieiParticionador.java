/**
 * 
 */
package br.pelommedrado.hieipart.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.pelommedrado.hieipart.IHieiFile;
import br.pelommedrado.hieipart.IHieiPart;
import br.pelommedrado.hieipart.IHieiParticionador;

/**
 * @author Andre Leite
 */
public class HieiParticionador implements IHieiParticionador {

	/** Arquivo a ser particionado **/
	private IHieiFile hieiFile = null;

	/**
	 * Construtor da classe.
	 */
	public HieiParticionador() {
		this(null);
	}

	/**
	 * Construtor da classe.
	 * @param hieiFile
	 */
	public HieiParticionador(IHieiFile hieiFile) {
		super();

		this.hieiFile = hieiFile;
	}

	/* (non-Javadoc)
	 * @see br.pelommedrado.hieipart.IHieiParticionador#particionar(br.pelommedrado.hieipart.IHieiFile)
	 */
	@Override
	public List<IHieiPart> particionar() throws IOException {

		if(hieiFile == null) {
			throw new IllegalArgumentException("nenhum arquivo foi informacao");	
		}

		if(!hieiFile.isFileExiste()) {
			throw new IOException("o arquivo " + hieiFile.getPath() + " nao foi encontrado");	
		}

		final List<IHieiPart> parts = new ArrayList<IHieiPart>();

		String nomePart = null;

		//obter o numero de parts do arquivo
		int numPart = hieiFile.getNumPart();
		//tamanho do arquivo
		long size = hieiFile.getLength();
		
		long bytes = 0;
		long falta = 0;

		for (int i = 0; i < numPart; i++) {
			nomePart = hieiFile.getPath() + EXT + i;

			falta = (size - bytes);

			if(falta > IHieiPart.PART_SIZE_MAX) {
				parts.add(new HieiPart(nomePart, bytes, IHieiPart.PART_SIZE_MAX));
				
				bytes += IHieiPart.PART_SIZE_MAX;
				
			} else {
				parts.add(new HieiPart(nomePart, bytes, falta));
				bytes += falta;
			}
		}

		return parts;
	}

	/**
	 * 
	 */
	@Override
	public IHieiFile getHieiFile() {
		return hieiFile;
	}

	/**
	 * 
	 */
	@Override
	public void setHieiFile(IHieiFile hieiFile) {
		this.hieiFile = hieiFile;
	}
}