/**
 * 
 */
package br.pelommedrado.hieipart;

import java.io.IOException;

/**
 * @author Andre Leite
 */
public interface IHieiParticionador {

	public static final String EXT = ".part";
	
	/**
	 * 
	 * @return
	 * @throws IOExceptio
	 */
	public void particionar() throws IOException;
	
	/**
	 * 
	 * @return
	 */
	public IHieiFile getHieiFile();
	
	/**
	 * 
	 * @param hieiFile
	 */
	public void setHieiFile(IHieiFile hieiFile);
}