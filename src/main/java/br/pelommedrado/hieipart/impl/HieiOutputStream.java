/**
 * 
 */
package br.pelommedrado.hieipart.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import br.pelommedrado.hieipart.IHieiOutputStream;
import br.pelommedrado.hieipart.IHieiPart;
import br.pelommedrado.hieipart.IHieiParticionador;

/**
 * @author Andre Leite
 */
public class HieiOutputStream implements IHieiOutputStream {

	/** Entrada de dados **/
	private FileInputStream in;

	/** Saida de dados **/
	private FileOutputStream out;

	/**
	 * Construtor da classe.
	 */
	public HieiOutputStream() {
		super();
	}

	/* (non-Javadoc)
	 * @see br.pelommedrado.hieipart.IHieiOutputStream#write(br.pelommedrado.hieipart.IHieiPart)
	 */
	@Override
	public long write(IHieiPart part) throws IOException {
		in = new FileInputStream(parseFileName(part.getNome()));
		in.skip(part.getOff());
		
		out = new FileOutputStream(part.getNome());

		byte[] buf = new byte[1024];
		int bytes = 0;
		long total = 0;

		while ((bytes = in.read(buf)) != -1) {

			if(total == part.getLen()) {
				return total;
				
			} else if( (total + bytes) > part.getLen()) {
				long l = ( (total+ bytes) - part.getLen() );
				
				out.write(buf, 0, (int) l);
				total += l;
				
				return total;
			}

			out.write(buf, 0, bytes);
			total += bytes;
		}
		
		return total;
	}

	/**
	 * 
	 * @param nome
	 * @return
	 */
	private String parseFileName(String nome) {
		return nome.replaceAll("\\" + IHieiParticionador.EXT + "[0-9]*", "");
	}

	/**
	 * 
	 */
	@Override
	public void close() throws IOException {
		if(in != null) {
			in.close();
		}

		if(out != null) {
			out.close();
		}
	}
}