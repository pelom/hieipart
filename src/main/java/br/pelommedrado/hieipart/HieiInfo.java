/**
 * 
 */
package br.pelommedrado.hieipart;

import java.util.List;

/**
 * @author Andre Leite
 */
public class HieiInfo {

	/** Tamanho do arquivo **/
	private long size;

	/** Nome do arquivo **/
	private String nome;

	/** Numero de parts **/
	private int numParts;

	/** Partes do arquivo **/
	private List<IHieiPart> parts;

	/**
	 * Construtor da classe.
	 */
	public HieiInfo() {
		super();
	}

	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the parts
	 */
	public List<IHieiPart> getParts() {
		return parts;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @param parts the parts to set
	 */
	public void setParts(List<IHieiPart> parts) {
		this.parts = parts;
	}

	/**
	 * @return the numParts
	 */
	public int getNumParts() {
		return numParts;
	}

	/**
	 * @param numParts the numParts to set
	 */
	public void setNumParts(int numParts) {
		this.numParts = numParts;
	}
}