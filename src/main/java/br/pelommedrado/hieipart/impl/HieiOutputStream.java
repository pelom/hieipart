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
		//abrir entrada de dados
		in = new FileInputStream(parseFileName(part.getNome()));
		in.skip(part.getOff());

		//abrir saida de dados
		out = new FileOutputStream(part.getNome());

		int bytes = 0;
		long total = 0;
		final byte[] buf = new byte[1024];

		while ((bytes = in.read(buf)) != -1) {
			//o total ja foi atingindo?
			if(total == part.getLen()) {
				return total;

				//os arquivos lidos ultrapassam o tamanho da parte?
			} else if( (total + bytes) > part.getLen()) {

				long read = ( (total+ bytes) - part.getLen() );

				out.write(buf, 0, (int) read);
				total += read;

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