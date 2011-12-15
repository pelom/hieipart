/**
 * 
 */
package br.pelommedrado.hieipart.impl;

import java.io.File;

import br.pelommedrado.hieipart.HieiInfo;
import br.pelommedrado.hieipart.IHieiFile;
import br.pelommedrado.hieipart.IHieiPart;

/**
 * @author Andre Leite
 */
public class HieiFile implements IHieiFile {
	/** Referencia ao arquivo **/
	private File file = null;
	
	/** Informacao do arquivo retalhado **/
	private HieiInfo info;
	
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

	/**
	 * 
	 */
	@Override
	public HieiInfo getInfo() {
		return info;
	}

	/**
	 * 
	 */
	@Override
	public void setHieiInfo(HieiInfo info) {
		this.info = info;
	}
	
	/**
	 * 
	 */
	@Override
	public String getNome() {
		return file.getName();
	}

	/**
	 * 
	 */
	@Override
	public boolean excluir() {
		return file.delete();
	}
}