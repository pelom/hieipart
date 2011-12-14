/**
 * 
 */
package br.pelommedrado.hieipart;

import java.io.IOException;

/**
 * @author Andre Leite
 */
public interface IHieiOutputStream {

	/**
	 * 
	 * @param part
	 * @return
	 * @throws IOException
	 */
	public long write(IHieiPart part) throws IOException;
	
	/**
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException;
}