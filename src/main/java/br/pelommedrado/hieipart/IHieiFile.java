/**
 * 
 */
package br.pelommedrado.hieipart;

/**
 * @author Andre Leite
 */
public interface IHieiFile {

	/**
	 * 
	 * @return
	 */
	public boolean isFileExiste();
	
	/**
	 * 
	 * @return
	 */
	public long getLength();
	
	/**
	 * 
	 * @return
	 */
	public String getPath();
	
	/**
	 * 
	 * @return
	 */
	public int getNumPart();
}