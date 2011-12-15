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
	public String getNome();
	
	/**
	 * 
	 * @return
	 */
	public int getNumPart();

	/**
	 * 
	 * @return
	 */
	public HieiInfo getInfo();

	/**
	 * 
	 * @param info
	 */
	public void setHieiInfo(HieiInfo info);
	
	/**
	 * 
	 * @return
	 */
	public boolean excluir();
}