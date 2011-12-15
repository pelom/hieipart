/**
 * 
 */
package br.pelommedrado.hieipart.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import br.pelommedrado.hieipart.IHieiPart;
import br.pelommedrado.hieipart.IHieiParticionador;
import br.pelommedrado.hieipart.IHieiStream;

/**
 * @author Andre Leite
 */
public class HieiStream implements IHieiStream {

	/** Entrada de dados **/
	private FileInputStream in;

	/** Saida de dados **/
	private RandomAccessFile out;

	/**
	 * Construtor da classe.
	 */
	public HieiStream() {
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
		out = new RandomAccessFile(part.getNome(), "rw");

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

	/**
	 * 
	 */
	@Override
	public long read(IHieiPart part) throws IOException {
		//abrir entrada de dados
		in = new FileInputStream(part.getNome());
		
		//abrir saida de dados
		out = new RandomAccessFile(parseFileName(part.getNome()), "rw");
		out.seek(part.getOff());
		
		int bytes = 0;
		long total = 0;
		final byte[] buf = new byte[1024];

		while ((bytes = in.read(buf)) != -1) {
			out.write(buf, 0, bytes);
			total += bytes;
		}
		
		return total;
	}
}