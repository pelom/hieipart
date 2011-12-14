/**
 * 
 */
package br.pelommedrado.hieipart.impl;

import java.io.File;

import br.pelommedrado.hieipart.IHieiFile;
import br.pelommedrado.hieipart.IHieiPart;

/**
 * @author Andre Leite
 */
public class HieiFile implements IHieiFile {
	/** Referencia ao arquivo **/
	private File file = null;
	
	/**
	 * Construtor da classe.
	 * @param pathName
	 */
	public HieiFile(String pathName) {
		super();
	
		this.file = new File(pathName);
	}

	/* (non-Javadoc)
	 * @see br.pelommedrado.hieipart.IHieiFile#isFileExiste()
	 */
	@Override
	public boolean isFileExiste() {
		return file.exists();
	}

	/* (non-Javadoc)
	 * @see br.pelommedrado.hieipart.IHieiFile#getLength()
	 */
	@Override
	public long getLength() {
		return file.length();
	}

	/* (non-Javadoc)
	 * @see br.pelommedrado.hieipart.IHieiFile#getNome()
	 */
	@Override
	public String getPath() {
		return file.getAbsolutePath();
	}

	/**
	 * 
	 */
	@Override
	public int getNumPart() {
		long len = getLength();
		return (int) Math.ceil( ((double) len / IHieiPart.PART_SIZE_MAX) );
	}
}