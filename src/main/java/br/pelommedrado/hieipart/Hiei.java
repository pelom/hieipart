/**
 * 
 */
package br.pelommedrado.hieipart;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import br.pelommedrado.hieipart.impl.HieiFile;
import br.pelommedrado.hieipart.impl.HieiPart;
import br.pelommedrado.hieipart.impl.HieiParticionador;
import br.pelommedrado.hieipart.impl.HieiStream;

/**
 * @author Andre Leite
 */
public class Hiei {

	/** Extensao do arquivo com as informacao do retalhamento do arquivo **/
	public static final String EXT_INFO = ".hieipart";

	/** Responsavel por escrever e ler a parts do arquivo **/
	private IHieiStream hieiStream;

	/**
	 * Construtor da classe.
	 */
	public Hiei() {
		super();

		//crair saido da particicao do arquivo para disco
		this.hieiStream = new HieiStream();
	}

	/**
	 * 
	 * @param file
	 * @throws IOException 
	 */
	public void compor(String pathName) throws IOException {
		final File fileHieiPart = new File(pathName + EXT_INFO);

		//o arquivo de comporsicao nao existe 
		if(!fileHieiPart.exists()) {
			throw new IOException("nao foi possivel encotrar o arquivo de composicao " + fileHieiPart);
		}

		final IHieiFile hieiFile = carregarFileParts(fileHieiPart);
		try {
			//varrer cada particao do arquivo
			for (IHieiPart iHieiPart : hieiFile.getInfo().getParts()) {
				hieiStream.read(iHieiPart);
			}

		} finally {
			//fechar IO
			hieiStream.close();
		}
		
		//o arquivo foi gerado corretamente?
		if(hieiFile.isFileExiste()) {
			//varrer cada particao do arquivo
			for (IHieiPart iHieiPart : hieiFile.getInfo().getParts()) {
				new File(iHieiPart.getNome()).delete();
			}
			fileHieiPart.delete();
		}
	}

	/**
	 * 
	 * @param file
	 * @throws IOException 
	 */
	public void retalhar(IHieiFile file) throws IOException {
		//criar particionador de arquivo
		IHieiParticionador particionador = new HieiParticionador(file);
		//realizar o processo de particionamento do arquivo
		particionador.particionar();

		try {
			//varrer cada particao doa rquivo
			for (IHieiPart iHieiPart : file.getInfo().getParts()) {
				//escrever particao no disco
				hieiStream.write(iHieiPart);
			}

		} finally {
			//fechar IO
			hieiStream.close();
		}

		//criar arquivo de infoa
		criarFileParts(file);
		
		file.excluir();
	}

	/**
	 * @param file
	 * @param parts
	 * @throws IOException
	 */
	private void criarFileParts(IHieiFile file) throws IOException {
		final Properties props = new Properties();
		props.setProperty("nome", file.getNome());
		props.setProperty("size", String.valueOf(file.getLength()));
		props.setProperty("numParts", String.valueOf(file.getNumPart()));

		int i = 0;
		File filePart = null;
		for (IHieiPart iHieiFile : file.getInfo().getParts()) {
			filePart = new File(iHieiFile.getNome());
			props.setProperty("part" + i, filePart.getName());
			props.setProperty("partSize" + i, String.valueOf(filePart.length()));
			i++;
		}

		final FileOutputStream fo = new FileOutputStream(file.getPath() + EXT_INFO);
		props.store(fo, "parametros para reconstruir o arquivo");
		fo.flush();
		fo.close();
	}

	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private IHieiFile carregarFileParts(File file) throws IOException {
		final Properties props = new Properties();
		final FileInputStream fi = new FileInputStream(file);
		props.load(fi);
		fi.close();

		final HieiInfo info = new HieiInfo();
		info.setNome(props.getProperty("nome"));
		info.setSize(Long.valueOf(props.getProperty("size")));
		info.setNumParts(Integer.valueOf(props.getProperty("numParts")));

		final List<IHieiPart> parts = new ArrayList<IHieiPart>();
		long off = 0;
		for (int i = 0; i < info.getNumParts(); i++) {
			String nome = file.getParent() + File.separator + props.getProperty("part" + i);
			long len 	= Long.valueOf(props.getProperty("partSize" + i));
			parts.add(new HieiPart(nome, off, len));
			off += len;
		}

		info.setParts(parts);

		IHieiFile hieiFile = 
				new HieiFile(file.getParent() + File.separator + info.getNome());
		hieiFile.setHieiInfo(info);
		
		return hieiFile;
	}
}