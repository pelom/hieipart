/**
 * 
 */
package br.pelommedrado.hieipart;

/**
 * @author Andre Leite
 */
public interface IHieiPart {
	/** Tamanho maximo de cada parte, 5 MB **/
	public static final int PART_SIZE_MAX = 1 * 1024 * 1024;
	
	/** Entensao do arquivo particionado **/
	public static final String EXT = ".part";
	
	/**
	 * 
	 * @return
	 */
	public long getOff();
	
	/**
	 * 
	 * @return
	 */
	public long getLen();
	
	/**
	 * 
	 * @return
	 */
	public String getNome();
}