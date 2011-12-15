/**
 * 
 */
package br.pelommedrado.hieipart;

import java.io.IOException;

/**
 * @author Andre Leite
 */
public interface IHieiParticionador {
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