/**
 * 
 */
package br.pelommedrado.hieipart.impl;

import br.pelommedrado.hieipart.IHieiPart;

/**
 * @author Andre Leite
 */
public class HieiPart implements IHieiPart {

	/** Indica o inicio da parte **/
	private long off;
	
	/** Indica o tamanho da parte **/
	private long len;
	
	/** Nome da part **/
	private String nome;
	
	/**
	 * Construtor da classe.
	 * 
	 * @param nome
	 * @param off
	 * @param len
	 */
	public HieiPart(String nome, long off, long len) {
		super();
		
		this.off = off;
		this.len = len;
		this.nome = nome;
	}

	/* (non-Javadoc)
	 * @see br.pelommedrado.hieipart.IHieiPart#getOff()
	 */
	@Override
	public long getOff() {
		return off;
	}

	/* (non-Javadoc)
	 * @see br.pelommedrado.hieipart.IHieiPart#getLen()
	 */
	@Override
	public long getLen() {
		return len;
	}

	/* (non-Javadoc)
	 * @see br.pelommedrado.hieipart.IHieiPart#getNome()
	 */
	@Override
	public String getNome() {
		return nome;
	}
}