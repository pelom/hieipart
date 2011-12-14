/**
 * 
 */
package br.pelommedrado.hieipart;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import br.pelommedrado.hieipart.impl.HieiOutputStream;
import br.pelommedrado.hieipart.impl.HieiParticionador;

/**
 * @author Andre Leite
 */
public class HieiPart {

	/** Extensao do arquivo com as informacao do retalhamento do arquivo **/
	public static final String EXT_INFO = ".hieiparts";
	
	/** Responsavel por escrever a parts do arquivo **/
	private IHieiOutputStream output;

	/**
	 * Construtor da classe.
	 */
	public HieiPart() {
		super();

		this.output = new HieiOutputStream();
	}

	/**
	 * 
	 * @param file
	 * @throws IOException 
	 */
	public void retalhar(IHieiFile file) throws IOException {
		IHieiParticionador particionador = new HieiParticionador(file);
		List<IHieiPart> parts = particionador.particionar();

		try {
			for (IHieiPart iHieiPart : parts) {
				output.write(iHieiPart);
			}
		} finally {
			output.close();
		}
		
		criarFileParts(file, parts);
	}

	/**
	 * a
	 * @param file
	 * @param parts
	 * @throws IOException
	 */
	private void criarFileParts(IHieiFile file, List<IHieiPart> parts) throws IOException {
		final File fileOriginal  = new File(file.getPath());
		
		Properties props = new Properties();
		props.setProperty("nome", fileOriginal.getName());
		props.setProperty("size", String.valueOf(fileOriginal.length()));
		props.setProperty("numParts", String.valueOf(parts.size()));
		
		int i = 0;
		File filePart = null;
		for (IHieiPart iHieiFile : parts) {
			filePart = new File(iHieiFile.getNome());
			props.setProperty("part" + i, filePart.getName());
			props.setProperty("partSize" + i, String.valueOf(filePart.length()));
			i++;
		}

		final FileOutputStream fo = new FileOutputStream(file.getPath() + ".hieiparts");
		props.store(fo, "parametros para reconstruir o arquivo");
		fo.flush();
		fo.close();
	}
}