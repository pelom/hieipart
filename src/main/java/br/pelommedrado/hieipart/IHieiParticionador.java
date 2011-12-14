/**
 * 
 */
package br.pelommedrado.hieipart;

import java.io.IOException;
import java.util.List;

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
	public List<IHieiPart> particionar() throws IOException;
	
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