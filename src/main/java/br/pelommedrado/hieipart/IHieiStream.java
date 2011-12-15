/**
 * 
 */
package br.pelommedrado.hieipart;

import java.io.IOException;

/**
 * @author Andre Leite
 */
public interface IHieiStream {

	public long read(IHieiPart part) throws IOException;
	
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